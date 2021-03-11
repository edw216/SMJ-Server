package com.experiencers.server.smj.api;


import com.experiencers.server.smj.domain.Message;
import com.experiencers.server.smj.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/messages")
public class MessageApiController {

    @Autowired
    private MessageService messageService;

    @GetMapping("")
    public List<Message> getMessages(@RequestHeader("Authorization")String token){
        List<Message> messageList = messageService.readAllMessage();
        return messageList;
    }

    @PostMapping("")
    public Message postMessage(@RequestHeader("Authorization")String token, @RequestBody Message message){
        Message savedMessage = messageService.saveMessage(message);


        return savedMessage;
    }

    @PutMapping("/{message_id}")
    public ResponseEntity<Message> putMessage(@RequestHeader("Authorization")String token,@PathVariable("message_id")Long messageId, @RequestBody Message message){
        Message updatedMessage = messageService.readAndUpdateMessage(messageId, message);

        if(updatedMessage == null){
            message.setMessageId(messageId);

            return new ResponseEntity<>(updatedMessage, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(updatedMessage, HttpStatus.OK);
    }
    @DeleteMapping("/{message_id}")
    public ResponseEntity<Object> deleteMessage(@RequestHeader("Authorization")String token,@PathVariable("message_id")Long messageId){
        messageService.deleteMessage(messageId);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        data.put("message_id", messageId);
        result.put("message", data);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
