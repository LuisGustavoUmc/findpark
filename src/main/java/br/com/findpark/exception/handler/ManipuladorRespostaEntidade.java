package br.com.findpark.exception.handler;

import br.com.findpark.exception.AutenticacaoJwtInvalidaException;
import br.com.findpark.exception.RespostaException;
import br.com.findpark.exception.ObjetoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class ManipuladorRespostaEntidade extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<RespostaException> manipularTodasExceptions(Exception ex, WebRequest requisicao) {
        RespostaException resposta = new RespostaException(
                new Date(),
                ex.getMessage(),
                requisicao.getDescription(false));
        return new ResponseEntity<>(resposta, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public final ResponseEntity<RespostaException> manipularObjetoNaoEncontradoException(Exception ex, WebRequest requisicao) {
        RespostaException resposta = new RespostaException(
                new Date(),
                ex.getMessage(),
                requisicao.getDescription(false));
        return new ResponseEntity<>(resposta, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AutenticacaoJwtInvalidaException.class)
    public final ResponseEntity<RespostaException> manipularAutenticacaoJwtInvalidaException(Exception ex, WebRequest requisicao) {
        RespostaException resposta = new RespostaException(
                new Date(),
                ex.getMessage(),
                requisicao.getDescription(false));
        return new ResponseEntity<>(resposta, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<RespostaException> tratarCredenciaisInvalidas(BadCredentialsException ex, WebRequest request) {
        RespostaException resposta = new RespostaException(
                new Date(),
                "Usuário inexistente ou senha inválida",
                request.getDescription(false)
        );
        return new ResponseEntity<>(resposta, HttpStatus.UNAUTHORIZED);
    }
}
