package com.practise.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate Bean을 생성하고 관리하기 위한 클래스
 */
@Configuration
public class RestTemplateConfig {
    
    /**
     * 기본 RestTemplate을 생성한다.
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
