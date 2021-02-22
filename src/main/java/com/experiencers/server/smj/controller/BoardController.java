package com.experiencers.server.smj.controller;

import com.experiencers.server.smj.domain.Board;
import com.experiencers.server.smj.domain.Category;
import com.experiencers.server.smj.enumerate.BoardType;
import com.experiencers.server.smj.service.BoardService;
import com.experiencers.server.smj.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ModelAndView getBoards(){
        ModelAndView response = new ModelAndView("board/index");

        List<Board> boardList = boardService.readAllBoard();
        response.addObject("boards", boardList);
        response.addObject("types", BoardType.values());

        response = getCategories(response);

        return response;
    }

    @PostMapping("")
    public String postBoard(Map inputtedBoard){
        boardService.saveBoard(inputtedBoard);

        return "redirect:/board";
    }

    @GetMapping("/{board_id}/edit")
    public ModelAndView editBoard(@PathVariable("board_id") Long boardId){
        Board board = boardService.readBoard(boardId);

        ModelAndView response = new ModelAndView("board/edit");
        response.addObject("board", board);
        response.addObject("types", BoardType.values());
        response = getCategories(response);

        return response;
    }

    @PostMapping("/{board_id}/update")
    public String updateBoard(@PathVariable("board_id") Long boardId, @ModelAttribute Board board){
        boardService.readAndUpdateBoard(boardId, board);

        return "redirect:/board";
    }

    @PostMapping("/{board_id}/delete")
    public String deleteBoard(@PathVariable("board_id") Long boardId){
        boardService.deleteBoard(boardId);

        return "redirect:/board";
    }

    private ModelAndView getCategories(ModelAndView mav) {
        return mav.addObject("categories", categoryService.readAllCategory());
    }
}