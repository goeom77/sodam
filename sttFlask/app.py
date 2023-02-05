from flask import Flask, jsonify, request
import sttUtil
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
        'name': data['name'],
        'url': data['url'],
    }
    url = response.get("url")
    # asyncio.run(sttUtil.transcribe_gcs(url))
    sttUtil.transcribe_gcs(url)
    # print('async???')
    return jsonify(response), 201


if __name__=="__main__":
    gcpCredentials.save_credentials()
    app.run(debug=True)
    app.run(host="127.0.0.1", port="5000", debug=True)