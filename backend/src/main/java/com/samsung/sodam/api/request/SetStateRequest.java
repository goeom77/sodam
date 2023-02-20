package com.samsung.sodam.api.request;

import com.samsung.sodam.db.entity.STATE;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SetStateRequest {
    Integer sessionId;
    STATE state;

}
