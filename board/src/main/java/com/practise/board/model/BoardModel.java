package com.practise.board.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * board_tb에서 데이터를 가져오는 객체
 */
@Getter
@Setter
@Entity(name="board_tb")
public class BoardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "게시판ID",  allowEmptyValue = true, example = "0")
    private Long id;            // 데이터의 ID

    @ApiModelProperty(notes = "게시판이름",  allowEmptyValue = false, example = "AAA")
    private String boardName;   // 게시판이름
    @ApiModelProperty(notes = "게시판만든사람",  allowEmptyValue = false, example = "admin")
    private String owner;       // 게시판소유자

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
