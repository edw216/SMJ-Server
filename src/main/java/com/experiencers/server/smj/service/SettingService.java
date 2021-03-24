package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.domain.Setting;
import com.experiencers.server.smj.dto.SettingDto;
import com.experiencers.server.smj.manager.ManageMember;
import com.experiencers.server.smj.repository.MemberRepository;
import com.experiencers.server.smj.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingService {
    @Autowired
    private SettingRepository settingRepository;
    @Autowired
    private ManageMember manageMember;

    public Setting writeSetting(Setting inputtedSetting){
        Setting savedSetting = settingRepository.save(inputtedSetting);

        return savedSetting;
    } //저장하는거

    public Setting readSetting(Long settingId){
        Setting result = settingRepository.findById(settingId).get();

        return result;
    }//읽고 데이터 확인
    public Setting readMemberSetting(){
        Setting setting = manageMember.getManageMember().getSetting();

        return setting;
    }
    public List<Setting> readAllSetting(){return settingRepository.findAll();}

    public Setting updateSetting(SettingDto settingDto){
        Setting data = manageMember.getManageMember().getSetting();

        if(settingDto.isGps() || !settingDto.isGps()) {
            data.setGps(settingDto.isGps());
        }
        if(settingDto.isPush() || !settingDto.isPush()) {
            data.setPush(settingDto.isPush());
        }

        settingRepository.save(data);

        return data;
    }

    public void updateSetting(Long settingId, Setting setting) {
        setting.setId(settingId);
        settingRepository.save(setting);
    }

    public void removeSetting(Long setting_id){
        settingRepository.deleteById(setting_id);
    }

}

