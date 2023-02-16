from flask import Flask, jsonify, request, send_file
import json
import sttUtil
import emailUtil
# import gcpCredentials
import requests
from flask_sqlalchemy import SQLAlchemy
from datetime import datetime
import config
import grpc
import traceback
from datetime import datetime


db = SQLAlchemy()
def create_app():
    app = Flask(__name__)
    app.config.from_object(config)
    db.init_app(app)

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

    class ConsultSchedule(db.Model):
        __tablename__ = 'consult_schedule'

        id = db.Column(db.BigInteger, primary_key=True, nullable=False, autoincrement=True)
        consult_type = db.Column(db.Integer)
        content = db.Column(db.String(255, 'utf8mb4_0900_ai_ci'))

        email = db.Column(db.String(255, 'utf8mb4_0900_ai_ci'))
        is_consult = db.Column(db.String(255, 'utf8mb4_0900_ai_ci'))
        session_id = db.Column(db.Integer)
        state = db.Column(db.Integer)
        stt_status = db.Column(db.Integer)
        tel = db.Column(db.String(255, 'utf8mb4_0900_ai_ci'))
        turn = db.Column(db.Integer)
        consult_result = db.Column(db.String(255, 'utf8mb4_0900_ai_ci'))

        day_time = db.Column(db.DateTime)
        first_day_time = db.Column(db.DateTime)
        date_time = db.Column(db.DateTime)

    @app.route('/test', methods=['GET'])
    def hello_world():
        # response = ''
        # data = request.json
        # session_id = data['session_id']
        # session_id = 100
        # result = db.session.query(SttData).filter(SttData.session_id == session_id).first()
        # print(result)
        # print(result.id)
        #
        # ed_data = db.session.query(ConsultSession).filter(ConsultSession.id == session_id)
        # if ed_data:
        #     print(ed_data)
        #     # db.session.add(ed_data)
        #     # ed_data.stt_status = 1
        #     # print(db.session.dirty)
        #     # db.session.commit()
        #
        # user = db.session.query(ConsultSession).filter(ConsultSession.id == session_id).update({'stt_status': 1});
        # db.session.commit()

        file_url = sttUtil.transcribe_gcs("gs://stt-bucket-binu/recording/2023/FEBRUARY/SessionA_2", "recording/2023/FEBRUARY/", "SessionA_2", "mykey")
        # db_value = db.session.execute(str("SELECT * from stt_data where session_id = " + str(session_id))).fetchall()
        # if db_value:
        #     print(db_value)
        #     print(type(db_value))
        #     return json.dumps({'dd': 'dd'}), 200
        return jsonify("dd"), 200

    @app.route("/stt/do", methods=['POST'])
    def speechToText():
        data = request.json
        response = {
            # 'counselor_name': data['counselor_name'],
            'scheduleId': data['scheduleId']
        }
        url = data['url']
        key = data['key']
        # name = data['counselor_name']
        file_name = data['file_name']
        destination_blob_name = data['gcs_directory']
        # date_str = data['date'].replace(".", "_")

        schedule_id = data['scheduleId']
        session_data = db.session.query(ConsultSchedule).filter(ConsultSchedule.id == schedule_id).first()
        if session_data is None:
            return jsonify(), 404
        elif session_data.stt_status == 2:
            # 이미 완료한 건 재요청 x
            return jsonify(), 409
        db.session.query(ConsultSchedule).filter(ConsultSchedule.id == schedule_id).update({'stt_status': 1})
        db.session.commit()

        status = sttUtil.prepare_data(url, destination_blob_name, file_name, key)
        status_val = -1
        print(status)
        if status[0] and status[1]:
            status_val = 2
        elif status[0]:
            status_val = 3
        elif status[1]:
            status_val = 4

        db.session.query(ConsultSchedule).filter(ConsultSchedule.id == schedule_id).update({'stt_status': status_val})
        db.session.commit()

        response['stt_status'] = status_val
        # emailUtil.stt_send_mail(data['date'], data['counselor_email'], name, file_url)
        return jsonify(response), 200


    @app.route("/stt/text", methods=['POST'])
    def get_stt_text():
        data = request.json
        key = data['key']
        print(key)
        print(data)

        source_dir = data['gcs_directory']
        file_name = data['file_name']

        stt_text = sttUtil.get_text(source_dir, file_name, key)

        if stt_text is None:
            return jsonify('key error'), 401

        json_object = json.loads(stt_text)

        return json.dumps(json_object, ensure_ascii=False), 200

    # @app.route("/stt/audio", methods=['POST'])
    # def get_stt_audio():
    #     # data = request.json
    #     # key = data['key']
    #     key = 'mykey'
    #     print(key)
    #     # print(data)
    #
    #     # source_dir = data['gcs_directory']
    #     source_dir = "recording/2023/FEBRUARY/"
    #     # file_name = data['file_name']
    #     file_name = '0d6c94b1-7e29-4b26-969e-5f2b76daa39f_SessionA~2'
    #
    #     # stt_text = sttUtil.get_text(source_dir, file_name, key)
    #     # print(stt_text)
    #     audio_url = sttUtil.get_file(source_dir, file_name, key)
    #
    #
    #     print(audio_url)
    #
    #     # @api.route("/files/<path:path>")
    #     # def get_file(path):
    #     #     """Download a file."""
    #     #     return send_from_directory(UPLOAD_DIRECTORY, path, as_attachment=True)
    #     return send_file(audio_url, as_attachment=True,  attachment_filename='file_name.webm'), 200


    return app


if __name__ == "__main__":
    # gcpCredentials.save_credentials()
    app = create_app()
    app.run(host="0.0.0.0", port='8280', debug=True)
