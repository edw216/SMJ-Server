package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Board;
import com.experiencers.server.smj.domain.Category;
import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.enumerate.BoardType;
import com.experiencers.server.smj.manager.ManageMember;
import com.experiencers.server.smj.repository.BoardRepository;
import com.experiencers.server.smj.repository.CategoryRepository;
import com.experiencers.server.smj.repository.CommentRepository;
import com.experiencers.server.smj.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ManageMember manageMember;
    @Autowired
    private CategoryRepository categoryRepository;

    //타입, 제목, 내용, 카테고리
    public Board saveBoard(Board inputtedBoard){
        Member member = manageMember.getManageMember();

        inputtedBoard.setMember(member);
        if(inputtedBoard.getType().toString().equals("TRADE")){
            inputtedBoard.setType(BoardType.TRADE);
        }else {
            inputtedBoard.setType(BoardType.LIVE);
        }
        Optional<Category> categoryOptional = categoryRepository.findByName(inputtedBoard.getCategory().getName());
        if(!categoryOptional.isPresent()){
            inputtedBoard.setCategory(new Category());
        }else{
            inputtedBoard.setCategory(categoryOptional.get());
        }


        Board savedBoard = boardRepository.save(inputtedBoard);

        return savedBoard;
    }
    public Board readBoard(Long boardId){return boardRepository.findById(boardId).get();}

    public List<Board> readAllBoard(){return boardRepository.findAll();}

    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    public Board readAndUpdateBoard(Long boardId, Board board){

        Optional<Board> data = boardRepository.findById(boardId);
        if(data.isPresent()){
            Board target = data.get();
            target.setTitle(board.getTitle());
            target.setContent(board.getContent());

            if(board.getType().toString().equals("TRADE")){
                target.setType(BoardType.TRADE);
            }else {
                target.setType(BoardType.LIVE);
            }

            Optional<Category> categoryOptional = categoryRepository.findByName(board.getCategory().getName());
            if(!categoryOptional.isPresent()){
                target.setCategory(new Category());
            }else{
                target.setCategory(categoryOptional.get());
            }

            target = boardRepository.save(target);

            return target;
        }

        return null;
    }
    public List<Board> readMyBoard(){
        Member member = manageMember.getManageMember();
        List<Board> boards = boardRepository.findAllByMember_EmailEquals(member.getEmail());
        //List<Board> boards2 = boardRepository.findAllByMemberEmail(member.getEmail());

        System.out.println("my error check");
        System.out.println(member);
        System.out.println(boards);

        return boards;
    }
}
