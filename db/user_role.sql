/*
Navicat MySQL Data Transfer

Source Server         : mysql-xiaofei
Source Server Version : 50530
Source Host           : localhost:3306
Source Database       : shirotest

Target Server Type    : MYSQL
Target Server Version : 50530
File Encoding         : 65001

Date: 2018-04-30 22:49:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_name` varchar(32) DEFAULT NULL,
  `role_name` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('xiaofei', 'admin');
