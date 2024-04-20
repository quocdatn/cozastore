package com.Cozastore_java.CozaStore_java.repository;

import com.Cozastore_java.CozaStore_java.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    //    @Query("from users where email= ?1")
//    List<UserEntity> getUserByEmail(String email);

    UserEntity findByEmail(String email);
}
