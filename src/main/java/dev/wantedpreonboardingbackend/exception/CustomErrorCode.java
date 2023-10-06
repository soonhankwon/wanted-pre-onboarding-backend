package dev.wantedpreonboardingbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {

    COMPANY_NOT_FOUND_INVALID_ID("회사를 DB 에서 찾을수 없습니다. companyId path 확인 필요");

    private final String message;
}
