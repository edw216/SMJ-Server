package com.experiencers.server.smj.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    /*private static final Logger logger = LoggerFactory.getLogger(AdminController.class);*/


    /*@GetMapping({"/login"})
    public String Log(HttpSession session, Model model) throws Exception{

        String ID = (String) session.getAttribute("ID");
        if(ID == null || !(ID.equals("admin"))){
            return "redirect:/admin";
        }

        return "admin/login";
    }*/

    @GetMapping({"/login"})
    public String login() {
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



}
