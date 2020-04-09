package MyGradleProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import MyGradleProject.Entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
