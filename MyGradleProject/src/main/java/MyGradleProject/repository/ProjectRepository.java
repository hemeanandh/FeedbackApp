package MyGradleProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import MyGradleProject.Entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
