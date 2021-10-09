package com.practise.oauth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.practise.oauth.model.OAuthTokenModel;
import com.practise.oauth.service.OAuthTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * OAuth 관련 요청 처리
 */
@RestController
public class OAuthController {
    @Autowired
    OAuthTokenService oAuthTokenService;

    /**
     * OAuth 정보를 반환한다.
     * @param code
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/oauth2/callback")
    public OAuthTokenModel callback(@RequestParam String code) throws JsonProcessingException {
        OAuthTokenModel oAuthTokenModel = oAuthTokenService.getOAuthTokenModelByCode(code);
        return oAuthTokenModel;
    }
}
