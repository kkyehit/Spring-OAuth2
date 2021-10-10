# 생성
```
curl -X POST "http://localhost:8100/articles/" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"boardId\": \"1\", \"author\": \"admin\", \"contents\":\"contents\", \"title\":\"title\"}"
```
# 조회
```
curl -X GET "http://localhost:8100/articles/" 
```
```
curl -X GET "http://localhost:8100/articles/1" 
```
```
curl -X GET "http://localhost:8100/articles/boards/1" 
```
# 수정
curl -X PATCH "http://localhost:8100/articles/" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"boardId\": \"2\", \"author\": \"admin\", \"contents\":\"contents\", \"title\":\"title\"}"
# 삭제
```
curl -X DELETE "http://localhost:8100/articles/1" 
```


### token을 사용하지 않아 다음 오류가 발생한다.
```
{"timestamp":"2021-10-10T13:29:11.917+00:00","status":401,"error":"Unauthorized","path":"/articles/"}
```


### token 얻기 (authentication server 먼저 실행)
```
curl -d grant_type=password -d username=admin -d password=admin -H "Authorization: Basic Y2xpZW50X2lkOmNsaWVudF9zZWNyZXQ=" -X POST http://localhost:8080/oauth/token
```
-   Authorization은  `clientId + ":" + secret` 을 `base64`로 인코딩한 값
```
{"access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE2MzM5MTU3ODUsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiZ2QxTDdFOGpiaEt6LWppc3pqMDItdkdTV1QwIiwiY2xpZW50X2lkIjoiY2xpZW50X2lkIiwidXNlcm5hbWUiOiJhZG1pbiJ9.edX84eSchhksBDvwVC9KdEOeAerEDSQHHdThXDAElrc","token_type":"bearer","refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJhdGkiOiJnZDFMN0U4amJoS3otamlzemowMi12R1NXVDAiLCJleHAiOjE2MzY0NjQ1ODUsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoicFMxVmFQelVacmZZQ21ZNUV0Q2JzOTBSOUZzIiwiY2xpZW50X2lkIjoiY2xpZW50X2lkIiwidXNlcm5hbWUiOiJhZG1pbiJ9.U76OMeYV8jvPqOPDIgjW-bRZqnkENXny3oHZws_sJN4","expires_in":43199,"scope":"read write","username":"admin","jti":"gd1L7E8jbhKz-jiszj02-vGSWT0"}
```

### 요청 헤더에 토큰 추가
```
-H "Authorization: bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE2MzM4OTU1NTIsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiY0xPVjNTZkIwTUZPTWxxbFdJMlgwQXFzUDA4IiwiY2xpZW50X2lkIjoiY2xpZW50X2lkIiwidXNlcm5hbWUiOiJhZG1pbiJ9.lVHWw8Tbm5vP0H_lhugibsIR7Lr_FPV0kZLA9sbaDi8"
```


# 생성
```
curl -X POST "http://localhost:8100/articles/" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"boardId\": \"1\", \"author\": \"admin\", \"contents\":\"contents\", \"title\":\"title\"}" -H "Authorization: bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE2MzM4OTU1NTIsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiY0xPVjNTZkIwTUZPTWxxbFdJMlgwQXFzUDA4IiwiY2xpZW50X2lkIjoiY2xpZW50X2lkIiwidXNlcm5hbWUiOiJhZG1pbiJ9.lVHWw8Tbm5vP0H_lhugibsIR7Lr_FPV0kZLA9sbaDi8"
```
- 결과
```
{"id":1,"title":"title","contents":"contents","author":"admin","boardId":1,"boardName":null,"createdAt":"2021-10-10T13:31:28.763+00:00","modifiedAt":"2021-10-10T13:31:28.763+00:00"}
```