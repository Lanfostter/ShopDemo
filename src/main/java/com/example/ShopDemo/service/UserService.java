package com.example.ShopDemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ShopDemo.entity.UserEntity;
import com.example.ShopDemo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.getbyUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("No User");
		}
		/// convert role -> security role
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		// convert user model -> security user
		org.springframework.security.core.userdetails.User userSecurity = new org.springframework.security.core.userdetails.User(
				username, user.getPassword(), authorities);
		return userSecurity;
	}

}
