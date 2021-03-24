package com.experiencers.server.smj.service;


import com.experiencers.server.smj.domain.Alarm;
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
    private ManageMember manageMember;

    public Alarm saveAlarm(AlarmDto alarmDto){
        boolean check = false;
        Member member = manageMember.getManageMember();

        RepeatType repeatType[] = RepeatType.values();

        for(RepeatType rt : repeatType){
            if(rt.toString().equals(alarmDto.getRepeat().toString())){
                check =true;
            }
        }
        if(check == false){
            alarmDto.setRepeat(RepeatType.ONCE);
        }

        Alarm inputtedAlarm = Alarm.builder()
                .title(alarmDto.getTitle())
                .content(alarmDto.getContent())
                .day(alarmDto.getDay())
                .startTime(alarmDto.getStartTime())
                .endTime(alarmDto.getEndTime())
                .repeat(alarmDto.getRepeat())
                .member(member)
                .build();

        Alarm savedAlarm = alarmRepository.save(inputtedAlarm);

        return savedAlarm;
    }
    public Alarm saveAlarmOfMember(Alarm inputtedAlarm){
        Alarm savedAlarm = alarmRepository.save(inputtedAlarm);

        return savedAlarm;
    }

    public Alarm readAlarm(Long alarmId){return alarmRepository.findById(alarmId).get();}

    public List<Alarm> readAllAlarm(){
        Member member = manageMember.getManageMember();

        List<Alarm> alarms = member.getAlarms();

        return alarms;
    }
    public List<Alarm> readAllAlarmOfMember(){
        return alarmRepository.findAll();
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
    public Alarm readAndUpdateAlarm(Long alarmId, AlarmDto alarmDto) {
        Optional<Alarm> data = alarmRepository.findById(alarmId);

        if (!data.isPresent()) {
            return null;
        }

        Alarm updateData = data.get();
        updateData.setTitle(alarmDto.getTitle());
        updateData.setContent(alarmDto.getContent());
        updateData.setDay(alarmDto.getDay());
        updateData.setStartTime(alarmDto.getStartTime());
        updateData.setEndTime(alarmDto.getEndTime());
        updateData.setRepeat(alarmDto.getRepeat());

        Alarm updatedalarm = alarmRepository.save(updateData);

        return updatedalarm;
    }
}
