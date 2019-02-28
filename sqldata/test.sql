/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2019-02-21 08:34:09
*/
CREATE DATABASE test;
USE test;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `photo` mediumblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES ('1', '汪峰', 'wf@126.com', '2010-02-02', null);
INSERT INTO `customers` VALUES ('2', '王菲', 'wangf@163.com', '1988-12-26', null);
INSERT INTO `customers` VALUES ('3', '林志玲', 'linzl@gmail.com', '1984-06-12', null);
INSERT INTO `customers` VALUES ('4', '汤唯', 'tangw@sina.com', '1986-06-13', null);
INSERT INTO `customers` VALUES ('5', '成龙', 'Jackey@gmai.com', '1955-07-14', null);
INSERT INTO `customers` VALUES ('6', '郭富城', 'guofc@163.com', '1983-05-17', null);
INSERT INTO `customers` VALUES ('7', '刘菲', 'yangm@qq.com', '1991-11-14', null);
INSERT INTO `customers` VALUES ('8', '陈道明', 'bdf@126.com', '2014-01-17', null);
INSERT INTO `customers` VALUES ('10', '周杰伦', 'zhoujl@sina.com', '1979-11-15', null);
INSERT INTO `customers` VALUES ('12', '黎明', 'LiM@126.com', '1998-09-08', null);
INSERT INTO `customers` VALUES ('13', '张学友', 'zhangxy@126.com', '1998-12-21', null);
INSERT INTO `customers` VALUES ('18', '贝多芬', 'beidf@126.com', '2014-01-17', null);

-- ----------------------------
-- Table structure for examstudent
-- ----------------------------
DROP TABLE IF EXISTS `examstudent`;
CREATE TABLE `examstudent` (
  `FlowID` int(20) NOT NULL AUTO_INCREMENT,
  `Type` int(20) DEFAULT NULL,
  `IDCard` varchar(18) DEFAULT NULL,
  `ExamCard` varchar(15) DEFAULT NULL,
  `StudentName` varchar(20) DEFAULT NULL,
  `Location` varchar(20) DEFAULT NULL,
  `Grade` int(10) DEFAULT NULL,
  PRIMARY KEY (`FlowID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of examstudent
-- ----------------------------
INSERT INTO `examstudent` VALUES ('1', '4', '412824195263214584', '200523164754000', '张锋', '郑州\r\n', '85');
INSERT INTO `examstudent` VALUES ('2', '4', '222224195263214584', '200523164754001', '孙朋', '大连\r\n', '56');
INSERT INTO `examstudent` VALUES ('3', '6', '342824195263214584', '200523164754002', '刘明\r\n', '沈阳\r\n', '72');
INSERT INTO `examstudent` VALUES ('4', '6', '100824195263214584', '200523164754003', '赵虎\r\n', '哈尔滨\r\n', '95');
INSERT INTO `examstudent` VALUES ('5', '4', '454524195263214584', '200523164754004', '杨丽\r\n', '北京\r\n', '64');
INSERT INTO `examstudent` VALUES ('6', '4', '854524195263214584', '200523164754005', '王小红\r\n', '太原\r\n', '60');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(10) NOT NULL AUTO_INCREMENT,
  `order_name` varchar(20) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', 'AA', '2010-03-04');
INSERT INTO `order` VALUES ('2', 'BB', '2000-02-01');
INSERT INTO `order` VALUES ('4', 'GG', '1994-06-28');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '章子怡', 'Beijing', '13788658672');
INSERT INTO `users` VALUES ('2', '郭富城', 'HongKong', '15678909898');
INSERT INTO `users` VALUES ('3', '林志颖', 'Taiwan', '18612124565');
INSERT INTO `users` VALUES ('4', '梁静茹', 'malaixiya', '18912340998');
INSERT INTO `users` VALUES ('5', 'LadyGaGa', 'America', '13012386565');

-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table` (
  `user` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `balance` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of user_table
-- ----------------------------
INSERT INTO `user_table` VALUES ('AA', '123456', '900');
INSERT INTO `user_table` VALUES ('BB', '654321', '1100');
INSERT INTO `user_table` VALUES ('CC', 'abcd', '2000');
INSERT INTO `user_table` VALUES ('DD', 'abcder', '3000');