package com.samsung.sodam.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ReviewRequest {
    //최신순, 별점순
    String type;
    String clientId;
    String counselorId;
}
