package com.practise.article.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
/**
 * Token 관련 빈 설정 및 등록
 */
@Configuration
public class TokenConfig {
    private static final String CONVERT_KEY = "admin";  // Authentication 서버와 같아야 한다.
    
    /**
     * JwtAccessTokenConverter에 signingKey 등록 및 빈으로 등록
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(CONVERT_KEY);
        return jwtAccessTokenConverter;
    }

    /**
     * tokenStore로 JwtTokenStore 사용 및 JwtAccessTokenConverter 등록
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
}
