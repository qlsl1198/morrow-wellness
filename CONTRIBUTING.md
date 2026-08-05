# Morrow Wellness 개발 가이드

이 저장소는 React PC 웹, SwiftUI iPhone·Apple Watch 앱, Java Spring Boot 백엔드를 함께 관리하는 모노레포입니다.

## 작업 시작

1. 최신 `main` 브랜치를 받습니다.
2. 작업 목적에 맞는 브랜치를 만듭니다.
3. 한 브랜치에서는 하나의 기능이나 수정에 집중합니다.
4. 로컬 검증 후 Pull Request를 엽니다.

브랜치 이름 예시:

- `feature/watch-check-in`
- `feature/web-weekly-report`
- `fix/api-validation`
- `docs/healthkit-setup`

## 로컬 검증

PC 웹:

```bash
cd apps/web
npm ci
npm run build
```

백엔드:

```bash
cd backend
mvn test
```

iOS·watchOS는 Xcode에서 대상 타깃이 빌드되는지 확인합니다. HealthKit 기능은 실제 기기에서 별도로 검증합니다.

## Pull Request 규칙

- PR 제목은 변경 결과가 드러나도록 작성합니다.
- 본문에 변경 내용, 검증 방법, 관련 화면 또는 API를 적습니다.
- UI 변경은 가능하면 화면 캡처를 첨부합니다.
- API 계약을 바꾸면 `docs/api.md`도 함께 수정합니다.
- 건강 데이터 처리나 안전 문구를 바꾸면 `docs/safety-privacy.md`를 확인합니다.
- 자동 검증이 통과한 뒤 리뷰를 요청합니다.
- 다른 팀원의 승인 후 `main`에 병합하는 것을 권장합니다.

## 커밋 권장 형식

- `feat: add watch check-in`
- `fix: handle missing HealthKit permission`
- `docs: update local setup`
- `test: cover check-in API`
- `refactor: separate timeline mapper`

## 제품 안전 원칙

- 의료 진단이나 치료 효과를 주장하지 않습니다.
- 생체 신호만으로 사용자의 정신·신체 상태를 단정하지 않습니다.
- HealthKit과 민감정보는 명시적인 동의 범위에서만 사용합니다.
- 사용자가 기록을 수정하고 삭제할 수 있어야 합니다.
