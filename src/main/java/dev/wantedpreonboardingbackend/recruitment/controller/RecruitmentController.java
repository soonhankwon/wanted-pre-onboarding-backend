package dev.wantedpreonboardingbackend.recruitment.controller;

import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentRegisterRequest;
import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentUpdateRequest;
import dev.wantedpreonboardingbackend.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitments")
public class RecruitmentController {

    private final RecruitmentService regularRecruitmentService;

    @PostMapping("/{companyId}")
    public ResponseEntity<?> registerRecruitment(@PathVariable Long companyId,
                                                 @RequestBody RecruitmentRegisterRequest dto) {
        regularRecruitmentService.registerRecruitment(companyId, dto);
        return ResponseEntity.ok().body("채용공고 등록완료");
    }

    @PatchMapping("/{recruitmentId}/{companyId}")
    public ResponseEntity<?> updateRecruitment(@PathVariable Long recruitmentId,
                                               @PathVariable Long companyId,
                                               @RequestBody RecruitmentUpdateRequest dto) {
        regularRecruitmentService.updateRecruitment(recruitmentId, companyId, dto);
        return ResponseEntity.ok().body("채용공고 수정완료");
    }

    @DeleteMapping("/{recruitmentId}/{companyId}")
    public ResponseEntity<?> deleteRecruitment(@PathVariable Long recruitmentId,
                                               @PathVariable Long companyId) {
        regularRecruitmentService.deleteRecruitment(recruitmentId, companyId);
        return ResponseEntity.ok().body("채용공고 삭제완료");
    }
}
