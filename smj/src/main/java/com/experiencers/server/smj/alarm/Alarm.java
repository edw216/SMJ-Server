package com.experiencers.server.smj.alarm;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;


@Entity
@Table(name = "alarm")
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="alarm_id")
    private Long alarm_id;
    @Column(length = 50)
    private String title;
    @Column(nullable = false)
    private Date day;
    @Column(nullable = false)
    private Time time;  //Time타입
    @Column(nullable = false)
    private String repeat; //Enum 타입

    public Long getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(Long alarm_id) {
        this.alarm_id = alarm_id;
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
                "alarm_id="+alarm_id+
                ", title='" +title+'\''+
                ", day='"+day+'\''+
                ", time='"+time+'\''+
                ", repeat='"+repeat+'\''+
                '}';

    }
}


