package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.domain.Message;
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


    public Message saveMessage(String email,Message inputtedMessage) {
        Member sendMember = memberRepository.findByEmail(email).get(); //보내는 사람 객체
        //Member receiveMember =memberRepository.findByNickname(inputtedMessage.getReceiver()).get();
        inputtedMessage.setSender(sendMember.getEmail()); //메시지보낸는 사람추가입력 -> 나중에 메시지 불러올때 닉네임으로 찾아오기위해서

        //inputtedMessage.setMember(receiveMember);//메시지 멤버 객체에 보내는 사람 추가 -> 보내는사람 메시지 리스트에 추가됨

        //받는사람의 객체에도 메시지를 추가해주기
        //memberRepository.findByNickname(inputtedMessage.getReceiver()).get().setMessages(inputtedMessage);
        //System.out.println(member.getNickname()+"--"+member.getEmail());
        //memberRepository.findByEmail(email).get().setMessages(inputtedMessage);
        //inputtedMessage.setMember2(receiveMember); //메시지 멤버2 객에에 받는 사람 추가


        Message savedMessage = messageRepository.save(inputtedMessage);
        return savedMessage;

    }

    public Message readMessage(Long message_id) {
        return messageRepository.findById(message_id).get();
    }

    public List<Message> readAllMessage(String email) {
        List<Message> messages = messageRepository.findAllByReceiver(email);
        System.out.println(messages);
        return messages;
    }

    public void deleteMessage(Long message_id){
        messageRepository.deleteById(message_id);
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
