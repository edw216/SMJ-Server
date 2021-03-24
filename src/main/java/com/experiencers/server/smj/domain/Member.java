package com.experiencers.server.smj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    @ApiModelProperty(position = 1,notes = "멤버 아이디")
    private Long id;

    @ApiModelProperty(position = 2,notes = "멤버 이메일",example = "email@example.com")
    @Column(nullable = false, length = 50)
    private String email;


    @ApiModelProperty(position = 3,notes = "멤버 닉네임",example = "사용자 닉네임")
    @Column(nullable = false, length = 50)
    private String nickname;

    @ApiModelProperty(position = 4,notes = "멤버 이미지")
    @Lob
    private String image;

    @ApiModelProperty(position = 5)
    @CreationTimestamp
    private LocalDateTime createAt;

    @JsonIgnore
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Board> boards = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Alarm> alarms = new ArrayList<>();

    @JsonIgnore
    @ApiModelProperty(position = 5,notes = "멤버 셋팅")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "setting_id")
    @NotNull
    private Setting setting;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", image='" + image + '\'' +
                ", createAt='" + createAt + '\'' +
                '}';
    }

    public static class Builder{
        private String email;
        private String nickname;
        private String image;
        private Setting setting;

        public Builder email(String email){
            this.email = email;
            return this;
        }
        public Builder nickname(String nickname){
            this.nickname = nickname;
            return this;
        }
        public Builder image(String image){
            this.image = image;
            return this;
        }
        public Builder setting(Setting setting){
            this.setting = setting;
            return this;
        }
        public Member build(){
            return new Member(this);
        }
    }
    private Member(Builder builder){
        this.email = builder.email;
        this.nickname = builder.nickname;
        this.image = builder.image;
        this.setting = builder.setting;
    }
    public Member(){}

    public static Builder builder(){
        return new Builder();
    }

}
