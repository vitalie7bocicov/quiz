package ro.uaic.fii.TestService.converter;

import ro.uaic.fii.TestService.dto.TestReqDto;
import ro.uaic.fii.TestService.model.Test;

import java.util.UUID;

public class TestReqDtoToModel {
    public static Test convert(TestReqDto dto, UUID insertUuid, UUID updateUuid) {
        return new Test(dto.courseId(),
                        dto.name(),
                        dto.groupId(),
                        dto.startTime(),
                        dto.duration(),
                        dto.optNumber(),
                        dto.accessCode(),
                        dto.active(),
                        dto.completed(),
                        dto.demo(),
                        dto.notes(),
                        insertUuid,
                        updateUuid);
    }
}
