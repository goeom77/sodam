package com.samsung.sodam.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;

@ApiModel(description = "STT 요청 모델")
@Getter
@Setter
@ToString
public class SttRequest {

    @JsonProperty(value = "counselor_name")
    private String counselorName;

    @JsonProperty(value = "counselor_email")
    private String counselorEmail;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime date;

    private String url;

    @JsonProperty(value = "gcs_directory")
    private String gcsDirectory;

    @JsonProperty(value = "file_name")
    private String fileName;

    private String key;

    @JsonProperty(value = "schedule_id")
    private Integer scheduleId;

    @Builder
    public SttRequest(String url, String gcsDirectory, String fileName, String key, Integer scheduleId) {
        this.url = url;
        this.gcsDirectory = gcsDirectory;
        this.fileName = fileName;
        this.key = key;
        this.scheduleId = scheduleId;
    }

}
