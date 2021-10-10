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
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javassist.NotFoundException;
import reactor.core.publisher.Mono;

/**
 * board 서버와 통신할 수 있는 메서드 제공하는 클래스
 */
@Service
public class BoardService {
    // @Autowired
    // RestTemplate restTemplate;

    private final static String BOARD_HOST    = "http://localhost:8090/"; // 
    private final static String BOARD_PATH    = "/board/";                //  

    /**
     * RsstTemplate를 활용한 API 통신
     * @param boardId
     * @param authorizationToken
     * @return
     */
    // public BoardModel getBoard(Long boardId, String authorizationToken) {
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_JSON);
    //     headers.add("Authorization", authorizationToken);
        
    //     HttpEntity request = new HttpEntity(headers);

    //     System.out.println("before Call");
    //     ResponseEntity<BoardModel> res = restTemplate.exchange(BOARD_HOST + BOARD_PATH + boardId, HttpMethod.GET, request, BoardModel.class);
    //     System.out.println("after Call ... "+res);
    //     if(res.getStatusCode() == HttpStatus.OK){
    //         System.out.println("HttpStatus.OK .. \n\n"+res.getBody()+"...\n\n");
    //         return res.getBody();
    //     }
    //     System.out.println("HttpStatus .. \n\n"+res.getStatusCode()+"...\n\n");
    //     return null;
    // }

    /**
     * WebClient를 활용한 API 통신
     * @param boardId
     * @param authorizationToken
     * @return
     * @throws NotFoundException
     */
    public BoardModel getBoard(Long boardId, String authorizationToken) throws NotFoundException {
        WebClient client = WebClient.create(BOARD_HOST);    // baseUrl로 BOARD_HOST 지정 (optional)

        Mono<BoardModel> res = client.get()                 // get 요청
            .uri(BOARD_PATH+boardId)                        // url 지정
            .header("Authorization", authorizationToken)    // 헤더 지정
            .retrieve()                                     // response body를 가져오는 가장 단순한 방법
            .bodyToMono(BoardModel.class)                   // Mono 형태의 BoardModel.class로 추출
            .onErrorResume(WebClientResponseException.class, ex -> ex.getRawStatusCode() == 404 ? Mono.empty() : Mono.error(ex)); // 404 에러시 빈 객체 반환
        BoardModel boardModel = res.block(); 
        if(boardModel == null) throw new NotFoundException(boardId+" id not found");
        return boardModel;                                 // BoardModel을 반환
    }
}
