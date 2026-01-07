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
├── BNK_WAS/ # 메인 웹 애플리케이션 (Spring MVC)
│ ├── controller
│ ├── service
│ ├── mapper
│ ├── repository
│ ├── entity / dto
│ ├── security / config
│ └── templates (Thymeleaf)


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



