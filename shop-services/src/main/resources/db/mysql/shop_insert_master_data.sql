-- -------------------------------------------------------
-- Table `shoppingdb`.`Functions`
-- -------------------------------------------------------
DELETE FROM `shoppingdb`.`user`;

-- insert adminuser
INSERT INTO `shoppingdb`.`user` (`CREATED_DATE`, `IS_VALID`, `EMAIL`, `FIRST_NAME`, `LAST_NAME`, `PASSWORD`, `PHONE`, `STATUS`, `USERNAME`) VALUES (now(), 1, 'admin@email.com', 'admin', 'admin', 'password', '1234567890', 'ACTIVE', 'admin');

-- insert suppliers
INSERT INTO `shoppingdb`.`supplier` (`NAME`, `CREATED_BY`, `CREATED_DATE`, `IS_VALID`) VALUES ('ABC Supplier', '1', now(), 1);
INSERT INTO `shoppingdb`.`supplier` (`NAME`, `CREATED_BY`, `CREATED_DATE`, `IS_VALID`) VALUES ('XYZ Supplier', '1', now(), 1);

-- insert product category
INSERT INTO `shoppingdb`.`product_category` (`NAME`, `CREATED_BY`, `CREATED_DATE`, `IS_VALID`) VALUES ('Cakes', 1, now(), 1);
INSERT INTO `shoppingdb`.`product_category` (`NAME`, `CREATED_BY`, `CREATED_DATE`, `IS_VALID`) VALUES ('Flowers', 1, now(), 1);
INSERT INTO `shoppingdb`.`product_category` (`NAME`, `CREATED_BY`, `CREATED_DATE`, `IS_VALID`) VALUES ('Chocolates', 1, now(), 1);

INSERT INTO `shoppingdb`.`product_category` (`NAME`, `CREATED_BY`, `CREATED_DATE`, `IS_VALID`, `PARENT_CATEGORY_ID`) VALUES ('Cakes', 1, now(), 1, 1);
INSERT INTO `shoppingdb`.`product_category` (`NAME`, `CREATED_BY`, `CREATED_DATE`, `IS_VALID`, `PARENT_CATEGORY_ID`) VALUES ('Eggless Cakes', 1, now(), 1, 1);
INSERT INTO `shoppingdb`.`product_category` (`NAME`, `CREATED_BY`, `CREATED_DATE`, `IS_VALID`, `PARENT_CATEGORY_ID`) VALUES ('Suger Free Cakes', 1, now(), 1, 1);
INSERT INTO `shoppingdb`.`product_category` (`NAME`, `CREATED_BY`, `CREATED_DATE`, `IS_VALID`, `PARENT_CATEGORY_ID`) VALUES ('Premium Cakes', 1, now(), 1, 1);

INSERT INTO `shoppingdb`.`product_category` (`NAME`, `CREATED_BY`, `CREATED_DATE`, `IS_VALID`, `PARENT_CATEGORY_ID`) VALUES ('Flowers', 1, now(), 1, 2);
INSERT INTO `shoppingdb`.`product_category` (`NAME`, `CREATED_BY`, `CREATED_DATE`, `IS_VALID`, `PARENT_CATEGORY_ID`) VALUES ('Gift Combos', 1, now(), 1, 2);
INSERT INTO `shoppingdb`.`product_category` (`NAME`, `CREATED_BY`, `CREATED_DATE`, `IS_VALID`, `PARENT_CATEGORY_ID`) VALUES ('Chocolates Bouquets', 1, now(), 1, 2);

-- insert product


commit;

