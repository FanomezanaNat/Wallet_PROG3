create table IF NOt EXISTS currency
(
    id   uuid primary key not null,
    name varchar(50)      not null,
    code varchar(25)      not null
);



create table IF NOT EXISTS account
(

    id         uuid primary key not null,
    name       varchar(150),
    balance    double precision,
    updateDate timestamp default current_timestamp,
    type       varchar(150)     not null,
    Currency   uuid references currency (id)
);


create table IF NOT EXISTS transaction
(
    id              uuid primary key                    not null,
    label           varchar(100)                        not null,
    type            varchar(150)                        not null,
    transactionDate timestamp default current_timestamp not null,
    amount          integer                             not null,
    Account         uuid references account (id)
);


