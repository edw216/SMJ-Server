package com.experiencers.server.smj.dto;

import com.experiencers.server.smj.domain.Message;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MessageDto {


    @Getter
    @Setter
    public static class MessageDtoRequest{
        @ApiModelProperty(position = 1,notes = "쪽지 내용(최대 255자)")
        private String content;

        @ApiModelProperty(position = 2,notes = "쪽지 받는사람(이메일)",example = "example@example.com")
        private String receiver;

        public Message toEntity(String sender){
            return Message.builder()
                    .content(this.content)
                    .receiver(this.receiver)
                    .sender(sender)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class MessageDtoResponse{

        @ApiModelProperty(position = 1,notes = "쪽지 구분아이디")
        private Long id;

        @ApiModelProperty(position = 2,notes = "쪽지 내용(최대 255자)")
        private String content;

        @ApiModelProperty(position = 3,notes = "쪽지 보내는사람(이메일)",example = "example@example.com")
        private String sender;

        @ApiModelProperty(position = 4,notes = "쪽지 받는사람(이메일)",example = "example@example.com")
        private String receiver;

        @ApiModelProperty(position = 5)
        private LocalDateTime createAt;

        public static MessageDtoResponse of(Message message){
            ModelMapper modelMapper = new ModelMapper();
            MessageDtoResponse dto = modelMapper.map(message,MessageDtoResponse.class);

            return dto;
        }

        public static List<MessageDtoResponse> of(List<Message> messageList){

            return messageList.stream()
                    .map(MessageDtoResponse::of)
                    .collect(Collectors.toList());
        }
    }
}
