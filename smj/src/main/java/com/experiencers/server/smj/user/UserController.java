package com.experiencers.server.smj.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping({"", "/", "/user"})
    public ModelAndView getIndex() {
        List<User> userList = userService.readAllUser();

        ModelAndView response = new ModelAndView("user/info");//뷰이름설정
        response.addObject(userList);//뷰로 보낼 데이터 userlist

        return response;
    }

    @PostMapping("/user")
    public String postUser(@ModelAttribute User inputtedUser) {
        User savedUser = userService.writeUser(inputtedUser);

        return "redirect:/user";
    }


    @GetMapping("/user/{id}")
    public ModelAndView getPost(@PathVariable("id") Long userId) {
        User user = userService.readUser(userId);

        ModelAndView response = new ModelAndView("user/detail");
        response.addObject(user);

        return response;
    }
    @PostMapping("/user/{userId}/delete")
    public String deleteUser(@PathVariable("userId") Long userId, HttpServletRequest request){
        userService.removeUser(userId);
        return "redirect:/user";
    }
    @PostMapping("/user/{userId}/edit")
    public ModelAndView editUser(@PathVariable("userId")Long userId){
        User user = userService.readUser(userId);
        ModelAndView response = new ModelAndView("user/edit");
        response.addObject(user);
        return response;
    }
    @PostMapping("/user/{userId}/edit/update")
    public String updateUser(User user, HttpServletRequest request){
        userService.updateUser(user);

        return "redirect:/user";
    }

}
