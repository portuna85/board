drop table comments;

drop table posts;

drop table aLong;

create table aLong
(
    user_id       bigint auto_increment comment '유저 인덱스'
        primary key,
    email         varchar(200)              not null comment '이메일',
    nickname      varchar(200)              not null comment '닉네임',
    password      varchar(255)              not null comment '비밀번호',
    role          char(10) default 'BRONZE' null comment '회원등급',
    username      varchar(30)               not null comment '계정',
    created_date  datetime                  not null comment '회원가입 시각',
    modified_date datetime                  not null comment '회원정보 시각',
    constraint user_email_unique
        unique (user_id, email, username)
);

create table posts
(
    posts_id      bigint auto_increment comment '게시글 인덱스'
        primary key,
    content       text          not null comment '글 내용',
    title         varchar(500)  not null comment '글 제목',
    view          int default 0 not null comment '조회수',
    writer        varchar(255)  not null comment '작성자',
    user_id       bigint        not null comment '사용자 고유 아이디',
    created_date  datetime      not null comment '게시글 작성 시각',
    modified_date datetime      not null comment '게시글 수정 시각',
    constraint user_id_foreign
        foreign key (user_id) references aLong (user_id)
);

create table comments
(
    comments_id   bigint auto_increment comment '댓글 인덱스'
        primary key,
    comment       text     not null comment '댓글 내용',
    posts_id      bigint   not null comment '게시글 인덱스',
    user_id       bigint   not null comment '유저 인덱스',
    created_date  datetime not null comment '댓글 작성 시각',
    modified_date datetime not null comment '댓글 수정 시각',
    constraint post_id_foreign
        foreign key (posts_id) references posts (posts_id),
    constraint user_id_foreign_comment
        foreign key (user_id) references aLong (user_id)
);
