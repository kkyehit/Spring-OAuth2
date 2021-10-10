package com.practise.article.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/**
 * spring security 설정
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    /**
     * ResourceServerConfigurerAdapter 보다 우선순위가 낮은 듯 함
     * (여기서 .permitAll() 해도 ResourceServerConfigurerAdapter에서 .access 설정하면 401.Unauthorized 에러)
     */
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/articles/**").permitAll()
                    .antMatchers("/swagger*").permitAll()
                .and()
                    .formLogin()
                .and()
                    .httpBasic();
        
    }
}
