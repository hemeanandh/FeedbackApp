package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import MyGradleProject.UserPrincipal;
import MyGradleProject.Entities.LoginUser;
import MyGradleProject.Entities.Project;
import MyGradleProject.repository.LoginUserRepository;
import MyGradleProject.repository.ProjectRepository;

import static java.util.Collections.emptyList;

import java.util.Optional;

@Service
@EnableJpaRepositories("MyGradleProject.repository")
public class UserDetailsServiceImpl implements UserDetailsService {
   
	@Autowired
	private LoginUserRepository loginUserRepository;


   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	  System.out.println("username "+username);
//
//    	  LoginUser loginUser = new LoginUser();
//    	  loginUser.setPassword("user1");
//    	  loginUser.setUsername("arun");
//    	  loginUser.setId(1);
//    	Optional<Project> opLoginUser = projectRepository.findById((long) 1);
    	
    	  
//    	 System.out.println("tttest "+opLoginUser.get().getProjectName());
    	  LoginUser loginUser = loginUserRepository.findByUsername(username);
//        if (opLoginUser == null) {
//      	  System.out.println("test");
//            throw new UsernameNotFoundException(username);
//        }
        return new User(loginUser.getUsername(), loginUser.getPassword(), emptyList());
    }
}
