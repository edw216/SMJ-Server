package com.experiencers.server.smj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @RequestMapping("/admin")
    public ModelAndView getIndex() {

        ModelAndView mav = new ModelAndView("admin/index");

        return mav;
    }
}
