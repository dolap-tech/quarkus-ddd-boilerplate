create sequence seq_member increment by 50;

create table member (
	id bigint not null constraint member_pkey primary key,
	password varchar(255),
	country_code varchar(255),
	number varchar(255),
	username varchar(255)
);