package com.basepackage.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.basepackage.Entities.LoginUser;

public class UserPrincipal implements UserDetails{

	
	private LoginUser loginUser;
	
	 public UserPrincipal(LoginUser user) {
		// TODO Auto-generated constructor stub
		
		super();
		this.loginUser = user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		System.out.println("tttt"+loginUser.getRole_id());
		return Collections.singleton(new SimpleGrantedAuthority(loginUser.getRole_id()+""));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		System.out.println("ttt");
		return loginUser.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return loginUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
