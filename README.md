

# 📁 the-more-the-better
## 🐸 The More the Better 🐸

A midterm project from the PlayData Pose-Estimation Developer Training Program.
<br><br>
This service gathers participants to place group orders for drinks, allowing users to share the delivery fee and reduce individual costs.
<br>

## 👩‍💻 Team Members
#### • 🧑🏻‍ Jae-hyuk Yoo (Team Leader): QA, presentation and documentation, Git project management
#### • 👩🏻 Joo-hyun Park (Backend): User management, order processing, database modeling, scheduler management
#### • 👦🏻 Jun-soo Park (Frontend): Order interface via API, asynchronous processing, order logic implementation
#### • 👩🏻‍🦰 Chae-yeon Park (Frontend): SPA architecture management, cross-browser support, asynchronous handling, UI/UX design
<br>

## 📚 Project Overview
### <strong> 1. Technology of Untact<br> </strong>
In response to the ongoing social distancing measures during the COVID-19 pandemic, this project aimed to offer a non-face-to-face alternative to in-store café visits by enabling group delivery orders.
### <strong> 2. Cost-effectiveness<br> </strong>
Users can reduce their personal delivery costs by placing a group order with other customers at the same time.
### <strong> 3. Accessibilitybr>  </strong>
Real-time participant matching is available, and once the group is formed, users are guided to place their order together via SMS notifications.
<br><br>

