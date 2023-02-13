import base64

from google.cloud import storage

bucket_name = "stt-bucket-binu"

def upload_encrypted_blob(
    source_file_name,
    destination_blob_name,
    base64_encryption_key,
):
    """Uploads a file to a Google Cloud Storage bucket using a custom
    encryption key.

    The file will be encrypted by Google Cloud Storage and only
    retrievable using the provided encryption key.
    """

    # source_file_name = "local/path/to/file"
    # destination_blob_name = "storage-object-name"
    # base64_encryption_key = "TIbv/fjexq+VmtXzAlc63J4z5kFmWJ6NdAPQulQBT7g="

    storage_client = storage.Client('key.json')
    bucket = storage_client.bucket(bucket_name)

    # Encryption key must be an AES256 key represented as a bytestring with
    # 32 bytes. Since it's passed in as a base64 encoded string, it needs
    # to be decoded.
    encryption_key = base64.b64decode(base64_encryption_key)
    blob = bucket.blob(
        destination_blob_name, encryption_key=encryption_key
    )

    # Optional: set a generation-match precondition to avoid potential race conditions
    # and data corruptions. The request to upload is aborted if the object's
    # generation number does not match your precondition. For a destination
    # object that does not yet exist, set the if_generation_match precondition to 0.
    # If the destination object already exists in your bucket, set instead a
    # generation-match precondition using its generation number.
    generation_match_precondition = 0

    blob.upload_from_filename(source_file_name, if_generation_match=generation_match_precondition)

    print(
        f"File {source_file_name} uploaded to {destination_blob_name}."
    )

    return destination_blob_name

def download_encrypted_blob(
    source_blob_name,
    destination_file_name,
    base64_encryption_key,
):
    """Downloads a previously-encrypted blob from Google Cloud Storage.

    The encryption key provided must be the same key provided when uploading
    the blob.
    """
    # source_blob_name = "storage-object-name"
    # destination_file_name = "local/path/to/file"
    # base64_encryption_key = "base64-encoded-encryption-key"

    storage_client = storage.Client('key.json')

    bucket = storage_client.bucket(bucket_name)

    # Encryption key must be an AES256 key represented as a bytestring with
    # 32 bytes. Since it's passed in as a base64 encoded string, it needs
    # to be decoded.
    encryption_key = base64.b64decode(base64_encryption_key)
    blob = bucket.blob(source_blob_name, encryption_key=encryption_key)

    blob.download_to_filename(destination_file_name)

    print(
        f"Blob {source_blob_name} downloaded to {destination_file_name}."
    )

    return destination_file_name


def upload_blob_from_memory(contents, destination_blob_name):
    """Uploads a file to the bucket."""

    # The ID of your GCS bucket
    # bucket_name = "your-bucket-name"

    # The contents to upload to the file
    # contents = "these are my contents"

    # The ID of your GCS object
    # destination_blob_name = "storage-object-name"

    storage_client = storage.Client('key.json')
    bucket = storage_client.bucket(bucket_name)
    blob = bucket.blob(destination_blob_name)

    blob.upload_from_string(contents)

    print(
        f"{destination_blob_name} with contents {contents} uploaded to {bucket_name}."
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
            blob_name, bucket_name, contents
        )
    )

    return contents

def download_blob(source_blob_name, destination_file_name):

    print(source_blob_name)
    """Downloads a blob from the bucket."""
    # The ID of your GCS object
    # source_blob_name = "storage-object-name"

    # The path to which the file should be downloaded
    # destination_file_name = "local/path/to/file"

    storage_client = storage.Client('key.json')

    bucket = storage_client.bucket(bucket_name)

    # Construct a client side representation of a blob.
    # Note `Bucket.blob` differs from `Bucket.get_blob` as it doesn't retrieve
    # any content from Google Cloud Storage. As we don't need additional data,
    # using `Bucket.blob` is preferred here.
    blob = bucket.blob(source_blob_name)
    blob.download_to_filename(destination_file_name)

    print(
        "Downloaded storage object {} from bucket {} to local file {}.".format(
            source_blob_name, bucket_name, destination_file_name
        )
    )

    return destination_file_name


def delete_blob(blob_name, file_name):
    """Deletes a blob from the bucket."""
    # bucket_name = "your-bucket-name"
    # blob_name = "your-object-name"

    storage_client = storage.Client('key.json')

    bucket = storage_client.bucket(bucket_name)
    blob = bucket.blob(blob_name + file_name)
    blob.delete()

    print(f"Blob {blob_name} deleted.")
