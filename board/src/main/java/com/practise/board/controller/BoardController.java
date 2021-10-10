package com.practise.board.controller;

import java.util.List;


import com.practise.board.model.BoardModel;
import com.practise.board.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "게시판")
@RequestMapping("/board")
@RestController
public class BoardController {
    @Autowired
    BoardService boardService;

    /**
     * @ApiOperation : swagger에 표시할 설명작성
     * 게시판 생성
     * Post/
     * /board
     * @param boardModel
     */
    @ApiOperation(value = "게사판 생성", notes = "성공시 생성한 게시판 반환")
    @PostMapping("/")
    public BoardModel create(@RequestBody BoardModel boardModel){
        return boardService.create(boardModel);
    }
     
    /**
     * 리스트 조회
     * get/
     * /board
     */
    @ApiOperation(value = "게사판 목록 조회", notes = "성공시 게시판 목록 반환")
    @GetMapping("/")
    public List<BoardModel> findAll(){
        return boardService.findAll();
    }

    /**
     * 하나 조회
     * get/
     * /board/{id}
     * @param id
     */
    @ApiOperation(value = "게사판 조회", notes = "성공시 id에 해당하는 게시판 반환")
    @GetMapping("/{id}")
    public BoardModel findById(@PathVariable("id") Long id){
        return boardService.findById(id);
    }

    /**
     * 수정
     * patch/
     * /board
     * @param boardModel
     */
    @ApiOperation(value = "게사판 수정", notes = "성공시 게시판 수정")
    @PatchMapping("/")
    public BoardModel update(@RequestBody BoardModel boardModel){
        return boardService.update(boardModel.getId(), boardModel);
    }
    
    /**
     * 삭제
     * delete/
     * /board
     * @param id
     */
    @ApiOperation(value = "게사판 삭제", notes = "성공시 id에 해당하는 게시판 삭제")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        boardService.delete(id);
        return "deleted";
    }
        
}
