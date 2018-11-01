package br.com.devmedia.curso.exception;

public class IdNaoValidoServiceException extends RuntimeException {
    public IdNaoValidoServiceException(String message) {
        super(message);
    }
}
