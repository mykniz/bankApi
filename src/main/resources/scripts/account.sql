DROP TABLE IF EXISTS ACCOUNT CASCADE;

CREATE TABLE IF NOT EXISTS ACCOUNT
(
    account_id   INTEGER      not null AUTO_INCREMENT,
    account VARCHAR(255) not null,
    balance DECIMAL(13,2),
    is_open BOOL,
    client_id int,
    PRIMARY KEY (account_id),
    UNIQUE (account),
    foreign key (client_id) references CLIENT(client_id)
);

insert into ACCOUNT (account, balance, is_open, client_id) values ('64419815581464276017', 1000000.00, true, 1);
insert into ACCOUNT (account, balance, is_open, client_id) values ('66546345908858348082', 9999999999.00, true, 1);
insert into ACCOUNT (account, balance, is_open, client_id) values ('03217462696778617732', 10.00, true, 2);


