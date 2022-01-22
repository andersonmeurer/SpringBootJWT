package br.com.spring.security.jwt.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.security.jwt.data.UserEntity;
import br.com.spring.security.jwt.security.SecurityConstants;

@RestController
public class UserController {

	@RequestMapping(SecurityConstants.ALL_USERS)
	public List<UserEntity> listAll() {
		UserEntity user = new UserEntity();
		user.setUserName("anderson");
		user.setPassword(new BCryptPasswordEncoder().encode("anderson"));

		List<UserEntity> list = Arrays.asList(user);
		return list;
	}
}
