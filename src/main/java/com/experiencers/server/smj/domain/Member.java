package com.experiencers.server.smj.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
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
    private Long id;

    @NotNull
    @Column(length = 50,nullable = false)
    private String email;


    @NotNull
    @Column(length = 50,nullable = false)
    private String nickname;

    @Lob
    private String image;

    @CreationTimestamp
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Alarm> alarms = new ArrayList<>();


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "setting_id",nullable = false)
    @NotNull
    private Setting setting;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

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
