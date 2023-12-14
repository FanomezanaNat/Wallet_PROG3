insert into currencyValue (id, sourcecurrencyid, destinationcurrencyid, amount, dateeffect)
values ('98ac1788-92e3-11ee-b9d1-0242ac120002', '98ac0590-92e3-11ee-b9d1-0242ac120002',
        '98abfe06-92e3-11ee-b9d1-0242ac120002', 4500, '2023-12-06 12:00:00'),
       ('98ac1788-92e3-11ee-b9d1-0242ac120003', '98ac0590-92e3-11ee-b9d1-0242ac120002',
        '98abfe06-92e3-11ee-b9d1-0242ac120002', 4600, '2023-12-06 06:00:00'),
       ('98ac1788-92e3-11ee-b9d1-0242ac120004', '98ac0590-92e3-11ee-b9d1-0242ac120002',
        '98abfe06-92e3-11ee-b9d1-0242ac120002', 4550, '2023-12-06 16:00:00'),
       ('98ac1788-92e3-11ee-b9d1-0242ac120005', '98ac0590-92e3-11ee-b9d1-0242ac120002',
        '98abfe06-92e3-11ee-b9d1-0242ac120002', 4650, '2023-12-07 13:00:00')
ON CONFLICT (id) DO UPDATE SET sourcecurrencyid      = excluded.sourcecurrencyid,
                               destinationcurrencyid = excluded.destinationcurrencyid,
                               amount                = excluded.amount,
                               dateeffect            = excluded.dateeffect;