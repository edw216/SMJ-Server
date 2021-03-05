package com.experiencers.server.smj.api;

import com.experiencers.server.smj.domain.Setting;
import com.experiencers.server.smj.service.SettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "설정 불러오기",notes = "헤더에 jwt 토큰을 담고 성공시 모든 설정을 반환합니다.")
    @GetMapping("")
    public List<Setting> getSettings(@RequestHeader("Authorization")String token){
        return settingService.readAllSetting();
    }

    @ApiOperation(value = "설정 변경",notes = "변경할 설정 값을 보내면 변경됩니다.")
    @PutMapping("")
    public ResponseEntity<Setting> putSettings(@RequestHeader("Authorization")String token,@RequestBody Setting setting){
        Setting updateSetting = settingService.updateSetting(setting);

        if(updateSetting ==null){
            return new ResponseEntity<>(updateSetting, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updateSetting,HttpStatus.OK);
    }

}
