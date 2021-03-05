package com.experiencers.server.smj.api;

import com.experiencers.server.smj.domain.Alarm;
import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.manager.ManageMember;
import com.experiencers.server.smj.service.AlarmService;
import com.experiencers.server.smj.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alarms")
public class AlarmApiController {
    @Autowired
    private AlarmService alarmService;

    @Autowired
    private ManageMember manageMember;

    @ApiOperation(value = "모든 알람 불러오기",notes = "헤더에 jwt 토큰을 담고 성공시 사용자의 모든 알람을 반환합니다.")
    @GetMapping("")
    public List<Alarm> getAlarms(@RequestHeader("Authorization")String token) {
        List<Alarm> alarmList = alarmService.readAllAlarm();
        //List<Alarm> alarmList = alarmService.readAllAlarm();
        return alarmList;
    }

    @ApiOperation(value = "알람 저장하기",notes = "헤더에 jwt 토큰을 담고 성공시 해당 유저의 알람을 저장합니다.")
    @PostMapping("")
    // 성공: 201 Created
    public Alarm postAlarm(@RequestHeader("Authorization")String token, @RequestBody Alarm alarm){
        Alarm savedAlarm = alarmService.saveAlarm(alarm);

        return savedAlarm;
    }

    @ApiImplicitParam(name = "alarm_id",value = "알람번호",required = true,paramType = "path")
    @ApiOperation(value = "알람 변경하기",notes = "헤더에 jwt 토큰을 담고 성공시 해당 알람의 내용을 변경합니다.")
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

    @ApiImplicitParam(name = "alarm_id",value = "알람번호",required = true,paramType = "path")
    @ApiOperation(value = "알람 삭제하기",notes = "헤더에 jwt 토큰을 담고 성공시 해당 알람을 삭제합니다.")
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