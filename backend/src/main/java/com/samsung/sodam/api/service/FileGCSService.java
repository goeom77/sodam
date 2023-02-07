package com.samsung.sodam.api.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileGCSService {
    // GCS의 key.json 파일 내용이 등록되어서 주입됨
    private final Storage storage;

    public String fileUploadGCS (MultipartFile file, String path) throws IOException {
        String bucketName = "stt-bucket-binu";
        // GCS 자체 지원으로 폴더 추가할때 로직 필요 없음

        String savedName = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        log.info("파일이름 :"+file.getOriginalFilename());
        log.info("파일크기 : "+file.getSize());
        log.info("컨텐트 타입 : "+file.getContentType());

        LocalDate now = LocalDate.now();
        String datePath = Integer.toString(now.getYear()) + "/" + now.getMonth().toString();

        System.out.println(datePath);

        String newFileName = path + "/" + datePath + "/" + uuid + "_" + savedName;

//            String mimeType = tika.detect(file.getOriginalFilename());
//            if(!mimeType.startsWith("image"))
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST);

        BlobId blobId = BlobId.of(bucketName, newFileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, file.getBytes());
        System.out.println(storage);

        // DB에 저장될 값
        String url = newFileName;
        return url;
    }

}
