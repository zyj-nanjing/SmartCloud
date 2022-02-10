/*
 Navicat Premium Data Transfer

 Source Server         : DEV——DB
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : smart_cloud

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 10/02/2022 13:07:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_token
-- ----------------------------
DROP TABLE IF EXISTS `auth_token`;
CREATE TABLE `auth_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enableRefresh` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否支持 refreshToken, 默认: 1. 1 表示支持, 0 表示不支持',
  `providerId` varchar(20) DEFAULT NULL COMMENT '第三方服务商,如: qq,github',
  `accessToken` varchar(512) DEFAULT NULL COMMENT 'accessToken',
  `expireIn` bigint(20) DEFAULT '-1' COMMENT 'accessToken 过期时间, 无过期时间默认为 -1',
  `refreshTokenExpireIn` bigint(20) DEFAULT '-1' COMMENT 'refreshToken 过期时间, 无过期时间默认为 -1',
  `refreshToken` varchar(512) DEFAULT NULL COMMENT 'refreshToken',
  `uid` varchar(20) DEFAULT NULL COMMENT 'alipay userId',
  `openId` varchar(256) DEFAULT NULL COMMENT 'qq/mi/toutiao/wechatMp/wechatOpen/weibo/jd/kujiale/dingTalk/douyin/feishu',
  `accessCode` varchar(256) DEFAULT NULL COMMENT 'dingTalk, taobao 附带属性',
  `unionId` varchar(256) DEFAULT NULL COMMENT 'QQ附带属性',
  `scope` varchar(256) DEFAULT NULL COMMENT 'Google附带属性',
  `tokenType` varchar(20) DEFAULT NULL COMMENT 'Google附带属性',
  `idToken` varchar(256) DEFAULT NULL COMMENT 'Google附带属性',
  `macAlgorithm` varchar(20) DEFAULT NULL COMMENT '小米附带属性',
  `macKey` varchar(256) DEFAULT NULL COMMENT '小米附带属性',
  `code` varchar(256) DEFAULT NULL COMMENT '企业微信附带属性',
  `oauthToken` varchar(256) DEFAULT NULL COMMENT 'Twitter附带属性',
  `oauthTokenSecret` varchar(256) DEFAULT NULL COMMENT 'Twitter附带属性',
  `userId` varchar(64) DEFAULT NULL COMMENT 'Twitter附带属性',
  `screenName` varchar(64) DEFAULT NULL COMMENT 'Twitter附带属性',
  `oauthCallbackConfirmed` varchar(64) DEFAULT NULL COMMENT 'Twitter附带属性',
  `expireTime` bigint(20) DEFAULT '-1' COMMENT '过期时间, 基于 1970-01-01T00:00:00Z, 无过期时间默认为 -1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_token
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for biz_scheduled_config
-- ----------------------------
DROP TABLE IF EXISTS `biz_scheduled_config`;
CREATE TABLE `biz_scheduled_config` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `schedule_code` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '事务Code',
  `scheduled_name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '事务名称',
  `check_interval` int(10) DEFAULT NULL COMMENT '检查间隔',
  `biz_id` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '业务线编码',
  `remark` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `creator` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `CODE` (`schedule_code`) USING BTREE COMMENT '事务编码不能唯一'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统业务定时事务配置(单体多机部署环境)';

-- ----------------------------
-- Records of biz_scheduled_config
-- ----------------------------
BEGIN;
INSERT INTO `biz_scheduled_config` VALUES (1, 'TEST', 'DEFAULT_SCHEDULE', 100, 'TEST', '测试', 'admin', '2022-02-09 10:16:15');
COMMIT;

-- ----------------------------
-- Table structure for device_sensor_equipment
-- ----------------------------
DROP TABLE IF EXISTS `device_sensor_equipment`;
CREATE TABLE `device_sensor_equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `sensor_no` int(11) DEFAULT NULL COMMENT '传感器编号',
  `model_id` int(11) DEFAULT NULL COMMENT '模型编号',
  `project_id` int(11) DEFAULT NULL,
  `manager_id` int(11) DEFAULT NULL COMMENT '管理员编号',
  `member_group_id` int(11) DEFAULT NULL COMMENT '当前分组编号',
  `position_id` int(11) DEFAULT NULL COMMENT '测点编号',
  `longitude` double(30,10) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(30) DEFAULT NULL COMMENT '维度',
  `electricity` float DEFAULT NULL COMMENT '电量',
  `first_online_time` datetime DEFAULT NULL COMMENT '初次提交时间',
  `last_send_size` int(11) DEFAULT NULL COMMENT '上一次提交数据条数',
  `total_send_size` int(11) DEFAULT NULL COMMENT '总提交量',
  `last_send_count` varchar(255) DEFAULT NULL COMMENT '上一次提交总量',
  `last_send_address` varchar(255) DEFAULT NULL COMMENT '上一次提交地址',
  `last_send_time` datetime DEFAULT NULL COMMENT '上次提交时间',
  `total_size` int(11) DEFAULT NULL COMMENT '数据总量',
  `temperature` varchar(255) DEFAULT NULL COMMENT '温度',
  `phone_number` varchar(255) DEFAULT NULL COMMENT '手机号',
  `create_user` varchar(255) DEFAULT NULL COMMENT '创建者',
  `assign_time` datetime DEFAULT NULL COMMENT '布置时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`sn`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='传感器';

-- ----------------------------
-- Records of device_sensor_equipment
-- ----------------------------
BEGIN;
INSERT INTO `device_sensor_equipment` VALUES (14, '100000005', '倾角传感器1', 10010, 1, 21, 1, 1, 16, 120.2884098730, '31.513274820947032', 1, '2021-09-08 10:26:04', 5, 778588, '40', '39.144.7.127', '2021-12-14 09:54:26', 617935, '8.746', '1440018057609\r', NULL, NULL);
INSERT INTO `device_sensor_equipment` VALUES (15, '100000003', '倾角传感器2', NULL, 1, 22, 1, 1, 17, 120.2879807196, '31.5145919130638', 0, '2021-09-09 05:07:42', 1, 0, '0', '89.248.165.181', '2022-01-13 06:53:03', NULL, '0.0', NULL, NULL, NULL);
INSERT INTO `device_sensor_equipment` VALUES (16, '100000067', '倾角传感器3', NULL, 1, 23, 1, 1, 18, 120.2868649207, '31.51550654944506', 0, '2021-09-12 07:16:44', 1, 0, '0', '101.133.138.230', '2021-10-14 01:41:18', NULL, '0.0', NULL, NULL, NULL);
INSERT INTO `device_sensor_equipment` VALUES (17, '100000071', '倾角传感器4', NULL, 1, 22, 1, 1, 28, 120.2857920370, '31.516238252104156', 0, '2021-09-12 07:14:04', 1, 112, '0', '172.104.140.107', '2022-01-11 10:26:20', 112, '-199.99', NULL, NULL, NULL);
INSERT INTO `device_sensor_equipment` VALUES (24, '100000008', '无锡测试', NULL, 1, 26, 1, 1, 33, 1234.0000000000, '123.0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '188066', NULL, NULL);
INSERT INTO `device_sensor_equipment` VALUES (27, '999999999', '成都测试用传感器', NULL, 1, 27, 1, 1, 36, 104.3110000000, '30.357', 1, '2021-12-07 03:31:40', 5, 119816, '20', '221.178.126.92', '2022-01-06 01:03:16', 117904, '10.0159', '1440018057609\r', NULL, NULL);
INSERT INTO `device_sensor_equipment` VALUES (29, '100000888', '广告牌试用', NULL, 1, 29, 19, 13, 45, 0.0000000000, '0.0', 1, '2021-12-14 10:04:20', 4, 32, '32', '39.144.6.137', '2021-12-14 10:04:20', 32, '12.8201', '1440018057609\r', NULL, NULL);
INSERT INTO `device_sensor_equipment` VALUES (30, '100000015', '测试', NULL, 1, 29, 19, 13, 46, 0.0000000000, '0.0', 1, '2021-12-14 10:10:50', 4, 400680, '32', '39.144.8.80', '2022-01-07 03:33:29', 363432, '15.5185', '1440018057609\r', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for monitor_items
-- ----------------------------
DROP TABLE IF EXISTS `monitor_items`;
CREATE TABLE `monitor_items` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_name` varchar(255) NOT NULL COMMENT '检测项名称',
  `data_id` varchar(255) NOT NULL COMMENT '名称',
  `data_type` varchar(255) DEFAULT NULL COMMENT '数据类型',
  `decimal_size` int(15) DEFAULT NULL COMMENT '小数位',
  `unit` varchar(255) DEFAULT NULL COMMENT '单位',
  `ascii_index` int(11) DEFAULT NULL COMMENT 'ASCII 解析标位',
  `hex_index` int(11) DEFAULT NULL COMMENT 'HEX 解析标位',
  `ascii_prefix` varchar(255) DEFAULT NULL COMMENT 'ASCII 解析前缀',
  `reduce_number` int(11) DEFAULT NULL COMMENT 'HEX减 偏移量',
  `divide_number` int(11) DEFAULT NULL COMMENT 'HEX除 偏移量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_DATA_ID` (`data_id`) USING BTREE COMMENT '唯一时序数据库标识'
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='监控条目';

-- ----------------------------
-- Records of monitor_items
-- ----------------------------
BEGIN;
INSERT INTO `monitor_items` VALUES (1, 'X轴倾角', 'x_ang', 'float', 4, '°', 1, 2, '1', 0, 0);
INSERT INTO `monitor_items` VALUES (2, 'Y轴倾角', 'y_ang', 'float', 4, '°', 1, 2, '1', 0, 0);
INSERT INTO `monitor_items` VALUES (3, 'Z轴倾角', 'z_ang', 'float', 4, '°', 1, 2, '1', 0, 0);
INSERT INTO `monitor_items` VALUES (4, 'X轴加速度', 'x_acc', 'float', 4, 'g', 1, 2, '1', 0, 0);
INSERT INTO `monitor_items` VALUES (5, 'Y轴加速度', 'y_acc', 'float', 4, 'g', 1, 2, '1', 0, 0);
INSERT INTO `monitor_items` VALUES (6, 'Z轴加速度', 'z_acc', 'float', 4, 'g', 1, 2, '1', 0, 0);
INSERT INTO `monitor_items` VALUES (7, '电量', 'elect', 'float', 4, NULL, 1, 2, '1', 0, 0);
INSERT INTO `monitor_items` VALUES (8, '温度', 'temp', 'float', 4, '°C ', 1, 2, '1', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for monitor_position
-- ----------------------------
DROP TABLE IF EXISTS `monitor_position`;
CREATE TABLE `monitor_position` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `structure_id` int(11) NOT NULL COMMENT '结构物编号',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `picture` varchar(255) DEFAULT NULL COMMENT '对应图片',
  `sensor_sn` varchar(255) DEFAULT NULL COMMENT '传感器Sn码',
  `comment` varchar(255) DEFAULT NULL COMMENT '说明',
  `order_sort` int(11) DEFAULT NULL COMMENT '排序',
  `effective` int(11) DEFAULT NULL COMMENT '有效性',
  `binding_status` int(11) DEFAULT NULL COMMENT '绑定状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COMMENT='测点';

-- ----------------------------
-- Records of monitor_position
-- ----------------------------
BEGIN;
INSERT INTO `monitor_position` VALUES (16, 23, '桥头', NULL, NULL, '桥头测点', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (17, 24, '桥头', NULL, NULL, '桥头测点', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (18, 25, '桥头', NULL, NULL, '桥头测点', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (19, 26, '桥头', NULL, NULL, '桥头测点', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (23, 28, '头部 位置', NULL, NULL, '风车头部', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (24, 28, '中部 位置', NULL, NULL, '风车中部', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (25, 28, '尾部 位置', NULL, NULL, '风车尾部', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (28, 24, '桥尾', NULL, NULL, '桥尾', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (29, 23, '桥尾', NULL, NULL, '桥尾', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (31, 24, '桥底', NULL, NULL, '桥底', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (32, 23, '桥底', NULL, NULL, '桥底', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (33, 29, '桥头', NULL, NULL, '桥头测点', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (34, 29, '桥尾', NULL, NULL, '桥尾', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (35, 29, '桥底', NULL, NULL, '桥底', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (36, 30, '客厅', NULL, NULL, '客厅测点', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (37, 30, '卧室', NULL, NULL, '卧室测点', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (38, 30, '厨房', NULL, NULL, '厨房测点', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (39, 23, '桥墩', NULL, NULL, '测试', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (40, 29, '桥墩', NULL, NULL, '测试', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (41, 24, '桥墩', NULL, NULL, '测试', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (45, 32, '客厅', NULL, NULL, '客厅测点', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (46, 32, '卧室', NULL, NULL, '卧室测点', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (47, 32, '厨房', NULL, NULL, '厨房测点', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (48, 33, '客厅', NULL, NULL, '客厅测点', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (49, 33, '卧室', NULL, NULL, '卧室测点', 0, 1, NULL);
INSERT INTO `monitor_position` VALUES (50, 33, '厨房', NULL, NULL, '厨房测点', 0, 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for monitor_position_model
-- ----------------------------
DROP TABLE IF EXISTS `monitor_position_model`;
CREATE TABLE `monitor_position_model` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `structure_code` varchar(255) NOT NULL COMMENT '结构物标识编号',
  `model_id` int(11) DEFAULT NULL COMMENT '结构物模型编号',
  `name` varchar(255) NOT NULL COMMENT '位置名称',
  `comment` varchar(255) DEFAULT NULL COMMENT '位置描述',
  `version` double(20,2) DEFAULT NULL COMMENT '版本',
  `structure_version` double(20,2) DEFAULT NULL,
  `order_sort` varchar(255) DEFAULT NULL COMMENT '排序',
  `effective` int(11) DEFAULT NULL COMMENT '有效性',
  `creator` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COMMENT='测点模板';

-- ----------------------------
-- Records of monitor_position_model
-- ----------------------------
BEGIN;
INSERT INTO `monitor_position_model` VALUES (38, 'FD5S8PPe', 40, '桥头', '桥头测点', 0.01, 0.01, '0', 1, 'admin', '2021-08-23 15:02:54');
INSERT INTO `monitor_position_model` VALUES (50, 'FD5S8PPe', 46, '桥头', '桥头测点', 0.02, 0.02, '0', 1, 'admin', '2021-09-24 15:25:48');
INSERT INTO `monitor_position_model` VALUES (51, 'FD5S8PPe', 46, '桥中梁', '桥中梁', 0.01, 0.02, '0', 1, 'admin', '2021-09-24 15:25:48');
INSERT INTO `monitor_position_model` VALUES (52, 'FD5S8PPe', 47, '桥头', '桥头测点', 0.03, 0.03, '0', 1, 'admin', '2021-09-24 15:29:24');
INSERT INTO `monitor_position_model` VALUES (53, 'FD5S8PPe', 48, '桥头', '桥头测点', 0.04, 0.04, '0', 1, 'admin', '2021-09-24 15:32:01');
INSERT INTO `monitor_position_model` VALUES (54, 'FD5S8PPe', 48, '桥中', '桥中', 0.01, 0.04, '0', 1, 'admin', '2021-09-24 15:32:01');
INSERT INTO `monitor_position_model` VALUES (55, 'FD5S8PPe', 49, '桥头', '桥头测点', 0.05, 0.05, '0', 1, 'admin', '2021-09-24 15:40:08');
INSERT INTO `monitor_position_model` VALUES (56, 'FD5S8PPe', 49, '桥中', '桥中', 0.02, 0.05, '0', 1, 'admin', '2021-09-24 15:40:08');
INSERT INTO `monitor_position_model` VALUES (57, 'FD5S8PPe', 49, '桥中', '桥中', 0.01, 0.05, '0', 1, 'admin', '2021-09-24 15:40:08');
INSERT INTO `monitor_position_model` VALUES (58, 'FD5S8PPe', 50, '桥头', '桥头测点', 0.06, 0.06, '0', 1, 'admin', '2021-09-24 15:40:32');
INSERT INTO `monitor_position_model` VALUES (59, 'FD5S8PPe', 50, '桥尾', '桥尾', 0.01, 0.06, '0', 1, 'admin', '2021-09-24 15:40:32');
INSERT INTO `monitor_position_model` VALUES (60, 'FD5S8PPe', 51, '桥头', '桥头测点', 0.07, 0.07, '0', 1, 'admin', '2021-09-26 05:10:06');
INSERT INTO `monitor_position_model` VALUES (61, 'FD5S8PPe', 51, '桥尾', '桥尾', 0.02, 0.07, '0', 1, 'admin', '2021-09-26 05:10:06');
INSERT INTO `monitor_position_model` VALUES (62, 'FD5S8PPe', 51, '桥墩', '桥墩', 0.01, 0.07, '0', 1, 'admin', '2021-09-26 05:10:06');
INSERT INTO `monitor_position_model` VALUES (63, 'FD5S8PPe', 52, '桥头', '桥头测点', 0.08, 0.08, '0', 1, 'admin', '2021-09-26 12:11:00');
INSERT INTO `monitor_position_model` VALUES (64, 'FD5S8PPe', 52, '桥尾', '桥尾', 0.03, 0.08, '0', 1, 'admin', '2021-09-26 12:11:00');
INSERT INTO `monitor_position_model` VALUES (65, 'FD5S8PPe', 53, '桥头', '桥头测点', 0.09, 0.09, '0', 1, 'admin', '2021-09-26 12:46:14');
INSERT INTO `monitor_position_model` VALUES (66, 'FD5S8PPe', 53, '桥尾', '桥尾', 0.04, 0.09, '0', 1, 'admin', '2021-09-26 12:46:14');
INSERT INTO `monitor_position_model` VALUES (67, 'FD5S8PPe', 53, '桥底', '桥底', 0.01, 0.09, '0', 1, 'admin', '2021-09-26 12:46:14');
INSERT INTO `monitor_position_model` VALUES (76, 'PTA7cX3M', 57, '大门', '大门测点', 0.01, 0.01, '0', 1, 'admin', '2021-09-30 00:55:26');
INSERT INTO `monitor_position_model` VALUES (77, 'PTA7cX3M', 57, '客厅', '客厅测点', 0.01, 0.01, '0', 1, 'admin', '2021-09-30 00:55:26');
INSERT INTO `monitor_position_model` VALUES (78, 'PTA7cX3M', 57, '卧室', '卧室测点', 0.01, 0.01, '0', 1, 'admin', '2021-09-30 00:55:26');
INSERT INTO `monitor_position_model` VALUES (79, 'PTA7cX3M', 57, '厨房', '厨房测点', 0.01, 0.01, '0', 1, 'admin', '2021-09-30 00:55:26');
INSERT INTO `monitor_position_model` VALUES (80, 'PTA7cX3M', 58, '客厅', '客厅测点', 0.02, 0.02, '0', 1, 'admin', '2021-10-27 07:01:25');
INSERT INTO `monitor_position_model` VALUES (81, 'PTA7cX3M', 58, '卧室', '卧室测点', 0.02, 0.02, '0', 1, 'admin', '2021-10-27 07:01:25');
INSERT INTO `monitor_position_model` VALUES (82, 'PTA7cX3M', 58, '厨房', '厨房测点', 0.02, 0.02, '0', 1, 'admin', '2021-10-27 07:01:25');
INSERT INTO `monitor_position_model` VALUES (83, '8rh9DHog', 59, 'eee', 'eeee', 0.01, 0.01, '0', 1, 'admin', '2021-11-02 07:06:44');
INSERT INTO `monitor_position_model` VALUES (84, '8rh9DHog', 59, 'ffff', 'ffff', 0.01, 0.01, '0', 1, 'admin', '2021-11-02 07:06:44');
INSERT INTO `monitor_position_model` VALUES (85, 'FD5S8PPe', 60, '桥头', '桥头测点', 0.10, 0.10, '0', 1, 'admin', '2021-11-14 05:36:45');
INSERT INTO `monitor_position_model` VALUES (86, 'FD5S8PPe', 60, '桥尾', '桥尾', 0.05, 0.10, '0', 1, 'admin', '2021-11-14 05:36:45');
INSERT INTO `monitor_position_model` VALUES (87, 'FD5S8PPe', 60, '桥底', '桥底', 0.02, 0.10, '0', 1, 'admin', '2021-11-14 05:36:45');
INSERT INTO `monitor_position_model` VALUES (88, 'FD5S8PPe', 60, '桥墩', '测试', 0.01, 0.10, '0', 1, 'admin', '2021-11-14 05:36:45');
COMMIT;

-- ----------------------------
-- Table structure for monitor_project
-- ----------------------------
DROP TABLE IF EXISTS `monitor_project`;
CREATE TABLE `monitor_project` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `picture` varchar(255) DEFAULT NULL COMMENT '照片路劲',
  `owner_id` varchar(255) DEFAULT NULL COMMENT '所属编号',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COMMENT='监测项目';

-- ----------------------------
-- Records of monitor_project
-- ----------------------------
BEGIN;
INSERT INTO `monitor_project` VALUES (21, '无锡1', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629731492490_70f55386', '1', 'admin', '2021-08-23 15:11:34', NULL);
INSERT INTO `monitor_project` VALUES (22, '无锡2', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629766111027_a7823d00', '6', 'tyler', '2021-08-24 00:48:36', NULL);
INSERT INTO `monitor_project` VALUES (23, 'test1', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629971611694_d7f6d3b9', '7', 'bwadmin', '2021-08-26 09:53:35', NULL);
INSERT INTO `monitor_project` VALUES (24, 'test2', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629971803268_9c0502bf', '7', 'bwadmin', '2021-08-26 09:56:46', NULL);
INSERT INTO `monitor_project` VALUES (26, '无锡测试', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1634542030191_1ead3c2b', '1', 'admin', '2021-10-18 07:27:12', NULL);
INSERT INTO `monitor_project` VALUES (27, '111', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1635836657971_66c7f45d', '1', 'admin', '2021-11-02 07:04:22', NULL);
INSERT INTO `monitor_project` VALUES (29, '测试', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1639477384948_96984e5b', '19', 'Client123', '2021-12-14 10:23:13', NULL);
COMMIT;

-- ----------------------------
-- Table structure for monitor_prototype
-- ----------------------------
DROP TABLE IF EXISTS `monitor_prototype`;
CREATE TABLE `monitor_prototype` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `updater` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_effective` int(11) DEFAULT NULL,
  `order_sort` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='监测类型\n';

-- ----------------------------
-- Records of monitor_prototype
-- ----------------------------
BEGIN;
INSERT INTO `monitor_prototype` VALUES (1, '倾角', 'admin', '2021-08-14 08:19:27', 'admin', '2021-08-14 08:26:01', 1, '0');
INSERT INTO `monitor_prototype` VALUES (2, '加速度', 'admin', '2021-09-03 15:31:36', 'admin', '2021-09-03 15:31:41', 1, '0');
INSERT INTO `monitor_prototype` VALUES (3, '基础信息', 'admin', '2021-09-03 15:40:07', 'admin', '2021-09-03 15:40:10', 1, '0');
COMMIT;

-- ----------------------------
-- Table structure for monitor_structure
-- ----------------------------
DROP TABLE IF EXISTS `monitor_structure`;
CREATE TABLE `monitor_structure` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `model_id` int(11) DEFAULT NULL COMMENT '模型编号',
  `project_id` int(11) DEFAULT NULL COMMENT '项目编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `structure_name` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL COMMENT '描述',
  `current_version` double(20,2) DEFAULT NULL COMMENT '当前版本',
  `order_sort` varchar(255) DEFAULT NULL COMMENT '排序',
  `effective` varchar(255) DEFAULT NULL COMMENT '有效',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) DEFAULT NULL COMMENT '修改',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COMMENT='结构物实体';

-- ----------------------------
-- Records of monitor_structure
-- ----------------------------
BEGIN;
INSERT INTO `monitor_structure` VALUES (23, 60, 21, '桥梁', '桥梁', '桥梁结构物', 0.10, '0', '1', 'admin', NULL, NULL, NULL);
INSERT INTO `monitor_structure` VALUES (24, 60, 22, '桥梁', '桥梁', '桥梁结构物', 0.10, '0', '1', 'tyler', NULL, NULL, NULL);
INSERT INTO `monitor_structure` VALUES (25, 40, 23, '桥梁', '桥梁', '桥梁结构物', 0.01, '0', '1', 'bwadmin', NULL, NULL, NULL);
INSERT INTO `monitor_structure` VALUES (26, 40, 24, '桥梁', '桥梁', '桥梁结构物', 0.01, '0', '1', 'bwadmin', NULL, NULL, NULL);
INSERT INTO `monitor_structure` VALUES (29, 60, 26, '桥梁', '桥梁', '桥梁结构物', 0.10, '0', '1', 'admin', NULL, NULL, NULL);
INSERT INTO `monitor_structure` VALUES (30, 58, 27, '居民屋', '居民屋', '房屋', 0.02, '0', '1', 'admin', NULL, NULL, NULL);
INSERT INTO `monitor_structure` VALUES (32, 58, 29, '居民屋', '居民屋', '房屋', 0.02, '0', '1', 'Client123', NULL, NULL, NULL);
INSERT INTO `monitor_structure` VALUES (33, 58, 29, '居民屋', '居民屋', '房屋', 0.02, '0', '1', 'Client123', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for monitor_structure_model
-- ----------------------------
DROP TABLE IF EXISTS `monitor_structure_model`;
CREATE TABLE `monitor_structure_model` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `picture` varchar(300) DEFAULT NULL COMMENT '结构物模板图片',
  `structure_code` varchar(255) DEFAULT NULL COMMENT '结构体表示编码',
  `comment` varchar(255) DEFAULT NULL COMMENT '描述',
  `is_public` int(11) DEFAULT NULL COMMENT '是否为公共结构物',
  `is_contain_mobile` int(11) DEFAULT NULL COMMENT '是否包含手机号',
  `is_contain_position` int(11) DEFAULT NULL COMMENT '是否包含经纬度',
  `version` double(20,2) DEFAULT NULL COMMENT '版本',
  `order_sort` int(11) DEFAULT NULL COMMENT '排序',
  `effective` varchar(255) DEFAULT NULL COMMENT '有效性',
  `group_id` int(11) DEFAULT NULL COMMENT '小组编号',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `editor` varchar(255) DEFAULT NULL COMMENT '修改者',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COMMENT='结构体模板';

-- ----------------------------
-- Records of monitor_structure_model
-- ----------------------------
BEGIN;
INSERT INTO `monitor_structure_model` VALUES (40, '桥梁', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629730961454_c1438d99', 'FD5S8PPe', '桥梁结构物', 0, 1, 1, 0.01, 0, '1', 1, 'admin', '2021-08-23 15:02:53', NULL, NULL);
INSERT INTO `monitor_structure_model` VALUES (46, '桥梁', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629730961454_c1438d99', 'FD5S8PPe', '桥梁结构物', 0, 1, 0, 0.02, 1, '1', 1, 'admin', '2021-09-24 15:25:48', NULL, NULL);
INSERT INTO `monitor_structure_model` VALUES (47, '桥梁', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629730961454_c1438d99', 'FD5S8PPe', '桥梁结构物', 0, 1, 0, 0.03, 0, '1', 1, 'admin', '2021-09-24 15:29:24', NULL, NULL);
INSERT INTO `monitor_structure_model` VALUES (48, '桥梁', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629730961454_c1438d99', 'FD5S8PPe', '桥梁结构物', 0, 1, 0, 0.04, 0, '1', 1, 'admin', '2021-09-24 15:32:01', NULL, NULL);
INSERT INTO `monitor_structure_model` VALUES (49, '桥梁', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629730961454_c1438d99', 'FD5S8PPe', '桥梁结构物', 0, 1, 0, 0.05, 0, '1', 1, 'admin', '2021-09-24 15:40:08', NULL, NULL);
INSERT INTO `monitor_structure_model` VALUES (50, '桥梁', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629730961454_c1438d99', 'FD5S8PPe', '桥梁结构物', 0, 1, 0, 0.06, 0, '1', 1, 'admin', '2021-09-24 15:40:32', NULL, NULL);
INSERT INTO `monitor_structure_model` VALUES (51, '桥梁', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629730961454_c1438d99', 'FD5S8PPe', '桥梁结构物', 0, 1, 0, 0.07, 0, '1', 1, 'admin', '2021-09-26 05:10:05', NULL, NULL);
INSERT INTO `monitor_structure_model` VALUES (52, '桥梁', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629730961454_c1438d99', 'FD5S8PPe', '桥梁结构物', 0, 1, 0, 0.08, 0, '1', 1, 'admin', '2021-09-26 12:11:00', NULL, NULL);
INSERT INTO `monitor_structure_model` VALUES (53, '桥梁', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629730961454_c1438d99', 'FD5S8PPe', '桥梁结构物', 0, 1, 0, 0.09, 0, '1', 1, 'admin', '2021-09-26 12:46:14', NULL, NULL);
INSERT INTO `monitor_structure_model` VALUES (57, '居民屋', 'http://www.bwsensing.com.cn/upload/Images/201807/201807111623548.png', 'PTA7cX3M', '房屋', 1, 1, 1, 0.01, 0, '1', 1, 'admin', '2021-09-30 00:55:26', NULL, NULL);
INSERT INTO `monitor_structure_model` VALUES (58, '居民屋', 'http://www.bwsensing.com.cn/upload/Images/201807/201807111623548.png', 'PTA7cX3M', '房屋', 1, 1, 0, 0.02, 1, '1', 1, 'admin', '2021-10-27 07:01:25', NULL, NULL);
INSERT INTO `monitor_structure_model` VALUES (59, 'tres', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1635836790359_87b7eaef', '8rh9DHog', '1111', 0, 1, 1, 0.01, 0, '1', 1, 'admin', '2021-11-02 07:06:44', NULL, NULL);
INSERT INTO `monitor_structure_model` VALUES (60, '桥梁', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629730961454_c1438d99', 'FD5S8PPe', '桥梁结构物', 0, 1, 0, 0.10, 0, '1', 1, 'admin', '2021-11-14 05:36:44', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for operate_group
-- ----------------------------
DROP TABLE IF EXISTS `operate_group`;
CREATE TABLE `operate_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_name` varchar(255) DEFAULT NULL COMMENT '组名',
  `group_code` varchar(255) DEFAULT NULL COMMENT '小组编码',
  `person_number` int(11) DEFAULT NULL COMMENT '人员数量',
  `is_inner` int(11) DEFAULT NULL COMMENT '是否为内部组织',
  `company_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `is_enabled` int(11) DEFAULT NULL COMMENT '是否允许',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='企业组';

-- ----------------------------
-- Records of operate_group
-- ----------------------------
BEGIN;
INSERT INTO `operate_group` VALUES (1, '北微核心组', 'BW_MAIN', 4, 1, '核心组', 1, '2021-08-07 18:24:50');
INSERT INTO `operate_group` VALUES (7, '成都北微工作组', 'BW_CHENGDU', 1, 0, '北微', 0, '2021-08-23 14:14:26');
INSERT INTO `operate_group` VALUES (8, '测试', 'BW_TEST', 0, 0, '测试', 0, '2021-08-26 08:38:58');
INSERT INTO `operate_group` VALUES (9, '3', '3', 0, 0, '3', 0, '2021-11-02 15:08:14');
INSERT INTO `operate_group` VALUES (10, '测试', '12356', 0, 0, '测试', 0, '2021-12-06 16:38:52');
INSERT INTO `operate_group` VALUES (11, 'test', ' 23213', 0, 0, 'r w s r', 0, '2021-12-06 16:49:26');
INSERT INTO `operate_group` VALUES (12, '测试组', '1000002', 0, 0, '平台测试', 1, '2021-12-10 16:53:01');
INSERT INTO `operate_group` VALUES (13, '广告牌监测', '产品测试', 0, 0, '测试', 1, '2021-12-14 15:00:59');
INSERT INTO `operate_group` VALUES (14, '培训熟悉', '999', 0, 1, 'BSL', 0, '2021-12-14 15:53:18');
COMMIT;

-- ----------------------------
-- Table structure for project_member
-- ----------------------------
DROP TABLE IF EXISTS `project_member`;
CREATE TABLE `project_member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id` int(11) DEFAULT NULL COMMENT '项目编号',
  `role_code` varchar(255) DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `account_name` varchar(255) DEFAULT NULL COMMENT '账户名称',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `join_time` datetime DEFAULT NULL COMMENT '加入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COMMENT='项目成员';

-- ----------------------------
-- Records of project_member
-- ----------------------------
BEGIN;
INSERT INTO `project_member` VALUES (14, 20, 'PROJECT_OWNER', '项目拥有者', 'admin', 1, '2021-08-23 15:04:21');
INSERT INTO `project_member` VALUES (15, 21, 'PROJECT_OWNER', '项目拥有者', 'admin', 1, '2021-08-23 15:11:34');
INSERT INTO `project_member` VALUES (16, 21, 'PROJECT_MANAGER', '管理员', 'tyler', 6, '2021-08-23 15:12:06');
INSERT INTO `project_member` VALUES (19, 22, 'PROJECT_OWNER', '项目拥有者', 'tyler', 6, '2021-08-24 00:48:36');
INSERT INTO `project_member` VALUES (20, 22, 'PROJECT_MANAGER', '管理员', 'admin', 1, '2021-08-24 00:54:17');
INSERT INTO `project_member` VALUES (22, 21, 'PROJECT_MANAGER', '管理员', 'bwadmin', 7, '2021-08-26 09:38:47');
INSERT INTO `project_member` VALUES (23, 22, 'PROJECT_MANAGER', '管理员', 'bwadmin', 7, '2021-08-26 09:40:12');
INSERT INTO `project_member` VALUES (24, 23, 'PROJECT_OWNER', '项目拥有者', 'bwadmin', 7, '2021-08-26 09:53:35');
INSERT INTO `project_member` VALUES (25, 24, 'PROJECT_OWNER', '项目拥有者', 'bwadmin', 7, '2021-08-26 09:56:46');
INSERT INTO `project_member` VALUES (27, 26, 'PROJECT_OWNER', '项目拥有者', 'admin', 1, '2021-10-18 07:27:12');
INSERT INTO `project_member` VALUES (28, 27, 'PROJECT_OWNER', '项目拥有者', 'admin', 1, '2021-11-02 07:04:22');
INSERT INTO `project_member` VALUES (29, 27, 'PROJECT_INVOLVED', '项目参与者', 'client', 18, '2021-12-14 03:37:05');
INSERT INTO `project_member` VALUES (31, 29, 'PROJECT_OWNER', '项目拥有者', 'Client123', 19, '2021-12-14 10:23:13');
COMMIT;

-- ----------------------------
-- Table structure for project_role
-- ----------------------------
DROP TABLE IF EXISTS `project_role`;
CREATE TABLE `project_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(255) DEFAULT NULL COMMENT '编号',
  `role_name` varchar(255) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='项目权限';

-- ----------------------------
-- Records of project_role
-- ----------------------------
BEGIN;
INSERT INTO `project_role` VALUES (1, 'PROJECT_OWNER', '项目拥有者');
INSERT INTO `project_role` VALUES (2, 'PROJECT_MANAGER', '管理员');
INSERT INTO `project_role` VALUES (3, 'PROJECT_INVOLVED', '项目参与者');
INSERT INTO `project_role` VALUES (4, 'PROJECT_VIEWER', '项目浏览者');
COMMIT;

-- ----------------------------
-- Table structure for proto_type_item
-- ----------------------------
DROP TABLE IF EXISTS `proto_type_item`;
CREATE TABLE `proto_type_item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_id` int(11) DEFAULT NULL COMMENT '监测类型编号',
  `item_id` int(11) DEFAULT NULL COMMENT '监测',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='监测类型与监测项关联';

-- ----------------------------
-- Records of proto_type_item
-- ----------------------------
BEGIN;
INSERT INTO `proto_type_item` VALUES (1, 1, 1);
INSERT INTO `proto_type_item` VALUES (2, 1, 2);
INSERT INTO `proto_type_item` VALUES (3, 1, 3);
INSERT INTO `proto_type_item` VALUES (4, 2, 4);
INSERT INTO `proto_type_item` VALUES (5, 2, 5);
INSERT INTO `proto_type_item` VALUES (6, 2, 6);
INSERT INTO `proto_type_item` VALUES (7, 3, 7);
INSERT INTO `proto_type_item` VALUES (8, 3, 8);
COMMIT;

-- ----------------------------
-- Table structure for schedule_service_release
-- ----------------------------
DROP TABLE IF EXISTS `schedule_service_release`;
CREATE TABLE `schedule_service_release` (
  `schedule_id` int(10) unsigned NOT NULL COMMENT '主键',
  `service_id` int(11) NOT NULL COMMENT '系统编号',
  `weight` double NOT NULL COMMENT '优先执行权级',
  `shift_weight` double unsigned NOT NULL DEFAULT '0' COMMENT '优先级偏移量',
  UNIQUE KEY `UNIQUE_WEIGHT` (`schedule_id`,`weight`) USING BTREE COMMENT '权重不能一致',
  UNIQUE KEY `UNIQUE_SERVICE` (`schedule_id`,`service_id`) USING BTREE COMMENT '服务器不能重复'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='定时任务与服务器关联';

-- ----------------------------
-- Records of schedule_service_release
-- ----------------------------
BEGIN;
INSERT INTO `schedule_service_release` VALUES (1, 1, 0, 0);
INSERT INTO `schedule_service_release` VALUES (1, 2, 4, 0);
COMMIT;

-- ----------------------------
-- Table structure for sensor_model
-- ----------------------------
DROP TABLE IF EXISTS `sensor_model`;
CREATE TABLE `sensor_model` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `model_name` varchar(255) DEFAULT NULL,
  `model_kind` varchar(255) DEFAULT NULL,
  `model_no` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `is_effective` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='传感器模板\n';

-- ----------------------------
-- Records of sensor_model
-- ----------------------------
BEGIN;
INSERT INTO `sensor_model` VALUES (1, '倾角传感器', 'ANGLE_SENSOR', '100001', 'http://www.bwsensing.com.cn/upload/Images/201807/201807111623548.png', '倾角传感器', '2021-08-22 16:21:44', '0.1', 1);
INSERT INTO `sensor_model` VALUES (2, '倾角传感器二代', 'ANGLE_SENSOR', '100002', 'http://www.bwsensing.com.cn/upload/Images/201807/201807111623548.png', '倾角传感器二代', '2021-08-22 16:21:48', '1', 1);
COMMIT;

-- ----------------------------
-- Table structure for sensor_model_type
-- ----------------------------
DROP TABLE IF EXISTS `sensor_model_type`;
CREATE TABLE `sensor_model_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `model_id` int(11) NOT NULL COMMENT '模型编号',
  `type_id` int(11) NOT NULL COMMENT '类型编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COMMENT='传感器 监测类型关联';

-- ----------------------------
-- Records of sensor_model_type
-- ----------------------------
BEGIN;
INSERT INTO `sensor_model_type` VALUES (41, 6, 1);
INSERT INTO `sensor_model_type` VALUES (42, 6, 2);
INSERT INTO `sensor_model_type` VALUES (43, 6, 3);
INSERT INTO `sensor_model_type` VALUES (47, 2, 1);
INSERT INTO `sensor_model_type` VALUES (48, 2, 2);
INSERT INTO `sensor_model_type` VALUES (49, 1, 1);
INSERT INTO `sensor_model_type` VALUES (50, 1, 2);
INSERT INTO `sensor_model_type` VALUES (51, 1, 3);
COMMIT;

-- ----------------------------
-- Table structure for service_deploy_config
-- ----------------------------
DROP TABLE IF EXISTS `service_deploy_config`;
CREATE TABLE `service_deploy_config` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `host_name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '主机名称',
  `weight` double NOT NULL COMMENT '权级',
  `location` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地理区域',
  `configure` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配置',
  `ipv4` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'IP地址',
  `ipv4_inner` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '内部IP地址\n(单地区部署尽量同网段部署，多部署需要避免两地网段一致)',
  `is_healthy` int(2) NOT NULL DEFAULT '1' COMMENT '是否健康',
  `remark` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统部署信息表';

-- ----------------------------
-- Records of service_deploy_config
-- ----------------------------
BEGIN;
INSERT INTO `service_deploy_config` VALUES (1, 'MacOs-Zyj.local', 0, '江苏南京', '8/16', ' 223.65.100.63', '192.168.31.190', 1, '开发环境');
INSERT INTO `service_deploy_config` VALUES (2, 'TEST', 1, '南京', '1', ' 223.65.100.62', '192.168.31.191', 1, '测试');
COMMIT;

-- ----------------------------
-- Table structure for structure_template_dept
-- ----------------------------
DROP TABLE IF EXISTS `structure_template_dept`;
CREATE TABLE `structure_template_dept` (
  `temp_id` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='组织结构模板与部门管理';

-- ----------------------------
-- Records of structure_template_dept
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for structure_template_field
-- ----------------------------
DROP TABLE IF EXISTS `structure_template_field`;
CREATE TABLE `structure_template_field` (
  `temp_id` int(11) DEFAULT NULL,
  `field_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='组织结构与行业领域关联';

-- ----------------------------
-- Records of structure_template_field
-- ----------------------------
BEGIN;
INSERT INTO `structure_template_field` VALUES (7, 1);
COMMIT;

-- ----------------------------
-- Table structure for support_mail_config
-- ----------------------------
DROP TABLE IF EXISTS `support_mail_config`;
CREATE TABLE `support_mail_config` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `config_name` varchar(255) DEFAULT NULL COMMENT '配置名称',
  `account_name` varchar(255) DEFAULT NULL COMMENT '控制台创建的发信地址',
  `from_alias` varchar(255) DEFAULT NULL COMMENT '发信人昵称',
  `reply_to_address` varchar(255) DEFAULT NULL COMMENT '目标地址',
  `tag_name` varchar(255) DEFAULT NULL COMMENT '控制台创建的标签',
  `template_name` varchar(255) DEFAULT NULL COMMENT '模板名称',
  `subscribe` varchar(255) DEFAULT NULL COMMENT '邮件主题',
  `type` varchar(255) DEFAULT NULL COMMENT '邮件类型',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '修改人',
  `update_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '修改者',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='系统邮件推送设置';

-- ----------------------------
-- Records of support_mail_config
-- ----------------------------
BEGIN;
INSERT INTO `support_mail_config` VALUES (1, 'mail_notification', 'notifaction@send.xianyun.site', '北微传感器', NULL, 'Notifaction', 'alert_notifation', '预警消息订阅', '1', 'admin', '2021-09-21 17:45:14', 'admin', '2021-09-21 17:45:17', '预警邮件消息');
COMMIT;

-- ----------------------------
-- Table structure for support_mail_template
-- ----------------------------
DROP TABLE IF EXISTS `support_mail_template`;
CREATE TABLE `support_mail_template` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) DEFAULT NULL COMMENT '抬头',
  `version` double(8,2) DEFAULT NULL COMMENT '版本号',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `model` varchar(255) DEFAULT NULL COMMENT '模板',
  `template_local` varchar(255) DEFAULT NULL COMMENT '模板路径',
  `is_default` int(11) DEFAULT NULL COMMENT '是否为默认',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='邮件模板';

-- ----------------------------
-- Records of support_mail_template
-- ----------------------------
BEGIN;
INSERT INTO `support_mail_template` VALUES (1, '传感器告警推送', 0.02, 'mail_notification', '推送', 'https://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/mail/alertMail.html?versionId=CAEQJBiBgICX46a34BciIGFhNmZiM2JjOTUyNzRiOGQ4Mzg4NDBiMzZiNzhjNmE2', 1, NULL, 'admin', '2021-09-22 08:31:36', 'admin', '2021-09-22 08:31:40');
COMMIT;

-- ----------------------------
-- Table structure for sys_alert_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_alert_group`;
CREATE TABLE `sys_alert_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_name` varchar(255) DEFAULT NULL COMMENT '预警分组名称',
  `operate_group_id` int(11) DEFAULT NULL COMMENT '操作组',
  `current_sensor_id` int(11) DEFAULT NULL COMMENT '当前传感器',
  `template_id` int(11) DEFAULT NULL COMMENT '模板编号',
  `push_type` int(11) DEFAULT NULL COMMENT '推送方式',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='预警分组 用作预警规则的聚合根';

-- ----------------------------
-- Records of sys_alert_group
-- ----------------------------
BEGIN;
INSERT INTO `sys_alert_group` VALUES (15, '温度检测', 1, 23, NULL, 1, 'admin', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_alert_notification
-- ----------------------------
DROP TABLE IF EXISTS `sys_alert_notification`;
CREATE TABLE `sys_alert_notification` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` int(11) DEFAULT NULL COMMENT '小组编号',
  `alert_group_id` int(11) DEFAULT NULL COMMENT '预警分组编号',
  `group_name` varchar(100) DEFAULT NULL COMMENT '小组名称',
  `alert_name` varchar(100) DEFAULT NULL COMMENT '告警名称',
  `role_name` varchar(100) DEFAULT NULL COMMENT '规则名称',
  `sensor_id` int(11) DEFAULT NULL COMMENT '传感器编号',
  `sn` varchar(100) DEFAULT NULL COMMENT '传感器Sn码',
  `sensor_name` varchar(100) DEFAULT NULL COMMENT '传感器名称',
  `sensor_model` varchar(100) DEFAULT NULL COMMENT '传感器型号',
  `model_id` int(11) DEFAULT NULL COMMENT '传感器型号编号',
  `alert_time` varchar(100) NOT NULL DEFAULT '' COMMENT '告警时间',
  `summary` varchar(100) DEFAULT NULL COMMENT '告警信息',
  `color` varchar(100) DEFAULT NULL COMMENT '展示颜色',
  `is_handle` int(11) DEFAULT '0' COMMENT '是否被处理',
  PRIMARY KEY (`id`),
  KEY `alert_time` (`alert_time`,`group_id`,`alert_name`,`role_name`,`sensor_id`,`summary`,`color`) USING BTREE
) /*!50100 STORAGE MEMORY */ ENGINE=InnoDB AUTO_INCREMENT=5107 DEFAULT CHARSET=utf8mb4 COMMENT='预警通知日志';

