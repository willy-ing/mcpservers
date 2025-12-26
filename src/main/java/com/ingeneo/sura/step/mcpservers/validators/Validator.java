package com.ingeneo.sura.step.mcpservers.validators;

public interface Validator<T> {
    void validate(T target) throws ValidationException;
}
