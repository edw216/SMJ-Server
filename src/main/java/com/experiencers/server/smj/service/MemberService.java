package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.dto.MemberDto;
import com.experiencers.server.smj.manager.MemberManager;
import com.experiencers.server.smj.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberManager memberManager;

    //Api Service
    public List<MemberDto.MemberDtoResponse> readAllMemberOfApi() {
        List<Member> memberList = memberRepository.findAll();

        return MemberDto.MemberDtoResponse.of(memberList);
    }

    public MemberDto.MemberDtoResponse readAndUpdateMember(MemberDto.MemberDtoRequest memberDto){

        Member member = memberManager.getMember();
        if(!memberDto.getNickname().equals("닉네임")) {
            member.setNickname(memberDto.getNickname());
        }
        if(!memberDto.getImage().equals("image.jpg")) {
            member.setImage(memberDto.getImage());
        }

        member = memberRepository.save(member);
        return MemberDto.MemberDtoResponse.of(member);
    }
    //Api Admin Service
    public void deleteMember(Long memberId){
        memberRepository.deleteById(memberId);
    }

    //Admin Service
    public List<Member> readAllMember() {
        return memberRepository.findAll();
    }
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
