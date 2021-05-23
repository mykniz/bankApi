DROP TABLE IF EXISTS CLIENT CASCADE;

CREATE TABLE IF NOT EXISTS CLIENT
(
    client_id   INTEGER      not null AUTO_INCREMENT,
    first_name VARCHAR(255) not null,
    last_name VARCHAR(255) not null,
    phone_number VARCHAR(255) not null,
    PRIMARY KEY (client_id)
);

insert into CLIENT (first_name, last_name, phone_number) values ('Leo', 'Messi', '+7(070)-246-3274');
insert into CLIENT (first_name, last_name, phone_number) values ('John', 'Smith', '+7(712)-305-9426');
insert into CLIENT (first_name, last_name, phone_number) values ('Fleurette', 'Aldins', '+7(064)-014-9187');
insert into CLIENT (first_name, last_name, phone_number) values ('Lucilia', 'Studdard', '+7(295)-392-7920');
