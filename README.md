![Logo](https://blog.kakaocdn.net/dn/bDmSBg/btsNmu7mC2m/7CzKvN3mM1QuQUUl7lWlwk/img.png)
<!--# ${\textsf{\color{#0062ff}EpiMap}}$-->

> ## EpiMap 프로젝트 설명
> <b>개발 기간: 2025.03.27 ~ 2025.04.16</b> <br>
> <b>배포 주소: https://epimap.kro.kr/ </b> <br>
> 사용자에게 다음과 같은 서비스들을 제공하는 감염병 의료 정보 모바일 사이트<br>
>> 1. 감염병 현황을 시각화
>> 2. 감염병 관련 트렌드를 분석해 뉴스 정보 제공
>> 3. 사용자 위치정보를 기반으로 주변 응급실 현황 정보 제공
>> 4. 간단한 자가진단을 통해 사용자 감염병 증상에 대한 조언
>> 5. Chatbot을 이용해 사용자에게 가이드 및 관리자 피드백 가능
>> 6. 카카오 로그인으로 간편 로그인
>> 7. 보기 좋은 공지사항

> ## 👥 MediTrackers Member
> ### **네이버 클라우드 네이티브 부트캠프 17기 Semi Project**
> | 역할     | 이름      |
> |----------|-----------|
> | 팀장     | **최은영** |
> | 팀원     | 서지훈 <br> 이재호 <br> 전세호 <br> 조진용 <br> 채원석 |
<p>&nbsp;</p>


## 🛠️ Tech Stacks
<!--
| Category        | Technology                                   |
|----------------|----------------------------------------------|
| **Language**    | Java                                          |
| **Framework**   | Spring Boot, MyBatis                         |
| **Template Engine** | Thymeleaf |
| **Frontend**    | HTML, CSS, JavaScript, jQuery, Bootstrap 5   |
| **Database**    | MySQL                                        |
| **Server**      | Naver Cloud Platform (Ubuntu Server)         |
| **CI/CD**       | DooD 방식 - Jenkins & Docker                 |-->

### Environment
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=Spring%20Boot&logoColor=white)&nbsp;
![Thymeleaf](https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)&nbsp;
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white)&nbsp;
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white)&nbsp;

### Development
![HTML5](https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white)&nbsp;
![CSS3](https://img.shields.io/badge/css3-1572B6?style=for-the-badge&logo=css3&logoColor=white)&nbsp;
![JavaScript](https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white)&nbsp;
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)&nbsp;
![jQuery](https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white)&nbsp;
![Bootstrap](https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)<br>
![Naver API](https://img.shields.io/badge/Naver%20API-03C75A?style=for-the-badge&logo=naver&logoColor=white)&nbsp;
![Kakao API](https://img.shields.io/badge/kakao%20API-FFCD00?style=for-the-badge&logo=kakao&logoColor=white)&nbsp;

### Database
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)&nbsp;

### Server (Naver Cloud Platform)
![Ubuntu Server](https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white)&nbsp;

### CI/CD (DooD)
![Jenkins](https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white)&nbsp;
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)&nbsp;

