package com.practise.oauth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.practise.oauth.service.OAuthTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {
    @Autowired
    OAuthTokenService oAuthTokenService;

    @GetMapping("/oauth2/callback")
    public String callback(@RequestParam String code) throws JsonProcessingException {
        return oAuthTokenService.getOAuthTokenModelByCode(code).toString();
    }
}
