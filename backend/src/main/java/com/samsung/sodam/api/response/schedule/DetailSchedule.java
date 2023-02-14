package com.samsung.sodam.api.response.schedule;

import com.samsung.sodam.db.entity.CONSULT_TYPE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailSchedule {
    @Column(name = "name")
    public String counselorName;
    @Column(name = "client_name")
    public String clientName;
    @Column(name = "openviduId")
    public String openViduId;
    @Column(name = "session_id")
    public Integer sessionId;
    @Column(name = "email")
    public String email;
    public LocalDateTime start;
    public Integer turn;
    public String phone;
    public CONSULT_TYPE type;
}
