-- 게시판 생성
INSERT INTO board_table (board_idx, board_name) VALUES (1, '자유게시판');
INSERT INTO board_table (board_idx, board_name) VALUES (2, '질문게시판');
INSERT INTO board_table (board_idx, board_name) VALUES (3, 'Tip게시판');
INSERT INTO board_table (board_idx, board_name) VALUES (4, '공지사항');

-- 유저 생성
INSERT INTO user_table (user_name, user_id, user_pw, user_email, user_introduction, user_auth_status)
VALUES ('홍길동', 'aaaa', 1234, 'mweapon@naver.com', '안녕하세요!', 'Y');
INSERT INTO user_table (user_name, user_id, user_pw, user_email, user_introduction, user_auth_status)
VALUES ('이승환', 'bbbb', 1234, 'mweapon@naver.com', '안녕하세요!', 'Y');
INSERT INTO user_table (user_name, user_id, user_pw, user_email, user_introduction, user_auth_status)
VALUES ('이지은', 'cccc', 1234, 'mweapon@naver.com', '안녕하세요!', 'Y');
INSERT INTO user_table (user_name, user_id, user_pw, user_email, user_introduction, user_auth_status)
VALUES ('깡패', 'dddd', 1234, 'mweapon@naver.com', '안녕하세요!', 'Y');