package com.experiencers.server.smj.api;


import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.service.MemberService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "Member", description = "사용자")
@RestController
@RequestMapping("/api/member")
public class MemberApiController {

    @Autowired
    private MemberService memberService;

    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    @ApiOperation(value = "사용자 상세",notes = "성공시 사용자의 정보를 반환합니다.")
    @GetMapping("")
    public ResponseEntity<?> getMember(){
        List<Member> memberList = memberService.readAllMember();

        return new ResponseEntity<>(memberList,HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    @ApiImplicitParam(name = "member_id",value = "유저번호",required = true,paramType = "path")
    @ApiOperation(value = "사용자 수정",notes = "성공시 사용자의 정보를 변경합니다.")
    @PutMapping("/{member_id}")
    public ResponseEntity<?> putMember(@PathVariable("member_id")Long memberId, @RequestBody Member member){
        Member updatedMember = memberService.readAndUpdateMember(memberId, member);

        if(updatedMember == null){
            member.setId(memberId);

            return new ResponseEntity<>(member,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(code = 204, message = "콘텐츠 없음")
    })
    @ApiImplicitParam(name = "member_id",value = "유저번호",required = true,paramType = "path")
    @ApiOperation(value = "사용자 삭제",notes = "성공시 사용자를 삭제합니다.")
    @DeleteMapping("/{member_id}")
    public ResponseEntity<?> deleteMember(@PathVariable("member_id")Long memberId){
        memberService.deleteMember(memberId);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        data.put("member_id", memberId);
        result.put("member", data);

        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);

    }

}
