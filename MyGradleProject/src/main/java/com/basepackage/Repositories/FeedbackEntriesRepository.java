package com.basepackage.Repositories;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.context.annotation.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.basepackage.Entities.FeedbackAttr;
import com.basepackage.Entities.FeedbackEntries;
import com.basepackage.Entities.User_project_link;


public interface FeedbackEntriesRepository extends JpaRepository<FeedbackEntries, Long> {
	
	@Query(value="select * from feedback_entries  where  fb_target_id IN "
			+ "(select user_id from user_project_link where project_id in " 
						+ "(select project_id from user_project_link where user_id=(:empId)) )",nativeQuery = true)
	
	
	public List<FeedbackEntries> getFeedbacksOfMySubordinates(@Param("empId")long empId);

}
