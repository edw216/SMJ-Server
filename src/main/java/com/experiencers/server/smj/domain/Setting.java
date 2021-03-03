package com.experiencers.server.smj.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "setting")
@JsonRootName("setting")
public class Setting{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setting_id")
    @JsonProperty(value = "setting_id", index = 1)
    private Long id;

    @Column
    private Boolean push;

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