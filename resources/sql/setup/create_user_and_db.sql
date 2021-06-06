create database surt;

create schema surt;

-- password = surt
CREATE ROLE surt WITH
    LOGIN
    SUPERUSER
    INHERIT
    CREATEDB
    CREATEROLE
    REPLICATION
    ENCRYPTED PASSWORD 'md5f8c65d8e4b1e86ba9e0daa1d82c8d947';