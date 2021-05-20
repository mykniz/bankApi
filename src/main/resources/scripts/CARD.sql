DROP TABLE IF EXISTS CARD;

CREATE TABLE IF NOT EXISTS CARD
(
    card_id   INTEGER      not null AUTO_INCREMENT,
    number VARCHAR(255),
    card_type VARCHAR(255),
    pay_system VARCHAR(255),
    is_active BOOL,
    account_id INT,
    PRIMARY KEY (card_id),
    UNIQUE (number),
    foreign key (account_id) references ACCOUNT(account_id)
);

insert into CARD (number, card_type, pay_system, is_active, account_id) values ('4994125505622193', 'DEBET', 'VISA', true, 1);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('6468652353287900', 'DEBET', 'VISA', true, 2);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('9495700472568181', 'DEBET', 'VISA', true, 3);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('1032274611722232', 'DEBET', 'VISA', true, 4);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('1772841203002047', 'DEBET', 'VISA', true, 5);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('1865500216113361', 'DEBET', 'VISA', true, 6);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('8114691717465423', 'DEBET', 'VISA', true, 7);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('0103843229131417', 'DEBET', 'VISA', false, 8);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('8867999681626240', 'DEBET', 'VISA', false, 9);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('6020994409354683', 'DEBET', 'VISA', false, 10);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('3977099096556852', 'DEBET', 'VISA', true, 11);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('7758146279354457', 'DEBET', 'VISA', false, 11);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('4827962168934295', 'DEBET', 'VISA', false, 11);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('1545854117516842', 'DEBET', 'VISA', false, 11);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('9578964012194478', 'DEBET', 'VISA', true, 11);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('6670466686397532', 'DEBET', 'VISA', false, 12);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('6736120655927725', 'DEBET', 'VISA', true, 12);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('4470519348988070', 'DEBET', 'VISA', false, 12);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('6034616555369052', 'DEBET', 'VISA', true, 12);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('3576708664986986', 'DEBET', 'VISA', true, 12);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('1369794890153349', 'DEBET', 'VISA', true, 12);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('8456252448334480', 'DEBET', 'VISA', true, 12);

insert into CARD (number, card_type, pay_system, is_active, account_id) values ('3596589122236442', 'DEBET', 'MASTERCARD', true, 13);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('0886247445920447', 'DEBET', 'MASTERCARD', true, 14);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('2978797142770814', 'DEBET', 'MASTERCARD', true, 14);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('7021451364414179', 'DEBET', 'MASTERCARD', true, 14);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('4985173465622141', 'DEBET', 'MASTERCARD', false, 14);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('5295443930765683', 'DEBET', 'MASTERCARD', false, 14);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('6247005385996060', 'DEBET', 'MASTERCARD', true, 14);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('3703075607206368', 'DEBET', 'MASTERCARD', true, 14);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('4878198737909722', 'DEBET', 'MASTERCARD', true, 14);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('2648458525511478', 'DEBET', 'MASTERCARD', true, 14);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('7362295318253693', 'DEBET', 'MASTERCARD', true, 14);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('3713584506515215', 'DEBET', 'MASTERCARD', false, 14);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('7340471875233802', 'DEBET', 'MASTERCARD', false, 15);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('0279377766417123', 'DEBET', 'MASTERCARD', false, 15);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('9923904396117910', 'DEBET', 'MASTERCARD', false, 15);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('9944919479045429', 'DEBET', 'MASTERCARD', true, 16);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('9946749126526571', 'DEBET', 'MASTERCARD', true, 16);

insert into CARD (number, card_type, pay_system, is_active, account_id) values ('3774744089843907', 'CREDIT', 'MIR', false, 16);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('1420557784616134', 'CREDIT', 'MIR', true, 16);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('8026564147597868', 'CREDIT', 'MIR', true, 16);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('1173295655619834', 'CREDIT', 'MIR', true, 16);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('2004745619932758', 'CREDIT', 'MIR', true, 16);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('6851204010102061', 'CREDIT', 'MIR', false, 16);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('9309486367034134', 'CREDIT', 'MIR', true, 17);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('6507715596679336', 'CREDIT', 'MIR', false, 17);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('4412141765028543', 'CREDIT', 'MIR', true, 18);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('2765295780347423', 'CREDIT', 'MIR', true, 18);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('9246841636075554', 'CREDIT', 'MIR', true, 18);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('1062340668054672', 'CREDIT', 'MIR', true, 19);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('8685534166998947', 'CREDIT', 'MIR', true, 19);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('5441044117164113', 'CREDIT', 'MIR', true, 19);
insert into CARD (number, card_type, pay_system, is_active, account_id) values ('8535019801488809', 'CREDIT', 'MIR', false, 20);

