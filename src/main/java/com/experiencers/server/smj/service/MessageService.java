package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.domain.Message;
import com.experiencers.server.smj.dto.MessageDto;
import com.experiencers.server.smj.manager.ManageMember;
import com.experiencers.server.smj.repository.MemberRepository;
import com.experiencers.server.smj.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ManageMember manageMember;

    //Api Service
    public Message saveMessage(MessageDto messageDto) {
        Member sendMember = manageMember.getManageMember();

        Message message = Message.builder()
                .content(messageDto.getContent())
                .receiver(messageDto.getReceiver())
                .sender(sendMember.getEmail())
                .build();

        Message savedMessage = messageRepository.save(message);

        return savedMessage;
    }

    public List<Message> readAllMessage() {
        String email = manageMember.getManageMembername();
        List<Message> messages = messageRepository.findAllByReceiver(email);

        return messages;
    }

    //Api Admin Service
    public void deleteMessage(Long message_id){
        messageRepository.deleteById(message_id);
    }

    //Admin Service
    public Message readMessage(Long message_id) {
        return messageRepository.findById(message_id).get();
    }

    public Message readAndUpdateMessage(Long messageId, Message message){

        Optional<Message> data = messageRepository.findById(messageId);

        if(data.isPresent()){
            Message target = data.get();
            target.setContent(message.getContent());
            target.setSender(message.getSender());
            target.setReceiver(message.getReceiver());

            target = messageRepository.save(target);

            return target;
        }
        return null;
    }
}
