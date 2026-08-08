# Morrow Wellness

Apple Watch, iPhone, PC 웹을 잇는 크로스디바이스 AI 웰니스 어시스턴트입니다. 생체 신호와 짧은 자기 보고를 결합해 사용자가 지금 할 수 있는 작은 회복 행동을 설명 가능한 형태로 제안합니다.

- Apple Watch: 오늘 회복 점수·건강 신호·추천 확인, 2단계 체크인, 1분 호흡 회복, 최근 기록
- iPhone: HealthKit 권한·동기화, 개인 기준선 분석, 타임라인, 행동 추천
- PC 웹: 실시간 상태 보드, 주간 패턴, 음성 지원 AI 대화, 기록·개인정보 관리
- 개인화 학습: 체크인과 추천 피드백을 설명 가능한 장기 메모리로 누적하고, 다음 추천과 AI 답변에 반영
- Java API: 체크인→타임라인→추천 파이프라인, 사용자 격리, 피드백과 전체 삭제 제공

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
백엔드는 첫 실행 시 발표용 샘플 데이터를 메모리 DB에 채웁니다. 빈 상태로 시작하려면 `MORROW_DEMO_SEED=false`를 설정하세요.

## 완성된 핵심 흐름

1. Watch·iPhone·웹에서 상태와 원인을 체크인합니다.
2. 체크인이 사용자 타임라인에 기록되고, 원인에 맞는 행동 추천이 즉시 생성됩니다.
3. 추천의 근거와 예상 시간을 확인하고 도움 여부를 피드백합니다.
4. 주간 리포트에서 반복되는 상태·원인 패턴을 확인합니다.
5. 개인정보 센터에서 사용자 데이터 전체를 즉시 삭제할 수 있습니다.

API 없이도 전체 UX를 시연할 수 있고, API가 연결되면 화면 상단에 `LIVE API` 상태가 표시됩니다.

### AI 어시스턴트 설정

AI 대화 기능을 사용하려면 OpenAI API 키가 필요합니다:

1. [OpenAI API](https://platform.openai.com/)에서 API 키 발급
2. `backend/.env` 파일 생성 및 키 설정
3. `OPENAI_ENABLED=true` 설정

기본 모델은 환경 변수로 설정할 수 있습니다. AI 기능을 끈 상태에서도 안전 규칙이 적용된 로컬 응답으로 핵심 흐름을 시연할 수 있습니다.

로컬 기본 DB는 `backend/data/`의 영구 H2 파일을 사용하므로 서버 재시작 후에도 사용자별 체크인, 대화, 피드백, 개인화 메모리가 유지됩니다. 배포 환경에서는 `DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD`로 PostgreSQL을 연결할 수 있습니다.

API 키 없이도 기본 안내 메시지로 동작합니다. 자세한 내용은 [AI 어시스턴트 문서](docs/ai-assistant.md)를 참고하세요.

## iOS 및 Watch

`apps/apple/Morrow.xcodeproj`에 iOS·watchOS 타깃이 함께 구성되어 있습니다. HealthKit capability와 사용 목적 문구, 실제 기기 서명만 확인하면 됩니다. 자세한 내용은 [iOS/watchOS 설정](docs/ios-watch-setup.md)을 참고하세요.

## 문서

- [MVP 범위](docs/mvp.md)
- [아키텍처](docs/architecture.md)
- [API 계약](docs/api.md)
- [AI 어시스턴트](docs/ai-assistant.md)
- [안전 및 개인정보](docs/safety-privacy.md)
- [3분 해커톤 데모 스크립트](docs/demo-script.md)

## 팀 개발

모든 작업은 기능 브랜치에서 진행하고 Pull Request로 `main`에 병합합니다. PR이 열리면 PC 웹 빌드와 Java 백엔드 테스트가 자동으로 실행됩니다.

- [기여 및 브랜치·PR 가이드](CONTRIBUTING.md)
- [Pull Request 템플릿](.github/pull_request_template.md)
- 기능 제안·버그 제보용 이슈 템플릿
- React 빌드와 Spring Boot 테스트용 GitHub Actions CI
