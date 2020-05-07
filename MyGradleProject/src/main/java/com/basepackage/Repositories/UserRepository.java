package com.basepackage.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.basepackage.Entities.User;
import com.basepackage.Entities.User_project_link;



public interface UserRepository extends JpaRepository<User, Long>{
	
	
	
	
}
