insert into transaction (id, amount, account, category)
values ('98ac0d10-92e3-11ee-b9d1-0242ac120002', 10000, '98ac0770-92e3-11ee-b9d1-0242ac120002',
        '98ac1b3e-92e3-11ee-b9d1-0242ac120002'),
       ('98ac0ee6-92e3-11ee-b9d1-0242ac120002', 20, '98ac0b4e-92e3-11ee-b9d1-0242ac120002',
        '98ac1b3e-92e3-11ee-b9d1-0242ac120007'),
       ('98ac10bc-92e3-11ee-b9d1-0242ac120002', 25000, '98ac0928-92e3-11ee-b9d1-0242ac120002',
        '98ac1b3e-92e3-11ee-b9d1-0242ac120003')
ON CONFLICT (id) DO UPDATE SET amount   = excluded.amount,
                               account  = excluded.amount,
                               category = excluded.category;