package com.experiencers.server.smj.user;

import com.experiencers.server.smj.message.Message;
import com.experiencers.server.smj.message.MessageRepository;
import com.experiencers.server.smj.user.User;
import com.experiencers.server.smj.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    public User writeUser(User inputtedUser) {
        User savedUser = userRepository.save(inputtedUser);

        return savedUser;
    }

    public User readUser(Long userId) {
        return userRepository.getOne(userId);
    }


    public List<User> readAllUser() {
        return userRepository.findAll();
    }

    public void removeUser(Long userId){

        List<Message> message = userRepository.getOne(userId).getMessages();
        for(int i = 0; i<message.size(); i++){
            messageRepository.deleteById(message.get(i).getMessageId());
        }
        userRepository.deleteById(userId);
    }
    public void updateUser(User user){
        User beforeUser = userRepository.findById(user.getUserId()).get();
        beforeUser.setEmail(user.getEmail());
        beforeUser.setNickname(user.getNickname());
        beforeUser.setImage(user.getImage());

        userRepository.save(beforeUser);
    }
}
