package com.opuscapita.demo.error;

import java.util.List;

public class ValidationErrorResponseDto extends ErrorResponseDto {

    private List<ValidationErrorDto> errors;

    public ValidationErrorResponseDto(String message, List<ValidationErrorDto> errors) {
        super(message);
        this.errors = errors;
    }

    public List<ValidationErrorDto> getErrors() {
        return errors;
    }
}
