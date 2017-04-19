/*--==============================================================================
-- * 
-- * FILE: shop_create_user_and_db.sql   
-- *
-- * MODULE DESCRIPTION:
-- * This script creates a shop@localhost user w/ password shop.
-- * Creates shoppingdb database and gives shop@localhost access to the database.
-- *
-- * (c) COPYRIGHT 2016-2017 NYP Inc.  All Rights Reserved.
-- * NYP Inc. Proprietary.
-- * 
--==============================================================================*/
START TRANSACTION;

DROP USER 'shop'@'localhost';
DROP USER 'shop'@'%';
DROP DATABASE shoppingdb;

CREATE DATABASE IF NOT EXISTS shoppingdb
    CHARACTER SET utf8
    COLLATE utf8_general_ci;

CREATE USER 'shop'@'localhost' IDENTIFIED BY 'shop';
GRANT ALL PRIVILEGES ON shoppingdb.* TO 'shop'@'localhost';

CREATE USER 'shop'@'%' IDENTIFIED BY 'shop';
GRANT ALL PRIVILEGES ON shoppingdb.* TO 'shop'@'%';

FLUSH PRIVILEGES;
COMMIT;

