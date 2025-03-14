package br.com.projetomensageria.domain.exception;

import br.com.projetomensageria.domain.exception.generic.GenericAuthenticationException;

public class TokenInvalidoException extends GenericAuthenticationException {

    public TokenInvalidoException() {
        super("Token Inválido!");
    }

}
