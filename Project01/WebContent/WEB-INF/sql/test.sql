select a1.board_name, a3.user_name as content_writer_name, a2.content_idx, a2.content_subject, 
       to_char(a2.content_date, 'YYYY-MM-DD') as content_date 
from board_table a1, content_table a2, user_table a3
where a2.content_writer_idx = 3
      and a3.user_idx = 3
      and a1.board_idx = a2.board_idx
order by a2.content_idx desc;

SELECT * FROM BOARD_TABLE;
SELECT * FROM CONTENT_TABLE;