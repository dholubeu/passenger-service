--liquibase formatted sql

--changeset dholubeuu:002_edit_tables

alter table passengers drop column password;