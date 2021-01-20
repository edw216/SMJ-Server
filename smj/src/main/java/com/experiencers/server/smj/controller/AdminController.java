package com.experiencers.server.smj.controller;

//import com.experiencers.server.smj.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


//

@Controller
public class AdminController {

    @Autowired
//    private AdminService adminService;
    /*private static final Logger logger = LoggerFactory.getLogger(AdminController.class);*/

    @GetMapping({"/login"})
    public String login(String ID) {

        return "admin/login";
    }
    @GetMapping({"/admin"})
    public String admin() {


        return "admin/admin";
    }



    /*@RequestMapping(value="/admin", method= RequestMethod.POST)
    public String admin(HttpServletRequest request, Model model){

        String login = request.getParameter("admin");

        if(login == null || !login.equals("admin")){
            model.addAttribute("message", "로그인 실패");
            return "admin/admin";
        }
        return "admin/home";
    }*/

    /*@GetMapping({"", "/", "/admin"})
    public String admin(@ModelAttribute HttpServletRequest request, Model model) {
        String login = request.getParameter("admin");

        if(login == null || !login.equals("admin")){
            model.addAttribute("message", "로그인 실패");
            return "admin/admin";
        }
        return "admin/home";
    }*/


}
