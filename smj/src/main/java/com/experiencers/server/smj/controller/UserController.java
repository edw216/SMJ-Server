package com.experiencers.server.smj.controller;

import com.experiencers.server.smj.domain.User;
import com.experiencers.server.smj.service.UserService;

//import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ModelAndView getIndex() {
        List<User> userList = userService.readAllUser();

        ModelAndView response = new ModelAndView("user/index");//뷰이름설정
        response.addObject(userList);//뷰로 보낼 데이터 userlist

        return response;
    }

    @PostMapping("")
    public String postUser(@RequestParam("profile_image") MultipartFile image, @ModelAttribute User inputtedUser) {
        System.out.println(image);

//        byte[] fileContent = FileUti.readFileToByteArray(new File(filePath))

        User savedUser = userService.writeUser(inputtedUser);

        return "redirect:/user";
    }

    @PostMapping("/{user_id}/delete")
    public String deleteUser(@PathVariable("user_id") Long user_id, HttpServletRequest request){
        userService.removeUser(user_id);
        return "redirect:"+request.getHeader("referer");
    }

    @GetMapping("/{user_id}/edit")
    public ModelAndView editUser(@PathVariable("user_id") Long user_id){
        User user = userService.readUser(user_id);
        ModelAndView response = new ModelAndView("user/edit");
        response.getModel().put("user", user);

        return response;
    }

    @PostMapping("/{user_id}/update")
    public String updateUser(@PathVariable("user_id") Long userId, User user, HttpServletRequest request){
        System.out.println(user);
        user.setId(userId);
        System.out.println(user);
        userService.updateUser(user);

        return "redirect:/user";
    }
}
