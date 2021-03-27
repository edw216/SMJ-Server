package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.dto.MemberDto;
import com.experiencers.server.smj.manager.ManageMember;
import com.experiencers.server.smj.repository.MemberRepository;
import com.experiencers.server.smj.repository.MessageRepository;
import com.experiencers.server.smj.repository.SettingRepository;
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

    @Autowired
    private ManageMember manageMember;

    //Api Service
    public List<Member> readAllMember() {
        return memberRepository.findAll();
    }
    public Member readAndUpdateMember(Long memberId, MemberDto memberDto){
        Optional<Member> data = memberRepository.findById(memberId);

        if(data.isPresent()){
            Member target = data.get();
            target.setNickname(memberDto.getNickname());
            target.setEmail(memberDto.getEmail());
            target.setImage(memberDto.getImage());

            target = memberRepository.save(target);

            return target;
        }
        return null;
    }
    //Api Admin Service
    public void deleteMember(Long memberId){
        memberRepository.deleteById(memberId);
    }

    //Admin Service
    public Member saveMemberWithConvertImage(String image, Member member)  {

        if (!image.isEmpty()) {
            String stringImage = image;
            member.setImage(stringImage);
        }

        return memberRepository.save(member);
    }


    public Member readMember(Long memberId) {
        return memberRepository.findById(memberId).get();
    }


    public Member updateMemberWithConvertImage(Long userId, String image, Member member)  {
        member.setId(userId);
        return saveMemberWithConvertImage(image, member);
    }
}
