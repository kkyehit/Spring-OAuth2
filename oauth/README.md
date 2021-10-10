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

### 결과
```
{"access_token":"GskCIpBGIoLJvRneTqffBER3s-E","token_type":"bearer","refresh_token":"65NkeA_k4EkWP2rFPnNUNoSZUgI","expires_in":43199,"scope":"read write"}
```

https://lemontia.tistory.com/927