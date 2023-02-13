package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.RoomRequest;
import com.samsung.sodam.api.response.ResponseDto;
import com.samsung.sodam.db.repository.SessionCustomRepository;
import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {


    @Value("${openvidu.url}")
    private String OPENVIDU_URL;
    @Value("${openvidu.secret}")
    private String SECRET;

    private final SessionCustomRepository sessionCustomRepository;
    private OpenVidu openVidu;
    private final FileGCSService fileService;
    private Map<String, String> sessionRecordingMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void init() {
        // openvidu 서버와 연결
        this.openVidu = new OpenVidu(OPENVIDU_URL, SECRET);
    }

    @Override
    public void createSession(RoomRequest request) {
        sessionCustomRepository.createSession(request);
    }

    @Override
    public void deleteSession(RoomRequest request) {
        sessionCustomRepository.deleteSession(request);
    }

    @Override
    public ResponseDto<?> startRecord(String sessionId) {
        // 이미 녹음중일 때
        if (sessionRecordingMap.get(sessionId) != null) {
            try {
                Recording recording = openVidu.getRecording(sessionRecordingMap.get(sessionId));
                if (recording.getStatus().equals(Recording.Status.started)) {
                    // 녹음중
                    return ResponseDto.builder()
                            .ok(true)
                            .message("녹음중입니다")
                            .result(recording)
                            .build();
                }
                return ResponseDto.builder().message("이미 녹음중 입니다").build();
            } catch (OpenViduJavaClientException | OpenViduHttpException e) {
                return ResponseDto.builder()
                        .ok(false)
                        .message("화상채팅 서버 오류 입니다.")
                        .build();
            }
        }

        // 새로운 녹음 시작
        Recording recording = null;
        try {
            //openvidu 서버의 녹음 데이터 확인
            recording = openVidu.getRecording(sessionId);
        } catch (OpenViduJavaClientException | OpenViduHttpException exception) {
            log.info("not current Recording");
        }
        try {
            RecordingProperties properties = new RecordingProperties.Builder()
                    .outputMode(Recording.OutputMode.COMPOSED)
                    .hasAudio(true)
                    .hasVideo(false)
                    .build();
            recording = openVidu.startRecording(sessionId, properties);
            sessionRecordingMap.put(sessionId, recording.getId());
            return ResponseDto.builder()
                    .ok(true)
                    .message("녹음 시작")
                    .result(recording)
                    .build();

        } catch (OpenViduException e) {
            //녹음 시작 실패
            sessionRecordingMap.remove(sessionId);
            return ResponseDto.builder()
                    .ok(false)
                    .message("화상채팅 서버 오류 입니다.")
                    .build();
        }
    }

    @Override
    public ResponseDto<?> stopRecord(String sessionId) {
        try {
            // 녹음 중지
            Recording recording = openVidu.stopRecording(sessionRecordingMap.get(sessionId));
            sessionRecordingMap.remove(sessionId);

            // GCS에 업로드
            fileService.recordUploadGCS(recording, "recording");

            return ResponseDto.builder().ok(true).build();
        } catch (OpenViduJavaClientException | OpenViduHttpException exception) {
            sessionRecordingMap.remove(sessionId);
            return ResponseDto.builder()
                    .ok(false)
                    .message("화상채팅 서버 오류 입니다.")
                    .build();
        } catch (IOException exception) {
            exception.printStackTrace();
            return ResponseDto.builder()
                    .ok(false)
                    .message("파일 업로드 오류 입니다.")
                    .build();
        }

    }
}
