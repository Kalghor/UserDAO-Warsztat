DROP DATABASE workshop2;
CREATE DATABASE if not exists workshop2
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
USE workshop2;
CREATE TABLE users (
                       id INT(11) AUTO_INCREMENT,
                       username VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE,
                       password VARCHAR(60) NOT NULL,
                       PRIMARY KEY(id)
);