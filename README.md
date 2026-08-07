# Morrow Wellness

Apple Watch, iPhone, PC 웹을 잇는 크로스디바이스 AI 웰니스 어시스턴트입니다.

- Apple Watch: HealthKit 데이터 기반의 빠른 상태 체크인과 짧은 입력
- iPhone: HealthKit 권한·동기화, 개인 기준선 분석, 타임라인, 행동 추천
- PC 웹: 일간·주간 리포트, 패턴 분석, AI 대화, 기록 관리
- Java API: 사용자 상태, 타임라인, 추천, 피드백과 삭제 정책을 제공

이 서비스는 의료 진단이나 치료를 제공하지 않습니다. 생체 신호만으로 상태를 단정하지 않고 사용자의 직접 입력을 함께 반영합니다.

## 폴더 구조

```text
apps/
  web/       React + Vite PC 대시보드
  ios/       SwiftUI iPhone 앱 소스
  watch/     SwiftUI watchOS 앱 소스
backend/     Java 21 + Spring Boot API
docs/        아키텍처, API, MVP, 안전 정책
infra/       PostgreSQL 로컬 환경
```

## 빠른 실행

웹:

```bash
cd apps/web
npm install
npm run dev
```

백엔드:

```bash
cd backend
# AI 기능 활성화 (선택)
cp .env.example .env
# .env 파일에서 OPENAI_API_KEY 설정

mvn spring-boot:run
```

- 웹: http://localhost:5173
- API: http://localhost:8080/api/v1
- API 상태: http://localhost:8080/actuator/health

웹은 API가 실행 중이면 실제 API를 사용하고, 꺼져 있으면 데모 데이터로 동작합니다.

### AI 어시스턴트 설정

AI 대화 기능을 사용하려면 OpenAI API 키가 필요합니다:

1. [OpenAI API](https://platform.openai.com/)에서 API 키 발급
2. `backend/.env` 파일 생성 및 키 설정
3. `OPENAI_ENABLED=true` 설정

API 키 없이도 기본 안내 메시지로 동작합니다. 자세한 내용은 [AI 어시스턴트 문서](docs/ai-assistant.md)를 참고하세요.

## iOS 및 Watch

Xcode에서 iOS App 프로젝트와 Watch App 타깃을 만든 뒤 `apps/ios/Sources`, `apps/watch/Sources` 파일을 각각 추가합니다. HealthKit capability와 사용 목적 문구를 설정해야 합니다. 자세한 내용은 [iOS/watchOS 설정](docs/ios-watch-setup.md)을 참고하세요.

## 문서

- [MVP 범위](docs/mvp.md)
- [아키텍처](docs/architecture.md)
- [API 계약](docs/api.md)
- [AI 어시스턴트](docs/ai-assistant.md)
- [안전 및 개인정보](docs/safety-privacy.md)

## 팀 개발

모든 작업은 기능 브랜치에서 진행하고 Pull Request로 `main`에 병합합니다. PR이 열리면 PC 웹 빌드와 Java 백엔드 테스트가 자동으로 실행됩니다.

- [기여 및 브랜치·PR 가이드](CONTRIBUTING.md)
- [Pull Request 템플릿](.github/pull_request_template.md)
- 기능 제안·버그 제보용 이슈 템플릿
- React 빌드와 Spring Boot 테스트용 GitHub Actions CI
