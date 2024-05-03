package ro.uaic.fii.CollegeMgmtSvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.fii.CollegeMgmtSvc.dto.mapper.SettingMapper;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.SettingCreateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.reqDto.SettingUpdateDto;
import ro.uaic.fii.CollegeMgmtSvc.dto.resDto.SettingDto;
import ro.uaic.fii.CollegeMgmtSvc.exceptions.NotFoundException;
import ro.uaic.fii.CollegeMgmtSvc.repository.SettingRepository;
import ro.uaic.fii.CollegeMgmtSvc.repository.model.Setting;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SettingService {

    private final SettingRepository settingRepository;
    private final SettingMapper settingMapper;
    public List<SettingDto> getAll() {
        List<Setting> settings = settingRepository.findAll();
        return settings.stream().map(settingMapper::toDto).toList();
    }

    public SettingDto getById(int id) {
        Setting setting =  settingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Setting", id));
        return settingMapper.toDto(setting);
    }

    public SettingDto save(SettingCreateDto createDto) {
        Setting savedSetting = settingRepository.save(settingMapper.dtoToEntity(createDto));
        return settingMapper.toDto(savedSetting);
    }

    public SettingDto update(int id, SettingUpdateDto updateDto) {
        Setting existingSetting = settingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Setting", id));
        existingSetting.setValue(updateDto.value());
        existingSetting.setUpdateUid(updateDto.userUid());
        Setting updatedSetting = settingRepository.save(existingSetting);
        return settingMapper.toDto(updatedSetting);
    }

    public void deleteById(int id) {
        if (!settingRepository.existsById(id)) {
            throw new NotFoundException("Setting", id);
        }
        settingRepository.deleteById(id);
    }
}
