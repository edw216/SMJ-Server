package com.experiencers.server.smj.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "setting")
public class Setting{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setting_id")
    private Long id;

    @Column(nullable = false)
    private Boolean push;

    @Column(nullable = false)
    private Boolean gps;


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