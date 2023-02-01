//package com.samsung.sodam.db.repository;
//
//import com.samsung.sodam.config.TestConfig;
//import com.samsung.sodam.db.entity.Counselor;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.Import;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@Import(TestConfig.class)
//public class CounselorTest {
//
//    @Autowired
//    CounselorRepository repository;
//
//    @Test
//    public void createCounselor() {
//        Counselor counselor
//                = Counselor.builder()
//                .id("id1")
//                .email("email@naver.com")
//                .tel("01023234545")
//                .enterprise(null)
//                .password("password")
//                .major("major")
//                .career("career")
//                .groopCodeId("group-code")
//                //.gender(Gender.MALE)
//                .qualification(false)
//                .introduce("introduce")
//                .name("이름")
//                .build();
//
//        repository.save(counselor);
//
//        Counselor c2 = repository.getById("id1");
//        assertThat(counselor.getId()).isEqualTo(c2.getId());
//    }
//
//}
