package com.experiencers.server.smj.controller;

import com.experiencers.server.smj.domain.Comment;
import com.experiencers.server.smj.service.BoardService;
import com.experiencers.server.smj.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    BoardService boardService;

    @GetMapping("/comment")
    public ModelAndView getIndex(){
        List<Comment> commentList = commentService.readAllComment();

        ModelAndView response = new ModelAndView("comment/index");
        response.addObject(commentList);
        response = getBoards(response);

        return response;
    }

    @PostMapping("/comment")
    public String postComment(@ModelAttribute Comment inputtedComment,@RequestParam(value = "board_id")Long boardId,
                              HttpServletRequest request){
        System.out.println(inputtedComment.toString());
        commentService.saveCommentOfAdmin(inputtedComment,boardId);

        return "redirect:"+request.getHeader("referer");
    }

    @PostMapping("/comment/{comment_id}/delete")
    public String deleteComment(@PathVariable("comment_id") Long commentId){
        commentService.deleteComment(commentId);
        return "redirect:/admin/comment";
    }

    @GetMapping("/comment/{comment_id}/edit")
    public ModelAndView editComment(@PathVariable("comment_id") Long commentId){
        Comment comment = commentService.readCommentOfAdmin(commentId);

        ModelAndView response = new ModelAndView("comment/edit");
        response.addObject("comment", comment);
        response = getBoards(response);

        return response;

    }
    @PostMapping("/comment/{comment_id}/update")
    public String updateComment(@PathVariable("comment_id")Long commentId, @ModelAttribute Comment comment){

        commentService.readAndUpdateCommentOfAdmin(commentId, comment);

        return "redirect:/admin/comment";
    }
    private ModelAndView getBoards(ModelAndView mav) {
        return mav.addObject("boards", boardService.readAllBoard());
    }

}