package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.SttRequest;
import com.samsung.sodam.api.service.FileGCSService;
import com.samsung.sodam.api.service.SttService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;

@RestController
@Slf4j
@RequestMapping("/api/gcs")
@RequiredArgsConstructor
public class GCSController {
    private final FileGCSService service;
    private final SttService sttService;

    @PostMapping(value = "/audio/{scheduleId}")
    public ResponseEntity<Resource> downloadAudio(@PathVariable Long scheduleId, @RequestBody SttRequest request) throws IOException, NoSuchAlgorithmException {
        try {
            SttRequest newRequest = sttService.getSttDir(scheduleId);

            // 해당 스케쥴 없음
            if (newRequest == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            String objName = newRequest.getGcsDirectory() + "enc/" + newRequest.getFileName() + ".webm";
            Path path = service.downloadEncryptedObject(objName, request.getKey());
//        Path path = Paths.get("C:/workspace/sodam/backend/src/main/resources/recording_2023_FEBRUARY_0d6c94b1-7e29-4b26-969e-5f2b76daa39f_SessionA_2.webm");
            String contentType = Files.probeContentType(path);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(
                    ContentDisposition.builder("attachment")
                            .filename(newRequest.getFileName(), StandardCharsets.UTF_8)
                            .build());
            headers.add(HttpHeaders.CONTENT_TYPE, contentType);

            Resource resource = new InputStreamResource(Files.newInputStream(path));

            // 임시 파일 남아있을 시 삭제
            Files.deleteIfExists(path);
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
