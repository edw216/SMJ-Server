package com.experiencers.server.smj.service;

import com.experiencers.server.smj.domain.Category;
import com.experiencers.server.smj.domain.Comment;
import com.experiencers.server.smj.domain.Setting;
import com.experiencers.server.smj.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingService {
    @Autowired
    private SettingRepository settingRepository;

    public Setting writeSetting(Setting inputtedSetting){
        Setting savedSetting = settingRepository.save(inputtedSetting);

        return savedSetting;
    } //저장하는거

    public Setting readSetting(Long settingId){
        Setting result = settingRepository.findById(settingId).get();

        return result;
    }//읽고 데이터 확인

    public List<Setting> readAllSetting(){return settingRepository.findAll();}
    public void updateSetting(Setting setting){
        // 1. 저장된 데이터를 찾기
        // 2. 찾은 데이터의 값을 바꿔주기
        // 3. 바꾼 데이터를 다시 저장
        // - #중요: id가 바뀌면 새로 생성, 동일하면 수정
        Setting data = settingRepository.findById(setting.getId()).get();
        data.setId(setting.getId());
        settingRepository.save(data);
    }

    public void updateSetting(Long settingId, Setting setting) {
        setting.setId(settingId);
        settingRepository.save(setting);
    }

    public void removeSetting(Long setting_id){
        settingRepository.deleteById(setting_id);
    }

}

