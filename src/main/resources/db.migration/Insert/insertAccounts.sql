INSERT INTO account (id, name, balance, type, currency)
VALUES
    ('98ac0770-92e3-11ee-b9d1-0242ac120002', '', 100000, 'Cash', '98abfe06-92e3-11ee-b9d1-0242ac120002'),
    ('98ac0928-92e3-11ee-b9d1-0242ac120002', 'Current Account', 230000, 'Mobile Money', '98abfe06-92e3-11ee-b9d1-0242ac120002'),
    ('98ac0b4e-92e3-11ee-b9d1-0242ac120002', 'Saving Account', 50, 'Bank', '98ac0590-92e3-11ee-b9d1-0242ac120002')
ON CONFLICT (id) DO UPDATE SET
                               name = excluded.name,
                               balance = excluded.balance,
                               type = excluded.type,
                               currency = excluded.currency;