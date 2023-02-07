package com.samsung.sodam.api.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class EduRequest {
    String degree;
    String school;
    String major;
    String is_graduate;
    MultipartFile file;
}
