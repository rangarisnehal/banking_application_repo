package com.snehal.bankDemo.dtos;

import com.snehal.bankDemo.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String email;

    private String password;

    private String name;

    public static UserDto userToUserDto(Users user){
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(userDto.getEmail());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

    public static Users userDtoToUser(UserDto userDto){
        Users user = new Users();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return user;
    }

}
