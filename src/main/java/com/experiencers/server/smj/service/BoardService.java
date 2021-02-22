package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Board;
import com.experiencers.server.smj.domain.Comment;
import com.experiencers.server.smj.repository.BoardRepository;
import com.experiencers.server.smj.repository.CommentRepository;
import com.experiencers.server.smj.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MemberRepository memberRepository;

    public Board saveBoard(Map inputtedBoard){
        Board board = new Board();

        BoardType type = BoardType.valueOf(inputtedBoard.get("type").toString());
        board.setType(type);
        String title = String.valueOf(inputtedBoard.get("title").toString());
        board.setTitle(title);
        String content = String.valueOf(inputtedBoard.get("content").toString());
        board.setContent(content);

        Board savedBoard = boardRepository.save(board);

        return savedBoard;
    }
    public Board readBoard(Long boardId){return boardRepository.findById(boardId).get();}

    public List<Board> readAllBoard(){return boardRepository.findAll();}

    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }
    public Board readAndUpdateBoard(Long boardId, Board board){
/*        Board beforeBoard = boardRepository.findById(boardId).get();
        beforeBoard.setTitle(board.getTitle());
        beforeBoard.setContent(board.getContent());
        beforeBoard.setType(board.getType());
        beforeBoard.setCategory(board.getCategory());*/

        Optional<Board> data = boardRepository.findById(boardId);
        if(data.isPresent()){
            Board target = data.get();
            target.setTitle(board.getTitle());
            target.setContent(board.getContent());
            target.setCategory(board.getCategory());
            target.setType(board.getType());

            target = boardRepository.save(target);

            return target;
        }

        return null;
    }
}
