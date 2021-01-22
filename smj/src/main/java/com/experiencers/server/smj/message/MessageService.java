package com.experiencers.server.smj.message;

import com.experiencers.server.smj.user.User;
import com.experiencers.server.smj.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    public Message writeMessage(Message inputtedMessage, Long userId) {

        User user = userRepository.getOne(userId);
        inputtedMessage.setUser(user);

        Message savedMessage = messageRepository.save(inputtedMessage);

        return savedMessage;
    }

    public Message readMessage(Long messageId) {
        return messageRepository.getOne(messageId);
    }

    public List<Message> readMessage() {
        return messageRepository.findAll();
    }

    public void removeMessage(Long messageId){
        messageRepository.deleteById(messageId);
    }
    public void updateMessage(Message message){
        Message beforeMessage = messageRepository.findById(message.getMessageId()).get();
        beforeMessage.setContent(message.getContent());
        beforeMessage.setSender(message.getSender());
        beforeMessage.setReceiver(message.getReceiver());
        beforeMessage.setDate(message.getDate());
        messageRepository.save(beforeMessage);
    }
}
