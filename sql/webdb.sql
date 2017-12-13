select * from gallery order by no desc;

insert into gallery values(seq_gallery.nextval, '마리', '123');
insert into gallery values(seq_gallery.nextval, '마리2', '1234');
insert into gallery values(seq_gallery.nextval, '마리3', '12345');
insert into gallery values(seq_gallery.nextval, '박보영2', '/bigdata/workspace/mysite3/gallery/images/201738185153388.jpg');

delete from gallery;
delete from gallery;

drop sequence seq_gallery;
create sequence seq_gallery
start with 1
increment by 1
maxvalue 9999999999;



select * from member;

alter table member add (role VARCHAR2(10) default 'USER');

insert into member values(seq_member.nextval, '관리자', '1234', 'admin', 'male', sysdate, 'ADMIN');

update member set password = '1' where no = '141';



select count(*)
  from guestBook
 where no < 238;
 
select * from guestBook order by no desc;

-- creat sequence
drop sequence seq_member;
create sequence seq_member
start with 1
increment by 1
maxvalue 9999999999;

drop sequence seq_emaillist;
create sequence seq_emaillist
start with 1
increment by 1
maxvalue 9999999999;

-- insert
insert into member
VALUES (seq_member.nextval, '태훈', '1234', 'urimaumero@naver.com', 'male', sysdate);

insert into EMAILLIST
VALUES (seq_emaillist.nextval, '노', '태훈', 'HailFederer@naver.com');

-- select
select *
	from member
	order by no desc;
	
select no, first_name, last_name, email
	from emaillist
	order by no desc;

select no, name
	from member;












