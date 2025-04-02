package br.com.findpark.exception;

import java.util.Date;

public record RespostaException(Date timestamp, String message, String detail) {}
