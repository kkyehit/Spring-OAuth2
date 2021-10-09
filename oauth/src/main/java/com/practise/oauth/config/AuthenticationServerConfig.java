package com.practise.oauth.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


/**
 * @EnableAuthorizationServer : OAuth 관련 endpoints(/oauth/token, /oauth/authorize 등.)가 생성된다.(AuthorizationEndpoint 및 TokenEndpoint를 활성화) 
 */

@Configuration
@EnableAuthorizationServer
public class AuthenticationServerConfig extends AuthorizationServerConfigurerAdapter {
    
    final static String CLINET_ID = "client_id";            // Client 아이디
    final static String CLINET_SECRET = "client_secret";    // Client 시크릿

    /**
     * Spring Security에서 인증 (Authenticaiton) 은 AuthenticationManager가 담당한다.
     */
    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    TokenStore tokenStore;
    
    @Autowired
    TokenEnhancer tokenEnhancer;

    @Autowired
    JwtAccessTokenConverter tokenConverter;


    /**
     * 요청 클라이언트 정보를 설정
     * inMemory(): 클라이언트 정보를 메모리에 저장한다. 개발 환경에 적합하다. 반면 jdbc()는 데이터베이스에 저장한다. 운영 환경에 적합하다.
     * withClient(): client_id 값을 설정한다. 
     * secret() : client_secret 값을 설정한다.
     * scopes() : scope 값을 설정한다.
     * authorizedGrantTypes() : grant_type값을 설정한다. 복수개를 저장할 수 있다. 
     *                          client_credentials 타입 : client_id, client_secret 만으로 access_token을 요청할 수 있는
     * 
     * 메모리에 클라이언트 정보를 저장
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient(CLINET_ID)
            .secret(CLINET_SECRET)
            .redirectUris("http://localhost:8080/oauth2/callback")
            .authorizedGrantTypes("authorization_code", "password", "refresh_token")
            .scopes("read", "write");
    }

    /**
     * token 발듬 방식을 JWT 토큰 방식으로 변경한다.
     * @param endpoints
     * @throws Exception
     *  */    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();

        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer, tokenConverter));
                
        endpoints
            .authenticationManager(authenticationManager)
            .tokenStore(tokenStore)
            .tokenEnhancer(tokenEnhancerChain);
    }

    /** 
     * 
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 토큰유효성(/token/check_token) 접근을 위해 설정
        security.checkTokenAccess("permitAll()");
    }
}
