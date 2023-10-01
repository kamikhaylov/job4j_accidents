create table authorities (
    username    varchar(50)     not null,
    authority   varchar(50)     not null,
    foreign key (username) references users(username)
);

comment on table authorities is 'Роли пользователей';
comment on column authorities.username is 'Имя пользователя';
comment on column authorities.authority is 'Роль пользователя';