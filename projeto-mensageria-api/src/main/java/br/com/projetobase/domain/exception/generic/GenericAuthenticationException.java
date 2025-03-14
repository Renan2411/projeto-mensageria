package br.com.projetobase.domain.exception.generic;

public class GenericAuthenticationException extends RuntimeException{

    public GenericAuthenticationException(String message) {
        super(message);
    }

}
