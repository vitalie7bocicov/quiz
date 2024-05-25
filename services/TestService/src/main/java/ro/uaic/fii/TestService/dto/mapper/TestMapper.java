package ro.uaic.fii.TestService.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.uaic.fii.TestService.dto.reqDto.TestReqDto;
import ro.uaic.fii.TestService.dto.resDto.TestResDto;
import ro.uaic.fii.TestService.repository.model.Test;

@Mapper(componentModel = "spring")
public interface TestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "updateUid", ignore = true)
    @Mapping(target = "insertUid", source = "userUid")
    @Mapping(target = "completed", ignore = true)
    Test dtoToEntity(TestReqDto createDto);

    TestResDto toDto(Test test);
}
