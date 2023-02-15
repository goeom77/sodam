package com.samsung.sodam.api.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.samsung.sodam.db.entity.SttData;
import com.samsung.sodam.db.repository.SttDataRepository;
import com.samsung.sodam.util.ciperUtil;

import com.samsung.sodam.api.request.SttRequest;

import io.openvidu.java.client.Recording;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileGCSService {
    // GCS의 key.json 파일 내용이 등록되어서 주입됨
    private final Storage storage;

    private final SttDataRepository sttRepository;

    public String fileUploadGCS (MultipartFile file, String rootDir) throws IOException {
        String bucketName = "stt-bucket-binu";

        // GCS 자체 지원으로 폴더 추가할때 로직 필요 없음
        String savedName = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        log.info("파일이름 :"+file.getOriginalFilename());
        log.info("파일크기 : "+file.getSize());
        log.info("컨텐트 타입 : "+file.getContentType());

        LocalDate now = LocalDate.now();
        String datePath = now.getYear() + "/" + now.getMonth().toString();

        String path = rootDir + "/" + datePath + "/" + uuid + "_";
        String newFileName = path + savedName;

//            String mimeType = tika.detect(file.getOriginalFilename());
//            if(!mimeType.startsWith("image"))
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST);

        BlobId blobId = BlobId.of(bucketName, newFileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, file.getBytes());

        String encodeFileName = URLEncoder.encode(savedName, StandardCharsets.UTF_8);

        // DB에 저장될 값 (파일 다운로드 경로)
        return new StringBuilder().append("https://storage.googleapis.com").append("/").append(bucketName).append("/").append(path).append(encodeFileName).toString();
    }

    public SttRequest recordUploadGCS (Recording file, String rootDir) throws IOException {

        // 파일 저장 경로 설정
        String savedName = file.getName();
        UUID uuid = UUID.randomUUID();
        String datePath = LocalDate.now().getYear() + "/" + LocalDate.now().getMonth().toString();
        String path = rootDir + "/" + datePath + "/" + uuid + "_";
        String newFileName = path + savedName;

        // url에서 파일 다운로드
        URL url = new URL(file.getUrl());
        URLConnection urlConnection = url.openConnection();
        InputStream is = urlConnection.getInputStream();

        // 파일 저장
        String bucketName = "stt-bucket-binu";
        BlobId blobId = BlobId.of(bucketName, newFileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo, is);

        // 리턴타입
        SttRequest sttRequest = SttRequest.builder()
                .gcsDirectory("recording/" + datePath)
                .url("gs://stt-bucket-binu/" + newFileName)
                .fileName(uuid + "_" + savedName)
                .build();
        return sttRequest;
    }

    public String downloadSttText(int scheduleId){
        String bucketName = "stt-bucket-binu";
        String contents = "";
        SttData data = null;
        Optional<SttData> obj = sttRepository.findByScheduleId(scheduleId);

        if(obj.isEmpty()) return null;
        data = obj.get();

        String objectName = data.getGcs_directory() + data.getFile_name();

        byte[] content = storage.readAllBytes(bucketName, objectName);

        System.out.println(
                "The contents of "
                        + objectName
                        + " from bucket name "
                        + bucketName
                        + " are: "
                        + new String(content, StandardCharsets.UTF_8));
        return contents;
    }

    public void downloadEncryptedObject(String objectName, String fileName, String key) throws IOException, NoSuchAlgorithmException {
        String bucketName = "stt-bucket-binu";

        // The ID of your GCS object
        // String objectName = "your-object-name";

        // The path to which the file should be downloaded
        // Path destFilePath = Paths.get("/local/path/to/file.txt");

        // The Base64 encoded decryption key, which should be the same key originally used to encrypt
        // the object
        // String decryptionKey = "TIbv/fjexq+VmtXzAlc63J4z5kFmWJ6NdAPQulQBT7g=";
        Path path = Paths.get("../../../storage");
        Path destFilePath = Path.of(path +"/" +fileName);

        // 폴더 없을시 생성
        File folder = new File(path.toUri());
        if (!folder.exists()) {
            try{
                folder.mkdir(); //폴더 생성합니다. ("새폴더"만 생성)
                System.out.println("폴더 생성완료.");
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }else {
            System.out.println("폴더가 이미 존재합니다..");
        }
        String realKey1 = ciperUtil.encryptSHA256(key);
        String realKey2 = ciperUtil.makeSHA(key);


        System.out.println("encryptSHA256 "+realKey1);

        byte[] decodedBytes = Base64.getEncoder().encode(realKey1.getBytes("utf-8"));
//        realKey1 = realKey1 + ":";
        Blob blob = storage.get(bucketName, objectName);

        String decryptionKey = new String(decodedBytes, StandardCharsets.UTF_8);
        System.out.println(decryptionKey);

        blob.downloadTo(destFilePath, Blob.BlobSourceOption.decryptionKey(decryptionKey));

        System.out.println(
                "Downloaded object "
                        + objectName
                        + " from bucket name "
                        + bucketName
                        + " to "
                        + destFilePath
                        + " using customer-supplied encryption key");
    }


}
