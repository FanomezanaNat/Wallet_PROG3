create table IF NOt EXISTS currency
(
    id   uuid primary key not null,
    type varchar(50)      not null
);



create table IF NOT EXISTS account
(
    id         serial primary key not null,
    idCurrency uuid references currency (id),
);


create table IF NOT EXISTS transaction
(
    id              uuid primary key                    not null,
    type            varchar(150)                        not null,
    transactionDate timestamp default current_timestamp not null,
    amount          integer                             not null,
    idAccount       integer references account (id)
);


