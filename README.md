# spring-member-management

멤버들을 관리하는 간단한 프로그램이다.

### 비즈니스 요구 사항

- 데이터
    - [X]  회원 ID
    - [X]  회원 이름
- 기능 (서비스)
    - [X] 회원 등록
        - 동일한 이름을 가진 회원은 등록할 수 없다.
    - [X] 회원 조회
- 저장소
  - [X] 저장
  - [X] 조회
- 테스트
  - [X] 저장소 테스트
  - [X] 기능 테스트
- 추가 정보
    - 데이터 저장소 선정 X
        - 초기 개발 단계에서는 구현체로 가벼운 메모리 기반의 데이터 저장소를 사용한다.

### 구조

일반적인 웹 어플리케이션 구조를 따른다.

- 컨트롤러 : 웹 MVC의 컨트롤러 역할을 한다.
- 서비스 : 핵심 비즈니스 로직 구현한다.
- 리포지토리 : 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리한다.
- 도메인 : 비즈니스 도메인 객체 (회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장되고 관리된다.)