-- ----------------------------
-- Records of sys_alert_notification
-- ----------------------------
BEGIN;
INSERT INTO `sys_alert_notification` VALUES (5060, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:21.4974 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5061, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:21.4445 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5062, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:20.8624 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5063, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:20.4921 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5064, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:20.3333 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5065, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:20.2275 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5066, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:20.1746 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5067, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:20.0688 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5068, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:20.0159 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5069, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:19.963 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5070, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:19.9101 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5071, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:19.8042 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5072, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:20.0159 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5073, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:20.2804 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5074, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:20.7566 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5075, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:21.0741 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5076, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:21.1799 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5077, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:21.4974 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5078, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:21.7619 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5079, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:21.8677 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5080, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:21.7619 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5081, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:21.6561 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5082, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:21.6561 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5083, 1, 14, '北微核心组', '温度预警1216', 'AUTO_ROLE11216', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-27 13:29:00.0', '传感器:无锡职院传感器910 发生 ${alertName} 当前 ${paramName} 监测数据为:21.6561 型号:倾角传感器', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5084, 1, 15, '北微核心组', '温度检测6888', 'AUTO_ROLE16888', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-30 04:49:00.0', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:21.8148 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5085, 1, 15, '北微核心组', '温度检测6888', 'AUTO_ROLE16888', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-30 04:49:00.0', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:22.0265 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5086, 1, 15, '北微核心组', '温度检测6888', 'AUTO_ROLE16888', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-30 04:49:00.0', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:22.0794 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5087, 1, 15, '北微核心组', '温度检测6888', 'AUTO_ROLE16888', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-30 04:49:00.0', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:22.3968 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5088, 1, 15, '北微核心组', '温度检测6888', 'AUTO_ROLE16888', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-30 04:49:00.0', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:22.3439 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5089, 1, 15, '北微核心组', '温度检测6888', 'AUTO_ROLE16888', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-30 04:49:00.0', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:22.0265 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5090, 1, 15, '北微核心组', '温度检测6888', 'AUTO_ROLE16888', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-30 04:49:00.0', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:22.1323 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5091, 1, 15, '北微核心组', '温度检测6888', 'AUTO_ROLE16888', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-30 04:49:00.0', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:21.8148 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5092, 1, 15, '北微核心组', '温度检测6888', 'AUTO_ROLE16888', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-30 04:49:00.0', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:21.6032 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5093, 1, 15, '北微核心组', '温度检测6888', 'AUTO_ROLE16888', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-30 04:49:00.0', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:21.4974 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5094, 1, 15, '北微核心组', '温度检测6888', 'AUTO_ROLE16888', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-30 04:49:00.0', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:21.4974 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5095, 1, 15, '北微核心组', '温度检测6888', 'AUTO_ROLE16888', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-10-30 04:49:00.0', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:21.4974 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5096, 1, 15, '北微核心组', '温度检测6888', 'AUTO_ROLE16888', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-11-20 11:20:00', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:21.4445 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5097, 1, 18, '北微核心组', '温度6993', 'AUTO_ROLE16993', 14, '100000005', '倾角传感器1', '倾角传感器', 1, '2021-11-20 11:20:00', '倾角传感器1(倾角传感器) 发生 温度 问题 ！ 当前数值:57 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5098, 1, 15, '北微核心组', '温度检测6888', 'AUTO_ROLE16888', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-11-20 11:20:00', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:21.2328 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5099, 1, 15, '北微核心组', '温度检测6888', 'AUTO_ROLE16888', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-11-20 11:20:00', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:21.2328 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5100, 1, 18, '北微核心组', '电量6997', 'AUTO_ROLE16997', 14, '100000005', '倾角传感器1', '倾角传感器', 1, '2021-12-03 05:32:00', '倾角传感器1(倾角传感器) 发生 电量 问题 ！ 当前数值:8 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5101, 1, 18, '北微核心组', '温度6993', 'AUTO_ROLE16993', 14, '100000005', '倾角传感器1', '倾角传感器', 1, '2021-12-03 05:32:00', '倾角传感器1(倾角传感器) 发生 温度 问题 ！ 当前数值:57 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5102, 1, 15, '北微核心组', '温度检测6888', 'AUTO_ROLE16888', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-12-03 05:32:00', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:22.3968 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5103, 1, 15, '北微核心组', '温度检测6888', 'AUTO_ROLE16888', 23, '100000108', '无锡职院传感器910', '倾角传感器', 1, '2021-12-03 05:32:00', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:22.3968 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5104, 1, 18, '北微核心组', '电量6997', 'AUTO_ROLE16997', 14, '100000005', '倾角传感器1', '倾角传感器', 1, '2021-12-03 09:47:00', '倾角传感器1(倾角传感器) 发生 电量 问题 ！ 当前数值:9 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5105, 1, 18, '北微核心组', '温度6993', 'AUTO_ROLE16993', 14, '100000005', '倾角传感器1', '倾角传感器', 1, '2021-12-03 09:47:00', '倾角传感器1(倾角传感器) 发生 温度 问题 ！ 当前数值:23.5609 ', 'red', 0);
INSERT INTO `sys_alert_notification` VALUES (5106, 1, 18, '北微核心组', '温度6993', 'AUTO_ROLE16993', 14, '100000005', '倾角传感器1', '倾角传感器', 1, '2021-12-03 09:47:00', '倾角传感器1(倾角传感器) 发生 温度 问题 ！ 当前数值:23.5609 ', 'red', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_alert_notification_member
-- ----------------------------
DROP TABLE IF EXISTS `sys_alert_notification_member`;
CREATE TABLE `sys_alert_notification_member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `alert_group_id` int(11) DEFAULT NULL COMMENT '预警分组编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COMMENT='消息通知接收';

-- ----------------------------
-- Records of sys_alert_notification_member
-- ----------------------------
BEGIN;
INSERT INTO `sys_alert_notification_member` VALUES (35, 15, 6);
INSERT INTO `sys_alert_notification_member` VALUES (36, 15, 7);
INSERT INTO `sys_alert_notification_member` VALUES (37, 15, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_alert_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_alert_param`;
CREATE TABLE `sys_alert_param` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `template_id` int(11) DEFAULT NULL COMMENT '模板编号',
  `alert_name` varchar(255) DEFAULT NULL COMMENT '告警名称',
  `param_no` int(11) DEFAULT NULL COMMENT '监测参数编号',
  `last_time` varchar(255) DEFAULT NULL COMMENT '问题出现后多久开始预警',
  `period` varchar(255) DEFAULT NULL COMMENT '监测周期',
  `formulas` varchar(255) DEFAULT NULL COMMENT '函数公式',
  `color` varchar(255) DEFAULT NULL COMMENT '颜色',
  `summary` varchar(1000) DEFAULT NULL COMMENT '告警信息预设',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='告警参数\n';

-- ----------------------------
-- Records of sys_alert_param
-- ----------------------------
BEGIN;
INSERT INTO `sys_alert_param` VALUES (4, 2, 'X倾角预警', 1, '0', '1m', 'max,>,80,||#min,<,10', 'red', '传感器:${sensor} 发生 ${alertName} 当前 ${roleName}监测数据为:${dataNumber} 型号:${sensorModel}');
INSERT INTO `sys_alert_param` VALUES (7, 5, 'X倾角预警', 1, '10s', '10m', 'max,>,80,||#min,<,10', 'red', '传感器:${sensor} 发生 ${alertName} 当前 ${roleName} 监测数据为:${dataNumber} 型号:${sensorModel}');
INSERT INTO `sys_alert_param` VALUES (8, 5, 'Y倾角预警', 2, '10s', '10m', 'max,>,80,||#min,<,10', 'red', '传感器:${sensor} 发生 ${alertName} 当前${roleName} 监测数据为:${dataNumber} 型号:${sensorModel}');
INSERT INTO `sys_alert_param` VALUES (9, 6, '测试预警', 1, '10s', '10m', 'max,<,10,||#min,>,1', 'red', '传感器:${sensor} 发生 ${alertName} 当前 ${roleName} 监测数据为:${dataNumber} 型号:${sensorModel}');
INSERT INTO `sys_alert_param` VALUES (10, 7, '倾角过大', 1, '10s', '10m', 'max,<,15,||#min,>,5', 'red', '传感器:${sensor} 发生 ${alertName} 当前 ${roleName} 监测数据为:${dataNumber} 型号:${sensorModel}');
COMMIT;

-- ----------------------------
-- Table structure for sys_alert_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_alert_role`;
CREATE TABLE `sys_alert_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `alert_name` varchar(255) DEFAULT NULL COMMENT '告警名称',
  `name` varchar(255) DEFAULT NULL COMMENT '规则名称',
  `role_name` varchar(255) DEFAULT NULL COMMENT '告警规则名称信息',
  `alert_group_id` int(11) DEFAULT NULL COMMENT '告警分组编号',
  `template_id` int(11) DEFAULT NULL COMMENT '模板编号',
  `color` varchar(255) DEFAULT NULL COMMENT '颜色',
  `param_id` int(11) DEFAULT NULL COMMENT '参数编号',
  `group_id` int(11) DEFAULT NULL COMMENT '小组编号',
  `sensor_id` int(11) DEFAULT NULL COMMENT '传感器编号',
  `formulas` varchar(1000) DEFAULT NULL COMMENT '预警公式',
  `forward` varchar(255) DEFAULT NULL COMMENT '回查时间',
  `label` varchar(255) DEFAULT NULL COMMENT '标签',
  `summary` varchar(255) DEFAULT NULL COMMENT '提示信息',
  `alert_status` int(11) DEFAULT NULL COMMENT '监测状态',
  `alert_info` varchar(255) DEFAULT NULL COMMENT '内涵信息用于解析',
  `state_sql` varchar(1000) DEFAULT NULL COMMENT 'TDengin 查询SQL',
  `template_sql` varchar(1000) DEFAULT NULL COMMENT '未修改回查时间的SQL',
  `expr` varchar(500) DEFAULT NULL COMMENT '计算公式',
  `last_time` varchar(255) DEFAULT NULL COMMENT '问题持续时间',
  `check_period` varchar(255) DEFAULT NULL COMMENT '规则检查周期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_NAME` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COMMENT='告警规则';

-- ----------------------------
-- Records of sys_alert_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_alert_role` VALUES (21, '温度检测6888', 'AUTO_ROLE16888', '温度检测', 15, NULL, 'red', NULL, 1, 23, 'max,<,20,||#min,>,10', '2h', 'AUTO_ROLE', '无锡职院传感器910(倾角传感器) 发生 温度检测6888 问题 ！ 当前数值:{{$values.data_value}} ', 1, '100000108|1|AUTO_ROLE16888|15', 'select  data_value from smart_cloud.sensor_data where ts > now - 2h and data_id =\'temp\' and sn =\'100000108\'', 'select  data_value from smart_cloud.sensor_data where ts > now - [forward] and data_id =\'temp\' and sn =\'100000108\'', 'max(data_value) < 20 || min(data_value) > 10', '0', '1m');
COMMIT;

-- ----------------------------
-- Table structure for sys_alert_template
-- ----------------------------
DROP TABLE IF EXISTS `sys_alert_template`;
CREATE TABLE `sys_alert_template` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `model_no` int(11) DEFAULT NULL COMMENT '模型编号',
  `group_id` int(11) DEFAULT NULL COMMENT '小组编号',
  `template_name` varchar(255) DEFAULT NULL COMMENT '模型名称',
  `name_prefix` varchar(255) DEFAULT NULL COMMENT '告警role前缀名称',
  `summary_model` varchar(1000) DEFAULT NULL COMMENT '告警信息模板',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updater` varchar(255) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='告警模板\n';

-- ----------------------------
-- Records of sys_alert_template
-- ----------------------------
BEGIN;
INSERT INTO `sys_alert_template` VALUES (2, 1, 1, '测试模板', 'test_temp', '传感器:${sensor} 发生 ${alertName} 问题 当前 ${paramName} 监测查询值为:${dataNumber} 传感器型号:${sensorModel}', '2021-09-04 03:53:07', '2021-09-04 07:14:26', 'admin', 'admin');
INSERT INTO `sys_alert_template` VALUES (5, 1, 1, '倾角预警', 'acc_check', '传感器:${sensor} 发生 ${alertName} 当前 ${paramName} 监测数据为:${dataNumber} 型号:${sensorModel}', '2021-09-05 10:26:07', '2021-09-05 10:27:44', 'admin', 'admin');
INSERT INTO `sys_alert_template` VALUES (6, 1, 1, '测试', 'acc_test', '传感器:${sensor} 发生 ${alertName} 当前 ${paramName} 监测数据为:${dataNumber} 型号:${sensorModel}', '2021-09-06 13:40:58', NULL, 'admin', NULL);
INSERT INTO `sys_alert_template` VALUES (7, 1, 1, '倾角过大', 'test_test', '传感器:${sensor} 发生 ${alertName} 当前 ${paramName} 监测数据为:${dataNumber} 型号:${sensorModel}', '2021-09-10 06:39:56', NULL, 'admin', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_client
-- ----------------------------
DROP TABLE IF EXISTS `sys_client`;
CREATE TABLE `sys_client` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `client_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户名称',
  `client_short_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户简称',
  `client_logo` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户品牌标识',
  `country` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '国家地区',
  `client_phone` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户电话',
  `client_fax` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户传真',
  `client_address` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户地址',
  `email` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户邮箱',
  `comes_from` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户来源',
  `level` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '等级',
  `client_type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户类型',
  `client_status` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户状态',
  `client_desc` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '客户描述',
  `creator` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `updater` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `order_number` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `weight` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '权级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='客户信息';

-- ----------------------------
-- Records of sys_client
-- ----------------------------
BEGIN;
INSERT INTO `sys_client` VALUES (16, '南京理工大学', 'NJLG', 'https://bkimg.cdn.bcebos.com/pic/a6efce1b9d16fdfaaf51668f55c79b5494eef01fcd33?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg', NULL, NULL, NULL, NULL, NULL, '合作', NULL, '持久客户', NULL, NULL, '北微-超级管理员', NULL, '2022-01-20 09:28:03', NULL, 0, 16);
INSERT INTO `sys_client` VALUES (18, '南京理工大紫金学院', 'NJLGZJ', 'https://bkimg.cdn.bcebos.com/pic/a6efce1b9d16fdfaaf51668f55c79b5494eef01fcd33?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg', NULL, NULL, NULL, NULL, NULL, '合作', NULL, '持久客户', NULL, NULL, '北微-超级管理员', NULL, '2022-01-20 12:39:58', NULL, 1, 8);
INSERT INTO `sys_client` VALUES (21, '南京理工大泰州', 'NJLGTZ', 'https://bkimg.cdn.bcebos.com/pic/a6efce1b9d16fdfaaf51668f55c79b5494eef01fcd33?x-bce-process=image/resize,m_lfit,w_536,limit_1/format,f_jpg', NULL, NULL, NULL, NULL, NULL, '合作', NULL, '持久客户', NULL, NULL, '北微-超级管理员', NULL, '2022-01-21 07:23:54', NULL, 0, 34);
COMMIT;

-- ----------------------------
-- Table structure for sys_client_field_relate
-- ----------------------------
DROP TABLE IF EXISTS `sys_client_field_relate`;
CREATE TABLE `sys_client_field_relate` (
  `client_id` int(11) DEFAULT NULL COMMENT '客户编号',
  `field_id` int(11) DEFAULT NULL COMMENT '领域编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='客户与行业领域关联';

-- ----------------------------
-- Records of sys_client_field_relate
-- ----------------------------
BEGIN;
INSERT INTO `sys_client_field_relate` VALUES (18, 1);
INSERT INTO `sys_client_field_relate` VALUES (21, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `structure_id` int(11) DEFAULT NULL COMMENT '组织编号',
  `parent_id` int(11) DEFAULT NULL COMMENT '父部门编号',
  `ancestors` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '祖级列表',
  `dept_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部门名称',
  `order_number` int(11) DEFAULT '1' COMMENT '显示顺序',
  `leader` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '负责人',
  `phone` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `status` int(2) DEFAULT '0' COMMENT '部门状态',
  `exclude_id` int(8) DEFAULT NULL COMMENT '排除编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统部门';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (31, 14, NULL, NULL, 'XXX总公司', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (32, 14, 31, NULL, '财务部门', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (33, 14, 31, NULL, '技术部门', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (34, 14, 33, NULL, '开发组', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (35, 14, 33, NULL, '设计组', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (36, 14, 33, NULL, '运维组', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (37, 14, 31, NULL, '人力资源部门', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (38, 14, NULL, NULL, 'XXX分公司', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (39, 14, 38, NULL, '实施部门', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (40, 14, 38, NULL, '市场部门', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES (42, 14, 33, NULL, '管理组', 1, NULL, NULL, NULL, 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dept_template
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_template`;
CREATE TABLE `sys_dept_template` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `structure_id` int(11) DEFAULT NULL COMMENT '组织编号',
  `parent_id` int(11) DEFAULT NULL COMMENT '父部门ID',
  `ancestors` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '祖级列表',
  `dept_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT '1' COMMENT '显示顺序',
  `parent_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '父部门名称',
  `exclude_id` int(11) DEFAULT NULL COMMENT '排除编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='部门模板';

-- ----------------------------
-- Records of sys_dept_template
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept_template` VALUES (1, 7, NULL, NULL, 'XXX总公司', 1, NULL, NULL);
INSERT INTO `sys_dept_template` VALUES (2, 7, NULL, NULL, 'XXX分公司', 1, NULL, NULL);
INSERT INTO `sys_dept_template` VALUES (3, 7, 1, NULL, '财务部门', 1, NULL, NULL);
INSERT INTO `sys_dept_template` VALUES (4, 7, 1, NULL, '技术部门', 1, NULL, NULL);
INSERT INTO `sys_dept_template` VALUES (5, 7, 1, NULL, '人力资源部门', 1, NULL, NULL);
INSERT INTO `sys_dept_template` VALUES (6, 7, 2, NULL, '实施部门', 1, NULL, NULL);
INSERT INTO `sys_dept_template` VALUES (7, 7, 2, NULL, '市场部门', 1, NULL, NULL);
INSERT INTO `sys_dept_template` VALUES (8, 7, 4, NULL, '开发组', 1, NULL, NULL);
INSERT INTO `sys_dept_template` VALUES (9, 7, 4, NULL, '设计组', 1, NULL, NULL);
INSERT INTO `sys_dept_template` VALUES (10, 7, 4, NULL, '运维组', 1, NULL, NULL);
INSERT INTO `sys_dept_template` VALUES (11, 7, 1, NULL, '管理组', 1, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_industry_field
-- ----------------------------
DROP TABLE IF EXISTS `sys_industry_field`;
CREATE TABLE `sys_industry_field` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `code` varchar(255) DEFAULT NULL COMMENT '编码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='行业领域\n';

-- ----------------------------
-- Records of sys_industry_field
-- ----------------------------
BEGIN;
INSERT INTO `sys_industry_field` VALUES (1, '测试', 'TEST', '2022-01-18 19:28:53');
INSERT INTO `sys_industry_field` VALUES (2, '水利', 'WW', '2022-01-19 02:03:29');
COMMIT;

-- ----------------------------
-- Table structure for sys_monitor_receive_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_monitor_receive_log`;
CREATE TABLE `sys_monitor_receive_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sn` varchar(255) DEFAULT NULL COMMENT 'SN编码',
  `channel_id` varchar(255) DEFAULT NULL COMMENT 'CHANEL编号',
  `ip` varchar(255) DEFAULT NULL COMMENT 'IP地址',
  `receive_time` datetime DEFAULT NULL COMMENT '接收时间',
  `receive_size` int(11) DEFAULT NULL COMMENT '接收数据条数',
  `send_count` int(11) DEFAULT NULL COMMENT '发送总量',
  `receive_message` text COMMENT '接收地址',
  `total_size` int(11) DEFAULT NULL COMMENT '保存后总数据量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='传感器接收日志';

-- ----------------------------
-- Records of sys_monitor_receive_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_notification_cache
-- ----------------------------
DROP TABLE IF EXISTS `sys_notification_cache`;
CREATE TABLE `sys_notification_cache` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` int(10) NOT NULL COMMENT '预警分组编号',
  `message` varchar(300) DEFAULT NULL COMMENT '消息',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `reader_id` int(10) DEFAULT NULL COMMENT '处理人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_notification_cache
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `code` varchar(255) DEFAULT NULL COMMENT '权限Code',
  `url` varchar(255) DEFAULT NULL COMMENT '权限URL',
  `comment` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COMMENT='系统权限';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES (26, 'login', 'BASE_API', '/login', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_role`;
CREATE TABLE `sys_permission_role` (
  `permission_id` int(11) NOT NULL COMMENT '权限Id',
  `role_id` int(11) NOT NULL COMMENT '角色Id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='权限与角色关联';

-- ----------------------------
-- Records of sys_permission_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_product_manufacturer
-- ----------------------------
DROP TABLE IF EXISTS `sys_product_manufacturer`;
CREATE TABLE `sys_product_manufacturer` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `facture_code` varchar(255) DEFAULT NULL COMMENT '厂商编码',
  `post_code` varchar(255) DEFAULT NULL COMMENT '邮编',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `connector` varchar(255) DEFAULT NULL COMMENT '联系人',
  `contact_number` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `is_inner` int(11) DEFAULT NULL COMMENT '是否为内部',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='传感器厂商';

-- ----------------------------
-- Records of sys_product_manufacturer
-- ----------------------------
BEGIN;
INSERT INTO `sys_product_manufacturer` VALUES (1, '无锡北微传感科技有限公司', 'D8vigNnU', '214026', '无锡市滨湖区绣溪路58-30', '王春波', '0510-85737158', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 6);
INSERT INTO `sys_role_menu` VALUES (1, 7);
INSERT INTO `sys_role_menu` VALUES (1, 8);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (1, 13);
INSERT INTO `sys_role_menu` VALUES (1, 14);
INSERT INTO `sys_role_menu` VALUES (1, 15);
INSERT INTO `sys_role_menu` VALUES (1, 16);
INSERT INTO `sys_role_menu` VALUES (1, 17);
INSERT INTO `sys_role_menu` VALUES (1, 18);
INSERT INTO `sys_role_menu` VALUES (1, 19);
INSERT INTO `sys_role_menu` VALUES (1, 20);
INSERT INTO `sys_role_menu` VALUES (1, 21);
INSERT INTO `sys_role_menu` VALUES (1, 22);
INSERT INTO `sys_role_menu` VALUES (1, 24);
INSERT INTO `sys_role_menu` VALUES (1, 25);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 6);
INSERT INTO `sys_role_menu` VALUES (2, 7);
INSERT INTO `sys_role_menu` VALUES (2, 8);
INSERT INTO `sys_role_menu` VALUES (2, 9);
INSERT INTO `sys_role_menu` VALUES (2, 10);
INSERT INTO `sys_role_menu` VALUES (2, 11);
INSERT INTO `sys_role_menu` VALUES (2, 12);
INSERT INTO `sys_role_menu` VALUES (2, 13);
INSERT INTO `sys_role_menu` VALUES (2, 14);
INSERT INTO `sys_role_menu` VALUES (2, 15);
INSERT INTO `sys_role_menu` VALUES (2, 19);
INSERT INTO `sys_role_menu` VALUES (2, 20);
INSERT INTO `sys_role_menu` VALUES (3, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 6);
INSERT INTO `sys_role_menu` VALUES (3, 7);
INSERT INTO `sys_role_menu` VALUES (3, 8);
INSERT INTO `sys_role_menu` VALUES (3, 9);
INSERT INTO `sys_role_menu` VALUES (3, 10);
INSERT INTO `sys_role_menu` VALUES (3, 11);
INSERT INTO `sys_role_menu` VALUES (3, 12);
INSERT INTO `sys_role_menu` VALUES (3, 13);
COMMIT;

-- ----------------------------
-- Table structure for sys_structure
-- ----------------------------
DROP TABLE IF EXISTS `sys_structure`;
CREATE TABLE `sys_structure` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '组织名称',
  `owner_id` int(11) DEFAULT NULL COMMENT '所属编号',
  `type_id` int(11) DEFAULT NULL COMMENT '所属类型编号',
  `creator` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统组织';

-- ----------------------------
-- Records of sys_structure
-- ----------------------------
BEGIN;
INSERT INTO `sys_structure` VALUES (14, '南京理工大学的组织', 16, 2, '北微-超级管理员', '2022-01-20 09:28:03', NULL, NULL);
INSERT INTO `sys_structure` VALUES (15, '南京理工大紫金学院的组织', 18, 2, '北微-超级管理员', '2022-01-20 12:39:58', NULL, NULL);
INSERT INTO `sys_structure` VALUES (16, '南京理工大泰州的组织', 21, 2, '北微-超级管理员', '2022-01-21 07:23:54', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_structure_template
-- ----------------------------
DROP TABLE IF EXISTS `sys_structure_template`;
CREATE TABLE `sys_structure_template` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `template_name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '模板名称',
  `creator` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统组织模板';

-- ----------------------------
-- Records of sys_structure_template
-- ----------------------------
BEGIN;
INSERT INTO `sys_structure_template` VALUES (7, '测试行业组织结构', '北微-超级管理员', '2022-01-18 12:56:23', '北微-超级管理员', '2022-01-18 12:57:25');
COMMIT;

-- ----------------------------
-- Table structure for sys_view_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_view_menu`;
CREATE TABLE `sys_view_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `is_view` int(2) DEFAULT NULL COMMENT '是否为前端配置',
  `menu_kind` varchar(255) DEFAULT NULL COMMENT '视图类型',
  `api_path` varchar(255) DEFAULT NULL COMMENT '请求路由路径',
  `role_code` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `path` varchar(255) DEFAULT NULL COMMENT '前端路径',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  `hidden` int(11) DEFAULT NULL COMMENT '是否隐藏',
  `always_show` varchar(255) DEFAULT NULL COMMENT '是否长显示',
  `redirect` varchar(255) DEFAULT NULL COMMENT '定向地址',
  `is_base_node` int(2) DEFAULT '0' COMMENT '是否为根节点',
  `upper_node` int(11) DEFAULT NULL COMMENT '上层节点',
  `order_num` int(2) NOT NULL DEFAULT '0' COMMENT '显示顺序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updater` varchar(255) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COMMENT='系统路由配置';

-- ----------------------------
-- Records of sys_view_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_view_menu` VALUES (1, 'UserInfo', '个人中心', 'peoples', NULL, 'C', NULL, NULL, '/user-info', 'Layout', 1, '0', '/user-info/index', 1, NULL, 1, '2021-10-10 15:37:01', 'admin', '2021-10-10 15:37:08', 'admin');
INSERT INTO `sys_view_menu` VALUES (2, 'UserIndex', '个人信息', 'lock', NULL, 'M', NULL, NULL, 'index', 'user-info/index', 0, '0', NULL, 0, 1, 1, '2021-10-10 15:38:37', 'admin', '2021-10-10 15:38:39', 'admin');
INSERT INTO `sys_view_menu` VALUES (3, 'PersonManager', '账号管理', 'peoples', NULL, 'C', NULL, NULL, '/person-manager', 'Layout', 0, '1', '/person-manager/index', 1, NULL, 1, '2021-10-10 15:44:20', 'admin', '2021-10-10 15:44:22', 'admin');
INSERT INTO `sys_view_menu` VALUES (4, 'CurdManager', '人员账号管理', 'el-icon-user', NULL, 'M', NULL, NULL, 'index', 'person-manager/curd', 0, '0', NULL, 0, 3, 1, '2021-10-10 15:47:32', 'admin', '2021-10-10 15:47:34', 'admin');
INSERT INTO `sys_view_menu` VALUES (5, 'GroupManager', '人员组管理', 'el-icon-s-check', NULL, 'M', NULL, NULL, 'group', 'person-manager/group', 0, '0', NULL, 0, 3, 1, '2021-10-10 15:49:36', 'admin', '2021-10-10 15:49:38', 'admin');
INSERT INTO `sys_view_menu` VALUES (6, 'ProjectManager', '项目管理', 'el-icon-s-management', NULL, 'C', NULL, NULL, '/project-manager', 'Layout', 0, '1', '/project-manager/overview', 1, NULL, 1, '2021-10-10 15:56:54', 'admin', '2021-10-10 15:57:00', 'admin');
INSERT INTO `sys_view_menu` VALUES (7, 'OverView', '项目总览', 'edit', NULL, 'M', NULL, NULL, 'overview', 'base-message/overview', 1, '0', NULL, 0, 6, 1, '2021-10-10 16:01:08', 'admin', '2021-10-10 16:01:12', 'admin');
INSERT INTO `sys_view_menu` VALUES (8, 'BaseMessage', '项目列表', 'el-icon-s-grid', NULL, 'M', NULL, NULL, 'index', 'base-message/index', 0, '0', NULL, 0, 6, 1, '2021-10-10 16:02:35', 'admin', '2021-10-10 16:02:37', 'admin');
INSERT INTO `sys_view_menu` VALUES (9, 'project-detail', '项目详情', NULL, NULL, 'M', NULL, NULL, 'detail', 'base-message/project-detail', 1, '0', NULL, 0, 6, 1, '2021-10-10 16:07:48', 'admin', '2021-10-10 16:07:51', 'admin');
INSERT INTO `sys_view_menu` VALUES (10, 'AddStructure', '新建项目', NULL, NULL, 'M', NULL, NULL, 'add', 'base-message/add-structure', 1, '0', NULL, 0, 6, 1, '2021-10-10 16:34:42', 'admin', '2021-10-10 16:34:46', 'admin');
INSERT INTO `sys_view_menu` VALUES (11, 'editorProject', '修改项目', NULL, NULL, 'M', NULL, NULL, 'editor', 'base-message/editor-structure', 1, '0', NULL, 0, 6, 1, '2021-10-10 16:36:41', 'admin', '2021-10-10 16:36:44', 'admin');
INSERT INTO `sys_view_menu` VALUES (12, 'TimeWatcher', '设备监测', 'el-icon-view', NULL, 'M', NULL, NULL, 'watcher', 'time-watcher/index', 0, '0', NULL, 0, 6, 1, '2021-10-10 16:37:22', 'admin', '2021-10-10 16:37:24', 'admin');
INSERT INTO `sys_view_menu` VALUES (13, 'DataAnalysis', '数据分析', 'el-icon-s-marketing', NULL, 'M', NULL, NULL, 'analysis', 'data-analysis/index', 0, '0', NULL, 0, 6, 1, '2021-10-10 16:38:25', 'admin', '2021-10-10 16:38:27', 'admin');
INSERT INTO `sys_view_menu` VALUES (14, 'WarnList', '预警列表', 'el-icon-message-solid', NULL, 'M', NULL, NULL, 'warn', 'warn/index', 0, '0', NULL, 0, 6, 1, '2021-10-10 16:39:07', 'admin', '2021-10-10 16:39:09', 'admin');
INSERT INTO `sys_view_menu` VALUES (15, 'WarnGroup', '预警组管理', 'el-icon-message-solid', NULL, 'M', NULL, NULL, 'warn-group', 'warn/group', 0, '0', NULL, 0, 6, 1, '2021-10-10 16:39:48', 'admin', '2021-10-10 16:39:50', 'admin');
INSERT INTO `sys_view_menu` VALUES (16, 'StructureManager', '行业模版', 'el-icon-office-building', NULL, 'C', NULL, NULL, '/structure-manager', 'Layout', 0, '1', '/structure-manager/index', 1, NULL, 2, '2021-10-10 16:41:01', 'admin', '2021-10-10 16:42:34', 'dmin');
INSERT INTO `sys_view_menu` VALUES (17, 'CheckStructure', '结构物管理', 'el-icon-s-grid', NULL, 'M', NULL, NULL, 'index', 'check-structure/index', 0, '0', NULL, 0, 16, 1, '2021-10-10 16:43:29', 'admin', '2021-10-10 16:43:37', 'admin');
INSERT INTO `sys_view_menu` VALUES (18, 'EditStructure', '修改结构物', 'edit', NULL, 'M', NULL, NULL, 'edit', 'check-structure/edit-structure', 1, '0', NULL, 0, 16, 1, '2021-10-10 16:45:29', 'admin', '2021-10-10 16:45:31', 'admin');
INSERT INTO `sys_view_menu` VALUES (19, 'SensorManager', '产品管理', 'el-icon-s-platform', NULL, 'C', NULL, NULL, '/sensor-manager', 'Layout', 0, '1', '/sensor-manager/index', 1, NULL, 3, '2021-10-10 16:46:31', 'admin', '2021-10-10 16:46:33', 'admin');
INSERT INTO `sys_view_menu` VALUES (20, 'SensorEdit', '设备管理', 'el-icon-s-help', NULL, 'M', NULL, NULL, 'index', 'sensor/index', 0, '0', NULL, 0, 19, 1, '2021-10-10 16:49:01', 'admin', '2021-10-10 16:49:09', 'admin');
INSERT INTO `sys_view_menu` VALUES (21, 'SensorModule', '型号管理', 'el-icon-s-order', NULL, 'M', NULL, NULL, 'sensor-model-module', 'sensor-model/index', 0, '0', NULL, 0, 19, 0, '2021-10-10 16:49:04', 'admin', '2021-10-10 16:49:11', 'admin');
INSERT INTO `sys_view_menu` VALUES (22, 'SystemManager', '系统管理', 'el-icon-s-tools', 1, 'C', NULL, NULL, '/system-manager', 'Layout', 0, '1', '/system-manager/manu', 1, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_view_menu` VALUES (24, 'Menu', '菜单管理', 'el-icon-menu', 1, 'M', NULL, NULL, 'menu', 'system-menu/index', 0, '0', '', 0, 22, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_view_menu` VALUES (25, 'Role', '角色管理', 'el-icon-s-custom', 1, 'M', NULL, NULL, 'role', 'system-role/index', 0, '0', '', 0, 22, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_view_menu` VALUES (26, 'login', '登录接口', NULL, 0, 'A', '/login', 'LOGIN', NULL, NULL, NULL, NULL, NULL, 0, 1, 0, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '角色编码',
  `role_name` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '角色名称',
  `role_status` int(11) NOT NULL COMMENT '角色状态',
  `del_flag` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '删除标志',
  `flag` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分配标志',
  `role_sort` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '角色排序',
  `data_scope` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '数据范围',
  `creator` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE` (`role_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统角色';

-- ----------------------------
-- Records of system_role
-- ----------------------------
BEGIN;
INSERT INTO `system_role` VALUES (1, 'ROOT_ADMIN', '超级管理员', 0, '0', '1', '1', '1', 'admin', '2022-01-06 12:44:07', NULL, NULL);
INSERT INTO `system_role` VALUES (2, 'GROUP_ADMIN', '小组管理员', 0, '0', '1', '2', '1', 'admin', '2022-01-06 12:44:10', NULL, NULL);
INSERT INTO `system_role` VALUES (3, 'USER', '普通用户', 0, '0', '1', '3', '1', 'admin', '2022-01-06 12:44:13', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `work_number` varchar(255) NOT NULL COMMENT '工号',
  `account_name` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `user_name` varchar(255) NOT NULL COMMENT '姓名',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `mobile` varchar(255) NOT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮件地址',
  `role` varchar(255) NOT NULL COMMENT '权限标识',
  `role_id` int(11) DEFAULT NULL COMMENT '权限编号',
  `position` varchar(255) DEFAULT NULL COMMENT '职位',
  `is_admin` int(2) NOT NULL DEFAULT '0' COMMENT '是否为管理员账户(0 否 1 是)',
  `operate_group_id` int(11) DEFAULT NULL COMMENT '操作组编号',
  `enabled` int(11) DEFAULT NULL COMMENT '是否被销户',
  `account_non_locked` int(11) DEFAULT NULL COMMENT '是否被锁定',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `last_lease` int(11) DEFAULT NULL COMMENT '是否长期有效',
  `lease_start_time` datetime DEFAULT NULL COMMENT '起始时间',
  `lease_time` int(11) DEFAULT NULL COMMENT '租期（月）',
  `enable_notification` int(1) DEFAULT NULL COMMENT '是否允许推送服务(邮件等)',
  `account_non_expired` int(2) DEFAULT '1' COMMENT '账户是否过期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX` (`account_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of system_user
-- ----------------------------
BEGIN;
INSERT INTO `system_user` VALUES (1, '100001', 'admin', '$2a$10$/6ArmS07S4n9gMLFN0ENpuEP5cq31AMIynEmRkUpvsvz4lko2OS/2', '北微-超级管理员', NULL, '1111111', '1', 'ROOT_ADMIN', 1, NULL, 1, 1, 1, 1, NULL, NULL, NULL, NULL, 0, 1);
INSERT INTO `system_user` VALUES (6, '100001', 'tyler', '$2a$10$GkpWZqbiBpgHgUa4k5MTteEIrhhZ8Fic.DKRocsSV90.UE.USZnou', '北微-超级管理员', NULL, '111111', '1', 'ROOT_ADMIN', 3, NULL, 0, 1, 1, 1, NULL, NULL, NULL, NULL, 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role` (
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `role_id` int(11) DEFAULT NULL COMMENT '角色编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户与角色关联';

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
BEGIN;
INSERT INTO `system_user_role` VALUES (1, 1);
INSERT INTO `system_user_role` VALUES (6, 1);
INSERT INTO `system_user_role` VALUES (19, 2);
INSERT INTO `system_user_role` VALUES (1, 2);
INSERT INTO `system_user_role` VALUES (1, 3);
COMMIT;

-- ----------------------------
-- Table structure for user_connection
-- ----------------------------
DROP TABLE IF EXISTS `user_connection`;
CREATE TABLE `user_connection` (
  `userId` varchar(36) NOT NULL COMMENT '本地用户id',
  `providerId` varchar(20) NOT NULL COMMENT '第三方服务商',
  `providerUserId` varchar(36) NOT NULL COMMENT '第三方用户id',
  `rank` int(11) NOT NULL COMMENT 'userId 绑定同一个 providerId 的排序',
  `displayName` varchar(64) DEFAULT NULL COMMENT '第三方用户名',
  `profileUrl` varchar(256) DEFAULT NULL COMMENT '主页',
  `imageUrl` varchar(256) DEFAULT NULL COMMENT '头像',
  `accessToken` varchar(512) NOT NULL COMMENT 'accessToken',
  `tokenId` bigint(20) DEFAULT NULL COMMENT 'auth_token.id',
  `refreshToken` varchar(512) DEFAULT NULL COMMENT 'refreshToken',
  `expireTime` bigint(20) DEFAULT '-1' COMMENT '过期时间, 基于 1970-01-01T00:00:00Z, 无过期时间默认为 -1',
  PRIMARY KEY (`userId`,`providerId`,`providerUserId`),
  UNIQUE KEY `idx_userId_providerId_rank` (`userId`,`providerId`,`rank`),
  KEY `idx_providerId_providerUserId_rank` (`providerId`,`providerUserId`,`rank`),
  KEY `idx_tokenId` (`tokenId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_connection
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
