package com.basepackage.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basepackage.Entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}	
