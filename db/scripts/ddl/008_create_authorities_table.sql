create table if not exists authorities (
    id          serial       primary key,
    authority   varchar(50)  not null unique
);

comment on table authorities is 'Роли пользователей';
comment on column authorities.id is 'Идентификатор роли пользователя';
comment on column authorities.authority is 'Роль пользователя';