# Spring Oauth2

### Authorization Server
- Authorization Server를 만들고 액세스 토큰을 부여하려면 @EnableAuthorizationServer를 사용하고 security.oauth2.client.client-id 및 security.oauth2.client.client-secret] 속성을 제공해야 합니다. 
- 클라이언트는 메모리 내 저장소에 등록됩니다.
- 다음 명령어를 통해 token을 얻을 수 있다.
```bash
$ curl client_id:client_secret@localhost:8080/oauth/token -d grant_type=authorization_code -d username=admin -d password=admin
```
브라우저에서 접속
```
http://localhost:8080/oauth/authorize?client_id=client_Id&response_type=code
```

### Resource Server
- 액세스 토큰을 사용하려면 리소스 서버(인증 서버와 동일할 수 있음)가 필요합니다. 
- 리소스 서버를 만드는 것은 쉽습니다. @EnableResourceServer를 추가하고 서버가 액세스 토큰을 디코딩할 수 있도록 일부 구성을 제공하기만 하면 됩니다. 
- 애플리케이션이 Authorization Server이기도 한 경우 토큰을 디코딩하는 방법을 이미 알고 있으므로 다른 할 일이 없습니다. 
- 앱이 독립 실행형 서비스인 경우 다음 옵션 중 하나인 추가 구성을 제공해야 합니다.
    - security.oauth2.resource.user-info-uri  (Ex, https://uaa.run.pivotal.io/userinfo)
    - security.oauth2.resource.token-info-uri (Ex, https://uaa.run.pivotal.io/check_token)
- 또는 토큰이 JWT인 경우 (user-info-uri 또는 token-info-uri 대신) security.oauth2.resource.jwt.key-value를 구성하여 로컬에서 디코딩할 수 있습니다(여기서 키는 확인 키임). 
- 확인 키 값은 대칭 비밀 또는 PEM으로 인코딩된 RSA 공개 키입니다. 
- 키가 없고 공개된 경우 security.oauth2.resource.jwt.key-uri를 사용하여 다운로드할 수 있는 URI("값" 필드가 있는 JSON 객체로)를 제공할 수 있습니다.


### No Authorization Server Support
- 2012년 10월에는 OAuth 2.0 인증 프레임워크인 RFC 6749가 게시되었습니다. 이후 2014년 5월 Spring Security OAuth는 Authorization Server, Resource Server 및 Client를 지원하는 2.0.0 버전을 출시했습니다. 이것은 OAuth 2.0 라이브러리와 제품이 없는 상황에서 매우 의미가 있었습니다.
https://spring.io/blog/2019/11/14/spring-security-oauth-2-0-roadmap-update

- Spring Security의 Authorization Server 지원은 결코 적합하지 않았습니다. Authorization Server는 제품을 빌드하기 위해 라이브러리가 필요합니다. 프레임워크인 Spring Security는 라이브러리나 제품을 구축하는 비즈니스가 아닙니다. 예를 들어, 우리는 JWT 라이브러리가 없지만 대신 Nimbus를 사용하기 쉽게 만듭니다. 또한 자체 SAML IdP, CAS 또는 LDAP 제품을 유지 관리하지 않습니다.

- 2019년에는 상용 및 오픈 소스 인증 서버가 많이 있습니다. 따라서 Spring Security 팀은 더 이상 인증 서버에 대한 지원을 제공하지 않기로 결정했습니다.

- 업데이트: Authorization Server를 지원하지 않기로 한 결정에 대한 의견을 보내주신 모든 분들께 감사드립니다. 이 피드백과 일부 내부 논의로 인해 이 결정을 다시 검토하고 있습니다. 진행 상황이 있으면 커뮤니티에 알립니다.


https://docs.spring.io/spring-security-oauth2-boot/docs/2.1.0.M2/reference/htmlsingle/


### 실행
- 브라우저에서 아래에 URL로 접속
```
http://localhost:8080/oauth/authorize?client_id=client_id&response_type=code
```
- 등록된 사용자 계정으로 접속하면 토큰을 얻을 수 있다 (admin/admin)


- CMD에서 CURL 명령어 실행

```
curl -d grant_type=password -d username=admin -d password=admin -H "Authorization: Basic Y2xpZW50X2lkOmNsaWVudF9zZWNyZXQ=" -X POST http://localhost:8080/oauth/token
```
- String credentials = clientId + ":" + secret;
- String encodedCredentials = new String(Base64.encode(credentials.getBytes()));
- /oauth/token 을 호출할때 위에서 생성된 encodedCredentials 값을 Header 내 Authorization 에 넣어줘야 한다.

결과
```
{"access_token":"GskCIpBGIoLJvRneTqffBER3s-E","token_type":"bearer","refresh_token":"65NkeA_k4EkWP2rFPnNUNoSZUgI","expires_in":43199,"scope":"read write"}
```

https://lemontia.tistory.com/927