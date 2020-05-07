package com.basepackage.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.basepackage.Entities.LoginUser;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {
	
	
    LoginUser findByUsername(String username);
    LoginUser findByid(long user_id);
}