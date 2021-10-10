package com.practise.article.service.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practise.article.model.board.BoardModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * board 서버와 통신할 수 있는 메서드 제공하는 클래스
 */
@Service
public class BoardService {
    @Autowired
    RestTemplate restTemplate;

    private final static String BOARD_HOST    = "http://localhost:8090/"; // 
    private final static String BOARD_PATH    = "/board/";                //  

    
    public BoardModel getBoard(Long boardId) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE2MzM4OTU1NTIsImF1dGhvcml0aWVzIjpbIlVTRVIiXSwianRpIjoiY0xPVjNTZkIwTUZPTWxxbFdJMlgwQXFzUDA4IiwiY2xpZW50X2lkIjoiY2xpZW50X2lkIiwidXNlcm5hbWUiOiJhZG1pbiJ9.lVHWw8Tbm5vP0H_lhugibsIR7Lr_FPV0kZLA9sbaDi8";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "bearer  "+token);
        
        HttpEntity request = new HttpEntity(headers);

        System.out.println("before Call");
        ResponseEntity<BoardModel> res = restTemplate.exchange(BOARD_HOST + BOARD_PATH + boardId, HttpMethod.GET, request, BoardModel.class);
        System.out.println("after Call ... "+res);
        if(res.getStatusCode() == HttpStatus.OK){
            System.out.println("HttpStatus.OK .. \n\n"+res.getBody()+"...\n\n");
            return res.getBody();
        }
        System.out.println("HttpStatus .. \n\n"+res.getStatusCode()+"...\n\n");
        return null;
    }
}
