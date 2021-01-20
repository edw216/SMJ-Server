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

    @PostMapping("/user/{user_id}/message")
    public String postMessage(@PathVariable("user_id") Long user_id,
                              @ModelAttribute Message inputtedMessage, HttpServletRequest request) {
        messageService.writeMessage(inputtedMessage,user_id);
        return "redirect:"+request.getHeader("referer");
    }

    @PostMapping("/user/{user_id}/message/{message_id}/delete")
    public String deleteMessage(@PathVariable("message_id") Long message_id,
                                @PathVariable("user_id") Long user_id,
                                HttpServletRequest request){
        messageService.removeMessage(message_id);
        return "redirect:"+ user_id;
    }
    @PostMapping("/user/{user_id}/message/{message_id}/edit")
    public ModelAndView editMessage(@PathVariable("message_id")Long message_id,
                                    @PathVariable("user_id") Long user_id){
        Message message = messageService.readMessage(message_id);
        ModelAndView response = new ModelAndView("message/edit");
        response.addObject(message);
        return response;
    }
    @PostMapping("/user/{user_id}/message/{message_id}/edit/update")
    public String updateMessage(Message message, @PathVariable("user_id") Long user_id){
        messageService.updateMessage(message);

        return "redirect:/user/" + user_id;
    }
}
