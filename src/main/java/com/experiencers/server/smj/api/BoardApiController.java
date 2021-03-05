package com.experiencers.server.smj.api;

import com.experiencers.server.smj.domain.Board;
import com.experiencers.server.smj.domain.Comment;
import com.experiencers.server.smj.manager.ManageMember;
import com.experiencers.server.smj.service.BoardService;
import com.experiencers.server.smj.service.CommentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "모든 게시글 불러오기",notes = "헤더에 jwt 토큰을 담고 성공시 모든 게시글을 반환합니다.")
    @GetMapping("")
    public List<Board> getBoards(@RequestHeader("Authorization")String token){
        List<Board> boardList = boardService.readAllBoard();

        return boardList;
    }

    @ApiOperation(value = "게시글 작성하기",notes = "헤더에 jwt 토큰을 담고 성공시 게시글을 저장합니다.")
    @PostMapping("")
    public Board postBoard(@RequestHeader("Authorization")String token,@RequestBody Board board){
        Board savedBoard = boardService.saveBoard(board);


        return savedBoard;
    }

    @ApiImplicitParam(name = "board_id",value = "게시글번호",required = true,paramType = "path")
    @ApiOperation(value = "게시글 변경하기",notes = "헤더에 jwt 토큰을 담고 성공시 해당 게시글의 내용을 변경합니다.")
    @PutMapping("/{board_id}")
    public ResponseEntity<Board> putBoard(@RequestHeader("Authorization")String token,@PathVariable("board_id")Long boardId, @RequestBody Board board){
        Board updatedBoard = boardService.readAndUpdateBoard(boardId,board);

        if(updatedBoard == null){
            board.setId(boardId);

            return new ResponseEntity<>(board, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedBoard,HttpStatus.OK);
    }

    @ApiImplicitParam(name = "board_id",value = "게시글번호",required = true,paramType = "path")
    @ApiOperation(value = "게시글 삭제하기",notes = "헤더에 jwt 토큰을 담고 성공시 해당 게시글을 삭제합니다.")
    @DeleteMapping("/{board_id}")
    public ResponseEntity<Object> deleteBoard(@RequestHeader("Authorization")String token,@PathVariable("board_id")Long boardId){
        boardService.deleteBoard(boardId);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        data.put("board_id",boardId);
        result.put("board", data);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "사용자 게시글 불러오기",notes = "헤더에 jwt 토큰을 담고 성공시 해당 유저의 모든 게시글을 반환합니다.")
    @GetMapping("/my")
    public List<Board> getMyBoards(@RequestHeader("Authorization")String token){
        List<Board> board = boardService.readMyBoard();

        return board;
    }
}