package com.practise.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

/**
 * Resource Server 설정 클래스
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

    @Autowired
    TokenStore tokenStore;

    /**jwt안쓰는 경우 */
    // @Value("${security.oauth2.client.client-id}")
    // private String clientId;

    // @Value("${security.oauth2.client.client-secret}")
    // private String clientSecret;

    // @Value("${security.oauth2.resource.token-info-uri}")
    // private String checkTokenEndpointUrl;
    /*************** */

    /**
     * token store 지정
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources){
        resources.tokenStore(tokenStore);
        
        /**jwt안쓰는 경우 */
        // RemoteTokenServices remoteTokenService1 = new RemoteTokenServices();
        // remoteTokenService1.setClientId(clientId);
        // remoteTokenService1.setClientSecret(clientSecret);
        // remoteTokenService1.setCheckTokenEndpointUrl(checkTokenEndpointUrl);

        // resources.tokenServices(remoteTokenService1);
        /*************** */
    }

    /**
     * resource server에 접근할때의 보안 설정을 한다
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception{
        /**세션이 아닌 JWT(Json Web Token)를 통하여 Stateless로 로그인을 구현하기에 세션 서비스를 사용하지 않음 */
        // HttpSessionRequestCache httpSessionRequestCache = new HttpSessionRequestCache();
        // httpSessionRequestCache.setCreateSessionAllowed(false);
        http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
                .authorizeRequests()
                    .antMatchers("/board/*").access("#oauth2.hasScope('write')") // write 권한 확인
            .and()
                .formLogin()
            .and()
                .httpBasic();
    }
}

