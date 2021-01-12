package com.experiencers.server.smj.controller;

import com.experiencers.server.smj.domain.Message;
import com.experiencers.server.smj.service.MessageService;
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

    @PostMapping("/message")
    public String postMessage(@ModelAttribute Message inputtedMessage) {
        Message savedMessage = messageService.writeMessage(inputtedMessage);

        return "redirect:/message/" + savedMessage.getMessage_id();
    }

    @GetMapping("/message/{id}")
    public ModelAndView getPost(@PathVariable("id") Long message_id) {
        Message message = messageService.readMessage(message_id);

        ModelAndView response = new ModelAndView("message/detail");
        response.addObject(message);

        return response;
    }
    @PostMapping("/message/{message_id}/delete")
    public String deleteMessage(@PathVariable("message_id") Long message_id, HttpServletRequest request){
        messageService.removeMessage(message_id);
        return "redirect:"+request.getHeader("referer");
    }
    @PostMapping("/message/{message_id}/edit")
    public ModelAndView editMessage(@PathVariable("message_id")Long message_id){
        Message message = messageService.readMessage(message_id);
        ModelAndView response = new ModelAndView("message/edit");
        response.addObject(message);
        return response;
    }
    @PostMapping("/message/{message_id}/edit/update")
    public String updateMessage(Message message, HttpServletRequest request){
        messageService.updateMessage(message);

        return "redirect:/message/";
    }
}
