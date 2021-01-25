package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Board;
import com.experiencers.server.smj.repository.BoardRepository;
import com.experiencers.server.smj.domain.Comment;
import com.experiencers.server.smj.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BoardRepository boardRepository;
    public Comment saveComment(Comment inputtedComment,Long boardId){
        Board board = boardRepository.findById(boardId).get();
        inputtedComment.setBoard(board);


        Comment savedComment = commentRepository.save(inputtedComment);


        return savedComment;
    }
    public Comment readComment(Long commentId){return commentRepository.findById(commentId).get();}

    public List<Comment> readAllComment(){return commentRepository.findAll();}

    public void deleteComment(Long commentId){
        //commentRepository.delete(comment);
        commentRepository.deleteById(commentId);
    }

    public Comment readAndUpdateComment(Long commentId,Comment comment){
        Optional<Comment> data = commentRepository.findById(commentId);

        if(data.isPresent()){
            Comment target = data.get();
            target.setUser(comment.getUser());
            target.setContent(comment.getContent());

            target = commentRepository.save(target);

            return target;
        }

        return null;
    }
}
