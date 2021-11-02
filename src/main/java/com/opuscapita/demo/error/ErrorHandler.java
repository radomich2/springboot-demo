package com.opuscapita.demo.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponseDto onValidationException(HttpServletRequest request, MethodArgumentNotValidException e) {

        String errorStr = "Input validation failed!";
        log.debug(formatErrorStr(errorStr, request));
        List<ValidationErrorDto> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach((error) -> {
            errors.add(new ValidationErrorDto(
                error.getField(),
                error.getDefaultMessage()));
        });
        return new ValidationErrorResponseDto(errorStr, errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto onOtherExceptions(HttpServletRequest request, Exception e) {

        String errorStr = errorToString(e);
        log.error(formatErrorStr(errorStr, request), e);
        var responseDto = new ErrorResponseDto(errorStr);
        request.setAttribute(AppErrorController.ERROR_RESPONSE_ATTR, responseDto);
        return responseDto;
    }

    private String formatErrorStr(String errorMessage, HttpServletRequest request) {
        return String.format(
            "Error handled at [%s %s]: %s",
            request.getMethod(),
            request.getServletPath(),
            errorMessage);
    }

    private String errorToString(Exception e) {
        return e.getClass().getSimpleName() + '(' + e.getMessage() + ')';
    }
}
