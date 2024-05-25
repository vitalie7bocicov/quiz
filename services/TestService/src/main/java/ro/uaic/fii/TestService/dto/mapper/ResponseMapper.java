package ro.uaic.fii.TestService.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.uaic.fii.TestService.dto.reqDto.ResponseReqDto;
import ro.uaic.fii.TestService.dto.resDto.ResponseResDto;
import ro.uaic.fii.TestService.repository.model.Response;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "updateUid", ignore = true)
    @Mapping(target = "insertUid", source = "userUid")
   Response dtoToEntity(ResponseReqDto dto);

   ResponseResDto toDto(Response response);
}
