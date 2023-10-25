package com.eshopbackend.mapper;

import com.eshopbackend.entity.User;
import com.eshopbackend.payload.UserDto;

public class UserMapper {

    public static UserDto mapToDto(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());

        return userDto;

    }

    public static User mapToEntity(UserDto userDto)
    {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        return user;
    }

}
