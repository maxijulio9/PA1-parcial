package exception;

public class PilotoExistenteException extends RuntimeException {
    public PilotoExistenteException(String mensaje) {
        super(mensaje);
    }
}
