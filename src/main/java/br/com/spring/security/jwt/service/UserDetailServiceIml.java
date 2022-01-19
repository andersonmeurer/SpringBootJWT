package br.com.spring.security.jwt.service;

import java.util.Collections;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.spring.security.jwt.data.UserData;

@Service
public class UserDetailServiceIml implements UserDetailsService {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserDetailServiceIml(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserData user = findUser(username);
		if (null == user) {
			throw new UsernameNotFoundException(username);
		}

		return new User(user.getUserName(), user.getPassword(), Collections.emptyList());
	}

	private UserData findUser(String username) {

		UserData user = new UserData();
		user.setUserName("admin");
		user.setPassword(bCryptPasswordEncoder.encode("admin"));

		return user;
	}
}
