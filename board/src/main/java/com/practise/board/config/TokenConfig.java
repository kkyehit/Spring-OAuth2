package com.practise.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class TokenConfig {
    private static final String CONVERT_KEY = "admin";  // Authentication 서버와 같아야 한다.


    /**
     * token Converter 빈 등록
     * jwt 토큰을 키로 검증한다. 이 키는 resource server와 auth server가 동일해야 한다.
     * @return
     */
    @Bean
    public JwtAccessTokenConverter tokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(CONVERT_KEY);
        return jwtAccessTokenConverter;
    }

    /**
     * 토큰을 jwt Token Store에 저장
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(tokenConverter());
    }
}
