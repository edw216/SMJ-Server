package com.experiencers.server.smj.dto;

import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.domain.Setting;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MemberDto {

    @Getter
    @Setter
    public static class MemberDtoRequest {
        @ApiModelProperty(position = 1,notes = "멤버 닉네임",example = "닉네임")
        private String nickname;

        @ApiModelProperty(position = 2,notes = "멤버 이미지",example = "image.jpg/png")
        private String image;

    }

    @Getter
    @Setter
    public static class MemberDtoResponse {
        @ApiModelProperty(position = 1,notes = "멤버 구분아이디")
        private Long id;

        @ApiModelProperty(position = 2,notes = "멤버 이메일",example = "email@example.com")
        private String email;

        @ApiModelProperty(position = 3,notes = "멤버 닉네임",example = "닉네임")
        private String nickname;

        @ApiModelProperty(position = 4,notes = "멤버 이미지",example = "image.jpg")
        private String image;

        @ApiModelProperty(position = 5)
        private LocalDateTime createAt;

        public static MemberDtoResponse of(Member member){
            ModelMapper modelMapper = new ModelMapper();
            MemberDtoResponse dto = modelMapper.map(member,MemberDtoResponse.class);

            return dto;
        }

        public static List<MemberDtoResponse> of(List<Member> memberList){

            return memberList.stream()
                    .map(MemberDtoResponse::of)
                    .collect(Collectors.toList());
        }
    }

}
