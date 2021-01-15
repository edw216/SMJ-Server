package com.experiencers.server.smj.alarm;


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
    public Alarm readAlarm(Long alarmId){return alarmRepository.getOne(alarmId);}

    public List<Alarm> readAllAlarm(){return alarmRepository.findAll();}

    public void removeAlarm(Long alarmId){
        alarmRepository.deleteById(alarmId);
    }

    public void updateAlarm(Alarm alarm){
        Alarm beforeAlarm = alarmRepository.findById(alarm.getId()).get();
        beforeAlarm.setTitle(alarm.getTitle());
        beforeAlarm.setDay(alarm.getDay());
        beforeAlarm.setTime(alarm.getTime());
        beforeAlarm.setRepeat(alarm.getRepeat());

        alarmRepository.save(beforeAlarm);

    }

    public Alarm readAndUpdateAlarm(Long alarmId, Alarm alarm) {
        Optional<Alarm> data = alarmRepository.findById(alarmId);

        if (data.isPresent()) {
            Alarm target = data.get();
            target.setTitle(alarm.getTitle());
            target.setTime(alarm.getTime());
            target.setRepeat(alarm.getRepeat());
            target.setDay(alarm.getDay());

            target = alarmRepository.save(target);

            return target;
        }

        return null;
    }
}
