package com.experiencers.server.smj.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPush() {
        return push;
    }

    public void setPush(Boolean push) {
        this.push = push;
    }

    public Boolean getGps() {
        return gps;
    }

    public void setGps(Boolean gps) {
        this.gps = gps;
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