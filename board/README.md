### swagger
http://localhost:8090/swagger-ui/


## 게시판 생성
```
curl -X POST "http://localhost:8090/board/" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"boardName\": \"AAA\", \"owner\": \"admin\"}"
```

## 게시판 조회
```
curl -X GET "http://localhost:8090/board/"
```
```
curl -X GET "http://localhost:8090/board/1"
```


## 게시판 수정
```
curl -X PATCH "http://localhost:8090/board/" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"id\":\"1\", \"boardName\": \"AAA\", \"owner\": \"admin2\"}"
```

## 게시판 삭제

```
curl -X DELETE "http://localhost:8090/board/1"
```


### token을 사용하지 않아 다음 오류가 발생한다.
```
{"timestamp":"2021-10-10T07:50:14.216+00:00","status":401,"error":"Unauthorized","path":"/board/"}
```


### token 얻기 (authentication server 먼저 실행)
```
curl -d grant_type=password -d username=admin -d password=admin -H "Authorization: Basic Y2xpZW50X2lkOmNsaWVudF9zZWNyZXQ=" -X POST http://localhost:8080/oauth/token
```
```
{"access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE2MzM4OTU1NTIsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiY0xPVjNTZkIwTUZPTWxxbFdJMlgwQXFzUDA4IiwiY2xpZW50X2lkIjoiY2xpZW50X2lkIiwidXNlcm5hbWUiOiJhZG1pbiJ9.lVHWw8Tbm5vP0H_lhugibsIR7Lr_FPV0kZLA9sbaDi8","token_type":"bearer","refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJhdGkiOiJjTE9WM1NmQjBNRk9NbHFsV0kyWDBBcXNQMDgiLCJleHAiOjE2MzY0NDQzNTIsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoia0t1bDQ3MERJQXhQNjJtUF9NM3VseU9naVRZIiwiY2xpZW50X2lkIjoiY2xpZW50X2lkIiwidXNlcm5hbWUiOiJhZG1pbiJ9.FoLa1Hs4008sDT7tyr5Q6ZP8MZgrY0J08uEKLBpD-0M","expires_in":43199,"scope":"read write","username":"admin","jti":"cLOV3SfB0MFOMlqlWI2X0AqsP08"}
```

### 요청 헤더에 토큰 추가
```
-H "Authorization: bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE2MzM4OTU1NTIsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiY0xPVjNTZkIwTUZPTWxxbFdJMlgwQXFzUDA4IiwiY2xpZW50X2lkIjoiY2xpZW50X2lkIiwidXNlcm5hbWUiOiJhZG1pbiJ9.lVHWw8Tbm5vP0H_lhugibsIR7Lr_FPV0kZLA9sbaDi8"
```


### 예
```
curl -X POST "http://localhost:8090/board/" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"id\":\"1\", \"boardName\": \"AAA\", \"owner\": \"admin2\"}" -H "Authorization: bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE2MzM4OTU1NTIsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiY0xPVjNTZkIwTUZPTWxxbFdJMlgwQXFzUDA4IiwiY2xpZW50X2lkIjoiY2xpZW50X2lkIiwidXNlcm5hbWUiOiJhZG1pbiJ9.lVHWw8Tbm5vP0H_lhugibsIR7Lr_FPV0kZLA9sbaDi8"
```
```
curl -X GET "http://localhost:8090/board/1" -H "Authorization: bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE2MzM4OTU1NTIsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiY0xPVjNTZkIwTUZPTWxxbFdJMlgwQXFzUDA4IiwiY2xpZW50X2lkIjoiY2xpZW50X2lkIiwidXNlcm5hbWUiOiJhZG1pbiJ9.lVHWw8Tbm5vP0H_lhugibsIR7Lr_FPV0kZLA9sbaDi8"
```