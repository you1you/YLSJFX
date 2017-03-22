/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : ylsjfx

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-03-22 12:44:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '',
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `dlm` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('b', '3e6c8b8eeb29411197128c8ad85fb24c', 'b', 'man', 'b', '15506772241');
INSERT INTO `user` VALUES ('c', '40288040598879ec0159887b8d990001', 'c', 'man', 'c', '15778561795');
INSERT INTO `user` VALUES ('f', '402880405988839c01598883f4ba0001', 'f', 'man', 'f', '13878511623');

-- ----------------------------
-- Table structure for yldata
-- ----------------------------
DROP TABLE IF EXISTS `yldata`;
CREATE TABLE `yldata` (
  `住院号` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `区域` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `出院科室` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `病人名字` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `性别` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `年龄` int(11) DEFAULT NULL,
  `出院诊断` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `出院日期` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of yldata
-- ----------------------------
