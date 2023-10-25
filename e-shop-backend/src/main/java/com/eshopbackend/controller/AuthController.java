package com.eshopbackend.controller;

import com.eshopbackend.payload.LoginRegisterDto;
import com.eshopbackend.payload.UserInfoDto;
import com.eshopbackend.payload.ValidateTokenDto;
import com.eshopbackend.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/auth", produces = "application/json; charset=utf8")
public class AuthController {

    private AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginRegisterDto> login(@RequestBody @Valid LoginRegisterDto loginDto){
        String token = authService.login(loginDto);
        loginDto.setToken(token);
        return new ResponseEntity<>(loginDto, HttpStatus.OK);
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<String> logout()
    {
        return new ResponseEntity<>(authService.logout(),HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody @Valid LoginRegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "/validate-token")
    public ResponseEntity<ValidateTokenDto> validateToken(@RequestBody ValidateTokenDto validateTokenDto)
    {
        Boolean isValid = authService.validateToken(validateTokenDto.getToken());
        validateTokenDto.setIsValid(isValid);
        return new ResponseEntity<>(validateTokenDto,HttpStatus.OK);
    }


    @GetMapping(value = "/user-info/token={token}")
    public ResponseEntity<UserInfoDto> getUserInfo
            (@PathVariable(value = "token")String token)
    {
        UserInfoDto user = authService.getUserInfo(token);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<String> deleteUser(@RequestBody String username)
    {
        return new ResponseEntity(authService.deleteUser(username),HttpStatus.OK);
    }

}
