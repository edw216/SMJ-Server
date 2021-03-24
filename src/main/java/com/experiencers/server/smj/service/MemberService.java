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

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ManageMember manageMember;

    public Member saveMember(Member inputtedMember) {
        //inputtedMember.setSetting();
        Member savedMember = memberRepository.save(inputtedMember);

        return savedMember;
    }

    public Member saveMemberWithConvertImage(MultipartFile image, Member member) throws IOException  {
        if(!image.isEmpty()){
            String stringImage = convertImageToString(image);
            member.setImage(stringImage);
        }


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

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).get();
    }

    public Member updateMemberWithConvertImage(Long userId, String image, Member member)  {
        member.setId(userId);
        return saveMemberWithConvertImage(image, member);
    }

    //닉네임만 변경 할시 사용하는 서비스메소드
    public Member updateMemberNickname(String nickname){
        Member updatedMember = manageMember.getManageMember();

        updatedMember.setNickname(nickname);
        updatedMember = memberRepository.save(updatedMember);

        return updatedMember;
    }

    //이미지만 변경 할 시 사용하는 서비스메소드
    public Member updateMemberImage(String imageUrl){
        Member updatedMember = manageMember.getManageMember();

        updatedMember.setImage(imageUrl);
        updatedMember = memberRepository.save(updatedMember);

        return updatedMember;
    }
}
