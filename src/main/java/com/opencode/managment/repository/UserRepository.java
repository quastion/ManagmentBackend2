package com.opencode.managment.repository;

import com.opencode.managment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.userName = :userName")
    User fundByUserName(@Param("userName") String userName);
}
