package com.experiencers.server.smj.api;

import com.experiencers.server.smj.domain.Board;
import com.experiencers.server.smj.domain.Comment;
import com.experiencers.server.smj.manager.ManageMember;
import com.experiencers.server.smj.service.BoardService;
import com.experiencers.server.smj.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/boards")
public class BoardApiController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @GetMapping("")
    public List<Board> getBoards(@RequestHeader("Authorization")String token){
        List<Board> boardList = boardService.readAllBoard();

        return boardList;
    }

    @PostMapping("")
    public Board postBoard(@RequestHeader("Authorization")String token,@RequestBody Board board){
        Board savedBoard = boardService.saveBoard(board);


        return savedBoard;
    }

    @PutMapping("/{board_id}")
    public ResponseEntity<Board> putBoard(@RequestHeader("Authorization")String token,@PathVariable("board_id")Long boardId, @RequestBody Board board){
        Board updatedBoard = boardService.readAndUpdateBoard(boardId,board);

        if(updatedBoard == null){
            board.setId(boardId);

            return new ResponseEntity<>(board, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedBoard,HttpStatus.OK);
    }

    @DeleteMapping("/{board_id}")
    public ResponseEntity<Object> deleteBoard(@RequestHeader("Authorization")String token,@PathVariable("board_id")Long boardId){
        boardService.deleteBoard(boardId);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        data.put("board_id",boardId);
        result.put("board", data);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/my")
    public List<Board> getMyBoards(@RequestHeader("Authorization")String token){
        List<Board> board = boardService.readMyBoard();

        return board;
    }

    @PostMapping("/{board_id}/comments")
    public Comment postComment(@RequestHeader("Authorization")String token,@PathVariable("board_id")Long boardId, @RequestBody Comment comment){
        Comment savedComment = commentService.saveComment(comment,boardId);

        return savedComment;
    }
}