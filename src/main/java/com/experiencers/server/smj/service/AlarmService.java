package com.experiencers.server.smj.service;


import com.experiencers.server.smj.domain.Alarm;
import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.enumerate.RepeatType;
import com.experiencers.server.smj.manager.ManageMember;
import com.experiencers.server.smj.repository.AlarmRepository;
import com.experiencers.server.smj.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AlarmService {

    @Autowired
    private AlarmRepository alarmRepository;
    @Autowired
    private ManageMember manageMember;

    public Alarm saveAlarm(Alarm inputtedAlarm){
        boolean check = false;
        Member member = manageMember.getManageMember();

        RepeatType repeatType[] = RepeatType.values();

        for(RepeatType rt : repeatType){
            if(rt.toString().equals(inputtedAlarm.getRepeat().toString())){
                check =true;
            }
        }
        if(check == false){
            inputtedAlarm.setRepeat(RepeatType.ONCE);
        }

        inputtedAlarm.setMember(member);


        Alarm savedAlarm = alarmRepository.save(inputtedAlarm);

        return savedAlarm;
    }
    public Alarm readAlarm(Long alarmId){return alarmRepository.findById(alarmId).get();}

    public List<Alarm> readAllAlarm(){

        List<Alarm> alarms = manageMember.getManageMember().getAlarms();

        return alarms;
    }

    public void removeAlarm(Long alarmId){
        alarmRepository.deleteById(alarmId);
    }

    public void updateAlarm(Alarm alarm){
        Alarm beforeAlarm = alarmRepository.findById(alarm.getId()).get();
        beforeAlarm.setTitle(alarm.getTitle());
        beforeAlarm.setDay(alarm.getDay());
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
        updateData.setContent(alarm.getContent());
        updateData.setStartTime(alarm.getStartTime());
        updateData.setEndTime(alarm.getEndTime());
        updateData.setDay(alarm.getDay());

        Alarm updatedalarm = alarmRepository.save(updateData);

        return updatedalarm;
    }
}
