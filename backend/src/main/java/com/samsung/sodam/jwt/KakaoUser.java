package com.samsung.sodam.jwt;

import com.samsung.sodam.db.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class KakaoUser extends Member {
    //private String id;  // longtype -> String으로 저장
    //private String nickname;
    private String thumbnailImage;
    //private String email;

    private String password = "NO_PASSWORD";
}
