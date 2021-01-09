package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Comment;
import com.experiencers.server.smj.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment writeComment(Comment inputtedComment){
        Comment savedComment = commentRepository.save(inputtedComment);

        return savedComment;
    }
    public Comment readComment(Long comment_id){return commentRepository.getOne(comment_id);}

    public List<Comment> readAllComment(){return commentRepository.findAll();}

    public void removeComment(Long comment_id){
        commentRepository.deleteById(comment_id);
    }

    public void updateComment(Comment comment){
        Comment beforeComment = commentRepository.findById(comment.getComment_id()).get();
        beforeComment.setUser(comment.getUser());
        beforeComment.setContent(comment.getContent());

        commentRepository.save(beforeComment);
    }
}
