# AISH

AISH는 **AI for Sustainable Human wellbeing**을 지향하는 제품 팀입니다. AI가 사용자를 대신해 건강 상태를 단정하는 것이 아니라, 사용자가 자신의 신호를 이해하고 더 나은 다음 행동을 선택하도록 돕는 기술을 만듭니다.

GitHub 조직: [AISH-Official](https://github.com/AISH-Official)

## 현재 프로젝트: Morrow

Morrow는 Apple Watch의 생체 신호, iPhone의 HealthKit 데이터, 사용자의 짧은 체크인을 연결하는 크로스디바이스 AI 웰니스 어시스턴트입니다. 개인 기준선과 피드백을 활용해 설명 가능한 회복 행동을 제안하며, 웹에서도 동일한 타임라인과 개인화 결과를 확인할 수 있습니다.

| 저장소 | 책임 |
| --- | --- |
| [morrow-frontend](https://github.com/AISH-Official/morrow-frontend) | 웹, iPhone, Apple Watch 사용자 경험과 기기 데이터 수집 |
| [morrow-backend](https://github.com/AISH-Official/morrow-backend) | 인증, 데이터 동기화, 개인화, AI, APNs 알림 API |
| [morrow-docs](https://github.com/AISH-Official/morrow-docs) | 제품·API·아키텍처·안전 정책과 해커톤 데모 문서 |

## 제품 원칙

- **Human-confirmed:** 생체 신호만으로 상태를 단정하지 않고 사용자 입력과 확인을 함께 사용합니다.
- **Explainable:** 모든 추천은 근거와 예상 소요 시간을 함께 제공합니다.
- **Private by design:** 필요한 최소 데이터만 수집하고 사용자가 기록과 개인화 메모리를 삭제할 수 있게 합니다.
- **Safety first:** 의료 진단을 제공하지 않으며 위기 표현에는 전문 지원 안내를 우선합니다.
- **Cross-device continuity:** Watch, iPhone, 웹이 동일한 사용자 맥락과 피드백 루프를 공유합니다.

## 협업 방식

- 구현은 프론트엔드와 백엔드 저장소에서 독립적으로 개발·검증합니다.
- 공통 계약과 제품 결정은 문서 저장소의 Pull Request로 기록합니다.
- API 변경은 관련 프론트엔드·백엔드 PR과 `docs/api.md`를 서로 연결합니다.
- 건강 데이터, AI 개인화, 알림 변경은 안전·개인정보 문서를 함께 검토합니다.
- `main` 브랜치는 배포 가능한 상태를 유지하고 자동화된 검증을 통과한 변경만 병합합니다.

## 공개 데모

- 웹: [Morrow GitHub Pages](https://aish-official.github.io/morrow-frontend/)
- 소스와 문서: [AISH-Official GitHub](https://github.com/AISH-Official)
