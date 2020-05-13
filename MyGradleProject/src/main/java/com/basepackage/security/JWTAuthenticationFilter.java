package com.basepackage.security;

import com.auth0.jwt.JWT;
import com.basepackage.MyConstants;
import com.basepackage.Entities.LoginUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
        	 System.out.println(req.getInputStream().toString());
            LoginUser creds = new ObjectMapper()
                   .readValue(req.getInputStream(), LoginUser.class);
        	 
//        	 LoginUser creds = new LoginUser();
//        	 creds.setPassword("user10");
//        	 creds.setUsername("vincent");
            
            res.getWriter().write(new ObjectMapper().writeValueAsString(creds));
           
            Authentication authentication =  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getId(),
                            creds.getPassword(),
                            Collections.singleton(new SimpleGrantedAuthority(creds.getRole_id()+"")))
            );
            
            return authentication;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

    	SimpleGrantedAuthority simpleGrantedAuthority = (SimpleGrantedAuthority) auth.getAuthorities().toArray()[0];
        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withClaim("role",simpleGrantedAuthority.getAuthority() )
                .withExpiresAt(new Date(System.currentTimeMillis() + MyConstants.EXPIRATION_TIME))
                .sign(HMAC512(MyConstants.SECRET.getBytes()));
        
        String storeToken = MyConstants.TOKEN_PREFIX + token;
        res.addHeader(MyConstants.HEADER_STRING, storeToken);
       res.addCookie(new Cookie(MyConstants.HEADER_STRING,token));
       
      
       
    }
}
