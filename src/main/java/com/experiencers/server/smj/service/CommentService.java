package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Board;
import com.experiencers.server.smj.repository.BoardRepository;
import com.experiencers.server.smj.domain.Comment;
import com.experiencers.server.smj.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BoardRepository boardRepository;
    public Comment writeComment(Comment inputtedComment,Long board_id){
        Board board = boardRepository.findById(board_id).get();
        inputtedComment.setBoard(board);


        Comment savedComment = commentRepository.save(inputtedComment);


        return savedComment;
    }
    public Comment readComment(Long comment_id){return commentRepository.findById(comment_id).get();}

    public List<Comment> readAllComment(){return commentRepository.findAll();}

    public void removeComment(Long comment_id){
        //commentRepository.delete(comment);
        commentRepository.deleteById(comment_id);
    }

    public void updateComment(Comment comment){
        Comment beforeComment = commentRepository.findById(comment.getComment_id()).get();
        beforeComment.setUser(comment.getUser());
        beforeComment.setContent(comment.getContent());

        commentRepository.save(beforeComment);
    }
}
