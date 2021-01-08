package com.experiencers.server.smj.controller;


import com.experiencers.server.smj.domain.Alarm;
import com.experiencers.server.smj.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AlarmController {
    @Autowired
    private AlarmService alarmService;

    @GetMapping("/alarm")
    public ModelAndView getIndex(){
        List<Alarm> alarmList = alarmService.readAllAlarm();

        ModelAndView response = new ModelAndView("alarm/index");
        response.addObject(alarmList);

        return response;
    }

    @PostMapping("/alarm")
    public String createAlarm(@ModelAttribute Alarm inputtedAlarm){
        System.out.println(inputtedAlarm.toString());
        Alarm savedAlarm = alarmService.writeAlarm(inputtedAlarm);

        return "redirect:/alarm";
    }


}
