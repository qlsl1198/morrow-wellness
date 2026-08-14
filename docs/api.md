# API 계약

기준 경로는 `/api/v1`입니다. 배포 환경에서는 기기별 `Authorization: Bearer {accessToken}`을 사용하며, 토큰으로 확인된 사용자와 요청의 `userId`가 다르면 거부합니다.

## 인증과 기기 연결

| Method | Path | 설명 |
| --- | --- | --- |
| `POST` | `/auth/account` | 계정 ID로 로그인하고 현재 기기 토큰 발급 |
| `POST` | `/auth/pairing-code?deviceId={id}` | 로그인한 설정 화면에서 연결 코드 갱신 |
| `POST` | `/auth/pair` | 연결 코드로 새 기기를 같은 계정에 연결 |
| `GET` | `/auth/devices` | 현재 계정에 연결된 기기 조회 |
| `DELETE` | `/auth/devices/{id}` | 선택한 기기 연결 해제 |
| `POST` | `/auth/logout` | 현재 기기 토큰 폐기 |
| `POST` | `/auth/device` | 로컬·이전 클라이언트 호환용 기기 등록 |

일반 사용자 흐름은 로그인 화면에서 계정 ID만 입력합니다. 연결 코드는 로그인 수단이 아니며, 로그인 후 설정에서 iPhone·Watch·웹을 추가할 때만 사용합니다. 코드는 만료되지만 한 번 연결된 기기와 계정 관계는 로그아웃, 기기 해제 또는 계정 삭제 전까지 유지됩니다.

```json
POST /api/v1/auth/account
{
  "accountId": "demo-user",
  "deviceId": "web-7c15...",
  "deviceName": "Chrome on Mac",
  "platform": "WEB"
}
```

## 핵심 데이터

| Method | Path | 설명 |
| --- | --- | --- |
| `GET` | `/dashboard?userId={id}` | 회복 점수, 근거, 건강 상세, KST 타임라인, 현재 추천 |
| `POST` | `/check-ins` | 멱등 체크인 생성 |
| `GET` | `/check-ins?userId={id}` | 사용자 체크인 조회 |
| `DELETE` | `/check-ins/{id}` | 개별 체크인 삭제 |
| `POST` | `/health/snapshots` | HealthKit 파생 요약과 수면·운동 상세 동기화 |
| `PATCH` | `/timeline/{id}` | 타임라인 내용과 사용자 확인 상태 수정 |
| `POST` | `/recommendations/{id}/feedback` | 추천 완료·도움 여부 기록 |
| `GET` | `/reports/weekly?userId={id}` | 최근 7일 패턴과 회복 효과 조회 |

### 체크인 멱등성

```json
POST /api/v1/check-ins
{
  "userId": "demo-user",
  "clientEventId": "watch-20260814-143000-abc",
  "status": "TIRED",
  "cause": "SLEEP",
  "note": "어제 늦게 잠들었음",
  "source": "WATCH",
  "recordedAt": "2026-08-14T14:30:00+09:00"
}
```

같은 `clientEventId`를 WatchConnectivity나 네트워크 재시도로 다시 보내면 기존 체크인을 반환합니다. 타임라인·추천·개인화 학습도 중복 생성하지 않습니다. 상태는 `OK`, `TENSE`, `TIRED`, `LOW_FOCUS`, `UNCOMFORTABLE`, 원인은 `SLEEP`, `WORK`, `STUDY`, `RELATIONSHIP`, `PHYSICAL`, `UNKNOWN`, 소스는 `WATCH`, `IPHONE`, `WEB`입니다.

### 건강 상세

`POST /health/snapshots`는 일별 수면·심박·HRV·걸음·활동 에너지뿐 아니라 다음 상세를 받습니다.

- 수면: 시작·종료, 총 시간, 코어·깊은·REM·깨어있음 분, 데이터 소스
- 운동: 종류, 시작·종료, 시간, 활동 에너지, 거리, 평균·최대 심박, 추정 강도

`clientSnapshotId`와 수면·운동별 클라이언트 ID로 재전송을 멱등 처리합니다. 서버와 웹의 날짜 묶음 및 타임라인 표시 기준은 `Asia/Seoul`입니다.

## 회복 행동과 AI

| Method | Path | 설명 |
| --- | --- | --- |
| `GET/POST` | `/recovery-attempts` | 최근 회복 행동 조회·새 실행 생성 |
| `PATCH` | `/recovery-attempts/{id}/start` | 제안 행동 실행 시작 |
| `PATCH` | `/recovery-attempts/{id}/complete` | 체감 결과 기록과 개인화 학습 |
| `POST/GET` | `/assistant/messages` | 새 AI 응답 생성·최근 이력 조회 |
| `DELETE` | `/assistant/messages` | 계정의 대화 기록 삭제 |
| `POST` | `/assistant/proactive-insight` | 건강·체크인 흐름 기반 선제 알림 판단·문구 생성 |
| `GET` | `/assistant/status` | 모델 준비 상태와 최근 성공·실패 지표 조회 |
| `GET` | `/personalization/profile` | 활성 메모리와 학습 근거 요약 |
| `GET/POST` | `/personalization/memories` | 메모리 조회·사용자 선호/목표 생성 |
| `PATCH/DELETE` | `/personalization/memories/{id}` | 메모리 수정·비활성화·삭제 |
| `POST` | `/personalization/rebuild` | 기록에서 자동 학습 메모리 재구성 |

## 알림·개인정보

| Method | Path | 설명 |
| --- | --- | --- |
| `POST/DELETE` | `/notifications/devices` | iOS/watchOS APNs 토큰 등록·비활성화 |
| `POST` | `/notifications/test` | 현재 계정의 등록 기기에 테스트 알림 발송 |
| `GET` | `/notifications/status` | APNs 설정 준비 상태와 활성 기기 수 조회 |
| `GET/PATCH` | `/privacy/ai-health-consent` | 건강 요약의 AI 컨텍스트 사용 동의 조회·변경 |
| `DELETE` | `/users/me/data` | 웰니스 기록·추천·대화·메모리·건강 요약 삭제 |
| `DELETE` | `/users/me/account` | 계정, 연결 기기와 모든 데이터 완전 삭제 |

로컬 데모 호환성을 위해 `MORROW_AUTH_REQUIRED=false`를 사용할 수 있지만, 운영 배포는 `true`여야 합니다. 실제 키와 비밀번호는 응답·로그·문서에 노출하지 않습니다.
