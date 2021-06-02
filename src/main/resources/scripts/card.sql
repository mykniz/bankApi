DROP TABLE IF EXISTS CARD;

CREATE TABLE IF NOT EXISTS CARD
(
    card_id   INTEGER      not null AUTO_INCREMENT,
    number VARCHAR(255),
    card_type VARCHAR(255),
    pay_system VARCHAR(255),
    is_active BOOL,
    account_id INT UNIQUE,
    PRIMARY KEY (card_id),
    UNIQUE (number),
    foreign key (account_id) references ACCOUNT(account_id)
);

insert into CARD (number, card_type, pay_system, is_active, account_id) values ('4994125505622193', 'CREDIT', 'VISA', true, 1);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('9495700472568181', 'CREDIT', 'VISA', true, 1);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('1032274611722232', 'CREDIT', 'MASTERCARD', true, 2);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('1772841203002047', 'DEBET', 'MIR', false, 3);


