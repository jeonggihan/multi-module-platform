# Git 브랜치 전략 가이드

## 1. 브랜치 개요

| 브랜치                            | 용도                                  |
|--------------------------------|-------------------------------------|
| `main`                         | 운영 배포용 브랜치. 항상 안정 상태 유지             |
| `release/YYYY-MM-DD-{version}` | 릴리즈 대상 코드 집합. main 기준으로 생성          |
| `dev`                          | 기능 통합 및 QA 검증용 브랜치                  |
| `feature/{ISSUE_KEY}-{desc}`   | Jira 이슈 기반 개발 브랜치 (각 개발자 fork에서 생성) |

---

## 2. 브랜치 흐름

```
main ──┬────────▶ release/2025-05-23-1.0.0 ─────────▶ main (배포)
       │
       └──▶ feature/ABC-123-login-api (fork)
                  ├── PR → dev
                  └── PR → release/2025-05-23-1.0.0
```

- release 브랜치는 항상 `main` 기준으로 생성한다.
- 각 개발자는 **fork 저장소**에서 `feature` 브랜치를 생성하고,
  동일한 브랜치로 `dev`와 `release` 브랜치에 각각 PR을 보낸다.
- QA 및 테스트 완료 후 PR 병합.

---

## 3. 브랜치 명명 규칙

| 브랜치 유형  | 형식                   |
|---------|----------------------|
| Release | `release/YYYY-MM-DD` |
| Feature | `feature/{JIRA_KEY}` |
| Hotfix  | `hotfix/{JIRA_KEY}`  |

---

## 4. Release 브랜치 생성

운영 릴리즈를 위한 브랜치는 항상 `main` 기준으로 생성한다.

### 생성 예시

```bash
git checkout main 
git pull origin main 
git checkout -b release/2025-05-23-1.0.0
git push origin release/2025-05-23-1.0.0
```

---

## 5. 개발자 작업 흐름

1. Fork 저장소에서 Jira 기반 브랜치 생성

```bash
git checkout -b feature/ABC-123-login-api 
```

2. 기능 개발 및 커밋 후 Push

3. PR 생성
    - PR 1: `feature/*` → `dev`
    - PR 2: `feature/*` → `release/YYYY-MM-DD-{version}`

4. 코드 리뷰 및 QA

5. 기능 정상 작동 시 두 브랜치에 병합

6. 릴리즈 시점  
   `release/YYYY-MM-DD` → `main` 병합 및 운영 배포

---

## 6. Hotfix 처리

운영 중 긴급 이슈 발생 시, `hotfix` 브랜치를 사용한다.

```
main ───▶ hotfix/ABC-999-critical-fix
                 ├── PR → release/2025-05-23-1.0.0
                 └── PR → dev
```

---

## 7. 브랜치 보호 설정 권장

| 브랜치         | 보호 규칙                             |
|-------------|-----------------------------------|
| `main`      | 리뷰 1건 이상, CI 통과 필수, force push 금지 |
| `release/*` | 리뷰, CI 통과 필수                      |
| `dev`       | 리뷰, CI 권장 필수                      |

---

## 8. FAQ

**Q. release 브랜치는 dev 기준인가요?**  
A. 아니요, 항상 `main` 기준입니다.

**Q. feature 브랜치는 dev와 release 둘 다에 PR을 올려야 하나요?**  
A. 네, QA와 릴리즈 타이밍을 병렬로 관리하기 위해 반드시 둘 다에 올려야 합니다.
