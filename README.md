## 프로젝트 소개

---

<aside>
  
- 본 서비스는 기업의 채용을 위한 웹 서비스 백엔드 API 입니다.

</aside>

## 핵심 기능

---
- 채용공고 등록
- 채용공고 수정
- 채용공고 삭제
- 채용공고 목록 조회
- 채용공고 키워드 검색
- 채용상세 페이지 조회
- 채용공고 지원
---

## TECK STACK

---

- Java 17
- SpringBoot 3.1.4
- Spring Data JPA
- Querydsl 5.0.0
- JUnit5
- Swagger 3.0.0
- MySQL 8.0.31

---

## DB 테이블 정의서

---

### ERD
<details>
<summary><strong> Diagram </strong></summary>
<div markdown="1">       

![dev-space-erd](https://github.com/soonhankwon/coffee-plz-backend/assets/113872320/3c8b524e-783d-4b3d-8064-954cc53621c2)

</div>
</details>

## API

---

- [Swagger API 명세서](http://localhost:8080/swagger-ui/index.html)
  
    - 프로젝트 **애플리케이션 RUN** 후 링크를 클릭하면 확인 가능합니다.

## API TEST

---

### Swagger UI

- 위의 **스웨거 명세서 링크**를 통해 간단한 API 테스트가 가능합니다.

### Test Code

API 테스트 검증 및 자동화된 테스트를 위해 /test 경로에 **테스트 코드**를 작성했습니다.

- **도메인 단위 테스트 (Unit Test)**
  
    - 대부분의 비즈니스 로직은 도메인 클래스 안에서 이루어지도록 **캡슐화**했습니다.
    - 모든 메서드의 **단위 테스트**를 진행합니다.
    - 경계범위값의 예외처리 테스트 코드 또한 작성되어있습니다.

## 요구사항 분석 및 구현 과정
---
