package dev.wantedpreonboardingbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {

    private final CustomErrorCode customErrorCode;
}
