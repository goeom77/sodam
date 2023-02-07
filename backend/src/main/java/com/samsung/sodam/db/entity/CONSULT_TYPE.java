package com.samsung.sodam.db.entity;

import com.samsung.sodam.db.converter.BaseEnumCode;
import com.samsung.sodam.db.converter.ConsultConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Convert;
import javax.persistence.Enumerated;

@Getter
@AllArgsConstructor
public enum CONSULT_TYPE implements BaseEnumCode<Integer> {
    //아동청소년, 재난, 부부및가족상담, 재활상담, 노인상담, 중독상담, 정신건강, 교정상담, 진로상담, 상담자교육, 성폭력상담, 상담자슈퍼비전, 스포츠상담,학교상담.
    CHILD_TEENAGER("아동청소년", 0),
    CALAMITY("재난", 1),
    COUPLE_FAMILY("부부및가족상담", 2),
    REHABILITATION("재활상담", 3),
    AGED("노인상담", 4),
    ADDICTED("중독상담", 5),
    MENTAL_HEALTH("정신건강", 6),
    CORRECTION("교정상담", 7),
    COURSE("진로상담", 8),
    EDUCATION("상담자교육", 9),
    SEXUAL_VIOLENCY("성폭력상담", 10),
    SUPERVISION("상담자슈퍼비전", 11),
    SPORTS("스포츠상담", 12),
    SCHOOL("학교상담", 13),
    ALL("전체", 14);

    String name;
    Integer value;
}
