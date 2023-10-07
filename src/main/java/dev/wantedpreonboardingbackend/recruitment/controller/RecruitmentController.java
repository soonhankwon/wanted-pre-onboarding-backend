package dev.wantedpreonboardingbackend.recruitment.controller;

import dev.wantedpreonboardingbackend.recruitment.controller.dto.*;
import dev.wantedpreonboardingbackend.recruitment.service.RecruitmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitments")
@Tag(name = "채용공고 관련 API")
public class RecruitmentController {

    private final RecruitmentService regularRecruitmentService;

    @PostMapping("/{companyId}")
    @Operation(summary = "채용공고 등록 API")
    public ResponseEntity<?> registerRecruitment(@PathVariable Long companyId,
                                                 @RequestBody RecruitmentRegisterRequest dto) {
        regularRecruitmentService.registerRecruitment(companyId, dto);
        return ResponseEntity.ok().body("채용공고 등록완료");
    }

    @PatchMapping("/{recruitmentId}/{companyId}")
    @Operation(summary = "채용공고 수정 API")
    public ResponseEntity<?> updateRecruitment(@PathVariable Long recruitmentId,
                                               @PathVariable Long companyId,
                                               @RequestBody RecruitmentUpdateRequest dto) {
        regularRecruitmentService.updateRecruitment(recruitmentId, companyId, dto);
        return ResponseEntity.ok().body("채용공고 수정완료");
    }

    @DeleteMapping("/{recruitmentId}/{companyId}")
    @Operation(summary = "채용공고 삭제 API")
    public ResponseEntity<?> deleteRecruitment(@PathVariable Long recruitmentId,
                                               @PathVariable Long companyId) {
        regularRecruitmentService.deleteRecruitment(recruitmentId, companyId);
        return ResponseEntity.ok().body("채용공고 삭제완료");
    }

    @GetMapping
    @Operation(summary = "채용공고 목록 조회 API",
            description = "ex)/page=0&size=10&sort=id,DESC => page, size 페이지네이션, sort 정렬이 가능, query param size, sort 생략시 기본값 size 10, sort id,DESC")
    public ResponseEntity<List<RecruitmentGetResponse>> getRecruitments(@Parameter(hidden = true) @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        List<RecruitmentGetResponse> allRecruitments = regularRecruitmentService.getAllRecruitments(pageable);
        return ResponseEntity.ok().body(allRecruitments);
    }

    @GetMapping("/search")
    @Operation(summary = "채용공고 키워드 검색 API")
    public ResponseEntity<List<RecruitmentGetResponse>> searchRecruitments(@RequestParam String keyword) {
        List<RecruitmentGetResponse> searchRecruitments = regularRecruitmentService.searchRecruitments(keyword);
        return ResponseEntity.ok().body(searchRecruitments);
    }

    @GetMapping("/{recruitmentId}")
    @Operation(summary = "채용공고 상세페이지 조회 API")
    public ResponseEntity<RecruitmentDetailGetResponse> getRecruitmentDetail(@PathVariable Long recruitmentId) {
        RecruitmentDetailGetResponse detailResponse = regularRecruitmentService.getRecruitmentDetail(recruitmentId);
        return ResponseEntity.ok().body(detailResponse);
    }

    @PostMapping("/{recruitmentId}/apply")
    @Operation(summary = "채용공고 지원 API")
    public ResponseEntity<?> applyRecruitment(@PathVariable Long recruitmentId,
                                              @RequestBody RecruitmentApplyRequest dto) {
        regularRecruitmentService.applyRecruitment(recruitmentId, dto);
        return ResponseEntity.ok().body("채용공고 지원완료");
    }
}
