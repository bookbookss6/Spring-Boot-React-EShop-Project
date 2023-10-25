package com.eshopbackend.service.impl;

import com.eshopbackend.entity.User;
import com.eshopbackend.exception.APIException;
import com.eshopbackend.payload.LoginRegisterDto;
import com.eshopbackend.payload.Role;
import com.eshopbackend.payload.UserInfoDto;
import com.eshopbackend.repository.UserRepository;
import com.eshopbackend.security.JwtTokenProvider;
import com.eshopbackend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginRegisterDto loginDto) {

        User user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(
                () -> new APIException(HttpStatus.BAD_REQUEST,"해당 유저가 없습니다.")
        );

        if(passwordEncoder.matches(loginDto.getPassword(),user.getPassword()))
        {
            loginDto.setPassword(loginDto.getPassword());
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public String logout() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.isAuthenticated())
        {
            auth.setAuthenticated(false);
            return "로그아웃 성공.";
        }
        else
        {
            return "로그아웃 실패.";
        }
    }

    @Override
    public Boolean validateToken(String token) {
        return jwtTokenProvider.validateToken(token);
    }

    @Override
    public UserInfoDto getUserInfo(String token) {
        String username = jwtTokenProvider.getUsername(token);
        User userDto = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("유저를 찾을 수가 없습니다"));

        UserInfoDto user = new UserInfoDto();
        user.setUsername(userDto.getUsername());
        user.setRoleName(userDto.getRole().name());

        return user;
    }

    @Override
    public String deleteUser(String username) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.isAuthenticated())
        {
            auth.setAuthenticated(false);
        }

        userRepository.deleteByUsername(username).
                orElseThrow(()-> new UsernameNotFoundException("유저를 찾을 수가 없습니다."));

        return "유저가 삭제되었습니다.";
    }

    @Override
    public String register(LoginRegisterDto registerDto) {

        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new APIException(HttpStatus.BAD_REQUEST, "이미 존재한 유저가 있습니다.");
        }


        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        if(user.getUsername().equalsIgnoreCase("admin"))
        {
            user.setRole(Role.ROLE_ADMIN);
        }
        else
        {
            user.setRole(Role.ROLE_USER);
        }

        userRepository.save(user);

        return "회원가입 완료되었습니다.";
    }
}
