package com.experiencers.server.smj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(example = "email@example.com")
    @Column(nullable = false, length = 50)
    private String email;
    @ApiModelProperty(example = "사용자 닉네임")
    @Column(nullable = false, length = 50)
    private String nickname;
    @Lob
    private String image;
    @CreationTimestamp
    private LocalDateTime createAt;

    @JsonIgnore
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Board> boards = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Alarm> alarms = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "setting_id",nullable = false)
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

}
