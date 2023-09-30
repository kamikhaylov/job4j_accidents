create table if not exists accident_rules (
    id              serial   primary key,
    accident_id     int      not null       references accidents(id),
    rule_id         int      not null       references rules(id)
);

comment on table accident_rules is 'Статьи инцидентов';
comment on column accident_rules.id is 'Идентификатор статьи инцидента';
comment on column accident_rules.accident_id is 'Идентификатор инцидента';
comment on column accident_rules.rule_id is 'Идентификатор статьи инцидента';