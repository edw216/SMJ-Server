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
        Board savedBoard = boardService.saveBoard(inputtedBoard);

        return "redirect:/board/"+savedBoard.getBoardId();
    }
    @GetMapping("/board/{board_id}")
    public ModelAndView getPost(@PathVariable("board_id") Long boardId){
        Board board = boardService.readBoard(boardId);

        //System.out.println(board.getComments().toString());
        ModelAndView response = new ModelAndView("board/post");
        response.addObject(board);

        return response;
    }
    @PostMapping("/board/{board_id}/delete")
    public String deleteBoard(@PathVariable("board_id") Long boardId,
                              HttpServletRequest request){
        boardService.deleteBoard(boardId);

        return "redirect:/board";
    }
    @PostMapping("/board/{board_id}/edit")
    public ModelAndView editBoard(@PathVariable("board_id") Long boardId){
        Board board = boardService.readBoard(boardId);

        ModelAndView response = new ModelAndView("board/edit");
        response.addObject(board);

        return response;
    }

    @PostMapping("/board/{board_id}/edit/update")
    public String updateBoard(@PathVariable("board_id")Long boardId,
                                Board board,
                              HttpServletRequest request){
        boardService.readAndUpdateBoard(boardId,board);

        return "redirect:/board";
    }
}
