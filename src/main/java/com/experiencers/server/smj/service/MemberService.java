package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.domain.Message;
import com.experiencers.server.smj.repository.MemberRepository;
import com.experiencers.server.smj.repository.MessageRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member saveMember(Member inputtedMember) {
        Member savedMember = memberRepository.save(inputtedMember);

        return savedMember;
    }

    public Member saveMemberWithConvertImage(String image, Member member)  {
        System.out.println("==");
        if (!image.isEmpty()) {
            System.out.println("====");
            String stringImage = image;
            member.setImage(stringImage);
        }
        System.out.println("===");

        return memberRepository.save(member);
    }

    public Member readMember(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    public List<Member> readAllMember() {
        return memberRepository.findAll();
    }

    public void deleteMember(Long memberId){
        memberRepository.deleteById(memberId);
    }
    public Member readAndUpdateMember(Long memberId, Member member){
        Optional<Member> data = memberRepository.findById(memberId);

        if(data.isPresent()){
            Member target = data.get();
            target.setNickname(member.getNickname());
            target.setEmail(member.getEmail());

            target = memberRepository.save(target);

            return target;
        }
        return null;
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).get();
    }

    public Member updateMemberWithConvertImage(Long userId, String image, Member member)  {
        member.setId(userId);
        return saveMemberWithConvertImage(image, member);
    }

    //닉네임만 변경 할시 사용하는 서비스메소드
    public Member updateMemberNickname(String email, String nickname){
        Optional<Member> updatedMember = memberRepository.findByEmail(email);
        Member target = new Member();

        if(updatedMember.isPresent()){
            target = updatedMember.get();
            target.setNickname(nickname);

            target = memberRepository.save(target);
            return target;
        }

        return null;
    }
    //이미지만 변경 할 시 사용하는 서비스메소드
    public Member updateMemberImage(String email, String imageUrl){
        Optional<Member> updatedMember = memberRepository.findByEmail(email);
        Member target = new Member();

        if(updatedMember.isPresent()){
            target = updatedMember.get();
            target.setImage(imageUrl);

            target = memberRepository.save(target);
            return target;
        }

        return null;
    }
}
