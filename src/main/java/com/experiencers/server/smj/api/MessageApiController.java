package com.experiencers.server.smj.api;


import com.experiencers.server.smj.domain.Message;
import com.experiencers.server.smj.service.MessageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "사용자가 받은 쪽지 불러오기",notes = "헤더에 jwt 토큰을 담고 성공시 받은 모든 쪽지를 불러옵니다.")
    @GetMapping("")
    public List<Message> getMessages(@RequestHeader("Authorization")String token){
        List<Message> messageList = messageService.readAllMessage();

        return messageList;
    }

    @ApiOperation(value = "쪽지 보내기",notes = "헤더에 jwt 토큰을 담고 성공시 쪽지가 작성됩니다.")
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

    @ApiImplicitParam(name = "message_id",value = "쪽지번호",required = true,paramType = "path")
    @ApiOperation(value = "쪽지 삭제하기",notes = "헤더에 jwt 토큰을 담고 성공시 쪽지아이디를 통해 해당 쪽지를 삭제됩니다.")
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
