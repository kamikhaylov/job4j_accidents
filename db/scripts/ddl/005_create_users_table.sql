create table users (
    username    varchar(50)     not null,
    password    varchar(100)    not null,
    enabled     boolean         default true,
    primary key (username)
);

comment on table users is 'Пользователи';
comment on column users.username is 'Имя пользователя';
comment on column users.password is 'Пароль';
comment on column users.enabled is 'Признак доступности учетной записи';