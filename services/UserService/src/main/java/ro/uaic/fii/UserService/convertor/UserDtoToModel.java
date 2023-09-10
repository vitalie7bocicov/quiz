package ro.uaic.fii.UserService.convertor;

import ro.uaic.fii.UserService.dto.UserDto;
import ro.uaic.fii.UserService.model.User;

public class UserDtoToModel {
    public static User convert(UserDto dto)
    {
        return new User(dto.getDomainId(),
                dto.getRole(),
                dto.getAccount(),
                dto.getPassword(),
                dto.getName(),
                dto.getEmail(),
                dto.getNotes(),
                dto.getActive(),
                dto.getUserUid(),
                dto.getUserUid());
    }
}
