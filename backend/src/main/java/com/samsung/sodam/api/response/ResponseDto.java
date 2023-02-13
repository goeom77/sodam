package com.samsung.sodam.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {

    private boolean ok;
    private String message;
    private T result;

    public ResponseDto(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }

    public ResponseDto(boolean ok, String message, T t) {
        this.ok = ok;
        this.message = message;
        this.result = t;
    }
}
