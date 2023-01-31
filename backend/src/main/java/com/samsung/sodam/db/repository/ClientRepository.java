package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.Client;
import com.samsung.sodam.db.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,String> , ClientCustomRepository{

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Client c SET c.password= :password where c.id= :id")
    int updatePassword(String id, String password);


    boolean existsByEmail(String email);

    // 내부적으로 EntityManager.getReference() 메소드를 호출하기 때문에 프록시만 반환, 실제로 사용하기 전에는 db 접근 x)
    Client getById(String id);

    Optional<Client> findById(String id);

    List<Client> findByName(String name);

    Client getByEmail(String email);

    Member findOneById(String id);
}
