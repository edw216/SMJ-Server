package com.experiencers.server.smj.api;

import com.experiencers.server.smj.domain.Comment;
import com.experiencers.server.smj.service.CommentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "모든댓글 불러오기",notes = "헤더에 jwt 토큰을 담고 성공시 모든 댓글을 반환합니다.")
    @GetMapping("")
    public List<Comment> getComments(@RequestHeader("Authorization")String token){
        List<Comment> commentList = commentService.readAllComment();

        return commentList;
    }

    @ApiImplicitParam(name = "board_id",value = "게시글번호",required = true,paramType = "path")
    @ApiOperation(value = "게시글댓글 불러오기",notes = "헤더에 jwt 토큰을 담고 성공시 해당 게시글의 모든 댓글을 반환합니다.")
    @GetMapping("/{board_id}")
    public List<Comment> getBoardComments(@RequestHeader("Authorization")String token,@PathVariable("board_id")Long boardId){
        List<Comment> comments = commentService.readComment(boardId);

        return comments;
    }

    @ApiImplicitParam(name = "board_id",value = "게시글번호",required = true,paramType = "path")
    @ApiOperation(value = "댓글 작성하기",notes = "헤더에 jwt 토큰을 담고 성공시 해당 게시글에 댓글을 저장합니다.")
    @PostMapping("/{board_id}")
    public Comment postComment(@RequestHeader("Authorization")String token,@RequestBody Comment comment,@PathVariable("board_id")Long boardId){
        Comment savedComment = commentService.saveComment(comment,boardId);

        return savedComment;
    }

    @ApiImplicitParam(name = "comment_id",value = "댓글번호",required = true,paramType = "path")
    @ApiOperation(value = "댓글 수정하기",notes = "헤더에 jwt 토큰을 담고 성공시 해당 댓글내용을 변경합니다.")
    @PutMapping("/{comment_id}")
    public ResponseEntity<Comment> putComment(@PathVariable("comment_id")Long commentId, @RequestBody Comment comment){
        Comment updatedComment = commentService.readAndUpdateComment(commentId,comment);

        if(updatedComment == null){
            comment.setCommentId(commentId);

            return new ResponseEntity<>(updatedComment, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedComment,HttpStatus.OK);
    }

    @ApiImplicitParam(name = "comment_id",value = "댓글번호",required = true,paramType = "path")
    @ApiOperation(value = "댓글 삭제하기",notes = "헤더에 jwt 토큰을 담고 성공시 해당 댓글을 삭제합니다.")
    @DeleteMapping("/{comment_id}")
    public ResponseEntity<Object> deleteComment(@PathVariable("comment_id")Long commentId){
        commentService.deleteComment(commentId);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        data.put("comment_id",commentId);
        result.put("comment",data);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
