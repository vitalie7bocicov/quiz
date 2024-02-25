package ro.uaic.fii.SettingService.service;

import org.springframework.stereotype.Service;
import ro.uaic.fii.SettingService.exceptions.NotFoundException;
import ro.uaic.fii.SettingService.model.Setting;
import ro.uaic.fii.SettingService.repository.SettingRepository;

import java.util.List;

@Service
public class SettingService {

    private final SettingRepository settingRepository;

    public SettingService(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    public List<Setting> getAll() {
        return settingRepository.findAll();
    }

    public Setting getById(Integer id) {
        return settingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Setting with ID: " + id + " not found."));
    }

    public Setting save(Setting setting) {
        return settingRepository.save(setting);
    }

    public Setting update(Integer id, Setting setting) {
        Setting settingToUpdate = getById(id);
        settingToUpdate.setValue(setting.getValue());
        settingToUpdate.setUpdateUid(setting.getUpdateUid());
        return settingRepository.save(settingToUpdate);
    }

    public void deleteById(Integer id) {
        settingRepository.deleteById(id);
    }
}
