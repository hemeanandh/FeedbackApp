package com.basepackage.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.basepackage.Entities.User_project_link;

public interface UserProjectLinkRepository extends JpaRepository<User_project_link, Long>{

//	@Query(value ="select p.project_id from user_project_link p where p.user_id=(:empId)",nativeQuery = true)
//	 long getMyProjectId(@Param("empId")int empId);
//	
	
	@Query(value = "select user_id from user_project_link where project_id in "
			+ "(select project_id from user_project_link where user_id=(:empId))",nativeQuery = true)
	 List<Long> findUserByProject(@Param("empId")Long empId);
	
}
