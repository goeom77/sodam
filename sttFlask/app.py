from flask import Flask, jsonify, request, send_file
import json
import sttUtil
import emailUtil
# import gcpCredentials
import requests
from flask_sqlalchemy import SQLAlchemy
import config


def create_app():
    app = Flask(__name__)
    app.config.from_object(config)
    db = SQLAlchemy(app)

    class SttData(db.Model):
        __tablename__ = 'stt_data'

        id = db.Column(db.BigInteger, primary_key=True, nullable=False, autoincrement=True)
        file_name = db.Column(db.String(100, 'utf8mb4_0900_ai_ci'))
        gcs_directory = db.Column(db.String(100, 'utf8mb4_0900_ai_ci'))
        url = db.Column(db.String(200, 'utf8mb4_0900_ai_ci'))
        session_id = db.Column(db.BigInteger)

        def __init__(self, file_name, gcs_directory, url, session_id):
            self.file_name = file_name
            self.gcs_directory = gcs_directory
            self.url = url
            self.session_id = session_id

    class ConsultSession(db.Model):
        __tablename__ = 'consult_session'

        id = db.Column(db.Integer, primary_key=True, nullable=False, autoincrement=True)
        client_id = db.Column(db.String(255, 'utf8mb4_0900_ai_ci'))
        counselor_id = db.Column(db.String(255, 'utf8mb4_0900_ai_ci'))
        status = db.Column(db.Integer)
        openvidu_id = db.Column(db.String(255, 'utf8mb4_0900_ai_ci'))
        stt_status = db.Column(db.Integer)

        def __init__(self, client_id, counselor_id, status, openvidu_id, stt_status):
            self.client_id = client_id
            self.counselor_id = counselor_id
            self.status = status
            self.openvidu_id = openvidu_id
            self.stt_status = stt_status


    @app.route('/test')
    def hello_world():
        response = ''
        data = request.json
        session_id = data['session_id']
        result = db.session.query(SttData).filter(SttData.session_id == session_id).first()
        print(result)
        print(result.id)

        ed_data = db.session.query(ConsultSession).filter(ConsultSession.id == session_id)
        if ed_data:
            print(ed_data)
            # db.session.add(ed_data)
            # ed_data.stt_status = 1
            # print(db.session.dirty)
            # db.session.commit()

        user = db.session.query(ConsultSession).filter(ConsultSession.id == session_id).update({'stt_status': 1});
        db.session.commit()
        # db_value = db.session.execute(str("SELECT * from stt_data where session_id = " + str(session_id))).fetchall()
        # if db_value:
        #     print(db_value)
        #     print(type(db_value))
        #     return json.dumps({'dd': 'dd'}), 200
        return jsonify(response), 200

    @app.route("/stt/do", methods=['POST'])
    def speechToText():
        data = request.json
        response = {
            'counselor_name': data['counselor_name'],
            'session_id': data['session_id']
        }
        url = response.get("url")
        key = data['key']

        # date_str = data['date'].replace(".", "_")
        name = data['counselor_name']
        file_name = data['file_name']
        destination_blob_name = data['gcs_directory']

        session_id = response.get("session_id")
        session_data = db.session.query(ConsultSession).filter(ConsultSession.id == session_id).first()
        if session_data is None:
            return jsonify(), 404
        elif session_data.stt_status == 2:
            # 이미 완료한 건 재요청
            return jsonify(), 409


        db.session.query(ConsultSession).filter(ConsultSession.id == session_id).update({'stt_status': 1})
        db.session.commit()

        file_url = sttUtil.transcribe_gcs(url, destination_blob_name, file_name, key)
        if file_url is None:
            db.session.query(ConsultSession).filter(ConsultSession.id == session_id).update({'stt_status': -1})
            db.session.commit()
        else:
            db.session.query(ConsultSession).filter(ConsultSession.id == session_id).update({'stt_status': 2})
            db.session.commit()

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
        return json.dumps({'data': stt_text}, ensure_ascii=False), 200

    return app

if __name__ == "__main__":
    # gcpCredentials.save_credentials()
    app = create_app()
    app.run(host="0.0.0.0", port='8280', debug=True)
