package com.practise.board.service;

import java.util.List;
import java.util.Map;

import com.practise.board.model.BoardModel;
import com.practise.board.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

/**
 * boardRepository에서 BoardModel을 생성, 조회, 갱신, 삭제를 수행하는 객체
 */
@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    /**
     * 생성
     * @param boardModel
     * @return
     */
    public BoardModel create(BoardModel boardModel) {
        boardModel.setOwner(getUsername(SecurityContextHolder.getContext().getAuthentication()));   // token에 포함된 username을 게시판의 주인으로 설정한다.
        return boardRepository.save(boardModel);
    }

    /**
     * 조회 - findById, board하나
     * @param id
     * @return
     */
    public BoardModel findById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    /**
     * 조회 - board리스트
     * @return
     */
    public List<BoardModel> findAll() {
        return boardRepository.findAll();
    }


    /**
     * 갱신
     * @param id
     * @param boardModel
     * @return
     */
    public BoardModel update(Long id, BoardModel boardModel) {
        BoardModel board = boardRepository.findById(id).orElse(null);
        if(boardModel.getBoardName() != null) board.setBoardName(boardModel.getBoardName());
        if(boardModel.getOwner() != null) board.setBoardName(boardModel.getOwner());
        return boardRepository.save(board);
    }


    /**
     * 삭제
     * @param id
     */
    public void delete(Long id) {
        BoardModel board = boardRepository.findById(id).orElse(null);
        boardRepository.delete(board);
    }

    /**
     * Authentication에서 OAuth2AuthenticationDetails을 기져와 토큰에 포함된 username을 추출한다.
     * @param authentication
     * @return
     */
    public String getUsername(Authentication authentication){
        OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
        return ( ( Map<String, String> ) oauthDetails.getDecodedDetails() ).get("username");
    }
}
