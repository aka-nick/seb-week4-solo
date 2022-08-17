INSERT INTO sector(sector_code, sector_name) VALUES (1, 'UX디자이너');
INSERT INTO sector(sector_code, sector_name) VALUES (2, 'UI디자이너');
INSERT INTO sector(sector_code, sector_name) VALUES (3, '기획');
INSERT INTO sector(sector_code, sector_name) VALUES (4, '운영');
INSERT INTO sector(sector_code, sector_name) VALUES (5, 'QA');
INSERT INTO sector(sector_code, sector_name) VALUES (6, '프론트엔드');
INSERT INTO sector(sector_code, sector_name) VALUES (7, '백엔드');
INSERT INTO sector(sector_code, sector_name) VALUES (8, '풀스택');
INSERT INTO sector(sector_code, sector_name) VALUES (9, 'Android');
INSERT INTO sector(sector_code, sector_name) VALUES (10, 'iOS');
INSERT INTO sector(sector_code, sector_name) VALUES (11, '데브옵스');
INSERT INTO sector(sector_code, sector_name) VALUES (12, '시스템엔지니어');
INSERT INTO sector(sector_code, sector_name) VALUES (13, '인프라엔지니어');
INSERT INTO sector(sector_code, sector_name) VALUES (14, '네트워크엔지니어');
INSERT INTO sector(sector_code, sector_name) VALUES (15, '데이터엔지니어');
INSERT INTO sector(sector_code, sector_name) VALUES (16, '데이터애널리스트');
INSERT INTO sector(sector_code, sector_name) VALUES (17, '데이터사이언티스트');
INSERT INTO sector(sector_code, sector_name) VALUES (18, '임베디드');
INSERT INTO sector(sector_code, sector_name) VALUES (19, '커널');

INSERT INTO region(region_code, region_name) VALUES (1, '서울특별시');
INSERT INTO region(region_code, region_name) VALUES (2, '부산광역시');
INSERT INTO region(region_code, region_name) VALUES (3, '대구광역시');
INSERT INTO region(region_code, region_name) VALUES (4, '인천광역시');
INSERT INTO region(region_code, region_name) VALUES (5, '광주광역시');
INSERT INTO region(region_code, region_name) VALUES (6, '대전광역시');
INSERT INTO region(region_code, region_name) VALUES (7, '울산광역시');
INSERT INTO region(region_code, region_name) VALUES (8, '세종특별자치시');
INSERT INTO region(region_code, region_name) VALUES (9, '경기도');
INSERT INTO region(region_code, region_name) VALUES (10, '강원도');
INSERT INTO region(region_code, region_name) VALUES (11, '충청북도');
INSERT INTO region(region_code, region_name) VALUES (12, '충청남도');
INSERT INTO region(region_code, region_name) VALUES (13, '전라북도');
INSERT INTO region(region_code, region_name) VALUES (14, '전라남도');
INSERT INTO region(region_code, region_name) VALUES (15, '경상북도');
INSERT INTO region(region_code, region_name) VALUES (16, '경상남도');
INSERT INTO region(region_code, region_name) VALUES (17, '제주특별자치도');

INSERT INTO member(
    username, password, name, role, gender,
    company_name, sector_code, region_code
)
VALUES
('idid', 'pwpw', '박정수', 'ROLE_ADMIN', 1, 'ICBC', 7, 1),
('iddid_1', 'pwd1', '홍길동', 'ROLE_USER', 1, '중국건설은행', 1, 1),
('iddid_2', 'pwd2', '김길동', 'ROLE_USER', 2, 'JP모건 체이스', 2, 2),
('iddid_3', 'pwd3', '이길동', 'ROLE_USER', 3, '버크셔 해서웨이', 1, 3),
('iddid_4', 'pwd4', '박길동', 'ROLE_USER', 1, '중국농업은행', 3, 3),
('iddid_5', 'pwd5', '최길동', 'ROLE_USER', 1, '사우디 아람코', 2, 2),
('iddid_6', 'pwd5', '강길동', 'ROLE_USER', 1, '중국평안보험', 2, 2),
('iddid_7', 'pwd5', '고길동', 'ROLE_USER', 2, '뱅크오브아메리카', 12, 1),
('iddid_8', 'pwd5', '곽길동', 'ROLE_USER', 1, '애플', 10, 1),
('iddid_9', 'pwd5', '구길동', 'ROLE_USER', 2, '삼성전자', 6, 1),
('iddid_10', 'pwd5', '권길동', 'ROLE_USER', 1, '토요타', 4, 12),
('iddid_11', 'pwd5', '나길동', 'ROLE_USER', 1, '알파벳', 8, 17),
('iddid_12', 'pwd5', '남길동', 'ROLE_USER', 1, '중국은행', 19, 2),
('iddid_13', 'pwd5', '문길동', 'ROLE_USER', 2, '마이크로소프트', 14, 5),
('iddid_14', 'pwd5', '민길동', 'ROLE_USER', 3, '웰스파고', 7, 8),
('iddid_15', 'pwd5', '배길동', 'ROLE_USER', 3, '씨티그룹', 2, 9),
('iddid_16', 'pwd5', '백길동', 'ROLE_USER', 2, '월마트', 5, 9),
('iddid_17', 'pwd5', '서길동', 'ROLE_USER', 2, '버라이즌', 8, 3),
('iddid_18', 'pwd5', '성길동', 'ROLE_USER', 2, '로열 더치 쉘', 1, 5),
('iddid_19', 'pwd5', '손길동', 'ROLE_USER', 2, '아마존닷컴', 4, 6),
('iddid_19', 'pwd5', '손길동', 'ROLE_USER', 1, '폭스바겐', 2, 2),
('iddid_20', 'pwd5', '송길동', 'ROLE_USER', 1, '유나이티드헬스그룹', 1, 15),
('iddid_21', 'pwd5', '신길동', 'ROLE_USER', 1, '알리안츠', 14, 14),
('iddid_22', 'pwd5', '심길동', 'ROLE_USER', 1, '중국초상은행', 17, 1),
('iddid_23', 'pwd5', '안길동', 'ROLE_USER', 3, '컴캐스트', 1, 1),
('iddid_24', 'pwd5', '양길동', 'ROLE_USER', 2, '차이나 모바일', 12, 1),
('iddid_25', 'pwd5', '엄길동', 'ROLE_USER', 1, '토탈', 2, 3),
('iddid_26', 'pwd5', '오길동', 'ROLE_USER', 3, 'PSBC', 11, 4),
('iddid_27', 'pwd5', '우길동', 'ROLE_USER', 1, '알리바바 그룹', 10, 4),
('iddid_28', 'pwd5', '원길동', 'ROLE_USER', 1, '가즈프롬', 8, 17),
('iddid_29', 'pwd5', '유길동', 'ROLE_USER', 2, '페트로차이나', 9, 16),
('iddid_30', 'pwd5', '주길동', 'ROLE_USER', 2, '존슨앤드존슨', 9, 15)