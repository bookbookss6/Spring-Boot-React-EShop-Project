package com.eshopbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
public class APIException extends RuntimeException {

    private HttpStatus status;
    private String message;

}
