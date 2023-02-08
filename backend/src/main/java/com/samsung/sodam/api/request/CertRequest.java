package com.samsung.sodam.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class CertRequest {
    private long id;
    String name;
    String serial_num;
    String agency;
    MultipartFile file;
}
