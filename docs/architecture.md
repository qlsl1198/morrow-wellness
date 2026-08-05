# 아키텍처

```text
Apple Watch
  체크인 · 짧은 입력 · 햅틱
        │ WatchConnectivity
        ▼
iPhone
  HealthKit · 개인 기준선 · 권한 관리
        │ HTTPS
        ▼
Spring Boot API ── PostgreSQL
  타임라인 · 추천 · 피드백 · 삭제
        │
        ▼
React PC Web
  리포트 · 패턴 · AI 대화 · 기록 관리
```

## 설계 원칙

- iPhone이 HealthKit 원본 데이터 접근의 중심입니다.
- 서버에는 서비스 제공에 필요한 최소 데이터와 파생 결과만 보냅니다.
- 규칙/시계열 분석이 변화를 계산하고, 생성형 AI는 설명과 대화에 사용합니다.
- 모든 AI 결과에는 근거와 사용자 확인 여부를 연결합니다.
- PC 웹은 HealthKit을 직접 읽지 않고 API의 동기화 결과만 사용합니다.

## 분석 루프

`데이터 수집 → 개인 기준선 비교 → 사용자 확인 → 타임라인 → 행동 추천 → 효과 확인 → 개인화`
