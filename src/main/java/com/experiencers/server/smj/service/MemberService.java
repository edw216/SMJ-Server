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

    public Member writeMember(Member inputtedMember) {
        Member savedMember = memberRepository.save(inputtedMember);

        return savedMember;
    }

    public Member saveMemberWithConvertImage(MultipartFile image, Member member) throws IOException {
        if (!image.isEmpty()) {
            String stringImage = convertImageToString(image);
            member.setImage(stringImage);
        }

        return memberRepository.save(member);
    }

    public Member readMember(Long user_id) {
        return memberRepository.findById(user_id).get();
    }

    public List<Member> readAllMember() {
        return memberRepository.findAll();
    }

    public void removeMember(Long user_id){
        memberRepository.deleteById(user_id);
    }
    public void updateMember(Member member){
       /* Member beforeMember = userRepository.findById(user.getId()).get();
        beforeMember.setEmail(user.getEmail());
        beforeMember.setNickname(user.getNickname());
        beforeMember.setImage(user.getImage());

        userRepository.save(beforeMember);*/
        memberRepository.save(member);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).get();
    }

    public Member updateMemberWithConvertImage(Long userId, MultipartFile image, Member member) throws IOException {
        member.setId(userId);
        return saveMemberWithConvertImage(image, member);
    }

    private String convertImageToString(MultipartFile image) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("data:image/png;base64,");
        stringBuilder.append(new String(Base64.encodeBase64(image.getBytes()), "UTF-8"));
        return stringBuilder.toString();
    }
}
