<!-- Table of Contents -->
# :notebook_with_decorative_cover: Table of Contents

- [프로젝트 소개](#star2-프로젝트-소개)
  * [핵심기능](#dart-핵심기능)
  * [Tech Stack](#space_invader-tech-stack)
- [요구사항 분석 및 구현과정](#hugs-요구사항-분석-및-구현과정)
  * [DB 테이블 정의서](#book-DB-테이블-정의서)
  * [API 명세서](#computer-API-명세서)
  * [API 구현과정](#construction_worker-API-구현과정)

<!-- About the Project -->
## :star2: 프로젝트 소개
  
- 본 서비스는 기업의 채용을 위한 웹 서비스 백엔드 API 입니다.

<!-- Features -->
### :dart: 핵심기능

- 채용공고 등록
- 채용공고 수정
- 채용공고 삭제
- 채용공고 목록 조회
- 채용공고 키워드 검색
- 채용상세 페이지 조회
- 채용공고 지원

### :space_invader: Tech Stack

- Java 17
- SpringBoot 3.1.4
- Spring Data JPA
- Querydsl 5.0.0
- JUnit5
- Swagger 3.0.0
- MySQL 8.0.31

## :hugs: 요구사항 분석 및 구현과정

- 요구사항은 채용공고 등록, 수정, 삭제, 목록 조회, 키워드 검색, 채용상세 페이지 조회, 채용공고 지원입니다.
- 필수 기술요건 요구사항에 맞춰 Java/Spring 과 Spring Data JPA, MySQL을 사용했습니다.
- 요구사항(핵심기능)을 충족하기 위해 데이터베이스를 아래와 같이 모델링했습니다.

## :book: DB 테이블 정의서

- Recruitment : 채용공고 테이블 (채용공고id, 채용포지션, 채용보상금, 채용내용, 사용기술)
- Company : 회사 테이블 (회사id, 회사명, 국가, 지역)
- User : 유저 테이블 (유저id, 이메일)
- UserRecruitment : 유저 채용공고 테이블 (유저 채용공고id, 유저id, 채용공고id, 지원상태) 

### ERD
<details>
<summary><strong> Diagram </strong></summary>
<div markdown="1">       

![wanted-pre-be-erd](https://github.com/soonhankwon/wanted-pre-onboarding-backend/assets/113872320/d226ec24-4d73-4382-a3a3-8c4fc82d6f57)

</div>
</details>

## :computer: API 명세서

- [Swagger API 명세서](http://localhost:8080/swagger-ui/index.html)
  
    - 프로젝트 **애플리케이션 RUN** 후 링크를 클릭하면 확인 가능합니다.

### Swagger UI

- 위의 **스웨거 명세서 링크**를 통해 간단한 API 테스트가 가능합니다.

### Test Code

API 테스트 검증 및 자동화된 테스트를 위해 /test 경로에 **테스트 코드**를 작성했습니다.

- **도메인 단위 테스트 (Unit Test)**
    - JUnit5를 사용해서 단위테스트를 작성했습니다.
    - 대부분의 비즈니스 로직은 도메인 클래스 안에서 이루어지도록 **캡슐화**했습니다.
    - 경계범위값의 예외처리 테스트 코드 또한 작성되어있습니다.

## :construction_worker: API 구현과정

### 채용공고 등록
- JWT or Session을 사용하지 않는 경우임으로 Pathvariable로 companyId를 사용하도록 했습니다.
- RequestBody에 채용공고 등록요청 DTO를 파라미터로 받도록 구현했습니다.
<details>
<summary><strong> registerRecruitment - Controller</strong></summary>
<div markdown="1">       

```java
@PostMapping("/{companyId}")
    @Operation(summary = "채용공고 등록 API")
    public ResponseEntity<?> registerRecruitment(@PathVariable Long companyId,
                                                 @RequestBody RecruitmentRegisterRequest dto) {
        regularRecruitmentService.registerRecruitment(companyId, dto);
        return ResponseEntity.ok().body("채용공고 등록완료");
    }
```

</div>
</details>

- companyId 로 company 테이블 DB 조회 & 객체를 가져옵니다.
- dto 와 company(FK)로 recruitment(채용공고) 객체를 생성 및 DB에 저장하여 채용공고를 등록합니다.

---

### 채용공고 수정
- Pathvariable로 recruitmentId, companyId를 사용하도록 했습니다.
  * 채용공고를 수정하려면 해당 회사의 채용공고이어야 때문에 검증이 필요한 부분이 있기 때문입니다.
  * 해당 부분도 역시 JWT or Session을 사용한다면 companyId는 생략할 수 있는 부분입니다.
- RequestBody에 채용공고 수정요청 DTO를 파라미터로 받도록 구현했습니다.
<details>
<summary><strong> updateRecruitment - Controller</strong></summary>
<div markdown="1">       

```java
@PatchMapping("/{recruitmentId}/{companyId}")
    @Operation(summary = "채용공고 수정 API")
    public ResponseEntity<?> updateRecruitment(@PathVariable Long recruitmentId,
                                               @PathVariable Long companyId,
                                               @RequestBody RecruitmentUpdateRequest dto) {
        regularRecruitmentService.updateRecruitment(recruitmentId, companyId, dto);
        return ResponseEntity.ok().body("채용공고 수정완료");
    }
```

</div>
</details>

---

### 채용공고 삭제
- Pathvariable로 recruitmentId, companyId를 사용하도록 했습니다.
  * 채용공고를 삭제하려면 해당 회사의 채용공고이어야 때문에 검증이 필요한 부분이 있기 때문입니다.
<details>
<summary><strong> deleteRecruitment - Controller</strong></summary>
<div markdown="1">       

```java
@DeleteMapping("/{recruitmentId}/{companyId}")
    @Operation(summary = "채용공고 삭제 API")
    public ResponseEntity<?> deleteRecruitment(@PathVariable Long recruitmentId,
                                               @PathVariable Long companyId) {
        regularRecruitmentService.deleteRecruitment(recruitmentId, companyId);
        return ResponseEntity.ok().body("채용공고 삭제완료");
    }
```

</div>
</details>

---

### 채용공고 목록 조회
- 조회할 데이터량이 많고 이에따른 Latency가 클것으로 예상되는 API 이기 때문에 페이지네이션을 적용했습니다.
- Pageable 을 사용하며 ex)http://localhost:8080/page=0&size=10&sort=id,DESC 식으로 사용하며 size, sort 생략시 size10, sort는 id, DESC 기본값 입니다.
<details>
<summary><strong> getRecruitments - Controller</strong></summary>
<div markdown="1">       

```java
@GetMapping
    @Operation(summary = "채용공고 목록 조회 API",
            description = "ex)/page=0&size=10&sort=id,DESC => page, size 페이지네이션, sort 정렬이 가능, query param size, sort 생략시 기본값 size 10, sort id,DESC")
    public ResponseEntity<List<RecruitmentGetResponse>> getRecruitments(@Parameter(hidden = true) @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        List<RecruitmentGetResponse> allRecruitments = regularRecruitmentService.getAllRecruitments(pageable);
        return ResponseEntity.ok().body(allRecruitments);
    }
```

</div>
</details>

---

### 채용공고 키워드 검색
- RequestParam 으로 keyword (ex)/search?keyword="원티드") 를 사용합니다.
<details>
<summary><strong> searchRecruitments - Controller</strong></summary>
<div markdown="1">       

```java
@GetMapping("/search")
    @Operation(summary = "채용공고 키워드 검색 API")
    public ResponseEntity<List<RecruitmentGetResponse>> searchRecruitments(@RequestParam String keyword) {
        List<RecruitmentGetResponse> searchRecruitments = regularRecruitmentService.searchRecruitments(keyword);
        return ResponseEntity.ok().body(searchRecruitments);
    }
```

</div>
</details>

- 처음에 Jpa Criteria 로 구현했다가 보다 가독성이 좋고 활용도가 높은 Querydsl 로 수정 및 개선한 API 입니다.
- LIKE 구문을 사용해서 키워드에 매칭되는 채용공고를 검색하며 Querydsl 의 contains(%LIKE%)를 활용해서 구현했습니다.
- 현재는 요구사항 예시에 맞는 컬럼에만 LIKE 구문이 적용되어있습니다.
<details>
<summary><strong> findRecruitmentsByKeyword - Querydsl</strong></summary>
<div markdown="1">       

```java
@Override
    public List<Recruitment> findRecruitmentsByKeyword(String keyword) {
        return queryFactory.select(recruitment)
                .from(recruitment)
                .join(company).on(recruitment.company.eq(company))
                .where(recruitment.requiredTech.contains(keyword)
                        .or(recruitment.position.contains(keyword))
                        .or(company.name.contains(keyword)))
                .fetch();
    }
```

</div>
</details>

---

### 채용상세 페이지 조회
- Pathvariable로 recruitmentId를 사용하도록 했습니다.
<details>
<summary><strong> getRecruitmentDetail - controller</strong></summary>
<div markdown="1">       

```java
@GetMapping("/{recruitmentId}")
    @Operation(summary = "채용공고 상세페이지 조회 API")
    public ResponseEntity<RecruitmentDetailGetResponse> getRecruitmentDetail(@PathVariable Long recruitmentId) {
        RecruitmentDetailGetResponse detailResponse = regularRecruitmentService.getRecruitmentDetail(recruitmentId);
        return ResponseEntity.ok().body(detailResponse);
    }
```

</div>
</details>

- 응답데이터에 해당 회사의 다른 채용공고 id들을 추가적으로 포함하기 위해 Querydsl을 사용했습니다.
- 해당 채용공고의 company fk를 사용해서 company 테이블과 join 그리고 해당 채용공고의 회사와 같은 채용공고 id들을 가져옵니다.
  * 현재 채용공고의 id는 제외하고 가져옵니다.
<details>
<summary><strong> findRelatedRecruitmentsIdsByCompany - Querydsl</strong></summary>
<div markdown="1">       

```java
@Override
    public List<Long> findRelatedRecruitmentsIdsByCompany(Recruitment recruitmentNotice) {
        return queryFactory.select(recruitment.id)
                .from(recruitment)
                .join(company).on(recruitment.company.eq(company))
                .where(
                        recruitment.company.eq(recruitmentNotice.getCompany())
                                .and(recruitment.id.ne(recruitmentNotice.getId())))
                .fetch();
    }
```

</div>
</details>

---

### 채용공고 지원
- Pathvariable로 recruitmentId를 사용하도록 했습니다.
- RequestBody에 채용공고 지원요청 DTO(유저 이메일)를 파라미터로 받도록 구현했습니다.
<details>
<summary><strong> applyRecruitment - controller</strong></summary>
<div markdown="1">       

```java
@PostMapping("/{recruitmentId}/apply")
    @Operation(summary = "채용공고 지원 API")
    public ResponseEntity<?> applyRecruitment(@PathVariable Long recruitmentId,
                                              @RequestBody RecruitmentApplyRequest dto) {
        regularRecruitmentService.applyRecruitment(recruitmentId, dto);
        return ResponseEntity.ok().body("채용공고 지원완료");
    }
```

</div>
</details>

- 유저의 채용공고 지원은 UserRecruitment 라믄 User - Recruitment 간 N : N 관계의 중간 테이블로 구현했습니다.
- Status Enum 클래스를 통해 지원여부 상태를 구분합니다.
- 이미 지원한 상태라면 지원하지 못하도록 예외처리가 적용되어 있습니다.
<details>
<summary><strong> applyRecruitment - controller</strong></summary>
<div markdown="1">       

```java
public void apply(UserRecruitment userRecruitment) {
        if(existsByUserRecruitment(userRecruitment)) {
            throw new ApiException(CustomErrorCode.ALREADY_EXISTS_APPLYING);
        }
        userRecruitments.add(userRecruitment);
    }

    private boolean existsByUserRecruitment(UserRecruitment userRecruitment) {
        return this.userRecruitments.contains(userRecruitment);
    }
```

</div>
</details>
