package sk.matusturjak.price_sender.mapper;

import sk.matusturjak.price_sender.dto.UserDto;
import sk.matusturjak.price_sender.model.db.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    private UserMapper() {}

    public static List<UserDto> usersToUsersDto(List<User> users) {
        return users
                .stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setName(user.getName());
                    userDto.setSurname(user.getSurname());
                    userDto.setEmail(user.getEmail());
                    userDto.setLastEmailSend(user.getLastEmailSend());
                    userDto.setItems(user.getItems());

                    return userDto;
                })
                .collect(Collectors.toList());
    }
}
