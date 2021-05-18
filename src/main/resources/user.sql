
//--------------------------------------------//
DROP TABLE IF EXISTS CARD;
DROP TABLE IF EXISTS ACCOUNT;
DROP TABLE IF EXISTS USER;


//-----------------CREATE USER TABLE---------------------------//
CREATE TABLE IF NOT EXISTS USER
(
    user_id   INTEGER      not null AUTO_INCREMENT,
    user_name VARCHAR(255) not null,
    last_name VARCHAR(255) not null,
    PRIMARY KEY (user_id)
);
INSERT INTO USER (user_name, last_name) VALUES ('John', 'Doe');
INSERT INTO USER (user_name, last_name) VALUES ('Leo', 'Messi');


//-----------------CREATE ACCOUNT TABLE---------------------------//
CREATE TABLE IF NOT EXISTS ACCOUNT
(
    account_id   INTEGER      not null AUTO_INCREMENT,
    account VARCHAR(255),
    is_open BOOL,
    user_id int,
    PRIMARY KEY (account_id),
    foreign key (user_id) references USER(user_id)
);

INSERT INTO ACCOUNT (account, is_open, user_id) VALUES ('00000000000000000001', TRUE, 1);
INSERT INTO ACCOUNT (account, is_open, user_id) VALUES ('00000000000000000001', TRUE, 2);

//-----------------CREATE CARD TABLE---------------------------//
CREATE TABLE IF NOT EXISTS CARD
(
    card_id   INTEGER      not null AUTO_INCREMENT,
    number VARCHAR(255),
    balance BIGINT,
    card_type VARCHAR(255),
    pay_system VARCHAR(255),
    account_id INT,
    PRIMARY KEY (card_id),
    foreign key (account_id) references ACCOUNT(account_id)
);

INSERT INTO CARD (number, balance, card_type, pay_system, account_id) VALUES ( '1111 1111 1111 1111', 10,'DEBET', 'VISA', 1 );
INSERT INTO CARD (number, balance, card_type, pay_system, account_id) VALUES ( '2222 2222 2222 2222', 100, 'DEBET', 'MASTERCARD', 1);
INSERT INTO CARD (number, balance, card_type, pay_system, account_id) VALUES ( '3333 3333 3333 3333', 1000,'CREDIT', 'MIR', 1);
INSERT INTO CARD (number, balance, card_type, pay_system, account_id) VALUES ( '4444 4444 4444 4444', 10000000000,'CREDIT', 'VISA', 2);
INSERT INTO CARD (number, balance, card_type, pay_system, account_id) VALUES ( '5555 5555 5555 5555', 99999999999,'CREDIT', 'MASTERCARD', 2);




