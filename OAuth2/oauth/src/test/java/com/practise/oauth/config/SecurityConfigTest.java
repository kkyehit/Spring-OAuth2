package com.practise.oauth.config;

import java.util.Base64;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SecurityConfigTest {

	@Autowired
	private PasswordEncoder passwordEncoder;

    @Test
	@DisplayName("Password Encorder Test")
	void passwordEncoderTest() {

		String rawPassword = "admin";

		System.out.println("\n\n###Password Encorder Test###\n\n"+passwordEncoder.encode(rawPassword)+"\n\n");
	}

	@Test
	@DisplayName("encoded Credentials Test (base64)")
	void encodedCredentialsTest() {
		String clientId = "client_id";
		String clientSecret = "client_secret";
		String credentials = clientId+":"+clientSecret;

		System.out.println("\n\n###encoded Credentials Test (base64)###\n\n"+Base64.getEncoder().encode(credentials.getBytes())+"\n\n");
	}
}
