# Java Backend Code Style Guide

## 1. 기본 코드 스타일

| 항목       | 규칙                            |
|----------|-------------------------------|
| 인덴트      | 공백 4칸                         |
| 줄바꿈      | Unix 스타일                      |
| 줄 길이 제한  | 120자 이하 (권장), 예외적으로 200자까지 허용 |
| 중괄호 `{}` | 항상 새 줄에 작성                    |

---

## 2. 패키지 구조 (DDD 기반)

```
com.example.api
├── common
├── component
├── configuration
├── domain
│   ├── auth
│   │   ├── dto
│   │   ├── repository
│   │   ├── service
│   │   └── controller
│   └── member
│       ├── dto
│       ├── repository
│       ├── service
│       └── controller
├── exception
├── filter
├── variable
└── util
```

---

## 3. 클래스 & 메서드 명명 규칙

| 항목       | 규칙                                              |
|----------|-------------------------------------------------|
| 클래스 이름   | 의인화된 명사 사용 (예: `OrderManager`, `UserFinder`)    |
| 인터페이스 이름 | 기능 중심 이름 사용 (예: `OrderService`, `UserReader`)   |
| 메서드 이름   | 동사 사용, 동작을 명확히 표현 (`findUserById`, `saveOrder`) |
| 변수 이름    | `camelCase`, 의미 있는 명사 (`userId`, `createdAt`)   |
| 상수 이름    | `UPPER_SNAKE_CASE` (`MAX_RETRY_COUNT`)          |

---

## 4. 주석 규칙

- 주석은 **"무엇"보다는 "왜"**에 집중
- `// TODO`, `// FIXME` 주석 활용 권장

---

## 5. Lombok 사용 규칙

- `@Getter`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor` 주로 사용
- **Entity 클래스에서는 `@Setter` 금지**, 변경은 명시적 메서드로 처리

---

## 6. Spring 관련 규칙

| 항목            | 규칙                                                   |
|---------------|------------------------------------------------------|
| Controller    | `@RestController` + `@RequestMapping("/api/v1/...")` |
| Service       | `@Service`, 비즈니스 로직만 담당                              |
| Repository    | `@Repository`, JPA 또는 QueryDSL 기반                    |
| Configuration | `@Configuration` + `@Enable...` 계열은 명확히 목적별 분리       |

---

## 7. 예외 처리

### 🔹 공통 Exception 구조

```java
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
}
```

```java
public enum ErrorCode {
    INVALID_INPUT(400, "잘못된 요청입니다"),
    ...
}
```

### 🔹 Global Exception Handler

```java

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handle(BusinessException e) {
        ...
    }
}
```

---

## 8. 테스트 코드

- `JUnit5` + `AssertJ` + `Mockito` 조합 사용
- 테스트 클래스 명은 `XxxTest`
- 테스트 메서드는 `given_조건_when_행동_then_결과` 형식 권장

```java

@DisplayName("사용자 ID로 조회 시 유저 정보를 반환한다")
@Test
void givenUserId_whenFindUser_thenReturnUser() {
    ...
}
```

---

## 9. QueryDSL 사용 규칙

- `Q타입`은 직접 `new` 하지 않고 `QClass.class` 사용
- `BooleanBuilder`보다 `where(..., ...)` 사용 권장
- `null`인 조건은 `nullSafeBuilder()` 또는 `booleanExpression != null` 방식으로 처리

---

## 10. 기타 Best Practice

- `Builder 패턴` 적극 활용
- `DTO`와 `Entity`는 명확히 분리
- `Request`/`Response` 객체는 `dto` 패키지에 별도 분리
- Jackson 전역 설정은 `camelCase`만 사용 (snake_case 혼용 금지)

---

> ✨ 이 스타일 가이드는 팀 내 일관된 개발 환경을 위한 참고 기준입니다.
