package com.experiencers.server.smj.board;

import com.experiencers.server.smj.board.Board;
import com.experiencers.server.smj.board.BoardRepository;
import com.experiencers.server.smj.comment.Comment;
import com.experiencers.server.smj.comment.CommentRepository;
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

    public Board saveBoard(Board inputtedBoard){
        Board savedBoard = boardRepository.save(inputtedBoard);

        return savedBoard;
    }
    public Board readBoard(Long boardId){
        return boardRepository.getOne(boardId);
    }

    public List<Board> readAllBoard(){
        return boardRepository.findAll();
    }

    public void deleteBoard(Long boardId){

        List<Comment> comment = boardRepository.getOne(boardId).getComments();
        for(int i = 0; i<comment.size();i++){
            commentRepository.deleteById(comment.get(i).getComment_id());
        }
        boardRepository.deleteById(boardId);
    }

    public Board readAndUpdateBoard(Long boardId, Board board){
        Optional<Board> data = boardRepository.findById(boardId);

        if(data.isPresent()){
            Board target = data.get();
            target.setTitle(board.getTitle());
            target.setContent(board.getContent());

            target = boardRepository.save(target);

            return target;
        }
        return null;
    }
}
