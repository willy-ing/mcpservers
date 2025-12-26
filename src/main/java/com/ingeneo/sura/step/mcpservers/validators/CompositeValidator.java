package com.ingeneo.sura.step.mcpservers.validators;

import java.util.List;

public class CompositeValidator<T> implements Validator<T> {

    private final List<Validator<T>> validators;

    public CompositeValidator(List<Validator<T>> validators) {
        this.validators = validators;
    }

    @Override
    public void validate(T target) {
        validators.forEach(v -> v.validate(target));
    }
}
