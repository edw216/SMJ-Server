package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Board;
import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.manager.ManageMember;
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
    @Autowired
    private ManageMember manageMember;
    public Comment saveComment(Comment inputtedComment,Long boardId){
        Board board = boardRepository.findById(boardId).get();
        String member = manageMember.getManageMembername();
        inputtedComment.setBoard(board);

        inputtedComment.setUser(member);


        Comment savedComment = commentRepository.save(inputtedComment);


        return savedComment;
    }
    public Comment saveCommentOfAdmin(Comment inputtedComment, Long boardId){
        Board board = boardRepository.findById(boardId).get();

        inputtedComment.setBoard(board);

        Comment savedComment = commentRepository.save(inputtedComment);

        return savedComment;

    }
    public List<Comment> readComment(Long boardId){
        //Member member = manageMember.getManageMembername()
        List<Comment> comments = boardRepository.findById(boardId).get().getComments();
        System.out.println("readcomment error check");
        System.out.println(boardRepository.findById(boardId).get());
        System.out.println(boardRepository.findById(boardId).get().getComments());
        return comments;
    }
    public Comment readCommentOfAdmin(Long commentId){
        return commentRepository.findById(commentId).get();
    }
    public List<Comment> readAllComment(){return commentRepository.findAll();}

    public void deleteComment(Long commentId){
        //commentRepository.delete(comment);
        commentRepository.deleteById(commentId);
    }

    public Comment readAndUpdateComment(Long commentId, Comment comment){
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