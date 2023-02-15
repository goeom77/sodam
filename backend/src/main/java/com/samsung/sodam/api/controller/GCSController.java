package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.service.FileGCSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

@RestController
@Slf4j
@RequestMapping("/api/gcs")
@RequiredArgsConstructor
public class GCSController {
    private final FileGCSService service;
    @GetMapping(value = "/audio")
    public ResponseEntity<Resource> downloadAudio() throws IOException, NoSuchAlgorithmException {
        service.downloadEncryptedObject("recording/2023/FEBRUARY/0d6c94b1-7e29-4b26-969e-5f2b76daa39f_SessionA~2", "0d6c94b1-7e29-4b26-969e-5f2b76daa39f_SessionA~2.webm", "mykey");
        Path path = Paths.get("C:/workspace/sodam/backend/src/main/resources/recording_2023_FEBRUARY_0d6c94b1-7e29-4b26-969e-5f2b76daa39f_SessionA_2.webm");
        String contentType = Files.probeContentType(path);

        System.out.println(contentType);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(
                ContentDisposition.builder("attachment")
                        .filename("SessionA_2.webm", StandardCharsets.UTF_8)
                        .build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
