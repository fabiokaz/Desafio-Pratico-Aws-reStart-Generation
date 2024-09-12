package com.fabiokaz.alunos_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class ResourceFoundException extends RuntimeException{
    public ResourceFoundException(String message) {
        super(message);
    }

    public ResourceFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
