package com.experiencers.server.smj.api;

import com.experiencers.server.smj.domain.Board;
import com.experiencers.server.smj.dto.BoardDto;
import com.experiencers.server.smj.service.BoardService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "Boards", description = "게시판")
@RestController
@RequestMapping("/api/boards")
public class BoardApiController {
    @Autowired
    private BoardService boardService;

    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    @ApiOperation(value = "모든 게시글 목록",notes = "성공시 모든 게시글을 반환합니다.",response = Board.class)
    @GetMapping("")
    public ResponseEntity<?> getBoards(){
        List<Board> boardList = boardService.readAllBoard();

        return new ResponseEntity<>(boardList,HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    @ApiOperation(value = "사용자 게시글 목록",notes = "성공시 사용자의 모든 게시글을 반환합니다.",response = Board.class)
    @GetMapping("/my")
    public ResponseEntity<?> getMyBoards(){
        List<Board> board = boardService.readMyBoard();

        return new ResponseEntity<>(board,HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(code = 201, message = "작성됨")
    })
    @ApiOperation(value = "게시글 작성",notes = "성공시 게시글을 저장합니다.")
    @PostMapping("")
    public ResponseEntity<?> postBoards(@RequestBody BoardDto boardDto){
        Board savedBoard = boardService.saveBoard(boardDto);


        return new ResponseEntity<>(savedBoard,HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    @ApiImplicitParam(name = "board_id",value = "게시글번호",required = true,paramType = "path")
    @ApiOperation(value = "게시글 수정",notes = "성공시 해당 게시글의 내용을 변경합니다.")
    @PutMapping("/{board_id}")
    public ResponseEntity<?> putBoards(@PathVariable("board_id")Long boardId, @RequestBody BoardDto boardDto){
        Board updatedBoard = boardService.readAndUpdateBoard(boardId,boardDto);

        if(updatedBoard == null){

            return new ResponseEntity<>(boardDto, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedBoard,HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(code = 204, message = "콘텐츠 없음")
    })
    @ApiImplicitParam(name = "board_id",value = "게시글번호",required = true,paramType = "path")
    @ApiOperation(value = "게시글 삭제",notes = "성공시 해당 게시글을 삭제합니다.")
    @DeleteMapping("/{board_id}")
    public ResponseEntity<?> deleteBoards(@PathVariable("board_id")Long boardId){
        boardService.deleteBoard(boardId);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        data.put("board_id",boardId);
        result.put("board", data);

        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }


}