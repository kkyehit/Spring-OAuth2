package com.practise.board.config;

import java.util.Map;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

/**
 * 토큰에서 추가 정보를 얻기 위한 클래스
 */
@Component
public class CustomAccessTokenConverter extends DefaultAccessTokenConverter{
    
    @Override
    public  OAuth2Authentication extractAuthentication(Map<String,?> claims){
        OAuth2Authentication auth2Authentication = super.extractAuthentication(claims);
        auth2Authentication.setDetails(claims);
        return auth2Authentication;
    }
}
