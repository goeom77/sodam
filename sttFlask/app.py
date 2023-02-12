from flask import Flask, jsonify, request, send_file
import json
import sttUtil
import emailUtil
import asyncio
# import gcpCredentials
import requests

app = Flask(__name__)

@app.route('/')
def hello_world():
    response = ''
    return response

@app.route("/stt/do", methods=['POST'])
def speechToText():
    data = request.json
    response = {
        'counselor_name': data['counselor_name'],
        'url': data['url'],
    }
    url = response.get("url")
    # asyncio.run(sttUtil.transcribe_gcs(url))
    key = data['key']
    print(key)

    # date_str = data['date'].replace(".", "_")
    name = data['counselor_name']
    file_name = data['file_name']
    destination_blob_name = data['gcs_directory']
    file_url = sttUtil.transcribe_gcs(url, destination_blob_name, file_name, key)
    print(file_url)
    # emailUtil.stt_send_mail(data['date'], data['counselor_email'], name, file_url)

    return jsonify(response), 200


@app.route("/stt/audio", methods=['POST', 'GET'])
def get_stt_audio():
    data = request.json
    # key = data['key']
    key = 'mykey'
    print(key)
    print(data)

    # source_dir = data['gcs_directory']
    source_dir = "recording/2023/FEBRUARY/"
    # file_name = data['file_name']
    file_name = '0d6c94b1-7e29-4b26-969e-5f2b76daa39f_SessionA~2'

    stt_text = sttUtil.get_text(source_dir, file_name, key)

    audio_url = sttUtil.get_file(source_dir, file_name, key)

    print(stt_text)
    print(audio_url)

    # import requests
    # import re
    # from urllib.parse import unquote
    #
    # response = requests.get(audio_url)
    #
    # file_name = re.findall("filename=(.+)", response.headers.get("content-disposition"))
    #
    # real_file_name = unquote(file_name[0]).replace('"', "")
    #
    # open(real_file_name, "wb").write(response.content)
    # requests.post("http://192.168.0.1/test", files={"userfile": open(audio_url, 'rb')})
    return send_file(audio_url, as_attachment=True,  attachment_filename='file_name.webm'), 200


@app.route("/stt/text", methods=['POST'])
def get_stt_text():
    data = request.json
    key = data['key']
    print(key)
    print(data)

    source_dir = data['gcs_directory']
    file_name = data['file_name']

    stt_text = sttUtil.get_text(source_dir, file_name, key)

    print(stt_text)
    # import requests
    # import re
    # from urllib.parse import unquote
    #
    # response = requests.get(audio_url)
    #
    # file_name = re.findall("filename=(.+)", response.headers.get("content-disposition"))
    #
    # real_file_name = unquote(file_name[0]).replace('"', "")
    #
    # open(real_file_name, "wb").write(response.content)
    # requests.post("http://192.168.0.1/test", files={"userfile": open(audio_url, 'rb')})
    return json.dumps({'data': stt_text}, ensure_ascii=False), 200


if __name__ == "__main__":
    # gcpCredentials.save_credentials()
    app.run(debug=True)
    app.run(host="127.0.0.1", port="8280", debug=True)
