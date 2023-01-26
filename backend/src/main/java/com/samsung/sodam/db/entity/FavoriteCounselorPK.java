package com.samsung.sodam.db.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * 관심상담사 복합키
 */
@Data
public class FavoriteCounselorPK implements Serializable {
    private String clientId;
    private String CounselorId;
}
