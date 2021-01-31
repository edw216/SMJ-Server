package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Board;
import com.experiencers.server.smj.domain.Category;
import com.experiencers.server.smj.domain.Comment;
import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.repository.BoardRepository;
import com.experiencers.server.smj.repository.CategoryRepository;
import com.experiencers.server.smj.repository.CommentRepository;
import com.experiencers.server.smj.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Board saveBoard(Board inputtedBoard, Long memberId, Long categoryId){
        System.out.println("asd"+inputtedBoard.getCategory());
        Member member = memberRepository.findById(memberId).get();
        Category category = categoryRepository.findById(categoryId).get();
        inputtedBoard.setCategory(category);
        inputtedBoard.setMember(member);

        Board savedBoard = boardRepository.save(inputtedBoard);

        return savedBoard;
    }
    public Board readBoard(Long boardId){return boardRepository.findById(boardId).get();}

    public List<Board> readAllBoard(){return boardRepository.findAll();}

    public void deleteBoard(Long boardId){
        if(boardRepository.getOne(boardId).getComments() != null) {
            List<Comment> comment = boardRepository.findById(boardId).get().getComments();
            for (int i = 0; i < comment.size(); i++) {
                commentRepository.deleteById(comment.get(i).getCommentId());
            }
        }
        boardRepository.deleteById(boardId);
    }

    public Board readAndUpdateBoard(Long boardId, Board board){
/*        Board beforeBoard = boardRepository.findById(boardId).get();
        beforeBoard.setTitle(board.getTitle());
        beforeBoard.setContent(board.getContent());
        beforeBoard.setType(board.getType());
        beforeBoard.setCategory(board.getCategory());*/
        Optional<Board> data = boardRepository.findById(boardId);
        if(!data.isPresent()){
            return null;
        }
        Board updateData = data.get();
        updateData.setTitle(board.getTitle());
        updateData.setContent(board.getContent());
        updateData.setCategory(board.getCategory());
        updateData.setType(board.getType());
        //board.setId(boardId);
        //boardRepository.save(board);

        return updateData;
    }
}
