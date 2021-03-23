package com.experiencers.server.smj.service;


import com.experiencers.server.smj.domain.Alarm;
import com.experiencers.server.smj.domain.Category;
import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.manager.ManageMember;
import com.experiencers.server.smj.repository.AlarmRepository;
import com.experiencers.server.smj.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlarmService {

    @Autowired
    private AlarmRepository alarmRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ManageMember manageMember;

    public Alarm saveAlarm(Alarm inputtedAlarm){

        Member member = manageMember.getManageMember();
        Alarm savedAlarm = alarmRepository.save(inputtedAlarm);

        return savedAlarm;
    }
    public Alarm saveAlarmOfMember(Alarm inputtedAlarm){
        Alarm savedAlarm = alarmRepository.save(inputtedAlarm);

        return savedAlarm;
    }

    public Alarm readAlarm(Long alarmId){return alarmRepository.findById(alarmId).get();}

    public List<Alarm> readAllAlarm(){

        List<Alarm> alarms = manageMember.getManageMember().getAlarms();
        return alarms;
    }
    public List<Alarm> readAllAlarmOfMember(){
        return alarmRepository.findAll();
    }

    public void removeAlarm(Long alarmId){
        alarmRepository.deleteById(alarmId);
    }

    public Alarm readAndUpdateAlarm(Long alarmId, Alarm alarm) {
        Optional<Alarm> data = alarmRepository.findById(alarmId);

        if (data.isPresent()) {
            Alarm target = data.get();
            target.setRepeat(alarm.getRepeat());
            target.setTitle(alarm.getTitle());
            target.setContent(alarm.getContent());
            target.setStartTime(alarm.getStartTime());
            target.setEndTime(alarm.getEndTime());
            target.setDay(alarm.getDay());
            target = alarmRepository.save(target);

            return target;
        }

        return null;
    }
}
