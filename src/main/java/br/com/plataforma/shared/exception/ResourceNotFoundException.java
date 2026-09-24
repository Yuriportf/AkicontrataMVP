package br.com.plataforma.shared.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String recurso, Long id) {
        super(recurso + " não encontrado(a) com id: " + id);
    }
}
