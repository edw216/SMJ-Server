package com.experiencers.server.smj.board;

import com.experiencers.server.smj.board.Board;
import com.experiencers.server.smj.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public Board writeBoard(Board inputtedBoard){
        Board savedBoard = boardRepository.save(inputtedBoard);

        return savedBoard;
    }
    public Board readBoard(Long board_id){return boardRepository.getOne(board_id);}

    public List<Board> readAllBoard(){return boardRepository.findAll();}

    public void removeBoard(Long board_id){
        boardRepository.deleteById(board_id);
    }

    public void updateBoard(Board board){
        Board beforeBoard = boardRepository.findById(board.getBoard_id()).get();
        beforeBoard.setTitle(board.getTitle());
        beforeBoard.setContent(board.getContent());
        beforeBoard.setType(board.getType());

        boardRepository.save(beforeBoard);
    }
}
