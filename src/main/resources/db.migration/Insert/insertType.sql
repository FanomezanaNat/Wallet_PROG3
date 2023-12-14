insert into type (id, name)
values ('98ac1d14-92e3-11ee-b9d1-0242ac120002', 'credit'),
       ('98ac1d14-92e3-11ee-b9d1-0242ac120003', 'debit'),
       ('98ac1d14-92e3-11ee-b9d1-0242ac120004', 'credit & debit')
ON CONFLICT (id) DO UPDATE SET name = excluded.name;