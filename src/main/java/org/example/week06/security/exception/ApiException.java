package org.example.week06.security.exception;

import lombok.Getter;

@Getter
public class ApiException extends Throwable {
    public ApiException(int i, String invalidToken) {

    }
}
