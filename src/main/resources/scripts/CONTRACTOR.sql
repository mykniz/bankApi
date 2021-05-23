DROP TABLE IF EXISTS CONTRACTOR;

CREATE TABLE IF NOT EXISTS CONTRACTOR
(
    id   INTEGER      not null AUTO_INCREMENT,
    first_name VARCHAR(255) not null,
    last_name VARCHAR(255) not null,
    phone_number VARCHAR(255) not null,
    client_id int,
    contractor_id int,
    PRIMARY KEY (id),
    foreign key (client_id) references CLIENT(client_id)
);