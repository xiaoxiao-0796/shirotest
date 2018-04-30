/*
Navicat MySQL Data Transfer

Source Server         : mysql-xiaofei
Source Server Version : 50530
Source Host           : localhost:3306
Source Database       : shirotest

Target Server Type    : MYSQL
Target Server Version : 50530
File Encoding         : 65001

Date: 2018-04-30 22:49:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `sex` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'xiaofei', 'e10adc3949ba59abbe56e057f20f883e', 'M');
