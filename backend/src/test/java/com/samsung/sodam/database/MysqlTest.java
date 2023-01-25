package com.samsung.sodam.database;

import com.samsung.sodam.db.entity.Counselor;
import com.samsung.sodam.db.entity.STATE;
import com.samsung.sodam.db.entity.USER_TYPE;
import com.samsung.sodam.db.repository.CounselorRepository;
import com.samsung.sodam.db.specification.CounselorSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class MysqlTest {

    @Autowired
    CounselorRepository repository;

    @Test
    public void createDummyCounselor(){

        Counselor dummyCounselor = new Counselor(null,"유저이름","비밀번호","user1@naver.com","01012341234","경력사항입니다.","token", USER_TYPE.COUNSELOR,"부부상담","여", STATE.APPROVED,true,true);
        repository.saveCounselor(dummyCounselor);

        List<Counselor> allCounselor = repository.findAll(CounselorSpecification.equalFilterKeyword("유저이름"));

        assertThat(allCounselor.get(0).getEmail()).isEqualTo(dummyCounselor.getEmail());
    }
}
