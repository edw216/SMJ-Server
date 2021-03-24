package com.experiencers.server.smj.repository;

import com.experiencers.server.smj.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByMember_EmailEquals(String email);
}
