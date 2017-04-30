START TRANSACTION;

DROP USER 'testuser'@'localhost';
DROP USER 'testuser'@'%';
DROP DATABASE testdb;

CREATE DATABASE IF NOT EXISTS testdb
    CHARACTER SET utf8
    COLLATE utf8_general_ci;

CREATE USER 'testuser'@'localhost' IDENTIFIED BY 'testuser';
GRANT ALL PRIVILEGES ON testdb.* TO 'testuser'@'localhost';

CREATE USER 'testuser'@'%' IDENTIFIED BY 'testuser';
GRANT ALL PRIVILEGES ON testdb.* TO 'testuser'@'%';

FLUSH PRIVILEGES;
COMMIT;

