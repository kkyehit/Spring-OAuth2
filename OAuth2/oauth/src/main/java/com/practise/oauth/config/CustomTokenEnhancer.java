package com.practise.oauth.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
/**
 * 커스텀 tokne enhancer
 */
public class CustomTokenEnhancer implements TokenEnhancer{

    /**
     * enhance 메소드를 통해 토큰에 정보를 추가
     *  OAuth2Authentication 객체에서 principal에 대한 정보를 추출 후 OAuth2AccessToken 객체에 추가
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication){
        UserDetails userdDetails = (UserDetails) authentication.getPrincipal();
        Map<String, Object> additionalInfo = new HashMap<String, Object>();

        additionalInfo.put("username", userdDetails.getUsername());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
    }
}
