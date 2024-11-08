package com.example.ecommece_f1_v1.Repository.User;

import com.example.ecommece_f1_v1.Entity.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("select u from UserEntity u WHERE u.email = :input OR u.username = :input")
    Optional<UserEntity> findByUsernameOrEmail(@Param("input") String input);

}
