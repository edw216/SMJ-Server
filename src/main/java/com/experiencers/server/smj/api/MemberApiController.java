package com.experiencers.server.smj.api;


import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.service.MemberService;
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
@RequestMapping("/api/members")
public class MemberApiController {

    @Autowired
    private MemberService memberService;


    @ApiOperation(value = "모든 사용자 불러오기",notes = "헤더에 jwt 토큰을 담고 성공시 모든 사용자를 반환합니다.")
    @GetMapping("")
    public List<Member> getMembers(@RequestHeader("Authorization")String token){
        List<Member> memberList = memberService.readAllMember();

        return memberList;
    }

    @ApiOperation(value = "사용자 저장하기",notes = "헤더에 jwt 토큰을 담고 성공시 해당 사용를 저장합니다.")
    @PostMapping("")
    public Member postMember(@RequestBody Member member){
        Member savedMember = memberService.saveMember(member);

        return savedMember;
    }

    @ApiOperation(value = "닉네임 변경하기",notes = "헤더에 jwt 토큰을 담고 성공시 자신의 닉네임을 변경합니다.")
    @PutMapping("/nickname")
    public ResponseEntity<Member> putMemberNickname(@RequestHeader("Authorization")String token,@RequestBody Map<String,String> nickname){
        Member updatedMemberNickname = memberService.updateMemberNickname(nickname.get("nickname"));

        if(updatedMemberNickname == null){
            return new ResponseEntity<>(updatedMemberNickname,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedMemberNickname,HttpStatus.OK);
    }

    @ApiOperation(value = "이미지 변경하기",notes = "헤더에 jwt 토큰을 담고 성공시 자신의 이미지를 변경합니다.")
    @PutMapping("/image")
    public ResponseEntity<Member> putMemberImage(@RequestHeader("Authorization")String token,@RequestBody Map<String,String> imageUrl){
        Member updatedMemberImage = memberService.updateMemberImage(imageUrl.get("image"));

        if(updatedMemberImage ==null){
            return new ResponseEntity<>(updatedMemberImage,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedMemberImage,HttpStatus.OK);
    }

    @ApiImplicitParam(name = "member_id",value = "유저번호",required = true,paramType = "path")
    @ApiOperation(value = "사용자 변경하기",notes = "헤더에 jwt 토큰을 담고 성공시 해당 사용자를 변경합니다.")
    @PutMapping("/{member_id}")
    public ResponseEntity<Member> putMember(@PathVariable("member_id")Long memberId, @RequestBody Member member){
        Member updatedMember = memberService.readAndUpdateMember(memberId, member);

        if(updatedMember == null){
            member.setId(memberId);

            return new ResponseEntity<>(member,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    @ApiImplicitParam(name = "member_id",value = "유저번호",required = true,paramType = "path")
    @ApiOperation(value = "사용자 삭제하기",notes = "헤더에 jwt 토큰을 담고 성공시 해당 유저를 삭제합니다.")
    @DeleteMapping("/{member_id}")
    public ResponseEntity<Object> deleteMember(@PathVariable("member_id")Long memberId){
        memberService.deleteMember(memberId);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        data.put("member_id", memberId);
        result.put("member", data);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

}
