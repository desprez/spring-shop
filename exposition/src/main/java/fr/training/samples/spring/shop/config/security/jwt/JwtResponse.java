package fr.training.samples.spring.shop.config.security.jwt;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String jwttoken;

	public JwtResponse(final String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return jwttoken;
	}

}
