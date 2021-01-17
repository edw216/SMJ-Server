package com.experiencers.server.smj.alarm;


import com.experiencers.server.smj.alarm.Alarm;
import com.experiencers.server.smj.alarm.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmService {

    @Autowired
    private AlarmRepository alarmRepository;

    public Alarm writeAlarm(Alarm inputtedAlarm){
        Alarm savedAlarm = alarmRepository.save(inputtedAlarm);

        return savedAlarm;
    }
    public Alarm readAlarm(Long alarm_id){return alarmRepository.getOne(alarm_id);}

    public List<Alarm> readAllAlarm(){return alarmRepository.findAll();}

    public void removeAlarm(Long alarm_id){
        alarmRepository.deleteById(alarm_id);
    }

    public void updateAlarm(Alarm alarm){
        Alarm beforeAlarm = alarmRepository.findById(alarm.getAlarm_id()).get();
        beforeAlarm.setTitle(alarm.getTitle());
        beforeAlarm.setDay(alarm.getDay());
        beforeAlarm.setTime(alarm.getTime());
        beforeAlarm.setRepeat(alarm.getRepeat());

        alarmRepository.save(beforeAlarm);

    }
    //remove, update
}
