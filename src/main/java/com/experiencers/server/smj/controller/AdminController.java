package com.experiencers.server.smj.controller;

//import com.experiencers.server.smj.service.AdminService;
import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


//
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
public class AdminController {
    @Autowired
    private MemberService memberService;

    @RequestMapping({"", "/"})
    public ModelAndView getIndex(Principal principal) {
        String email = principal.getName();

        Member member = memberService.findByEmail(email);
        ModelAndView mav = new ModelAndView("admin/index");
        mav.addObject("member", member);
        return mav;
    }
}
