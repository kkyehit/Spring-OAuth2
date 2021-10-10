package com.practise.board.service;

import java.util.List;

import com.practise.board.model.BoardModel;
import com.practise.board.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
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
        if(boardModel.getOwner() != null) board.setOwner(boardModel.getOwner());
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
}
