package com.experiencers.server.smj.api;

import com.experiencers.server.smj.domain.Setting;
import com.experiencers.server.smj.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/settings")
public class SettingApiController {

    @Autowired
    private SettingService settingService;

    @GetMapping("")
    public List<Setting> getSettings(@RequestHeader("Authorization")String token){
        return settingService.readAllSetting();
    }

    @PutMapping("")
    public ResponseEntity<Setting> putSettings(@RequestHeader("Authorization")String token,@RequestBody Setting setting){
        Setting updateSetting = settingService.updateSetting(setting);

        if(updateSetting ==null){
            return new ResponseEntity<>(updateSetting, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updateSetting,HttpStatus.OK);
    }

}
