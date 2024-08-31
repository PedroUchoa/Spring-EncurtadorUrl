package com.pedro.encurtador_url.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Data expirada")
public class DateExpiredExeception extends RuntimeException{

    public DateExpiredExeception(){
        super("Data da Url Expirada");
    }

}
