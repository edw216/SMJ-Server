package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Message;
import com.experiencers.server.smj.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message writeMessage(Message inputtedMessage) {
        Message savedMessage = messageRepository.save(inputtedMessage);

        return savedMessage;
    }

    public Message readMessage(Long message_id) {
        return messageRepository.findById(message_id).get();
    }

    public List<Message> readMessage() {
        return messageRepository.findAll();
    }

    public void removeMessage(Long message_id){
        messageRepository.deleteById(message_id);
    }
    public void updateMessage(Message message){
        Message beforeMessage = messageRepository.findById(message.getMessage_id()).get();
        beforeMessage.setContent(message.getContent());
        beforeMessage.setSender(message.getSender());
        beforeMessage.setReceiver(message.getReceiver());
        beforeMessage.setDate(message.getDate());
        messageRepository.save(beforeMessage);
    }
}
