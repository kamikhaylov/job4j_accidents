create table if not exists rules (
    id      serial      primary key,
    name    varchar     not null
);

comment on table rules is 'Статьи';
comment on column rules.id is 'Идентификатор статьи инцидента';
comment on column rules.name is 'Наименование статьи инцидента';