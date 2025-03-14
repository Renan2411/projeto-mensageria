package br.com.projetobase.domain.exception.generic;

import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GenericValidationExceptionList extends RuntimeException {

    private final List<GenericValidationException> exceptions;

    public GenericValidationExceptionList(String message, List<GenericValidationException> exceptions) {
        super(message);
        this.exceptions = exceptions;
    }

    public GenericValidationExceptionList(List<Throwable> throwables) {
        super("");
        this.exceptions = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(throwables)) {
            throwables.forEach(throwable -> this.exceptions.add(new GenericValidationException(throwable)));
        }
    }

}
