package com.experiencers.server.smj.controller;

import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("")
    public ModelAndView getIndex() {
        List<Member> memberList = memberService.readAllMember();

        ModelAndView response = new ModelAndView("member/index");//뷰이름설정
        response.addObject("members", memberList);//뷰로 보낼 데이터 Memberlist

        return response;
    }

    @PostMapping("")
    public String postMember(@RequestParam("profile_image") String image, @ModelAttribute Member inputtedMember)  {
        memberService.saveMemberWithConvertImage(image, inputtedMember);

        return "redirect:/member";
    }

    @PostMapping("/{member_id}/delete")
    public String deleteMember(@PathVariable("member_id") Long memberId, HttpServletRequest request){
        memberService.deleteMember(memberId);
        return "redirect:"+request.getHeader("referer");
    }

    @GetMapping("/{member_id}/edit")
    public ModelAndView editMember(@PathVariable("member_id") Long memberId){
        Member member = memberService.readMember(memberId);
        ModelAndView response = new ModelAndView("member/edit");
        response.getModel().put("member", member);

        return response;
    }

    @PostMapping("/{member_id}/update")
    public String updateMember(@PathVariable("member_id") Long memberId,
                             @RequestParam("profile_image") String image,
                             @ModelAttribute Member member)  {

        memberService.updateMemberWithConvertImage(memberId, image, member);

        return "redirect:/member";
    }
}
