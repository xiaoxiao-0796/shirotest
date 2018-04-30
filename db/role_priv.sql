/*
Navicat MySQL Data Transfer

Source Server         : mysql-xiaofei
Source Server Version : 50530
Source Host           : localhost:3306
Source Database       : shirotest

Target Server Type    : MYSQL
Target Server Version : 50530
File Encoding         : 65001

Date: 2018-04-30 22:49:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role_priv
-- ----------------------------
DROP TABLE IF EXISTS `role_priv`;
CREATE TABLE `role_priv` (
  `role_name` varchar(32) DEFAULT NULL,
  `priv_name` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_priv
-- ----------------------------
INSERT INTO `role_priv` VALUES ('admin', 'insert');
