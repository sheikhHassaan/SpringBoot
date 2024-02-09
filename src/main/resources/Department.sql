CREATE SCHEMA `Department` ;

CREATE TABLE `Department`.`new_table` (
                                          `departmentId` CHAR(36) NOT NULL,
                                          `departmentName` VARCHAR(255) NOT NULL,
                                          `departmentCode` VARCHAR(255) NULL,
                                          `departmentAddress` VARCHAR(255) NULL,
                                          PRIMARY KEY (`departmentId`),
                                          UNIQUE INDEX `departmentId_UNIQUE` (`departmentId` ASC) VISIBLE);

ALTER TABLE `Department`.`new_table`
    RENAME TO  `Department`.`department` ;

ALTER TABLE `Department`.`department`
    CHANGE COLUMN `departmentId` `department_id` CHAR(36) NOT NULL ,
    CHANGE COLUMN `departmentName` `department_name` VARCHAR(255) NOT NULL ,
    CHANGE COLUMN `departmentCode` `department_code` VARCHAR(255) NULL DEFAULT NULL ,
    CHANGE COLUMN `departmentAddress` `department_address` VARCHAR(255) NULL DEFAULT NULL ;