### Communication
[![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)](https://ceyemong.notion.site/epimap?pvs=4)&nbsp;
[![Figma](https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white)](https://www.figma.com/design/Fmuj7dXLIpYdzIlDtoVmSj/%EC%8B%A4%EC%8B%9C%EA%B0%84-%EA%B0%90%EC%97%BC%EB%B3%91-%EA%B0%80%EC%9A%A9%EB%B3%91%EC%83%81-%ED%99%95%EC%9D%B8-%EC%95%B1?node-id=33-780&t=ECBtiR99xoWwbGFS-1)&nbsp;
[![Google Docs](https://img.shields.io/badge/Google%20Docs-4285F4?style=for-the-badge&logo=googledocs&logoColor=white)](https://docs.google.com/spreadsheets/d/1van1JKRC4Uk2Ln3flgZEPAa-xEQgy1IcU6o82DOehLU/edit)
<p>&nbsp;</p>


### 🔗 사용한 외부 API 및 서비스

| API / 서비스                          | 활용 목적 및 기능                                       |
|--------------------------------------|--------------------------------------------------------|
| **NCP Clova Chatbot**                | 챗봇 구현 – 사용자와의 자연어 대화 처리                    |
| **Geolocation API**                  | 사용자 위치 정보 수집 – 응급실 정보와 지도 기반 서비스 제공 |
| **네이버 Map API**                   | 지도 표시 및 위치 기반 마커 출력                          |
| **공공 데이터 포털 응급의료정보 API** | 응급실/병원 실시간 정보 제공 (위치, 대기시간 등)            |
| **Google Translation API**           | 긴급 메시지의 영문 번역 기능 제공                         |
| **Kakao 로그인 API**                 | 간편한 소셜 로그인 기능 구현                              |
| **네이버 인기 검색어 API**           | 메인 페이지 및 대시보드에 실시간 트렌드 반영               |
<p>&nbsp;</p>


### 🔍 Description

- **Java / Spring Boot**: 백엔드 로직과 RESTful API를 구현
- **MyBatis**: SQL Mapper를 사용한 데이터베이스 연동
- **Thymeleaf**: 서버 사이드 렌더링을 위한 템플릿 엔진
- **HTML, CSS, JS, jQuery, Bootstrap5**: UI 구성 및 사용자 인터랙션 처리
- **MySQL**: 사용자 및 게시글 등 주요 데이터 저장
- **NCP 서버**: Ubuntu 환경의 클라우드 서버를 통해 배포 및 운영
- **CI/CD**: Docker 이미지를 통한 DooD 방식으로 Jenkins를 통해 자동 배포

---

## 📱 화면 구성

Page 0. 메인 통계 대시보드
- 질병관리청 감염병포털, 네이버 검색 api 기반 대시보드 서비스
- 현재 유행 중인 인플루엔자에 대한 유행지표를 시각화하고 이용자들의 독감 정보를 확인할 수 있도록 대시보드로 구성하는 페이지 구현
![image](https://github.com/user-attachments/assets/c93c257c-081a-4818-b97d-be5ecd5e6fe0)

Page 2. 응급실 찾기
- 사용자의 위치를 기반으로 실시간 가용병상 및 응급실 정보를 제공하는 서비스
<img src = https://github.com/user-attachments/assets/b59143e3-bbae-488e-98a0-bff3002e8970 height="300">
<img src = https://github.com/user-attachments/assets/30617ac1-939e-471d-9234-0f284a7bda80 height="300">
<img src = https://github.com/user-attachments/assets/e47a63dd-690f-4002-a63e-bf62e9ab949a height="300">
<br>
<img src = https://github.com/user-attachments/assets/d6fc76b8-e3fb-448f-be1b-a0fb033946d8 height="300">
<img src = https://github.com/user-attachments/assets/64c276b7-d5d5-4163-ba72-f3506e6d4ee9 height="300">
<img src = https://github.com/user-attachments/assets/fb7667fe-dee4-4118-bee0-76d52ad6d93b height="300">

Page 3. 자가진단 페이지
- 증상을 선택해 자가 진단을 진행해 사용자에게 빠른 진단과 조언을 제공하는 서비스
<img src =https://github.com/user-attachments/assets/3a130b5d-2e73-4464-8606-56f74452ca77 height="300">
<img src = https://github.com/user-attachments/assets/1ec7d4ea-1a33-487b-b6df-194456fe2791 height="300">
<img src = https://github.com/user-attachments/assets/3247fbf8-9978-450c-8625-d077d25d18a0 height="300">

page 4. 공지사항
- 사용자에게 공지사항을 전달함을 동시에 관리자가 효율적으로 관리할수 있게 해주는 서비스
<img src = https://github.com/user-attachments/assets/8ecc4af8-9a2b-4e84-84c9-8d36a163b2a7 height="300">
<img src = https://github.com/user-attachments/assets/783968e4-85f1-4a4c-b91b-1120dc743220 height="300">
<img src = https://github.com/user-attachments/assets/2e7a66e2-5341-4424-9f9a-66e6a367c314 height="300">
<img src = https://github.com/user-attachments/assets/412e10a4-491f-4a67-a7e4-3768101afffd height="300">
<br>
<img src = https://github.com/user-attachments/assets/8f485d42-0453-4a93-ad42-f8221eebbe93 height="300">
<img src = https://github.com/user-attachments/assets/0a3ad8dd-1150-4667-a533-e18be23edcc9 height="300">
<img src = https://github.com/user-attachments/assets/0562c5d9-09e5-4125-a5af-96383ea8bd2f height="300">
<img src = https://github.com/user-attachments/assets/1ab3a6e3-fa52-4c2e-800e-062df1ceca60 height="300">
<br>
<img src = https://github.com/user-attachments/assets/da2c53cc-ed15-4b62-ae81-442042946a98 height="300">
<img src = https://github.com/user-attachments/assets/ce2b713b-071c-44c8-af3e-c168f4eecf20 height="300">


Page 5. 마이페이지
- 사용자에게 회원가입, 카카오 회원가입 / 로그인 및 마이페이지 뉴스 기사 스크랩 / 자가진단 결과 저장 기능을 제공하는 서비스
<img src = https://github.com/user-attachments/assets/ba16eeb2-d5b3-4bb5-a800-cd7ff4d52c6b height="300">
<img src = https://github.com/user-attachments/assets/e8dbea6d-2e40-44c2-bb94-50eedb0597ec height="300">
<img src = https://github.com/user-attachments/assets/277601ce-3392-4fd5-91af-f37cc7725e69 height="300">


