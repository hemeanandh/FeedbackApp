package com.basepackage.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.basepackage.DummyClass;
import com.basepackage.Entities.LoginUser;
import com.basepackage.Repositories.LoginUserRepository;

import static java.util.Collections.emptyList;

import java.util.Collections;
import java.util.Optional;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {
   
	@Autowired
	public LoginUserRepository loginUserRepository;

	@Autowired
	public DummyClass dummy;

   
	//UserId will be passed. Instead of userName
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	  System.out.println("username "+username);
    	  System.out.println("dummy "+ dummy.hello());
//
//    	  LoginUser loginUser = new LoginUser();
//    	  loginUser.setPassword("user1");
//    	  loginUser.setUsername("arun");
//    	  loginUser.setId(1);
//    	Optional<Project> opLoginUser = projectRepository.findById((long) 1);
    	
    	  
    	  
//    	 System.out.println("tttest "+opLoginUser.get().getProjectName());
    	  LoginUser loginUser = loginUserRepository.findByid(Long.parseLong(username));
//        if (opLoginUser == null) {
//      	  System.out.println("test");
//            throw new UsernameNotFoundException(username);
//        }
    	  System.out.println("ttrer"+loginUser.getRole_id());
        return new User(loginUser.getUsername(), loginUser.getPassword(), Collections.singleton(
        		new SimpleGrantedAuthority(loginUser.getRole_id()+"" )) );
    }
}
