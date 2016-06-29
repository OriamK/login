package com.mario.spring.dto;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mario.spring.entities.User;

public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8158281067831851119L;
	private User user;
	
	
	public UserDetailsImpl(User user) {
		
		this.user = user;		
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(1);
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return user.getPassword();	
	}

	@Override
	public String getUsername() {
	
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
	
		return true;
	}

	
	
	
}
