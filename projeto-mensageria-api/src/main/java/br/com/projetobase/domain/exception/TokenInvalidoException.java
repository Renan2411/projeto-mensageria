package br.com.projetobase.domain.exception;

import br.com.projetobase.domain.exception.generic.GenericAuthenticationException;

public class TokenInvalidoException extends GenericAuthenticationException {

    public TokenInvalidoException() {
        super("Token Inválido!");
    }

}
