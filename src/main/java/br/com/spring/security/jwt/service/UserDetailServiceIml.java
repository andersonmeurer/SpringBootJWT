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

import br.com.spring.security.jwt.data.UserEntity;

@Service
public class UserDetailServiceIml implements UserDetailsService {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserDetailServiceIml(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity user = findUser(username);
		if (null == user) {
			throw new UsernameNotFoundException(username);
		}

		return new User(user.getUserName(), user.getPassword(), Collections.emptyList());
	}

	private UserEntity findUser(String username) {

		//	buscar usuarios do banco
		UserEntity user = new UserEntity();
		user.setUserName("admin");
		user.setPassword(bCryptPasswordEncoder.encode("nimda"));

		return user;
	}
}
