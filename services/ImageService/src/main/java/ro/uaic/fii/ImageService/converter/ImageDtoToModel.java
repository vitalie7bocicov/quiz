package ro.uaic.fii.ImageService.converter;

import ro.uaic.fii.ImageService.dto.ImageDto;
import ro.uaic.fii.ImageService.model.Image;

import java.util.UUID;

public class ImageDtoToModel {
    public static Image convert(ImageDto dto, UUID inserterUid, UUID updaterUid) {
        return new Image(
                dto.topicId(),
                dto.name(),
                dto.mimeType(),
                dto.content(),
                dto.notes(),
                inserterUid,
                updaterUid
        );
    }
}
