package com.experiencers.server.smj.api;


import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.service.MemberService;


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

    @GetMapping("")
    public List<Member> getMembers(@RequestHeader("Authorization")String token){
        List<Member> memberList = memberService.readAllMember();

        return memberList;
    }

    @PostMapping("")
    public Member postMember(@RequestBody Member member){
        Member savedMember = memberService.saveMember(member);

        return savedMember;
    }
    @PutMapping("/nickname")
    public ResponseEntity<Member> putMemberNickname(@RequestHeader("Authorization")String token,@RequestBody Map<String,String> nickname){
        Member updatedMemberNickname = memberService.updateMemberNickname(nickname.get("nickname"));

        if(updatedMemberNickname == null){
            return new ResponseEntity<>(updatedMemberNickname,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedMemberNickname,HttpStatus.OK);
    }
    @PutMapping("/image")
    public ResponseEntity<Member> putMemberImage(@RequestHeader("Authorization")String token,@RequestBody Map<String,String> imageUrl){
        Member updatedMemberImage = memberService.updateMemberImage(imageUrl.get("image"));

        if(updatedMemberImage ==null){
            return new ResponseEntity<>(updatedMemberImage,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedMemberImage,HttpStatus.OK);
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
