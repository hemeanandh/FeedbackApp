package com.basepackage.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.basepackage.MyConstants;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(MyConstants.HEADER_STRING);
        Cookie[] cookie = req.getCookies();
        if(header == null && cookie != null)
        for(int i =0;i<cookie.length;i++) {
        	if(cookie[i].getName().equals(MyConstants.HEADER_STRING)) {
        		header = cookie[i].getValue();
        	}
        }
//        Enumeration<String> headerNames = req.getHeaderNames();
//        if (headerNames != null) {
//            while (headerNames.hasMoreElements()) {
//                   // System.out.println(headerNames.nextElement() + " : " + req.getHeader(headerNames.nextElement()));
//            }
//    }

        if (header == null/* || !header.startsWith("Bearer ")*/) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(MyConstants.HEADER_STRING);
        if(token == null) {
        	Cookie[] cookie = request.getCookies();
        	if(cookie != null) {
        		for(int i =0;i<cookie.length;i++) {
        				if(cookie[i].getName().equals(MyConstants.HEADER_STRING)) {
        					token = cookie[i].getValue();
        				}
        		}
        	}
        }
        if (token != null) {
            // parse the token.
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(MyConstants.SECRET.getBytes()))
                    .build()
//                   //.verify(token.replace(MyConstants.TOKEN_PREFIX, ""))
                    .verify(token);
           String user= decodedJWT.getSubject();
          
           Map<String,Claim> cl = decodedJWT.getClaims();

       
            final Collection authorities = Collections.singleton(new SimpleGrantedAuthority(cl.get("role").asString()));
                    

            if (token != null) {
                return new UsernamePasswordAuthenticationToken(user, null,authorities);
            }
            return null;
        }
        return null;
    }
}
