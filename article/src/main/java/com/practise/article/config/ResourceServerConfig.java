package com.practise.article.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 리소스서버 설정
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
    @Autowired
    TokenStore tokenStore;

    /**
     * ResourceServerSecurityConfigurer에 tokenStore 등록
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources){
        resources.tokenStore(tokenStore);
    }
    @Override
    public void configure(HttpSecurity security) throws Exception {
        security
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    /* Spring Security가 작동하는 방식과 유사하게 Spring Security OAuth에서 엔드포인트별로 권한 부여 규칙을 사용자 정의할 수 있다. */
                    .antMatchers("*").denyAll()                                      // denyAll() : 무조건 접근을 허용하지 않음
                    .antMatchers("/articles/**").access("#oauth2.hasScope('write')") // access(String) : 주어진 SpEL 표현식의 평가 결과가 true이면 접근을 허용
                    .antMatchers("/swagger*").permitAll()                            // permitAll() : 무조건 접근을 허용
                .and()
                    .formLogin()
                .and()
                    .httpBasic();
        
    }
}   
