package dev.wantedpreonboardingbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {

    COMPANY_NOT_FOUND_INVALID_ID("회사를 DB 에서 찾을수 없습니다. companyId path 확인 필요"),
    RECRUITMENT_NOT_MATCHES_COMPANY_ID("채용공고와 company Id가 일치하지 않습니다. 채용공고와 companyId path 매치 확인 필요");

    private final String message;
}
