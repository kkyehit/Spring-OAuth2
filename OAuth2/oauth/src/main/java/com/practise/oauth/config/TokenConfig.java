package com.practise.oauth.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import ch.qos.logback.core.rolling.helper.TokenConverter;

/**
 * token 관련 설정을 한다.
 */

@Configuration
public class TokenConfig {
    private static final String CONVERT_KEY = "admin";

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

    /**
     * 토큰에 담을 정보를 추가한다.
     * @return
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }
}
