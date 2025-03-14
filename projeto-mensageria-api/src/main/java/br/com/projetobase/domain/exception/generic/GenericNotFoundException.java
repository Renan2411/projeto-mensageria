package br.com.projetobase.domain.exception.generic;

import lombok.Getter;

import java.util.List;

@Getter
public class GenericNotFoundException extends RuntimeException {

    private final List<String> args;

    public GenericNotFoundException(String message, List<String> args) {
        super(message);
        this.args = args;
    }

    public GenericNotFoundException(String message) {
        super(message);
        this.args = null;
    }
}
