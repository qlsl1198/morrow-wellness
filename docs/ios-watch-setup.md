# iOS/watchOS 설정

1. Xcode에서 SwiftUI iOS App을 생성합니다.
2. Watch App for iOS App 타깃을 추가합니다.
3. `apps/ios/Sources`를 iOS 타깃에, `apps/watch/Sources`를 Watch 타깃에 넣습니다.
4. iOS 타깃의 Signing & Capabilities에서 HealthKit을 활성화합니다.
5. `NSHealthShareUsageDescription`에 사용 목적을 작성합니다.
6. iPhone과 Watch 타깃에 동일한 App Group을 설정합니다.
7. 실제 iPhone과 Apple Watch에서 권한과 통신 흐름을 검증합니다.

MVP 코드는 HealthKit 권한 요청과 화면 흐름을 포함합니다. 표본 집계 및 WatchConnectivity 전송은 각 서비스 파일의 확장 지점에 연결합니다.
