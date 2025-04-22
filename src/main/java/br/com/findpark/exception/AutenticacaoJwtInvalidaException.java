package br.com.findpark.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AutenticacaoJwtInvalidaException extends AuthenticationException {

    public AutenticacaoJwtInvalidaException(String message) {
        super(message);
    }
}
