package com.github.hygoni.dormitory.repository;

import com.github.hygoni.dormitory.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {
}
