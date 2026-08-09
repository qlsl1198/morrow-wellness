# API 계약

기준 URL은 `/api/v1`입니다.

| Method | Path | 설명 |
|---|---|---|
| POST | `/auth/device` | iPhone·웹 기기 세션과 Bearer 토큰·연결 코드 발급 |
| POST | `/auth/pair` | 6자리 연결 코드로 다른 기기를 같은 사용자에 연결 |
| GET | `/dashboard?userId={id}` | 오늘 상태, 타임라인, 추천 조회 |
| POST | `/check-ins` | 워치·iPhone·웹 체크인 생성 |
| POST | `/health/snapshots` | iPhone·Watch에서 계산한 웰니스 일별 요약 동기화 |
| POST | `/notifications/devices` | iOS/watchOS APNs 기기 토큰 등록 |
| DELETE | `/notifications/devices` | APNs 기기 토큰 비활성화 |
| POST | `/notifications/test` | 현재 사용자의 등록 기기에 테스트 알림 발송 |
| GET | `/notifications/status` | APNs 설정 준비 상태와 활성 기기 수 확인 |
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
- `GET /assistant/messages`: 사용자별 최근 대화 기록
- `GET /assistant/status`: OpenAI 활성화·키·모델 준비 상태(키 값은 노출하지 않음)
- `GET /personalization/profile`: 활성 메모리와 학습 근거 요약
- `GET /personalization/memories`: AI가 사용하는 설명 가능한 사용자 메모리 목록
- `POST /personalization/memories`: 사용자가 선호 또는 목표를 직접 기억시킴
- `PATCH /personalization/memories/{id}`: 메모리 수정 또는 비활성화
- `DELETE /personalization/memories/{id}`: 사용자별 메모리 삭제
- `POST /personalization/rebuild`: 전체 체크인·추천 피드백에서 메모리 재생성

## 기기 인증과 페어링

`POST /auth/device`에 고유 `deviceId`, 표시 이름, `IOS` 또는 `WEB` 플랫폼을 보내면 `userId`, `accessToken`, `pairingCode`가 발급됩니다. 이후 요청은 `Authorization: Bearer {accessToken}`을 포함합니다. iPhone 설정에 표시되는 연결 코드를 웹 개인정보 화면의 기기 연결 폼에 입력하면 두 기기가 같은 `userId`로 묶입니다. Watch는 iPhone의 암호화된 WatchConnectivity 채널로 서버 주소와 기기 세션을 전달받습니다.

로컬 데모 호환성을 위해 `MORROW_AUTH_REQUIRED=false`에서는 무인증 요청도 허용됩니다. 배포 환경은 반드시 `MORROW_AUTH_REQUIRED=true`로 설정해야 하며, Bearer 토큰이 있으면 요청 본문의 다른 `userId` 접근은 항상 거부됩니다.

## APNs 알림

`APNS_ENABLED=true`, Apple Team ID, APNs Key ID, `.p8` 개인 키 경로, iOS/watchOS bundle topic을 설정해야 실제 원격 알림이 발송됩니다. 앱의 로컬 체크인 알림은 APNs 설정 없이도 동작하지만, 서버 기반 회복 알림과 테스트 알림은 APNs 설정이 준비돼야 합니다.
