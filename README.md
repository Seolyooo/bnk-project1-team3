# 펀드 상품 안내 시스템
 Spring 기반 펀드 상품 안내 및 투자 관리 웹 애플리케이션

---

##  프로젝트 개요
<strong>펀드 상품 조회, 회원 관리, 투자 관리 기능을 제공하는 Spring 기반 웹 애플리케이션</strong>입니다.

실제 은행 웹사이트 구조를 참고하여  
단순 CRUD 구현이 아닌 <strong>금융 도메인 흐름을 고려한 설계</strong>를 목표로 개발했습니다.
 
---

## 🛠 기술 스택

<p align="center">

| Category  | Stack |
|-----------|--------|
| **Backend** | Java 17, Spring Boot, MyBatis |
| **Batch** | Spring Batch |
| **Database** | Oracle XE |
| **Frontend** | Thymeleaf, JavaScript, CSS |
| **CI/CD** | GitHub Actions, AWS EC2 |

</p>

---

## ✨ 주요 기능

### 👤 고객 기능
- 로그인 / 회원가입  
- 펀드 상품 목록 및 상세 조회  
- 펀드 수익률 및 NAV 조회  
- 투자 성향 분석  
- 고객센터 / 챗봇  

### 🛠 관리자 기능
- 펀드 상품 목록 및 상세 조회  
- 펀드 상품 등록 / 수정 / 승인 / 반영 예약  
- 회원 관리  
- 고객센터 관리  
- 펀드 카테고리 / 검색어 관리  

---

## 👨‍💻 맡은 업무

### 🔹 Frontend
- 관리자 메인 페이지
- 펀드 목록 및 상세 페이지
- 펀드 관리 화면
- 고객센터 화면

### 🔹 Backend
- 펀드 관리  
  - 상품 등록 / 수정
  - 결재
  - Spring Batch를 활용한 반영 예약 상품 자동 반영 처리
- 회원 관리  
- 고객센터 관리  
- 펀드 카테고리 / 검색어 관리  




---

## 🗄 ERD

### 📊 펀드
<p align="center">
<img width="90%" src="https://github.com/user-attachments/assets/e0bc712e-d3de-4f65-8446-0b0b8faffb25" />
</p>

### 👤 유저
<p align="center">
<img width="75%" src="https://github.com/user-attachments/assets/0f5d6f39-4eff-422e-9143-347f36b72381" />
</p>

---

## 🖥 주요 화면

### 📌 메인
<p align="center">
<img width="80%" src="https://github.com/user-attachments/assets/83c727a7-81c0-4a6e-b9c0-f8503634e807" />
</p>

### 📌 관리자 메인
<p align="center">
<img width="70%" src="https://github.com/user-attachments/assets/6a701130-98ee-47c2-ad4e-89df82e3c133" />
</p>

### 📌 펀드 목록 (관리자)
<p align="center">
<img width="70%" src="https://github.com/user-attachments/assets/a3dcba02-bc04-41df-a3c6-a9b218f5ec04" />
</p>

### 📌 펀드 상세 (관리자)
<p align="center">
<img width="65%" src="https://github.com/user-attachments/assets/97a961ba-e4f5-4b18-8d00-5aab10ec816f" />
</p>

### 📌 펀드 관리 (관리자)
<p align="center">
<img width="70%" src="https://github.com/user-attachments/assets/bad75d59-820c-4a67-b47c-668d99b8969b" />
</p>

### 📌 결재 (관리자)
<p align="center">
<img width="60%" src="https://github.com/user-attachments/assets/d8528ca9-6a96-4781-80f3-d6326d01d740" />
</p>

---

## 🗄 ERD

### 📊 펀드
<p align="center">
<img width="90%" src="https://github.com/user-attachments/assets/e0bc712e-d3de-4f65-8446-0b0b8faffb25" />
</p>

### 👤 유저
<p align="center">
<img width="75%" src="https://github.com/user-attachments/assets/0f5d6f39-4eff-422e-9143-347f36b72381" />
</p>
