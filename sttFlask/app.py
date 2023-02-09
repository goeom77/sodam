from flask import Flask, jsonify, request
import sttUtil
import emailUtil
import asyncio
import gcpCredentials


app = Flask(__name__)

@app.route('/')
def hello_world():
    response = ''
    return response

@app.route("/stt", methods=['POST'])
def speechToText():
    data = request.json
    response = {
        'counselor_name': data['counselor_name'],
        'url': data['url'],
    }
    url = response.get("url")
    # asyncio.run(sttUtil.transcribe_gcs(url))

    date_str = data['date'].replace(".", "_")
    name = data['counselor_name']
    file_name = date_str + '_' + name

    file_url = sttUtil.transcribe_gcs(url, file_name)
    print(file_url)
    emailUtil.stt_send_mail(data['date'], data['counselor_email'], name, file_url)
    return jsonify(response), 200


if __name__ == "__main__":
    gcpCredentials.save_credentials()
    app.run(debug=True)
    app.run(host="127.0.0.1", port="8280", debug=True)
