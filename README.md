# ReadMe App

![readme_long](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/cb87d3f4-2a02-47d1-8894-371e7954dd02)


# 앱 소개
`Read Me`는 **최고의 독서 경험을 선사하는 전자책 플랫폼 앱**입니다.</br>
다양한 장르의 책을 제공하며 편리한 인터페이스로 책 읽기를 더욱 즐겁게 만들어줍니다.</br>
수많은 전자책을 한 곳에서 쉽게 찾아 읽을 수 있는 편리함과 함께 즐겨찾은 페이지를 표시하고,</br> 읽은 책에 대한 리뷰를 공유하는 등 독서를 더욱 풍부하게 즐길 수 있습니다. </br>

# 시연영상
https://www.youtube.com/watch?v=MDKwmzJHqKE

# 핵심기능
> **부트페이**</br>
* 개념</br>
  * 부트페이는 개발사들이 쉽게 결제를 연동할 수 있도록 다양한 플랫폼별 SDK를 제공합니다.</br>
  * 개발환경, PG에 상관없이 쉬운 문법, 적은 개발 공수를 제공하고,</br>
  * 구매자가 결제 진행함에 있어 불편한 시나리오가 없도록 끊임없이 개선하고 있습니다.</br>
  * 또한 결제 오류나 검증이 개발사 실수로 인해 결제처리가 누락되지 않도록 진화된 SDK와 아키텍쳐를 제공하고 있습니다.</br></br>
