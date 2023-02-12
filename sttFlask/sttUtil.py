from flask import jsonify
import asyncio
import os
# import gcpCredentials
import storageUtil
import cryptoUtil
from google.cloud import storage
from google.cloud import speech

bucket_name = "stt-bucket-binu"


# async def transcribe_gcs(gcs_uri):
def transcribe_gcs(gcs_uri, destination_blob_name, file_name, key):
    """Asynchronously transcribes the audio file specified by the gcs_uri."""

    stt_data = []
    try:
        speech_client = speech.SpeechClient.from_service_account_file('key.json')
        # print(gcpCredentials.get_credentials_path())
        # speech_client = speech.SpeechClient.from_service_account_file(gcpCredentials.get_credentials_path())

        # storage_client = storage.Client('key.json')
        # bucket = storage_client.bucket(bucket_name)

        audio = speech.RecognitionAudio(uri=gcs_uri)
        config = speech.RecognitionConfig(
            encoding=speech.RecognitionConfig.AudioEncoding.ENCODING_UNSPECIFIED,
            sample_rate_hertz=48000,
            # sample_rate_hertz=16000,
            # model="latest_long",  // fit in long conversation
            audio_channel_count=2,
            # enable_separate_recognition_per_channel=True,
            language_code="ko-Kr",
            enable_word_time_offsets=True
        )
        print('1-1. send stt request')
        operation = speech_client.long_running_recognize(config=config, audio=audio)
        print("Waiting for operation to complete...")

        print('1-2. get stt response')
        response = operation.result()
        # Each result is for a consecutive portion of the audio. Iterate through
        # them to get the transcripts for the entire audio file.
        for result in response.results:
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

            print("*" * 100)
            stt_data.append(origin_str)

        print('1-3. encrypt stt file')
        aes = cryptoUtil.AESCipher(key)
        encrypt = aes.encrypt(''.join(stt_data))
        print("암호화:", encrypt)
        print("-" * 100)

        print('1-4. upload encrypt stt file')
        path = destination_blob_name + 'enc/' + file_name + '_stt.text'
        storageUtil.upload_blob_from_memory(encrypt, path)

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
        storageUtil.upload_encrypted_blob('storage/' + file_name, audio_enc_path, base64_key)
        # 업로드 끝난거 삭제
        if os.path.exists(audio_down_path):
            os.remove(audio_down_path)

        print('2-3. delete original audio file (develop - skip now)')
        # 원본 파일 삭제
        # storageUtil.delete_blob(destination_blob_name, file_name)
    except:
        print('error')
        path = None

    return path


def get_text(source_blob_name, file_name, key):
    aes = cryptoUtil.AESCipher(key)

    storage_client = storage.Client('key.json')
    bucket = storage_client.bucket(bucket_name)

    path = source_blob_name + 'enc/' + file_name + '_stt.text'

    encrypt = storageUtil.download_blob_into_memory(path)
    decrypt = aes.decrypt(encrypt)
    return decrypt


def get_file(source_blob_name, file_name, key):
    path = os.path.join(os.getcwd(), 'storage/')

    if not os.path.exists(path):
        # if the storage directory is not present
        # then create it.
        os.makedirs(path)

    audio_down_path = path + file_name + '.webm'

    aes = cryptoUtil.AESCipher(key)
    base64_key = aes.get_base64_key()
    storageUtil.download_encrypted_blob(source_blob_name + file_name, audio_down_path, base64_key)
    return audio_down_path


if __name__ == "__main__":
    # url = transcribe_gcs("gs://stt-bucket-binu/recording/2023/FEBRUARY/0d6c94b1-7e29-4b26-969e-5f2b76daa39f_SessionA~2", "recording/2023/FEBRUARY/", "0d6c94b1-7e29-4b26-969e-5f2b76daa39f_SessionA~2", "key")

    print(get_text("recording/2023/FEBRUARY/", "0d6c94b1-7e29-4b26-969e-5f2b76daa39f_SessionA~2", "key"))


