package com.experiencers.server.smj.api;

import com.experiencers.server.smj.domain.Comment;
import com.experiencers.server.smj.service.CommentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "Comments", description = "댓글")
@RestController
@RequestMapping("/api/boards")
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    @ApiImplicitParam(name = "board_id",value = "게시글번호",required = true,paramType = "path")
    @ApiOperation(value = "댓글 목록",notes = "성공시 해당 게시글의 모든 댓글을 반환합니다.")
    @GetMapping("/{board_id}/comments")
    public ResponseEntity<?> getComments(@PathVariable("board_id")Long boardId){
        List<Comment> comments = commentService.readComment(boardId);

        return new ResponseEntity<>(comments,HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(code = 201, message = "작성됨")
    })
    @ApiImplicitParam(name = "board_id",value = "게시글번호",required = true,paramType = "path")
    @ApiOperation(value = "댓글 작성",notes = "성공시 해당 게시글에 댓글을 저장합니다.")
    @PostMapping("/{board_id}/comments")
    public ResponseEntity<?> postComments(@PathVariable("board_id")Long boardId,@RequestBody Comment comment){
        Comment savedComment = commentService.saveComment(comment,boardId);

        return new ResponseEntity<>(savedComment,HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    @ApiImplicitParam(name = "comment_id",value = "댓글번호",required = true,paramType = "path")
    @ApiOperation(value = "댓글 수정",notes = "성공시 해당 댓글내용을 변경합니다.")
    @PutMapping("/comments/{comment_id}")
    public ResponseEntity<Comment> putComments(@PathVariable("comment_id")Long commentId, @RequestBody Comment comment){
        Comment updatedComment = commentService.readAndUpdateComment(commentId,comment);

        if(updatedComment == null){
            comment.setId(commentId);

            return new ResponseEntity<>(updatedComment, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedComment,HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(code = 204, message = "콘텐츠 없음")
    })
    @ApiImplicitParam(name = "comment_id",value = "댓글번호",required = true,paramType = "path")
    @ApiOperation(value = "댓글 삭제",notes = "성공시 해당 댓글을 삭제합니다.")
    @DeleteMapping("/comments/{comment_id}")
    public ResponseEntity<Object> deleteComments(@PathVariable("comment_id")Long commentId){
        commentService.deleteComment(commentId);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        data.put("comment_id",commentId);
        result.put("comment",data);

        return new ResponseEntity<>(result,HttpStatus.NO_CONTENT);
    }

}
