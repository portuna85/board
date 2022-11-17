### create user table ###
create table user
(
    id            int auto_increment comment '유저 인덱스'
        primary key,
    username      varchar(30)                         not null comment '유저 계정',
    pwd           varchar(200)                        null comment '유저 비밀번호',
    nickname      varchar(200)                        null comment '닉네임',
    role_user     char(10)  default 'BRONZE'          not null comment '회원등급',
    created_date  timestamp DEFAULT CURRENT_TIMESTAMP not null comment '회원가입 시간',
    modified_date timestamp DEFAULT CURRENT_TIMESTAMP null comment '회원정보 수정 시간',
    email         varchar(200)                        not null comment '이메일',
    constraint email
        unique (email),
    constraint id
        unique (id),
    constraint username
        unique (username)
);

### create posts table ###
create table posts
(
    id            int auto_increment comment '게시글 인덱스'
        primary key,
    title         varchar(500)                        not null comment '글 제목',
    content       text                                not null comment '게시글 내용',
    writer        varchar(255)                        not null comment '게시글 작성자',
    cnt           int                                 null comment '게시글 조회수',
    created_date  timestamp DEFAULT CURRENT_TIMESTAMP not null comment '게시글 작성 시간',
    modified_date timestamp DEFAULT CURRENT_TIMESTAMP not null comment '게시글 수정 시간',
    user_id       int                                 not null comment '사용자 고유 아이디',
    constraint id
        unique (id),
    constraint user_id
        unique (user_id),
    constraint writer
        unique (writer),
    constraint user_id
        foreign key (user_id) references user (id)
);

### create comments table ###
create table comments
(
    id            int auto_increment comment '댓글 인덱스'
        primary key,
    cmt           text                                not null comment '댓글 내용',
    user_id       int                                 not null comment '유저 인덱스',
    posts_id      int                                 not null comment '게시글 인덱스',
    created_data  timestamp DEFAULT CURRENT_TIMESTAMP not null comment '댓글 작성 시간',
    modified_date timestamp DEFAULT CURRENT_TIMESTAMP not null comment '회원정보 수정 시간',
    constraint id
        unique (id),
    constraint posts_id
        unique (posts_id),
    constraint user_id
        unique (user_id),
    constraint comments_posts_id_fk
        foreign key (posts_id) references posts (id),
    constraint comments_user_id_fk
        foreign key (user_id) references user (id)
);
