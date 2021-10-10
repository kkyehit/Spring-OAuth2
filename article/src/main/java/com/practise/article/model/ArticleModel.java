package com.practise.article.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import com.practise.article.service.board.BoardService;

import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * article_tb 의 데이터를 담는 객체
 */
@Getter
@Setter
@Entity(name="article_tb")
public class ArticleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "게시글ID",  allowEmptyValue = true, example = "1")
    private Long id;
    
    @ApiModelProperty(notes = "게시글ID",  allowEmptyValue = false, example = "Article1")
    private String title;

    @ApiModelProperty(notes = "게시글 내용",  allowEmptyValue = false, example = "Contents")
    private String contents;

    @ApiModelProperty(notes = "글쓴이",  allowEmptyValue = false, example = "author")
    private String author;

    @ApiModelProperty(notes = "게시판ID",  allowEmptyValue = false, example = "1")
    private Long boardId;

    @ApiModelProperty(notes = "게시판이름",  allowEmptyValue = false, example = "author")
    private String boardName;

    @ApiModelProperty(notes = "만든날짜",  allowEmptyValue = true, example = "2021-01-01")
    private Date createdAt;     // 만든날짜
    @ApiModelProperty(notes = "수정날짜",  allowEmptyValue = true, example = "2021-01-01")
    private Date modifiedAt;    // 변경날짜

    /**
     * 생성되기 전 생성날짜 및 변경 날자를 입력한다.
     */
    @PrePersist
    public void prePersist() {
        createdAt = new Date();
        modifiedAt = createdAt;
    }

    /**
     * 업데이트 전 변경 날자를 갱신한다.
     */
    @PreUpdate
    public void preUpdate(){
        modifiedAt = new Date();
    }
}
