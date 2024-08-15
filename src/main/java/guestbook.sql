#######################################################
################# guestbook_db 계정 ####################


drop user 'guestbook'@'%';

create user 'guestbook'@'%' identified by 'guestbook';

grant all privileges on guestbook_db.* to 'guestbook'@'%';

flush privileges;


#######################################################
################# guestbook_db DB생성 ##################


drop database guestbook_db;

create database guestbook_db
	default character set utf8mb4
    collate utf8mb4_general_ci
    default encryption='n'
;

show databases;

use guestbook_db;


#######################################################
################## guestbook_db 데이터 ##################


use guestbook_db;

drop table person;

create table person(
	no  	 	 integer 		  primary key     auto_increment,
    name		 varchar(80) 	  not null,
    password 	 varchar(20),
    content 	 text,
    reg_date     datetime
);

-- 조회
select * from person;

select  no,
		name,
        password,
        content,
        reg_date
from person
;


-- 등록
insert into person
values(null, '정우성', '010-1111-1111', '02-1111-1111')
;













-- 수정
update person
set name = '강호동',
	password = '010-9999-9999',
    content = '안녕하세요',
    reg_date = now()
where no = 2
;


-- 삭제
delete from person
where no = 2
and password = 1234
;






