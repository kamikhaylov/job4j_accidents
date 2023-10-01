create table users (
    id              serial          primary key,
    username        varchar(50)     not null unique,
    password        varchar(100)    not null,
    enabled         boolean         default true,
    authority_id    int             not null references authorities(id)
);

comment on table users is 'Пользователи';
comment on column users.username is 'Имя пользователя';
comment on column users.password is 'Пароль';
comment on column users.enabled is 'Признак доступности учетной записи';
comment on column users.authority_id is 'Идентификатор роли пользователя';