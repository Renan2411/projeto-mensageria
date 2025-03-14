package br.com.projetobase.domain.exception.generic;

import lombok.Getter;

import java.util.List;

@Getter
public class GenericValidationException extends RuntimeException{

    private final List<String> args;

    public GenericValidationException(String message){
        super(message);
        args = null;
    }

    public GenericValidationException(String message, List<String> args) {
        super(message);
        this.args = args;
    }

    public GenericValidationException(Throwable throwable) {
        super(throwable.getMessage() != null ? throwable.getMessage() : "", throwable);
        this.args = null;
    }


}
