package com.practise.article.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * API로 요청을 보내기 위한 RestTemplate을 설정 및 등록한다.
 */
public class RestTemplateConfig {
    /**
     * 새로운 RestAPI 생성
     * @return
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