## 📝 Languages & Tech Stack
![Generic badge](https://img.shields.io/badge/platform-Web-brightgreen.svg) ![Generic badge](https://img.shields.io/badge/library-vue-blue.svg) ![Generic badge](https://img.shields.io/badge/framework-spring-green.svg)
![Generic badge](https://img.shields.io/badge/database-ORACLE-yellow.svg) ![Generic badge](https://img.shields.io/badge/api-kakaomap-red,.svg) ![Generic badge](https://img.shields.io/badge/language-Java,JavaScript-important.svg)
<br>

### Technologies Used
| Technology | Version | Description |
| :--------: | :----: | ---- |
| SpringBoot | ![Generic badge](https://img.shields.io/badge/release-2.3.3-blue.svg) | Java-based application framework |
| JPA | ![Generic badge](https://img.shields.io/badge/release-5.4.20-blue.svg) | ORM framework for relational databases |
| Log4j | ![Generic badge](https://img.shields.io/badge/release-2.13.3-blue.svg) | Java-based logging utility |
| REST API | ![Generic badge](https://img.shields.io/badge/release--blue.svg) | API following REST architectural style |
| Oracle RDBMS | ![Generic badge](https://img.shields.io/badge/release-19.3.0.0-blue.svg) | Oracle’s relational database system |
| Spring Scheduler | ![Generic badge](https://img.shields.io/badge/release-2.3.2-blue.svg) | Scheduling library for automated tasks |
| MVC Pattern | ![Generic badge](https://img.shields.io/badge/release--blue.svg) | Architectural pattern separating concerns |
| Builder Pattern | ![Generic badge](https://img.shields.io/badge/release--blue.svg) | Design pattern for complex object creation |
| Swagger | ![Generic badge](https://img.shields.io/badge/release-2.8.0-blue.svg) | API documentation and design tool |
| JUnit4 | ![Generic badge](https://img.shields.io/badge/release-4.7.1-blue.svg) | Unit testing framework |
| Rombok | ![Generic badge](https://img.shields.io/badge/release-1.18.12-blue.svg) | Reduces boilerplate in data models |
| Nurigo | ![Generic badge](https://img.shields.io/badge/release-2.2.1-blue.svg) | SMS API for automated messaging |
| Vue.js | ![Generic badge](https://img.shields.io/badge/release-4.5.4-blue.svg) | Progressive JavaScript framework |
| ESLint | ![Generic badge](https://img.shields.io/badge/release-6.14.8-blue.svg) | Code linter for JavaScript/ECMAScript |

<br>

## 📜 ERD (Entity Relationship Diagram)
![KakaoTalk_20200930_153857747](https://user-images.githubusercontent.com/68583697/94651283-2f7f0100-0333-11eb-9093-fcb7d5aafb65.png)
<br>

| Entity | Attributes | Domains | DataType |
| :------: | ---------- | ------- | ------ |
| MEMBER <br> (User) | MEM_NO <br> CREATED_DATE <br> MODIFIED_DATE <br> MEM_ID <br> MEM_ISCERTIFY <br> MEM_ISHOST <br> MEM_NAME <br> MEM_PHONE <br> MEM_PW <br> CLASS_NO <br> WAIT_NO |Unique user ID <br> Date of registration <br> Date of last update <br> Login ID <br> Certification status <br> Is currently hosting an order <br> User name <br> Phone number <br> Password <br> Class (group) ID <br> Currently participating order ID | NUMBER(19) <br> DATE <br> DATE <br> VARCHAR2(20) <br> NUMBER(1) <br> NUMBER(1) <br> VARCHAR2(20) <br> VARCHAR2(20) <br> VARCHAR2(20) <br> NUMBER(19) <br> NUMBER(19) |
| WAITING <br> (Group Order) | WAIT_NO <br> CREATED_DATE <br> MODIFIED_DATE <br> WAIT_CLOSETIME <br> WAIT_PLACE <br> WAIT_MINCOST <br> WAIT_MINPERSON <br> WAIT_STANDBY <br> WAIT_TEXT <br> MEM_NO <br> ST_NO | Unique order ID <br> Order creation date <br> Last update date <br> Order deadline (closing time) <br> Pickup location <br> Minimum total order amount <br> Minimum number of participants <br> Current number of participants <br> Order description/details <br> Host user ID <br> Store ID | NUMBER(19) <br> DATE <br> DATE <br> DATE <br> VARCHAR2(255) <br> NUMBER(10) <br> NUMBER(10) <br> NUMBER(10) <br> VARCHAR2(255) <br> NUMBER(19) <br> NUMBER(19) | 
| CLASS <br> (Class Group) | CLASS_NO <br> CREATED_DATE <br> MODIFIED_DATE <br> CLASS_CODE <br> CLASS_NAME | Unique class ID <br> Start date <br> Last update date <br> Class code <br> Class name | NUMBER(19) <br> DATE <br> DATE <br> VARCHAR2(4) <br> VARCHAR(40) |
| STORE <br> (Vendor) | ST_NO <br> CREATED_DATE <br> MODIFIED_DATE <br> ST_ADDR <br> ST_NAME <br> ST_PICTURE | Unique store ID <br> Date added <br> Last update date <br> Store address <br> Store name <br> Store image | NUMBER(19) <br> DATE <br> DATE <br> VARCHAR2(50) <br> VARCHAR2(40) <br> VARCHAR2(255) |

<br><br>

## 🔎 Project Structure

| FrontEnd | BackEnd (main)| BackEnd (test) |
| -------- | ------------- | -------------- |
| ![Screen Shot 2020-10-11 at 9 49 34 PM](https://user-images.githubusercontent.com/64248514/95679127-b2de0380-0c0b-11eb-8a49-c50b1fd676d6.png) | ![Screen Shot 2020-10-11 at 9 44 55 PM](https://user-images.githubusercontent.com/64248514/95679002-1287df00-0c0b-11eb-8141-1c0bd3e5e787.png) | ![Screen Shot 2020-10-11 at 9 25 22 PM](https://user-images.githubusercontent.com/64248514/95678607-b1f7a280-0c08-11eb-8d2c-2856b91e2210.png) |

<br>



## 💻 Final Output
![실행화면](https://user-images.githubusercontent.com/68583697/95819839-d74bf400-0d61-11eb-9649-4760780e3f3d.png)




<br>

## 🔎 ERD Update 

### 2020-09-07
- Added phone number field to MEMBER
- Hosts now also included in waiting_mems
- Added WAIT_STANDBY field to track current participant count

### 2020-09-08
- Added WAIT_CLOSETIME, WAIT_PLACE, WAIT_MINCOST, WAIT_MINPERSON, WAIT_TEXT to WAITING table 
- Consolidated WAIT_MINLIMIT into WAIT_MINPERSON (WAIT_MINLIMIT marked for deletion)
