package com.example.obrestdata_jpa.Exceptions;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        String message,
        int status,
        LocalDateTime localDateTime
) {
}
