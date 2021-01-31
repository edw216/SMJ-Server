package com.experiencers.server.smj.api;

import com.experiencers.server.smj.domain.Board;
import com.experiencers.server.smj.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/boards")
public class BoardApiController {
    @Autowired
    private BoardService boardService;

    @GetMapping("")
    public List<Board> getBoards(){
        List<Board> boardList = boardService.readAllBoard();

        return boardList;
    }

    @PostMapping("")
    public Board postBoard(@RequestBody Board board,@RequestParam("member_id") Long memberId){
        Board savedBoard = boardService.saveBoard(board,memberId,board.getCategory().getId());


        return savedBoard;
    }

    @PutMapping("/{board_id}")
    public ResponseEntity<Board> putBoard(@PathVariable("board_id")Long boardId, @RequestBody Board board){
        Board updatedBoard = boardService.readAndUpdateBoard(boardId,board);

        if(updatedBoard == null){
            board.setId(boardId);

            return new ResponseEntity<>(board, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedBoard,HttpStatus.OK);
    }

    @DeleteMapping("/{board_id}")
    public ResponseEntity<Object> deleteBoard(@PathVariable("board_id")Long boardId){
        boardService.deleteBoard(boardId);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        data.put("board_id",boardId);
        result.put("board", data);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}