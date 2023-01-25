package com.samsung.sodam.database;

import com.samsung.sodam.config.TestConfig;
import com.samsung.sodam.db.entity.Counselor;
import com.samsung.sodam.db.repository.CounselorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class MysqlTest {

    @Autowired
    CounselorRepository repository;

    @Test
    public void createDummyCounselor(){
        //given
        Counselor dummyCounselor = Counselor.builder().counselorId("idid").name("유저이름").password("비밀번호").email("user1@naver.com").tel("01012341234").career("경력사항입니다.").refreshToken("token").groopCodeId("001").major("부부상담").gender("여").qualification(true).routine("rooooootine").build();
        repository.save(dummyCounselor);

        //when
        List<Counselor> postsList = repository.findAll();

        //then
        Counselor counselor = postsList.get(0);

        assertThat(counselor.getEmail()).isEqualTo(dummyCounselor.getEmail());
    }
}
