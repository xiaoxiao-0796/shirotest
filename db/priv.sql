/*
Navicat MySQL Data Transfer

Source Server         : mysql-xiaofei
Source Server Version : 50530
Source Host           : localhost:3306
Source Database       : shirotest

Target Server Type    : MYSQL
Target Server Version : 50530
File Encoding         : 65001

Date: 2018-04-30 22:49:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for priv
-- ----------------------------
DROP TABLE IF EXISTS `priv`;
CREATE TABLE `priv` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `priv_name` varchar(64) DEFAULT NULL,
  `priv_desc` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of priv
-- ----------------------------
INSERT INTO `priv` VALUES ('1', 'insert', '新增1');
