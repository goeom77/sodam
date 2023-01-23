package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,String> , ClientCustomRepository{

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Client c SET c.password= :password where c.clientId= :id")
    int updatePassword(String id, String password);




}
