package com.eshopbackend.service;

import com.eshopbackend.payload.LoginRegisterDto;
import com.eshopbackend.payload.UserInfoDto;

public interface AuthService {

    String register(LoginRegisterDto registerDto);

    String login(LoginRegisterDto loginDto);
    String logout();
    Boolean validateToken(String token);
    UserInfoDto getUserInfo(String token);
    String deleteUser(String username);
}
