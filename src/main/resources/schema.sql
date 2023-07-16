drop table if exists article_comment;
drop table if exists article;
drop table if exists user_account;

create table article
(
    id              bigint auto_increment,
    user_account_id varchar(50)   not null,
    title           varchar(100)  not null,
    content         varchar(1000) not null,
    created_at      datetime      not null,
    primary key (id)
);


create table user_account
(
    login_id varchar(50),
    password varchar(50) not null,
    name     varchar(20) not null,
    primary key (login_id)
);



create table article_comment
(
    id              bigint auto_increment,
    article_id      bigint        not null,
    user_account_id varchar(50)   not null,
    content         varchar(1000) not null,
    created_at      datetime      not null,
    primary key (id)
);


ALTER TABLE article
    ADD CONSTRAINT fk_user_account_login_id
        FOREIGN KEY (user_account_id) REFERENCES user_account (login_id);

ALTER TABLE article_comment
    ADD CONSTRAINT FK_article_comment_article
        FOREIGN KEY (article_id) REFERENCES article (id);

ALTER TABLE article_comment
    ADD CONSTRAINT FK_article_comment_user_account
        FOREIGN KEY (user_account_id) REFERENCES user_account (login_id);