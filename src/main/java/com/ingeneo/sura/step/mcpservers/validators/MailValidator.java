package com.ingeneo.sura.step.mcpservers.validators;

import java.util.function.Function;
import java.util.regex.Pattern;

public class MailValidator<T> implements Validator<T> {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE
    );

    private final Function<T, String> extractor;
    private final String fieldName;

    public MailValidator(Function<T, String> extractor, String fieldName) {
        this.extractor = extractor;
        this.fieldName = fieldName;
    }

    @Override
    public void validate(T target) {
        String email = extractor.apply(target);

        if (email == null || email.isBlank()) {
            throw new ValidationException(
                    "El campo '" + fieldName + "' es obligatorio"
            );
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException(
                    "El campo '" + fieldName + "' no tiene un formato de correo válido"
            );
        }
    }
}
