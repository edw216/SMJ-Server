package com.experiencers.server.smj.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping({"", "/", "/message"})
    public ModelAndView getIndex() {
        List<Message> messageList = messageService.readMessage();

        ModelAndView response = new ModelAndView("message/info");//뷰이름설정
        response.addObject(messageList);//뷰로 보낼 데이터 userlist

        return response;
    }

    @PostMapping("/user/{userId}/message")
    public String postMessage(@PathVariable("userId") Long userId,
                              @ModelAttribute Message inputtedMessage, HttpServletRequest request) {
        messageService.writeMessage(inputtedMessage,userId);
        return "redirect:"+request.getHeader("referer");
    }

    @PostMapping("/user/{userId}/message/{messageId}/delete")
    public String deleteMessage(@PathVariable("messageId") Long messageId,
                                @PathVariable("userId") Long userId,
                                HttpServletRequest request){
        messageService.removeMessage(messageId);
        return "redirect:"+ userId;
    }
    @PostMapping("/user/{userId}/message/{messageId}/edit")
    public ModelAndView editMessage(@PathVariable("messageId")Long messageId,
                                    @PathVariable("userId") Long userId){
        Message message = messageService.readMessage(messageId);
        ModelAndView response = new ModelAndView("message/edit");
        response.addObject(message);
        return response;
    }
    @PostMapping("/user/{userId}/message/{messageId}/edit/update")
    public String updateMessage(Message message, @PathVariable("userId") Long userId){
        messageService.updateMessage(message);

        return "redirect:/user/" + userId;
    }
}
