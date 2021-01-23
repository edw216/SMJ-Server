package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.repository.MemberRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member writeUser(Member inputtedMember) {
        Member savedMember = memberRepository.save(inputtedMember);

        return savedMember;
    }

    public Member saveUserWithConvertImage(MultipartFile image, Member member) throws IOException {
        if (!image.isEmpty()) {
            String stringImage = convertImageToString(image);
            member.setImage(stringImage);
        }

        return memberRepository.save(member);
    }

    public Member readUser(Long user_id) {
        return memberRepository.getOne(user_id);
    }

    public List<Member> readAllUser() {
        return memberRepository.findAll();
    }

    public void removeUser(Long user_id){
        memberRepository.deleteById(user_id);
    }
    public void updateUser(Member member){
       /* User beforeUser = userRepository.findById(user.getId()).get();
        beforeUser.setEmail(user.getEmail());
        beforeUser.setNickname(user.getNickname());
        beforeUser.setImage(user.getImage());

        userRepository.save(beforeUser);*/
        memberRepository.save(member);
    }

    public Member updateUserWithConvertImage(Long userId, MultipartFile image, Member member) throws IOException {
        member.setId(userId);
        return saveUserWithConvertImage(image, member);
    }

    private String convertImageToString(MultipartFile image) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("data:image/png;base64,");
        stringBuilder.append(new String(Base64.encodeBase64(image.getBytes()), "UTF-8"));
        return stringBuilder.toString();
    }
}
