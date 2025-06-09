# 📦 Multi-Module Spring Boot Project

[Git branch 전략 보기](./GIT_BRANCH.md)

[코드 컨벤션(Code Convention) 보기](./CODE_CONVENTION.md)

> 이 프로젝트는 Spring Boot 기반의 멀티 모듈 구조로 설계.  
> 공통 유틸성과 공통 로직을 명확히 분리하여 유지보수성과 재사용성을 극대화.
---
## 🚀 주요 기술 스택
- **Java 21 LTS**
- **Spring Boot 3.5.0**
- Gradle 멀티 모듈 빌드 시스템
- Lombok (공통 설정 적용)
---
## 🧱 모듈 구성
```text
root-project/
├── core       # 공통 상수, enum, 단순 유틸 클래스 (순수 Java)
├── common     # Spring 의존되는 공용로직 (entity 등)
├── api        # REST API 광고 관련 서비스 API
├── engine     # 광고엔진
├── build.gradle
├── settings.gradle
└── README.md

