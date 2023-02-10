from flask import jsonify
import asyncio
import os
import gcpCredentials


# async def transcribe_gcs(gcs_uri):
def transcribe_gcs(gcs_uri, file_name):
    """Asynchronously transcribes the audio file specified by the gcs_uri."""
    from google.cloud import speech

    # client = speech.SpeechClient.from_service_account_file('key.json')
    print(gcpCredentials.get_credentials_path())
    client = speech.SpeechClient.from_service_account_file(gcpCredentials.get_credentials_path())

    audio = speech.RecognitionAudio(uri=gcs_uri)
    config = speech.RecognitionConfig(
        encoding=speech.RecognitionConfig.AudioEncoding.FLAC,
        sample_rate_hertz=48000,    # ch1.flac test
        # sample_rate_hertz=16000,
        # model="latest_long",
        language_code="ko-Kr",
        enable_word_time_offsets=True
    )
    print('1. send stt request')
    operation = client.long_running_recognize(config=config, audio=audio)
    print("Waiting for operation to complete...")

    print('2. make file')
    path = os.path.join(os.getcwd(), 'storage')
    if not os.path.exists(path):
        # if the storage directory is not present
        # then create it.
        os.makedirs(path)

    path = os.path.join(path, file_name + '.txt')
    f = open(path, 'w', encoding='utf-8')


    response = operation.result()

    # Each result is for a consecutive portion of the audio. Iterate through
    # them to get the transcripts for the entire audio file.
    for result in response.results:
        # The first alternative is the most likely one for this portion.
        alternative = result.alternatives[0];

        start_time_ms = alternative.words[0].start_time.total_seconds() % 1
        start_time_int = int(alternative.words[0].start_time.total_seconds())


        # print(type(alternative.words[0].start_time.total_seconds()))
        # print(u"Transcript: {}".format(alternative.transcript))
        # print(start_time_ms)

        min = str(start_time_int // 60).zfill(2)
        sec = str(start_time_int % 60).zfill(2)
        msec = str(int(start_time_ms * 10)).zfill(2)
        print("[ {}:{}:{} ] {}".format(min, sec, msec, alternative.transcript))
        f.write("[ {}:{}:{} ] {}".format(min, sec, msec, alternative.transcript) + '\n')
        # for word_info in alternative.words:
        #     print("[{} - {}]".format(word_info.start_time.total_seconds(), word_info.end_time.total_seconds()), end=' : ')
        #     print(word_info.word)

    f.close()
    return path



if __name__ == "__main__":
    transcribe_gcs("gs://stt-bucket-binu/consulting.flac")