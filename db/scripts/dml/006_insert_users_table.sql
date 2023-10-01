insert into users (
    username,
    enabled,
    password,
    authority_id
)
values (
    'root',
    true,
    '$2a$10$qsvuecwqYo.ISn0i0hWEX.Q143UcZnCNG91DGg6oDEW32HWB3klL.',
    (select id from authorities where authority = 'ROLE_ADMIN')
);