package com.ingeneo.sura.step.mcpservers.validators;

import java.util.function.Function;

public class NotBlankValidator<T> implements Validator<T> {

    private final Function<T, String> extractor;
    private final String fieldName;

    public NotBlankValidator(Function<T, String> extractor, String fieldName) {
        this.extractor = extractor;
        this.fieldName = fieldName;
    }

    @Override
    public void validate(T target) {
        String value = extractor.apply(target);
        if (value == null || value.isBlank()) {
            throw new ValidationException("El campo '" + fieldName + "' es obligatorio");
        }
    }
}
