package br.com.projetobase.domain.validation;

import br.com.projetobase.domain.exception.generic.GenericValidationExceptionList;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Validator<T> {

    private final T t;
    private final List<Throwable> exceptions = new ArrayList<>();

    public Validator(T t) {
        this.t = t;
    }

    public static <T> Validator<T> of(T t){
        return new Validator<>(t);
    };

    public Validator<T> validate(boolean assertion, String message){
        if(!assertion){
            exceptions.add(new IllegalStateException(message));
        }

        return this;
    }

    public T get(){
        if(CollectionUtils.isNotEmpty(exceptions)){
            throw new GenericValidationExceptionList(exceptions);
        }

        return t;
    }

    public Validator<T> check(){
        if(CollectionUtils.isNotEmpty(exceptions)){
            throw new GenericValidationExceptionList(exceptions);
        }

        return this;
    }

}
