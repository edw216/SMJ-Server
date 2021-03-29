package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Setting;
import com.experiencers.server.smj.dto.SettingDto;
import com.experiencers.server.smj.manager.MemberManager;
import com.experiencers.server.smj.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingService {
    @Autowired
    private SettingRepository settingRepository;
    @Autowired
    private MemberManager memberManager;

    //Api Service
    public SettingDto.SettingDtoResponse readMemberSetting(){
        Setting setting = memberManager.getMember().getSetting();

        return SettingDto.SettingDtoResponse.of(setting);
    }

    public SettingDto.SettingDtoResponse updateSetting(SettingDto.SettingDtoRequest settingDto){
        Setting data = memberManager.getMember().getSetting();

        if(settingDto.isGps() || !settingDto.isGps()) {
            data.setGps(settingDto.isGps());
        }
        if(settingDto.isPush() || !settingDto.isPush()) {
            data.setPush(settingDto.isPush());
        }

        Setting updateSetting = settingRepository.save(data);


        return SettingDto.SettingDtoResponse.of(updateSetting);
    }

    //Admin Service
    public Setting writeSetting(Setting inputtedSetting){
        Setting savedSetting = settingRepository.save(inputtedSetting);

        return savedSetting;
    }

    public Setting readSetting(Long settingId){
        Setting result = settingRepository.findById(settingId).get();

        return result;
    }
    public List<Setting> readAllSetting(){return settingRepository.findAll();}



    public void updateSetting(Long settingId, Setting setting) {
        setting.setId(settingId);
        settingRepository.save(setting);
    }

    public void removeSetting(Long setting_id){
        settingRepository.deleteById(setting_id);
    }

}

