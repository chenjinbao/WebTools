/*
Navicat MySQL Data Transfer

Source Server         : MariaDB
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : webtools

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2014-11-15 22:51:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_ddm_action
-- ----------------------------
DROP TABLE IF EXISTS `t_ddm_action`;
CREATE TABLE `t_ddm_action` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` varchar(10) NOT NULL,
  `action_type` varchar(50) NOT NULL,
  `field_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ddm_action
-- ----------------------------
INSERT INTO `t_ddm_action` VALUES ('1', '2', 'ddmQueryDisk', 'enableDiskClean');
INSERT INTO `t_ddm_action` VALUES ('2', '2', 'ddmCleanDisk', 'enableDiskClean');
INSERT INTO `t_ddm_action` VALUES ('3', '2', 'ddmOperationResetBoard', 'enableReset');
INSERT INTO `t_ddm_action` VALUES ('4', '2', 'ddmOperationPowerOffResetBoard', 'enablePowerReset');
INSERT INTO `t_ddm_action` VALUES ('5', '2', 'ddmOperationPowerOnBoard', 'enablePowerOn');
INSERT INTO `t_ddm_action` VALUES ('6', '2', 'ddmOperationPowerOffBoard', 'enablePowerOff');
INSERT INTO `t_ddm_action` VALUES ('7', '2', 'ddmOperationHardwareResetBoard', 'enableHardwareReset');
INSERT INTO `t_ddm_action` VALUES ('8', '2', 'ddmOperationBlockBoard', 'enableBlock');
INSERT INTO `t_ddm_action` VALUES ('9', '2', 'ddmOperationUnblockBoard', 'enableBlock');
INSERT INTO `t_ddm_action` VALUES ('10', '2', 'ddmOperationPaOn', 'enablePA1');
INSERT INTO `t_ddm_action` VALUES ('11', '2', 'ddmOperationPaOnNEW', 'enablePA2');
INSERT INTO `t_ddm_action` VALUES ('12', '2', 'ddmOperationPaOff', 'enablePA1');
INSERT INTO `t_ddm_action` VALUES ('13', '2', 'ddmOperationPaOffNEW', 'enablePA2');
INSERT INTO `t_ddm_action` VALUES ('14', '2', 'ddmQueryCarrierPower', 'notSupport');
INSERT INTO `t_ddm_action` VALUES ('15', '2', 'ddmQueryCarrierPowerNEW', 'enableCarrierPower');
INSERT INTO `t_ddm_action` VALUES ('16', '2', 'ddmQueryFile', 'rruQueryFile');
INSERT INTO `t_ddm_action` VALUES ('17', '2', 'ddmOperationFileDownload', 'rruFileDownload');
INSERT INTO `t_ddm_action` VALUES ('18', '2', 'ddmOperationFileUpload', 'rruFileUpload');
INSERT INTO `t_ddm_action` VALUES ('19', '2', 'ddmOperationFileDelete', 'rruFileDelete');
INSERT INTO `t_ddm_action` VALUES ('20', '2', 'ddmAASFileDownload', 'rruAASFileDownload');
INSERT INTO `t_ddm_action` VALUES ('21', '2', 'ddmAASOpen', 'rruAASOpen');
INSERT INTO `t_ddm_action` VALUES ('22', '2', 'ddmAASClose', 'rruAASClose');
INSERT INTO `t_ddm_action` VALUES ('23', '2', 'ddmQueryAASStatus', 'rruQueryAASStatus');
INSERT INTO `t_ddm_action` VALUES ('24', '2', 'openAisgPower', 'aisgPowerOpen');
INSERT INTO `t_ddm_action` VALUES ('25', '2', 'closeAisgPower', 'aisgPowerClose');
INSERT INTO `t_ddm_action` VALUES ('26', '2', 'subscribeReversePowerTest', 'subscribeReversePowerTest');
INSERT INTO `t_ddm_action` VALUES ('27', '2', 'unsubscribeReversePowerTest', 'unsubscribeReversePowerTest');
INSERT INTO `t_ddm_action` VALUES ('28', '2', 'ddmOperationLogUpload', 'rruLogUpload');

-- ----------------------------
-- Table structure for t_ddm_board
-- ----------------------------
DROP TABLE IF EXISTS `t_ddm_board`;
CREATE TABLE `t_ddm_board` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` varchar(10) NOT NULL,
  `column_name` varchar(50) NOT NULL,
  `field_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ddm_board
-- ----------------------------
INSERT INTO `t_ddm_board` VALUES ('1', '2', '单板名称', 'boardName');
INSERT INTO `t_ddm_board` VALUES ('2', '2', '无线制式', 'radioMode');
INSERT INTO `t_ddm_board` VALUES ('3', '2', '主控设备', 'enableDiskClean');
INSERT INTO `t_ddm_board` VALUES ('4', '2', '复位操作', 'enableReset');
INSERT INTO `t_ddm_board` VALUES ('5', '2', '下电复位操作', 'enablePowerReset');
INSERT INTO `t_ddm_board` VALUES ('6', '2', '硬复位操作', 'enableHardwareReset');
INSERT INTO `t_ddm_board` VALUES ('7', '2', '阻塞/解阻塞操作', 'enableBlock');
INSERT INTO `t_ddm_board` VALUES ('8', '2', '启用/停用操作', 'enablePA1');
INSERT INTO `t_ddm_board` VALUES ('9', '2', '上电', 'enablePowerOn');
INSERT INTO `t_ddm_board` VALUES ('10', '2', '下电', 'enablePowerOff');

-- ----------------------------
-- Table structure for t_ddm_rru
-- ----------------------------
DROP TABLE IF EXISTS `t_ddm_rru`;
CREATE TABLE `t_ddm_rru` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `column_name` varchar(50) NOT NULL,
  `field_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ddm_rru
-- ----------------------------
INSERT INTO `t_ddm_rru` VALUES ('2', '2', '单板名称', 'boardName');
INSERT INTO `t_ddm_rru` VALUES ('3', '2', '无线制式', 'radioMode');
INSERT INTO `t_ddm_rru` VALUES ('4', '2', '复位操作', 'enableReset');
INSERT INTO `t_ddm_rru` VALUES ('5', '2', '掉电复位操作', 'enablePowerReset');
INSERT INTO `t_ddm_rru` VALUES ('6', '2', '硬复位操作', 'enableHardwareReset');
INSERT INTO `t_ddm_rru` VALUES ('7', '2', '闭塞/解闭塞操作', 'enableBlock');
INSERT INTO `t_ddm_rru` VALUES ('8', '2', 'PA使能/去使能（新）', 'enablePA2');
INSERT INTO `t_ddm_rru` VALUES ('9', '2', '查询载波功率（新）', 'enableCarrierPower');
INSERT INTO `t_ddm_rru` VALUES ('10', '2', 'RRU文件查询', 'rruQueryFile');
INSERT INTO `t_ddm_rru` VALUES ('11', '2', 'RRU文件下载', 'rruFileDownload');
INSERT INTO `t_ddm_rru` VALUES ('12', '2', 'RRU文件上传', 'rruFileUpload');
INSERT INTO `t_ddm_rru` VALUES ('13', '2', 'RRU文件删除', 'rruFileDelete');
INSERT INTO `t_ddm_rru` VALUES ('14', '2', '下载幅相参数文件', 'rruAASFileDownload');
INSERT INTO `t_ddm_rru` VALUES ('15', '2', '开启有源天线自愈', 'rruAASOpen');
INSERT INTO `t_ddm_rru` VALUES ('16', '2', '关闭有源天线自愈', 'rruAASClose');
INSERT INTO `t_ddm_rru` VALUES ('17', '2', '查询有源天线自愈状态', 'rruQueryAASStatus');
INSERT INTO `t_ddm_rru` VALUES ('18', '2', '打开AISG口供电', 'aisgPowerOpen');
INSERT INTO `t_ddm_rru` VALUES ('19', '2', '关闭AISG口供电', 'aisgPowerClose');
INSERT INTO `t_ddm_rru` VALUES ('20', '2', '订阅反向功率检测', 'subscribeReversePowerTest');
INSERT INTO `t_ddm_rru` VALUES ('21', '2', '退订反向功率检测', 'unsubscribeReversePowerTest');
INSERT INTO `t_ddm_rru` VALUES ('22', '2', 'RRU日志上传', 'rruLogUpload');

-- ----------------------------
-- Table structure for t_ddm_version
-- ----------------------------
DROP TABLE IF EXISTS `t_ddm_version`;
CREATE TABLE `t_ddm_version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` varchar(10) NOT NULL,
  `display` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ddm_version` (`version`) USING BTREE,
  UNIQUE KEY `ddm_display` (`display`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ddm_version
-- ----------------------------
INSERT INTO `t_ddm_version` VALUES ('1', '1', '1.02.02.01');
INSERT INTO `t_ddm_version` VALUES ('2', '2', '1.02.03.01');

-- ----------------------------
-- Table structure for t_task
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_task
-- ----------------------------
INSERT INTO `t_task` VALUES ('1', 'Study PlayFramework 2.0', 'http://www.playframework.org/', '2');
INSERT INTO `t_task` VALUES ('2', 'Study Grails 2.0', 'http://www.grails.org/', '2');
INSERT INTO `t_task` VALUES ('3', 'Try SpringFuse', 'http://www.springfuse.com/', '2');
INSERT INTO `t_task` VALUES ('4', 'Try Spring Roo', 'http://www.springsource.org/spring-roo', '2');
INSERT INTO `t_task` VALUES ('5', 'Release SpringSide 4.0', 'As soon as posibble.', '2');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(64) NOT NULL,
  `roles` varchar(255) NOT NULL,
  `register_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'Admin', '691b14d79bf0fa2215f155235df5e670b64394cc', '7efbd59d9741d34f', 'admin', '2012-06-04 01:00:00');
INSERT INTO `t_user` VALUES ('2', 'user', 'Calvin', '2488aa0c31c624687bd9928e0a5d29e7d1ed520b', '6d65d24122c30500', 'user', '2012-06-04 02:00:00');
