package com.practise.oauth.service;

import java.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practise.oauth.model.OAuthTokenModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 토큰 정보를 반환할 서비스 클래스
 */
@Service
public class OAuthTokenService {
    final static String CLINET_ID       = "client_id";        // Client 아이디
    final static String CLINET_SECRET   = "client_secret";    // Client 시크릿

    final static String AUTHENTICATION_SERVER_URL   = "http://localhost:8080";  // 인증 서버 주소 
    final static String AUTHENTICATION_SERVER_PATH  = "/oauth/token";           // 인증 서버에서 토큰을 받급받기 위한 경로

    @Autowired
    RestTemplate restTemplate;  // URL로 요청을 보내기 위한 RestTemplate 

    @Autowired
    ObjectMapper objectMapper;  // 요청 결과를 객체로 맵핑하기 위한 ObjectMapper

    /**
     * code를 전달받아 이에 해당하는 토큰 정보를 반환하는 메서드
     * @param Code
     * @throws JsonProcessingException
     * @return
     */
    public OAuthTokenModel getOAuthTokenModelByCode(String code) throws JsonProcessingException{
        /* CLINET_ID와 CLINET_SECRET로부터 BASE64로 인코딩된 문자열을 만든다. */
        String encodedCredentials = Base64.getEncoder().encodeToString((CLINET_ID+":"+CLINET_SECRET).getBytes());

        /* 요청 헤더를 만든다. */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + encodedCredentials);

        /* Map 객체 생성하고 요청에 필요한 파라미터 입력 */
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("grant_type", "authorization_code");

        /* 요청에 사용할 Request 객체 생성 */
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        /* 인증 서버에 토큰 요청 */
        ResponseEntity<String> response = restTemplate.postForEntity(AUTHENTICATION_SERVER_URL+AUTHENTICATION_SERVER_PATH, request, String.class);

        /* 요청 결과 확인 */
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("response.getBody() = " + response.getBody());
            OAuthTokenModel oAuthTokenModel = objectMapper.readValue(response.getBody(), OAuthTokenModel.class);
            return oAuthTokenModel;
        }
        
        /* 요청 결과가 정상적이지 않으면 null 반환*/
        return null;
    }
}
