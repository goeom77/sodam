package com.samsung.sodam.api.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TroubleListResponse {

    private List<TroubleDetailResponse> list = new ArrayList<>();
    private int totalCount;
}
