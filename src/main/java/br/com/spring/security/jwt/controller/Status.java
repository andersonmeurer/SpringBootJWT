package br.com.spring.security.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.security.jwt.security.SecurityConstants;

@RestController
public class Status {

	@GetMapping(SecurityConstants.STATUS_URL)
	public String Status() {
		return "online";
	}
}