# iOS/watchOS 설정

1. Xcode에서 `apps/apple/Morrow.xcodeproj`를 엽니다.
2. iOS App과 Watch App 타깃의 Team·Bundle Identifier를 확인합니다.
3. `apps/ios/Sources`, `apps/watch/Sources`가 각 타깃에 포함됐는지 확인합니다.
4. iOS와 Watch 타깃의 Signing & Capabilities에서 HealthKit과 Push Notifications를 활성화합니다.
5. `NSHealthShareUsageDescription`에 사용 목적을 작성합니다.
6. 실제 iPhone과 Apple Watch에서 권한과 통신 흐름을 검증합니다.

MVP 코드는 HealthKit 권한 요청, 실제 일별 표본 집계, WatchConnectivity 재전송, 기기 인증과 APNs 토큰 등록 흐름을 포함합니다. Watch 체크인과 건강 요약은 `WatchSessionManager`를 통해 iPhone에 전송되고 iPhone이 동일 사용자 토큰으로 API에 저장합니다.

## 실제 iPhone에서 로컬 백엔드 연결

`localhost`는 iPhone 자신을 가리키므로 Mac의 서버에 연결되지 않습니다. Mac과 iPhone을 같은 Wi-Fi에 연결하고 다음 순서로 설정합니다.

1. 백엔드를 외부 인터페이스에 열어 실행합니다: `mvn spring-boot:run -Dspring-boot.run.arguments=--server.address=0.0.0.0`
2. Mac의 LAN IP를 확인합니다: `ipconfig getifaddr en0`
3. iPhone Morrow → 설정 → 서버 API 주소에 `http://{MAC_IP}:8080/api/v1`을 입력합니다.
4. 연결 상태와 웹 연결 코드가 표시되는지 확인합니다.
5. 웹 Morrow → 데이터 → 기기 계정에 iPhone의 6자리 코드를 입력합니다.

실서비스는 로컬 HTTP가 아니라 유효한 HTTPS 도메인을 사용해야 합니다. 개발 빌드는 LAN 테스트를 위해 HTTP를 허용합니다.

## 원격 알림 필수 조건

- Apple Developer에서 두 bundle identifier에 Push Notifications capability가 활성화돼 있어야 합니다.
- 백엔드에 `APNS_ENABLED=true`, `APNS_TEAM_ID`, `APNS_KEY_ID`, `APNS_PRIVATE_KEY_PATH`를 설정합니다.
- Debug 기기는 `SANDBOX`, TestFlight/App Store는 `PRODUCTION` APNs 환경으로 자동 등록됩니다.
- iPhone과 Watch에서 알림 권한을 각각 허용해야 합니다.
- `/api/v1/notifications/status`의 `ready`가 `true`여야 서버 원격 알림을 실제로 보낼 수 있습니다.

## 화면 구성

- iPhone 대시보드: 웹 콘솔과 동일한 다크·청록 디자인, 회복 게이지, 근거 칩, 수면·HRV·안정 심박·걸음, 행동 추천과 최근 신호 스토리
- iPhone 상태 기록: 상태·원인·선택 메모를 남기는 30초 체크인, 햅틱과 저장 완료 피드백
- Watch 홈: iPhone에서 동기화한 회복 점수·수면·HRV·심박·행동 추천, 연결 상태, 최근 체크인
- Watch 체크인: 상태 선택 → 원인 선택의 2단계 입력, 전송 성공 햅틱과 iPhone 동기화
- Watch 1분 회복: 들숨·날숨 큐, 진행 링, 주기적 햅틱, 일시정지·재시작

디자인 토큰(간격, 코너 반경, 부하 레벨 컬러)은 `apps/ios/Sources/DesignSystem/Theme.swift`에 모여 있으며 시스템 컬러 기반이라 다크 모드를 자동 지원합니다.
