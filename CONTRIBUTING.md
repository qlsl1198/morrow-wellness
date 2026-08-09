# Morrow 문서 기여 가이드

이 저장소에는 제품 사양, 아키텍처, API 계약, 안전·개인정보 정책, 데모 자료만 커밋합니다. 애플리케이션 코드는 [morrow-frontend](https://github.com/AISH-Official/morrow-frontend), 서버 코드는 [morrow-backend](https://github.com/AISH-Official/morrow-backend)에서 관리합니다.

## 작업 절차

1. 최신 `main`에서 `docs/<topic>` 또는 `codex/<topic>` 브랜치를 만듭니다.
2. 한 Pull Request에서는 하나의 문서 목적에 집중합니다.
3. 상대 링크와 코드 예시가 현재 프론트엔드·백엔드 구조와 일치하는지 확인합니다.
4. `git diff --check`로 공백 및 패치 오류를 검증합니다.
5. 관련 구현 PR이나 이슈를 본문에 연결하고 리뷰 후 병합합니다.

## 변경 시 함께 확인할 문서

- API 요청·응답: `docs/api.md`
- 기기 및 데이터 흐름: `docs/architecture.md`, `docs/ios-watch-setup.md`
- AI 개인화: `docs/ai-assistant.md`
- 건강 데이터·알림·사용자 권리: `docs/safety-privacy.md`, `docs/privacy-policy.md`
- 발표 흐름: `docs/demo-script.md`

## 제품 안전 원칙

- 의료 진단이나 치료 효과를 주장하지 않습니다.
- 생체 신호만으로 사용자의 정신·신체 상태를 단정하지 않습니다.
- HealthKit과 민감정보는 명시적인 동의 범위에서만 사용합니다.
- 사용자가 개인화 메모리와 기록을 확인·수정·삭제할 수 있어야 합니다.
