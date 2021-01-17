package com.experiencers.server.smj.comment;

import com.experiencers.server.smj.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
