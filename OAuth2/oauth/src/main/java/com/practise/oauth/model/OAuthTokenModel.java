package com.practise.oauth.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 토큰 정보를 담을 객체
 */

@Getter
@Setter
public class OAuthTokenModel {
    private String access_token;    // acceess token
    private String token_type;      // 토큰 타입
    private String refresh_token;   // token refresh
    private long expires_in;        // 토큰 만료 시간
    private String scope;           // 범위
}
