package com.experiencers.server.smj.controller;

import com.experiencers.server.smj.domain.Comment;
import com.experiencers.server.smj.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("")
    public ModelAndView getIndex(){
        List<Comment> commentList = commentService.readAllComment();

        ModelAndView response = new ModelAndView("comment/index");
        response.addObject(commentList);

        return response;
    }

    @PostMapping("/board/{board_id}/comment")
    public String postComment(@PathVariable("board_id") Long board_id,
            @ModelAttribute Comment inputtedComment,
                              HttpServletRequest request){
        System.out.println(inputtedComment.toString());
        commentService.writeComment(inputtedComment,board_id);

        return "redirect:"+request.getHeader("referer");
    }

    @PostMapping("/board/{board_id}/comment/{comment_id}/delete")
    public String deleteComment(@PathVariable("comment_id") Long comment_id,
                                @PathVariable("board_id")Long board_id,
                                HttpServletRequest request){
        commentService.removeComment(comment_id);
        return "redirect:/board/"+board_id;
    }
    //@PostMapping
    @PostMapping("/board/{board_id}/comment/{comment_id}/edit")
    public ModelAndView editComment(@PathVariable("comment_id") Long comment_id,@PathVariable("board_id")Long board_id){
        Comment comment = commentService.readComment(comment_id);

        ModelAndView response = new ModelAndView("comment/edit");
        response.addObject(comment);

        return response;

    }
    @PostMapping("/board/{board_id}/comment/{comment_id}/edit/update")
    public String updateComment(Comment comment,@PathVariable("board_id") Long board_id){

        commentService.updateComment(comment);

        return "redirect:/board/"+board_id;
    }

}
