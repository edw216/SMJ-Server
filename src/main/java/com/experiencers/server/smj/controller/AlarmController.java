package com.experiencers.server.smj.controller;


import com.experiencers.server.smj.domain.Alarm;
import com.experiencers.server.smj.domain.Category;
import com.experiencers.server.smj.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AlarmController {
    @Autowired
    private AlarmService alarmService;

    @GetMapping("/alarm")
    public ModelAndView getIndex(){
        //List<Alarm> alarmList = alarmService.readAllAlarm();

        //ModelAndView response = new ModelAndView("alarm/index");
        //response.addObject(alarmList);

        //return response;

        List<Alarm> alarms = alarmService.readAllAlarmOfMember();

        ModelAndView response = new ModelAndView("alarm/index");
        response.addObject("alarms", alarms);

        return response;
    }

    @PostMapping("/alarm")
    public String postAlarm(@ModelAttribute Alarm inputtedAlarm){
        //System.out.println(inputtedAlarm.toString());
        alarmService.saveAlarmOfMember(inputtedAlarm);

        return "redirect:/admin/alarm";
    }

    @PostMapping("/alarm/{alarm_id}/delete")
    public String deleteAlarm(@PathVariable("alarm_id") Long alarmId){
        alarmService.removeAlarm(alarmId);

        return "redirect:/admin/alarm";
    }

    @PostMapping("/alarm/{alarm_id}/edit")
    public ModelAndView editAlarm(@PathVariable("alarm_id") Long alarmId){
        Alarm alarm = alarmService.readAlarm(alarmId);

        ModelAndView mav = new ModelAndView("alarm/edit");
        mav.addObject("alarm",alarm);

        return mav;
    }

    @PostMapping("/alarm/{alarm_id}/update")
    public String updateAlarm(@PathVariable("alarm_id") Long alarmId,
                              @ModelAttribute Alarm alarm){
        alarmService.readAndUpdateAlarm(alarmId, alarm);

        return "redirect:/admin/alarm";
    }



}
