# 아키텍처

```text
Apple Watch
  HealthKit 요약 · 체크인 · APNs 알림
        │ WatchConnectivity (재전송·사용자 세션)
        ▼
iPhone
  HealthKit · 개인 기준선 · 권한 관리
        │ HTTPS
        ▼
Spring Boot API ── PostgreSQL
  기기 인증 · 파생 건강 요약 · 개인화 · APNs
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
- 범용 AI 모델을 사용자 데이터로 재학습하지 않습니다. 체크인·피드백에서 만든 설명 가능한 계정별 메모리만 프롬프트 컨텍스트와 추천 규칙에 사용합니다.
- iPhone·Watch·웹은 기기별 Bearer 토큰을 사용하고 6자리 코드로 같은 사용자에 페어링합니다.

## 분석 루프

`데이터 수집 → 개인 기준선 비교 → 사용자 확인 → 타임라인 → 행동 추천 → 효과 확인 → 개인화`
