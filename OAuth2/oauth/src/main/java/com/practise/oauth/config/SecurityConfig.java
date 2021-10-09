package com.practise.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * Spring Security 설정 
 * @EnableWebSecurity : WebSecurityConfigurerAdapter를 상속받은 클래스에 SpringSecurityFilterChain 포함
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    /**
     * Spring Security에서는 비밀번호의 단방향 암호화를 지원하는 PasswordEncoder 인터페이스와 구현체들을 제공한다
     * BcryptPasswordEncoder : BCrypt 해시 함수를 사용해 비밀번호를 암호화
     * Argon2PasswordEncoder : Argon2 해시 함수를 사용해 비밀번호를 암호화
     * Pbkdf2PasswordEncoder : PBKDF2 해시 함수를 사용해 비밀번호를 암호화
     * SCryptPasswordEncoder : SCrypt 해시 함수를 사용해 비밀번호를 암호화
     * 
     * PasswordEncoder 구현체를 Bean으로 등록한다.
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        // return new Pbkdf2PasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * AuthenticationManagerBuilder: 스프링 시큐리티의 인증에 대한 지원을 설정하는 몇 가지 메소드를 가지고 있다.
     *                               inMemoryAuthentication()를 호출하면 인메모리 사용자 저장소가 활성화된다. (인 메모리에 사용자 정보를 등록할 수 있다.)
     * 
     * 
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin")
                .roles("USER");
    }

    /**
     * HttpSecurity : 리소스(URL)에 대한 접근 권한 설정 ( 특정한 경로에 특정한 권한을 가진 사용자만 접근할 수 있도록 설정 )
     * 
     * .csrf().disable()                                         : csrf 비활성화
     * .headers().frameOptions().disable()                       : X-Frame-Options header 추가를 비활성화
     * .authorizeRequests().antMatchers("/oauth/**").permitAll() : "/oauth/**" 대해 허용
     * .formLogin()                                              : 로그인 기능 허용
     * .httpBasic()                                              : resource에 대한 접근을 요청할때 브라우저가 사용자에게 username과 password를 확인해 인가를 제한
     *                                                            요청 header에는 username과 password를 BASE64로 Encode한 값을 포함해야 한다. (Authorization: Basic <ENCODED VALUE>)
     * @param security
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests().antMatchers("/oauth/**").permitAll()
                .and()
                    .formLogin()
                .and()
                    .httpBasic();
    }

    /**
     * AuthenticationManager를 외부에서 사용 하기 위해 AuthenticationManagerBean을 이용하여 Spring Securtiy 밖으로 AuthenticationManager 빼낸다.
     * 
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
