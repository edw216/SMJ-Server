package com.experiencers.server.smj.alarm;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;


@Entity
@Table(name = "alarm")
@JsonRootName("alarm")
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="alarm_id")
    @JsonProperty(value = "alarm_id", index = 1)
    private Long id;
    @Column(length = 50)
    private String title;
    @Column(nullable = false)
    private Date day;
    @Column(nullable = false)
    private Time time;  //Time타입
    @Column(nullable = false)
    private String repeat; //Enum 타입

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    @Override
    public String toString() {
        return "Alarm{"+
                "id="+id+
                ", title='" +title+'\''+
                ", day='"+day+'\''+
                ", time='"+time+'\''+
                ", repeat='"+repeat+'\''+
                '}';

    }
}


