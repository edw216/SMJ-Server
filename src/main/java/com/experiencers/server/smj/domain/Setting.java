package com.experiencers.server.smj.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "setting")
@JsonRootName("setting")
public class Setting{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setting_id")
    @JsonProperty(value = "id", index = 1)
    @ApiModelProperty(position = 1,notes = "셋팅 아이디")
    private Long id;

    @ApiModelProperty(position = 2,notes = "푸쉬 알림",example = "false")
    @Column
    private Boolean push;

    @ApiModelProperty(position = 3,notes = "GPS 수신상태",example = "true")
    @Column
    private Boolean gps;

    @JsonIgnore
    @OneToOne(mappedBy = "setting",fetch = FetchType.EAGER)
    private Member member;


    public Setting() {
        this.push = false;
        this.gps = false;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "id=" + id +
                ", push=" + push +
                ", gps=" + gps +
                '}';
    }
}