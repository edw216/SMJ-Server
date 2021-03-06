package com.experiencers.server.smj.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;





@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
@RequestMapping
public class AdminController {

    @GetMapping("/admin")
    public ModelAndView getIndex() {

        ModelAndView mav = new ModelAndView("admin/index");
        return mav;
    }
}
