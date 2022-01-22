package br.com.spring.security.jwt.security;

public class SecurityConstants {

	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 864000000; // 10 dias em millisegundos
	public static final String TOKE_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/login";
	public static final String STATUS_URL = "/status";
	public static final String ALL_USERS = "/all-users";
}
