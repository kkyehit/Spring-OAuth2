package com.practise.board.repository;

import java.util.Optional;

import com.practise.board.model.BoardModel;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BoardModel을 생성, 조회, 갱신, 삭제를 수행하는 객체
 */
public interface BoardRepository extends JpaRepository<BoardModel, Long>{
    public Optional<BoardModel> findById(Long Id);      //Id를 이용해 BoardModel 조회
}
