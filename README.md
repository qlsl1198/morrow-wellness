# Morrow Documentation

Morrow는 Apple Watch, iPhone, 웹을 연결해 생체 신호와 짧은 자기 보고를 함께 해석하고, 사용자가 지금 실행할 수 있는 작은 회복 행동을 제안하는 AI 웰니스 서비스입니다.

이 저장소는 Morrow의 제품·아키텍처·API·안전 정책을 관리하는 **문서 전용 저장소**입니다. 실행 가능한 프론트엔드와 백엔드 소스는 각각의 저장소에서 관리합니다.

## 저장소

| 저장소 | 역할 | 주요 기술 |
| --- | --- | --- |
| [morrow-frontend](https://github.com/AISH-Official/morrow-frontend) | React 웹, SwiftUI iPhone·Apple Watch 앱 | React, TypeScript, SwiftUI, HealthKit, WatchConnectivity |
| [morrow-backend](https://github.com/AISH-Official/morrow-backend) | API, AI 개인화, 건강 데이터 동기화, 알림 | Java 21, Spring Boot, PostgreSQL, OpenAI API, APNs |
| [morrow-docs](https://github.com/AISH-Official/morrow-docs) | 제품 사양, 아키텍처, API 계약, 안전·데모 문서 | Markdown, GitHub Actions |

웹 데모: [aish-official.github.io/morrow-frontend](https://aish-official.github.io/morrow-frontend/)

## 제품 흐름

1. Apple Watch와 iPhone이 HealthKit 요약 및 사용자의 체크인을 수집합니다.
2. iPhone이 같은 사용자 세션으로 데이터를 백엔드에 동기화합니다.
3. 백엔드가 개인 기준선, 체크인, 추천 피드백을 분석해 설명 가능한 개인화 메모리를 갱신합니다.
4. iPhone·Watch·웹이 동일한 타임라인, 추천, AI 대화 결과를 사용합니다.
5. 로컬 알림과 APNs가 iPhone·Apple Watch에 적절한 회복 알림을 전달합니다.

Morrow는 의료 진단이나 치료를 제공하지 않습니다. 생체 신호만으로 상태를 단정하지 않으며, 사용자의 직접 입력과 확인을 함께 반영합니다.

## 문서

- [AISH 조직 소개 및 협업 원칙](docs/organization.md)
- [MVP 범위](docs/mvp.md)
- [시스템 아키텍처](docs/architecture.md)
- [API 계약](docs/api.md)
- [AI 개인화 및 안전 설계](docs/ai-assistant.md)
- [iOS·watchOS 실행 및 알림 설정](docs/ios-watch-setup.md)
- [안전 및 개인정보 원칙](docs/safety-privacy.md)
- [개인정보 처리방침](docs/privacy-policy.md)
- [3분 해커톤 데모 스크립트](docs/demo-script.md)

## 문서 변경 원칙

- API 계약이 바뀌면 `docs/api.md`를 함께 갱신합니다.
- 건강 데이터 수집·보관·삭제 방식이 바뀌면 안전·개인정보 문서를 확인합니다.
- 프론트엔드나 백엔드 구현을 변경할 때 관련 문서 PR을 이 저장소에 연결합니다.
- 모든 변경은 기능 브랜치와 Pull Request를 통해 `main`에 병합합니다.

자세한 절차는 [기여 가이드](CONTRIBUTING.md)를 참고하세요.
