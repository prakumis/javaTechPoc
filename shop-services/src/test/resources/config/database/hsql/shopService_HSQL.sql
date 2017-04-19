-- -----------------------------------------------------
-- Table shoppingdb.Employee
-- -----------------------------------------------------
DROP TABLE Employee IF EXISTS;;

CREATE TABLE Employee (
  EmployeeID INT NOT NULL PRIMARY KEY,
  EmpName VARCHAR(200) NOT NULL,
  CreatedBy VARCHAR(200),
  CreationDate TIMESTAMP,
  LastUpdatedBy  VARCHAR(200),
  LastUpdatedDate TIMESTAMP,
  UNIQUE (EmployeeID));;

INSERT INTO Employee (EmployeeID, EmpName) VALUES (1, 'Praveen Kumar Mishra');
