CREATE TABLE user_table
(
    `user_idx`           int             NOT NULL       AUTO_INCREMENT, 
    `user_name`          VARCHAR(50)     NOT NULL, 
    `user_id`            VARCHAR(100)    NOT NULL, 
    `user_pw`            VARCHAR(100)    NOT NULL, 
    `user_email`         VARCHAR(100)    NULL, 
    `user_profile_img`   VARCHAR(100)    NULL, 
    `user_introduction`  VARCHAR(900)    NULL, 
    `user_auth_status`   VARCHAR(6)      DEFAULT 'N'    NOT NULL, 
    PRIMARY KEY (user_idx)
);


CREATE TABLE auth_table
(
    `auth_idx`  INT             NOT NULL    AUTO_INCREMENT, 
    `user_idx`  INT             NOT NULL, 
    `auth_key`  VARCHAR(100)    NOT NULL, 
    PRIMARY KEY (auth_idx)
);

ALTER TABLE auth_table
    ADD CONSTRAINT FK1_auth_table FOREIGN KEY (user_idx)
        REFERENCES user_table (user_idx);


CREATE TABLE board_table
(
    `board_idx`   INT             NOT NULL, 
    `board_name`  VARCHAR(200)    NOT NULL, 
    PRIMARY KEY (board_idx)
);


CREATE TABLE content_table
(
    `content_idx`          INT             NOT NULL     AUTO_INCREMENT, 
    `board_idx`            INT             NOT NULL, 
    `content_writer_idx`   INT             NOT NULL, 
    `content_subject`      VARCHAR(500)    NOT NULL, 
    `content_text`         TEXT            NOT NULL, 
    `content_file`         VARCHAR(500)    NULL, 
    `content_date`         DATE            NOT NULL, 
    `content_comment_cnt`  INT             DEFAULT 0    NOT NULL, 
    PRIMARY KEY (content_idx)
);

ALTER TABLE content_table
    ADD CONSTRAINT FK1_content_table FOREIGN KEY (board_idx)
        REFERENCES board_table (board_idx);

ALTER TABLE content_table
    ADD CONSTRAINT FK2_content_table FOREIGN KEY (content_writer_idx)
        REFERENCES user_table (user_idx);


CREATE TABLE comment_table
(
    `comment_idx`            INT           NOT NULL       AUTO_INCREMENT, 
    `comment_parent_idx`     INT           DEFAULT 0      NOT NULL, 
    `content_idx`            INT           NOT NULL, 
    `comment_writer_idx`     INT           NOT NULL, 
    `comment_text`           TEXT          NOT NULL, 
    `comment_date`           DATE          NOT NULL, 
    `comment_delete_status`  VARCHAR(6)    DEFAULT 'N'    NOT NULL, 
    `comment_reply_cnt`      INT           DEFAULT 0      NOT NULL, 
    PRIMARY KEY (comment_idx)
);

ALTER TABLE comment_table
    ADD CONSTRAINT FK1_comment_table FOREIGN KEY (content_idx)
        REFERENCES content_table (content_idx);

ALTER TABLE comment_table
    ADD CONSTRAINT FK2_comment_table FOREIGN KEY (comment_writer_idx)
        REFERENCES user_table (user_idx);


CREATE TABLE like_table
(
    `like_idx`       INT           NOT NULL    AUTO_INCREMENT, 
    `like_obj_code`  VARCHAR(6)    NOT NULL, 
    `content_idx`    INT           NULL, 
    `comment_idx`    INT           NULL, 
    `user_idx`       INT           NOT NULL, 
    `like_flag`      VARCHAR(6)    NOT NULL, 
    PRIMARY KEY (like_idx)
);

ALTER TABLE like_table
    ADD CONSTRAINT FK1_like_table FOREIGN KEY (user_idx)
        REFERENCES user_table (user_idx);

ALTER TABLE like_table
    ADD CONSTRAINT FK2_like_table FOREIGN KEY (content_idx)
        REFERENCES content_table (content_idx);

ALTER TABLE like_table
    ADD CONSTRAINT FK3_like_table FOREIGN KEY (comment_idx)
        REFERENCES comment_table (comment_idx);
