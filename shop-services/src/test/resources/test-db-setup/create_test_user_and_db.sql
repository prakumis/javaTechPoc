/*--==============================================================================
-- * 
-- * FILE: create_test_user_and_db.sql   
-- *
-- * MODULE DESCRIPTION:
-- * This script creates a testuser@localhost user w/ password shop.
-- * Creates testdb database and gives testuser@localhost access to the database.
-- *
-- * (c) COPYRIGHT 2016-2017 NYP Inc.  All Rights Reserved.
-- * NYP Inc. Proprietary.
-- * 
--==============================================================================*/
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

