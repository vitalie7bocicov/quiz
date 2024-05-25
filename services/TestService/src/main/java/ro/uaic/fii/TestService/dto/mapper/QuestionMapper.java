package ro.uaic.fii.TestService.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.uaic.fii.TestService.dto.reqDto.QuestionReqDto;
import ro.uaic.fii.TestService.dto.resDto.QuestionResDto;
import ro.uaic.fii.TestService.repository.model.Question;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(target = "topicIds", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "insertTimestamp", ignore = true)
    @Mapping(target = "updateTimestamp", ignore = true)
    @Mapping(target = "updateUid", ignore = true)
    @Mapping(target = "insertUid", source = "userUid")
    Question dtoToEntity(QuestionReqDto dto);

    QuestionResDto toDto(Question question);
}
