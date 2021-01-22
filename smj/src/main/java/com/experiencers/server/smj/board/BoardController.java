package com.experiencers.server.smj.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;
    
    @GetMapping({"","/","/board"})
    public ModelAndView getIndex(){
        List<Board> boardList = boardService.readAllBoard();

        ModelAndView responce = new ModelAndView("board/index");
        responce.addObject(boardList);

        return responce;
    }
    @PostMapping("/board")
    public String postBoard(@ModelAttribute Board inputtedBoard,
                            HttpServletRequest request){
        Board savedBoard = boardService.writeBoard(inputtedBoard);

        return "redirect:/board/"+savedBoard.getBoard_id();
    }
    @GetMapping("/board/{id}")
    public ModelAndView getPost(@PathVariable("id") Long board_id){
        Board board = boardService.readBoard(board_id);

        //System.out.println(board.getComments().toString());
        ModelAndView response = new ModelAndView("board/post");
        response.addObject(board);

        return response;
    }
    @PostMapping("/board/{board_id}/delete")
    public String deleteBoard(@PathVariable("board_id") Long board_id,
                              HttpServletRequest request){
        boardService.removeBoard(board_id);

        return "redirect:"+request.getHeader("referer");
    }
    @PostMapping("/board/{board_id}/edit")
    public ModelAndView editBoard(@PathVariable("board_id") Long board_id){
        Board board = boardService.readBoard(board_id);

        ModelAndView response = new ModelAndView("board/edit");
        response.addObject(board);

        return response;
    }

    @PostMapping("/board/{board_id}/edit/update")
    public String updateBoard(Board board,
                              HttpServletRequest request){
        boardService.updateBoard(board);

        return "redirect:/";
    }
}
