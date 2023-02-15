from flask import jsonify
import json
import os
from collections import Counter
# import gcpCredentials
import storageUtil
import cryptoUtil
import POSUtil
import traceback
from google.cloud import storage
from google.cloud import speech

bucket_name = "stt-bucket-binu"


class Sentence(object):
    def __init__(self, start_time, sentence, channel):
        self.start_time = start_time
        self.sentence = sentence
        self.channel = channel

    def __str__(self):
        return 'time: {} channel-{} \n {}'.format(self.start_time, self.channel, self.sentence)


def prepare_data(gcs_uri, destination_blob_name, file_name, key):
    """Prepare stt script and audio file for serve"""
    data_status = [False, False]
    try:
        is_stt_finish = _transcribe_gcs(gcs_uri, destination_blob_name, file_name, key)
        data_status[0] = is_stt_finish;

        is_audio_finish = _upload_encrypt_audio(destination_blob_name, file_name, key)
        data_status[1] = is_audio_finish;

    except:
        print('error')
        traceback.print_exc()

    return data_status


# async def transcribe_gcs(gcs_uri):
def _transcribe_gcs2(gcs_uri, destination_blob_name, file_name, key):
    """Asynchronously transcribes the audio file specified by the gcs_uri."""
    path = ''
    stt_data = []
    try:
        speech_client = speech.SpeechClient.from_service_account_json('key.json')

        audio = speech.RecognitionAudio(uri=gcs_uri)
        config = speech.RecognitionConfig(
            encoding=speech.RecognitionConfig.AudioEncoding.ENCODING_UNSPECIFIED,
            language_code="ko-Kr",
            enable_word_time_offsets=True,
            # sample_rate_hertz=48000,
            # audio_channel_count=2,
            # enable_separate_recognition_per_channel=True,

            sample_rate_hertz=16000,
            model="latest_long",  # fit in long conversation



            audio_channel_count=1
        )
        print('1-1. send stt request')
        operation = speech_client.long_running_recognize(config=config, audio=audio)
        print("Waiting for operation to complete...")

        print('1-2. get stt response')
        response = operation.result()

        sentence_list = []
        channel = True

        # Each result is for a consecutive portion of the audio. Iterate through
        # them to get the transcripts for the entire audio file.
        for result in response.results:
            if result is None:
                continue

            # The first alternative is the most likely one for this portion.
            alternative = result.alternatives[0];
            if alternative is None:
                continue

            # 다른 채널 data임
            channel = not channel

            # word_len = sum(1 for _ in alternative.words)

            start_time = 0
            before_end_time = 0
            sentence = []
            # channel = True
            # str_list.append(alternative.words[0].word)

            for one_word in alternative.words:

                # do at first time
                if start_time == 0:
                    print('-'*50 + 'start sentence')
                    print(one_word)
                    start_time = one_word.start_time.total_seconds()
                    before_end_time = one_word.end_time.total_seconds()
                    sentence.append(one_word.word)
                    continue;

                curr_start_time = one_word.start_time.total_seconds()
                calc = curr_start_time - before_end_time
                print('start at {}, before word end at {}, current word start at {}, calc? {} '.format(start_time, before_end_time, curr_start_time, calc ))
                print(one_word)

                if curr_start_time - before_end_time > 0.5:
                    # tmp = {'start_time': start_time, 'sentence': ''.join(sentence), 'channel': int(channel)}
                    # sentence_list.append(tmp)
                    sentence_list.append(Sentence(start_time, ' '.join(sentence), int(channel)))
                    start_time = one_word.start_time.total_seconds()
                    sentence.clear()

                before_end_time = one_word.end_time.total_seconds()
                sentence.append(one_word.word)

                # start_time_10ms = one_word.start_time.total_seconds() * 10 % 10
                # start_time_int = int(one_word.start_time.total_seconds())

                # print("origin {} int {}, ms {}".format(one_word.start_time.total_seconds(), start_time_int, start_time_10ms))
                #
                # min = str(start_time_int // 60).zfill(2)
                # sec = str(start_time_int % 60).zfill(2)
                # msec = str(int(start_time_10ms * 10)).zfill(2)
                #
                # print("[ {}:{}:{} ] {}".format(min, sec, msec, one_word.word))
                # stt_data.append()

            # 남은 문장 넣기
            if sentence:
                # Sentence
                # tmp = {'start_time': start_time, 'sentence': ''.join(sentence), 'channel': int(channel)}
                sentence_list.append(Sentence(start_time, ' '.join(sentence), int(channel)))



            # start_time_ms = alternative.words[0].start_time.total_seconds() % 1
            # start_time_int = int(alternative.words[0].start_time.total_seconds())
            #
            # min = str(start_time_int // 60).zfill(2)
            # sec = str(start_time_int % 60).zfill(2)
            # msec = str(int(start_time_ms * 10)).zfill(2)
            #
            # print("[ {}:{}:{} ] {}".format(min, sec, msec, alternative.transcript))
            #
            # origin_str = "[ {}:{}:{} ] {}".format(min, sec, msec, alternative.transcript) + '\n'
            #
            # print("*" * 100)
            # stt_data.append(origin_str)

        for tmp in sentence_list: print(tmp)
        print('*' * 100)
        # sentence_list sort
        sorted(sentence_list, key=lambda sentence: sentence.start_time)  # sort by start_time

        for tmp in sentence_list: print(tmp)
        print('*' * 100)

        # print('1-3. encrypt stt file')
        # aes = cryptoUtil.AESCipher(key)
        # encrypt = aes.encrypt(''.join(stt_data))
        # print("암호화:", encrypt)
        # print("-" * 100)
        #
        # print('1-4. upload encrypt stt file')
        # path = destination_blob_name + 'enc/' + file_name + '_stt.text'
        # storageUtil.upload_blob_from_memory(encrypt, path)

    except:
        print('error')
        path = None
        traceback.print_exc()
        return False

    return True

