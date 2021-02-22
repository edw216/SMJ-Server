package com.experiencers.server.smj.api;


import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
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

    @GetMapping("")
    public List<Member> getMembers(){
        List<Member> memberList = memberService.readAllMember();

        return memberList;
    }

    @PostMapping("")
    public Member postMember(@RequestBody Member member){
        Member savedMember = memberService.saveMember(member);

        return savedMember;
    }

    @PutMapping("/{member_id}")
    public ResponseEntity<Member> putMember(@PathVariable("member_id")Long memberId, @RequestBody Member member){
        Member updatedMember = memberService.readAndUpdateMember(memberId, member);

        if(updatedMember == null){
            member.setId(memberId);

            return new ResponseEntity<>(member,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }
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
