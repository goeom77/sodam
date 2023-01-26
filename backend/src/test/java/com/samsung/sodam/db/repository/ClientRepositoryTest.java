package com.samsung.sodam.db.repository;

import com.samsung.sodam.config.TestConfig;
import com.samsung.sodam.db.entity.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("고객 회원가입")
    @Order(1)
    public void clientSignUp(){
        //given
        String id = "idd";
        String pw = "pw";
        String email = "ddd@gmail.com";

        clientRepository.save(Client.builder()
                .clientId(id)
                .password(pw)
                .email(email)
                .tel("010-0000-0000")
                .build());

        //when
        List<Client> postsList = clientRepository.findAll();

        //then
        Client client = postsList.get(0);
        assertThat(client.getId()).isEqualTo(id);
        assertThat(client.getPassword()).isEqualTo(pw);


    }

    @Test
    @DisplayName("고객 비밀번호 수정")
    @Order(2)
    public void clientUpdatePassword(){

        String id = "idd";
        String pw = "pw";
        String newPw = "newpw";
        String email = "ddd@gmail.com";

        clientSignUp();
        Client client1 = new Client();
        client1.setId(id);
        client1.setPassword(newPw);
        //clientRepository.updateClientInfo(client1);
        clientRepository.updatePassword(id, newPw);

        //when
        List<Client> postsList = clientRepository.findAll();

        //then
        Client client = postsList.get(0);
        assertThat(client.getId()).isEqualTo(id);
        assertThat(client.getPassword()).isEqualTo(newPw);
        assertThat(client.getEmail()).isEqualTo(email);
    }
}
