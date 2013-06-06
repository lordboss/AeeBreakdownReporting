delimiter $$

CREATE DATABASE `aeebk` /*!40100 DEFAULT CHARACTER SET utf8 */$$

CREATE TABLE `bks_archived` (
  `CITY` varchar(35) NOT NULL,
  `AREA` varchar(55) NOT NULL,
  `STATUS` varchar(100) DEFAULT NULL,
  `RPTD_LAST_UPDATE_TS` datetime DEFAULT NULL,
  `OPEN_TS` datetime NOT NULL,
  `CLOSE_TS` datetime NOT NULL,
  PRIMARY KEY (`CITY`,`AREA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

CREATE TABLE `bks_reported` (
  `CITY` varchar(35) NOT NULL,
  `AREA` varchar(55) NOT NULL,
  `STATUS` varchar(100) DEFAULT NULL,
  `RPTD_LAST_UPDATE_TS` datetime DEFAULT NULL,
  `OPEN_TS` datetime NOT NULL,
  PRIMARY KEY (`CITY`,`AREA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

CREATE TABLE `users` (
  `PK_USER_ID` varchar(50) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `PHONE` int(11) DEFAULT NULL,
  `SMS_IND` char(1) DEFAULT NULL,
  PRIMARY KEY (`PK_USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

CREATE TABLE `bk_2_users` (
  `CITY` varchar(35) NOT NULL,
  `AREA` varchar(55) NOT NULL,
  `FK_USER_ID` varchar(50) NOT NULL,
  PRIMARY KEY (`CITY`,`AREA`,`FK_USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

