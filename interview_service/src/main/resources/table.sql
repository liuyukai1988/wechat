drop database if exists `questions`;
create database `questions` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
--面试题表
CREATE TABLE `answer_question` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Type` int(11) DEFAULT NULL,
  `Title` text COLLATE utf8mb4_bin,
  `Answer1` text COLLATE utf8mb4_bin,
  `Answer2` text COLLATE utf8mb4_bin,
  `Answer3` text COLLATE utf8mb4_bin,
  `ViewCount` int(11) DEFAULT 0,
  `CreateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--账号表
CREATE TABLE `questions`.`user` (
  `ID` INT NOT NULL,
  `UserName` VARCHAR(45) NULL,
  `PassWord` VARCHAR(45) NULL,
  `State` int(2) DEFAULT 0,
  `CreateTime` DATETIME(6) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

--试题类型表
CREATE TABLE `answer_type` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PID` int(11) DEFAULT NULL,
  `TypeName` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `TypeOrder` int(11) DEFAULT NULL,
  `CreateTime` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--会员表
CREATE TABLE `member` (
  `ID` int(20) NOT NULL AUTO_INCREMENT,
  `OpenId` varchar(28) DEFAULT NULL COMMENT '小程序用户的openid',
  `NickName` varchar(100) DEFAULT NULL COMMENT '用户昵称',
  `AvatarUrl` varchar(1000) DEFAULT NULL COMMENT '用户头像',
  `Gender` tinyint(1) DEFAULT NULL COMMENT '性别  0-男、1-女',
  `Country` varchar(100) DEFAULT NULL COMMENT '所在国家',
  `Province` varchar(100) DEFAULT NULL COMMENT '省份',
  `City` varchar(100) DEFAULT NULL COMMENT '城市',
  `Language` varchar(100) DEFAULT NULL COMMENT '语言',
  `CTime` datetime DEFAULT NULL COMMENT '创建/注册时间',
  `Mobile` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `CreateTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小程序用户表';