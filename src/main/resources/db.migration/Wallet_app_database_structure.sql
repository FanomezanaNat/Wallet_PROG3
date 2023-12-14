create table IF NOt EXISTS currency
(
    id   uuid primary key,
    name varchar(50) not null,
    code varchar(25) not null
);



create table IF NOT EXISTS account
(

    id         uuid primary key,
    name       varchar(150),
    balance    double precision,
    updateDate timestamp default current_timestamp,
    type       varchar(150) not null,
    Currency   uuid references currency (id)
);

create table IF NOT EXISTS type
(
    id   uuid primary key,
    name varchar(100) not null
);

create table IF NOT EXISTS category
(
    id   uuid primary key,
    name varchar(250) unique       not null,
    type uuid references type (id) not null
);

create table IF NOT EXISTS transaction
(
    id              uuid primary key,
    transactionDate timestamp default current_timestamp not null,
    amount          double precision                    not null,
    category uuid references category (id) not null,
    Account         uuid references account (id)
);

create table IF NOT EXISTS transferHistory
(
    id                  uuid primary key,
    debitTransactionId  uuid references transaction (id)    not null,
    creditTransactionId uuid references transaction (id)    not null,
    transferDate        timestamp default current_timestamp not null
);

create table IF NOT EXISTS currencyValue
(
    id                    uuid primary key,
    sourceCurrencyId      uuid references currency (id) not null,
    destinationCurrencyId uuid references currency (id) not null,
    amount                double precision              not null,
    dateEffect            timestamp                     not null
);

