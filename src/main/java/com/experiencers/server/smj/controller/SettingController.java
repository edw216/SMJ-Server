package com.experiencers.server.smj.controller;

import com.experiencers.server.smj.domain.Setting;
import com.experiencers.server.smj.enumerate.BoardType;
import com.experiencers.server.smj.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class SettingController {
    @Autowired
    SettingService settingService;

    @GetMapping("/setting")
    public ModelAndView getSetting() {
        ModelAndView response = new ModelAndView("Setting/index"); //새로운 객체

        List<Setting> settingList = settingService.readAllSetting();
        response.addObject("settings", settingList);

        return response;
    }

    @PostMapping("/setting")
    public String postSetting(@ModelAttribute Setting inputtedSetting){
        Setting savedSetting = settingService.writeSetting(inputtedSetting);
        return "redirect:/admin/setting";
    }//파라미터 (저장할 부분)

    @GetMapping("/setting/{setting_id}/edit") //복합적인 설정을 할 ㄸ에 @PathVariable 사용
    public ModelAndView editSetting(@PathVariable("setting_id") Long settingId) {
        Setting setting = settingService.readSetting(settingId);

        ModelAndView mav = new ModelAndView("Setting/edit");
        mav.addObject("setting", setting);
        return mav;
    }

    @PostMapping("/setting/{setting_id}/update")
    public String updateSetting(@PathVariable("setting_id") Long settingId,
                              @ModelAttribute Setting setting){
        settingService.updateSetting(settingId, setting);

        return "redirect:/admin/setting";
    } //수정사항 업데이트

    @PostMapping("/setting/{setting_id}/delete")
    public String deleteSetting(@PathVariable("setting_id") Long settingId){
        settingService.removeSetting(settingId);

        return "redirect:/admin/setting";
    } //데이터 삭제

}
