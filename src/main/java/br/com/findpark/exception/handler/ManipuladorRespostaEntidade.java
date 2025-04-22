package br.com.findpark.exception.handler;

import br.com.findpark.exception.RespostaException;
import br.com.findpark.exception.ObjetoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
