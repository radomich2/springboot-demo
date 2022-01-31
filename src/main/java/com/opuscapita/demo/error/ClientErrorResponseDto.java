package com.opuscapita.demo.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClientErrorResponseDto {
    private String errorCode;
    private String details;
}
