# ReadMe App

![readme_long](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/cb87d3f4-2a02-47d1-8894-371e7954dd02)


# 앱 소개
`Read Me`는 **최고의 독서 경험을 선사하는 전자책 플랫폼 앱**입니다.</br>
다양한 장르의 책을 제공하며 편리한 인터페이스로 책 읽기를 더욱 즐겁게 만들어줍니다.</br>
수많은 전자책을 한 곳에서 쉽게 찾아 읽을 수 있는 편리함과 함께 즐겨찾은 페이지를 표시하고,</br> 읽은 책에 대한 리뷰를 공유하는 등 독서를 더욱 풍부하게 즐길 수 있습니다. </br>

# 핵심기능
> **부트페이**</br>
* gradle 의존성
```gradle
implementation 'io.github.bootpay:backend:+'
```
* 개념</br>
  * 부트페이는 개발사들이 쉽게 결제를 연동할 수 있도록 다양한 플랫폼별 SDK를 제공합니다.</br>
  * 개발환경, PG에 상관없이 쉬운 문법, 적은 개발 공수를 제공하고,</br>
  * 구매자가 결제 진행함에 있어 불편한 시나리오가 없도록 끊임없이 개선하고 있습니다.</br>
  * 또한 결제 오류나 검증이 개발사 실수로 인해 결제처리가 누락되지 않도록 진화된 SDK와 아키텍쳐를 제공하고 있습니다.</br></br>
