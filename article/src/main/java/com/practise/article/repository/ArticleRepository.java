package com.practise.article.repository;

import java.util.List;
import java.util.Optional;

import com.practise.article.model.ArticleModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * database에 ArticleModel을 생성, 조회, 수정, 삭제 등의 작업 수행
 */
@Repository
public interface ArticleRepository extends JpaRepository<ArticleModel, Long>{
    public List<ArticleModel> findAllByBoardId(Long boardId);   // boardId 기준으로 ArticleModel 리스트를 가져온다.
    public Optional<ArticleModel> findById(Long id);            // id를 기준으로 ArticleModel 하나를 가져온다.
}
