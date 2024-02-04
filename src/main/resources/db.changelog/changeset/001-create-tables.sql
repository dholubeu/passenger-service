--liquibase formatted sql

--changeset dholubeu:001_create_tables

set schema 'passengers_schema';

create table passengers
(
    id bigserial,
    email varchar(100) not null unique,
    password varchar(100) not null,
    name varchar(60) not null,
    surname varchar(60) not null,
    date_of_birth date not null,
    phone_number varchar(12) not null,
    rating real default 0.0,
    primary key (id)
);