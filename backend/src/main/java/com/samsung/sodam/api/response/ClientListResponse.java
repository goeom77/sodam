package com.samsung.sodam.api.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientListResponse {
    String id;
    String name;
    String email;
    String tel;

    @QueryProjection
    public ClientListResponse(String id, String name, String email, String tel) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tel = tel;
    }
}
