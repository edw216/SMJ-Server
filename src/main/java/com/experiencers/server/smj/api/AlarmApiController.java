package com.experiencers.server.smj.api;

import com.experiencers.server.smj.domain.Alarm;
import com.experiencers.server.smj.service.AlarmService;
import com.experiencers.server.smj.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alarms")
public class AlarmApiController {
    @Autowired
    private AlarmService alarmService;

    @Autowired
    private MemberService memberService;

    @GetMapping("")
    public List<Alarm> getAlarms(@RequestHeader("Authorization")String token,Principal principal) {
        //ist<Alarm> alarmList = alarmService.readAllAlarm(principal.getName());
        List<Alarm> alarmList = alarmService.readAllAlarm();

        return alarmList;
    }

    @PostMapping("")
    // 성공: 201 Created
    public Alarm postAlarm(@RequestBody Alarm alarm){
        System.out.println(alarm.toString());
        Alarm savedAlarm = alarmService.saveAlarm(alarm);

        return savedAlarm;
    }

    @PutMapping("/{alarm_id}")
    // 성공: 200 OK
    // 실패: 404 NOT FOUND
    public ResponseEntity<Alarm> putAlarm(@RequestHeader("Authorization")String token,@PathVariable("alarm_id") Long alarmId, @RequestBody Alarm alarm){
        Alarm updatedAlarm = alarmService.readAndUpdateAlarm(alarmId, alarm);

        if (updatedAlarm == null) {
            alarm.setId(alarmId);
            return new ResponseEntity<>(alarm, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedAlarm, HttpStatus.OK);
    }

    @DeleteMapping("/{alarm_id}")
    public ResponseEntity<Object> deleteAlarm(@RequestHeader("Authorization")String token,@PathVariable("alarm_id") Long alarmId){
        alarmService.removeAlarm(alarmId);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("alarm_id", alarmId);
        result.put("alarm", data);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}