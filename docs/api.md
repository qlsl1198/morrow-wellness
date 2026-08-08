# API 계약

기준 URL은 `/api/v1`입니다.

| Method | Path | 설명 |
|---|---|---|
| GET | `/dashboard?userId={id}` | 오늘 상태, 타임라인, 추천 조회 |
| POST | `/check-ins` | 워치·iPhone·웹 체크인 생성 |
| DELETE | `/check-ins/{id}` | 개별 체크인 삭제 |
| DELETE | `/users/me/data?userId={id}` | 현재 사용자의 웰니스 데이터 전체 삭제 |

## 체크인 생성 예시

```json
{
  "userId": "default-user",
  "status": "TIRED",
  "cause": "SLEEP",
  "note": "어제 늦게 잠들었음",
  "source": "WATCH",
  "recordedAt": "2026-08-03T14:30:00+09:00"
}
```

상태: `OK`, `TENSE`, `TIRED`, `LOW_FOCUS`, `UNCOMFORTABLE`

원인: `SLEEP`, `WORK`, `STUDY`, `RELATIONSHIP`, `PHYSICAL`, `UNKNOWN`

소스: `WATCH`, `IPHONE`, `WEB`

## 후속 API

- `PATCH /timeline/{id}`: AI 생성 기록 수정
- `POST /recommendations/{id}/feedback`: 실행 및 도움 여부 기록
- `GET /reports/weekly?userId={id}`: 주간 패턴 조회
- `POST /assistant/messages`: 안전 정책을 적용한 AI 대화

`userId`를 생략하면 데모용 기본 사용자(`default-user`)가 적용됩니다. 운영 환경에서는 인증 토큰의 주체로 대체해야 합니다.
