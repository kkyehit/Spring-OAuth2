# Spring Oauth2

### Authorization Server
- Authorization Server를 만들고 액세스 토큰을 부여하려면 @EnableAuthorizationServer를 사용하고 security.oauth2.client.client-id 및 security.oauth2.client.client-secret] 속성을 제공해야 합니다. 
- 클라이언트는 메모리 내 저장소에 등록됩니다.
- 다음 명령어를 통해 token을 얻을 수 있다.
```bash
$ curl client:secret@localhost:8080/oauth/token -d grant_type=password -d username=user -d password=pwd
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


https://docs.spring.io/spring-security-oauth2-boot/docs/2.1.0.M2/reference/htmlsingle/