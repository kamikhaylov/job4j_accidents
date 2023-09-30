create table if not exists accident_types (
    id      serial      primary key,
    name    varchar     not null
);

comment on table accident_types is 'Типы инцидентов';
comment on column accident_types.id is 'Идентификатор типа инцидента';
comment on column accident_types.name is 'Наименование типа инцидента';