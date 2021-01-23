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
@RequestMapping("/user")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("")
    public ModelAndView getIndex() {
        List<Member> memberList = memberService.readAllUser();

        ModelAndView response = new ModelAndView("user/index");//뷰이름설정
        response.addObject("users", memberList);//뷰로 보낼 데이터 userlist

        return response;
    }

    @PostMapping("")
    public String postUser(@RequestParam("profile_image") MultipartFile image, @ModelAttribute Member inputtedMember) throws IOException {
        memberService.saveUserWithConvertImage(image, inputtedMember);

        return "redirect:/user";
    }

    @PostMapping("/{user_id}/delete")
    public String deleteUser(@PathVariable("user_id") Long user_id, HttpServletRequest request){
        memberService.removeUser(user_id);
        return "redirect:"+request.getHeader("referer");
    }

    @GetMapping("/{user_id}/edit")
    public ModelAndView editUser(@PathVariable("user_id") Long user_id){
        Member member = memberService.readUser(user_id);
        ModelAndView response = new ModelAndView("user/edit");
        response.getModel().put("user", member);

        return response;
    }

    @PostMapping("/{user_id}/update")
    public String updateUser(@PathVariable("user_id") Long userId,
                             @RequestParam("profile_image") MultipartFile image,
                             @ModelAttribute Member member) throws IOException {

        memberService.updateUserWithConvertImage(userId, image, member);

        return "redirect:/user";
    }
}
