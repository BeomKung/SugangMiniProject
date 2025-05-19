INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(1,'1000','김우석', '1234@naver.com', '1000', '영문과', '1', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(2,'1001','서승범', '1235@naver.com', '1001', '영문과', '2', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(3,'1002','차혜린', '1236@naver.com', '1002', '영문과', '3', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(4,'1003','조석현', '1237@naver.com', '1003', '영문과', '4', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(5,'1004','이지현', '1238@naver.com', '1004', '영문과', '석사', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(6,'1005','주성현', '1239@naver.com', '1005', '영문과', '박사', 'STUDENT');

INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(7,'2000','서우석', '2234@naver.com', '2000', '컴퓨터공학과', '1', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(8,'2001','김승범', '2235@naver.com', '2001', '컴퓨터공학과', '2', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(9,'2002','조혜린', '2236@naver.com', '2002', '컴퓨터공학과', '3', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(10,'2003','차석현', '2237@naver.com', '2003', '컴퓨터공학과', '4', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(11,'2004','주지현', '2238@naver.com', '2004', '컴퓨터공학과', '석사', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(12,'2005','이성현', '2239@naver.com', '2005', '컴퓨터공학과', '박사', 'STUDENT');

INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(13,'3000','주우석', '3234@naver.com', '3000', '디자인학과', '1', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(14,'3001','차승범', '3235@naver.com', '3001', '디자인학과', '2', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(15,'3002','이혜린', '3236@naver.com', '3002', '디자인학과', '3', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(16,'3003','주석현', '3237@naver.com', '3003', '디자인학과', '4', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(17,'3004','김지현', '3238@naver.com', '3004', '디자인학과', '석사', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(18,'3005','서성현', '3239@naver.com', '3005', '디자인학과', '박사', 'STUDENT');

INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(19,'4000','차우석', '4234@naver.com', '4000', '교양학부', '1', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(20,'4001','조승범', '4235@naver.com', '4001', '교양학부', '2', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(21,'4002','주혜린', '4236@naver.com', '4002', '교양학부', '3', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(22,'4003','이석현', '4237@naver.com', '4003', '교양학부', '4', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(23,'4004','서지현', '4238@naver.com', '4004', '교양학부', '석사', 'STUDENT');
INSERT INTO STUDENT(id, studentNumber, name, email, password, major, grade, role) VALUES(24,'4005','김성현', '4239@naver.com', '4005', '교양학부', '박사', 'STUDENT');


INSERT INTO ADMIN(id, studentNumber, password, role) VALUES(1,'admin','admin', 'ADMIN');


---- 테이블 생성
--CREATE TABLE notice (
--    id INT AUTO_INCREMENT PRIMARY KEY,
--    title VARCHAR(255) NOT NULL,
--    content TEXT NOT NULL
--);
--
---- 데이터 삽입 예시
--INSERT INTO notice (title, content) VALUES
--('시스템 점검 안내', '5월 25일(일) 00:00~06:00 시스템 점검으로 서비스가 일시 중단됩니다.'),
--('새 학기 수강신청 안내', '2025학년도 1학기 수강신청 기간은 5월 20일부터 5월 23일까지입니다.'),
--('교내 코로나19 예방수칙', '마스크 착용 및 손 소독 등 개인위생 수칙을 반드시 준수해 주세요.');
--
--
--INSERT INTO SUBJECT (courseType, department, grade, subjectCode, subjectName, professor, weekday, startTime, endTime, credit, capacity) VALUES
--('전공(필수)', '컴퓨터공학과', '1', 'CS101', '기초프로그래밍', '김교수', '월', '09:00', '09:50', 3, 30),
--('전공(선택)', '컴퓨터공학과', '2', 'CS102', '자료구조', '이교수', '화', '10:00', '10:50', 3, 40),
--('전공(필수)', '컴퓨터공학과', '3', 'CS103', '알고리즘', '박교수', '수', '11:00', '11:50', 3, 35),
--('전공(선택)', '컴퓨터공학과', '4', 'CS104', '운영체제', '최교수', '목', '13:00', '13:50', 3, 30),
--('전공(필수)', '컴퓨터공학과', '2', 'CS105', '네트워크', '정교수', '금', '14:00', '14:50', 3, 25),
--
--('전공(필수)', '영어영문학과', '1', 'EN101', '기초영어', '홍교수', '월', '09:00', '09:50', 2, 40),
--('전공(선택)', '영어영문학과', '2', 'EN102', '영문학개론', '박교수', '화', '10:00', '10:50', 3, 30),
--('전공(필수)', '영어영문학과', '3', 'EN103', '심화영어', '이교수', '수', '11:00', '11:50', 3, 35),
--('전공(선택)', '영어영문학과', '4', 'EN104', '영어회화', '김교수', '목', '13:00', '13:50', 2, 25),
--('전공(필수)', '영어영문학과', '2', 'EN105', '영어작문', '최교수', '금', '14:00', '14:50', 3, 30),
--
--('전공(필수)', '디자인학과', '1', 'DS101', '기초디자인', '정교수', '월', '09:00', '09:50', 2, 30),
--('전공(선택)', '디자인학과', '2', 'DS102', '색채학', '홍교수', '화', '10:00', '10:50', 3, 25),
--('전공(필수)', '디자인학과', '3', 'DS103', '타이포그래피', '박교수', '수', '11:00', '11:50', 3, 30),
--('전공(선택)', '디자인학과', '4', 'DS104', '웹디자인', '이교수', '목', '13:00', '13:50', 3, 20),
--('전공(필수)', '디자인학과', '2', 'DS105', '3D모델링', '김교수', '금', '14:00', '14:50', 3, 15),
--
--('교양(필수)', '교양학부', '1', 'GE101', '논리적사고', '최교수', '월', '09:00', '09:50', 2, 50),
--('교양(선택)', '교양학부', '2', 'GE102', '대학수학', '정교수', '화', '10:00', '10:50', 3, 40),
--('교양(필수)', '교양학부', '3', 'GE103', '철학입문', '홍교수', '수', '11:00', '11:50', 2, 45),
--('교양(선택)', '교양학부', '4', 'GE104', '심리학개론', '박교수', '목', '13:00', '13:50', 3, 35),
--('교양(필수)', '교양학부', '2', 'GE105', '경제학원론', '이교수', '금', '14:00', '14:50', 3, 40),
--
--('전공(필수)', '컴퓨터공학과', '1', 'CS106', '데이터베이스', '김교수', '월', '15:00', '15:50', 3, 30),
--('전공(선택)', '컴퓨터공학과', '2', 'CS107', '소프트웨어공학', '이교수', '화', '16:00', '16:50', 3, 30),
--('전공(필수)', '컴퓨터공학과', '3', 'CS108', '인공지능', '박교수', '수', '17:00', '17:50', 3, 25),
--('교양(필수)', '교양학부', '1', 'GE106', '환경과학', '최교수', '목', '15:00', '15:50', 2, 40),
--('교양(선택)', '교양학부', '2', 'GE107', '문화와 예술', '정교수', '금', '16:00', '16:50', 2, 30),
--
--('전공(선택)', '영어영문학과', '3', 'EN106', '영문법', '홍교수', '월', '17:00', '17:50', 3, 30),
--('전공(필수)', '디자인학과', '4', 'DS106', '사진학', '박교수', '화', '15:00', '15:50', 3, 20),
--('교양(선택)', '교양학부', '3', 'GE108', '사회학개론', '이교수', '수', '16:00', '16:50', 3, 40),
--('교양(필수)', '교양학부', '4', 'GE109', '문학개론', '김교수', '목', '17:00', '17:50', 2, 35);
