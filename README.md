![Logo](https://blog.kakaocdn.net/dn/3V3c1/btsNmexbMaL/9heJqHL4FigHZNI5swAJok/img.png)
# ${\textsf{\color{#0062ff}EpiMap}}$
> ## EpiMap 프로젝트 설명
> 사용자에게 다음과 같은 서비스들을 제공하는 감염병 의료 정보 모바일 사이트<br>
>> 1. 감염병 현황을 시각화
>> 2. 감염병 관련 트렌드를 분석해 뉴스 정보 제공
>> 3. 사용자 위치정보를 기반으로 주변 응급실 현황 정보 제공
>> 4. 간단한 자가진단을 통해 사용자 감염병 증상에 대한 조언
>> 5. Chatbot을 이용해 사용자에게 가이드 및 관리자 피드백 가능
>> 6. 카카오 로그인으로 간편 로그인
>> 7. 보기 좋은 공지사항


> ## 👥 MediTrackers Member
> ### **네이버 비트캠프 17기 Semi Project**
> | 역할     | 이름      |
> |----------|-----------|
> | 팀장     | **최은영** |
> | 팀원     | 서지훈 <br> 이재호 <br> 전세호 <br> 조진용 <br> 채원석 |

## 🔗 사용한 외부 API 및 서비스

| API / 서비스                          | 활용 목적 및 기능                                       |
|--------------------------------------|--------------------------------------------------------|
| **NCP Clova Chatbot**                | 챗봇 구현 – 사용자와의 자연어 대화 처리                    |
| **Geolocation API**                  | 사용자 위치 정보 수집 – 응급실 정보와 지도 기반 서비스 제공 |
| **네이버 Map API**                   | 지도 표시 및 위치 기반 마커 출력                          |
| **공공 데이터 포털 응급의료정보 API** | 응급실/병원 실시간 정보 제공 (위치, 대기시간 등)            |
| **Google Translation API**           | 긴급 메시지의 영문 번역 기능 제공                         |
| **Kakao 로그인 API**                 | 간편한 소셜 로그인 기능 구현                              |
| **네이버 인기 검색어 API**           | 대시보드 또는 메인 페이지에 실시간 트렌드 반영               |



## 🛠️ Tech Stack

| Category        | Technology                                   |
|----------------|----------------------------------------------|
| **Language**    | Java                                          |
| **Framework**   | Spring Boot, MyBatis                         |
| **Template Engine** | Thymeleaf |
| **Frontend**    | HTML, CSS, JavaScript, jQuery, Bootstrap 5   |
| **Database**    | MySQL                                        |
| **Server**      | Naver Cloud Platform (Ubuntu Server)         |
| **CI/CD**       | DooD 방식 - Jenkins & Docker                 |

---

### 🔍 Description

- **Java / Spring Boot**: 백엔드 로직과 RESTful API를 구현
- **MyBatis**: SQL Mapper를 사용한 데이터베이스 연동
- **Thymeleaf**: 서버 사이드 렌더링을 위한 템플릿 엔진
- **HTML, CSS, JS, jQuery, Bootstrap5**: UI 구성 및 사용자 인터랙션 처리
- **MySQL**: 사용자 및 게시글 등 주요 데이터 저장
- **NCP 서버**: Ubuntu 환경의 클라우드 서버를 통해 배포 및 운영
- **CI/CD**: Docker 이미지를 통한 DooD 방식으로 Jenkins를 통해 자동 배포
