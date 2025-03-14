package br.com.projetomensageria.domain.exception.generic;

public class GenericAuthenticationException extends RuntimeException{

    public GenericAuthenticationException(String message) {
        super(message);
    }

}
