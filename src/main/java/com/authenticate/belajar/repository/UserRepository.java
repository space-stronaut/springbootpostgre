package com.authenticate.belajar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.authenticate.belajar.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query(value = "delete from tb_user where uuid=:id", nativeQuery = true)
    void deleteByUUID(@Param("id") String id);

    @Query(value = "select * from tb_user where email=:email", nativeQuery = true)
    User getUserbyEmail(@Param("email") String email);

    @Query(value = "select * from tb_user where email=:email and password=:password", nativeQuery = true)
    User getUserbyEmailandPassword(@Param("email") String email, @Param("password") String password);

    @Query(value = "select * from tb_user where uuid=:id", nativeQuery = true)
    User getUserById(@Param("id") String id);
}
