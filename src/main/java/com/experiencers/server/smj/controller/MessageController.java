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
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("")
    public ModelAndView getIndex() {
        List<Message> messageList = messageService.readAllMessage();

        ModelAndView response = new ModelAndView("message/info");//뷰이름설정
        response.addObject(messageList);//뷰로 보낼 데이터 userlist

        return response;
    }

    @PostMapping("/member/{member_id/message")
    public String postMessage(@PathVariable("member_id") Long memberId,
            @ModelAttribute Message inputtedMessage, HttpServletRequest request) {
        messageService.saveMessage(inputtedMessage,memberId);

        return "redirect:" + request.getHeader("referer");
    }

    @GetMapping("/{id}")
    public ModelAndView getPost(@PathVariable("id") Long messageId) {
        Message message = messageService.readMessage(messageId);

        ModelAndView response = new ModelAndView("message/detail");
        response.addObject(message);

        return response;
    }
    @PostMapping("/{message_id}/delete")
    public String deleteMessage(@PathVariable("message_id") Long messageId,
                                @PathVariable("member_id") Long memberId,
                                HttpServletRequest request){
        messageService.deleteMessage(messageId);
        return "redirect:/member/" + memberId;
    }
    @PostMapping("/{message_id}/edit")
    public ModelAndView editMessage(@PathVariable("message_id")Long messageId,
                                    @PathVariable("member_id")Long memberId){
        Message message = messageService.readMessage(messageId);
        ModelAndView response = new ModelAndView("message/edit");
        response.addObject(message);
        return response;
    }
    @PostMapping("/{message_id}/edit/update")
    public String updateMessage(@PathVariable("message_id")Long messageId, Message message, @PathVariable("member_id") Long memberId){
        messageService.readAndUpdateMessage(messageId, message);

        return "redirect:/member/"+memberId;
    }
}
