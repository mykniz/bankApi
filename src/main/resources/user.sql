
//--------------------------------------------//
DROP TABLE IF EXISTS CARD;
DROP TABLE IF EXISTS ACCOUNT;
DROP TABLE IF EXISTS USER;


//-----------------CREATE USER TABLE---------------------------//
CREATE TABLE IF NOT EXISTS USER
(
    user_id   INTEGER      not null AUTO_INCREMENT,
    first_name VARCHAR(255) not null,
    last_name VARCHAR(255) not null,
    PRIMARY KEY (user_id)
);
INSERT INTO USER (first_name, last_name) VALUES ('John', 'Smith');
INSERT INTO USER (first_name, last_name) VALUES ('Leo', 'Messi');


//-----------------CREATE ACCOUNT TABLE---------------------------//
CREATE TABLE IF NOT EXISTS ACCOUNT
(
    account_id   INTEGER      not null AUTO_INCREMENT,
    account VARCHAR(255) not null,
    balance DECIMAL(13,2),
    is_open BOOL,
    user_id int,
    PRIMARY KEY (account_id),
    foreign key (user_id) references USER(user_id)
);

INSERT INTO ACCOUNT (account, balance, is_open, user_id) VALUES ('00000000000000000001', 100.00, TRUE, 1);
INSERT INTO ACCOUNT (account, balance, is_open, user_id) VALUES ('00000000000000000002', 1000000.00, TRUE, 2);
INSERT INTO ACCOUNT (account, balance, is_open, user_id) VALUES ('00000000000000000003', 5000000.00, TRUE, 2);
INSERT INTO ACCOUNT (account, balance, is_open, user_id) VALUES ('00000000000000000004', 6000000.00, TRUE, 2);



//-----------------CREATE CARD TABLE---------------------------//
CREATE TABLE IF NOT EXISTS CARD
(
    card_id   INTEGER      not null AUTO_INCREMENT,
    number VARCHAR(255),
    card_type VARCHAR(255),
    pay_system VARCHAR(255),
    is_active BOOL,
    account_id INT,
    PRIMARY KEY (card_id),
    foreign key (account_id) references ACCOUNT(account_id)
);

INSERT INTO CARD (number, card_type, pay_system, is_active, account_id) VALUES ( '1111 1111 1111 1111','DEBET', 'VISA', true, 1 );
INSERT INTO CARD (number, card_type, pay_system, is_active, account_id) VALUES ( '2222 2222 2222 2222', 'DEBET', 'MASTERCARD', true, 1);
INSERT INTO CARD (number, card_type, pay_system, is_active, account_id) VALUES ( '3333 3333 3333 3333','CREDIT', 'MIR', true, 1);
INSERT INTO CARD (number, card_type, pay_system, is_active, account_id) VALUES ( '4444 4444 4444 4444', 'CREDIT', 'VISA', true, 2);
INSERT INTO CARD (number, card_type, pay_system, is_active, account_id) VALUES ( '5555 5555 5555 5555','CREDIT', 'MASTERCARD', true, 2);




