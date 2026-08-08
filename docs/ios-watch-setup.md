# iOS/watchOS 설정

1. Xcode에서 SwiftUI iOS App을 생성합니다.
2. Watch App for iOS App 타깃을 추가합니다.
3. `apps/ios/Sources`를 iOS 타깃에, `apps/watch/Sources`를 Watch 타깃에 넣습니다.
4. iOS 타깃의 Signing & Capabilities에서 HealthKit을 활성화합니다.
5. `NSHealthShareUsageDescription`에 사용 목적을 작성합니다.
6. iPhone과 Watch 타깃에 동일한 App Group을 설정합니다.
7. 실제 iPhone과 Apple Watch에서 권한과 통신 흐름을 검증합니다.

MVP 코드는 HealthKit 권한 요청과 화면 흐름을 포함합니다. Watch 체크인은 `WatchSessionManager`를 통해 WatchConnectivity로 iPhone에 전송됩니다. HealthKit 표본 집계는 `HealthStore`의 확장 지점에 연결합니다.

## 화면 구성

- iPhone 대시보드: 웰니스 부하 원형 게이지, 근거 칩, 수면·HRV·안정 심박·걸음 지표 카드, 행동 추천 카드, 상태 기록 진입 버튼
- iPhone 상태 기록: 아이콘 카드 그리드에서 상태 선택, 선택 햅틱, 저장 완료 피드백 후 자동 닫기
- Watch 체크인: 상태별 컬러 아이콘 버튼, 탭 즉시 전송과 성공 햅틱, 기록 완료 확인 화면

디자인 토큰(간격, 코너 반경, 부하 레벨 컬러)은 `apps/ios/Sources/DesignSystem/Theme.swift`에 모여 있으며 시스템 컬러 기반이라 다크 모드를 자동 지원합니다.
