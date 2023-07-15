drop table if exists article;
drop table if exists user_account;

create table article(
    id bigint primary key auto_increment,
    user_account_id varchar(100) not null,
    title varchar(100) not null,
    content varchar(1000) not null,
    created_at datetime not null
);

create table user_account (
    login_id varchar(50) not null,
    password varchar(50),
    name varchar(20),
    primary key (login_id)
);

ALTER TABLE article
ADD CONSTRAINT fk_user_account_login_id
FOREIGN KEY (user_account_id)
REFERENCES user_account(login_id);