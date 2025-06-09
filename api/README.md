# ⚙️ engine 모듈 실행 가이드

---

## 🚀 실행 방법
### 0. Support profile
```angular2html
local, development, production
```
### 1. Gradle `bootRun`으로 실행하기 (개발용)

```bash
./gradlew :api:bootRun -Dspring-boot.run.profiles=local