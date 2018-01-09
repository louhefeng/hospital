/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : hospitalprivate

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-09 18:59:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `tezheng` varchar(255) DEFAULT NULL,
  `money` int(10) DEFAULT NULL,
  `yiliao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6', '1', '18231131593', '1', '2017-12-26 11:34:26', '1', '1', '1');

-- ----------------------------
-- Table structure for `userlog`
-- ----------------------------
DROP TABLE IF EXISTS `userlog`;
CREATE TABLE `userlog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vipid` bigint(20) NOT NULL,
  `type` int(11) NOT NULL COMMENT '0卡消费1现金消费2充值3修改',
  `yiliao` varchar(255) DEFAULT NULL,
  `money` bigint(20) NOT NULL,
  `createdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `tezheng` varchar(255) DEFAULT NULL,
  `oldusermoney` varchar(255) NOT NULL COMMENT '操作前会员余额',
  `updatedate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userlog
-- ----------------------------

-- ----------------------------
-- Table structure for `vipuser`
-- ----------------------------
DROP TABLE IF EXISTS `vipuser`;
CREATE TABLE `vipuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(22) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `addr` varchar(100) NOT NULL,
  `money` bigint(20) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `imgurl` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `creatdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `updatedate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vipuser
-- ----------------------------
