# iOS/watchOS 설정

1. Xcode에서 `apps/apple/Morrow.xcodeproj`를 엽니다.
2. iOS App과 Watch App 타깃의 Team·Bundle Identifier를 확인합니다.
3. `apps/ios/Sources`, `apps/watch/Sources`가 각 타깃에 포함됐는지 확인합니다.
4. iOS 타깃의 Signing & Capabilities에서 HealthKit을 활성화합니다.
5. `NSHealthShareUsageDescription`에 사용 목적을 작성합니다.
6. iPhone과 Watch 타깃에 동일한 App Group을 설정합니다.
7. 실제 iPhone과 Apple Watch에서 권한과 통신 흐름을 검증합니다.

MVP 코드는 HealthKit 권한 요청과 화면 흐름을 포함합니다. Watch 체크인은 `WatchSessionManager`를 통해 WatchConnectivity로 iPhone에 전송됩니다. HealthKit 표본 집계는 `HealthStore`의 확장 지점에 연결합니다.

## 화면 구성

- iPhone 대시보드: 웹 콘솔과 동일한 다크·청록 디자인, 회복 게이지, 근거 칩, 수면·HRV·안정 심박·걸음, 행동 추천과 최근 신호 스토리
- iPhone 상태 기록: 상태·원인·선택 메모를 남기는 30초 체크인, 햅틱과 저장 완료 피드백
- Watch 홈: iPhone에서 동기화한 회복 점수·수면·HRV·심박·행동 추천, 연결 상태, 최근 체크인
- Watch 체크인: 상태 선택 → 원인 선택의 2단계 입력, 전송 성공 햅틱과 iPhone 동기화
- Watch 1분 회복: 들숨·날숨 큐, 진행 링, 주기적 햅틱, 일시정지·재시작

디자인 토큰(간격, 코너 반경, 부하 레벨 컬러)은 `apps/ios/Sources/DesignSystem/Theme.swift`에 모여 있으며 시스템 컬러 기반이라 다크 모드를 자동 지원합니다.
