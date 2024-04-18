package com.anjoe.SpringJPA.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse<T> {
    private HttpStatus status;
    private String message;
}