![image](https://github.com/ReadMeCorporation/user_server_ReadMe/assets/68271830/81834691-9b5a-4d09-b9ed-6773bc3abb19)

* 적용</br>
  * [1] 클라이언트가 결제 요청을 클릭시 먼저 서버에 결제 데이터를 저장하고 결제번호를 응답받습니다.</br>
  * [2] 해당 id 값을 부트페이 서버에 전송합니다.</br>
  * [3] 클라이언트에서 결제 완료를 하고 부트페이 서버에서 스프링 서버로 결제 대기 상태의 웹훅 통지를 날립니다.</br>
  * [4] 웹훅 통지 안의 가격과 클라이언트를 통해 넘겨받은 결제번호로 검증합니다.</br>
  * [5] 서버 검증을 마치고 정상적이면 부트페이에 `결제승인처리` 데이터를 전송하고 실패하면 `결제승인취소`를 보냅니다. </br>
  * [6] 결제 승인 처리, 실패처리가 되면 부트 페이 서버에서 클라이언트와 서버쪽으로 데이터를 보내고 클라이언트는 결제 창이 닫히고 정상적으로 결제처리를 완료시킵니다.</br></br>

![image](https://github.com/ReadMeCorporation/user_server_ReadMe/assets/68271830/9a0c3dd6-ea6c-4200-a0f6-90e12a8b249d)

<!-- > **FCM**
* 개념</br>

* 적용</br> -->

---
> **S3**</br>
* gradle 의존성
```gradle
implementation 'org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE'
```
* 개념</br>
  * AWS S3(Amazon Simple Storage Service)는 아마존 웹 서비스(Amazon Web Services)에서 제공하는 클라우드 기반의 객체 스토리지 서비스입니다.</br>
  * S3는 인터넷을 통해 데이터를 저장하고 검색할 수 있는 스토리지 솔루션으로, 안정적이고 확장 가능한 저장소를 제공하여 다양한 용도로 활용됩니다.</br>

* 적용</br>
  * S3 SDK를 설정하고 인스턴스를 생성하는 Configuration을 생성합니다.</br>
  * 생성된 인스턴스로 S3 putObject 메서드를 호출해 버킷과 파일이름, 파일의 용량을 S3에 업로드. 이후 getUrl메서드를 리턴해 해당 파일의 경로를 받아옵니다.</br>
  * S3 버킷의 폴더경로로 파일의 출처를 구분짓고 받아온 파일의 경로를 리턴합니다.</br>

```java
@Component
public class S3Upload {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public S3Upload(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String upload(MultipartFile multipartFile, String dir) throws IOException {
        String s3FileName = dir;
        s3FileName += UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());
        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);

        return amazonS3.getUrl(bucket, s3FileName).toString();
    }
}
```

---
> **Security & OAuth 2.0**</br>
* gradle 의존성
```gradle
implementation 'com.google.firebase:firebase-admin:9.1.1'
implementation 'org.springframework.boot:spring-boot-starter-security'
```
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

# 기술 스택
## IDE
[![IntelliJ IDEA](https://img.shields.io/badge/-IntelliJ%20IDEA-blue?logo=intellij-idea&logoColor=white)](https://www.jetbrains.com/idea/)

## FrameWork
[![Spring Boot](https://img.shields.io/badge/-Spring%20Boot-brightgreen?logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)

## DB
[![H2](https://img.shields.io/badge/-H2-orange?logo=h2&logoColor=white)](http://www.h2database.com)
[![MySQL](https://img.shields.io/badge/-MySQL-blue?logo=mysql&logoColor=white)](https://www.mysql.com)

## 사용기술
[![EC2](https://img.shields.io/badge/-EC2-orange?logo=amazon-aws&logoColor=white)](https://aws.amazon.com/ec2/)
[![S3](https://img.shields.io/badge/-S3-yellow?logo=amazon-s3&logoColor=white)](https://aws.amazon.com/s3/)
![JPA](https://github.com/kwonkyungryul/user_server_ReadMe/assets/68271830/e03f90f7-ae4f-4dbe-9918-281f35580383)
[![JSP](https://img.shields.io/badge/-JSP-orange?logo=java&logoColor=white)](https://www.oracle.com/java/technologies/javaserverpages.html)
[![BootPay](https://img.shields.io/badge/-BootPay-yellow?logo=bootpay&logoColor=white)](https://www.bootpay.co.kr/)
[![Firebase](https://img.shields.io/badge/-Firebase-yellow?logo=firebase&logoColor=white)](https://firebase.google.com/)
[![OAuth](https://img.shields.io/badge/-OAuth-blue?logo=oauth&logoColor=white)](https://oauth.net/)
![Junit](https://github.com/kwonkyungryul/user_server_ReadMe/assets/68271830/39202382-e8c9-4247-8c92-c2068b6b5fac)
![Mockito](https://github.com/kwonkyungryul/user_server_ReadMe/assets/68271830/8bf7d8a1-3e10-472a-8965-50ad410c7408)

## 협업 툴
[![Figma](https://img.shields.io/badge/-Figma-purple?logo=figma&logoColor=white)](https://www.figma.com)
[![Notion](https://img.shields.io/badge/-Notion-black?logo=notion&logoColor=white)](https://www.notion.so)
[![Git](https://img.shields.io/badge/-Git-red?logo=git&logoColor=white)](https://git-scm.com)
[![GitHub](https://img.shields.io/badge/-GitHub-black?logo=github&logoColor=white)](https://github.com)
[![JIRA](https://img.shields.io/badge/-JIRA-blue?logo=jira&logoColor=white)](https://www.atlassian.com/software/jira)
[![Slack](https://img.shields.io/badge/-Slack-purple?logo=slack&logoColor=white)](https://slack.com)

## 기타 툴
[![Postman](https://img.shields.io/badge/-Postman-orange?logo=postman&logoColor=white)](https://www.postman.com)

## ERD
**전체**

![erd](https://github.com/ReadMeCorporation/user_server_ReadMe/assets/68271830/a2014fa3-7ec9-425a-bc5b-2308351e60b5)


**유저 관련 테이블**

![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/4c0e2dfb-544f-46ba-ae22-e4062f5ae7e6)

**도서 관련 테이블**

![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/d9808944-db68-4366-ad55-a3270f079046)

**결제 관련 테이블**

![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/1f479158-2d32-4c9c-9ad7-ee0610c08159)

## Jira를 이용한 브랜치 전략 & Slack을 이용한 협업
- Jira를 이용해 작업 항목을 관리하고 이슈 생성
- 각 이슈에 대해 새로운 브랜치를 생성하고, 해당 브랜치에서 작업을 수행
- 작업이 완료되면 해당 브랜치에서 코드 리뷰 진행. 리뷰어는 변경 사항 확인 후 피드백. 리뷰 완료되면 MERGE.
- Slack으로 Github PR요청 또는 Issue사항 확인

![image](https://github.com/kwonkyungryul/user_server_ReadMe/assets/68271830/2e580d9d-c772-4792-a759-0b6c8c6baa67)
![image](https://github.com/kwonkyungryul/user_server_ReadMe/assets/68271830/64df3710-1385-4fc1-90db-965204b5bb88)

---
## 유저 시나리오
![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/6ef610ff-c958-415a-ac21-cb409863666f)
> CommonService
```java
    @Transactional
    public String getUser(FirebaseRequest request) {
        FirebaseToken firebaseToken = null;
        try {
            firebaseToken = FirebaseAuth.getInstance().verifyIdToken(request.getIdToken());
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            throw new Exception400("잘못된 FirebaseToken 입니다.");
        }

        User user = null;
        Optional<User> optionalUser = userRepository.findByUsername(firebaseToken.getEmail());
        if (optionalUser.isEmpty()) {
            user = userRepository.save(new User(null, firebaseToken.getEmail(), UUID.randomUUID().toString(), RoleType.USER.name(), false, false));
        } else {
            user = optionalUser.get();
        }
        return MyJwtProvider.create(user);
    }
```

```java
public class MyJwtProvider {

    private static final String SUBJECT = "ReadMeCorpJWT";
    private static final int EXP = 1000 * 60 * 60* 24;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
    private static final String SECRET = System.getenv("JWT_SECRET");

    public static String create(User user) {
        String jwt = JWT.create()
                .withSubject(SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXP))
                .withClaim("id", user.getId())
                .withClaim("role", user.getRole().name())
                .sign(Algorithm.HMAC512(SECRET));
        return TOKEN_PREFIX + jwt;
    }

    public static DecodedJWT verify(String jwt) throws SignatureVerificationException, TokenExpiredException {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET))
                .build().verify(jwt);
        return decodedJWT;
    }
}
```

```
프론트로부터 받아온 FirebaseToken을 Firebase 서버에 검증요청 후 유효한 토큰이라면
토큰안의 로그인하는 유저의 email로 유저 테이블에 조회를 합니다.
조회 결과가 존재한다면 해당 정보로 JWT 토큰을 생성 후 리턴합니다.
조회 결과가 존재하지 않는다면 Firebase의 토큰안의 email을 토대로 회원가입을 진행시킨 후 결과로 JWT 토큰을 생성 후 리턴합니다
```

---

![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/9c2ada18-3b49-4ea2-adc7-4bb09f526ec3)
![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/ea54798f-048a-4d2a-9fa0-563596447c5d)
> CommonService
```java
    public MetaDTO getMetaData(MyUserDetails myUserDetails) {
        List<BigCategoryDTO> categories = categoryService.getCategories();
        UserDTO userDTO = null;
        String jwt = "";
        if (myUserDetails != null) {
            userDTO = userService.getUser(myUserDetails.getUser());
            jwt = MyJwtProvider.create(myUserDetails.getUser());
        }

        List<CommonDTO> storageBoxTabList = Arrays.stream(StorageBoxType.values())
                .map(storageBoxType -> new CommonDTO(storageBoxType.getName(), storageBoxType.getRequestName()))
                .collect(Collectors.toList());

        List<CommonDTO> mainTabList = Arrays.stream(MainTabType.values())
                .map(mainTabType -> new CommonDTO(mainTabType.getName(), mainTabType.getRequestName()))
                .collect(Collectors.toList());

        List<CommonDTO> paymentTabList = Arrays.stream(PaymentTabType.values())
                .map(paymentTabType -> new CommonDTO(paymentTabType.getName(), paymentTabType.getRequestName()))
                .collect(Collectors.toList());

        List<String> notificationTypes = Arrays.stream(NotificationType.values()).map(Enum::name).collect(Collectors.toList());

        return MetaDTO.builder()
                .bigCategory(categories)
                .user(userDTO)
                .jwt(jwt)
                .storageBoxTabs(storageBoxTabList)
                .mainTabs(mainTabList)
                .paymentTabs(paymentTabList)
                .notificationTypes(notificationTypes)
                .build();
    }
```

```java
    public MembershipPaymentNoneUserDTO getMyPage(MyUserDetails myUserDetails) {
        Optional<User> optionalUser = userRepository.findById(myUserDetails.getUser().getId());
        if (optionalUser.isEmpty()) {
            throw new Exception400(UserConst.notFound);
        }
        User user = optionalUser.get();
        Optional<MembershipPayment> optionalMembershipPayment = membershipPaymentRepository.findByUserIdAndStatusNot(user.getId(), PaymentStatus.DELETE);

        MembershipPaymentNoneUserDTO noneUserDTO = null;

        if (optionalMembershipPayment.isPresent()) {
            noneUserDTO = optionalMembershipPayment.get().toNoneUserDTO();
        }

        return noneUserDTO;
    }
```

```
앱 최초 로드시에 '공통(정적) 데이터(탭 이름 등)', '로그인 데이터'를 해당 페이지 로드시마다 요청하지 않게
한 번에 프론트에 전송해준다.(앱은 SQFlite 에 저장 후 사용)
만약 로그인을 한 상태이면(myUserDetails != null) 해당 유저의 정보로 jwt 토큰을 생성해준다.
```

> MyUserDetails
```java
@Setter
@Getter
public class MyUserDetails implements UserDetails {
    private User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> user.getRole().name());
        return collector;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
```

```
MyUserDeatils는 직접 구현한 UserDetails 입니다.
UserDeatils를 상속받아 타입을 일치시켜주고 SecurityContextHolder에 등록할 수 있게 합니다.
```

> BookService
```java
 public Page<BookDTO> getPage (
        Integer bigCategoryId,
        Integer smallCategoryId,
        Pageable pageable,
        String status,
        MyUserDetails myUserDetails
    ) {
        Page<Book> page = new PageImpl<>(List.of(), pageable, 0);

        // 전체 (bigCategoryId, smallCategoryID)
        if (status.equals(MainTabType.ALL.getRequestName())) {
            if (bigCategoryId != 0) {
                if (smallCategoryId != 0) {
                    page = bookRepository.findByStatusAndSmallCategoryId(BookStatus.ACTIVE, smallCategoryId, pageable);
                } else {
                    List<Integer> smallCategoryIds = smallCategoryRepository.findByBigCategoryId(bigCategoryId).stream().map(SmallCategory::getId).collect(Collectors.toList());
                    page = bookRepository.findByStatusAndSmallCategoryIdIn(BookStatus.ACTIVE, smallCategoryIds, pageable);
                }
            } else {
                page = bookRepository.findByStatus(pageable, BookStatus.ACTIVE);
            }

        // heart 가 많은 순
        } else if (status.equals(MainTabType.RECOMMEND.getRequestName())) {
            page = bookRepository.findByBookHeartCount(pageable, BookStatus.ACTIVE);

        // bestSeller -> payment 판매순
        } else if (status.equals(MainTabType.BESTSELLER.getRequestName())) {
            page = bookRepository.findByBookPaymentDESC(pageable, BookStatus.ACTIVE);

        // 신간 OrderBy id Desc
        } else if (status.equals(MainTabType.NEW.getRequestName())) {
            page = bookRepository.findByStatusOrderByIdDesc(pageable, BookStatus.ACTIVE);

        }

        return page.map(book -> getBookDTO(book, myUserDetails));
    }
```

```
하나의 엔드포인트로 Query Parameter의 유무에 따라 조회하는 쿼리문이 달라집니다.
Query Parameter로 받아온 status가 ALL이라면 Query Parameter의 bigCategoryId, smallCategoryId가 0인지 0이 아닌지에 따라
전체조회를 할 것인지 대분류 조회를 할 것인지 소분류 조회를 할 것인지 분기합니다.
status가 RECOMMEND라면 책에 대한 Heart(좋아요)가 많은 순으로 조회합니다.(추천)
status가 BESTSELLER라면 책에 대한 판매가 가장 많은 순으로 조회합니다.(베스트셀러)
status가 NEW라면 최근 등록된 순서로 조회합니다.(신간)
```
**<i>동적으로 파라미터를 쉽게 받아서 사용하는 방법으로 변경할 예정[QueryDSL]</i>**
> bookService
```java
...
Double stars = reviewRepository.findAvgStars(bookDTO.getId());
if (stars != null) {
    bookDTO.setStars(Math.ceil((stars * 10) / 10));
} else {
    bookDTO.setStars(0.0);
}

bookDTO.setIsHeart(false);
if (myUserDetails != null) {
    User user = myUserDetails.getUser();
    Optional<Heart> optionalHeart = heartRepository.heartCount(book.getId(), user.getId());
    if (optionalHeart.isPresent()) {
        bookDTO.setIsHeart(true);
    }
}
...
```

```
도서 조회에 들어가는 공통 로직입니다.
책에 대한 리뷰의 평균값, 로그인 한 유저가 좋아요를 했는지의 여부를 조회합니다.
```

![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/89f81900-cacd-4761-b565-b4afdad7bb24)
> BookService
```java
    private BookDTO getBookDTO(Book book, MyUserDetails myUserDetails) {
        BigCategory bigCategory = book.getSmallCategory().getBigCategory();
        BookDTO bookDTO = book.toDTO();
        List<File> epubFiles = fileRepository.findByFileInfo_Id(book.getEpub().getId());
        if (epubFiles.size() == 0) {
            bookDTO.setEpubFile(BookConst.defaultBookFileDTO);
        } else {
            bookDTO.setEpubFile(epubFiles.get(0).toDTO());
        }

        List<File> coverFiles = fileRepository.findByFileInfo_Id(book.getCover().getId());
        if (epubFiles.size() == 0) {
            bookDTO.setCoverFile(BookConst.defaultBookFileDTO);
        } else {
            bookDTO.setCoverFile(coverFiles.get(0).toDTO());
        }

        Double stars = reviewRepository.findAvgStars(bookDTO.getId());
        if (stars != null) {
            bookDTO.setStars(Math.ceil((stars * 10) / 10));
        } else {
            bookDTO.setStars(0.0);
        }

        bookDTO.setIsHeart(false);
        if (myUserDetails != null) {
            User user = myUserDetails.getUser();
            Optional<Heart> optionalHeart = heartRepository.heartCount(book.getId(), user.getId());
            if (optionalHeart.isPresent()) {
                bookDTO.setIsHeart(true);
            }
        }
        bookDTO.setBigCategory(bigCategory.toSingleDTO());
        return bookDTO;
    }
```

```
받아온 책의 정보로 전자책파일(epub)과 표지사진을 조회하고 해당 책의 평점을 조회합니다.
유저가 로그인을 한 상태이면 해당 책에 좋아요를 했는지에 대한 정보를 받아줍니다.(로그인을 하지 않았다면 false)
공통 파일 테이블은 파일이 여러개인데 epub, cover는 1개의 데이터만 저장되기 때문에 index를 0으로 고정합니다.[어드민과의 약속]
```

![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/0ec6d7c3-ed76-4d22-b560-42c96bdb2101)
> CommonController [ADMIN]
```java
    @GetMapping("/push")
    public ResponseEntity<?> push (
            @RequestParam String token,
            String type,
            String data,
            String title,
            String message
    ) {
        firebaseConfig.sendPushNotification(token, title, message, type, data);
        return ResponseEntity.ok(new ResponseDTO<>(1, "성공", "OK"));
    }
```

```java
    public boolean sendPushNotification(String token, String title, String body, String notificationType, String notificationData) {

        // FCM 메시지 생성
        Message message = Message.builder()
                .setToken(token)
                .putData("title", title)
                .putData("body", body)
                .putData("notificationType", notificationType)
                .putData("notificationData", notificationData)
                .build();

        // FCM 서버로 메시지 전송
        try {
            ApiFuture<String> send = FirebaseMessaging.getInstance().sendAsync(message);
            String response = send.get();
            System.out.println("Successfully sent message: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
```

```
요청 시 사용자의 Firbase Token, 알림의 타입(공지, 결제, 관리자, 광고), 데이터(index번호), 제목, 내용을 Query Parameter로 받고
Message 객체를 생성해 FCM서버로 전송합니다.
앱에서는 알림 타입과 데이터로, 알림 터치 시에 이동될 경로를 설정해줍니다.
```

![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/70d909b9-0024-4a06-b12a-f21d4e646179)
> BookService
```java
public List<BookDTO> getSearch(
            String keyword,
            MyUserDetails myUserDetails
    ) {
        return bookRepository.findByStatusNotAndTitleLike(BookStatus.DELETE, "%" + keyword + "%").stream()
                .map(book -> {
                    return getBookDTO(book, myUserDetails);
                })
                .collect(Collectors.toList());
    }
```

```
키워드를 Query Parameter로 받아주고 Like쿼리로 해당하는 정보를 조회합니다.
통합검색을 시도했는데 속도가 너무 느려지는 이슈가 발생해 해결법을 찾고 있는 중입니다.
```

![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/78f107ea-1309-4022-ba9e-d6573eb56a80)
> BookController
```java
    @GetMapping("/books/my")
    public ResponseEntity<ResponseDTO<List<BookPaymentDTO>>> getMyPaymentBook(
            @AuthenticationPrincipal MyUserDetails myUserDetails
    ) {
        List<BookPaymentDTO> bookPayments = bookPaymentService.getMyPaymentList(
                myUserDetails.getUser(),
                bookService,
                fileService
        );
        return ResponseEntity.ok(new ResponseDTO<>(1, "보관함 - 구매내역 조회가 완료되었습니다.", bookPayments));
    }
```
> BookService
```java
    public List<BookPaymentDTO> getMyPaymentList(User user, BookService bookService, FileService fileService) {
        return bookPaymentRepository.findByStatusNotAndUser(PaymentStatus.DELETE, user)
                .stream()
                .map(BookPayment::toDTO)
                .peek(bookPaymentDTO -> {
                        Optional<Book> optionalBook = bookService.getBook(bookPaymentDTO.getBook().getId());
                        if (optionalBook.isEmpty()) {
                            throw new Exception400(BookConst.notFound);
                        }
                        Book book = optionalBook.get();
                        Optional<FileDTO> optionalFile = fileService.getFile(book.getCover().getId());
                        if (optionalFile.isEmpty()) {
                            bookPaymentDTO.getBook().setCoverFile(PaymentConst.defaultBookFileDTO);
                        } else {
                            bookPaymentDTO.getBook().setCoverFile(optionalFile.get());
                        }
                    }).collect(Collectors.toList());
    }
```

```
서비스에서 서비스를 호출했을 시 순환참조 문제가 생길 수 있어 서비스를 매개변수로 받아주었습니다.
인증된 유저 정보로 Book 테이블을 조회하고 stream framework를 이용해 map 메서드로 응답해줄 타입으로 변환해주었습니다.
peek(중간처리 메서드) 반복문을 돌려 조회된 리스트들(결제내역)의 도서정보가 현재 존재하는지와, 존재한다면 화면에 필요한 사진정보를 set해줍니다.
이후 최종처리 메서드로 해당 데이터를 List로 변경후 리턴합니다.

```

**peek와 forEach** </br>

`peek`는 중간처리 메서드 이하 중간연산 입니다.</br>
`forEach`는 최종처리 메서드 이하 최종연산 입니다.</br></br>
두 메서드 전부 반복문을 돌리는 것은 동일합니다.</br>
`peek`는 중간연산이기 때문에 최종연산이 존재하지 않으면 동작하지 않습니다.</br>
`forEach`는 최종연산이기 때문에 이후 또다른 연산을 할 수 없습니다.</br></br>
위 코드에서, 조회 결과 List타입을 stream()을 이용해 타입을 Stream으로 변경해 주었습니다. -> 실제 데이터(Entity)를 DTO타입으로 변환하기 위한 과정</br>
실제 데이터를 map()을 이용해 DTO로 변환 후 데이터를 set하는 과정에서 반복문 중간연산이 필요하고 Stream타입을 List로 변경할 최종 연산이 필요합니다.</br>
`forEach`를 사용해 데이터를 set하게 되면 연산이 끝나고 최종적으로 Stream이 닫히게 됩니다.(List로 변환 불가)</br>
따라서, `peek`를 이용해 중간연산을 하고 최종적으로 collect(Collectors.toList())메서드로 Stream타입을 List타입으로 변경합니다.

---

![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/a949919d-1444-4b9e-a96e-0aafcb18a36d)
> BootPayController
```java

    @Value("${bootpay.restapikey}")
    private String restApiKey;

    @Value("${bootpay.privatekey}")
    private String privateKey;
    
    @PostMapping("/callback")
    public ResponseEntity<HashMap<String, Boolean>> bootPayCallBack(
            @RequestBody BootPaySaveRequest request,
            @AuthenticationPrincipal MyUserDetails myUserDetails
            ) {

        Bootpay bootpay = new Bootpay(restApiKey, privateKey);

        BootPayDTO bootPayDTO;
        if (request.getStatus() == 0) { // 결제 대기 상태
            bootPayDTO = bootPayService.save(request);

            // 토큰 검증
            String accessToken = null;
            try {
                accessToken = bootPayService.getAccessToken(bootpay, restApiKey, privateKey);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception500("토큰 발급에 실패하였습니다.");
            }

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", accessToken);

            // 영수증 ID 검증
            try {
                bootPayService.confirmReceiptId(bootpay, request.getReceiptId());
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception500("ReceiptId 검증에 실패하였습니다.");
            }

            Integer paymentId = request.getMetadata().getPaymentId();
            List<BookPaymentDTO> bookPaymentList = bookPaymentService.getBookPayments(paymentId, myUserDetails.getUser());

            String confirmResult = bootPayService.confirmPriceAndType(bootPayDTO, bookPaymentList, request);
            if (!confirmResult.equals("success")) {
                throw new Exception400(confirmResult);
            }
        } else if (request.getStatus() == 1) { // 결제 완료 상태
            bootPayService.save(request);
        } else if (request.getStatus() == 2) { // 결제 승인중인 상태(서버 검증 전)
            bootPayService.save(request);
        } else if (request.getStatus() == 20) { // 결제 취소 상태
            bootPayService.save(request);
        }

        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        var map = new HashMap<String, Boolean>();
        map.put("success", true);

        return ResponseEntity.ok(map);
    }
```

```
부트페이 관리자 계정에 등록해놓은 callback url로 웹훅 통지를 받습니다.
웹훅 통지 안의 status값으로 현재 결제 상태를 구분짓고 로직을 분기시킵니다.
대기 상태에서는 해당 정보를 먼저 save해주고 EC2 환경변수에 설정해놓은 키값들을 이용해 토큰을 발급하고 해당 토큰을 부트페이 서버에 검증 요청합니다.
이후 해당 토큰이 유효하다면 응답받은 access_token을 헤더에 심어줍니다.
웹훅통지안의 receipt_id(영수증 id)가 유효한 id인지 부트페이 서버에 검증 요청합니다.
유효하다면 이후 앱에서 전달해준 고유 번호를 웹훅통지안에서 꺼내, 서버의 데이터베이스에 조회해 결제 금액, 결제 타입을 검증합니다.
모두 정상적으로 검증이 완료되었다면 부트페이 서버에 성공을 응답해줍니다.
```

![image](https://github.com/ReadMeCorporation/app_ReadMe/assets/68271830/d943b57d-1b26-44ad-9c13-0ab84027e8ac)
> CartController
```java
@PostMapping
    public ResponseEntity<ResponseDTO<CartResponse>> saveCart (
            @AuthenticationPrincipal MyUserDetails myUserDetails,
            @Valid @RequestBody CartSaveRequest request,
            Errors error
    ) {
        if (error.hasErrors()) {
            throw new Exception400(error.getAllErrors().get(0).getDefaultMessage());
        }

        Optional<Book> optionalBook = bookService.getBook(request.getBookId());
        if (optionalBook.isEmpty()) {
            throw new Exception400("책 정보를 찾을 수 없습니다.");
        }

        if (cartService.isCart(optionalBook.get(), myUserDetails.getUser())) {
            throw new Exception400("이미 장바구니에 있는 책입니다.");
        }

        List<Integer> myBookIds = bookPaymentService.getMyList(myUserDetails.getUser())
                                                    .stream()
                                                    .map(BookPaymentDTO::getId)
                                                    .collect(Collectors.toList());

        if (myBookIds.contains(request.getBookId())) {
            throw new Exception400("이미 구매한 도서가 존재합니다.");
        }

        UserDTO userDTO = userService.getUser(myUserDetails.getUser());
        return ResponseEntity.ok(new ResponseDTO<>(1, "장바구니 등록 성공", cartService.save(userDTO.toEntity(), optionalBook.get())));
    }
```

```
장바구니에 등록할 때, 등록하는 책의 정보가 유효한 책인지 검증해줍니다.
이후 이미 담겨있는 책인지 검증합니다.
마지막으로 이미 구매한 도서인지 검증합니다.
모든 검증이 끝나면 장바구니에 등록합니다.
```
