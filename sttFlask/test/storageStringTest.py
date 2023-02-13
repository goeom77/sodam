from google.cloud import storage

bucket_name = "stt-bucket-binu"

def upload_blob_from_memory(destination_blob_name):
    """Uploads a file to the bucket."""

    # The ID of your GCS bucket
    bucket_name = "stt-bucket-binu"

    # The contents to upload to the file
    content = '[ 0.2.4 sec ]줄이는 방법도 있고요 마스터 나지 별로 같이 아주 중요한 프렌치는 출시를 자제하고 애마를 날리는 방법 등이 있습니다\n[ 0.13.1 sec ] 하지만 본질적으로 2시 전에 풀 뭐지 하는 과정을 통해 버전을 잘 맞춰 주시면 컴플렉스 날 일은 거의 없을 겁니다\n[ 0.25.4 sec ] 마스크 하나 디벨롭 브랜치에 잘못 푸시하는 일이 생길 수 있어요 그걸 방지할 수 있게 앱에서 팀원들을 권한을 설정해 줄 수 있습니다 프로젝트 인포메이션에서 엠버 탭을 눌러 보시면 맥스로를 부여할 수 있습니다\n'

    # The ID of your GCS object
    # destination_blob_name = "storage-object-name"

    storage_client = storage.Client('key.json')
    bucket = storage_client.bucket(bucket_name)
    blob = bucket.blob(destination_blob_name)

    # blob.upload_from_string(content)
    contents = content*6*60;
    print(contents.__sizeof__())
    blob.upload_from_string(contents)

    print(
        f"{destination_blob_name} with contents {content} uploaded to {bucket_name}."
    )


def download_blob_into_memory(blob_name):
    """Downloads a blob into memory."""
    # The ID of your GCS bucket
    # bucket_name = "your-bucket-name"

    # The ID of your GCS object
    # blob_name = "storage-object-name"

    storage_client = storage.Client('key.json')

    bucket = storage_client.bucket(bucket_name)

    # Construct a client side representation of a blob.
    # Note `Bucket.blob` differs from `Bucket.get_blob` as it doesn't retrieve
    # any content from Google Cloud Storage. As we don't need additional data,
    # using `Bucket.blob` is preferred here.
    blob = bucket.blob(blob_name)
    contents = blob.download_as_string()

    print(
        "Downloaded storage object {} from bucket {} as the following string: {}.".format(
            blob_name, bucket_name, contents.decode("utf-8")
        )
    )


if __name__ == '__main__':
    upload_blob_from_memory('test/txt-file2')
    download_blob_into_memory('test/txt-file2')
