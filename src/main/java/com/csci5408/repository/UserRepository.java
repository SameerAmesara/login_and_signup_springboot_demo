package com.csci5408.repository;

import com.csci5408.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<UserData, Long> {
    @Query("SELECT u FROM UserData u WHERE u.uname = ?1")
    public UserData findByUname(String uname);
}
