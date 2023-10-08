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
@Override
    @Transactional
    public void registerRecruitment(Long companyId, RecruitmentRegisterRequest dto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ApiException(CustomErrorCode.COMPANY_NOT_FOUND_INVALID_ID));
        Recruitment recruitment = new Recruitment(dto, company);
        recruitmentRepository.save(recruitment);
    }
```

</div>
</details>

- companyId 로 company 테이블 DB 조회 & 객체를 가져옵니다.
- dto 와 company(FK)로 recruitment(채용공고) 객체를 생성 및 DB에 저장하여 채용공고를 등록합니다.

### 채용공고 수정
- Pathvariable로 recruitmentId, companyId를 사용하도록 했습니다.
  * 채용공고를 수정하려면 해당 회사의 채용공고이어야 때문에 검증이 필요한 부분이 있기 때문입니다.
  * 해당 부분도 역시 JWT or Session을 사용한다면 companyId는 생략할 수 있는 부분입니다.
- RequestBody에 채용공고 수정요청 DTO를 파라미터로 받도록 구현했습니다.
<details>
<summary><strong> registerRecruitment - Controller</strong></summary>
<div markdown="1">       

```java
@Override
    @Transactional
    public void updateRecruitment(Long recruitmentId, Long companyId, RecruitmentUpdateRequest dto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ApiException(CustomErrorCode.COMPANY_NOT_FOUND_INVALID_ID));
        Recruitment recruitment = recruitmentRepository.findByIdAndCompany(recruitmentId, company)
                .orElseThrow(() -> new ApiException(CustomErrorCode.RECRUITMENT_NOT_MATCHES_COMPANY_ID));

        recruitment.update(dto);
    }
```

</div>
</details>
