package com.practise.article.model.board;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * API를 통해 Board 데이터를 가져오기 위한 객체 
 */
@Getter
@Setter
public class BoardModel {
    private Long id;            // 게시판ID
    private String boardName;   // 게시판이름
    private String owner;       // 게시판소유자
    private Date createdAt;     // 만든날짜
    private Date modifiedAt;    // 변경날짜

}
