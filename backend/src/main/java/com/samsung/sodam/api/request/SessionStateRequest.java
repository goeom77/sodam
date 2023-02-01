package com.samsung.sodam.api.request;

import com.samsung.sodam.db.entity.STATE;
import lombok.Data;

@Data
public class SessionStateRequest {
    Integer sessionId;
    STATE state;
}
