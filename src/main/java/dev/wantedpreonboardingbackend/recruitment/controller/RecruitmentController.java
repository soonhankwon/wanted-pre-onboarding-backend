package dev.wantedpreonboardingbackend.recruitment.controller;

import dev.wantedpreonboardingbackend.recruitment.controller.dto.RecruitmentRegisterRequest;
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
}
