package com.experiencers.server.smj.api;

import com.experiencers.server.smj.domain.Board;
import com.experiencers.server.smj.domain.Setting;
import com.experiencers.server.smj.dto.SettingDto;
import com.experiencers.server.smj.service.SettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(tags = "Setting", description = "설정")
@RestController
@RequestMapping("/api/setting")
public class SettingApiController {

    @Autowired
    private SettingService settingService;

    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    @ApiOperation(value = "설정 상세",notes = "성공시 사용자의 설정정보를 반환합니다.",response = Setting.class)
    @GetMapping("")
    public ResponseEntity<?> getSetting(){
        Setting setting = settingService.readMemberSetting();

        return new ResponseEntity<>(setting,HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "성공")
    })
    @ApiOperation(value = "설정 수정",notes = "성공시 사용자의 설정내용을 변경합니다.")
    @PutMapping("")
    public ResponseEntity<?> putSetting(@RequestBody SettingDto settingDto){
        Setting updateSetting = settingService.updateSetting(settingDto);

        if(updateSetting ==null){
            return new ResponseEntity<>(settingDto, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updateSetting,HttpStatus.OK);
    }

}
