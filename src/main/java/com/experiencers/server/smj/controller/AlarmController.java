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

        ModelAndView response = new ModelAndView("alarm/index");
        //response.addObject(alarmList);

        return response;
    }

    @PostMapping("/alarm")
    public String postAlarm(@ModelAttribute Alarm inputtedAlarm){
        System.out.println(inputtedAlarm.toString());
        Alarm savedAlarm = alarmService.saveAlarm(inputtedAlarm);

        return "redirect:/admin/alarm";
    }

    @PostMapping("/alarm/{alarm_id}/delete")
    public String deleteAlarm(@PathVariable("alarm_id") Long alarm_id){
        alarmService.removeAlarm(alarm_id);

        return "redirect:/admin/alarm";
    }

    @PostMapping("/alarm/{alarm_id}/edit")
    public ModelAndView editAlarm(@PathVariable("alarm_id") Long alarm_id){
        Alarm alarm = alarmService.readAlarm(alarm_id);

        ModelAndView response = new ModelAndView("alarm/edit");
        response.addObject(alarm);

        return response;
    }

    @PostMapping("/alarm/{alarm_id}/update")
    public String updateAlarm(@PathVariable("alarm_id") Long alarm_id,
                              @ModelAttribute Alarm alarm){
        alarmService.readAndUpdateAlarm(alarm_id, alarm);

        return "redirect:/admin/alarm";
    }



}
