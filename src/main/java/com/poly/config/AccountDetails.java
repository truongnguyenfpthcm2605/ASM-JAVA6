package com.poly.config;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.poly.entity.Account;


public class AccountDetails implements UserDetails {

	/**
	 * 
	 */
	BCryptPasswordEncoder p = new BCryptPasswordEncoder();
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public AccountDetails(Account account) {
		username = account.getEmail();
		password = p.encode(account.getPassword());
		authorities = account.getAuthorities().stream().map(e -> "ROLE_"+e.getRole().getId().trim())
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());


	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
