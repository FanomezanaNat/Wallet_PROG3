insert into currency (id, name, code)
values ('98abfe06-92e3-11ee-b9d1-0242ac120002', 'Ariary', 'MGA'),
       ('98ac0590-92e3-11ee-b9d1-0242ac120002', 'Euro', 'EUR')
ON CONFLICT (id) DO UPDATE SET name = excluded.name,
                               code = excluded.code;