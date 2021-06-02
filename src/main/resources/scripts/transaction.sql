DROP TABLE IF EXISTS TRANSACTION;

CREATE TABLE IF NOT EXISTS TRANSACTION
(
    transaction_id INTEGER not null AUTO_INCREMENT,
    client_id      int UNIQUE,
    contractor_id  int UNIQUE,
    PRIMARY KEY (transaction_id),
    foreign key (client_id) references CLIENT (client_id),
    foreign key (contractor_id) references CLIENT (client_id)
);

