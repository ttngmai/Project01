ALTER TABLE auth_table DROP CONSTRAINT FK1_auth_table;
ALTER TABLE content_table DROP CONSTRAINT FK1_content_table;
ALTER TABLE content_table DROP CONSTRAINT FK2_content_table;
ALTER TABLE comment_table DROP CONSTRAINT FK1_comment_table;
ALTER TABLE comment_table DROP CONSTRAINT FK2_comment_table;
ALTER TABLE like_table DROP CONSTRAINT FK1_like_table;
ALTER TABLE like_table DROP CONSTRAINT FK2_like_table;
ALTER TABLE like_table DROP CONSTRAINT FK3_like_table;

DROP TABLE user_table CASCADE;
DROP TABLE auth_table CASCADE;
DROP TABLE board_table CASCADE;
DROP TABLE content_table CASCADE;
DROP TABLE comment_table CASCADE;
DROP TABLE like_table CASCADE;