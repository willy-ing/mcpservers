package com.ingeneo.sura.step.mcpservers.validators;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class DateValidator<T> implements Validator<T> {

    private final Function<T, String> extractor;
    private final String fieldName;

    public DateValidator(Function<T, String> extractor, String fieldName) {
        this.extractor = extractor;
        this.fieldName = fieldName;
    }

    @Override
    public void validate(T target) {
        try {
            LocalDateTime.parse(extractor.apply(target));
        } catch (Exception e) {
            throw new ValidationException(
                    "El campo '" + fieldName + "' no tiene un formato de fecha válido (YYYY-MM-DD)"
            );
        }
    }
}
