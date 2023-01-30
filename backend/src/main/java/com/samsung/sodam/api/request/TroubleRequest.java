package com.samsung.sodam.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TroubleRequest {

    private String clientId;
    private String category;
    private String title;
    private String content;

}
