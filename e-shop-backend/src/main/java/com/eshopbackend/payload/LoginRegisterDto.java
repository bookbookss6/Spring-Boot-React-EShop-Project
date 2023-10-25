package com.eshopbackend.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRegisterDto {

    @NotEmpty(message = "아이디가 입력되지 않았습니다.")
    private String username;
    @NotEmpty(message = "비밀번호가 입력되지 않았습니다")
    private String password;
    private String token;
}
