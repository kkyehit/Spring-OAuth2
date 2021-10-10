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
curl -X DELETE "http://localhost:8090/board/" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"id\":1}"
```