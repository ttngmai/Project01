CREATE SEQUENCE user_seq
START WITH 0
INCREMENT BY 1
MINVALUE 0;

CREATE SEQUENCE auth_seq
START WITH 0
INCREMENT BY 1
MINVALUE 0;

CREATE SEQUENCE content_seq
START WITH 0
INCREMENT BY 1
MINVALUE 0;

CREATE SEQUENCE comment_seq
START WITH 0
INCREMENT BY 1
MINVALUE 0;

CREATE SEQUENCE like_seq
START WITH 0
INCREMENT BY 1
MINVALUE 0;


CREATE TABLE user_table
(
    user_idx                NUMBER           NOT NULL, 
    user_name               VARCHAR2(50)     NOT NULL, 
    user_id                 VARCHAR2(100)    NOT NULL, 
    user_pw                 VARCHAR2(100)    NOT NULL, 
    user_email         	    VARCHAR2(100)    NULL,
    user_profile_img        VARCHAR2(100)    NULL,
    user_introduction       VARCHAR2(900)    NULL,
    user_auth_status        VARCHAR2(6)      DEFAULT 'N'    NOT NULL, 
    CONSTRAINT USER_TABLE_PK PRIMARY KEY (user_idx)
);


CREATE TABLE auth_table
(
    auth_idx    NUMBER           NOT NULL, 
    user_idx    NUMBER           NOT NULL, 
    auth_key    VARCHAR2(100)    NOT NULL, 
    CONSTRAINT AUTH_TABLE_PK PRIMARY KEY (auth_idx)
);

ALTER TABLE auth_table
    ADD CONSTRAINT FK1_auth_table FOREIGN KEY (user_idx)
        REFERENCES user_table (user_idx);


CREATE TABLE board_table
(
    board_idx     NUMBER           NOT NULL, 
    board_name    VARCHAR2(200)    NOT NULL, 
    CONSTRAINT BOARD_TABLE_PK PRIMARY KEY (board_idx)
);


CREATE TABLE content_table
(
    content_idx            NUMBER           NOT NULL, 
    board_idx              NUMBER           NOT NULL, 
    content_writer_idx     NUMBER           NOT NULL, 
    content_subject        VARCHAR2(500)    NOT NULL, 
    content_text           CLOB             NOT NULL, 
    content_file           VARCHAR2(500)    NULL, 
    content_date           DATE             NOT NULL,
    content_comment_cnt    NUMBER           DEFAULT 0    NOT NULL,
    CONSTRAINT CONTENT_TABLE_PK PRIMARY KEY (content_idx)
);

ALTER TABLE content_table
    ADD CONSTRAINT FK1_content_table FOREIGN KEY (board_idx)
        REFERENCES board_table (board_idx);

ALTER TABLE content_table
    ADD CONSTRAINT FK2_content_table FOREIGN KEY (content_writer_idx)
        REFERENCES user_table (user_idx);


CREATE TABLE comment_table
(
    comment_idx          	 NUMBER        NOT NULL, 
    comment_parent_idx   	 NUMBER        DEFAULT 0      NOT NULL, 
    content_idx           	 NUMBER        NOT NULL,
    comment_writer_idx    	 NUMBER        NOT NULL,
    comment_text         	 CLOB	       NOT NULL,
    comment_date          	 DATE      	   NOT NULL,
    comment_delete_status    VARCHAR(6)    DEFAULT 'N'    NOT NULL,
    comment_reply_cnt        NUMBER        DEFAULT 0      NOT NULL,
    CONSTRAINT REPLY_TABLE_PK PRIMARY KEY (comment_idx)
);

ALTER TABLE comment_table
    ADD CONSTRAINT FK1_comment_table FOREIGN KEY (content_idx)
        REFERENCES content_table (content_idx)
       	ON DELETE CASCADE;

ALTER TABLE comment_table
    ADD CONSTRAINT FK2_comment_table FOREIGN KEY (comment_writer_idx)
        REFERENCES user_table (user_idx);

        
CREATE TABLE like_table
(
    like_idx         NUMBER         NOT NULL, 
    like_obj_code    VARCHAR2(6)    NOT NULL, 
    content_idx      NUMBER         NOT NULL, 
    comment_idx      NUMBER         NULL,
    user_idx         NUMBER         NOT NULL,     
    like_flag		 VARCHAR2(6)    NOT NULL,
    CONSTRAINT LIKE_TABLE_PK PRIMARY KEY (like_idx)
);

ALTER TABLE like_table
    ADD CONSTRAINT FK1_like_table FOREIGN KEY (user_idx)
        REFERENCES user_table (user_idx);

ALTER TABLE like_table
    ADD CONSTRAINT FK2_like_table FOREIGN KEY (content_idx)
        REFERENCES content_table (content_idx)
        ON DELETE CASCADE;

ALTER TABLE like_table
    ADD CONSTRAINT FK3_like_table FOREIGN KEY (comment_idx)
        REFERENCES comment_table (comment_idx)
        ON DELETE CASCADE;
