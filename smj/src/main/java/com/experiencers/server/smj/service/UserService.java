package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.User;
import com.experiencers.server.smj.repository.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User writeUser(User inputtedUser) {
        User savedUser = userRepository.save(inputtedUser);

        return savedUser;
    }

    public User saveUserWithConvertImage(MultipartFile image, User user) throws IOException {
        if (!image.isEmpty()) {
            String stringImage = convertImageToString(image);
            user.setImage(stringImage);
        }

        return userRepository.save(user);
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

    public User updateUserWithConvertImage(Long userId, MultipartFile image, User user) throws IOException {
        user.setId(userId);
        return saveUserWithConvertImage(image, user);
    }

    private String convertImageToString(MultipartFile image) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("data:image/png;base64,");
        stringBuilder.append(new String(Base64.encodeBase64(image.getBytes()), "UTF-8"));
        return stringBuilder.toString();
    }
}
