package com.basepackage.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.basepackage.Repositories.SimpleCORSFilter;
import com.google.common.collect.ImmutableList;

import org.springframework.security.crypto.password.PasswordEncoder;



@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
//@EnableJpaRepositories(basePackageClasses = LoginUserRepository.class)
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	public UserDetailsServiceImpl userDetailsService;

	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService)
	                .passwordEncoder(NoOpPasswordEncoder.getInstance());
	    }
	 
	
	 
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {

	       http.csrf().disable();
	        
	        http
	        .cors().and()
	        	.authorizeRequests()
	                .antMatchers("/api/v1/manageFeedback").hasAuthority("2")
	                .antMatchers("/api/v1/**").authenticated()
	                .anyRequest().permitAll()
	                .and()
	                
	               .addFilter(new JWTAuthenticationFilter(authenticationManager()))
	                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
	                
	                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	                
	                .formLogin().successHandler(successHandler());
	    }
	 
	 private AuthenticationSuccessHandler successHandler() {
		    return new AuthenticationSuccessHandler() {
		     
		    	
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				// TODO Auto-generated method stub
				System.out.println("login success");
				 response.getWriter().append("OK");
			        response.setStatus(200);
			       
			        
			       
			}
		    };
		  }
	 

	 @Bean
		CorsConfigurationSource corsConfigurationSource() 
		{
			CorsConfiguration configuration = new CorsConfiguration();
			configuration.setAllowedOrigins(ImmutableList.of("*"));
			//configuration.addAllowedOrigin("*");
			configuration.setAllowedMethods(ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
			configuration.setAllowCredentials(true);
			configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", configuration);
			return source;
		}
	
	
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(userDetailsService);
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//		return provider;
//	}
	
	
	
	
	
    
//    public WebSecurity( ) {
//     
//       
//    }
//	 @Override
//	 
//	    protected void configure(HttpSecurity http) throws Exception {
//	        http.cors().and().csrf().disable().authorizeRequests()
//	                .antMatchers(HttpMethod.POST, "/test").permitAll()
//	                .anyRequest().authenticated()
//	                .and()
////	                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
////	                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
//	                // this disables session creation on Spring Security
//	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	    }
//
//	    @Override
//	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	    }
//
//	    @Bean
//	    public PasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
//	  @Bean
//	  CorsConfigurationSource corsConfigurationSource() {
//	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//	    return source;
//	  }

}
