package com.authenticate.belajar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.authenticate.belajar.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query(value = "delete from tb_user where uuid=:id", nativeQuery = true)
    void deleteByUUID(@Param("id") String id);

}
