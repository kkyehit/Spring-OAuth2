package com.practise.oauth.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

/**
 * 
 * @EnableWebSecurity : WebSecurityConfigurerAdapter를 상속받은 클래스에 SpringSecurityFilterChain 포함
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration{
    
}
