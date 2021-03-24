package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Board;
import com.experiencers.server.smj.domain.Category;
import com.experiencers.server.smj.domain.KakaoProfile;
import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.dto.BoardDto;
import com.experiencers.server.smj.enumerate.BoardType;
import com.experiencers.server.smj.manager.ManageMember;
import com.experiencers.server.smj.repository.BoardRepository;
import com.experiencers.server.smj.repository.CategoryRepository;
import com.experiencers.server.smj.repository.CommentRepository;
import com.experiencers.server.smj.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
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
    public Board saveBoard(BoardDto boardDto){
        Member member = manageMember.getManageMember();

        if(boardDto.getType().toString().equals("TRADE")){
            boardDto.setType(BoardType.TRADE);
        }else {
            boardDto.setType(BoardType.LIVE);
        }


        Optional<Category> categoryOptional = categoryRepository.findById(boardDto.getCategoryId());
        Category category = null;
        if(!categoryOptional.isPresent()){
            category = new Category();
        }else{
            category = categoryOptional.get();
        }

        Board inputtedBoard = Board.builder()
                .writer(member.getNickname())
                .content(boardDto.getContent())
                .title(boardDto.getTitle())
                .member(member)
                .category(category)
                .type(boardDto.getType())
                .build();


        Board savedBoard = boardRepository.save(inputtedBoard);

        return savedBoard;
    }
    public Board readBoard(Long boardId){return boardRepository.findById(boardId).get();}

    public List<Board> readAllBoard(){return boardRepository.findAll();}

    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    public Board readAndUpdateBoard(Long boardId, BoardDto boardDto){

        Optional<Board> data = boardRepository.findById(boardId);
        if(data.isPresent()){
            Board target = data.get();
            target.setTitle(boardDto.getTitle());
            target.setContent(boardDto.getContent());

            if(boardDto.getType().toString().equals("TRADE")){
                target.setType(BoardType.TRADE);
            }else {
                target.setType(BoardType.LIVE);
            }

            Optional<Category> categoryOptional = categoryRepository.findById(boardDto.getCategoryId());
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
