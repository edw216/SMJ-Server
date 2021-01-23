package com.experiencers.server.smj.service;


import com.experiencers.server.smj.domain.Alarm;
import com.experiencers.server.smj.repository.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlarmService {

    @Autowired
    private AlarmRepository alarmRepository;

    public Alarm saveAlarm(Alarm inputtedAlarm){
        Alarm savedAlarm = alarmRepository.save(inputtedAlarm);

        return savedAlarm;
    }
    public Alarm readAlarm(Long alarm_id){return alarmRepository.getOne(alarm_id);}

    public List<Alarm> readAllAlarm(){return alarmRepository.findAll();}

    public void removeAlarm(Long alarm_id){
        alarmRepository.deleteById(alarm_id);
    }

    public void updateAlarm(Alarm alarm){
        Alarm beforeAlarm = alarmRepository.findById(alarm.getId()).get();
        beforeAlarm.setTitle(alarm.getTitle());
        beforeAlarm.setDay(alarm.getDay());
        beforeAlarm.setTime(alarm.getTime());
        beforeAlarm.setRepeat(alarm.getRepeat());

        alarmRepository.save(beforeAlarm);

    }
    //remove, update
    public Alarm readAndUpdateAlarm(Long alarmId, Alarm alarm) {
        Optional<Alarm> data = alarmRepository.findById(alarmId);

        if (!data.isPresent()) {
            return null;
        }

        Alarm updateData = data.get();
        updateData.setRepeat(alarm.getRepeat());
        updateData.setTitle(alarm.getTitle());
        updateData.setTime(alarm.getTime());
        updateData.setDay(alarm.getDay());

        return updateData;
    }
}
