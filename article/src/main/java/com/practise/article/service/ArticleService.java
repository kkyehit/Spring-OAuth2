package com.practise.article.service;

import java.util.List;
import java.util.Map;

import com.practise.article.model.ArticleModel;
import com.practise.article.repository.ArticleRepository;
import com.practise.article.service.board.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    BoardService boardService;

    /**
     * 게시글 생성
     */
    public ArticleModel create(ArticleModel articleModel){
        articleModel.setBoardName(boardService.getBoard(articleModel.getBoardId()).getBoardName());
        return articleRepository.save(articleModel);
    }

    /**
     * 게시글 단일 조회
     * @throws NotFoundException
     */
    public ArticleModel findById(Long id) throws NotFoundException{
        return articleRepository.findById(id).orElseThrow(()->new NotFoundException(id+"not found"));
    }

    /**
     * 게시글 목록 조회
     */
    public List<ArticleModel> findAll() {
        return articleRepository.findAll();
    }

    /**
     * 게시판을 기준으로 게시글 목록 조회
     */
    public List<ArticleModel> findByBoardId(Long boardId) throws NotFoundException{
        return articleRepository.findAllByBoardId(boardId);
    }

    /**
     * 게시글 삭제
     */
    public void delete(Long id) throws NotFoundException{
        articleRepository.delete(
            articleRepository.findById(id).orElseThrow(()->new NotFoundException(id+"not found"))
        );
    }

    /**
     * 게시글 수정
     * @throws NotFoundException
     */
    public ArticleModel patch(ArticleModel articleModel) throws NotFoundException{
        ArticleModel savedArticle = articleRepository.findById(articleModel.getId()).orElseThrow(()->new NotFoundException(articleModel.getId()+"not exist"));
        if(articleModel.getTitle() != null ) savedArticle.setTitle(articleModel.getTitle());
        if(articleModel.getContents() != null ) savedArticle.setContents(articleModel.getContents());

        return articleRepository.save(savedArticle);
    }
}
