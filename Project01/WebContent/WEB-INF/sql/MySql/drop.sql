alter table auth_table drop constraint FK1_auth_table;
alter table content_table drop constraint FK1_content_table;
alter table content_table drop constraint FK2_content_table;
alter table comment_table drop constraint FK1_comment_table;
alter table comment_table drop constraint FK2_comment_table;
alter table like_table drop constraint FK1_like_table;
alter table like_table drop constraint FK2_like_table;
alter table like_table drop constraint FK3_like_table;

DROP TABLE user_table CASCADE;
DROP TABLE auth_table CASCADE;
DROP TABLE board_table CASCADE;
DROP TABLE content_table CASCADE;
DROP TABLE comment_table CASCADE;
DROP TABLE like_table CASCADE;