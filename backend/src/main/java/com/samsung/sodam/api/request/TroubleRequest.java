package com.samsung.sodam.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TroubleRequest {

    private String id;
    private String name;
    private String category;
    private String type;
    private String title;
    private String content;

}
