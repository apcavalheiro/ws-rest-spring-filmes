package xyz.cavalheiro.filmes.exception;

public class IdNaoValidoServiceException extends  RuntimeException{
    public IdNaoValidoServiceException(String message) {
        super(message);
    }
}
