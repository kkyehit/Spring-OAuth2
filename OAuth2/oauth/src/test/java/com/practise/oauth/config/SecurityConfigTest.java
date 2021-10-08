package com.practise.oauth.config;

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

			System.out.println(passwordEncoder.encode(rawPassword));
	}
}
