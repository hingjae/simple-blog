drop table if exists article;

create table article(
    id bigint primary key auto_increment,
    title varchar(100) not null,
    content varchar(1000) not null,
    username varchar(100) not null,
    created_at datetime not null
);