![image](https://github.com/ReadMeCorporation/user_server_ReadMe/assets/68271830/81834691-9b5a-4d09-b9ed-6773bc3abb19)

* 적용</br>
  * [1] 클라이언트가 결제 요청을 클릭시 먼저 서버에 결제 데이터를 저장하고 결제번호를 응답받습니다..</br>
  * [2] 해당 id 값을 부트페이 서버에 전송합니다.</br>
  * [3] 클라이언트에서 결제 완료를 하고 부트페이 서버에서 스프링 서버로 결제 대기 상태의 웹훅 통지를 날립니다.</br>
  * [4] 웹훅 통지 안의 가격과 클라이언트를 통해 넘겨받은 결제번호로 검증합니다.</br>
  * [5] 서버 검증을 마치고 정상적이면 부트페이에 `결제승인처리` 데이터를 전송하고 실패하면 `결제승인취소`를 보냅니다. </br>
  * [6] 결제 승인 처리, 실패처리가 되면 부트 페이 서버에서 클라이언트와 서버쪽으로 데이터를 보내고 클라이언트는 결제 창이 닫히고 정상적으로 결제처리를 완료시킵니다.</br></br>
![image](https://github.com/ReadMeCorporation/user_server_ReadMe/assets/68271830/9a0c3dd6-ea6c-4200-a0f6-90e12a8b249d)

> **FCM**
* 개념</br>


* 적용</br>

> **S3**</br>
* 개념</br>
  * AWS S3(Amazon Simple Storage Service)는 아마존 웹 서비스(Amazon Web Services)에서 제공하는 클라우드 기반의 객체 스토리지 서비스입니다.</br>
  * S3는 인터넷을 통해 데이터를 저장하고 검색할 수 있는 스토리지 솔루션으로, 안정적이고 확장 가능한 저장소를 제공하여 다양한 용도로 활용됩니다.</br>

* 적용</br>
  * S3 SDK를 설정하고 인스턴스를 생성하는 Configuration을 생성합니다.</br>
  * 생성된 인스턴스로 S3 putObject 메서드를 호출해 버킷과 파일이름, 파일의 용량을 S3에 업로드. 이후 getUrl메서드를 리턴해 해당 파일의 경로를 받아옵니다.</br>
  * S3 버킷의 폴더경로로 파일의 출처를 구분짓고 받아온 파일의 경로를 리턴합니다.</br>

> **Security & OAuth 2.0**</br>
* OAuth 2.0 개념</br>
  * OAuth 2.0은 인증과 권한 부여를 위한 개방형 표준 프로토콜로, 웹 및 모바일 애플리케이션에서 안전하게 제3자 서비스에 접근할 수 있도록 도와줍니다.</br>
  * OAuth 2.0은 사용자의 비밀 정보를 공유하지 않으면서 인증 및 권한을 관리하는 메커니즘을 제공합니다.

* OAuth 2.0 적용</br>
  * [1] Resource Owner가 Client(Application)에 로그인 요청을 합니다.
  * [2] Client는 Firebase에 idToken을 요청합니다.
  * [3] Firebase에서 Client로 idToken을 응답합니다.
  * [4] Client가 Server에 idToken을 넘깁니다.
  * [5] Server는 Firebase에 토큰이 유효한 토큰인지 검증합니다.
  * [6] 검증이 완료되면 서버에 AccessToken을 응답합니다.
  * [7] Server는 AccessToken안의 Resource Owner의 정보를 토대로 JWT Token을 생성해 응답합니다.
![image](https://github.com/ReadMeCorporation/user_server_ReadMe/assets/68271830/1b27cdd0-261d-4a25-b67d-3970a4c3e585)

* Spring Security 개념</br>
  * Spring Security는 스프링 기반의 보안 프레임워크로, 웹 애플리케이션의 인증(Authentication)과 권한 부여(Authorization)를 처리하는 데 사용됩니다.</br>
  * Spring Security는 사용자 인증, 접근 제어, 보안 설정 관리 등 다양한 보안 기능을 제공하여 애플리케이션의 보안 요구 사항을 충족시킬 수 있습니다.</br>

* Spring Security 적용</br>
<table>
 <tr>
  <td><img width="692" alt="security_config" src="https://github.com/ReadMeCorporation/user_server_ReadMe/assets/68271830/efa073a2-6d8e-40d6-b1ba-0baa85ac9b91"></td>
  <td><img width="576" alt="jwt_provider" src="https://github.com/ReadMeCorporation/user_server_ReadMe/assets/68271830/23c81de0-1f89-4d31-b6d6-afda7adb0e6f"></td> 
 </tr>
</table>

# 발표자료
[3조_파이널.pdf](https://github.com/ReadMeCorporation/user_server_ReadMe/files/11477830/3._.pdf)


# 프로젝트 기간
- 2023-04-10 ~ 2023.05.10

# 팀원 소개
### Back-End
- 권경렬([@kwonkyungryul](https://github.com/kwonkyungryul))
- 임지상([@tero1115](https://github.com/tero1115))
### Front-End
- 장희선([@heesun-b](https://github.com/heesun-b))
- 김유현([@yuhyunkimm](https://github.com/yuhyunkimm))
- 안정훈([@aj124578](https://github.com/aj124578))

# 기술 스택
## IDE
[![IntelliJ IDEA](https://img.shields.io/badge/-IntelliJ%20IDEA-blue?logo=intellij-idea&logoColor=white)](https://www.jetbrains.com/idea/)
[![Android Studio](https://img.shields.io/badge/-Android%20Studio-green?logo=android-studio&logoColor=white)](https://developer.android.com/studio)

## FrameWork
[![Spring Boot](https://img.shields.io/badge/-Spring%20Boot-brightgreen?logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Flutter](https://img.shields.io/badge/-Flutter-blue?logo=flutter&logoColor=white)](https://flutter.dev)


## DB
[![H2](https://img.shields.io/badge/-H2-orange?logo=h2&logoColor=white)](http://www.h2database.com)
[![MySQL](https://img.shields.io/badge/-MySQL-blue?logo=mysql&logoColor=white)](https://www.mysql.com)

## 사용기술
[![SQFLITE](https://img.shields.io/badge/-SQFLITE-blue?logo=flutter&logoColor=white)](https://pub.dev/packages/sqflite)
[![EC2](https://img.shields.io/badge/-EC2-orange?logo=amazon-aws&logoColor=white)](https://aws.amazon.com/ec2/)
[![S3](https://img.shields.io/badge/-S3-yellow?logo=amazon-s3&logoColor=white)](https://aws.amazon.com/s3/)
[![JSP](https://img.shields.io/badge/-JSP-orange?logo=java&logoColor=white)](https://www.oracle.com/java/technologies/javaserverpages.html)
[![RiverPod](https://img.shields.io/badge/-RiverPod-blue?logo=flutter&logoColor=white)](https://pub.dev/packages/riverpod)
[![BootPay](https://img.shields.io/badge/-BootPay-yellow?logo=bootpay&logoColor=white)](https://www.bootpay.co.kr/)
[![Freezed](https://img.shields.io/badge/-Freezed-brightgreen?logo=dart&logoColor=white)](https://pub.dev/packages/freezed)
[![Firebase](https://img.shields.io/badge/-Firebase-yellow?logo=firebase&logoColor=white)](https://firebase.google.com/)
[![OAuth](https://img.shields.io/badge/-OAuth-blue?logo=oauth&logoColor=white)](https://oauth.net/)

## 협업 툴
[![Figma](https://img.shields.io/badge/-Figma-purple?logo=figma&logoColor=white)](https://www.figma.com)
[![Notion](https://img.shields.io/badge/-Notion-black?logo=notion&logoColor=white)](https://www.notion.so)
[![Postman](https://img.shields.io/badge/-Postman-orange?logo=postman&logoColor=white)](https://www.postman.com)
[![Git](https://img.shields.io/badge/-Git-red?logo=git&logoColor=white)](https://git-scm.com)
[![GitHub](https://img.shields.io/badge/-GitHub-black?logo=github&logoColor=white)](https://github.com)
[![JIRA](https://img.shields.io/badge/-JIRA-blue?logo=jira&logoColor=white)](https://www.atlassian.com/software/jira)
[![Slack](https://img.shields.io/badge/-Slack-purple?logo=slack&logoColor=white)](https://slack.com)

## ERD
**전체**

![erd](https://github.com/ReadMeCorporation/user_server_ReadMe/assets/68271830/a2014fa3-7ec9-425a-bc5b-2308351e60b5)


**유저 관련 테이블**

![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/4c0e2dfb-544f-46ba-ae22-e4062f5ae7e6)

**도서 관련 테이블**

![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/d9808944-db68-4366-ad55-a3270f079046)

**결제 관련 테이블**

![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/1f479158-2d32-4c9c-9ad7-ee0610c08159)

## Jira를 이용한 브랜치 전략
- Jira를 이용해 작업 항목을 관리하고 이슈 생성
- 각 이슈에 대해 새로운 브랜치를 생성하고, 해당 브랜치에서 작업을 수행
- 작업이 완료되면 해당 브랜치에서 코드 리뷰 진행. 리뷰어는 변경 사항 확인 후 피드백. 리뷰 완료되면 MERGE.

## 유저 시나리오
![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/6ef610ff-c958-415a-ac21-cb409863666f)
![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/9c2ada18-3b49-4ea2-adc7-4bb09f526ec3)
![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/89f81900-cacd-4761-b565-b4afdad7bb24)
![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/7cb39d8d-c7aa-4301-8b32-b8fb518c29f5)
![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/ea54798f-048a-4d2a-9fa0-563596447c5d)
![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/0ec6d7c3-ed76-4d22-b560-42c96bdb2101)
![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/70d909b9-0024-4a06-b12a-f21d4e646179)
![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/78f107ea-1309-4022-ba9e-d6573eb56a80)
![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/150d0448-230d-4e4c-9fe1-0dde14a07689)
![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/a949919d-1444-4b9e-a96e-0aafcb18a36d)
![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/d943b57d-1b26-44ad-9c13-0ab84027e8ac)
