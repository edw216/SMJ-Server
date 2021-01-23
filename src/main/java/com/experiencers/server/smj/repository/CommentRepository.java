package com.experiencers.server.smj.repository;

import com.experiencers.server.smj.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
