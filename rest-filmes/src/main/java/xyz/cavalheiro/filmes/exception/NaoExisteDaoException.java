package xyz.cavalheiro.filmes.exception;

public class NaoExisteDaoException extends RuntimeException {
    public NaoExisteDaoException(String message) {
        super(message);
    }
}
