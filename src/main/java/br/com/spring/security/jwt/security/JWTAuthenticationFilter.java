package br.com.spring.security.jwt.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		String header = request.getHeader(SecurityConstants.HEADER_STRING);
		
		if (null == header || !header.startsWith(SecurityConstants.TOKE_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		if (null == token) {
			return null;
		}
		
		String user = JWT.require(com.auth0.jwt.algorithms.Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
				.build()
				.verify(token.replace(SecurityConstants.TOKE_PREFIX, ""))
				.getSubject();
		
		if (null != user) {
			return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
		}

		return null;
	}
}