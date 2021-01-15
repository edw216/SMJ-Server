package com.experiencers.server.smj.alarm;

import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alarms")
public class AlarmApiController {
    @Autowired
    private AlarmService alarmService;

    @GetMapping("")
    public List<Alarm> getAlarms() {
        List<Alarm> alarmList = alarmService.readAllAlarm();

        return alarmList;
    }

    @PostMapping("")
    public Alarm postAlarm(@RequestBody Alarm alarm){
        Alarm savedAlarm = alarmService.saveAlarm(alarm);

        return savedAlarm;
    }

    @PutMapping("/{alarm_id}")
    public ResponseEntity<Alarm> putAlarm(@PathVariable("alarm_id") Long alarmId, @RequestBody Alarm alarm){
        Alarm updatedAlarm = alarmService.readAndUpdateAlarm(alarmId, alarm);

        if (updatedAlarm == null) {
            alarm.setId(alarmId);
            return new ResponseEntity<>(alarm, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(updatedAlarm, HttpStatus.OK);
    }

    @DeleteMapping("/{alarm_id}")
    public ResponseEntity<Object> deleteAlarm(@PathVariable("alarm_id") Long alarmId){
        alarmService.removeAlarm(alarmId);

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("alarm_id", alarmId);
        result.put("alarm", data);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}