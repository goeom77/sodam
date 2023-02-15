package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.SttRequest;
import com.samsung.sodam.db.entity.SttData;
import com.samsung.sodam.db.repository.SttDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SttService {

    private final SttDataRepository sttRepository;

    public SttRequest getSttDir(long schedule_id){
        Optional<SttData> data = sttRepository.findByScheduleId(schedule_id);
        if(data.isEmpty()) return null;
        SttData sttData = data.get();
        SttRequest request = SttRequest.builder()
                .url(sttData.getUrl())
                .gcsDirectory(sttData.getGcs_directory())
                .fileName(sttData.getFile_name())
                .build();
        return request;
    }

}
