create table if not exists accidents (
    id         serial      primary key,
    name       varchar     not null,
    text       varchar     not null,
    address    varchar     not null,
    type_id    int         references accident_types(id)
);

comment on table accidents is 'Инциденты';
comment on column accidents.id is 'Идентификатор инцидента';
comment on column accidents.name is 'Наименование инцидента';
comment on column accidents.text is 'Описание инцидента';
comment on column accidents.address is 'Адрес инцидента';
comment on column accidents.type_id is 'Идентификатор типа инцидента';
