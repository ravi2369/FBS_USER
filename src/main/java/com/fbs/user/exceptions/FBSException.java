package com.fbs.user.exceptions;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class FBSException extends RuntimeException {
    private final String code;

    public FBSException(String code, String message) {
        super(message);
        this.code = code;
        log.error("{},{}", code, message);
    }

    public FBSException(String message) {
        super("\"" + message + "\"");
        this.code = "ERROR";
        log.error("{},{}", code, message);
    }
}
