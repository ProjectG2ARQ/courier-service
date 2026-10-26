package ar.edu.unju.fi.arquitecturas.courierservice.exception;

public class NegocioException extends RuntimeException {
    public NegocioException(String message) {
        super(message);
    }
}