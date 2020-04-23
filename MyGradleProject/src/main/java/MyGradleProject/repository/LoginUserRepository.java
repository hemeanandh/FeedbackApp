package MyGradleProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import MyGradleProject.Entities.LoginUser;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {
	
	
    LoginUser findByUsername(String username);
}