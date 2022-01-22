package br.com.spring.security.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.security.jwt.data.UserEntity;
import br.com.spring.security.jwt.security.SecurityConstants;

@RestController
public class AuthController {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public AuthController(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping(SecurityConstants.SIGN_UP_URL)
	public void login(@RequestBody UserEntity user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	}
}