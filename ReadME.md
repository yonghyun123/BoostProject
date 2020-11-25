# 프로젝트 3

요구사항 정의서



### Front-end

- DOMContentloaded 이후에 모든 자바스크립트 로직이 동작하게 합니다.
- 상단영역의 애니메이션은 CSS3의 transition과 transform 속성을 활용해서 구현해야 합니다.
- TABUI로 구성되는 카테고리 아이템이 노출되는 영역의 HTML은 카테고리별로 각각 만들지 않고 하나로 만들어 재사용합니다.
- 카테고리 탭을 선택할 때마다, Ajax 요청을 해서 데이터를 가져와야 합니다.
- 탭 메뉴 (전시/뮤지컬/콘서트 등)는 Event delegation으로 구현합니다.
- Template 코드를 javascript 함수 안에 보관하지 않고, 별도 분리시켜서 사용해야 합니다. (HTML에 script태그 안에 보관한다던가)
- 함수 하나에 여러 개의 기능을 넣지 않고, 함수를 여러 개로 분리합니다.



### Back-end

- 제공된 SQL을 이용해서 테이블을 생성하고, 샘플데이터를 입력합니다.
- maven을 이용해서 웹 어플리케이션 프로젝트를 작성합니다.
- 학습했던 것처럼 controller,service,dao로 레이어드 아키텍쳐를 구성합니다.
- spring JDBC를 이용하여 주어진 테이블로부터 입력, 수정, 삭제, 조회하는 DAO와 DTO를 작성합니다.
- 서비스 인터페이스를 작성하고 해당 서비스 인터페이스에 비지니스 메소드를 작성합니다.
- 서비스 인터페이스를 구현하는 클래스를 작성합니다.
- 해당 구현 클래스의 메소드에 적절한 트랜잭션에 관련된 애노테이션을 사용합니다.
- 클라이언트에게 Web API를 제공하기 위해 RestController 를 작성합니다.

# 프로젝트3 기능 완성 및 리뷰 완성





# 프로젝트4

https://www.edwith.org/boostcourse-web/project/10/content/8#summary



### Front-end

- 전체 코드는 객체리터럴 패턴으로 구현해야 하고, 더불어 전역변수를 최소화해야 합니다.
- 한 개 이상 객체리터럴을 사용할 수 있습니다.
- 상단 타이틀 이미지 영역의 애니메이션은 CSS3의 transition과 transform을 활용해서 구현해야 합니다.
- 상단에 추가로 노출해야 하는 기타 이미지는 Ajax요청을 통해 가져옵니다.
- DOMContentloaded 이후에 모든 자바스크립트 로직이 동작하게 합니다.
- Event delegation으로 처리할 수 있는 영역은 최대한 delegation방법으로 처리합니다.
- Templating 작업은 handlebar 라이브러리를 사용해서 구현해야 합니다. 
- 함수 하나에 여러 개의 기능을 넣지 않고, 함수를 여러 개로 분리합니다.
- 중복코드를 제거하고 공통부분은 공통함수로 만듭니다.
- 변수와 함수 이름은 본인이 정한 코딩컨벤션을 최대한 지킵니다.
- 접기/펼치기 기능은 jQuery 라이브러리를 사용할 수 있습니다.



### Back-end

- controller, service, dao로 레이어드 아키텍쳐를 구성합니다.
- spring JDBC를 이용하여 주어진 테이블로부터 입력, 수정, 삭제, 조회하는 DAO와 DTO를 작성합니다.
- 서비스 인터페이스를 작성하고 해당 인터페이스에 비지니스 메소드를 추가합니다.
- 서비스 인터페이스를 구현하는 클래스를 작성하고 클래스 내 메소드에 적절한 트랜잭션에 관련된 애노테이션을 사용합니다.
- 클라이언트에게 Web API를 제공하기 위해 RestController 를 작성합니다.



# 프로젝트4(상세페이지) 개발완료





# 프로젝트 5 예약하기 



웹프론트엔드 기술요구사항**

자주 사용되는 함수를 객체형태로 묶어서 사용해야 합니다.

form에 입력된 값을 체크를 할 때는 값의 유효성(validation)을 체크해야 하며, 정규표현식을 써서 구현해야 합니다. (이메일 필드는 반드시 유효성 검증해야 합니다.)

UI 별로 기능을 묶어서 객체화된 모듈을 만들어야 하며, prototype 방식을 적용해야 합니다.

이전 프로젝트와 같이 클린코드의 규칙대로 코드를 구현합니다.

 

**웹백엔드 기술요구사항**

예매확인을 위해 이메일을 입력 후 예약확인을 눌렀을 때 예매 내역이 있다면 이메일 정보를 세션에 저장합니다.

메인화면의 경우 세션에 이메일 정보가 있을 경우 예매 확인 버튼 대신 이메일을 보여주고, 이메일을 클릭하면 해당 이메일로 예약된 예매 내역이 보입니다.

