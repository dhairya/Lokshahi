SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `ppl` DEFAULT CHARACTER SET latin1 ;

-- -----------------------------------------------------
-- Table `ppl`.`categories`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `ppl`.`CATEGORIES` (
  `CAT_ID` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `CATEGORY` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`CAT_ID`) )
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ppl`.`company_details`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `ppl`.`COMPANY_DETAILS` (
  `USER_ID` VARCHAR(30) NOT NULL ,
  `PASSWORD` VARCHAR(32) NOT NULL ,
  `COMPANY_NAME` VARCHAR(30) NULL DEFAULT NULL ,
  `COMPANY_ADD` VARCHAR(50) NULL DEFAULT NULL ,
  `OWNER_NAME` VARCHAR(30) NOT NULL ,
  `OWNER_ADD` VARCHAR(50) NOT NULL ,
  `CONSTI_CODE` CHAR(3) NULL DEFAULT NULL ,
  `COMPANY_REG_NO` VARCHAR(30) NULL DEFAULT NULL ,
  `PHONE_NO` VARCHAR(15) NULL DEFAULT NULL ,
  `EMAIL_ID` VARCHAR(30) NULL DEFAULT NULL ,
  `WEBSITE` VARCHAR(30) NULL DEFAULT NULL ,
  `INDUSTRY` VARCHAR(30) NULL DEFAULT NULL ,
  `CONTACT_NAME` VARCHAR(30) NULL DEFAULT NULL ,
  `CONTACT_PHONE` VARCHAR(30) NULL DEFAULT NULL ,
  `VOTERID_PAN_NO` VARCHAR(30) NULL DEFAULT NULL ,
  PRIMARY KEY (`USER_ID`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ppl`.`constituency`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `ppl`.`CONSTITUENCY` (
  `CODE` CHAR(3) NOT NULL ,
  `CONSTI_NAME` VARCHAR(25) NOT NULL ,
  PRIMARY KEY (`CODE`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ppl`.`global`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `ppl`.`GLOBAL` (
  `VOTERID` VARCHAR(30) NOT NULL ,
  `PASSWORD` VARCHAR(100) NOT NULL ,
  `CONSTI_WARD` VARCHAR(20) NOT NULL )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ppl`.`global_data`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `ppl`.`GLOBAL_DATA` (
  `USERNAME` VARCHAR(10) NOT NULL ,
  `PASSWORD` VARCHAR(25) NOT NULL )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ppl`.`kbase`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `ppl`.`KBASE` (
  `INFO` VARCHAR(500) NULL DEFAULT NULL ,
  `MLANAME` VARCHAR(30) NULL DEFAULT NULL ,
  `MLACONTACT` VARCHAR(200) NULL DEFAULT NULL ,
  `POPULATION` VARCHAR(10) NULL DEFAULT NULL ,
  `LITERACY_RATE` VARCHAR(10) NULL DEFAULT NULL ,
  `SEX_RATIO` VARCHAR(10) NULL DEFAULT NULL ,
  `SCHOOL` VARCHAR(10) NULL DEFAULT NULL ,
  `HOSPITALS` VARCHAR(10) NULL DEFAULT NULL ,
  `POLICE` VARCHAR(10) NULL DEFAULT NULL ,
  `PARKS` VARCHAR(10) NULL DEFAULT NULL ,
  `HELPLINE` VARCHAR(50) NULL DEFAULT NULL ,
  `ADD_POLICE` VARCHAR(200) NULL DEFAULT NULL ,
  `ADD_HOSPITALS` VARCHAR(200) NULL DEFAULT NULL ,
  `ADD_RAILWAY` VARCHAR(200) NULL DEFAULT NULL ,
  `COLLECTOR_OFFICE` VARCHAR(200) NULL DEFAULT NULL ,
  `DEPT_CONTACT` VARCHAR(200) NULL DEFAULT NULL ,
  `MAP` VARCHAR(1500) NULL DEFAULT NULL ,
  `LINK_GOVT_SITES` VARCHAR(200) NULL DEFAULT NULL ,
  `ELECTION_OFFICE` VARCHAR(200) NULL DEFAULT NULL ,
  `CODE` CHAR(3) NOT NULL )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ppl`.`templates`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `ppl`.`TEMPLATES` (
  `TEMPLATE_ID` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `NAME` VARCHAR(50) NOT NULL ,
  `ACCESS_INFO` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`TEMPLATE_ID`) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ppl`.`tempuser`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `ppl`.`TEMPUSER` (
  `VOTERID` VARCHAR(10) NOT NULL ,
  `PASSWORD` VARCHAR(25) NOT NULL ,
  `FNAME` VARCHAR(25) NOT NULL ,
  `MNAME` VARCHAR(25) NOT NULL ,
  `LNAME` VARCHAR(25) NOT NULL ,
  `NICKNAME` VARCHAR(25) NOT NULL ,
  `CONSTI_CODE` VARCHAR(10) NOT NULL ,
  `WARD` VARCHAR(10) NOT NULL ,
  `DDATE` CHAR(2) NOT NULL ,
  `MMONTH` CHAR(2) NOT NULL ,
  `YYEAR` CHAR(4) NOT NULL ,
  `GENDER` CHAR(1) NOT NULL ,
  `ADDRESS` VARCHAR(2000) NOT NULL ,
  `EMAIL` VARCHAR(25) NULL DEFAULT NULL ,
  `PHONE` VARCHAR(18) NULL DEFAULT NULL ,
  `CELL` VARCHAR(12) NULL DEFAULT NULL )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
