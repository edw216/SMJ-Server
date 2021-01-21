package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.User;
import com.experiencers.server.smj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User writeUser(User inputtedUser) {
        User savedUser = userRepository.save(inputtedUser);

        return savedUser;
    }

    public User readUser(Long user_id) {
        return userRepository.getOne(user_id);
    }

    public List<User> readAllUser() {
        return userRepository.findAll();
    }

    public void removeUser(Long user_id){
        userRepository.deleteById(user_id);
    }
    public void updateUser(User user){
       /* User beforeUser = userRepository.findById(user.getId()).get();
        beforeUser.setEmail(user.getEmail());
        beforeUser.setNickname(user.getNickname());
        beforeUser.setImage(user.getImage());

        userRepository.save(beforeUser);*/
        userRepository.save(user);
    }
}
