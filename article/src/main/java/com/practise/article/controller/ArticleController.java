package com.practise.article.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practise.article.model.ArticleModel;
import com.practise.article.service.ArticleService;

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
import javassist.NotFoundException;

@Api(value = "게시글 CRUD 제공")
@RequestMapping("/articles")
@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    /**
     * 게시글 생성
     */
    @ApiOperation(value = "게시글 생성", notes = "게시글을 생성한다.")
    @PostMapping("/")
    public ArticleModel create(@RequestBody ArticleModel articleModel){
        return articleService.create(articleModel);
    }

    /**
     * 게시글 단일 조회
     * @throws NotFoundException
     */
    @ApiOperation(value = "게시글 단일 조회", notes = "ID에 해당하는 게시글 하나를 조회한다.")
    @GetMapping("/{id}")
    public ArticleModel findById(@PathVariable ("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        try{
            return articleService.findById(id);
        } catch(NotFoundException e){
            // Id에 해당하는 게시글이 없다면 NOT_FOUND 상태코드 반환
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    /**
     * 게시글 목록 조회
     */
    @ApiOperation(value = "게시글 목록 조회", notes = "게시글 전체를 조회한다.")
    @GetMapping("/")
    public List<ArticleModel> findAll() {
        return articleService.findAll();
    }

    /**
     * 게시판을 기준으로 게시글 목록 조회
     */
    @ApiOperation(value = "게시판의 게시글 목록 조회", notes = "게시판 ID에 해달하는 게시글 전체를 조회한다.")
    @GetMapping("/boards/{id}")
    public List<ArticleModel> findByBoardId(@PathVariable ("id") Long boardId, HttpServletRequest request, HttpServletResponse response){
        try {
            return articleService.findByBoardId(boardId);
        } catch (NotFoundException e) {
            // Id에 해당하는 게시글이 없다면 NOT_FOUND 상태코드 반환
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    /**
     * 게시글 삭제
     */
    @ApiOperation(value = "게시판 삭제", notes = "ID에 해달하는 게시글을 삭제한다.")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response){
        try {
            articleService.delete(id);
            return "Deleted";
        } catch (NotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "Not Found Exception";
        }
    }

    /**
     * 게시글 수정
     * @throws NotFoundException
     */
    @ApiOperation(value = "게시판 수정", notes = "게시글을 수정한다.")
    @PatchMapping("/")
    public ArticleModel patch(@RequestBody ArticleModel articleModel, HttpServletRequest request, HttpServletResponse response){
        try {
            return articleService.patch(articleModel);
        } catch (NotFoundException e) {
            // Id에 해당하는 게시글이 없다면 NOT_FOUND 상태코드 반환
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }   
}