def _transcribe_gcs(gcs_uri, destination_blob_name, file_name, key):
    """Asynchronously transcribes the audio file specified by the gcs_uri."""
    path = ''
    stt_data = []
    analysis_data = Counter()
    try:
        speech_client = speech.SpeechClient.from_service_account_json('key.json')

        audio = speech.RecognitionAudio(uri=gcs_uri)
        config = speech.RecognitionConfig(
            encoding=speech.RecognitionConfig.AudioEncoding.ENCODING_UNSPECIFIED,
            language_code="ko-Kr",
            enable_word_time_offsets=True,
            sample_rate_hertz=48000,
            audio_channel_count=2,
            enable_separate_recognition_per_channel=True,

            # sample_rate_hertz=16000,
            # model="latest_long",  # fit in long conversation


            # audio_channel_count=1
        )
        print('1-1. send stt request')
        operation = speech_client.long_running_recognize(config=config, audio=audio)
        print("Waiting for operation to complete...")

        print('1-2. get stt response')
        response = operation.result()
        channel = True

        # Each result is for a consecutive portion of the audio. Iterate through
        # them to get the transcripts for the entire audio file.
        for result in response.results:
            # 다른 채널로 변경
            channel = not channel

            # The first alternative is the most likely one for this portion.
            alternative = result.alternatives[0];

            start_time_ms = alternative.words[0].start_time.total_seconds() % 1
            start_time_int = int(alternative.words[0].start_time.total_seconds())

            min = str(start_time_int // 60).zfill(2)
            sec = str(start_time_int % 60).zfill(2)
            msec = str(int(start_time_ms * 10)).zfill(2)
            # print("[ {}:{}:{} ] {}".format(min, sec, msec, alternative.transcript))
            # f.write("[ {}:{}:{} ] {}".format(min, sec, msec, alternative.transcript) + '\n')

            origin_str = "[ {}:{}:{} ] {}".format(min, sec, msec, alternative.transcript) + '\n'
            # print(origin_str)

            stt_data.append(origin_str)

            if channel:
                analysis_data += POSUtil.analysis(alternative.transcript)
                print(analysis_data)

            # print("*" * 100)


        print(len(analysis_data))
        if len(analysis_data) > 20:
            analysis_data = analysis_data.most_common(20)

        analysis_data_dict = dict(analysis_data)

        all_data = {'script': ''.join(stt_data), 'analysis': analysis_data_dict}

        all_data_json = json.dumps(all_data, ensure_ascii=False)

        print('1-3. encrypt stt file')
        aes = cryptoUtil.AESCipher(key)
        encrypt = aes.encrypt(all_data_json)
        print("암호화:", encrypt)
        print("-" * 100)

        print('1-4. upload encrypt stt file')
        path = destination_blob_name + 'enc/' + file_name + '_stt.text'
        storageUtil.upload_blob_from_memory(encrypt, path)

    except:
        print('error')
        traceback.print_exc()
        return False

    return True




def _upload_encrypt_audio(destination_blob_name, file_name, key):
    try:
        aes = cryptoUtil.AESCipher(key)
        print('2-1. download audio file')
        audio_path = destination_blob_name + file_name
        path = os.path.join(os.getcwd(), 'storage')
        if not os.path.exists(path):
            # if the storage directory is not present
            # then create it.
            os.makedirs(path)

        audio_down_path = 'storage/' + file_name
        storageUtil.download_blob(audio_path, audio_down_path)

        print('2-2. upload audio file with encrypt key')
        base64_key = aes.get_base64_key()
        audio_enc_path = destination_blob_name + 'enc/' + file_name + '.webm'
        print(base64_key)
        storageUtil.upload_encrypted_blob('storage/' + file_name, audio_enc_path, base64_key)

        # 업로드 끝난후 로컬파일 삭제
        if os.path.exists(audio_down_path):
            os.remove(audio_down_path)

        print('2-3. delete original audio file (develop - skip now)')
        # 원본 파일 삭제
        storageUtil.delete_blob(destination_blob_name, file_name)

    except:
        print('error')
        path = None
        traceback.print_exc()
        return False

    return True

def get_text(source_blob_name, file_name, key):
    aes = cryptoUtil.AESCipher(key)

    storage_client = storage.Client('key.json')
    bucket = storage_client.bucket(bucket_name)

    path = source_blob_name + 'enc/' + file_name + '_stt.text'

    encrypt = storageUtil.download_blob_into_memory(path)

    try:
        decrypt = aes.decrypt(encrypt)
        return decrypt

    except:
        print('error')
        traceback.print_exc()
        return None


def get_file(source_blob_name, file_name, key):
    path = os.path.join(os.getcwd(), 'storage/')

    if not os.path.exists(path):
        # if the storage directory is not present
        # then create it.
        os.makedirs(path)

    audio_down_path = path + file_name + '.webm'

    aes = cryptoUtil.AESCipher(key)
    base64_key = aes.get_base64_key()
    print(aes.get_key())
    print(base64_key)
    print(base64_key.decode('utf-8'))
    storageUtil.download_encrypted_blob(source_blob_name + file_name, audio_down_path, base64_key)
    return audio_down_path


# if __name__ == "__main__":
    # transcribe_gcs("gs://stt-bucket-binu/recording/2023/FEBRUARY/ucc__(F) 상담분석.flac",
    #                        "recording/2023/FEBRUARY/", "ucc__(F) 상담분석.flac", "mykey")
    # url = transcribe_gcs("gs://stt-bucket-binu/recording/2023/FEBRUARY/SessionA_2", "recording/2023/FEBRUARY/", "SessionA_2", "mykey")

    # print(get_text("recording/2023/FEBRUARY/", "0d6c94b1-7e29-4b26-969e-5f2b76daa39f_SessionA~2", "key"))


