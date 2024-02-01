CREATE TABLE usuario (
    id varchar(255) primary key unique not null,
    login varchar(255) not null unique,
    password varchar(255) not null,
    role varchar(255) not null
);