# 🏦 BNK 펀드 관리 시스템
> Spring 기반 펀드 상품 안내 및 투자 관리 웹 애플리케이션

---

## 📌 프로젝트 개요

BNK 펀드 관리 시스템은  
**펀드 상품 조회, 회원 관리, 투자 관리 기능을 제공하는 Spring 기반 웹 애플리케이션**입니다.

실제 은행 웹사이트 구조를 참고하여  
단순 CRUD가 아닌 **금융 도메인 흐름을 고려한 설계**를 목표로 개발했습니다.

---

## 🛠 기술 스택

### Backend
- Java 17
- Spring Boot
- Spring MVC
- Spring Security
- MyBatis / JPA
- Oracle Database
- Thymeleaf

### Build & Tool
- Gradle
- Git / GitHub
- Lombok

---

## 📁 프로젝트 구조
```
BNK_WAS/
├── build.gradle                          # Gradle 빌드 설정
├── settings.gradle                       # Gradle 프로젝트 설정
├── README.md                             # 프로젝트 README
│
└── src/
    ├── main/
    │   ├── java/
    │   │   └── kr/co/bnk/bnk_project/
    │   │       ├── BnkProjectApplication.java    # 메인 애플리케이션 클래스
    │   │       │
    │   │       ├── config/                       # 설정 클래스
    │   │       │   ├── AppInfo.java
    │   │       │   ├── BatchTransactionManagerConfig.java
    │   │       │   ├── EditLockSessionListener.java
    │   │       │   ├── GlobalModelAdvice.java
    │   │       │   ├── ReserveBatchConfig.java
    │   │       │   ├── SchedulerConfig.java
    │   │       │   └── WebMvcConfig.java
    │   │       │
    │   │       ├── controller/                   # 컨트롤러 (HTTP 요청 처리)
    │   │       │   ├── admin/                    # 관리자 컨트롤러
    │   │       │   │   ├── AdminController.java
    │   │       │   │   ├── approval/
    │   │       │   │   │   └── AdminApprovalController.java
    │   │       │   │   ├── cs/
    │   │       │   │   ├── info/
    │   │       │   │   ├── member/
    │   │       │   │   ├── product/
    │   │       │   │   └── settings/
    │   │       │   ├── ChatController.java
    │   │       │   ├── ControllerEx.java
    │   │       │   ├── FlutterFundController.java      # Flutter API 컨트롤러
    │   │       │   ├── FundApiController.java
    │   │       │   ├── FundController.java
    │   │       │   ├── GlobalControllerAdvice.java
    │   │       │   ├── MainController.java
    │   │       │   ├── MemberController.java
    │   │       │   ├── mobile/                   # 모바일 API 컨트롤러
    │   │       │   │   ├── FlutterCsController.java
    │   │       │   │   ├── MockAiController.java
    │   │       │   │   └── MockInvestmentController.java
    │   │       │   └── MyController.java
    │   │       │
    │   │       ├── service/                      # 비즈니스 로직
    │   │       │   ├── admin/                    # 관리자 서비스
    │   │       │   │   ├── AdminFundService.java
    │   │       │   │   ├── AdminMemberService.java
    │   │       │   │   ├── ApprovalService.java
    │   │       │   │   ├── EditLockService.java
    │   │       │   │   ├── FundCategoryService.java
    │   │       │   │   ├── InfoPostService.java
    │   │       │   │   ├── PermissionService.java
    │   │       │   │   └── ProductService.java
    │   │       │   ├── mobile/                   # 모바일 서비스
    │   │       │   │   ├── FundOrderFixService.java
    │   │       │   │   ├── FundOrderStartService.java
    │   │       │   │   ├── FundSubscriptionService.java
    │   │       │   │   ├── MockAiDiagnosisService.java
    │   │       │   │   └── MockInvestmentService.java
    │   │       │   ├── ChatBotService.java
    │   │       │   ├── CsService.java
    │   │       │   ├── EmailService.java
    │   │       │   ├── FundService.java
    │   │       │   ├── GeminiService.java
    │   │       │   ├── InvestmentService.java
    │   │       │   ├── KeywordService.java
    │   │       │   ├── MemberService.java
    │   │       │   ├── MyFundService.java
    │   │       │   ├── UserTermsService.java
    │   │       │   └── WishListService.java
    │   │       │
    │   │       ├── mapper/                       # MyBatis 매퍼 인터페이스
    │   │       │   ├── admin/
    │   │       │   │   ├── AdminFundMapper.java
    │   │       │   │   ├── AdminMemberMapper.java
    │   │       │   │   ├── ApprovalMapper.java
    │   │       │   │   ├── FundCategoryMapper.java
    │   │       │   │   ├── FundMasterRevisionMapper.java
    │   │       │   │   ├── InfoAttachmentMapper.java
    │   │       │   │   ├── InfoPostMapper.java
    │   │       │   │   ├── PermissionMapper.java
    │   │       │   │   └── ProductMapper.java
    │   │       │   ├── mobile/
    │   │       │   │   ├── FundOrderMapper.java
    │   │       │   │   ├── FundPlanMapper.java
    │   │       │   │   ├── FundPositionMapper.java
    │   │       │   │   ├── FundTransactionMapper.java
    │   │       │   │   ├── MockAccountMapper.java
    │   │       │   │   └── MockInvestmentMapper.java
    │   │       │   ├── AdminMapper.java
    │   │       │   ├── CsMapper.java
    │   │       │   ├── FundMapper.java
    │   │       │   ├── KeywordMapper.java
    │   │       │   ├── LoginHistoryMapper.java
    │   │       │   ├── MemberMapper.java
    │   │       │   ├── RiskTestMapper.java
    │   │       │   ├── UserTermsMapper.java
    │   │       │   └── WishListMapper.java
    │   │       │
    │   │       ├── repository/                   # JPA 리포지토리
    │   │       │   ├── CsRepository.java
    │   │       │   ├── FundRepository.java
    │   │       │   ├── RiskTestResultRepository.java
    │   │       │   └── temp.java
    │   │       │
    │   │       ├── dto/                          # 데이터 전송 객체
    │   │       │   ├── admin/                    # 관리자 DTO
    │   │       │   │   ├── AdminFundMasterDTO.java
    │   │       │   │   ├── AdminListDTO.java
    │   │       │   │   ├── ApprovalDTO.java
    │   │       │   │   ├── FieldChangeDTO.java
    │   │       │   │   ├── FundAssetAllocationDTO.java
    │   │       │   │   ├── FundCategoryDTO.java
    │   │       │   │   ├── FundDocumentDTO.java
    │   │       │   │   ├── FundListDetailDTO.java
    │   │       │   │   ├── FundMasterRevisionDTO.java
    │   │       │   │   ├── FundPriceHistoryDTO.java
    │   │       │   │   ├── FundReturnHistoryDTO.java
    │   │       │   │   ├── FundSettlementHistoryDTO.java
    │   │       │   │   ├── InfoAttachmentDTO.java
    │   │       │   │   ├── InfoPostDTO.java
    │   │       │   │   ├── MemberListDTO.java
    │   │       │   │   ├── ProductListDTO.java
    │   │       │   │   └── UserSearchDTO.java
    │   │       │   ├── mobile/                   # 모바일 DTO
    │   │       │   │   ├── FundOrderDTO.java
    │   │       │   │   ├── FundPlanDTO.java
    │   │       │   │   ├── FundPositionDTO.java
    │   │       │   │   ├── FundSubscriptionRequestDTO.java
    │   │       │   │   ├── FundTransactionDTO.java
    │   │       │   │   ├── MockAccountDTO.java
    │   │       │   │   └── MockUserInvestmentDto.java
    │   │       │   ├── BnkAdminDTO.java
    │   │       │   ├── BnkUserDTO.java
    │   │       │   ├── CsDTO.java
    │   │       │   ├── FundChartDTO.java
    │   │       │   ├── FundMasterDTO.java
    │   │       │   ├── FundPeriodDTO.java
    │   │       │   ├── FundPriceDTO.java
    │   │       │   ├── FundSearchDTO.java
    │   │       │   ├── InvestmentResultDTO.java
    │   │       │   ├── InvestmentSurveyDTO.java
    │   │       │   ├── KeywordDTO.java
    │   │       │   ├── LoginHistoryDTO.java
    │   │       │   ├── MemberUpdateDTO.java
    │   │       │   ├── MyFundResponse.java
    │   │       │   ├── PageRequestDTO.java
    │   │       │   ├── PageResponseDTO.java
    │   │       │   ├── ProductDTO.java
    │   │       │   ├── RiskTestResultDTO.java
    │   │       │   ├── UserFundDTO.java
    │   │       │   └── UserTermsDTO.java
    │   │       │
    │   │       ├── entity/                       # JPA 엔티티
    │   │       │   ├── Cs.java
    │   │       │   ├── FundMaster.java
    │   │       │   ├── RiskTestResult.java
    │   │       │   └── temp.java
    │   │       │
    │   │       ├── security/                     # 보안 설정
    │   │       │   ├── AdminLoginSuccessHandler.java
    │   │       │   ├── AdminSecurityService.java
    │   │       │   ├── AdminUserDetails.java
    │   │       │   ├── LoginSuccessHandler.java
    │   │       │   ├── MyUserDetails.java
    │   │       │   ├── SecurityConfig.java
    │   │       │   └── UserSecurityService.java
    │   │       │
    │   │       ├── scheduler/                    # 스케줄러
    │   │       │   └── FundOrderScheduler.java   # 펀드 주문 배치 처리
    │   │       │
    │   │       ├── exception/                    # 예외 클래스
    │   │       │   └── DuplicateFundSubscriptionException.java
    │   │       │
    │   │       ├── interceptor/                  # 인터셉터
    │   │       │   └── FundAccessInterceptor.java
    │   │       │
    │   │       ├── socket/                       # 소켓 통신
    │   │       │   └── TcpClient.java
    │   │       │
    │   │       └── util/                         # 유틸리티
    │   │           └── HolidayUtil.java          # 휴일 처리 유틸
    │   │
    │   └── resources/
    │       ├── application.yml                   # 애플리케이션 설정
    │       │
    │       ├── mappers/                          # MyBatis XML 매퍼
    │       │   ├── admin/
    │       │   │   ├── AdminFundMapper.xml
    │       │   │   ├── AdminMemberMapper.xml
    │       │   │   ├── AdminProductMapper.xml
    │       │   │   ├── ApprovalMapper.xml
    │       │   │   ├── FundCategofyMapper.xml
    │       │   │   ├── FundMasterRevisionMapper.xml
    │       │   │   ├── InfoAttachment.xml
    │       │   │   └── InfoMapper.xml
    │       │   ├── mobile/
    │       │   │   ├── FundOrderMapper.xml
    │       │   │   ├── FundPlanMapper.xml
    │       │   │   ├── FundPositionMapper.xml
    │       │   │   ├── FundTransactionMapper.xml
    │       │   │   ├── MockAccountMapper.xml
    │       │   │   └── MockInvestmentMapper.xml
    │       │   ├── AdminMapper.xml
    │       │   ├── CsMapper.xml
    │       │   ├── FundMapper.xml
    │       │   ├── KeywordMapper.xml
    │       │   ├── LoginHistoryMapper.xml
    │       │   ├── MemberMapper.xml
    │       │   ├── PermissionMapper.xml
    │       │   ├── RiskTestMapper.xml
    │       │   ├── UserTermsMapper.xml
    │       │   └── WishListMapper.xml
    │       │
    │       ├── templates/                        # Thymeleaf 템플릿
    │       │   ├── admin/                        # 관리자 페이지
    │       │   ├── member/                       # 회원 페이지
    │       │   ├── my/                           # 마이페이지
    │       │   ├── chatbot.html
    │       │   ├── dopisitGuide.html
    │       │   ├── FAQ.html
    │       │   ├── fundGuide.html
    │       │   ├── fundInformation.html
    │       │   ├── fundSihwang.html
    │       │   ├── fundSusi.html
    │       │   ├── gaip.html
    │       │   ├── index.html
    │       │   ├── investorInfo.html
    │       │   ├── investTest.html
    │       │   ├── productDetail.html
    │       │   ├── productList.html
    │       │   ├── searchResult.html
    │       │   └── sidebar.html
    │       │
    │       └── static/                           # 정적 리소스
    │           ├── css/                          # 스타일시트
    │           ├── js/                           # JavaScript
    │           └── images/                       # 이미지
    │
    └── test/                                     # 테스트 코드
        └── java/
            └── kr/co/bnk/bnk_project/
                └── BnkProjectApplicationTests.java
```


## ✨ 주요 기능

### 👤 고객 기능
- 펀드 상품 목록 / 상세 조회
- 펀드 수익률 및 NAV 조회
- 투자 성향 조사

### 🛠 관리자 기능
- 펀드 상품 목록 / 상세 조회
- 펀드 상품 등록 / 수정 / 승인 / 반영예약
- 회원 관리 / 고객센터
- 펀드 카테고리 / 검색어 관리



