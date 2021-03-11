package com.experiencers.server.smj.api;

import com.experiencers.server.smj.domain.Comment;
import com.experiencers.server.smj.service.CommentService;
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

    @GetMapping("")
    public List<Comment> getComments(@RequestHeader("Authorization")String token){
        List<Comment> commentList = commentService.readAllComment();

        return commentList;
    }
    @GetMapping("/{board_id}")
    public List<Comment> getBoardComments(@RequestHeader("Authorization")String token,@PathVariable("board_id")Long boardId){
        List<Comment> comments = commentService.readComment(boardId);

        return comments;
    }

    @PostMapping("/{board_id}")
    public Comment postComment(@RequestHeader("Authorization")String token,@RequestBody Comment comment,@PathVariable("board_id")Long boardId){
        Comment savedComment = commentService.saveComment(comment,boardId);

        return savedComment;
    }

    @PutMapping("/{comment_id}")
    public ResponseEntity<Comment> putComment(@PathVariable("comment_id")Long commentId, @RequestBody Comment comment){
        Comment updatedComment = commentService.readAndUpdateComment(commentId,comment);

        if(updatedComment == null){
            comment.setCommentId(commentId);

            return new ResponseEntity<>(updatedComment, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedComment,HttpStatus.OK);
    }

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
