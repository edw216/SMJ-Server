package com.experiencers.server.smj.controller;

//import com.experiencers.server.smj.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


//
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
public class AdminController {
    @RequestMapping({"", "/"})
    public ModelAndView getIndex() {
        return new ModelAndView("admin/index");
    }
}
