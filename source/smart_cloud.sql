/*
 Navicat Premium Data Transfer

 Source Server         : 121.41.96.206
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : 121.41.96.206:3306
 Source Schema         : smart_cloud

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 16/10/2021 14:55:56
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='传感器';

-- ----------------------------
-- Records of device_sensor_equipment
-- ----------------------------
BEGIN;
INSERT INTO `device_sensor_equipment` VALUES (14, '100000005', '倾角传感器1', 10010, 1, 21, 1, 1, 16, 120.2884098730, '31.513274820947032', 0, '2021-09-08 10:26:04', 10, 207588, '80', '117.132.192.200', '2021-10-15 08:46:42', 47040, '20.6508', '1440018057609\r', NULL, NULL);
INSERT INTO `device_sensor_equipment` VALUES (15, '100000003', '倾角传感器2', NULL, 1, 22, 1, 1, 17, 120.2879807196, '31.5145919130638', 0, '2021-09-09 05:07:42', 1, 0, '0', '101.133.138.230', '2021-10-14 01:38:19', NULL, '0.0', NULL, NULL, NULL);
INSERT INTO `device_sensor_equipment` VALUES (16, '100000067', '倾角传感器3', NULL, 1, 23, 1, 1, 18, 120.2868649207, '31.51550654944506', 0, '2021-09-12 07:16:44', 1, 0, '0', '101.133.138.230', '2021-10-14 01:41:18', NULL, '0.0', NULL, NULL, NULL);
INSERT INTO `device_sensor_equipment` VALUES (17, '100000071', '倾角传感器4', NULL, 1, 22, 1, 1, 28, 120.2857920370, '31.516238252104156', 0, '2021-09-12 07:14:04', 1, 96, '8', '101.133.138.230', '2021-10-14 01:41:09', 96, '-199.99', NULL, NULL, NULL);
INSERT INTO `device_sensor_equipment` VALUES (22, '100000008', '无锡测试传感器', NULL, 1, 22, 6, 1, 31, 120.2793540000, '31.48057', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1234657', NULL, NULL);
INSERT INTO `device_sensor_equipment` VALUES (23, '100000108', '无锡职院传感器910', NULL, 1, 21, 1, 1, 29, 31.1000000000, '102.1', 9, '2021-09-10 07:33:21', 1, 1460, '0', '101.133.138.230', '2021-10-14 01:37:49', 373, '24.5661', '123455678', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for monitor_factors
-- ----------------------------
DROP TABLE IF EXISTS `monitor_factors`;
CREATE TABLE `monitor_factors` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `factors_name` varchar(255) DEFAULT NULL COMMENT '因素名称',
  `structure_id` int(11) DEFAULT NULL COMMENT '结构编号',
  `prototype_id` int(11) DEFAULT NULL COMMENT '监测原型',
  `monitor_item` varchar(255) DEFAULT NULL COMMENT '检测项',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_effective` int(11) DEFAULT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='监测因素';

-- ----------------------------
-- Records of monitor_factors
-- ----------------------------
BEGIN;
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
  `unit` varchar(255) DEFAULT NULL COMMENT '单位',
  `ascii_index` int(11) DEFAULT NULL COMMENT 'ASCII 解析标位',
  `hex_index` int(11) DEFAULT NULL COMMENT 'HEX 解析标位',
  `ascii_prefix` varchar(255) DEFAULT NULL COMMENT 'ASCII 解析前缀',
  `reduce_number` int(11) DEFAULT NULL COMMENT 'HEX减 偏移量',
  `divide_number` int(11) DEFAULT NULL COMMENT 'HEX除 偏移量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_DATA_ID` (`data_id`) USING BTREE COMMENT '唯一时序数据库标识'
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COMMENT='监控条目';

-- ----------------------------
-- Records of monitor_items
-- ----------------------------
BEGIN;
INSERT INTO `monitor_items` VALUES (1, 'X轴倾角', 'x_ang', 'float', '°', 1, 2, '1', 0, 0);
INSERT INTO `monitor_items` VALUES (2, 'Y轴倾角', 'y_ang', 'float', '°', 1, 2, '1', 0, 0);
INSERT INTO `monitor_items` VALUES (3, 'Z轴倾角', 'z_ang', 'float', '°', 1, 2, '1', 0, 0);
INSERT INTO `monitor_items` VALUES (4, 'X轴加速度', 'x_acc', 'float', 'g', 1, 2, '1', 0, 0);
INSERT INTO `monitor_items` VALUES (5, 'Y轴加速度', 'y_acc', 'float', 'g', 1, 2, '1', 0, 0);
INSERT INTO `monitor_items` VALUES (6, 'Z轴加速度', 'z_acc', 'float', 'g', 1, 2, '1', 0, 0);
INSERT INTO `monitor_items` VALUES (7, '电量', 'elect', 'float', NULL, 1, 2, '1', 0, 0);
INSERT INTO `monitor_items` VALUES (8, '温度', 'temp', 'float', '°C ', 1, 2, '1', 0, 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COMMENT='测点';

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
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COMMENT='测点模板';

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COMMENT='监测项目';

-- ----------------------------
-- Records of monitor_project
-- ----------------------------
BEGIN;
INSERT INTO `monitor_project` VALUES (21, '无锡软件园', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629731492490_70f55386', '1', 'admin', '2021-08-23 15:11:34', NULL);
INSERT INTO `monitor_project` VALUES (22, '无锡职院风机', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629766111027_a7823d00', '6', 'tyler', '2021-08-24 00:48:36', NULL);
INSERT INTO `monitor_project` VALUES (23, 'test1', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629971611694_d7f6d3b9', '7', 'bwadmin', '2021-08-26 09:53:35', NULL);
INSERT INTO `monitor_project` VALUES (24, 'test2', 'http://beiwei-smart-cloud.oss-cn-hangzhou.aliyuncs.com/pic/bw1629971803268_9c0502bf', '7', 'bwadmin', '2021-08-26 09:56:46', NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='监测类型\n';

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COMMENT='结构物实体';

-- ----------------------------
-- Records of monitor_structure
-- ----------------------------
BEGIN;
INSERT INTO `monitor_structure` VALUES (23, 53, 21, '桥梁', '桥梁', '桥梁结构物', 0.09, '0', '1', 'admin', NULL, NULL, NULL);
INSERT INTO `monitor_structure` VALUES (24, 53, 22, '桥梁', '桥梁', '桥梁结构物', 0.09, '0', '1', 'tyler', NULL, NULL, NULL);
INSERT INTO `monitor_structure` VALUES (25, 40, 23, '桥梁', '桥梁', '桥梁结构物', 0.01, '0', '1', 'bwadmin', NULL, NULL, NULL);
INSERT INTO `monitor_structure` VALUES (26, 40, 24, '桥梁', '桥梁', '桥梁结构物', 0.01, '0', '1', 'bwadmin', NULL, NULL, NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COMMENT='结构体模板';

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='企业组';

-- ----------------------------
-- Records of operate_group
-- ----------------------------
BEGIN;
INSERT INTO `operate_group` VALUES (1, '北微核心组', 'BW_MAIN', 4, 1, '核心组', 1, '2021-08-07 18:24:50');
INSERT INTO `operate_group` VALUES (7, '成都北微工作组', 'BW_CHENGDU', 1, 0, '北微', 0, '2021-08-23 14:14:26');
INSERT INTO `operate_group` VALUES (8, '测试', 'BW_TEST', 0, 0, '测试', 0, '2021-08-26 08:38:58');
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COMMENT='项目成员';

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='传感器模板\n';

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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COMMENT='传感器 监测类型关联';

-- ----------------------------
-- Records of sensor_model_type
-- ----------------------------
BEGIN;
INSERT INTO `sensor_model_type` VALUES (4, 2, 1);
INSERT INTO `sensor_model_type` VALUES (5, 2, 2);
INSERT INTO `sensor_model_type` VALUES (29, 1, 1);
INSERT INTO `sensor_model_type` VALUES (30, 1, 2);
INSERT INTO `sensor_model_type` VALUES (31, 1, 3);
INSERT INTO `sensor_model_type` VALUES (41, 6, 1);
INSERT INTO `sensor_model_type` VALUES (42, 6, 2);
INSERT INTO `sensor_model_type` VALUES (43, 6, 3);
COMMIT;

-- ----------------------------
-- Table structure for structure_type
-- ----------------------------
DROP TABLE IF EXISTS `structure_type`;
CREATE TABLE `structure_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `structure_code` varchar(255) NOT NULL,
  `is_effective` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='结构体类型';

-- ----------------------------
-- Records of structure_type
-- ----------------------------
BEGIN;
INSERT INTO `structure_type` VALUES (1, '建筑物', 'TEST_BUILD', 1);
INSERT INTO `structure_type` VALUES (2, '长桥', 'hello', 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='预警分组 用作预警规则的聚合根';

-- ----------------------------
-- Records of sys_alert_group
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_alert_notifaction_member
-- ----------------------------
DROP TABLE IF EXISTS `sys_alert_notifaction_member`;
CREATE TABLE `sys_alert_notifaction_member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `alert_group_id` int(11) DEFAULT NULL COMMENT '预警分组编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='消息通知接收';

-- ----------------------------
-- Records of sys_alert_notifaction_member
-- ----------------------------
BEGIN;
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
) /*!50100 STORAGE MEMORY */ ENGINE=InnoDB AUTO_INCREMENT=5060 DEFAULT CHARSET=utf8mb4 COMMENT='预警通知日志';

-- ----------------------------
-- Records of sys_alert_notification
-- ----------------------------
BEGIN;
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='告警规则';

-- ----------------------------
-- Records of sys_alert_role
-- ----------------------------
BEGIN;
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
-- Table structure for sys_industry_field
-- ----------------------------
DROP TABLE IF EXISTS `sys_industry_field`;
CREATE TABLE `sys_industry_field` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `code` varchar(255) DEFAULT NULL COMMENT '编码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行业领域\n';

-- ----------------------------
-- Records of sys_industry_field
-- ----------------------------
BEGIN;
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
  `send_address` varchar(255) DEFAULT NULL COMMENT '接收地址',
  `total_size` int(11) DEFAULT NULL COMMENT '保存后总数据量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1284 DEFAULT CHARSET=utf8mb4 COMMENT='传感器接收日志';

-- ----------------------------
-- Records of sys_monitor_receive_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_monitor_receive_log` VALUES (1, '100000005', '0ce8ebd0', '39.144.11.149', '2021-09-07 07:55:25', 40, 320, ' ', 23570);
INSERT INTO `sys_monitor_receive_log` VALUES (2, '100000005', '252cdad2', '117.132.193.140', '2021-09-07 08:07:28', 40, 320, '北京市 北京市', 23650);
INSERT INTO `sys_monitor_receive_log` VALUES (3, '100000005', 'e263fa0d', '39.144.18.27', '2021-09-07 08:11:28', 40, 320, ' ', 23746);
INSERT INTO `sys_monitor_receive_log` VALUES (4, '100000005', 'cd5d7666', '221.178.126.227', '2021-09-07 09:15:55', 25, 200, '重庆市 重庆市', 23770);
INSERT INTO `sys_monitor_receive_log` VALUES (7, '100000005', '8d331433', '39.144.3.140', '2021-09-07 11:04:25', 33, 264, ' ', 23794);
INSERT INTO `sys_monitor_receive_log` VALUES (8, '100000005', 'e79e035c', '117.132.192.16', '2021-09-07 11:32:20', 40, 320, '北京市 北京市', 23826);
INSERT INTO `sys_monitor_receive_log` VALUES (9, '100000005', 'b92b78d6', '221.178.127.229', '2021-09-07 12:28:34', 40, 320, '重庆市 重庆市', 23850);
INSERT INTO `sys_monitor_receive_log` VALUES (10, '100000005', 'd2bf75b6', '117.132.197.93', '2021-09-07 12:36:41', 40, 320, '北京市 北京市', 23882);
INSERT INTO `sys_monitor_receive_log` VALUES (11, '100000005', 'aef75137', '39.144.6.18', '2021-09-07 13:00:40', 40, 320, ' ', 23962);
INSERT INTO `sys_monitor_receive_log` VALUES (13, '100000005', '7988e6cb', '39.144.16.128', '2021-09-07 14:21:01', 40, 320, ' ', 24036);
INSERT INTO `sys_monitor_receive_log` VALUES (14, '100000005', '60187375', '39.144.14.194', '2021-09-07 14:29:13', 35, 280, ' ', 24084);
INSERT INTO `sys_monitor_receive_log` VALUES (15, '100000005', '3ade2a0f', '39.144.1.29', '2021-09-07 14:33:14', 35, 280, ' ', 24124);
INSERT INTO `sys_monitor_receive_log` VALUES (16, '100000005', '3e426d6b', '39.144.17.41', '2021-09-07 15:13:14', 40, 320, ' ', 24204);
INSERT INTO `sys_monitor_receive_log` VALUES (17, '100000005', '498321e2', '39.144.5.67', '2021-09-07 22:51:04', 14, 112, ' ', 24220);
INSERT INTO `sys_monitor_receive_log` VALUES (18, '100000005', 'd1cabe3f', '39.144.15.245', '2021-09-07 23:07:13', 40, 320, ' ', 24252);
INSERT INTO `sys_monitor_receive_log` VALUES (19, '100000005', '1e804dc0', '223.104.254.181', '2021-09-07 23:11:10', 33, 264, '北京市 北京市', 24284);
INSERT INTO `sys_monitor_receive_log` VALUES (20, '100000005', 'bc57997f', '39.144.8.7', '2021-09-07 23:26:50', 10, 80, ' ', 24300);
INSERT INTO `sys_monitor_receive_log` VALUES (21, '100000005', '3cd60054', '39.144.9.12', '2021-09-07 23:43:22', 40, 320, ' ', 24332);
INSERT INTO `sys_monitor_receive_log` VALUES (22, '100000005', '240572e2', '39.144.2.162', '2021-09-07 23:47:26', 40, 320, ' ', 24356);
INSERT INTO `sys_monitor_receive_log` VALUES (23, '100000005', 'a4d13b8a', '39.144.17.14', '2021-09-07 23:55:24', 40, 320, ' ', 24380);
INSERT INTO `sys_monitor_receive_log` VALUES (24, '100000005', '0a1c05f8', '117.132.197.34', '2021-09-08 01:39:50', 40, 320, '北京市 北京市', 24412);
INSERT INTO `sys_monitor_receive_log` VALUES (25, '100000005', '43d87ab2', '39.144.2.230', '2021-09-08 02:07:57', 40, 320, ' ', 24436);
INSERT INTO `sys_monitor_receive_log` VALUES (26, '100000005', '08719359', '221.178.127.88', '2021-09-08 02:32:03', 40, 320, '重庆市 重庆市', 24460);
INSERT INTO `sys_monitor_receive_log` VALUES (27, '100000005', 'e5c48ac6', '39.144.17.99', '2021-09-08 03:08:12', 40, 320, ' ', 24484);
INSERT INTO `sys_monitor_receive_log` VALUES (28, '100000005', '9d77f9b5', '218.204.253.3', '2021-09-08 04:04:36', 34, 272, '广东省 广州市', 24508);
INSERT INTO `sys_monitor_receive_log` VALUES (29, '100000005', '128025ae', '39.144.8.148', '2021-09-08 04:56:49', 35, 280, ' ', 24524);
INSERT INTO `sys_monitor_receive_log` VALUES (30, '100000005', '48e6eb31', '221.178.127.201', '2021-09-08 05:28:48', 40, 320, '重庆市 重庆市', 24556);
INSERT INTO `sys_monitor_receive_log` VALUES (31, '100000005', 'dfbb0e5c', '39.144.2.115', '2021-09-08 06:04:56', 40, 320, ' ', 24580);
INSERT INTO `sys_monitor_receive_log` VALUES (32, '100000005', '5931399a', '117.132.196.216', '2021-09-08 06:12:59', 40, 320, '北京市 北京市', 24604);
INSERT INTO `sys_monitor_receive_log` VALUES (33, '100000005', 'fc41759c', '221.178.127.183', '2021-09-08 06:17:08', 40, 320, '重庆市 重庆市', 24644);
INSERT INTO `sys_monitor_receive_log` VALUES (34, '100000005', '0a762c36', '39.144.17.92', '2021-09-08 06:36:59', 31, 248, ' ', 24668);
INSERT INTO `sys_monitor_receive_log` VALUES (35, '100000005', '418d5537', '39.144.2.24', '2021-09-08 07:09:12', 40, 320, ' ', 24700);
INSERT INTO `sys_monitor_receive_log` VALUES (36, '100000005', '42fb8b11', '39.144.16.146', '2021-09-08 07:41:20', 40, 320, ' ', 24724);
INSERT INTO `sys_monitor_receive_log` VALUES (38, '100000005', '9067dcc2', '117.132.195.133', '2021-09-08 09:21:46', 40, 320, '北京市 北京市', 24748);
INSERT INTO `sys_monitor_receive_log` VALUES (39, '100000005', '73e2d242', '39.144.14.177', '2021-09-08 10:26:04', 40, 320, ' ', 24820);
INSERT INTO `sys_monitor_receive_log` VALUES (40, '100000005', 'cedfd3c5', '39.144.4.6', '2021-09-08 12:50:07', 4, 32, ' ', 24828);
INSERT INTO `sys_monitor_receive_log` VALUES (41, '100000005', 'eea283bb', '39.144.13.1', '2021-09-08 13:10:20', 15, 120, ' ', 24844);
INSERT INTO `sys_monitor_receive_log` VALUES (42, '100000005', '68948cfc', '39.144.1.37', '2021-09-08 15:53:53', 40, 320, ' ', 24876);
INSERT INTO `sys_monitor_receive_log` VALUES (43, '100000005', '5ca89086', '39.144.14.235', '2021-09-08 15:57:57', 34, 272, ' ', 24908);
INSERT INTO `sys_monitor_receive_log` VALUES (44, '100000005', 'd6e1e4a1', '39.144.18.140', '2021-09-08 20:19:05', 35, 280, ' ', 24932);
INSERT INTO `sys_monitor_receive_log` VALUES (45, '100000005', '828dd111', '117.132.197.158', '2021-09-08 22:31:26', 40, 320, '北京市 北京市', 24964);
INSERT INTO `sys_monitor_receive_log` VALUES (46, '100000005', '1c3c4c93', '39.144.10.108', '2021-09-08 22:34:58', 4, 32, ' ', 24972);
INSERT INTO `sys_monitor_receive_log` VALUES (47, '100000005', 'c3a61c6a', '117.132.196.212', '2021-09-08 23:15:11', 12, 96, '北京市 北京市', 24988);
INSERT INTO `sys_monitor_receive_log` VALUES (48, '100000005', 'f0b2051a', '221.178.125.136', '2021-09-08 23:27:40', 40, 320, '重庆市 重庆市', 25028);
INSERT INTO `sys_monitor_receive_log` VALUES (49, '100000005', '10ad594c', '39.144.1.124', '2021-09-09 00:03:33', 17, 136, ' ', 25044);
INSERT INTO `sys_monitor_receive_log` VALUES (50, '100000005', '381daf5b', '39.144.7.9', '2021-09-09 00:07:51', 40, 320, ' ', 25116);
INSERT INTO `sys_monitor_receive_log` VALUES (51, '100000005', '9321d401', '117.132.197.251', '2021-09-09 00:11:50', 40, 320, '北京市 北京市', 25172);
INSERT INTO `sys_monitor_receive_log` VALUES (52, '100000005', '9de03841', '117.132.193.230', '2021-09-09 00:15:22', 3, 24, '北京市 北京市', 25180);
INSERT INTO `sys_monitor_receive_log` VALUES (53, '100000005', '5c3a4935', '221.178.126.64', '2021-09-09 00:23:57', 38, 304, '重庆市 重庆市', 25236);
INSERT INTO `sys_monitor_receive_log` VALUES (54, '100000005', 'a6223ce6', '39.144.8.87', '2021-09-09 00:59:40', 14, 112, ' ', 25260);
INSERT INTO `sys_monitor_receive_log` VALUES (55, '100000005', '282f5e60', '39.144.13.34', '2021-09-09 01:04:04', 38, 304, ' ', 25292);
INSERT INTO `sys_monitor_receive_log` VALUES (56, '100000005', '08dd311c', '221.178.124.208', '2021-09-09 01:08:03', 31, 248, '重庆市 重庆市', 25324);
INSERT INTO `sys_monitor_receive_log` VALUES (57, '100000005', '8dcdac80', '218.204.253.152', '2021-09-09 01:15:40', 1, 8, '广东省 广州市', 25332);
INSERT INTO `sys_monitor_receive_log` VALUES (58, '100000005', '8a3cec53', '117.132.197.40', '2021-09-09 01:20:10', 40, 320, '北京市 北京市', 25364);
INSERT INTO `sys_monitor_receive_log` VALUES (59, '100000005', '4d263227', '221.178.126.206', '2021-09-09 01:35:56', 6, 48, '重庆市 重庆市', 25372);
INSERT INTO `sys_monitor_receive_log` VALUES (60, '100000005', 'e94368fa', '223.104.255.173', '2021-09-09 01:52:25', 39, 312, '广东省 广州市', 25396);
INSERT INTO `sys_monitor_receive_log` VALUES (61, '100000005', 'd16eb8fb', '39.144.14.210', '2021-09-09 02:32:08', 20, 160, ' ', 25412);
INSERT INTO `sys_monitor_receive_log` VALUES (62, '100000005', 'fa15c764', '39.144.9.48', '2021-09-09 02:40:39', 13, 104, ' ', 25428);
INSERT INTO `sys_monitor_receive_log` VALUES (63, '100000005', '8c9e283f', '221.178.125.48', '2021-09-09 03:36:21', 5, 40, '重庆市 重庆市', 25444);
INSERT INTO `sys_monitor_receive_log` VALUES (64, '100000003', 'acde3e07', '94.102.49.74', '2021-09-09 05:07:42', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (65, '100000005', '1f3ae2ff', '39.144.16.91', '2021-09-09 06:21:32', 34, 272, ' ', 25468);
INSERT INTO `sys_monitor_receive_log` VALUES (66, '100000005', '14af4a2b', '39.144.6.35', '2021-09-09 06:49:30', 40, 320, ' ', 25500);
INSERT INTO `sys_monitor_receive_log` VALUES (67, '100000005', 'ac2922e9', '218.204.253.31', '2021-09-09 06:57:02', 9, 72, '广东省 广州市', 25516);
INSERT INTO `sys_monitor_receive_log` VALUES (68, '100000005', '53248378', '117.132.197.197', '2021-09-09 07:01:42', 20, 160, '北京市 北京市', 25540);
INSERT INTO `sys_monitor_receive_log` VALUES (69, '100000005', 'ae63ac8e', '218.204.252.121', '2021-09-09 07:17:36', 40, 320, '广东省 广州市', 25604);
INSERT INTO `sys_monitor_receive_log` VALUES (70, '100000005', 'da634c98', '39.144.17.34', '2021-09-09 07:21:39', 39, 312, ' ', 25684);
INSERT INTO `sys_monitor_receive_log` VALUES (71, '100000005', '34702dc6', '39.144.6.203', '2021-09-09 08:26:04', 32, 256, ' ', 25716);
INSERT INTO `sys_monitor_receive_log` VALUES (72, '100000005', '789b55f4', '117.132.194.50', '2021-09-09 09:09:53', 25, 200, '北京市 北京市', 25740);
INSERT INTO `sys_monitor_receive_log` VALUES (73, '100000005', '583eeb0c', '39.144.11.13', '2021-09-09 09:21:56', 8, 64, ' ', 25756);
INSERT INTO `sys_monitor_receive_log` VALUES (74, '100000005', '8fd0d1d8', '117.132.197.183', '2021-09-09 10:34:06', 8, 64, '北京市 北京市', 25764);
INSERT INTO `sys_monitor_receive_log` VALUES (75, '100000005', '2693c6f7', '39.144.16.149', '2021-09-09 10:46:33', 17, 136, ' ', 25780);
INSERT INTO `sys_monitor_receive_log` VALUES (76, '100000005', 'e034365d', '117.132.191.185', '2021-09-09 11:30:52', 39, 312, '北京市 北京市', 25812);
INSERT INTO `sys_monitor_receive_log` VALUES (77, '100000005', 'b7aadae9', '221.178.126.222', '2021-09-09 11:42:31', 7, 56, '重庆市 重庆市', 25828);
INSERT INTO `sys_monitor_receive_log` VALUES (78, '100000005', '4900105f', '39.144.10.86', '2021-09-09 11:50:29', 4, 32, ' ', 25844);
INSERT INTO `sys_monitor_receive_log` VALUES (79, '100000005', '9f45d768', '117.132.191.255', '2021-09-09 12:46:41', 13, 104, '北京市 北京市', 25860);
INSERT INTO `sys_monitor_receive_log` VALUES (80, '100000005', 'bf586a41', '223.104.255.2', '2021-09-09 13:42:59', 15, 120, '广东省 广州市', 25876);
INSERT INTO `sys_monitor_receive_log` VALUES (81, '100000005', '2dd29474', '39.144.7.172', '2021-09-09 13:54:51', 5, 40, ' ', 25892);
INSERT INTO `sys_monitor_receive_log` VALUES (82, '100000005', 'affd6c77', '117.132.198.23', '2021-09-09 13:58:54', 6, 48, '北京市 北京市', 25908);
INSERT INTO `sys_monitor_receive_log` VALUES (83, '100000005', '8ffe7e18', '223.104.254.206', '2021-09-09 14:07:17', 6, 48, '北京市 北京市', 25916);
INSERT INTO `sys_monitor_receive_log` VALUES (84, '100000005', '6c545d31', '117.132.198.204', '2021-09-09 14:23:18', 1, 8, '北京市 北京市', 25924);
INSERT INTO `sys_monitor_receive_log` VALUES (85, '100000005', '09836aa7', '223.104.255.114', '2021-09-09 14:27:07', 1, 8, '广东省 广州市', 25932);
INSERT INTO `sys_monitor_receive_log` VALUES (86, '100000005', '3a36ce1e', '223.104.255.194', '2021-09-09 14:35:06', 1, 8, '广东省 广州市', 25940);
INSERT INTO `sys_monitor_receive_log` VALUES (87, '100000005', 'ed7f0562', '39.144.2.195', '2021-09-09 14:39:39', 33, 264, ' ', 25964);
INSERT INTO `sys_monitor_receive_log` VALUES (88, '100000005', '6a8cb205', '223.104.254.242', '2021-09-09 15:03:17', 11, 88, '北京市 北京市', 25972);
INSERT INTO `sys_monitor_receive_log` VALUES (89, '100000005', '5ade1409', '39.144.3.14', '2021-09-09 15:19:19', 6, 48, ' ', 25980);
INSERT INTO `sys_monitor_receive_log` VALUES (90, '100000005', '8033343d', '39.144.12.188', '2021-09-09 15:59:42', 11, 88, ' ', 25996);
INSERT INTO `sys_monitor_receive_log` VALUES (91, '100000005', '225e1cca', '39.144.5.187', '2021-09-09 16:03:39', 2, 16, ' ', 26004);
INSERT INTO `sys_monitor_receive_log` VALUES (92, '100000005', '023d5a03', '223.104.254.80', '2021-09-09 16:27:27', 10, 80, '北京市 北京市', 26020);
INSERT INTO `sys_monitor_receive_log` VALUES (93, '100000005', '34f63ecf', '39.144.2.109', '2021-09-09 16:44:07', 33, 264, ' ', 26044);
INSERT INTO `sys_monitor_receive_log` VALUES (94, '100000005', '497cc2d6', '218.204.252.187', '2021-09-09 16:47:33', 12, 96, '广东省 广州市', 26060);
INSERT INTO `sys_monitor_receive_log` VALUES (95, '100000005', 'e3b60846', '223.104.255.182', '2021-09-09 17:00:01', 10, 80, '广东省 广州市', 26076);
INSERT INTO `sys_monitor_receive_log` VALUES (96, '100000005', '7cda5ed2', '39.144.17.135', '2021-09-09 17:44:06', 1, 8, ' ', 26084);
INSERT INTO `sys_monitor_receive_log` VALUES (98, '100000005', 'e611cfe2', '39.144.3.164', '2021-09-09 18:07:45', 2, 16, ' ', 26092);
INSERT INTO `sys_monitor_receive_log` VALUES (99, '100000005', 'a014a7df', '221.178.124.96', '2021-09-09 18:28:15', 20, 160, '重庆市 重庆市', 26116);
INSERT INTO `sys_monitor_receive_log` VALUES (100, '100000005', 'f968b7c8', '39.144.15.7', '2021-09-09 18:51:53', 1, 8, ' ', 26124);
INSERT INTO `sys_monitor_receive_log` VALUES (101, '100000005', 'b9512910', '39.144.15.56', '2021-09-09 19:52:11', 2, 16, ' ', 26132);
INSERT INTO `sys_monitor_receive_log` VALUES (102, '100000005', '35aae102', '39.144.6.213', '2021-09-09 20:21:02', 35, 280, ' ', 26156);
INSERT INTO `sys_monitor_receive_log` VALUES (103, '100000005', '487a884d', '39.144.8.1', '2021-09-09 22:32:56', 8, 64, ' ', 26172);
INSERT INTO `sys_monitor_receive_log` VALUES (104, '100000005', '3fdcd40c', '39.144.16.238', '2021-09-09 22:40:56', 2, 16, ' ', 26180);
INSERT INTO `sys_monitor_receive_log` VALUES (105, '100000005', '53c1e9c6', '39.144.3.227', '2021-09-09 22:49:10', 7, 56, ' ', 26196);
INSERT INTO `sys_monitor_receive_log` VALUES (106, '100000005', 'b702ca03', '39.144.11.72', '2021-09-09 22:57:32', 38, 304, ' ', 26220);
INSERT INTO `sys_monitor_receive_log` VALUES (107, '100000005', 'c0902bad', '39.144.16.189', '2021-09-09 23:05:07', 1, 8, ' ', 26228);
INSERT INTO `sys_monitor_receive_log` VALUES (109, '100000005', '696a69a3', '39.144.12.190', '2021-09-09 23:13:38', 40, 320, ' ', 26252);
INSERT INTO `sys_monitor_receive_log` VALUES (110, '100000005', 'ba204113', '223.104.255.72', '2021-09-09 23:25:33', 20, 160, '广东省 广州市', 26268);
INSERT INTO `sys_monitor_receive_log` VALUES (111, '100000005', 'bc99e0ce', '39.144.10.236', '2021-09-09 23:29:03', 1, 8, ' ', 26276);
INSERT INTO `sys_monitor_receive_log` VALUES (112, '100000005', '1d3fc8c4', '39.144.16.6', '2021-09-09 23:49:33', 27, 216, ' ', 26300);
INSERT INTO `sys_monitor_receive_log` VALUES (113, '100000005', '027ebfc7', '223.104.255.183', '2021-09-10 00:01:51', 38, 304, '广东省 广州市', 26324);
INSERT INTO `sys_monitor_receive_log` VALUES (114, '100000005', '6096d14b', '117.132.195.19', '2021-09-10 00:05:57', 33, 264, '北京市 北京市', 26340);
INSERT INTO `sys_monitor_receive_log` VALUES (115, '100000005', 'e9603fb5', '39.144.2.90', '2021-09-10 00:41:58', 40, 320, ' ', 26364);
INSERT INTO `sys_monitor_receive_log` VALUES (116, '100000005', 'fc97d355', '223.104.254.146', '2021-09-10 01:02:12', 28, 224, '北京市 北京市', 26388);
INSERT INTO `sys_monitor_receive_log` VALUES (117, '100000005', '89a754f5', '39.144.4.10', '2021-09-10 01:34:11', 13, 104, ' ', 26396);
INSERT INTO `sys_monitor_receive_log` VALUES (118, '100000005', '7d665bba', '39.144.16.95', '2021-09-10 01:37:36', 1, 8, ' ', 26404);
INSERT INTO `sys_monitor_receive_log` VALUES (119, '100000005', 'c85db576', '117.132.193.103', '2021-09-10 01:53:51', 5, 40, '北京市 北京市', 26412);
INSERT INTO `sys_monitor_receive_log` VALUES (120, '100000005', '5443c04d', '39.144.8.66', '2021-09-10 01:57:49', 1, 8, ' ', 26420);
INSERT INTO `sys_monitor_receive_log` VALUES (121, '100000005', '928ed5f8', '39.144.12.27', '2021-09-10 02:14:18', 27, 216, ' ', 26436);
INSERT INTO `sys_monitor_receive_log` VALUES (122, '100000005', 'c83205e3', '39.144.7.224', '2021-09-10 02:29:57', 1, 8, ' ', 26444);
INSERT INTO `sys_monitor_receive_log` VALUES (123, '100000005', '96cf4a7b', '39.144.6.114', '2021-09-10 02:46:38', 33, 264, ' ', 26468);
INSERT INTO `sys_monitor_receive_log` VALUES (124, '100000005', 'cb698e88', '117.132.192.16', '2021-09-10 04:14:50', 37, 296, '北京市 北京市', 26492);
INSERT INTO `sys_monitor_receive_log` VALUES (125, '100000005', '70986d5e', '39.144.5.34', '2021-09-10 04:26:53', 40, 320, ' ', 26516);
INSERT INTO `sys_monitor_receive_log` VALUES (126, '100000005', '7d0d2be5', '218.204.252.58', '2021-09-10 04:46:59', 40, 320, '广东省 广州市', 26540);
INSERT INTO `sys_monitor_receive_log` VALUES (127, '100000005', 'ab233fa5', '221.178.124.12', '2021-09-10 04:51:00', 40, 320, '重庆市 重庆市', 26564);
INSERT INTO `sys_monitor_receive_log` VALUES (128, '100000005', 'c48ee57c', '39.144.11.109', '2021-09-10 05:51:22', 40, 320, ' ', 26596);
INSERT INTO `sys_monitor_receive_log` VALUES (129, '100000005', 'bacdd9c7', '117.132.194.206', '2021-09-10 05:58:50', 11, 88, '北京市 北京市', 26604);
INSERT INTO `sys_monitor_receive_log` VALUES (130, '100000005', '2e928b4b', '117.132.193.92', '2021-09-10 06:07:27', 31, 248, '北京市 北京市', 26628);
INSERT INTO `sys_monitor_receive_log` VALUES (131, '100000005', '321586f6', '221.178.126.249', '2021-09-10 06:14:46', 2, 16, '重庆市 重庆市', 26636);
INSERT INTO `sys_monitor_receive_log` VALUES (132, '100000005', '93617b38', '39.144.14.168', '2021-09-10 06:39:36', 32, 256, ' ', 26660);
INSERT INTO `sys_monitor_receive_log` VALUES (133, '100000005', 'e1e0ea1b', '39.144.1.164', '2021-09-10 06:43:02', 11, 88, ' ', 26676);
INSERT INTO `sys_monitor_receive_log` VALUES (134, '100000005', 'e37eb345', '39.144.8.24', '2021-09-10 06:46:54', 1, 8, ' ', 26684);
INSERT INTO `sys_monitor_receive_log` VALUES (135, '100000005', 'cd3f564c', '221.178.124.39', '2021-09-10 06:55:11', 14, 112, '重庆市 重庆市', 26700);
INSERT INTO `sys_monitor_receive_log` VALUES (136, '100000005', '51cf33c8', '39.144.3.123', '2021-09-10 07:07:27', 26, 208, ' ', 26716);
INSERT INTO `sys_monitor_receive_log` VALUES (137, '100000005', 'cb42ad6e', '221.178.125.220', '2021-09-10 07:19:08', 7, 56, '重庆市 重庆市', 26732);
INSERT INTO `sys_monitor_receive_log` VALUES (138, '100000005', 'dc9f54b5', '117.132.195.49', '2021-09-10 07:23:31', 26, 208, '北京市 北京市', 26756);
INSERT INTO `sys_monitor_receive_log` VALUES (139, '100000005', '3d7ed1f6', '221.178.127.1', '2021-09-10 07:27:07', 1, 8, '重庆市 重庆市', 26764);
INSERT INTO `sys_monitor_receive_log` VALUES (140, '100000108', '8fb7dbfe', '39.144.4.25', '2021-09-10 07:33:21', 5, 20, ' ', 4);
INSERT INTO `sys_monitor_receive_log` VALUES (141, '100000108', 'e7d3cc39', '39.144.18.131', '2021-09-10 07:34:50', 5, 20, ' ', 8);
INSERT INTO `sys_monitor_receive_log` VALUES (142, '100000108', '0f833477', '117.132.197.236', '2021-09-10 07:35:25', 5, 20, '北京市 北京市', 12);
INSERT INTO `sys_monitor_receive_log` VALUES (143, '100000005', '0101ad73', '39.144.16.167', '2021-09-10 07:35:50', 40, 320, ' ', 26804);
INSERT INTO `sys_monitor_receive_log` VALUES (144, '100000108', '2df19084', '221.178.127.145', '2021-09-10 07:36:16', 5, 20, '重庆市 重庆市', 16);
INSERT INTO `sys_monitor_receive_log` VALUES (145, '100000108', 'db4a6df0', '39.144.9.137', '2021-09-10 07:37:07', 5, 20, ' ', 20);
INSERT INTO `sys_monitor_receive_log` VALUES (146, '100000005', '2c247a9f', '39.144.3.28', '2021-09-10 07:59:27', 4, 32, ' ', 26820);
INSERT INTO `sys_monitor_receive_log` VALUES (147, '100000005', '71141e7b', '117.132.197.71', '2021-09-10 08:16:04', 25, 200, '北京市 北京市', 26836);
INSERT INTO `sys_monitor_receive_log` VALUES (148, '100000005', '320a20a6', '39.144.4.150', '2021-09-10 08:27:26', 8, 64, ' ', 26844);
INSERT INTO `sys_monitor_receive_log` VALUES (149, '100000005', '2730da86', '39.144.8.40', '2021-09-10 08:36:02', 32, 256, ' ', 26868);
INSERT INTO `sys_monitor_receive_log` VALUES (150, '100000005', '11301ae2', '221.178.126.60', '2021-09-10 09:00:09', 31, 248, '重庆市 重庆市', 26892);
INSERT INTO `sys_monitor_receive_log` VALUES (151, '100000005', 'd7426dca', '39.144.8.151', '2021-09-10 09:16:12', 40, 320, ' ', 26916);
INSERT INTO `sys_monitor_receive_log` VALUES (152, '100000005', 'e627d193', '39.144.16.136', '2021-09-10 10:00:16', 40, 320, ' ', 26948);
INSERT INTO `sys_monitor_receive_log` VALUES (153, '100000005', '4964ef59', '39.144.5.241', '2021-09-10 11:16:11', 9, 72, ' ', 26964);
INSERT INTO `sys_monitor_receive_log` VALUES (154, '100000005', '319bec37', '117.132.198.185', '2021-09-10 11:20:36', 40, 320, '北京市 北京市', 26988);
INSERT INTO `sys_monitor_receive_log` VALUES (155, '100000005', 'aeea51a7', '39.144.13.16', '2021-09-10 13:45:14', 40, 320, ' ', 27012);
INSERT INTO `sys_monitor_receive_log` VALUES (156, '100000005', 'ce05553a', '221.178.124.169', '2021-09-10 17:34:10', 40, 320, '重庆市 重庆市', 27036);
INSERT INTO `sys_monitor_receive_log` VALUES (157, '100000003', '493bb5ee', '185.156.72.32', '2021-09-10 20:32:09', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (158, '100000005', '4d47fe46', '221.178.124.139', '2021-09-10 22:22:58', 14, 112, '重庆市 重庆市', 27052);
INSERT INTO `sys_monitor_receive_log` VALUES (159, '100000005', '26835f9b', '117.132.196.55', '2021-09-10 22:31:26', 39, 312, '北京市 北京市', 27076);
INSERT INTO `sys_monitor_receive_log` VALUES (160, '100000005', '09144e7a', '39.144.8.93', '2021-09-10 23:03:04', 3, 24, ' ', 27084);
INSERT INTO `sys_monitor_receive_log` VALUES (161, '100000005', '814f40ad', '39.144.12.156', '2021-09-10 23:31:50', 22, 176, ' ', 27100);
INSERT INTO `sys_monitor_receive_log` VALUES (162, '100000005', '01da6776', '39.144.11.115', '2021-09-10 23:35:17', 16, 128, ' ', 27116);
INSERT INTO `sys_monitor_receive_log` VALUES (163, '100000005', '6c828dee', '221.178.127.145', '2021-09-10 23:51:50', 39, 312, '重庆市 重庆市', 27140);
INSERT INTO `sys_monitor_receive_log` VALUES (164, '100000005', '1b576ff4', '39.144.6.255', '2021-09-11 00:11:22', 1, 8, ' ', 27148);
INSERT INTO `sys_monitor_receive_log` VALUES (165, '100000005', '77359da8', '39.144.15.147', '2021-09-11 00:31:35', 15, 120, ' ', 27164);
INSERT INTO `sys_monitor_receive_log` VALUES (166, '100000005', '40b2720e', '39.144.17.53', '2021-09-11 00:52:01', 40, 320, ' ', 27188);
INSERT INTO `sys_monitor_receive_log` VALUES (167, '100000005', 'a1c4e3a1', '39.144.14.179', '2021-09-11 01:04:11', 17, 136, ' ', 27204);
INSERT INTO `sys_monitor_receive_log` VALUES (168, '100000005', 'cf37f3b2', '221.178.126.183', '2021-09-11 01:08:12', 26, 208, '重庆市 重庆市', 27228);
INSERT INTO `sys_monitor_receive_log` VALUES (169, '100000005', '0686fdd1', '39.144.15.102', '2021-09-11 01:16:06', 40, 320, ' ', 27244);
INSERT INTO `sys_monitor_receive_log` VALUES (170, '100000005', '978bf723', '39.144.7.15', '2021-09-11 01:20:15', 40, 320, ' ', 27268);
INSERT INTO `sys_monitor_receive_log` VALUES (171, '100000005', 'f04912b6', '221.178.124.217', '2021-09-11 01:44:01', 25, 200, '重庆市 重庆市', 27292);
INSERT INTO `sys_monitor_receive_log` VALUES (172, '100000005', '335f1e1f', '117.132.193.124', '2021-09-11 01:47:47', 11, 88, '北京市 北京市', 27308);
INSERT INTO `sys_monitor_receive_log` VALUES (173, '100000005', '81f99673', '117.132.192.5', '2021-09-11 02:12:19', 40, 320, '北京市 北京市', 27332);
INSERT INTO `sys_monitor_receive_log` VALUES (174, '100000005', '84c1c639', '39.144.6.143', '2021-09-11 02:24:31', 35, 280, ' ', 27364);
INSERT INTO `sys_monitor_receive_log` VALUES (175, '100000005', '5f2b2ff9', '117.132.196.30', '2021-09-11 03:00:32', 40, 320, '北京市 北京市', 27388);
INSERT INTO `sys_monitor_receive_log` VALUES (176, '100000005', 'cc42460c', '117.132.198.19', '2021-09-11 03:24:36', 40, 320, '北京市 北京市', 27412);
INSERT INTO `sys_monitor_receive_log` VALUES (177, '100000005', 'ca4b45c3', '39.144.15.36', '2021-09-11 03:44:41', 40, 320, ' ', 27436);
INSERT INTO `sys_monitor_receive_log` VALUES (178, '100000005', '821ac5f0', '221.178.124.177', '2021-09-11 04:32:54', 40, 320, '重庆市 重庆市', 27460);
INSERT INTO `sys_monitor_receive_log` VALUES (179, '100000005', '9112637e', '117.132.195.211', '2021-09-11 05:29:09', 40, 320, '北京市 北京市', 27484);
INSERT INTO `sys_monitor_receive_log` VALUES (180, '100000005', 'a70215c6', '218.204.252.58', '2021-09-11 05:37:12', 40, 320, '广东省 广州市', 27508);
INSERT INTO `sys_monitor_receive_log` VALUES (181, '100000005', 'f0e27bec', '117.132.193.104', '2021-09-11 05:45:14', 40, 320, '北京市 北京市', 27532);
INSERT INTO `sys_monitor_receive_log` VALUES (182, '100000005', 'e0995a29', '39.144.7.30', '2021-09-11 05:53:14', 40, 320, ' ', 27564);
INSERT INTO `sys_monitor_receive_log` VALUES (183, '100000005', '3a3f3144', '39.144.2.29', '2021-09-11 06:37:28', 37, 296, ' ', 27588);
INSERT INTO `sys_monitor_receive_log` VALUES (184, '100000005', '568376ad', '39.144.13.216', '2021-09-11 06:40:52', 1, 8, ' ', 27596);
INSERT INTO `sys_monitor_receive_log` VALUES (185, '100000005', '1deed52a', '221.178.126.31', '2021-09-11 07:01:02', 8, 64, '重庆市 重庆市', 27604);
INSERT INTO `sys_monitor_receive_log` VALUES (186, '100000005', 'f2ababd0', '39.144.1.115', '2021-09-11 07:05:20', 26, 208, ' ', 27620);
INSERT INTO `sys_monitor_receive_log` VALUES (187, '100000005', 'd0ee5fc1', '39.144.17.104', '2021-09-11 07:41:45', 40, 320, ' ', 27644);
INSERT INTO `sys_monitor_receive_log` VALUES (188, '100000005', '35e78dac', '117.132.195.108', '2021-09-11 08:21:54', 40, 320, '北京市 北京市', 27668);
INSERT INTO `sys_monitor_receive_log` VALUES (189, '100000003', '19bf5b4a', '178.159.37.58', '2021-09-11 08:31:19', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (190, '100000005', 'cd2e2dd2', '218.204.253.166', '2021-09-11 08:54:01', 40, 320, '广东省 广州市', 27692);
INSERT INTO `sys_monitor_receive_log` VALUES (191, '100000005', '6e73c13f', '223.104.254.35', '2021-09-11 10:38:29', 40, 320, '北京市 北京市', 27728);
INSERT INTO `sys_monitor_receive_log` VALUES (192, '100000005', '9af18ab6', '39.144.12.1', '2021-09-11 10:46:27', 40, 320, ' ', 27752);
INSERT INTO `sys_monitor_receive_log` VALUES (193, '100000005', 'e3a2702b', '218.204.253.111', '2021-09-11 12:06:50', 40, 320, '广东省 广州市', 27776);
INSERT INTO `sys_monitor_receive_log` VALUES (194, '100000005', 'a5068738', '39.144.13.154', '2021-09-11 12:31:03', 35, 280, ' ', 27800);
INSERT INTO `sys_monitor_receive_log` VALUES (195, '100000005', '151c742f', '39.144.9.44', '2021-09-11 12:55:08', 34, 272, ' ', 27832);
INSERT INTO `sys_monitor_receive_log` VALUES (196, '100000005', 'c4385684', '39.144.16.179', '2021-09-11 13:39:22', 34, 272, ' ', 27856);
INSERT INTO `sys_monitor_receive_log` VALUES (197, '100000005', '9c43d53a', '221.178.125.37', '2021-09-11 15:47:52', 33, 264, '重庆市 重庆市', 27880);
INSERT INTO `sys_monitor_receive_log` VALUES (198, '100000003', 'a667ffe5', '80.82.65.202', '2021-09-11 21:06:46', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (199, '100000005', 'a9b46385', '39.144.10.175', '2021-09-11 22:41:35', 34, 272, ' ', 27904);
INSERT INTO `sys_monitor_receive_log` VALUES (200, '100000005', 'ea4caaa9', '39.144.2.143', '2021-09-11 23:25:11', 10, 80, ' ', 27912);
INSERT INTO `sys_monitor_receive_log` VALUES (201, '100000005', 'a4f0ca74', '39.144.1.200', '2021-09-12 00:05:46', 40, 320, ' ', 27944);
INSERT INTO `sys_monitor_receive_log` VALUES (202, '100000005', '6cbb3267', '117.132.192.145', '2021-09-12 00:13:28', 3, 24, '北京市 北京市', 27952);
INSERT INTO `sys_monitor_receive_log` VALUES (203, '100000005', '8276069d', '39.144.11.245', '2021-09-12 00:21:21', 9, 72, ' ', 27960);
INSERT INTO `sys_monitor_receive_log` VALUES (204, '100000005', '4a480fbd', '39.144.9.167', '2021-09-12 00:42:04', 39, 312, ' ', 28000);
INSERT INTO `sys_monitor_receive_log` VALUES (205, '100000005', 'e6189d50', '39.144.8.206', '2021-09-12 01:22:15', 29, 232, ' ', 28024);
INSERT INTO `sys_monitor_receive_log` VALUES (206, '100000005', '0f0000b1', '221.178.126.154', '2021-09-12 01:29:35', 1, 8, '重庆市 重庆市', 28032);
INSERT INTO `sys_monitor_receive_log` VALUES (207, '100000005', 'a6326f3e', '221.178.124.9', '2021-09-12 01:42:20', 35, 280, '重庆市 重庆市', 28056);
INSERT INTO `sys_monitor_receive_log` VALUES (208, '100000005', 'a536ec36', '221.178.125.6', '2021-09-12 02:33:57', 10, 80, '重庆市 重庆市', 28072);
INSERT INTO `sys_monitor_receive_log` VALUES (209, '100000005', 'a882f9ae', '117.132.196.38', '2021-09-12 02:46:26', 40, 320, '北京市 北京市', 28096);
INSERT INTO `sys_monitor_receive_log` VALUES (210, '100000005', 'b4325875', '39.144.17.217', '2021-09-12 03:22:39', 40, 320, ' ', 28120);
INSERT INTO `sys_monitor_receive_log` VALUES (211, '100000005', 'd18a4c1c', '117.132.193.233', '2021-09-12 05:07:00', 40, 320, '北京市 北京市', 28152);
INSERT INTO `sys_monitor_receive_log` VALUES (212, '100000005', 'cb23c30c', '39.144.10.149', '2021-09-12 05:35:19', 32, 256, ' ', 28168);
INSERT INTO `sys_monitor_receive_log` VALUES (213, '100000005', 'bafa363c', '223.104.254.157', '2021-09-12 05:43:20', 18, 144, '北京市 北京市', 28176);
INSERT INTO `sys_monitor_receive_log` VALUES (214, '100000005', '90a3da41', '117.132.196.174', '2021-09-12 05:55:25', 13, 104, '北京市 北京市', 28184);
INSERT INTO `sys_monitor_receive_log` VALUES (215, '100000005', '21fd2f29', '39.144.2.70', '2021-09-12 06:11:29', 33, 264, ' ', 28208);
INSERT INTO `sys_monitor_receive_log` VALUES (216, '100000005', '2ecca9e0', '221.178.126.199', '2021-09-12 06:27:08', 9, 72, '重庆市 重庆市', 28216);
INSERT INTO `sys_monitor_receive_log` VALUES (217, '100000005', 'c231c358', '39.144.7.140', '2021-09-12 06:39:31', 31, 248, ' ', 28232);
INSERT INTO `sys_monitor_receive_log` VALUES (218, '100000005', 'd4d12a12', '117.132.195.47', '2021-09-12 06:59:09', 10, 80, '北京市 北京市', 28248);
INSERT INTO `sys_monitor_receive_log` VALUES (219, '100000071', 'e96a75cb', '47.103.33.128', '2021-09-12 07:14:04', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (220, '100000003', '4085faa5', '47.103.33.128', '2021-09-12 07:14:41', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (221, '100000108', 'b7f6bca4', '47.103.33.128', '2021-09-12 07:14:58', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (222, '100000071', '9790f8e2', '47.103.33.128', '2021-09-12 07:15:02', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (223, '100000003', 'a98b26ee', '47.103.33.128', '2021-09-12 07:15:28', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (224, '100000005', '463fc728', '47.103.33.128', '2021-09-12 07:16:11', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (225, '100000018', 'a1634a4e', '47.103.33.128', '2021-09-12 07:16:19', 1, 8, '上海市 上海市', 8);
INSERT INTO `sys_monitor_receive_log` VALUES (226, '100000067', '5d1798e3', '47.103.33.128', '2021-09-12 07:16:44', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (227, '100000018', 'c32115f4', '47.103.33.128', '2021-09-12 07:16:53', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (228, '100000071', 'f964f3d6', '47.103.33.128', '2021-09-12 07:17:23', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (229, '100000071', '35c9e302', '47.103.33.128', '2021-09-12 07:18:05', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (230, '100000071', 'c405e4c5', '47.103.33.128', '2021-09-12 07:18:18', 1, 8, '上海市 上海市', 8);
INSERT INTO `sys_monitor_receive_log` VALUES (231, '100000067', '70e14473', '47.103.33.128', '2021-09-12 07:18:26', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (232, '100000005', '360c3237', '39.144.16.91', '2021-09-12 07:31:49', 26, 208, ' ', 28264);
INSERT INTO `sys_monitor_receive_log` VALUES (233, '100000005', 'c579cca0', '39.144.11.183', '2021-09-12 07:43:47', 19, 152, ' ', 28280);
INSERT INTO `sys_monitor_receive_log` VALUES (234, '100000005', '8d1b691a', '39.144.5.154', '2021-09-12 07:59:46', 38, 304, ' ', 28312);
INSERT INTO `sys_monitor_receive_log` VALUES (235, '100000005', 'af1e1f74', '117.132.194.133', '2021-09-12 08:07:20', 1, 8, '北京市 北京市', 28320);
INSERT INTO `sys_monitor_receive_log` VALUES (236, '100000005', '963d0489', '39.144.2.139', '2021-09-12 08:11:32', 1, 8, ' ', 28328);
INSERT INTO `sys_monitor_receive_log` VALUES (237, '100000005', '980c1ed8', '221.178.126.102', '2021-09-12 08:15:51', 33, 264, '重庆市 重庆市', 28352);
INSERT INTO `sys_monitor_receive_log` VALUES (238, '100000005', '43c4ac94', '117.132.196.78', '2021-09-12 08:19:24', 1, 8, '北京市 北京市', 28360);
INSERT INTO `sys_monitor_receive_log` VALUES (239, '100000005', 'f780686d', '39.144.2.86', '2021-09-12 08:55:35', 4, 32, ' ', 28368);
INSERT INTO `sys_monitor_receive_log` VALUES (240, '100000005', 'f51bdd66', '39.144.16.213', '2021-09-12 09:16:13', 33, 264, ' ', 28392);
INSERT INTO `sys_monitor_receive_log` VALUES (241, '100000005', '0b0a6964', '117.132.191.76', '2021-09-12 09:24:14', 40, 320, '北京市 北京市', 28424);
INSERT INTO `sys_monitor_receive_log` VALUES (242, '100000005', 'bde7d3a0', '223.104.255.76', '2021-09-12 09:27:53', 9, 72, '广东省 广州市', 28440);
INSERT INTO `sys_monitor_receive_log` VALUES (243, '100000005', '3a99b773', '223.104.255.139', '2021-09-12 09:36:15', 38, 304, '广东省 广州市', 28464);
INSERT INTO `sys_monitor_receive_log` VALUES (244, '100000005', '135c9c9e', '39.144.4.119', '2021-09-12 10:40:10', 20, 160, ' ', 28480);
INSERT INTO `sys_monitor_receive_log` VALUES (245, '100000005', 'a66555a1', '117.132.191.238', '2021-09-12 10:43:57', 9, 72, '北京市 北京市', 28496);
INSERT INTO `sys_monitor_receive_log` VALUES (246, '100000005', '6af7607c', '39.144.13.201', '2021-09-12 10:56:40', 33, 264, ' ', 28520);
INSERT INTO `sys_monitor_receive_log` VALUES (247, '100000005', '64110dd3', '39.144.10.239', '2021-09-12 11:48:44', 41, 320, ' ', 28544);
INSERT INTO `sys_monitor_receive_log` VALUES (248, '100000005', 'ca4a1829', '39.144.9.54', '2021-09-12 12:08:53', 39, 312, ' ', 28568);
INSERT INTO `sys_monitor_receive_log` VALUES (249, '100000005', 'c884ee3c', '39.144.5.245', '2021-09-12 13:08:35', 9, 72, ' ', 28584);
INSERT INTO `sys_monitor_receive_log` VALUES (250, '100000005', '4b06f3f4', '117.132.196.138', '2021-09-12 13:37:08', 40, 320, '北京市 北京市', 28608);
INSERT INTO `sys_monitor_receive_log` VALUES (251, '100000005', 'ab5ce36f', '218.204.253.210', '2021-09-12 13:40:40', 8, 64, '广东省 广州市', 28616);
INSERT INTO `sys_monitor_receive_log` VALUES (252, '100000005', 'daa0fbc4', '221.178.124.242', '2021-09-12 15:01:30', 40, 320, '重庆市 重庆市', 28648);
INSERT INTO `sys_monitor_receive_log` VALUES (253, '100000005', '58989820', '117.132.198.242', '2021-09-12 15:13:33', 40, 320, '北京市 北京市', 28672);
INSERT INTO `sys_monitor_receive_log` VALUES (254, '100000005', '95272576', '39.144.16.223', '2021-09-12 15:17:35', 40, 320, ' ', 28704);
INSERT INTO `sys_monitor_receive_log` VALUES (255, '100000005', '1a68cb03', '39.144.8.25', '2021-09-12 15:25:16', 18, 144, ' ', 28720);
INSERT INTO `sys_monitor_receive_log` VALUES (256, '100000005', 'b81a96af', '223.104.255.254', '2021-09-12 15:41:49', 32, 256, '广东省 广州市', 28736);
INSERT INTO `sys_monitor_receive_log` VALUES (257, '100000005', '13ffcd5a', '39.144.8.81', '2021-09-12 16:09:56', 32, 256, ' ', 28752);
INSERT INTO `sys_monitor_receive_log` VALUES (258, '100000005', '2d6b0605', '117.132.196.215', '2021-09-12 16:13:35', 24, 192, '北京市 北京市', 28768);
INSERT INTO `sys_monitor_receive_log` VALUES (259, '100000005', '31a690ae', '39.144.13.140', '2021-09-12 17:05:39', 12, 96, ' ', 28784);
INSERT INTO `sys_monitor_receive_log` VALUES (260, '100000005', 'dc60a5e9', '39.144.7.104', '2021-09-12 17:13:38', 3, 24, ' ', 28792);
INSERT INTO `sys_monitor_receive_log` VALUES (261, '100000005', '5d7b2b70', '117.132.198.213', '2021-09-12 17:21:39', 2, 16, '北京市 北京市', 28800);
INSERT INTO `sys_monitor_receive_log` VALUES (262, '100000005', '5813aa09', '39.144.6.236', '2021-09-12 17:25:39', 9, 72, ' ', 28808);
INSERT INTO `sys_monitor_receive_log` VALUES (263, '100000005', '3d160b0d', '39.144.12.158', '2021-09-12 17:45:56', 17, 136, ' ', 28816);
INSERT INTO `sys_monitor_receive_log` VALUES (264, '100000005', '47e7027e', '39.144.3.180', '2021-09-12 18:01:56', 12, 96, ' ', 28832);
INSERT INTO `sys_monitor_receive_log` VALUES (265, '100000005', '03a599d3', '221.178.124.218', '2021-09-12 18:05:50', 11, 88, '重庆市 重庆市', 28848);
INSERT INTO `sys_monitor_receive_log` VALUES (266, '100000005', '99937928', '39.144.13.190', '2021-09-12 18:25:57', 15, 120, ' ', 28864);
INSERT INTO `sys_monitor_receive_log` VALUES (267, '100000005', 'e0e3c4c7', '117.132.197.59', '2021-09-12 18:38:19', 12, 96, '北京市 北京市', 28880);
INSERT INTO `sys_monitor_receive_log` VALUES (268, '100000005', '8b4156ce', '117.132.194.42', '2021-09-12 19:14:06', 8, 64, '北京市 北京市', 28888);
INSERT INTO `sys_monitor_receive_log` VALUES (269, '100000005', 'a04e63d9', '39.144.18.204', '2021-09-12 20:06:10', 1, 8, ' ', 28896);
INSERT INTO `sys_monitor_receive_log` VALUES (270, '100000005', '0c5cd0d6', '117.132.196.118', '2021-09-12 20:34:36', 1, 8, '北京市 北京市', 28904);
INSERT INTO `sys_monitor_receive_log` VALUES (271, '100000005', '0e76127b', '117.132.194.244', '2021-09-12 21:14:36', 1, 8, '北京市 北京市', 28912);
INSERT INTO `sys_monitor_receive_log` VALUES (272, '100000005', 'd111803e', '221.178.126.78', '2021-09-12 21:43:03', 9, 72, '重庆市 重庆市', 28920);
INSERT INTO `sys_monitor_receive_log` VALUES (273, '100000005', '116b63ad', '221.178.127.209', '2021-09-12 21:50:59', 19, 152, '重庆市 重庆市', 28936);
INSERT INTO `sys_monitor_receive_log` VALUES (274, '100000005', 'b66582f9', '117.132.193.120', '2021-09-12 22:02:57', 1, 8, '北京市 北京市', 28944);
INSERT INTO `sys_monitor_receive_log` VALUES (275, '100000005', '2c536e60', '39.144.3.114', '2021-09-12 23:03:14', 1, 8, ' ', 28952);
INSERT INTO `sys_monitor_receive_log` VALUES (276, '100000005', '8dcfc0a3', '117.132.192.120', '2021-09-12 23:31:30', 4, 32, '北京市 北京市', 28960);
INSERT INTO `sys_monitor_receive_log` VALUES (277, '100000005', '383b07b8', '117.132.192.76', '2021-09-12 23:35:24', 13, 104, '北京市 北京市', 28968);
INSERT INTO `sys_monitor_receive_log` VALUES (278, '100000005', '5cf43b7d', '39.144.2.110', '2021-09-13 00:55:40', 7, 56, ' ', 28984);
INSERT INTO `sys_monitor_receive_log` VALUES (279, '100000005', '3e9cf268', '39.144.8.191', '2021-09-13 01:19:49', 1, 8, ' ', 28992);
INSERT INTO `sys_monitor_receive_log` VALUES (280, '100000071', 'ab59bf52', '101.133.224.74', '2021-09-13 01:59:01', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (281, '100000003', 'e615a5c8', '101.133.224.74', '2021-09-13 01:59:36', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (282, '100000108', '109cf67f', '101.133.224.74', '2021-09-13 01:59:50', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (283, '100000071', 'ed46d79b', '101.133.224.74', '2021-09-13 01:59:55', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (284, '100000003', '01df2a44', '101.133.224.74', '2021-09-13 02:00:20', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (285, '100000005', '1cbca825', '101.133.224.74', '2021-09-13 02:01:03', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (286, '100000018', '711441f7', '101.133.224.74', '2021-09-13 02:01:11', 1, 8, '北京市 北京市', 16);
INSERT INTO `sys_monitor_receive_log` VALUES (287, '100000067', '98ea0d35', '101.133.224.74', '2021-09-13 02:01:37', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (288, '100000018', '937f9d35', '101.133.224.74', '2021-09-13 02:01:45', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (289, '100000071', '0c04fd7c', '101.133.224.74', '2021-09-13 02:02:15', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (290, '100000071', '3c8e6d82', '101.133.224.74', '2021-09-13 02:02:57', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (291, '100000071', 'a9551873', '101.133.224.74', '2021-09-13 02:03:10', 1, 8, '北京市 北京市', 16);
INSERT INTO `sys_monitor_receive_log` VALUES (292, '100000067', 'f487a950', '101.133.224.74', '2021-09-13 02:03:19', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (293, '100000005', '6e54d48d', '117.132.196.66', '2021-09-13 02:41:42', 13, 104, '北京市 北京市', 29008);
INSERT INTO `sys_monitor_receive_log` VALUES (294, '100000005', '78f2e3eb', '218.204.252.74', '2021-09-13 03:18:15', 40, 320, '广东省 广州市', 29032);
INSERT INTO `sys_monitor_receive_log` VALUES (295, '100000005', '96c3a0c0', '39.144.11.77', '2021-09-13 03:42:21', 40, 320, ' ', 29069);
INSERT INTO `sys_monitor_receive_log` VALUES (296, '100000005', '671b2c19', '39.144.1.200', '2021-09-13 03:50:04', 18, 144, ' ', 29085);
INSERT INTO `sys_monitor_receive_log` VALUES (297, '100000005', '988131cf', '39.144.7.163', '2021-09-13 04:34:34', 40, 320, ' ', 29109);
INSERT INTO `sys_monitor_receive_log` VALUES (298, '100000005', 'a33891f8', '39.144.8.139', '2021-09-13 05:13:29', 40, 320, ' ', 29133);
INSERT INTO `sys_monitor_receive_log` VALUES (299, '100000005', '19f452fb', '117.132.197.240', '2021-09-13 06:01:07', 2, 16, '北京市 北京市', 29141);
INSERT INTO `sys_monitor_receive_log` VALUES (300, '100000005', 'e1a1d26d', '39.144.1.201', '2021-09-13 06:09:41', 40, 320, ' ', 29165);
INSERT INTO `sys_monitor_receive_log` VALUES (301, '100000005', '595eef57', '39.144.11.109', '2021-09-13 06:17:26', 20, 160, ' ', 29181);
INSERT INTO `sys_monitor_receive_log` VALUES (302, '100000005', 'a5b1c0bb', '39.144.17.199', '2021-09-13 08:01:52', 23, 184, ' ', 29197);
INSERT INTO `sys_monitor_receive_log` VALUES (303, '100000005', '7c37c897', '39.144.10.120', '2021-09-13 08:38:16', 40, 320, ' ', 29221);
INSERT INTO `sys_monitor_receive_log` VALUES (304, '100000005', 'f0e954ff', '39.144.1.61', '2021-09-13 08:42:16', 40, 320, ' ', 29237);
INSERT INTO `sys_monitor_receive_log` VALUES (305, '100000005', '6367d023', '39.144.10.242', '2021-09-13 08:50:28', 35, 280, ' ', 29261);
INSERT INTO `sys_monitor_receive_log` VALUES (306, '100000005', '6cde8b73', '117.132.195.220', '2021-09-13 09:38:30', 40, 320, '北京市 北京市', 29285);
INSERT INTO `sys_monitor_receive_log` VALUES (307, '100000005', '71879117', '39.144.6.80', '2021-09-13 11:27:07', 35, 280, ' ', 29301);
INSERT INTO `sys_monitor_receive_log` VALUES (308, '100000005', '7638c542', '223.104.255.187', '2021-09-13 12:07:09', 40, 320, '广东省 广州市', 29325);
INSERT INTO `sys_monitor_receive_log` VALUES (309, '100000071', '28326ac8', '139.59.14.152', '2021-09-13 12:18:08', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (310, '100000005', '35b23a76', '39.144.13.18', '2021-09-13 16:20:20', 33, 264, ' ', 29349);
INSERT INTO `sys_monitor_receive_log` VALUES (311, '100000005', '552eb226', '39.144.5.160', '2021-09-13 22:49:57', 32, 256, ' ', 29365);
INSERT INTO `sys_monitor_receive_log` VALUES (312, '100000005', '03278745', '117.132.191.251', '2021-09-13 23:13:53', 40, 320, '北京市 北京市', 29389);
INSERT INTO `sys_monitor_receive_log` VALUES (313, '100000005', 'e458082b', '39.144.10.20', '2021-09-13 23:25:57', 40, 320, ' ', 29413);
INSERT INTO `sys_monitor_receive_log` VALUES (314, '100000005', '3a8c2f0a', '39.144.17.144', '2021-09-14 00:26:12', 40, 320, ' ', 29437);
INSERT INTO `sys_monitor_receive_log` VALUES (315, '100000005', '3492cd1c', '117.132.196.213', '2021-09-14 01:54:05', 7, 56, '北京市 北京市', 29445);
INSERT INTO `sys_monitor_receive_log` VALUES (316, '100000005', '37d7fdaa', '39.144.4.188', '2021-09-14 02:22:26', 20, 160, ' ', 29461);
INSERT INTO `sys_monitor_receive_log` VALUES (317, '100000005', '8b6eb199', '221.178.125.193', '2021-09-14 02:30:12', 1, 8, '重庆市 重庆市', 29469);
INSERT INTO `sys_monitor_receive_log` VALUES (318, '100000005', '5e296e7b', '223.104.255.254', '2021-09-14 02:34:21', 14, 112, '广东省 广州市', 29485);
INSERT INTO `sys_monitor_receive_log` VALUES (319, '100000005', '46433286', '39.144.7.252', '2021-09-14 03:02:21', 8, 64, ' ', 29493);
INSERT INTO `sys_monitor_receive_log` VALUES (320, '100000005', '1e1cfb22', '223.104.254.43', '2021-09-14 03:31:00', 40, 320, '北京市 北京市', 29509);
INSERT INTO `sys_monitor_receive_log` VALUES (321, '100000005', '6ba3cf03', '39.144.2.253', '2021-09-14 04:25:58', 5, 40, ' ', 29517);
INSERT INTO `sys_monitor_receive_log` VALUES (322, '100000005', '065129a5', '117.132.194.222', '2021-09-14 04:42:06', 9, 72, '北京市 北京市', 29541);
INSERT INTO `sys_monitor_receive_log` VALUES (323, '100000005', '12784d79', '39.144.5.120', '2021-09-14 05:06:40', 41, 312, ' ', 29565);
INSERT INTO `sys_monitor_receive_log` VALUES (324, '100000005', 'd6481ab6', '39.144.17.89', '2021-09-14 05:30:45', 40, 320, ' ', 29589);
INSERT INTO `sys_monitor_receive_log` VALUES (325, '100000005', '7447745d', '117.132.196.236', '2021-09-14 05:50:17', 4, 32, '北京市 北京市', 29597);
INSERT INTO `sys_monitor_receive_log` VALUES (326, '100000005', '867de3a9', '117.132.193.113', '2021-09-14 06:02:57', 40, 320, '北京市 北京市', 29621);
INSERT INTO `sys_monitor_receive_log` VALUES (327, '100000005', 'dfd3a857', '39.144.2.197', '2021-09-14 06:26:27', 5, 40, ' ', 29629);
INSERT INTO `sys_monitor_receive_log` VALUES (328, '100000005', '289632ac', '39.144.17.55', '2021-09-14 06:55:07', 40, 320, ' ', 29653);
INSERT INTO `sys_monitor_receive_log` VALUES (329, '100000005', '6c7ea70a', '117.132.196.140', '2021-09-14 07:03:08', 40, 320, '北京市 北京市', 29677);
INSERT INTO `sys_monitor_receive_log` VALUES (330, '100000005', '8c02312a', '221.178.125.98', '2021-09-14 07:11:11', 40, 320, '重庆市 重庆市', 29701);
INSERT INTO `sys_monitor_receive_log` VALUES (331, '100000005', '51f7bdba', '117.132.194.123', '2021-09-14 07:26:46', 10, 80, '北京市 北京市', 29717);
INSERT INTO `sys_monitor_receive_log` VALUES (332, '100000005', '0575315e', '221.178.126.166', '2021-09-14 07:55:34', 33, 264, '重庆市 重庆市', 29741);
INSERT INTO `sys_monitor_receive_log` VALUES (333, '100000005', '996678ec', '39.144.4.188', '2021-09-14 08:43:36', 40, 320, ' ', 29765);
INSERT INTO `sys_monitor_receive_log` VALUES (334, '100000005', '00ff3f83', '223.104.254.82', '2021-09-14 08:51:35', 40, 320, '北京市 北京市', 29797);
INSERT INTO `sys_monitor_receive_log` VALUES (335, '100000005', '7bb75533', '39.144.14.45', '2021-09-14 09:23:43', 40, 320, ' ', 29836);
INSERT INTO `sys_monitor_receive_log` VALUES (336, '100000005', '626ce35d', '39.144.14.191', '2021-09-14 10:40:12', 33, 264, ' ', 29852);
INSERT INTO `sys_monitor_receive_log` VALUES (337, '100000005', 'ab36cf4f', '221.178.126.156', '2021-09-14 12:52:35', 40, 320, '重庆市 重庆市', 29876);
INSERT INTO `sys_monitor_receive_log` VALUES (338, '100000005', '1d8a553f', '39.144.7.10', '2021-09-14 16:57:36', 40, 320, ' ', 29956);
INSERT INTO `sys_monitor_receive_log` VALUES (339, '100000005', '8ffe42c2', '39.144.4.55', '2021-09-14 23:18:49', 15, 120, ' ', 29980);
INSERT INTO `sys_monitor_receive_log` VALUES (340, '100000071', '0c158e87', '162.142.125.128', '2021-09-15 00:28:20', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (341, '100000005', 'f595957b', '39.144.9.91', '2021-09-15 00:46:39', 40, 320, ' ', 30012);
INSERT INTO `sys_monitor_receive_log` VALUES (342, '100000005', '2b0c7f6a', '223.104.255.106', '2021-09-15 00:50:37', 41, 316, '广东省 广州市', 30044);
INSERT INTO `sys_monitor_receive_log` VALUES (343, '100000005', 'b66bad5b', '39.144.12.83', '2021-09-15 01:14:42', 40, 320, ' ', 30092);
INSERT INTO `sys_monitor_receive_log` VALUES (344, '100000005', 'a83d3be3', '223.104.255.105', '2021-09-15 01:18:38', 32, 256, '广东省 广州市', 30116);
INSERT INTO `sys_monitor_receive_log` VALUES (345, '100000005', '4f72a891', '117.132.191.30', '2021-09-15 01:22:47', 40, 320, '北京市 北京市', 30140);
INSERT INTO `sys_monitor_receive_log` VALUES (346, '100000005', '9a0585c9', '221.178.125.59', '2021-09-15 01:38:58', 32, 256, '重庆市 重庆市', 30156);
INSERT INTO `sys_monitor_receive_log` VALUES (347, '100000005', 'f7a41ccf', '223.104.255.251', '2021-09-15 02:11:01', 40, 320, '广东省 广州市', 30188);
INSERT INTO `sys_monitor_receive_log` VALUES (348, '100000005', '36c21716', '39.144.15.120', '2021-09-15 03:35:17', 38, 304, ' ', 30212);
INSERT INTO `sys_monitor_receive_log` VALUES (349, '100000005', '5c34d774', '39.144.18.136', '2021-09-15 03:43:20', 40, 320, ' ', 30236);
INSERT INTO `sys_monitor_receive_log` VALUES (350, '100000005', '33a76709', '117.132.192.251', '2021-09-15 03:46:47', 2, 16, '北京市 北京市', 30244);
INSERT INTO `sys_monitor_receive_log` VALUES (351, '100000005', 'a3082f00', '117.132.192.70', '2021-09-15 03:51:22', 40, 320, '北京市 北京市', 30284);
INSERT INTO `sys_monitor_receive_log` VALUES (352, '100000005', '9e592389', '117.132.198.63', '2021-09-15 04:03:34', 32, 256, '北京市 北京市', 30308);
INSERT INTO `sys_monitor_receive_log` VALUES (353, '100000005', '8e53847c', '39.144.13.184', '2021-09-15 04:30:58', 4, 32, ' ', 30316);
INSERT INTO `sys_monitor_receive_log` VALUES (354, '100000005', '9efec324', '221.178.127.226', '2021-09-15 04:43:38', 39, 312, '重庆市 重庆市', 30340);
INSERT INTO `sys_monitor_receive_log` VALUES (355, '100000005', '7ca2817b', '117.132.197.119', '2021-09-15 06:08:05', 33, 264, '北京市 北京市', 30364);
INSERT INTO `sys_monitor_receive_log` VALUES (356, '100000005', '303adec9', '117.132.192.79', '2021-09-15 06:11:33', 14, 112, '北京市 北京市', 30372);
INSERT INTO `sys_monitor_receive_log` VALUES (357, '100000005', 'a8a63f51', '39.144.1.252', '2021-09-15 06:20:02', 40, 320, ' ', 30396);
INSERT INTO `sys_monitor_receive_log` VALUES (358, '100000005', 'abb78969', '223.104.255.139', '2021-09-15 06:32:03', 40, 320, '广东省 广州市', 30436);
INSERT INTO `sys_monitor_receive_log` VALUES (359, '100000005', 'f8a8374b', '117.132.197.234', '2021-09-15 06:35:42', 18, 144, '北京市 北京市', 30452);
INSERT INTO `sys_monitor_receive_log` VALUES (360, '100000005', 'b5490a74', '39.144.11.168', '2021-09-15 06:43:44', 17, 136, ' ', 30468);
INSERT INTO `sys_monitor_receive_log` VALUES (361, '100000005', '219b3bb7', '117.132.195.188', '2021-09-15 06:48:05', 41, 312, '北京市 北京市', 30492);
INSERT INTO `sys_monitor_receive_log` VALUES (362, '100000005', '31da16f1', '218.204.253.2', '2021-09-15 07:20:25', 29, 232, '广东省 广州市', 30516);
INSERT INTO `sys_monitor_receive_log` VALUES (363, '100000005', '4aa1f359', '39.144.10.228', '2021-09-15 07:44:19', 40, 320, ' ', 30540);
INSERT INTO `sys_monitor_receive_log` VALUES (364, '100000005', 'c0fdfad6', '39.144.7.19', '2021-09-15 08:44:41', 40, 320, ' ', 30564);
INSERT INTO `sys_monitor_receive_log` VALUES (365, '100000005', '6e7d0876', '39.144.8.99', '2021-09-15 08:52:37', 40, 320, ' ', 30588);
INSERT INTO `sys_monitor_receive_log` VALUES (366, '100000005', '510e45f8', '39.144.14.112', '2021-09-15 09:12:07', 1, 8, ' ', 30596);
INSERT INTO `sys_monitor_receive_log` VALUES (367, '100000005', '40d39699', '39.144.10.216', '2021-09-15 09:48:22', 9, 72, ' ', 30604);
INSERT INTO `sys_monitor_receive_log` VALUES (368, '100000005', '475d324d', '117.132.191.241', '2021-09-15 12:09:36', 31, 248, '北京市 北京市', 30628);
INSERT INTO `sys_monitor_receive_log` VALUES (369, '100000005', '04ddbe62', '39.144.7.55', '2021-09-15 12:12:56', 7, 56, ' ', 30644);
INSERT INTO `sys_monitor_receive_log` VALUES (370, '100000005', '9065005f', '218.204.252.131', '2021-09-15 12:25:28', 37, 284, '广东省 广州市', 30668);
INSERT INTO `sys_monitor_receive_log` VALUES (371, '100000005', '719ecadb', '39.144.5.232', '2021-09-15 12:41:34', 40, 320, ' ', 30692);
INSERT INTO `sys_monitor_receive_log` VALUES (372, '100000005', '8a0c6ba0', '39.144.3.84', '2021-09-15 13:17:46', 40, 320, ' ', 30724);
INSERT INTO `sys_monitor_receive_log` VALUES (373, '100000005', '4fdd9be8', '39.144.1.1', '2021-09-15 13:25:13', 1, 8, ' ', 30732);
INSERT INTO `sys_monitor_receive_log` VALUES (374, '100000005', 'c67808d4', '39.144.12.93', '2021-09-15 13:37:19', 9, 72, ' ', 30740);
INSERT INTO `sys_monitor_receive_log` VALUES (375, '100000005', 'db9c073a', '221.178.124.2', '2021-09-15 13:57:36', 21, 168, '重庆市 重庆市', 30764);
INSERT INTO `sys_monitor_receive_log` VALUES (376, '100000005', '78fb8d51', '221.178.127.37', '2021-09-15 14:01:20', 1, 8, '重庆市 重庆市', 30772);
INSERT INTO `sys_monitor_receive_log` VALUES (377, '100000005', '60d91f0d', '117.132.198.56', '2021-09-15 15:22:18', 40, 320, '北京市 北京市', 30796);
INSERT INTO `sys_monitor_receive_log` VALUES (378, '100000005', '8036c879', '221.178.127.136', '2021-09-15 15:29:58', 18, 144, '重庆市 重庆市', 30812);
INSERT INTO `sys_monitor_receive_log` VALUES (379, '100000071', '92eb9cf1', '167.248.133.43', '2021-09-15 15:30:58', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (380, '100000005', '05691ea5', '218.204.253.108', '2021-09-15 15:46:29', 27, 216, '广东省 广州市', 30836);
INSERT INTO `sys_monitor_receive_log` VALUES (381, '100000005', '5208f97d', '39.144.13.249', '2021-09-15 15:54:31', 28, 224, ' ', 30860);
INSERT INTO `sys_monitor_receive_log` VALUES (382, '100000005', 'cf8bcd5d', '117.132.191.121', '2021-09-15 15:57:48', 2, 16, '北京市 北京市', 30876);
INSERT INTO `sys_monitor_receive_log` VALUES (383, '100000071', '22f0efde', '125.64.94.138', '2021-09-15 17:25:32', 1, 0, '四川省 德阳市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (384, '100000005', 'a4794d87', '39.144.7.63', '2021-09-15 17:43:01', 34, 272, ' ', 30900);
INSERT INTO `sys_monitor_receive_log` VALUES (385, '100000071', 'ecbef102', '123.160.221.19', '2021-09-15 19:22:12', 1, 0, '河南省 郑州市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (386, '100000003', 'fe9f636b', '123.160.221.19', '2021-09-15 19:22:20', 1, 0, '河南省 郑州市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (387, '100000071', '9df029d5', '123.160.221.19', '2021-09-15 19:22:49', 1, 0, '河南省 郑州市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (388, '100000005', '1f7317ab', '39.144.16.94', '2021-09-15 22:20:10', 33, 264, ' ', 30924);
INSERT INTO `sys_monitor_receive_log` VALUES (389, '100000005', 'a28176e1', '223.104.254.175', '2021-09-15 22:48:14', 30, 240, '北京市 北京市', 30940);
INSERT INTO `sys_monitor_receive_log` VALUES (390, '100000005', '60668c0f', '39.144.7.192', '2021-09-16 00:52:28', 29, 232, ' ', 30964);
INSERT INTO `sys_monitor_receive_log` VALUES (391, '100000005', 'bdf4d40c', '39.144.11.234', '2021-09-16 01:00:50', 31, 248, ' ', 30988);
INSERT INTO `sys_monitor_receive_log` VALUES (392, '100000005', 'd6ce048b', '39.144.5.248', '2021-09-16 01:08:39', 40, 320, ' ', 31028);
INSERT INTO `sys_monitor_receive_log` VALUES (393, '100000005', '2ff7713e', '117.132.195.173', '2021-09-16 02:24:49', 24, 192, '北京市 北京市', 31052);
INSERT INTO `sys_monitor_receive_log` VALUES (394, '100000005', '48416f70', '221.178.126.187', '2021-09-16 02:37:02', 40, 320, '重庆市 重庆市', 31076);
INSERT INTO `sys_monitor_receive_log` VALUES (395, '100000003', '21ed0363', '45.155.205.127', '2021-09-16 02:38:42', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (396, '100000005', '836db97c', '117.132.192.27', '2021-09-16 03:01:20', 32, 256, '北京市 北京市', 31100);
INSERT INTO `sys_monitor_receive_log` VALUES (397, '100000005', 'c2502bf8', '39.144.7.20', '2021-09-16 03:21:16', 38, 304, ' ', 31124);
INSERT INTO `sys_monitor_receive_log` VALUES (398, '100000003', '62abe591', '45.155.205.127', '2021-09-16 03:33:28', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (399, '100000005', 'eb3fd266', '117.132.196.210', '2021-09-16 03:53:24', 40, 320, '北京市 北京市', 31148);
INSERT INTO `sys_monitor_receive_log` VALUES (400, '100000005', '627f3d00', '39.144.14.31', '2021-09-16 04:05:26', 37, 296, ' ', 31172);
INSERT INTO `sys_monitor_receive_log` VALUES (401, '100000003', '298422eb', '45.155.205.127', '2021-09-16 04:08:48', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (402, '100000003', 'e4115c57', '45.155.205.127', '2021-09-16 04:55:06', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (403, '100000005', 'b38c6286', '117.132.191.50', '2021-09-16 04:57:39', 40, 320, '北京市 北京市', 31196);
INSERT INTO `sys_monitor_receive_log` VALUES (404, '100000005', 'bd7d02ed', '39.144.8.158', '2021-09-16 05:03:15', 27, 216, ' ', 31212);
INSERT INTO `sys_monitor_receive_log` VALUES (405, '100000005', '8e90929c', '223.104.255.116', '2021-09-16 05:11:30', 38, 304, '广东省 广州市', 31236);
INSERT INTO `sys_monitor_receive_log` VALUES (406, '100000003', '92aa3d83', '78.128.112.14', '2021-09-16 05:21:15', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (407, '100000003', '350e1dba', '193.106.29.74', '2021-09-16 06:15:57', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (408, '100000005', '8202b068', '223.104.255.203', '2021-09-16 06:19:13', 5, 40, '广东省 广州市', 31244);
INSERT INTO `sys_monitor_receive_log` VALUES (409, '100000005', '30be32c7', '39.144.6.240', '2021-09-16 06:23:15', 2, 16, ' ', 31252);
INSERT INTO `sys_monitor_receive_log` VALUES (410, '100000005', '3c0d3ff1', '39.144.5.165', '2021-09-16 06:35:33', 21, 168, ' ', 31276);
INSERT INTO `sys_monitor_receive_log` VALUES (411, '100000005', 'a0e0144c', '117.132.194.221', '2021-09-16 06:39:14', 1, 8, '北京市 北京市', 31284);
INSERT INTO `sys_monitor_receive_log` VALUES (412, '100000005', '83547d96', '39.144.8.38', '2021-09-16 06:47:43', 28, 224, ' ', 31300);
INSERT INTO `sys_monitor_receive_log` VALUES (413, '100000003', 'cfb91e19', '45.155.205.127', '2021-09-16 07:10:29', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (414, '100000005', '5e674ae9', '39.144.4.190', '2021-09-16 07:24:05', 40, 320, ' ', 31372);
INSERT INTO `sys_monitor_receive_log` VALUES (415, '100000005', '801fd50d', '39.144.1.121', '2021-09-16 07:44:05', 40, 320, ' ', 31452);
INSERT INTO `sys_monitor_receive_log` VALUES (416, '100000005', '2a12a310', '117.132.193.13', '2021-09-16 08:00:12', 40, 320, '北京市 北京市', 31516);
INSERT INTO `sys_monitor_receive_log` VALUES (417, '100000005', '8ea3b49b', '39.144.15.41', '2021-09-16 08:20:18', 39, 312, ' ', 31572);
INSERT INTO `sys_monitor_receive_log` VALUES (418, '100000005', '36aa46a8', '117.132.196.46', '2021-09-16 08:44:05', 20, 160, '北京市 北京市', 31596);
INSERT INTO `sys_monitor_receive_log` VALUES (419, '100000005', '46f1f4d3', '39.144.12.166', '2021-09-16 08:52:23', 40, 320, ' ', 31636);
INSERT INTO `sys_monitor_receive_log` VALUES (420, '100000005', '43a3e930', '221.178.124.181', '2021-09-16 09:52:07', 7, 56, '重庆市 重庆市', 31652);
INSERT INTO `sys_monitor_receive_log` VALUES (421, '100000005', '340daa68', '39.144.9.130', '2021-09-16 11:00:56', 40, 320, ' ', 31676);
INSERT INTO `sys_monitor_receive_log` VALUES (422, '100000005', '6e80c851', '223.104.255.243', '2021-09-16 13:29:31', 40, 320, '广东省 广州市', 31716);
INSERT INTO `sys_monitor_receive_log` VALUES (423, '100000005', '1d01847e', '39.144.7.181', '2021-09-16 14:37:48', 40, 320, ' ', 31748);
INSERT INTO `sys_monitor_receive_log` VALUES (424, '100000071', 'cd65b96b', '183.136.225.42', '2021-09-16 15:29:26', 1, 0, '浙江省 嘉兴市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (425, '100000005', 'bd2ca8ba', '117.132.194.218', '2021-09-16 16:26:25', 35, 280, '北京市 北京市', 31772);
INSERT INTO `sys_monitor_receive_log` VALUES (426, '100000005', 'ebc43c7f', '223.104.254.235', '2021-09-16 22:47:54', 40, 320, '北京市 北京市', 31796);
INSERT INTO `sys_monitor_receive_log` VALUES (427, '100000005', 'fe6c2e0a', '223.104.255.227', '2021-09-16 23:26:49', 33, 264, '广东省 广州市', 31812);
INSERT INTO `sys_monitor_receive_log` VALUES (428, '100000005', 'fcea0836', '39.144.15.69', '2021-09-17 00:10:26', 10, 80, ' ', 31828);
INSERT INTO `sys_monitor_receive_log` VALUES (429, '100000005', '80a84fef', '39.144.5.210', '2021-09-17 00:51:00', 40, 320, ' ', 31852);
INSERT INTO `sys_monitor_receive_log` VALUES (430, '100000005', '21ecd12d', '117.132.195.115', '2021-09-17 01:11:11', 28, 224, '北京市 北京市', 31876);
INSERT INTO `sys_monitor_receive_log` VALUES (431, '100000005', '02598857', '39.144.11.202', '2021-09-17 01:15:07', 40, 320, ' ', 31908);
INSERT INTO `sys_monitor_receive_log` VALUES (432, '100000005', '58eb6e3b', '39.144.10.213', '2021-09-17 01:27:07', 40, 320, ' ', 31948);
INSERT INTO `sys_monitor_receive_log` VALUES (433, '100000005', '3194ab78', '39.144.9.213', '2021-09-17 02:19:20', 40, 320, ' ', 31980);
INSERT INTO `sys_monitor_receive_log` VALUES (434, '100000005', '239337e8', '39.144.5.199', '2021-09-17 02:47:27', 40, 320, ' ', 32004);
INSERT INTO `sys_monitor_receive_log` VALUES (435, '100000005', '366d1a55', '117.132.191.180', '2021-09-17 03:07:34', 40, 320, '北京市 北京市', 32028);
INSERT INTO `sys_monitor_receive_log` VALUES (436, '100000005', 'b060beff', '39.144.18.80', '2021-09-17 03:11:22', 27, 216, ' ', 32052);
INSERT INTO `sys_monitor_receive_log` VALUES (437, '100000005', 'fefd2f55', '117.132.196.199', '2021-09-17 03:15:34', 40, 320, '北京市 北京市', 32084);
INSERT INTO `sys_monitor_receive_log` VALUES (438, '100000005', '6609fea0', '39.144.1.44', '2021-09-17 03:47:23', 11, 88, ' ', 32100);
INSERT INTO `sys_monitor_receive_log` VALUES (439, '100000005', '497cd426', '218.204.253.126', '2021-09-17 04:07:14', 1, 8, '广东省 广州市', 32108);
INSERT INTO `sys_monitor_receive_log` VALUES (440, '100000005', '2e2b5a18', '221.178.127.2', '2021-09-17 04:11:33', 23, 184, '重庆市 重庆市', 32124);
INSERT INTO `sys_monitor_receive_log` VALUES (441, '100000005', 'aa4ae146', '221.178.124.108', '2021-09-17 04:23:44', 30, 240, '重庆市 重庆市', 32140);
INSERT INTO `sys_monitor_receive_log` VALUES (442, '100000005', 'ac3a8cbb', '218.204.252.132', '2021-09-17 04:43:58', 38, 304, '广东省 广州市', 32164);
INSERT INTO `sys_monitor_receive_log` VALUES (443, '100000005', '43aaab74', '39.144.9.27', '2021-09-17 04:47:56', 40, 320, ' ', 32188);
INSERT INTO `sys_monitor_receive_log` VALUES (444, '100000005', '843cb46b', '117.132.192.218', '2021-09-17 05:00:00', 40, 320, '北京市 北京市', 32212);
INSERT INTO `sys_monitor_receive_log` VALUES (445, '100000005', '9dacba27', '39.144.14.100', '2021-09-17 05:08:01', 40, 320, ' ', 32236);
INSERT INTO `sys_monitor_receive_log` VALUES (446, '100000005', '6d56bd38', '39.144.12.1', '2021-09-17 05:12:06', 40, 320, ' ', 32260);
INSERT INTO `sys_monitor_receive_log` VALUES (447, '100000005', '86424e4a', '39.144.5.19', '2021-09-17 05:36:09', 40, 320, ' ', 32292);
INSERT INTO `sys_monitor_receive_log` VALUES (448, '100000108', 'ad0e6cf8', '117.132.197.147', '2021-09-17 05:51:44', 5, 20, '北京市 北京市', 24);
INSERT INTO `sys_monitor_receive_log` VALUES (449, '100000108', '9e355a03', '117.132.193.178', '2021-09-17 05:56:48', 5, 20, '北京市 北京市', 28);
INSERT INTO `sys_monitor_receive_log` VALUES (450, '100000108', '1f41676d', '221.178.124.73', '2021-09-17 06:06:50', 5, 20, '重庆市 重庆市', 36);
INSERT INTO `sys_monitor_receive_log` VALUES (451, '100000108', '14c8a469', '221.178.127.98', '2021-09-17 06:11:52', 5, 20, '重庆市 重庆市', 40);
INSERT INTO `sys_monitor_receive_log` VALUES (452, '100000005', '02aeff57', '221.178.124.28', '2021-09-17 06:11:58', 16, 128, '重庆市 重庆市', 32300);
INSERT INTO `sys_monitor_receive_log` VALUES (453, '100000005', 'd85a97ff', '221.178.125.23', '2021-09-17 06:16:16', 14, 112, '重庆市 重庆市', 32316);
INSERT INTO `sys_monitor_receive_log` VALUES (454, '100000108', '0354285e', '223.104.254.213', '2021-09-17 06:21:50', 5, 20, '北京市 北京市', 44);
INSERT INTO `sys_monitor_receive_log` VALUES (455, '100000005', '495e7254', '39.144.5.214', '2021-09-17 06:23:46', 2, 16, ' ', 32324);
INSERT INTO `sys_monitor_receive_log` VALUES (456, '100000005', '06f2e2ab', '117.132.191.89', '2021-09-17 06:32:23', 40, 320, '北京市 北京市', 32348);
INSERT INTO `sys_monitor_receive_log` VALUES (457, '100000005', '115dd632', '117.132.196.8', '2021-09-17 06:40:24', 40, 320, '北京市 北京市', 32372);
INSERT INTO `sys_monitor_receive_log` VALUES (458, '100000108', '153cfa0a', '117.132.194.70', '2021-09-17 06:41:55', 5, 20, '北京市 北京市', 48);
INSERT INTO `sys_monitor_receive_log` VALUES (459, '100000108', '564b067c', '39.144.7.2', '2021-09-17 06:46:57', 5, 20, ' ', 56);
INSERT INTO `sys_monitor_receive_log` VALUES (460, '100000108', 'b59f0261', '39.144.18.128', '2021-09-17 06:51:59', 5, 20, ' ', 64);
INSERT INTO `sys_monitor_receive_log` VALUES (461, '100000005', 'ade66c86', '39.144.2.151', '2021-09-17 06:56:17', 27, 216, ' ', 32396);
INSERT INTO `sys_monitor_receive_log` VALUES (462, '100000108', 'd2cde72c', '39.144.11.176', '2021-09-17 07:07:00', 5, 20, ' ', 68);
INSERT INTO `sys_monitor_receive_log` VALUES (463, '100000108', 'd31d3b43', '39.144.8.64', '2021-09-17 07:12:02', 5, 20, ' ', 76);
INSERT INTO `sys_monitor_receive_log` VALUES (464, '100000005', '2c222fb8', '39.144.15.192', '2021-09-17 07:16:43', 33, 264, ' ', 32420);
INSERT INTO `sys_monitor_receive_log` VALUES (465, '100000108', '407b8134', '221.178.126.206', '2021-09-17 07:17:04', 5, 20, '重庆市 重庆市', 81);
INSERT INTO `sys_monitor_receive_log` VALUES (466, '100000005', 'f63a9128', '39.144.12.228', '2021-09-17 07:20:34', 40, 320, ' ', 32452);
INSERT INTO `sys_monitor_receive_log` VALUES (467, '100000108', '09410ba6', '221.178.125.30', '2021-09-17 07:27:04', 5, 20, '重庆市 重庆市', 85);
INSERT INTO `sys_monitor_receive_log` VALUES (468, '100000108', '22a7aa70', '39.144.10.194', '2021-09-17 07:37:07', 5, 20, ' ', 89);
INSERT INTO `sys_monitor_receive_log` VALUES (469, '100000005', '4cbefbf1', '39.144.11.173', '2021-09-17 07:40:09', 8, 64, ' ', 32468);
INSERT INTO `sys_monitor_receive_log` VALUES (470, '100000108', '1a29fc33', '223.104.255.231', '2021-09-17 07:42:09', 5, 20, '广东省 广州市', 93);
INSERT INTO `sys_monitor_receive_log` VALUES (471, '100000108', '6fbd8525', '39.144.18.174', '2021-09-17 07:47:11', 5, 20, ' ', 97);
INSERT INTO `sys_monitor_receive_log` VALUES (472, '100000108', 'dfcc4064', '117.132.196.25', '2021-09-17 07:52:08', 5, 20, '北京市 北京市', 101);
INSERT INTO `sys_monitor_receive_log` VALUES (473, '100000108', '5adb06ea', '39.144.11.117', '2021-09-17 07:57:11', 5, 20, ' ', 109);
INSERT INTO `sys_monitor_receive_log` VALUES (474, '100000108', '926e61ba', '39.144.12.172', '2021-09-17 08:02:12', 5, 20, ' ', 113);
INSERT INTO `sys_monitor_receive_log` VALUES (475, '100000108', '435cb7a6', '221.178.125.216', '2021-09-17 08:07:15', 5, 20, '重庆市 重庆市', 117);
INSERT INTO `sys_monitor_receive_log` VALUES (476, '100000108', '03ef4fbc', '39.144.16.136', '2021-09-17 08:12:12', 5, 20, ' ', 121);
INSERT INTO `sys_monitor_receive_log` VALUES (477, '100000108', '1ea43daa', '221.178.126.251', '2021-09-17 08:17:15', 5, 20, '重庆市 重庆市', 129);
INSERT INTO `sys_monitor_receive_log` VALUES (478, '100000108', '450d50c8', '39.144.14.112', '2021-09-17 08:22:16', 5, 20, ' ', 137);
INSERT INTO `sys_monitor_receive_log` VALUES (479, '100000108', 'baec42c6', '117.132.194.119', '2021-09-17 08:27:18', 5, 20, '北京市 北京市', 141);
INSERT INTO `sys_monitor_receive_log` VALUES (480, '100000108', '34b5a704', '117.132.195.112', '2021-09-17 08:32:20', 5, 20, '北京市 北京市', 145);
INSERT INTO `sys_monitor_receive_log` VALUES (481, '100000108', '6a85691a', '39.144.9.111', '2021-09-17 08:36:10', 5, 20, ' ', 153);
INSERT INTO `sys_monitor_receive_log` VALUES (482, '100000108', 'cdb81e3c', '117.132.198.94', '2021-09-17 08:40:06', 5, 20, '北京市 北京市', 157);
INSERT INTO `sys_monitor_receive_log` VALUES (483, '100000108', '856eab46', '223.104.255.40', '2021-09-17 08:50:07', 5, 20, '广东省 广州市', 161);
INSERT INTO `sys_monitor_receive_log` VALUES (484, '100000005', 'ff344607', '223.104.254.251', '2021-09-17 09:13:04', 40, 320, '北京市 北京市', 32492);
INSERT INTO `sys_monitor_receive_log` VALUES (485, '100000108', 'd836e12e', '221.178.124.247', '2021-09-17 09:25:14', 5, 20, '重庆市 重庆市', 165);
INSERT INTO `sys_monitor_receive_log` VALUES (486, '100000108', '3eccf43f', '117.132.194.52', '2021-09-17 09:30:16', 5, 20, '北京市 北京市', 169);
INSERT INTO `sys_monitor_receive_log` VALUES (487, '100000108', '5637983a', '39.144.11.38', '2021-09-17 09:35:13', 5, 20, ' ', 173);
INSERT INTO `sys_monitor_receive_log` VALUES (488, '100000108', '81225f17', '221.178.125.192', '2021-09-17 09:45:17', 5, 20, '重庆市 重庆市', 177);
INSERT INTO `sys_monitor_receive_log` VALUES (489, '100000108', '74502038', '39.144.18.100', '2021-09-17 09:50:20', 5, 20, ' ', 181);
INSERT INTO `sys_monitor_receive_log` VALUES (490, '100000108', 'db8b547c', '39.144.8.201', '2021-09-17 09:55:17', 5, 20, ' ', 189);
INSERT INTO `sys_monitor_receive_log` VALUES (491, '100000005', '09722318', '117.132.193.71', '2021-09-17 09:57:13', 40, 320, '北京市 北京市', 32516);
INSERT INTO `sys_monitor_receive_log` VALUES (492, '100000108', '2104e1ab', '223.104.254.147', '2021-09-17 10:00:19', 5, 20, '北京市 北京市', 193);
INSERT INTO `sys_monitor_receive_log` VALUES (493, '100000108', 'ed505ae1', '39.144.16.63', '2021-09-17 10:05:21', 5, 20, ' ', 197);
INSERT INTO `sys_monitor_receive_log` VALUES (494, '100000108', '32243301', '221.178.124.142', '2021-09-17 10:10:23', 5, 20, '重庆市 重庆市', 201);
INSERT INTO `sys_monitor_receive_log` VALUES (495, '100000108', '0d8b3c61', '117.132.191.77', '2021-09-17 10:15:25', 5, 20, '北京市 北京市', 205);
INSERT INTO `sys_monitor_receive_log` VALUES (496, '100000108', '346d8e6c', '117.132.194.171', '2021-09-17 10:20:22', 5, 20, '北京市 北京市', 209);
INSERT INTO `sys_monitor_receive_log` VALUES (497, '100000005', 'e728e523', '117.132.196.227', '2021-09-17 10:21:29', 35, 280, '北京市 北京市', 32540);
INSERT INTO `sys_monitor_receive_log` VALUES (498, '100000108', '9eb4b285', '117.132.197.17', '2021-09-17 10:25:24', 5, 20, '北京市 北京市', 213);
INSERT INTO `sys_monitor_receive_log` VALUES (499, '100000108', '55254470', '223.104.255.247', '2021-09-17 10:35:29', 5, 20, '广东省 广州市', 217);
INSERT INTO `sys_monitor_receive_log` VALUES (500, '100000108', '0636358f', '39.144.1.28', '2021-09-17 10:40:30', 5, 20, ' ', 221);
INSERT INTO `sys_monitor_receive_log` VALUES (501, '100000108', 'ef562b35', '39.144.3.42', '2021-09-17 10:45:27', 5, 20, ' ', 225);
INSERT INTO `sys_monitor_receive_log` VALUES (502, '100000108', '0850363c', '218.204.253.92', '2021-09-17 10:50:29', 5, 20, '广东省 广州市', 229);
INSERT INTO `sys_monitor_receive_log` VALUES (503, '100000108', '4f8fe73b', '117.132.192.95', '2021-09-17 10:55:31', 5, 20, '北京市 北京市', 233);
INSERT INTO `sys_monitor_receive_log` VALUES (504, '100000108', '367facd8', '39.144.9.95', '2021-09-17 11:00:33', 5, 20, ' ', 237);
INSERT INTO `sys_monitor_receive_log` VALUES (505, '100000108', '9e75b016', '117.132.193.25', '2021-09-17 11:10:32', 5, 20, '北京市 北京市', 241);
INSERT INTO `sys_monitor_receive_log` VALUES (506, '100000108', '62f2b790', '221.178.126.121', '2021-09-17 11:15:34', 5, 20, '重庆市 重庆市', 249);
INSERT INTO `sys_monitor_receive_log` VALUES (507, '100000108', 'd4adddb2', '223.104.254.102', '2021-09-17 11:20:36', 5, 20, '北京市 北京市', 253);
INSERT INTO `sys_monitor_receive_log` VALUES (508, '100000108', '9e397739', '39.144.14.47', '2021-09-17 11:25:39', 5, 20, ' ', 261);
INSERT INTO `sys_monitor_receive_log` VALUES (509, '100000108', '51a5887d', '39.144.3.24', '2021-09-17 11:35:38', 5, 20, ' ', 269);
INSERT INTO `sys_monitor_receive_log` VALUES (510, '100000108', 'c406b10c', '39.144.16.76', '2021-09-17 11:45:44', 5, 20, ' ', 277);
INSERT INTO `sys_monitor_receive_log` VALUES (511, '100000108', '7813cd18', '39.144.8.210', '2021-09-17 11:50:44', 5, 20, ' ', 281);
INSERT INTO `sys_monitor_receive_log` VALUES (512, '100000005', 'bd4b5bfa', '117.132.193.178', '2021-09-17 11:57:43', 40, 320, '北京市 北京市', 32564);
INSERT INTO `sys_monitor_receive_log` VALUES (513, '100000108', '87481921', '39.144.17.214', '2021-09-17 12:00:43', 5, 20, ' ', 285);
INSERT INTO `sys_monitor_receive_log` VALUES (514, '100000005', '2f8c0390', '221.178.124.189', '2021-09-17 12:09:49', 40, 320, '重庆市 重庆市', 32588);
INSERT INTO `sys_monitor_receive_log` VALUES (515, '100000108', 'a9b6d0da', '39.144.18.171', '2021-09-17 12:10:47', 5, 20, ' ', 293);
INSERT INTO `sys_monitor_receive_log` VALUES (516, '100000108', '8758a8e2', '117.132.191.24', '2021-09-17 12:20:46', 5, 20, '北京市 北京市', 301);
INSERT INTO `sys_monitor_receive_log` VALUES (517, '100000108', '2af81a13', '223.104.255.151', '2021-09-17 12:30:50', 5, 20, '广东省 广州市', 305);
INSERT INTO `sys_monitor_receive_log` VALUES (518, '100000005', '108ecf00', '39.144.1.51', '2021-09-17 12:33:52', 40, 320, ' ', 32620);
INSERT INTO `sys_monitor_receive_log` VALUES (519, '100000108', '1bc569fc', '39.144.14.137', '2021-09-17 12:35:52', 5, 20, ' ', 309);
INSERT INTO `sys_monitor_receive_log` VALUES (520, '100000108', '7173a8f5', '39.144.9.90', '2021-09-17 12:40:50', 5, 20, ' ', 313);
INSERT INTO `sys_monitor_receive_log` VALUES (521, '100000108', 'f93da813', '39.144.9.83', '2021-09-17 12:45:51', 5, 20, ' ', 317);
INSERT INTO `sys_monitor_receive_log` VALUES (522, '100000108', '6eca84ef', '39.144.15.116', '2021-09-17 12:50:53', 5, 20, ' ', 325);
INSERT INTO `sys_monitor_receive_log` VALUES (523, '100000108', 'd8000828', '221.178.126.221', '2021-09-17 12:55:57', 5, 20, '重庆市 重庆市', 329);
INSERT INTO `sys_monitor_receive_log` VALUES (524, '100000108', '3bf730ef', '117.132.197.95', '2021-09-17 13:05:55', 5, 20, '北京市 北京市', 333);
INSERT INTO `sys_monitor_receive_log` VALUES (525, '100000108', 'a9736092', '221.178.125.208', '2021-09-17 13:16:02', 5, 20, '重庆市 重庆市', 337);
INSERT INTO `sys_monitor_receive_log` VALUES (526, '100000108', 'bdbd10e5', '218.204.252.5', '2021-09-17 13:21:01', 5, 20, '广东省 广州市', 341);
INSERT INTO `sys_monitor_receive_log` VALUES (527, '100000108', '3ede7205', '39.144.16.129', '2021-09-17 13:31:00', 5, 20, ' ', 349);
INSERT INTO `sys_monitor_receive_log` VALUES (528, '100000108', 'e02ac91d', '39.144.10.248', '2021-09-17 13:36:02', 5, 20, ' ', 357);
INSERT INTO `sys_monitor_receive_log` VALUES (529, '100000108', 'cf44442f', '218.204.252.200', '2021-09-17 13:41:05', 5, 20, '广东省 广州市', 365);
INSERT INTO `sys_monitor_receive_log` VALUES (530, '100000108', '9c9e3437', '117.132.195.23', '2021-09-17 13:46:08', 5, 20, '北京市 北京市', 373);
INSERT INTO `sys_monitor_receive_log` VALUES (531, '100000003', '2346a17b', '89.248.165.89', '2021-09-17 23:49:20', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (532, '100000005', 'b21df015', '39.144.14.88', '2021-09-18 04:17:48', 40, 320, ' ', 32940);
INSERT INTO `sys_monitor_receive_log` VALUES (533, '100000005', 'fc1aba9b', '39.144.9.102', '2021-09-18 04:33:21', 6, 48, ' ', 32948);
INSERT INTO `sys_monitor_receive_log` VALUES (534, '100000005', 'cbcfb206', '39.144.13.67', '2021-09-18 05:14:10', 40, 320, ' ', 32972);
INSERT INTO `sys_monitor_receive_log` VALUES (535, '100000005', '11105476', '117.132.194.226', '2021-09-18 07:06:30', 40, 320, '北京市 北京市', 32996);
INSERT INTO `sys_monitor_receive_log` VALUES (536, '100000005', '34140372', '39.144.17.41', '2021-09-18 07:26:13', 16, 128, ' ', 33012);
INSERT INTO `sys_monitor_receive_log` VALUES (537, '100000005', '49c76ea0', '218.204.252.67', '2021-09-18 07:46:39', 41, 316, '广东省 广州市', 33036);
INSERT INTO `sys_monitor_receive_log` VALUES (538, '100000005', '92216fab', '39.144.9.84', '2021-09-18 08:06:12', 3, 24, ' ', 33044);
INSERT INTO `sys_monitor_receive_log` VALUES (539, '100000005', 'b6c86774', '221.178.127.169', '2021-09-18 08:10:46', 40, 320, '重庆市 重庆市', 33060);
INSERT INTO `sys_monitor_receive_log` VALUES (540, '100000005', '15627a1d', '39.144.6.205', '2021-09-18 08:14:17', 6, 48, ' ', 33068);
INSERT INTO `sys_monitor_receive_log` VALUES (541, '100000005', '20ced679', '39.144.8.217', '2021-09-18 08:26:24', 12, 96, ' ', 33084);
INSERT INTO `sys_monitor_receive_log` VALUES (542, '100000005', '110a9bba', '223.104.255.79', '2021-09-18 08:30:50', 40, 320, '广东省 广州市', 33108);
INSERT INTO `sys_monitor_receive_log` VALUES (543, '100000005', '09351101', '117.132.195.244', '2021-09-18 08:42:55', 40, 320, '北京市 北京市', 33140);
INSERT INTO `sys_monitor_receive_log` VALUES (544, '100000005', 'f12615a1', '39.144.16.4', '2021-09-18 15:16:41', 35, 280, ' ', 33164);
INSERT INTO `sys_monitor_receive_log` VALUES (545, '100000005', '048609e5', '39.144.13.165', '2021-09-18 22:41:55', 9, 72, ' ', 33172);
INSERT INTO `sys_monitor_receive_log` VALUES (546, '100000005', '0349ed62', '39.144.7.244', '2021-09-18 22:46:00', 14, 112, ' ', 33188);
INSERT INTO `sys_monitor_receive_log` VALUES (547, '100000005', 'b55ba1d5', '117.132.198.57', '2021-09-18 22:53:59', 10, 80, '北京市 北京市', 33196);
INSERT INTO `sys_monitor_receive_log` VALUES (548, '100000005', '0d49955b', '39.144.1.168', '2021-09-18 23:17:59', 4, 32, ' ', 33204);
INSERT INTO `sys_monitor_receive_log` VALUES (549, '100000005', 'a13361fd', '39.144.11.82', '2021-09-18 23:30:35', 40, 320, ' ', 33228);
INSERT INTO `sys_monitor_receive_log` VALUES (550, '100000005', 'd7ea954d', '117.132.198.198', '2021-09-19 00:10:48', 40, 320, '北京市 北京市', 33252);
INSERT INTO `sys_monitor_receive_log` VALUES (551, '100000005', '808b34cc', '117.132.194.179', '2021-09-19 00:26:34', 19, 152, '北京市 北京市', 33268);
INSERT INTO `sys_monitor_receive_log` VALUES (552, '100000005', 'b890211f', '39.144.6.135', '2021-09-19 00:34:51', 40, 320, ' ', 33292);
INSERT INTO `sys_monitor_receive_log` VALUES (553, '100000003', 'b922c744', '45.141.87.59', '2021-09-19 02:09:44', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (554, '100000005', 'f4f94297', '39.144.18.175', '2021-09-19 03:11:30', 40, 320, ' ', 33308);
INSERT INTO `sys_monitor_receive_log` VALUES (555, '100000005', '5ccdd843', '39.144.11.209', '2021-09-19 03:35:37', 40, 320, ' ', 33332);
INSERT INTO `sys_monitor_receive_log` VALUES (556, '100000005', '80eb27f1', '117.132.191.55', '2021-09-19 03:39:01', 2, 16, '北京市 北京市', 33340);
INSERT INTO `sys_monitor_receive_log` VALUES (557, '100000005', '60f41a0e', '117.132.191.225', '2021-09-19 04:19:50', 40, 320, '北京市 北京市', 33364);
INSERT INTO `sys_monitor_receive_log` VALUES (558, '100000005', 'e49a2c7c', '39.144.6.183', '2021-09-19 05:23:47', 19, 152, ' ', 33380);
INSERT INTO `sys_monitor_receive_log` VALUES (559, '100000005', '3f9fa502', '223.104.255.164', '2021-09-19 05:48:10', 40, 320, '广东省 广州市', 33404);
INSERT INTO `sys_monitor_receive_log` VALUES (560, '100000005', '265336b8', '39.144.5.158', '2021-09-19 06:40:15', 29, 232, ' ', 33420);
INSERT INTO `sys_monitor_receive_log` VALUES (561, '100000005', 'e3cf9def', '39.144.17.149', '2021-09-19 07:04:30', 40, 320, ' ', 33444);
INSERT INTO `sys_monitor_receive_log` VALUES (562, '100000005', 'e747e0c4', '221.178.125.99', '2021-09-19 07:12:33', 40, 320, '重庆市 重庆市', 33468);
INSERT INTO `sys_monitor_receive_log` VALUES (563, '100000005', '4101f55b', '218.204.252.4', '2021-09-19 07:28:33', 35, 280, '广东省 广州市', 33508);
INSERT INTO `sys_monitor_receive_log` VALUES (564, '100000005', 'c3b1524b', '221.178.127.98', '2021-09-19 07:32:29', 29, 232, '重庆市 重庆市', 33524);
INSERT INTO `sys_monitor_receive_log` VALUES (565, '100000005', '2c7601eb', '39.144.5.122', '2021-09-19 07:36:36', 41, 312, ' ', 33548);
INSERT INTO `sys_monitor_receive_log` VALUES (566, '100000005', 'c438c979', '221.178.127.73', '2021-09-19 08:12:48', 40, 320, '重庆市 重庆市', 33572);
INSERT INTO `sys_monitor_receive_log` VALUES (567, '100000005', '737e518f', '117.132.194.54', '2021-09-19 08:40:55', 40, 320, '北京市 北京市', 33596);
INSERT INTO `sys_monitor_receive_log` VALUES (568, '100000005', '1c15b09e', '117.132.191.89', '2021-09-19 08:44:53', 40, 320, '北京市 北京市', 33620);
INSERT INTO `sys_monitor_receive_log` VALUES (569, '100000005', 'eb3d257c', '39.144.11.100', '2021-09-19 09:00:31', 6, 48, ' ', 33628);
INSERT INTO `sys_monitor_receive_log` VALUES (570, '100000005', '12fc032a', '221.178.126.234', '2021-09-19 16:10:54', 35, 280, '重庆市 重庆市', 33644);
INSERT INTO `sys_monitor_receive_log` VALUES (571, '100000005', 'bcff9f35', '39.144.9.73', '2021-09-19 23:28:33', 40, 320, ' ', 33668);
INSERT INTO `sys_monitor_receive_log` VALUES (572, '100000005', '1675df82', '39.144.13.222', '2021-09-20 00:20:27', 15, 120, ' ', 33684);
INSERT INTO `sys_monitor_receive_log` VALUES (573, '100000005', '6ad293eb', '117.132.196.57', '2021-09-20 00:24:52', 40, 320, '北京市 北京市', 33708);
INSERT INTO `sys_monitor_receive_log` VALUES (574, '100000005', 'd8c5a1df', '39.144.12.241', '2021-09-20 00:36:17', 1, 8, ' ', 33716);
INSERT INTO `sys_monitor_receive_log` VALUES (575, '100000005', '0a8d65a4', '117.132.198.94', '2021-09-20 00:44:52', 40, 320, '北京市 北京市', 33748);
INSERT INTO `sys_monitor_receive_log` VALUES (576, '100000005', 'e1bdaf7e', '39.144.14.78', '2021-09-20 00:48:46', 27, 216, ' ', 33764);
INSERT INTO `sys_monitor_receive_log` VALUES (577, '100000005', '75ccedc8', '39.144.15.1', '2021-09-20 01:24:57', 32, 256, ' ', 33780);
INSERT INTO `sys_monitor_receive_log` VALUES (578, '100000005', '7e863ef9', '221.178.124.187', '2021-09-20 01:49:09', 40, 320, '重庆市 重庆市', 33804);
INSERT INTO `sys_monitor_receive_log` VALUES (579, '100000005', '98cf0185', '39.144.2.230', '2021-09-20 02:17:17', 36, 288, ' ', 33828);
INSERT INTO `sys_monitor_receive_log` VALUES (580, '100000005', '6ede41d3', '39.144.8.12', '2021-09-20 02:37:20', 40, 320, ' ', 33852);
INSERT INTO `sys_monitor_receive_log` VALUES (581, '100000005', '1b83fb80', '39.144.3.238', '2021-09-20 02:41:21', 40, 320, ' ', 33876);
INSERT INTO `sys_monitor_receive_log` VALUES (582, '100000005', 'f0434874', '117.132.194.116', '2021-09-20 02:49:28', 39, 312, '北京市 北京市', 33900);
INSERT INTO `sys_monitor_receive_log` VALUES (583, '100000005', 'de018ccd', '117.132.198.132', '2021-09-20 03:17:34', 39, 312, '北京市 北京市', 33924);
INSERT INTO `sys_monitor_receive_log` VALUES (584, '100000005', 'cdce98de', '39.144.15.77', '2021-09-20 03:33:35', 40, 320, ' ', 33948);
INSERT INTO `sys_monitor_receive_log` VALUES (585, '100000005', '3cef8db0', '39.144.2.126', '2021-09-20 03:49:42', 40, 320, ' ', 33972);
INSERT INTO `sys_monitor_receive_log` VALUES (586, '100000005', '9944c8b1', '223.104.254.12', '2021-09-20 03:57:43', 40, 320, '北京市 北京市', 33996);
INSERT INTO `sys_monitor_receive_log` VALUES (587, '100000005', 'fda06057', '39.144.11.69', '2021-09-20 04:25:49', 40, 320, ' ', 34020);
INSERT INTO `sys_monitor_receive_log` VALUES (588, '100000005', 'de2d9592', '218.204.253.115', '2021-09-20 04:29:40', 28, 224, '广东省 广州市', 34044);
INSERT INTO `sys_monitor_receive_log` VALUES (589, '100000005', '4e636272', '39.144.14.26', '2021-09-20 04:45:26', 12, 96, ' ', 34060);
INSERT INTO `sys_monitor_receive_log` VALUES (590, '100000005', 'be1ba1e1', '39.144.1.192', '2021-09-20 05:13:23', 1, 8, ' ', 34068);
INSERT INTO `sys_monitor_receive_log` VALUES (591, '100000005', 'dc665af7', '39.144.13.179', '2021-09-20 05:17:49', 24, 192, ' ', 34092);
INSERT INTO `sys_monitor_receive_log` VALUES (592, '100000005', '9a669d98', '39.144.16.148', '2021-09-20 05:37:33', 6, 48, ' ', 34100);
INSERT INTO `sys_monitor_receive_log` VALUES (593, '100000005', 'aa40a939', '221.178.126.104', '2021-09-20 05:49:53', 20, 160, '重庆市 重庆市', 34116);
INSERT INTO `sys_monitor_receive_log` VALUES (594, '100000005', '437d9739', '117.132.195.22', '2021-09-20 05:57:39', 3, 24, '北京市 北京市', 34124);
INSERT INTO `sys_monitor_receive_log` VALUES (595, '100000005', '4cbb1d57', '117.132.195.19', '2021-09-20 06:26:20', 40, 320, '北京市 北京市', 34148);
INSERT INTO `sys_monitor_receive_log` VALUES (596, '100000005', 'b66be0d9', '221.178.125.251', '2021-09-20 06:30:22', 40, 320, '重庆市 重庆市', 34172);
INSERT INTO `sys_monitor_receive_log` VALUES (597, '100000005', 'c4c517fb', '117.132.193.204', '2021-09-20 06:45:51', 6, 48, '北京市 北京市', 34180);
INSERT INTO `sys_monitor_receive_log` VALUES (598, '100000005', 'a0659a6e', '39.144.12.228', '2021-09-20 07:01:58', 9, 72, ' ', 34196);
INSERT INTO `sys_monitor_receive_log` VALUES (599, '100000005', '79dfa9dd', '221.178.124.151', '2021-09-20 07:26:42', 34, 272, '重庆市 重庆市', 34220);
INSERT INTO `sys_monitor_receive_log` VALUES (600, '100000005', '53336360', '39.144.15.87', '2021-09-20 07:42:09', 8, 64, ' ', 34236);
INSERT INTO `sys_monitor_receive_log` VALUES (601, '100000005', '3bbaba38', '117.132.191.228', '2021-09-20 07:46:38', 40, 320, '北京市 北京市', 34260);
INSERT INTO `sys_monitor_receive_log` VALUES (602, '100000005', 'bc40908c', '117.132.193.58', '2021-09-20 07:54:34', 33, 264, '北京市 北京市', 34284);
INSERT INTO `sys_monitor_receive_log` VALUES (603, '100000005', 'f13e2e26', '39.144.5.62', '2021-09-20 08:47:02', 34, 272, ' ', 34300);
INSERT INTO `sys_monitor_receive_log` VALUES (604, '100000005', '75767c64', '39.144.12.15', '2021-09-20 09:18:52', 13, 104, ' ', 34308);
INSERT INTO `sys_monitor_receive_log` VALUES (605, '100000005', '6a54afce', '117.132.196.242', '2021-09-20 10:59:35', 20, 160, '北京市 北京市', 34324);
INSERT INTO `sys_monitor_receive_log` VALUES (607, '100000005', '904c471c', '221.178.127.144', '2021-09-20 17:25:12', 34, 272, '重庆市 重庆市', 34348);
INSERT INTO `sys_monitor_receive_log` VALUES (608, '100000005', '576d2d93', '39.144.13.185', '2021-09-20 19:25:41', 33, 264, ' ', 34372);
INSERT INTO `sys_monitor_receive_log` VALUES (609, '100000005', 'ccf47267', '39.144.5.24', '2021-09-20 20:33:58', 35, 280, ' ', 34396);
INSERT INTO `sys_monitor_receive_log` VALUES (610, '100000005', 'b003469d', '39.144.7.215', '2021-09-20 21:18:09', 35, 280, ' ', 34420);
INSERT INTO `sys_monitor_receive_log` VALUES (611, '100000005', 'fb941e6f', '39.144.18.184', '2021-09-20 22:58:35', 34, 272, ' ', 34436);
INSERT INTO `sys_monitor_receive_log` VALUES (612, '100000005', '7653eaf1', '39.144.1.47', '2021-09-20 23:26:41', 33, 264, ' ', 34460);
INSERT INTO `sys_monitor_receive_log` VALUES (613, '100000005', 'a3df9b9d', '221.178.127.176', '2021-09-21 00:30:57', 34, 272, '重庆市 重庆市', 34484);
INSERT INTO `sys_monitor_receive_log` VALUES (616, '100000005', '0ecaebb5', '39.144.4.234', '2021-09-21 09:58:59', 33, 264, ' ', 34508);
INSERT INTO `sys_monitor_receive_log` VALUES (617, '100000071', '0c1158b6', '5.163.247.195', '2021-09-21 14:36:00', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (618, '100000071', '86f72d34', '167.99.50.104', '2021-09-21 17:47:38', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (619, '100000005', '40fb64d9', '221.178.126.51', '2021-09-21 18:01:00', 33, 264, '重庆市 重庆市', 34532);
INSERT INTO `sys_monitor_receive_log` VALUES (620, '100000005', '83be4921', '117.132.196.61', '2021-09-21 19:37:24', 35, 280, '北京市 北京市', 34548);
INSERT INTO `sys_monitor_receive_log` VALUES (621, '100000005', 'ce323ff2', '39.144.14.56', '2021-09-21 22:46:11', 34, 272, ' ', 34564);
INSERT INTO `sys_monitor_receive_log` VALUES (622, '100000005', '144a2c3e', '117.132.196.185', '2021-09-22 01:06:36', 40, 320, '北京市 北京市', 34588);
INSERT INTO `sys_monitor_receive_log` VALUES (623, '100000005', '3853947c', '39.144.7.246', '2021-09-22 01:14:38', 40, 320, ' ', 34612);
INSERT INTO `sys_monitor_receive_log` VALUES (624, '100000005', '2235abdf', '39.144.14.106', '2021-09-22 03:11:14', 32, 256, ' ', 34636);
INSERT INTO `sys_monitor_receive_log` VALUES (625, '100000071', 'ad2b255b', '183.136.225.14', '2021-09-22 03:16:45', 1, 0, '浙江省 嘉兴市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (626, '100000005', '55ce94ce', '117.132.198.165', '2021-09-22 03:22:32', 3, 24, '北京市 北京市', 34644);
INSERT INTO `sys_monitor_receive_log` VALUES (627, '100000005', 'a9ee6b81', '218.204.253.77', '2021-09-22 07:20:06', 40, 320, '广东省 广州市', 34668);
INSERT INTO `sys_monitor_receive_log` VALUES (628, '100000003', 'dbc95716', '178.159.37.58', '2021-09-22 07:58:40', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (629, '100000005', '3fc3d1f4', '221.178.124.240', '2021-09-22 08:00:20', 39, 312, '重庆市 重庆市', 34692);
INSERT INTO `sys_monitor_receive_log` VALUES (630, '100000005', '25f20a92', '223.104.254.198', '2021-09-22 08:12:19', 40, 320, '北京市 北京市', 34716);
INSERT INTO `sys_monitor_receive_log` VALUES (631, '100000005', '9234ac66', '117.132.193.156', '2021-09-22 08:20:34', 32, 256, '北京市 北京市', 34740);
INSERT INTO `sys_monitor_receive_log` VALUES (632, '100000005', '2b83eccf', '39.144.11.108', '2021-09-22 14:13:59', 34, 272, ' ', 34756);
INSERT INTO `sys_monitor_receive_log` VALUES (633, '100000005', '3df10bd3', '39.144.10.203', '2021-09-22 21:59:30', 35, 280, ' ', 34772);
INSERT INTO `sys_monitor_receive_log` VALUES (634, '100000005', 'f9e3f7ab', '117.132.198.40', '2021-09-23 00:44:10', 35, 280, '北京市 北京市', 34788);
INSERT INTO `sys_monitor_receive_log` VALUES (635, '100000005', '1c041dd7', '221.178.125.249', '2021-09-23 01:08:08', 40, 320, '重庆市 重庆市', 34812);
INSERT INTO `sys_monitor_receive_log` VALUES (636, '100000005', '66532051', '39.144.13.106', '2021-09-23 03:04:46', 31, 248, ' ', 34836);
INSERT INTO `sys_monitor_receive_log` VALUES (637, '100000005', '6babd3a5', '223.104.255.146', '2021-09-23 03:32:53', 33, 264, '广东省 广州市', 34860);
INSERT INTO `sys_monitor_receive_log` VALUES (638, '100000005', 'bf683339', '117.132.197.142', '2021-09-23 04:04:51', 40, 320, '北京市 北京市', 34892);
INSERT INTO `sys_monitor_receive_log` VALUES (639, '100000005', '7085de5f', '39.144.4.235', '2021-09-23 05:21:10', 40, 320, ' ', 34916);
INSERT INTO `sys_monitor_receive_log` VALUES (640, '100000005', '4559755f', '221.178.124.4', '2021-09-23 05:32:53', 17, 136, '重庆市 重庆市', 34948);
INSERT INTO `sys_monitor_receive_log` VALUES (641, '100000005', '0cefb5a4', '117.132.198.208', '2021-09-23 05:45:18', 41, 320, '北京市 北京市', 35012);
INSERT INTO `sys_monitor_receive_log` VALUES (642, '100000005', '6615a9b8', '117.132.196.124', '2021-09-23 06:33:31', 40, 320, '北京市 北京市', 35052);
INSERT INTO `sys_monitor_receive_log` VALUES (643, '100000005', '992e3109', '218.204.253.21', '2021-09-23 07:01:35', 40, 320, '广东省 广州市', 35092);
INSERT INTO `sys_monitor_receive_log` VALUES (644, '100000005', '07870009', '39.144.2.134', '2021-09-23 07:45:47', 40, 320, ' ', 35132);
INSERT INTO `sys_monitor_receive_log` VALUES (645, '100000005', '9fd05565', '39.144.17.255', '2021-09-23 08:01:52', 41, 312, ' ', 35164);
INSERT INTO `sys_monitor_receive_log` VALUES (646, '100000005', 'add02a52', '39.144.7.118', '2021-09-23 08:05:51', 40, 320, ' ', 35196);
INSERT INTO `sys_monitor_receive_log` VALUES (647, '100000005', '895a12e2', '221.178.125.249', '2021-09-23 08:10:04', 31, 248, '重庆市 重庆市', 35220);
INSERT INTO `sys_monitor_receive_log` VALUES (648, '100000005', 'a8e24f0c', '117.132.193.7', '2021-09-23 08:21:56', 40, 320, '北京市 北京市', 35252);
INSERT INTO `sys_monitor_receive_log` VALUES (649, '100000005', 'ec04a391', '39.144.12.181', '2021-09-23 08:29:58', 40, 320, ' ', 35276);
INSERT INTO `sys_monitor_receive_log` VALUES (650, '100000003', '9a83b1f2', '194.61.25.4', '2021-09-23 11:08:55', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (651, '100000005', '3fde97ed', '117.132.192.20', '2021-09-23 12:32:29', 35, 280, '北京市 北京市', 35316);
INSERT INTO `sys_monitor_receive_log` VALUES (652, '100000005', '27c81087', '117.132.191.5', '2021-09-23 17:53:49', 33, 264, '北京市 北京市', 35340);
INSERT INTO `sys_monitor_receive_log` VALUES (653, '100000003', '883d3083', '194.61.25.4', '2021-09-23 19:21:27', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (654, '100000005', 'a35e0dad', '221.178.127.243', '2021-09-23 23:02:22', 1, 8, '重庆市 重庆市', 35348);
INSERT INTO `sys_monitor_receive_log` VALUES (655, '100000005', 'bf1a1914', '39.144.6.72', '2021-09-23 23:06:58', 40, 320, ' ', 35388);
INSERT INTO `sys_monitor_receive_log` VALUES (656, '100000005', 'e24c159c', '39.144.9.222', '2021-09-24 00:43:22', 40, 320, ' ', 35412);
INSERT INTO `sys_monitor_receive_log` VALUES (657, '100000005', '96187ce4', '117.132.197.59', '2021-09-24 00:59:35', 33, 264, '北京市 北京市', 35431);
INSERT INTO `sys_monitor_receive_log` VALUES (658, '100000005', '2630166c', '218.204.253.82', '2021-09-24 01:25:33', 32, 256, '广东省 广州市', 35455);
INSERT INTO `sys_monitor_receive_log` VALUES (659, '100000005', 'bf09fdb7', '117.132.191.226', '2021-09-24 02:05:32', 40, 320, '北京市 北京市', 35479);
INSERT INTO `sys_monitor_receive_log` VALUES (660, '100000005', 'e806bfb3', '39.144.3.49', '2021-09-24 03:33:55', 41, 312, ' ', 35511);
INSERT INTO `sys_monitor_receive_log` VALUES (661, '100000005', '5c00fdbd', '39.144.12.83', '2021-09-24 04:34:04', 29, 232, ' ', 35535);
INSERT INTO `sys_monitor_receive_log` VALUES (662, '100000005', '7e7c5a41', '221.178.126.126', '2021-09-24 06:26:07', 4, 32, '重庆市 重庆市', 35543);
INSERT INTO `sys_monitor_receive_log` VALUES (663, '100000005', '97c2d462', '39.144.5.37', '2021-09-24 06:42:26', 22, 176, ' ', 35567);
INSERT INTO `sys_monitor_receive_log` VALUES (664, '100000005', 'a37524e2', '39.144.6.182', '2021-09-24 06:54:17', 9, 72, ' ', 35583);
INSERT INTO `sys_monitor_receive_log` VALUES (665, '100000005', 'dce116cc', '221.178.124.73', '2021-09-24 06:58:45', 40, 320, '重庆市 重庆市', 35607);
INSERT INTO `sys_monitor_receive_log` VALUES (666, '100000071', '9b482ab7', '162.142.125.128', '2021-09-24 07:27:16', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (667, '100000005', 'd7a2ef45', '39.144.1.253', '2021-09-24 07:59:05', 40, 320, ' ', 35631);
INSERT INTO `sys_monitor_receive_log` VALUES (668, '100000005', '0085f28e', '117.132.191.81', '2021-09-24 08:06:56', 30, 240, '北京市 北京市', 35655);
INSERT INTO `sys_monitor_receive_log` VALUES (669, '100000005', '3e7daf12', '117.132.197.143', '2021-09-24 08:31:19', 34, 272, '北京市 北京市', 35679);
INSERT INTO `sys_monitor_receive_log` VALUES (670, '100000005', 'd45c0968', '39.144.8.74', '2021-09-24 08:51:16', 40, 320, ' ', 35703);
INSERT INTO `sys_monitor_receive_log` VALUES (671, '100000005', '4b8e83a1', '117.132.196.156', '2021-09-24 09:19:20', 40, 320, '北京市 北京市', 35727);
INSERT INTO `sys_monitor_receive_log` VALUES (672, '100000005', 'b2a57c92', '39.144.10.223', '2021-09-24 09:43:26', 40, 320, ' ', 35767);
INSERT INTO `sys_monitor_receive_log` VALUES (673, '100000005', '24a4cb6d', '117.132.197.150', '2021-09-24 09:47:37', 33, 264, '北京市 北京市', 35791);
INSERT INTO `sys_monitor_receive_log` VALUES (674, '100000005', '06c1ea9e', '39.144.16.34', '2021-09-24 09:51:28', 40, 320, ' ', 35815);
INSERT INTO `sys_monitor_receive_log` VALUES (675, '100000005', '290fcc59', '39.144.9.223', '2021-09-24 09:59:31', 40, 320, ' ', 35879);
INSERT INTO `sys_monitor_receive_log` VALUES (676, '100000071', '4d055a18', '42.240.136.36', '2021-09-24 12:54:24', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (677, '100000005', 'b8af1b49', '39.144.11.113', '2021-09-24 13:08:17', 40, 320, ' ', 35927);
INSERT INTO `sys_monitor_receive_log` VALUES (678, '100000005', '3bde81be', '39.144.1.191', '2021-09-24 13:56:40', 35, 280, ' ', 35959);
INSERT INTO `sys_monitor_receive_log` VALUES (679, '100000005', '5d28ddef', '39.144.12.209', '2021-09-24 14:20:35', 40, 320, ' ', 36027);
INSERT INTO `sys_monitor_receive_log` VALUES (680, '100000005', 'b969002e', '39.144.17.127', '2021-09-24 15:52:30', 40, 320, ' ', 36099);
INSERT INTO `sys_monitor_receive_log` VALUES (681, '100000005', 'e596b424', '117.132.192.132', '2021-09-24 16:16:46', 33, 264, '北京市 北京市', 36163);
INSERT INTO `sys_monitor_receive_log` VALUES (682, '100000005', '6658f495', '39.144.3.20', '2021-09-24 22:18:06', 40, 320, ' ', 36203);
INSERT INTO `sys_monitor_receive_log` VALUES (683, '100000005', '24d5973a', '117.132.194.118', '2021-09-25 00:22:47', 31, 248, '北京市 北京市', 36227);
INSERT INTO `sys_monitor_receive_log` VALUES (684, '100000005', 'a563bc83', '117.132.198.243', '2021-09-25 01:10:52', 40, 320, '北京市 北京市', 36259);
INSERT INTO `sys_monitor_receive_log` VALUES (685, '100000005', '95e1b526', '223.104.255.49', '2021-09-25 01:19:02', 32, 256, '广东省 广州市', 36283);
INSERT INTO `sys_monitor_receive_log` VALUES (686, '100000005', '7b95c4a2', '39.144.15.143', '2021-09-25 01:38:57', 40, 320, ' ', 36315);
INSERT INTO `sys_monitor_receive_log` VALUES (687, '100000005', 'd451a83c', '39.144.8.42', '2021-09-25 02:15:13', 40, 320, ' ', 36347);
INSERT INTO `sys_monitor_receive_log` VALUES (688, '100000005', '0ae7d398', '221.178.124.55', '2021-09-25 03:47:41', 34, 272, '重庆市 重庆市', 36371);
INSERT INTO `sys_monitor_receive_log` VALUES (689, '100000005', '87547ca6', '39.144.4.120', '2021-09-25 04:23:37', 40, 320, ' ', 36443);
INSERT INTO `sys_monitor_receive_log` VALUES (690, '100000005', '2f1071fd', '117.132.192.84', '2021-09-25 04:27:08', 7, 56, '北京市 北京市', 36459);
INSERT INTO `sys_monitor_receive_log` VALUES (691, '100000005', '885093a7', '39.144.1.12', '2021-09-25 07:20:07', 2, 16, ' ', 36467);
INSERT INTO `sys_monitor_receive_log` VALUES (692, '100000071', '57fb1739', '39.99.235.57', '2021-09-25 07:20:14', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (693, '100000003', '60cdb49c', '39.99.235.57', '2021-09-25 07:21:24', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (694, '100000108', '607c8497', '39.99.235.57', '2021-09-25 07:21:58', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (695, '100000071', 'ad6fe1f1', '39.99.235.57', '2021-09-25 07:22:08', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (696, '100000005', '64e04965', '39.99.235.57', '2021-09-25 07:24:12', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (697, '100000067', 'fc4dcfaf', '39.99.235.57', '2021-09-25 07:25:17', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (698, '100000071', 'c492008c', '39.99.235.57', '2021-09-25 07:26:02', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (699, '100000071', 'c4b6fdc1', '39.99.235.57', '2021-09-25 07:26:45', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (700, '100000071', '03724d0b', '39.99.235.57', '2021-09-25 07:26:57', 1, 8, '北京市 北京市', 24);
INSERT INTO `sys_monitor_receive_log` VALUES (701, '100000067', '84675cc0', '39.99.235.57', '2021-09-25 07:27:06', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (702, '100000005', '5bb7bf40', '221.178.124.132', '2021-09-25 07:40:26', 40, 320, '重庆市 重庆市', 36539);
INSERT INTO `sys_monitor_receive_log` VALUES (703, '100000005', 'b64cc56a', '223.104.254.142', '2021-09-25 07:56:27', 40, 320, '北京市 北京市', 36619);
INSERT INTO `sys_monitor_receive_log` VALUES (704, '100000005', 'aaca3905', '223.104.255.7', '2021-09-25 08:14:34', 40, 320, '广东省 广州市', 36667);
INSERT INTO `sys_monitor_receive_log` VALUES (705, '100000005', 'c0c47524', '117.132.196.94', '2021-09-25 09:01:08', 40, 320, '北京市 北京市', 36707);
INSERT INTO `sys_monitor_receive_log` VALUES (706, '100000005', '7a12c9e2', '39.144.5.191', '2021-09-25 09:09:08', 40, 320, ' ', 36739);
INSERT INTO `sys_monitor_receive_log` VALUES (707, '100000005', '5ef2291a', '39.144.1.36', '2021-09-25 09:21:11', 40, 320, ' ', 36771);
INSERT INTO `sys_monitor_receive_log` VALUES (708, '100000005', 'b75cab9e', '223.104.255.223', '2021-09-25 09:45:17', 40, 320, '广东省 广州市', 36803);
INSERT INTO `sys_monitor_receive_log` VALUES (709, '100000071', '8083000b', '183.136.225.14', '2021-09-25 12:49:50', 1, 0, '浙江省 嘉兴市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (710, '100000005', 'f4e972da', '117.132.191.13', '2021-09-25 16:14:35', 40, 320, '北京市 北京市', 36867);
INSERT INTO `sys_monitor_receive_log` VALUES (711, '100000005', '43cea56e', '223.104.255.22', '2021-09-25 21:49:31', 40, 320, '广东省 广州市', 36907);
INSERT INTO `sys_monitor_receive_log` VALUES (712, '100000005', '3c01ef7d', '223.104.254.158', '2021-09-26 00:01:20', 12, 96, '北京市 北京市', 36923);
INSERT INTO `sys_monitor_receive_log` VALUES (713, '100000005', 'be26fd8b', '117.132.193.13', '2021-09-26 00:25:16', 1, 8, '北京市 北京市', 36931);
INSERT INTO `sys_monitor_receive_log` VALUES (714, '100000005', '1bc2dcfa', '117.132.193.93', '2021-09-26 01:17:40', 11, 88, '北京市 北京市', 36947);
INSERT INTO `sys_monitor_receive_log` VALUES (715, '100000005', '955a1546', '39.144.14.44', '2021-09-26 01:37:55', 24, 192, ' ', 36971);
INSERT INTO `sys_monitor_receive_log` VALUES (716, '100000005', '63f24b32', '39.144.2.112', '2021-09-26 02:42:37', 30, 240, ' ', 36995);
INSERT INTO `sys_monitor_receive_log` VALUES (717, '100000005', '9cb70599', '117.132.196.65', '2021-09-26 03:18:36', 41, 312, '北京市 北京市', 37035);
INSERT INTO `sys_monitor_receive_log` VALUES (718, '100000005', 'd471ff81', '39.144.11.70', '2021-09-26 04:02:54', 33, 264, ' ', 37067);
INSERT INTO `sys_monitor_receive_log` VALUES (719, '100000005', 'bbcb23d3', '39.144.8.35', '2021-09-26 05:10:48', 23, 184, ' ', 37083);
INSERT INTO `sys_monitor_receive_log` VALUES (720, '100000005', '173bf450', '39.144.16.156', '2021-09-26 05:23:11', 40, 320, ' ', 37107);
INSERT INTO `sys_monitor_receive_log` VALUES (721, '100000005', 'b2142f94', '39.144.10.127', '2021-09-26 05:55:13', 40, 320, ' ', 37131);
INSERT INTO `sys_monitor_receive_log` VALUES (722, '100000005', '123baae4', '39.144.10.25', '2021-09-26 06:06:49', 10, 80, ' ', 37139);
INSERT INTO `sys_monitor_receive_log` VALUES (723, '100000005', 'cc370e26', '117.132.193.115', '2021-09-26 06:31:00', 15, 120, '北京市 北京市', 37155);
INSERT INTO `sys_monitor_receive_log` VALUES (724, '100000005', 'bd8e4f46', '39.144.8.199', '2021-09-26 06:57:55', 1, 8, ' ', 37163);
INSERT INTO `sys_monitor_receive_log` VALUES (725, '100000005', '8659014c', '221.178.125.88', '2021-09-26 08:05:28', 1, 8, '重庆市 重庆市', 37171);
INSERT INTO `sys_monitor_receive_log` VALUES (726, '100000005', 'e75daad0', '223.104.255.124', '2021-09-26 08:13:33', 1, 8, '广东省 广州市', 37179);
INSERT INTO `sys_monitor_receive_log` VALUES (727, '100000005', '732acabd', '39.144.9.173', '2021-09-26 08:17:32', 1, 8, ' ', 37187);
INSERT INTO `sys_monitor_receive_log` VALUES (728, '100000005', '05939c43', '218.204.253.55', '2021-09-26 08:25:34', 1, 8, '广东省 广州市', 37195);
INSERT INTO `sys_monitor_receive_log` VALUES (729, '100000005', '160e6836', '39.144.2.176', '2021-09-26 08:29:50', 20, 160, ' ', 37211);
INSERT INTO `sys_monitor_receive_log` VALUES (730, '100000005', 'b7660fd9', '39.144.9.45', '2021-09-26 08:41:58', 15, 120, ' ', 37227);
INSERT INTO `sys_monitor_receive_log` VALUES (731, '100000005', '7fa900ce', '117.132.197.161', '2021-09-26 08:45:39', 3, 24, '北京市 北京市', 37235);
INSERT INTO `sys_monitor_receive_log` VALUES (732, '100000005', '0023b336', '39.144.12.50', '2021-09-26 08:57:49', 12, 96, ' ', 37243);
INSERT INTO `sys_monitor_receive_log` VALUES (733, '100000005', '01ca280c', '39.144.9.155', '2021-09-26 09:01:45', 1, 8, ' ', 37251);
INSERT INTO `sys_monitor_receive_log` VALUES (734, '100000005', 'abd07b47', '39.144.3.131', '2021-09-26 09:10:10', 1, 8, ' ', 37259);
INSERT INTO `sys_monitor_receive_log` VALUES (735, '100000005', 'd052ea0a', '39.144.2.61', '2021-09-26 09:22:06', 21, 168, ' ', 37275);
INSERT INTO `sys_monitor_receive_log` VALUES (736, '100000005', 'd41245d8', '39.144.5.36', '2021-09-26 09:26:02', 16, 128, ' ', 37291);
INSERT INTO `sys_monitor_receive_log` VALUES (737, '100000005', 'dc716e8d', '39.144.11.84', '2021-09-26 09:38:25', 40, 320, ' ', 37315);
INSERT INTO `sys_monitor_receive_log` VALUES (738, '100000005', '759dc0d6', '117.132.193.214', '2021-09-26 09:41:59', 10, 80, '北京市 北京市', 37331);
INSERT INTO `sys_monitor_receive_log` VALUES (739, '100000005', '84e9b0c9', '39.144.12.19', '2021-09-26 09:46:27', 40, 320, ' ', 37363);
INSERT INTO `sys_monitor_receive_log` VALUES (740, '100000005', '7f7435fe', '117.132.191.31', '2021-09-26 09:50:05', 13, 104, '北京市 北京市', 37379);
INSERT INTO `sys_monitor_receive_log` VALUES (741, '100000005', 'a3dba7fc', '117.132.191.7', '2021-09-26 10:01:15', 1, 8, '北京市 北京市', 37387);
INSERT INTO `sys_monitor_receive_log` VALUES (742, '100000005', 'b064706f', '39.144.11.233', '2021-09-26 10:34:00', 40, 320, ' ', 37411);
INSERT INTO `sys_monitor_receive_log` VALUES (743, '100000005', '12c7ae8a', '117.132.193.79', '2021-09-26 10:54:04', 40, 320, '北京市 北京市', 37435);
INSERT INTO `sys_monitor_receive_log` VALUES (744, '100000005', '2aa35b20', '39.144.8.234', '2021-09-26 12:10:02', 18, 144, ' ', 37467);
INSERT INTO `sys_monitor_receive_log` VALUES (745, '100000005', 'e64d9a7c', '39.144.8.106', '2021-09-26 12:22:25', 40, 320, ' ', 37579);
INSERT INTO `sys_monitor_receive_log` VALUES (746, '100000005', 'db05ebb8', '223.104.254.48', '2021-09-26 12:38:38', 34, 272, '北京市 北京市', 37619);
INSERT INTO `sys_monitor_receive_log` VALUES (747, '100000005', 'd660f4f9', '39.144.6.80', '2021-09-26 13:06:36', 40, 320, ' ', 37699);
INSERT INTO `sys_monitor_receive_log` VALUES (748, '100000005', 'd6800e14', '117.132.195.116', '2021-09-26 19:52:27', 35, 280, '北京市 北京市', 37739);
INSERT INTO `sys_monitor_receive_log` VALUES (749, '100000005', 'e6cac6b9', '39.144.2.61', '2021-09-26 21:00:34', 40, 320, ' ', 37771);
INSERT INTO `sys_monitor_receive_log` VALUES (750, '100000005', '14f7af30', '218.204.253.43', '2021-09-26 22:20:55', 36, 288, '广东省 广州市', 37803);
INSERT INTO `sys_monitor_receive_log` VALUES (751, '100000005', '5cb0e42c', '39.144.7.89', '2021-09-26 22:41:03', 38, 304, ' ', 37835);
INSERT INTO `sys_monitor_receive_log` VALUES (752, '100000005', 'fec94ec2', '39.144.12.192', '2021-09-26 22:47:11', 31, 248, ' ', 37859);
INSERT INTO `sys_monitor_receive_log` VALUES (753, '100000005', '9d5a9d44', '39.144.9.13', '2021-09-26 22:55:21', 39, 312, ' ', 37891);
INSERT INTO `sys_monitor_receive_log` VALUES (754, '100000005', '006c033f', '117.132.198.163', '2021-09-26 22:59:15', 34, 272, '北京市 北京市', 37923);
INSERT INTO `sys_monitor_receive_log` VALUES (755, '100000005', '88965da4', '221.178.124.200', '2021-09-26 23:02:46', 1, 8, '重庆市 重庆市', 37931);
INSERT INTO `sys_monitor_receive_log` VALUES (756, '100000005', '57c458d4', '39.144.5.17', '2021-09-26 23:14:49', 4, 32, ' ', 37939);
INSERT INTO `sys_monitor_receive_log` VALUES (757, '100000005', '89e339df', '223.104.255.229', '2021-09-26 23:47:32', 40, 320, '广东省 广州市', 37971);
INSERT INTO `sys_monitor_receive_log` VALUES (758, '100000005', 'fb2994c3', '39.144.16.143', '2021-09-27 00:31:22', 15, 120, ' ', 37987);
INSERT INTO `sys_monitor_receive_log` VALUES (759, '100000005', 'df2f0597', '39.144.10.88', '2021-09-27 01:07:43', 27, 216, ' ', 38011);
INSERT INTO `sys_monitor_receive_log` VALUES (760, '100000005', 'f393ea59', '39.144.9.103', '2021-09-27 01:11:47', 31, 248, ' ', 38035);
INSERT INTO `sys_monitor_receive_log` VALUES (761, '100000005', '449649ee', '221.178.125.238', '2021-09-27 01:15:16', 1, 8, '重庆市 重庆市', 38043);
INSERT INTO `sys_monitor_receive_log` VALUES (762, '100000005', '5adb1f96', '218.204.252.16', '2021-09-27 01:39:43', 21, 168, '广东省 广州市', 38059);
INSERT INTO `sys_monitor_receive_log` VALUES (763, '100000071', 'f90ddf44', '47.103.99.182', '2021-09-27 01:41:01', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (764, '100000003', 'd7da4d7e', '47.103.99.182', '2021-09-27 01:41:39', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (765, '100000108', 'e9632bcd', '47.103.99.182', '2021-09-27 01:41:54', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (766, '100000071', '01aa26a5', '47.103.99.182', '2021-09-27 01:41:59', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (767, '100000003', 'd2858188', '47.103.99.182', '2021-09-27 01:42:25', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (768, '100000005', '2e060749', '47.103.99.182', '2021-09-27 01:43:07', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (769, '100000067', '906e085e', '47.103.99.182', '2021-09-27 01:43:41', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (770, '100000005', '32807666', '39.144.8.87', '2021-09-27 01:44:03', 40, 320, ' ', 38091);
INSERT INTO `sys_monitor_receive_log` VALUES (771, '100000071', '2969f7a4', '47.103.99.182', '2021-09-27 01:44:19', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (772, '100000071', '0fa50512', '47.103.99.182', '2021-09-27 01:45:02', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (773, '100000071', '8669032f', '47.103.99.182', '2021-09-27 01:45:14', 1, 8, '上海市 上海市', 32);
INSERT INTO `sys_monitor_receive_log` VALUES (774, '100000067', '04955b48', '47.103.99.182', '2021-09-27 01:45:23', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (775, '100000005', '4308fc39', '117.132.193.139', '2021-09-27 01:47:43', 21, 168, '北京市 北京市', 38107);
INSERT INTO `sys_monitor_receive_log` VALUES (776, '100000005', '8f9ae26a', '218.204.253.223', '2021-09-27 02:36:15', 40, 320, '广东省 广州市', 38131);
INSERT INTO `sys_monitor_receive_log` VALUES (777, '100000005', '082af88b', '117.132.191.228', '2021-09-27 03:48:26', 30, 240, '北京市 北京市', 38147);
INSERT INTO `sys_monitor_receive_log` VALUES (778, '100000005', '27e4b385', '39.144.18.59', '2021-09-27 03:52:28', 28, 224, ' ', 38171);
INSERT INTO `sys_monitor_receive_log` VALUES (779, '100000005', 'd4ca7554', '221.178.127.52', '2021-09-27 05:40:32', 11, 88, '重庆市 重庆市', 38187);
INSERT INTO `sys_monitor_receive_log` VALUES (780, '100000005', 'f9287197', '117.132.198.78', '2021-09-27 05:56:36', 9, 72, '北京市 北京市', 38195);
INSERT INTO `sys_monitor_receive_log` VALUES (781, '100000005', '632abd21', '223.104.255.226', '2021-09-27 06:00:30', 1, 8, '广东省 广州市', 38203);
INSERT INTO `sys_monitor_receive_log` VALUES (782, '100000005', '81efa341', '117.132.198.217', '2021-09-27 08:37:42', 40, 320, '北京市 北京市', 38235);
INSERT INTO `sys_monitor_receive_log` VALUES (783, '100000005', '601042d6', '117.132.198.142', '2021-09-27 09:42:08', 35, 280, '北京市 北京市', 38259);
INSERT INTO `sys_monitor_receive_log` VALUES (784, '100000005', '7d347ab3', '39.144.3.133', '2021-09-27 10:06:14', 35, 280, ' ', 38291);
INSERT INTO `sys_monitor_receive_log` VALUES (785, '100000005', '679e86e5', '39.144.14.227', '2021-09-27 10:30:11', 40, 320, ' ', 38315);
INSERT INTO `sys_monitor_receive_log` VALUES (786, '100000005', '758a2c74', '39.144.12.212', '2021-09-27 10:46:15', 40, 320, ' ', 38347);
INSERT INTO `sys_monitor_receive_log` VALUES (787, '100000003', '31452838', '193.106.29.74', '2021-09-27 10:59:45', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (788, '100000003', '4df06695', '45.155.205.127', '2021-09-27 11:06:00', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (789, '100000005', '0ef72769', '223.104.255.142', '2021-09-27 11:26:34', 34, 272, '广东省 广州市', 38371);
INSERT INTO `sys_monitor_receive_log` VALUES (790, '100000005', '6f076b0b', '39.144.8.109', '2021-09-27 12:02:43', 34, 272, ' ', 38395);
INSERT INTO `sys_monitor_receive_log` VALUES (791, '100000005', 'e32464c7', '117.132.192.14', '2021-09-27 17:03:48', 40, 320, '北京市 北京市', 38419);
INSERT INTO `sys_monitor_receive_log` VALUES (792, '100000005', '08465e4e', '39.144.11.50', '2021-09-27 17:52:00', 40, 320, ' ', 38451);
INSERT INTO `sys_monitor_receive_log` VALUES (793, '100000071', 'd24f7f16', '138.197.131.39', '2021-09-27 18:28:36', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (794, '100000005', '47f6431b', '223.104.254.165', '2021-09-27 19:24:33', 34, 272, '北京市 北京市', 38475);
INSERT INTO `sys_monitor_receive_log` VALUES (795, '100000005', 'cc7b013b', '223.104.254.88', '2021-09-27 20:04:33', 40, 320, '北京市 北京市', 38499);
INSERT INTO `sys_monitor_receive_log` VALUES (796, '100000005', 'b47932f2', '39.144.18.58', '2021-09-27 21:41:07', 35, 280, ' ', 38523);
INSERT INTO `sys_monitor_receive_log` VALUES (797, '100000005', '30779303', '39.144.16.106', '2021-09-28 00:13:18', 19, 152, ' ', 38547);
INSERT INTO `sys_monitor_receive_log` VALUES (798, '100000005', 'e69cf004', '39.144.9.19', '2021-09-28 00:17:01', 1, 8, ' ', 38555);
INSERT INTO `sys_monitor_receive_log` VALUES (799, '100000005', 'e05bd69e', '39.144.5.164', '2021-09-28 00:57:56', 33, 264, ' ', 38571);
INSERT INTO `sys_monitor_receive_log` VALUES (800, '100000005', '0cba5a51', '39.144.9.243', '2021-09-28 01:22:02', 34, 272, ' ', 38587);
INSERT INTO `sys_monitor_receive_log` VALUES (801, '100000005', 'dfe08ac3', '117.132.191.169', '2021-09-28 01:45:59', 40, 320, '北京市 北京市', 38611);
INSERT INTO `sys_monitor_receive_log` VALUES (802, '100000005', '5ed64f0c', '117.132.195.22', '2021-09-28 01:50:02', 40, 320, '北京市 北京市', 38731);
INSERT INTO `sys_monitor_receive_log` VALUES (803, '100000003', '2b5de96c', '94.232.42.169', '2021-09-28 03:23:19', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (804, '100000005', '019b6dea', '39.144.8.16', '2021-09-28 04:02:08', 11, 88, ' ', 38755);
INSERT INTO `sys_monitor_receive_log` VALUES (805, '100000005', '9ba96d46', '39.144.11.32', '2021-09-28 05:02:48', 40, 320, ' ', 38803);
INSERT INTO `sys_monitor_receive_log` VALUES (806, '100000005', '703ad6e1', '39.144.9.103', '2021-09-28 05:34:55', 40, 320, ' ', 38843);
INSERT INTO `sys_monitor_receive_log` VALUES (807, '100000005', 'f25f7230', '39.144.12.21', '2021-09-28 06:11:08', 40, 320, ' ', 38867);
INSERT INTO `sys_monitor_receive_log` VALUES (808, '100000005', 'af29b11a', '221.178.125.211', '2021-09-28 06:47:23', 31, 248, '重庆市 重庆市', 38899);
INSERT INTO `sys_monitor_receive_log` VALUES (809, '100000005', 'b26d6109', '39.144.17.115', '2021-09-28 06:55:17', 40, 320, ' ', 38923);
INSERT INTO `sys_monitor_receive_log` VALUES (810, '100000005', '6ac1604e', '117.132.196.96', '2021-09-28 07:02:46', 4, 32, '北京市 北京市', 38931);
INSERT INTO `sys_monitor_receive_log` VALUES (811, '100000005', 'daf05316', '39.144.5.203', '2021-09-28 07:27:28', 40, 320, ' ', 38963);
INSERT INTO `sys_monitor_receive_log` VALUES (812, '100000005', '1e005752', '39.144.1.162', '2021-09-28 07:38:52', 2, 16, ' ', 38971);
INSERT INTO `sys_monitor_receive_log` VALUES (813, '100000005', '1068b283', '39.144.13.37', '2021-09-28 07:47:28', 40, 320, ' ', 39019);
INSERT INTO `sys_monitor_receive_log` VALUES (814, '100000005', '11d18510', '117.132.198.14', '2021-09-28 08:47:43', 40, 320, '北京市 北京市', 39051);
INSERT INTO `sys_monitor_receive_log` VALUES (815, '100000071', 'd1ba3efe', '101.133.155.170', '2021-09-28 08:49:58', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (816, '100000003', '19944c59', '101.133.155.170', '2021-09-28 08:50:34', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (817, '100000108', 'bfec236d', '101.133.155.170', '2021-09-28 08:50:50', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (818, '100000071', '77429996', '101.133.155.170', '2021-09-28 08:50:54', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (819, '100000003', '86a81efa', '101.133.155.170', '2021-09-28 08:51:20', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (820, '100000005', 'e94d08bf', '101.133.155.170', '2021-09-28 08:52:03', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (821, '100000067', 'c13acc20', '101.133.155.170', '2021-09-28 08:52:37', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (822, '100000071', '5ab43520', '101.133.155.170', '2021-09-28 08:53:15', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (823, '100000071', '65db24e1', '101.133.155.170', '2021-09-28 08:53:57', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (824, '100000071', 'dc7f9034', '101.133.155.170', '2021-09-28 08:54:10', 1, 8, '北京市 北京市', 40);
INSERT INTO `sys_monitor_receive_log` VALUES (825, '100000067', '08de82c5', '101.133.155.170', '2021-09-28 08:54:18', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (826, '100000005', '8df9d962', '39.144.3.217', '2021-09-28 08:55:45', 40, 320, ' ', 39083);
INSERT INTO `sys_monitor_receive_log` VALUES (827, '100000005', '0c37fe97', '117.132.193.248', '2021-09-28 09:50:59', 34, 272, '北京市 北京市', 39099);
INSERT INTO `sys_monitor_receive_log` VALUES (828, '100000005', '8da5f052', '117.132.191.240', '2021-09-28 16:28:38', 34, 272, '北京市 北京市', 39123);
INSERT INTO `sys_monitor_receive_log` VALUES (829, '100000071', '6eaf46fc', '39.103.165.234', '2021-09-29 00:11:49', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (830, '100000003', 'c6522d99', '39.103.165.234', '2021-09-29 00:12:29', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (831, '100000108', 'efa61584', '39.103.165.234', '2021-09-29 00:12:45', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (832, '100000071', '83bc2acb', '39.103.165.234', '2021-09-29 00:12:50', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (833, '100000003', 'c2a6f1f6', '39.103.165.234', '2021-09-29 00:13:16', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (834, '100000005', '7282323c', '39.103.165.234', '2021-09-29 00:14:00', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (835, '100000067', 'ad0c925d', '39.103.165.234', '2021-09-29 00:14:34', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (836, '100000071', '820ee79f', '39.103.165.234', '2021-09-29 00:15:13', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (837, '100000071', '6b65e170', '39.103.165.234', '2021-09-29 00:15:56', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (838, '100000071', 'afbd9073', '39.103.165.234', '2021-09-29 00:16:08', 1, 8, '北京市 北京市', 48);
INSERT INTO `sys_monitor_receive_log` VALUES (839, '100000067', '3c3bae3b', '39.103.165.234', '2021-09-29 00:16:17', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (840, '100000005', '8004c887', '39.144.9.230', '2021-09-29 00:22:04', 40, 320, ' ', 39155);
INSERT INTO `sys_monitor_receive_log` VALUES (841, '100000005', '7e774ac7', '39.144.13.93', '2021-09-29 00:54:14', 40, 304, ' ', 39195);
INSERT INTO `sys_monitor_receive_log` VALUES (842, '100000005', '6d5ca3d3', '221.178.126.6', '2021-09-29 01:06:15', 40, 320, '重庆市 重庆市', 39267);
INSERT INTO `sys_monitor_receive_log` VALUES (843, '100000005', '35f69925', '117.132.192.70', '2021-09-29 01:13:43', 2, 16, '北京市 北京市', 39275);
INSERT INTO `sys_monitor_receive_log` VALUES (844, '100000005', '36bdeae4', '39.144.3.46', '2021-09-29 01:18:18', 40, 320, ' ', 39315);
INSERT INTO `sys_monitor_receive_log` VALUES (845, '100000005', '7e04d881', '221.178.125.94', '2021-09-29 01:38:10', 24, 192, '重庆市 重庆市', 39339);
INSERT INTO `sys_monitor_receive_log` VALUES (846, '100000005', '3d60b3d4', '39.144.4.127', '2021-09-29 01:45:57', 9, 72, ' ', 39355);
INSERT INTO `sys_monitor_receive_log` VALUES (847, '100000005', '2001d485', '223.104.255.122', '2021-09-29 02:26:35', 41, 312, '广东省 广州市', 39387);
INSERT INTO `sys_monitor_receive_log` VALUES (848, '100000005', 'b569acfb', '117.132.192.106', '2021-09-29 02:46:40', 41, 312, '北京市 北京市', 39427);
INSERT INTO `sys_monitor_receive_log` VALUES (849, '100000005', 'e02a2da4', '39.144.11.217', '2021-09-29 04:43:09', 40, 320, ' ', 39459);
INSERT INTO `sys_monitor_receive_log` VALUES (850, '100000005', '016c88bc', '221.178.125.129', '2021-09-29 04:59:13', 40, 320, '重庆市 重庆市', 39555);
INSERT INTO `sys_monitor_receive_log` VALUES (851, '100000005', '8f4fd2fc', '223.104.254.83', '2021-09-29 05:03:14', 40, 320, '北京市 北京市', 39603);
INSERT INTO `sys_monitor_receive_log` VALUES (852, '100000005', '64194433', '117.132.191.129', '2021-09-29 06:07:30', 40, 320, '北京市 北京市', 39643);
INSERT INTO `sys_monitor_receive_log` VALUES (853, '100000005', '7ce32306', '39.144.12.75', '2021-09-29 06:35:46', 35, 280, ' ', 39683);
INSERT INTO `sys_monitor_receive_log` VALUES (854, '100000005', '5f8c6602', '223.104.254.223', '2021-09-29 07:11:56', 34, 272, '北京市 北京市', 39715);
INSERT INTO `sys_monitor_receive_log` VALUES (855, '100000005', '0095cdb2', '221.178.127.164', '2021-09-29 08:08:00', 40, 320, '重庆市 重庆市', 39747);
INSERT INTO `sys_monitor_receive_log` VALUES (856, '100000005', 'ade30e1d', '39.144.9.227', '2021-09-29 09:00:13', 40, 320, ' ', 39787);
INSERT INTO `sys_monitor_receive_log` VALUES (857, '100000005', 'ec0eed22', '39.144.9.130', '2021-09-29 09:48:25', 40, 320, ' ', 39835);
INSERT INTO `sys_monitor_receive_log` VALUES (858, '100000005', 'b070c2a6', '221.178.126.101', '2021-09-29 10:20:42', 35, 280, '重庆市 重庆市', 39859);
INSERT INTO `sys_monitor_receive_log` VALUES (859, '100000005', '972a57fa', '39.144.1.149', '2021-09-29 14:41:21', 20, 160, ' ', 39883);
INSERT INTO `sys_monitor_receive_log` VALUES (860, '100000005', 'd485cf7f', '39.144.13.136', '2021-09-29 16:34:16', 35, 280, ' ', 39907);
INSERT INTO `sys_monitor_receive_log` VALUES (861, '100000005', 'b40142d3', '39.144.6.170', '2021-09-29 17:42:33', 35, 280, ' ', 39931);
INSERT INTO `sys_monitor_receive_log` VALUES (862, '100000005', '5713e8d0', '218.204.252.27', '2021-09-29 18:22:43', 34, 272, '广东省 广州市', 39955);
INSERT INTO `sys_monitor_receive_log` VALUES (863, '100000005', '436faf3a', '39.144.3.135', '2021-09-29 19:06:54', 33, 264, ' ', 39979);
INSERT INTO `sys_monitor_receive_log` VALUES (864, '100000005', 'a60a4565', '117.132.195.50', '2021-09-30 02:39:37', 40, 320, '北京市 北京市', 40011);
INSERT INTO `sys_monitor_receive_log` VALUES (865, '100000005', 'c8002879', '39.144.9.172', '2021-09-30 05:11:48', 4, 32, ' ', 40019);
INSERT INTO `sys_monitor_receive_log` VALUES (866, '100000005', 'd2d372a0', '117.132.193.86', '2021-09-30 05:28:14', 6, 48, '北京市 北京市', 40027);
INSERT INTO `sys_monitor_receive_log` VALUES (867, '100000005', '7a6d524d', '39.144.15.248', '2021-09-30 05:36:07', 8, 64, ' ', 40035);
INSERT INTO `sys_monitor_receive_log` VALUES (868, '100000005', 'ffccbbd8', '39.144.12.19', '2021-09-30 06:24:21', 3, 24, ' ', 40043);
INSERT INTO `sys_monitor_receive_log` VALUES (869, '100000005', '441922cf', '39.144.13.48', '2021-09-30 06:52:14', 2, 16, ' ', 40051);
INSERT INTO `sys_monitor_receive_log` VALUES (870, '100000005', 'a992bfe8', '39.144.10.15', '2021-09-30 07:32:34', 5, 40, ' ', 40059);
INSERT INTO `sys_monitor_receive_log` VALUES (871, '100000005', '1e99b729', '39.144.3.129', '2021-09-30 07:36:22', 2, 16, ' ', 40075);
INSERT INTO `sys_monitor_receive_log` VALUES (872, '100000005', '01d68e76', '39.144.3.21', '2021-09-30 07:45:03', 38, 304, ' ', 40099);
INSERT INTO `sys_monitor_receive_log` VALUES (873, '100000005', 'd2602c74', '117.132.196.219', '2021-09-30 07:48:38', 8, 64, '北京市 北京市', 40107);
INSERT INTO `sys_monitor_receive_log` VALUES (874, '100000005', '2f43c321', '223.104.255.26', '2021-09-30 08:06:52', 3, 24, '广东省 广州市', 40115);
INSERT INTO `sys_monitor_receive_log` VALUES (875, '100000005', '4011e51f', '39.144.12.211', '2021-09-30 09:07:07', 5, 40, ' ', 40131);
INSERT INTO `sys_monitor_receive_log` VALUES (876, '100000005', 'd914b16a', '117.132.193.78', '2021-09-30 09:27:47', 35, 280, '北京市 北京市', 40155);
INSERT INTO `sys_monitor_receive_log` VALUES (877, '100000005', 'f46dfd79', '39.144.10.156', '2021-09-30 10:51:46', 7, 56, ' ', 40163);
INSERT INTO `sys_monitor_receive_log` VALUES (878, '100000005', '1a560f69', '39.144.16.230', '2021-09-30 11:03:57', 5, 40, ' ', 40171);
INSERT INTO `sys_monitor_receive_log` VALUES (879, '100000005', '56fa9ed8', '39.144.8.148', '2021-09-30 13:16:44', 32, 256, ' ', 40195);
INSERT INTO `sys_monitor_receive_log` VALUES (880, '100000005', '90d9ae4f', '39.144.1.13', '2021-09-30 14:25:01', 33, 264, ' ', 40219);
INSERT INTO `sys_monitor_receive_log` VALUES (881, '100000005', '85accd67', '39.144.1.53', '2021-09-30 15:04:58', 18, 144, ' ', 40227);
INSERT INTO `sys_monitor_receive_log` VALUES (882, '100000005', 'ae1461af', '39.144.8.38', '2021-09-30 15:08:41', 14, 112, ' ', 40243);
INSERT INTO `sys_monitor_receive_log` VALUES (883, '100000005', '6dce0e67', '117.132.192.103', '2021-09-30 15:48:42', 40, 320, '北京市 北京市', 40267);
INSERT INTO `sys_monitor_receive_log` VALUES (884, '100000003', 'a6c497ed', '185.216.140.32', '2021-09-30 18:54:35', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (885, '100000005', '32a11821', '39.144.14.65', '2021-09-30 21:06:14', 34, 272, ' ', 40299);
INSERT INTO `sys_monitor_receive_log` VALUES (886, '100000071', '97e0cf39', '183.136.225.14', '2021-09-30 22:55:36', 1, 0, '浙江省 嘉兴市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (887, '100000005', '965e5c44', '39.144.1.213', '2021-10-01 00:14:39', 29, 232, ' ', 40323);
INSERT INTO `sys_monitor_receive_log` VALUES (888, '100000005', 'ab9b71bf', '39.144.1.165', '2021-10-01 01:39:10', 40, 320, ' ', 40347);
INSERT INTO `sys_monitor_receive_log` VALUES (889, '100000005', '96c3b46e', '117.132.198.177', '2021-10-01 03:48:59', 40, 320, '北京市 北京市', 40379);
INSERT INTO `sys_monitor_receive_log` VALUES (890, '100000005', '80bdd838', '39.144.2.157', '2021-10-01 04:37:11', 40, 320, ' ', 40403);
INSERT INTO `sys_monitor_receive_log` VALUES (891, '100000005', '976564d2', '39.144.17.82', '2021-10-01 04:41:12', 40, 320, ' ', 40427);
INSERT INTO `sys_monitor_receive_log` VALUES (892, '100000005', 'c7c16176', '117.132.197.151', '2021-10-01 05:37:29', 40, 320, '北京市 北京市', 40451);
INSERT INTO `sys_monitor_receive_log` VALUES (893, '100000003', 'd4ab44c5', '89.248.165.252', '2021-10-01 06:27:29', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (894, '100000005', '624a1ea1', '218.204.252.94', '2021-10-01 07:01:47', 40, 320, '广东省 广州市', 40483);
INSERT INTO `sys_monitor_receive_log` VALUES (895, '100000005', '6e7dbbc3', '117.132.197.92', '2021-10-01 07:25:53', 40, 320, '北京市 北京市', 40507);
INSERT INTO `sys_monitor_receive_log` VALUES (896, '100000005', '09f8798e', '39.144.10.155', '2021-10-01 08:06:13', 32, 256, ' ', 40531);
INSERT INTO `sys_monitor_receive_log` VALUES (897, '100000005', 'a89b59d9', '117.132.194.227', '2021-10-01 09:18:31', 33, 264, '北京市 北京市', 40555);
INSERT INTO `sys_monitor_receive_log` VALUES (898, '100000005', '2901456b', '39.144.6.97', '2021-10-01 21:04:01', 35, 280, ' ', 40579);
INSERT INTO `sys_monitor_receive_log` VALUES (899, '100000005', '30013b62', '39.144.10.60', '2021-10-02 00:28:27', 9, 72, ' ', 40587);
INSERT INTO `sys_monitor_receive_log` VALUES (900, '100000005', '0d608088', '117.132.193.124', '2021-10-02 00:44:56', 31, 248, '北京市 北京市', 40603);
INSERT INTO `sys_monitor_receive_log` VALUES (901, '100000005', '6672188f', '117.132.195.56', '2021-10-02 01:28:57', 40, 320, '北京市 北京市', 40627);
INSERT INTO `sys_monitor_receive_log` VALUES (902, '100000005', 'b612f2e2', '223.104.254.238', '2021-10-02 02:05:03', 16, 128, '北京市 北京市', 40643);
INSERT INTO `sys_monitor_receive_log` VALUES (903, '100000005', '993c0315', '39.144.2.199', '2021-10-02 02:21:20', 33, 264, ' ', 40667);
INSERT INTO `sys_monitor_receive_log` VALUES (904, '100000071', '5eae98a8', '123.160.221.19', '2021-10-02 04:24:40', 1, 0, '河南省 郑州市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (905, '100000003', '1d822cff', '123.160.221.19', '2021-10-02 04:24:47', 1, 0, '河南省 郑州市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (906, '100000071', '189a053b', '123.160.221.19', '2021-10-02 04:24:58', 1, 0, '河南省 郑州市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (907, '100000005', 'd682a1f6', '39.144.5.83', '2021-10-02 06:05:34', 34, 272, ' ', 40691);
INSERT INTO `sys_monitor_receive_log` VALUES (908, '100000005', '5fcb2faa', '39.144.7.9', '2021-10-02 06:17:12', 11, 88, ' ', 40699);
INSERT INTO `sys_monitor_receive_log` VALUES (909, '100000005', 'c4425514', '39.144.17.203', '2021-10-02 08:00:43', 6, 48, ' ', 40715);
INSERT INTO `sys_monitor_receive_log` VALUES (910, '100000005', 'd7e38839', '39.144.5.239', '2021-10-02 08:33:29', 32, 256, ' ', 40739);
INSERT INTO `sys_monitor_receive_log` VALUES (911, '100000005', '3a538041', '39.144.15.254', '2021-10-02 14:32:07', 40, 320, ' ', 40811);
INSERT INTO `sys_monitor_receive_log` VALUES (912, '100000005', '15ae3c86', '117.132.192.79', '2021-10-02 15:04:15', 40, 320, '北京市 北京市', 40867);
INSERT INTO `sys_monitor_receive_log` VALUES (913, '100000005', '71e33811', '39.144.9.206', '2021-10-02 15:08:16', 40, 320, ' ', 40931);
INSERT INTO `sys_monitor_receive_log` VALUES (914, '100000005', '066c36be', '39.144.14.143', '2021-10-02 16:04:40', 26, 208, ' ', 40971);
INSERT INTO `sys_monitor_receive_log` VALUES (915, '100000005', '19c824e4', '117.132.193.216', '2021-10-02 16:08:41', 35, 280, '北京市 北京市', 41019);
INSERT INTO `sys_monitor_receive_log` VALUES (916, '100000005', 'e1fed812', '117.132.192.227', '2021-10-02 16:28:46', 35, 280, '北京市 北京市', 41051);
INSERT INTO `sys_monitor_receive_log` VALUES (917, '100000005', '3e4d86fd', '221.178.124.142', '2021-10-02 23:26:30', 35, 280, '重庆市 重庆市', 41083);
INSERT INTO `sys_monitor_receive_log` VALUES (918, '100000003', 'c892d326', '94.102.56.229', '2021-10-03 01:00:21', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (919, '100000005', '254162fb', '39.144.7.97', '2021-10-03 02:08:51', 40, 320, ' ', 41115);
INSERT INTO `sys_monitor_receive_log` VALUES (920, '100000005', '935a9d68', '117.132.195.162', '2021-10-03 03:49:19', 40, 320, '北京市 北京市', 41147);
INSERT INTO `sys_monitor_receive_log` VALUES (921, '100000005', '664c694d', '117.132.196.155', '2021-10-03 05:01:44', 28, 224, '北京市 北京市', 41179);
INSERT INTO `sys_monitor_receive_log` VALUES (922, '100000005', 'e4b2cd94', '39.144.5.53', '2021-10-03 05:17:37', 40, 320, ' ', 41211);
INSERT INTO `sys_monitor_receive_log` VALUES (923, '100000071', '2ab3359c', '147.182.209.46', '2021-10-03 05:47:39', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (924, '100000005', '786831b3', '39.144.7.245', '2021-10-03 05:53:50', 40, 320, ' ', 41243);
INSERT INTO `sys_monitor_receive_log` VALUES (925, '100000005', '62d71143', '218.204.253.67', '2021-10-03 06:09:51', 40, 320, '广东省 广州市', 41267);
INSERT INTO `sys_monitor_receive_log` VALUES (926, '100000071', '51cd1597', '101.133.140.205', '2021-10-03 06:11:41', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (927, '100000003', '0cd2ccc3', '101.133.140.205', '2021-10-03 06:12:21', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (928, '100000108', '02e82c57', '101.133.140.205', '2021-10-03 06:12:36', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (929, '100000071', '082aefed', '101.133.140.205', '2021-10-03 06:12:41', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (930, '100000003', 'e8c8d1ce', '101.133.140.205', '2021-10-03 06:13:08', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (931, '100000005', 'd1c4ec20', '101.133.140.205', '2021-10-03 06:13:52', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (932, '100000005', '7f73cde7', '39.144.11.45', '2021-10-03 06:13:55', 40, 320, ' ', 41299);
INSERT INTO `sys_monitor_receive_log` VALUES (933, '100000067', 'db4de297', '101.133.140.205', '2021-10-03 06:14:27', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (934, '100000071', 'e8ed5b36', '101.133.140.205', '2021-10-03 06:15:05', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (935, '100000071', 'ea67d55a', '101.133.140.205', '2021-10-03 06:15:47', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (936, '100000071', 'bf3c4538', '101.133.140.205', '2021-10-03 06:16:00', 1, 8, '北京市 北京市', 56);
INSERT INTO `sys_monitor_receive_log` VALUES (937, '100000067', '0934b780', '101.133.140.205', '2021-10-03 06:16:08', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (938, '100000005', '90f8d302', '117.132.196.163', '2021-10-03 06:58:03', 40, 320, '北京市 北京市', 41331);
INSERT INTO `sys_monitor_receive_log` VALUES (939, '100000005', '572f34de', '221.178.127.236', '2021-10-03 08:02:21', 40, 320, '重庆市 重庆市', 41355);
INSERT INTO `sys_monitor_receive_log` VALUES (940, '100000005', 'cb35e869', '39.144.17.114', '2021-10-03 08:10:09', 26, 208, ' ', 41379);
INSERT INTO `sys_monitor_receive_log` VALUES (941, '100000005', '49d6d286', '39.144.5.105', '2021-10-03 08:14:34', 33, 264, ' ', 41403);
INSERT INTO `sys_monitor_receive_log` VALUES (942, '100000003', 'ea9af078', '87.251.75.206', '2021-10-03 08:16:39', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (943, '100000005', '2911d92b', '39.144.16.245', '2021-10-03 08:22:24', 40, 320, ' ', 41427);
INSERT INTO `sys_monitor_receive_log` VALUES (944, '100000005', '5b7f818b', '39.144.8.33', '2021-10-03 08:30:36', 34, 272, ' ', 41443);
INSERT INTO `sys_monitor_receive_log` VALUES (945, '100000005', '7b08dc3c', '39.144.4.249', '2021-10-03 08:38:28', 40, 320, ' ', 41483);
INSERT INTO `sys_monitor_receive_log` VALUES (946, '100000005', 'f1ccc571', '223.104.255.25', '2021-10-03 09:22:21', 18, 144, '广东省 广州市', 41499);
INSERT INTO `sys_monitor_receive_log` VALUES (947, '100000003', '411051f2', '185.219.52.105', '2021-10-03 16:56:41', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (948, '100000005', '884e6f8a', '39.144.14.210', '2021-10-03 17:47:20', 35, 280, ' ', 41523);
INSERT INTO `sys_monitor_receive_log` VALUES (949, '100000005', '0105fdda', '117.132.194.235', '2021-10-03 23:00:35', 33, 264, '北京市 北京市', 41547);
INSERT INTO `sys_monitor_receive_log` VALUES (950, '100000003', '8321bd8e', '45.155.205.127', '2021-10-04 03:31:59', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (951, '100000071', 'bbb9bc8a', '101.133.225.252', '2021-10-04 03:40:24', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (952, '100000003', '56351a4e', '101.133.225.252', '2021-10-04 03:41:02', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (953, '100000108', 'b21db10d', '101.133.225.252', '2021-10-04 03:41:18', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (954, '100000071', '6bee6410', '101.133.225.252', '2021-10-04 03:41:22', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (955, '100000003', 'eed11eb2', '101.133.225.252', '2021-10-04 03:41:49', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (956, '100000005', 'c649d01d', '101.133.225.252', '2021-10-04 03:42:32', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (957, '100000067', 'fa05f223', '101.133.225.252', '2021-10-04 03:43:06', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (958, '100000071', 'cee9ebe7', '101.133.225.252', '2021-10-04 03:43:44', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (959, '100000071', '2ed59e2f', '101.133.225.252', '2021-10-04 03:44:26', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (960, '100000071', 'ed8c0c31', '101.133.225.252', '2021-10-04 03:44:39', 1, 8, '北京市 北京市', 64);
INSERT INTO `sys_monitor_receive_log` VALUES (961, '100000067', '7ce50ae8', '101.133.225.252', '2021-10-04 03:44:48', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (962, '100000005', 'a2e36cee', '39.144.18.96', '2021-10-04 04:37:36', 40, 320, ' ', 41571);
INSERT INTO `sys_monitor_receive_log` VALUES (963, '100000003', '390386da', '45.155.205.127', '2021-10-04 05:54:23', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (964, '100000003', '1a4416a0', '193.106.29.74', '2021-10-04 06:31:41', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (965, '100000005', 'bd8e9a7e', '39.144.1.4', '2021-10-04 06:51:15', 37, 296, ' ', 41595);
INSERT INTO `sys_monitor_receive_log` VALUES (966, '100000005', '09d683b3', '39.144.2.108', '2021-10-04 06:59:15', 40, 320, ' ', 41619);
INSERT INTO `sys_monitor_receive_log` VALUES (967, '100000005', 'de6777a4', '218.204.252.196', '2021-10-04 07:06:37', 3, 24, '广东省 广州市', 41627);
INSERT INTO `sys_monitor_receive_log` VALUES (968, '100000005', '64ac2266', '117.132.192.127', '2021-10-04 07:10:38', 1, 8, '北京市 北京市', 41635);
INSERT INTO `sys_monitor_receive_log` VALUES (969, '100000005', '57c3e7e6', '117.132.192.200', '2021-10-04 07:19:22', 1, 8, '北京市 北京市', 41643);
INSERT INTO `sys_monitor_receive_log` VALUES (970, '100000005', 'ed1321b7', '39.144.8.79', '2021-10-04 07:23:14', 35, 280, ' ', 41667);
INSERT INTO `sys_monitor_receive_log` VALUES (971, '100000005', '3c25e8ff', '117.132.192.141', '2021-10-04 07:47:27', 40, 320, '北京市 北京市', 41691);
INSERT INTO `sys_monitor_receive_log` VALUES (972, '100000005', '8fab949d', '39.144.13.45', '2021-10-04 07:50:50', 8, 64, ' ', 41707);
INSERT INTO `sys_monitor_receive_log` VALUES (973, '100000005', 'd222e26e', '117.132.197.46', '2021-10-04 08:07:28', 40, 320, '北京市 北京市', 41731);
INSERT INTO `sys_monitor_receive_log` VALUES (974, '100000005', 'dc9fef58', '221.178.127.204', '2021-10-04 08:11:06', 20, 160, '重庆市 重庆市', 41747);
INSERT INTO `sys_monitor_receive_log` VALUES (975, '100000003', 'bd4ef3b5', '178.159.37.58', '2021-10-04 08:17:12', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (976, '100000005', '6c85aab1', '117.132.193.185', '2021-10-04 08:27:27', 34, 272, '北京市 北京市', 41771);
INSERT INTO `sys_monitor_receive_log` VALUES (977, '100000005', 'ef2601e5', '39.144.9.85', '2021-10-04 08:35:21', 1, 8, ' ', 41779);
INSERT INTO `sys_monitor_receive_log` VALUES (978, '100000005', 'a183ab88', '39.144.9.95', '2021-10-04 08:42:58', 1, 8, ' ', 41787);
INSERT INTO `sys_monitor_receive_log` VALUES (979, '100000005', '1249632a', '39.144.3.75', '2021-10-04 08:47:41', 16, 128, ' ', 41795);
INSERT INTO `sys_monitor_receive_log` VALUES (980, '100000005', '284034f3', '117.132.197.236', '2021-10-04 13:28:27', 34, 272, '北京市 北京市', 41834);
INSERT INTO `sys_monitor_receive_log` VALUES (981, '100000005', '0580a214', '39.144.9.233', '2021-10-04 15:20:46', 40, 320, ' ', 41866);
INSERT INTO `sys_monitor_receive_log` VALUES (982, '100000005', '83844297', '117.132.198.71', '2021-10-04 16:41:16', 32, 256, '北京市 北京市', 41890);
INSERT INTO `sys_monitor_receive_log` VALUES (983, '100000003', 'f7beb3bc', '89.248.165.31', '2021-10-04 21:16:00', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (984, '100000005', '52a660e8', '117.132.198.77', '2021-10-04 22:18:33', 40, 320, '北京市 北京市', 41914);
INSERT INTO `sys_monitor_receive_log` VALUES (985, '100000005', 'f9bdb4e5', '39.144.13.28', '2021-10-04 23:14:17', 1, 8, ' ', 41922);
INSERT INTO `sys_monitor_receive_log` VALUES (986, '100000005', '1e56f758', '117.132.194.69', '2021-10-04 23:22:36', 28, 224, '北京市 北京市', 41946);
INSERT INTO `sys_monitor_receive_log` VALUES (987, '100000005', 'e3f025b0', '223.104.255.64', '2021-10-05 00:17:34', 35, 280, '广东省 广州市', 41970);
INSERT INTO `sys_monitor_receive_log` VALUES (988, '100000005', '95b65164', '39.144.17.142', '2021-10-05 01:33:16', 9, 72, ' ', 41986);
INSERT INTO `sys_monitor_receive_log` VALUES (989, '100000005', '82a5befc', '39.144.18.92', '2021-10-05 03:06:06', 40, 320, ' ', 42010);
INSERT INTO `sys_monitor_receive_log` VALUES (990, '100000005', 'abf829b5', '39.144.3.72', '2021-10-05 03:50:17', 40, 320, ' ', 42034);
INSERT INTO `sys_monitor_receive_log` VALUES (991, '100000005', '535247d7', '39.144.5.221', '2021-10-05 03:58:19', 40, 320, ' ', 42058);
INSERT INTO `sys_monitor_receive_log` VALUES (992, '100000005', 'f3060c24', '117.132.197.15', '2021-10-05 05:14:39', 40, 320, '北京市 北京市', 42082);
INSERT INTO `sys_monitor_receive_log` VALUES (993, '100000071', 'ffa5d83d', '162.142.125.128', '2021-10-05 05:23:02', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (994, '100000071', '62166b39', '106.14.45.145', '2021-10-05 05:35:26', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (995, '100000003', '1a22f408', '106.14.45.145', '2021-10-05 05:36:00', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (996, '100000108', 'e1578579', '106.14.45.145', '2021-10-05 05:36:15', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (997, '100000071', 'dcc7ac6b', '106.14.45.145', '2021-10-05 05:36:19', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (998, '100000003', 'f5acbcc7', '106.14.45.145', '2021-10-05 05:36:44', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (999, '100000005', 'c8417130', '106.14.45.145', '2021-10-05 05:37:28', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1000, '100000067', 'aab79c98', '106.14.45.145', '2021-10-05 05:38:02', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1001, '100000071', 'a8b04d72', '106.14.45.145', '2021-10-05 05:38:40', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1002, '100000071', 'b4a79901', '106.14.45.145', '2021-10-05 05:39:22', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1003, '100000071', '785629a9', '106.14.45.145', '2021-10-05 05:39:35', 1, 8, '上海市 上海市', 72);
INSERT INTO `sys_monitor_receive_log` VALUES (1004, '100000067', '545cbdf2', '106.14.45.145', '2021-10-05 05:39:43', 1, 0, '上海市 上海市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1005, '100000005', '73c530e4', '221.178.127.156', '2021-10-05 08:51:35', 40, 320, '重庆市 重庆市', 42138);
INSERT INTO `sys_monitor_receive_log` VALUES (1006, '100000003', '47da670b', '89.248.165.41', '2021-10-05 10:36:01', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1007, '100000005', '2ecad3ad', '39.144.6.196', '2021-10-05 11:24:17', 20, 160, ' ', 42154);
INSERT INTO `sys_monitor_receive_log` VALUES (1008, '100000003', '22f9c329', '89.248.165.41', '2021-10-05 13:07:38', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1009, '100000005', 'c14e8892', '39.144.5.32', '2021-10-05 13:08:46', 34, 272, ' ', 42178);
INSERT INTO `sys_monitor_receive_log` VALUES (1010, '100000005', '23b29efc', '39.144.5.195', '2021-10-05 14:33:07', 34, 272, ' ', 42202);
INSERT INTO `sys_monitor_receive_log` VALUES (1011, '100000005', '25a08f30', '221.178.126.162', '2021-10-05 14:37:08', 34, 272, '重庆市 重庆市', 42226);
INSERT INTO `sys_monitor_receive_log` VALUES (1012, '100000005', 'aa78a9a1', '117.132.195.201', '2021-10-05 23:35:00', 25, 200, '北京市 北京市', 42250);
INSERT INTO `sys_monitor_receive_log` VALUES (1013, '100000005', 'aa35b5fd', '39.144.11.141', '2021-10-06 00:43:29', 40, 320, ' ', 42274);
INSERT INTO `sys_monitor_receive_log` VALUES (1014, '100000005', '88cbad43', '39.144.9.0', '2021-10-06 01:51:50', 40, 320, ' ', 42298);
INSERT INTO `sys_monitor_receive_log` VALUES (1015, '100000003', '914277df', '89.248.165.96', '2021-10-06 04:38:57', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1016, '100000005', '2e6a3a51', '117.132.198.4', '2021-10-06 04:40:33', 6, 48, '北京市 北京市', 42314);
INSERT INTO `sys_monitor_receive_log` VALUES (1017, '100000005', '9433a9e9', '39.144.15.55', '2021-10-06 05:14:10', 1, 8, ' ', 42322);
INSERT INTO `sys_monitor_receive_log` VALUES (1018, '100000003', '17c62200', '45.9.20.97', '2021-10-06 05:19:45', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1019, '100000005', '42bb0414', '39.144.3.106', '2021-10-06 05:34:38', 8, 64, ' ', 42338);
INSERT INTO `sys_monitor_receive_log` VALUES (1020, '100000005', '9c29552f', '223.104.255.186', '2021-10-06 06:02:55', 20, 160, '广东省 广州市', 42354);
INSERT INTO `sys_monitor_receive_log` VALUES (1021, '100000005', '4a045d31', '117.132.197.212', '2021-10-06 06:06:42', 7, 56, '北京市 北京市', 42370);
INSERT INTO `sys_monitor_receive_log` VALUES (1022, '100000005', '6654a90f', '117.132.195.222', '2021-10-06 06:14:40', 8, 64, '北京市 北京市', 42386);
INSERT INTO `sys_monitor_receive_log` VALUES (1023, '100000005', 'c06d32ac', '221.178.127.86', '2021-10-06 06:22:37', 4, 32, '重庆市 重庆市', 42402);
INSERT INTO `sys_monitor_receive_log` VALUES (1024, '100000005', '4c1f04be', '117.132.198.103', '2021-10-06 06:54:52', 19, 152, '北京市 北京市', 42418);
INSERT INTO `sys_monitor_receive_log` VALUES (1025, '100000005', '764924db', '39.144.16.36', '2021-10-06 07:26:50', 1, 8, ' ', 42426);
INSERT INTO `sys_monitor_receive_log` VALUES (1026, '100000005', '3f5b96a9', '117.132.192.243', '2021-10-06 07:35:22', 40, 320, '北京市 北京市', 42450);
INSERT INTO `sys_monitor_receive_log` VALUES (1027, '100000005', '1a333733', '117.132.194.150', '2021-10-06 07:43:20', 16, 128, '北京市 北京市', 42466);
INSERT INTO `sys_monitor_receive_log` VALUES (1028, '100000005', '14fb1d10', '39.144.8.2', '2021-10-06 07:51:18', 12, 96, ' ', 42482);
INSERT INTO `sys_monitor_receive_log` VALUES (1029, '100000005', 'b24120bd', '223.104.254.232', '2021-10-06 07:58:59', 1, 8, '北京市 北京市', 42490);
INSERT INTO `sys_monitor_receive_log` VALUES (1030, '100000005', '39a28f18', '221.178.127.176', '2021-10-06 08:03:19', 20, 160, '重庆市 重庆市', 42506);
INSERT INTO `sys_monitor_receive_log` VALUES (1031, '100000005', 'ff3b85b1', '39.144.7.221', '2021-10-06 08:07:16', 4, 32, ' ', 42514);
INSERT INTO `sys_monitor_receive_log` VALUES (1032, '100000005', '4f847d65', '39.144.8.110', '2021-10-06 08:27:13', 14, 112, ' ', 42530);
INSERT INTO `sys_monitor_receive_log` VALUES (1033, '100000005', '0df63082', '117.132.193.17', '2021-10-06 08:31:00', 4, 32, '北京市 北京市', 42538);
INSERT INTO `sys_monitor_receive_log` VALUES (1034, '100000005', '52f48ea2', '39.144.7.22', '2021-10-06 08:43:18', 2, 16, ' ', 42546);
INSERT INTO `sys_monitor_receive_log` VALUES (1035, '100000005', 'd33dc516', '39.144.2.73', '2021-10-06 09:23:29', 18, 144, ' ', 42562);
INSERT INTO `sys_monitor_receive_log` VALUES (1036, '100000005', 'f7fa5788', '39.144.9.182', '2021-10-06 09:31:56', 23, 184, ' ', 42578);
INSERT INTO `sys_monitor_receive_log` VALUES (1037, '100000005', 'd8a5e8e2', '39.144.2.140', '2021-10-06 09:35:42', 5, 40, ' ', 42586);
INSERT INTO `sys_monitor_receive_log` VALUES (1038, '100000005', '1879fbdf', '39.144.15.43', '2021-10-06 10:03:43', 14, 112, ' ', 42602);
INSERT INTO `sys_monitor_receive_log` VALUES (1039, '100000005', '80df20a9', '39.144.17.105', '2021-10-06 10:12:06', 16, 128, ' ', 42618);
INSERT INTO `sys_monitor_receive_log` VALUES (1040, '100000005', '71638c34', '39.144.5.129', '2021-10-06 10:23:45', 5, 40, ' ', 42626);
INSERT INTO `sys_monitor_receive_log` VALUES (1041, '100000005', '11598bc0', '223.104.255.236', '2021-10-06 11:11:44', 1, 8, '广东省 广州市', 42634);
INSERT INTO `sys_monitor_receive_log` VALUES (1042, '100000005', '286f12ec', '117.132.192.25', '2021-10-06 11:20:03', 10, 80, '北京市 北京市', 42650);
INSERT INTO `sys_monitor_receive_log` VALUES (1043, '100000005', '4d8d338e', '221.178.127.94', '2021-10-06 11:24:13', 2, 16, '重庆市 重庆市', 42658);
INSERT INTO `sys_monitor_receive_log` VALUES (1044, '100000005', '1b880e5d', '117.132.195.163', '2021-10-06 11:31:51', 2, 16, '北京市 北京市', 42666);
INSERT INTO `sys_monitor_receive_log` VALUES (1045, '100000005', 'bf3de847', '39.144.9.163', '2021-10-06 11:36:04', 2, 16, ' ', 42674);
INSERT INTO `sys_monitor_receive_log` VALUES (1046, '100000005', '5c55fa06', '39.144.16.2', '2021-10-06 11:40:31', 32, 256, ' ', 42690);
INSERT INTO `sys_monitor_receive_log` VALUES (1047, '100000005', '4d99c5e3', '117.132.195.33', '2021-10-06 12:00:28', 9, 72, '北京市 北京市', 42698);
INSERT INTO `sys_monitor_receive_log` VALUES (1048, '100000005', '386faa56', '221.178.126.22', '2021-10-06 12:12:39', 38, 304, '重庆市 重庆市', 42722);
INSERT INTO `sys_monitor_receive_log` VALUES (1049, '100000005', '9f6bd66b', '39.144.9.212', '2021-10-06 12:20:06', 4, 32, ' ', 42738);
INSERT INTO `sys_monitor_receive_log` VALUES (1050, '100000071', 'b55bdb71', '167.248.133.60', '2021-10-06 12:26:04', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1051, '100000005', 'e73329bb', '39.144.10.216', '2021-10-06 12:32:11', 9, 72, ' ', 42746);
INSERT INTO `sys_monitor_receive_log` VALUES (1052, '100000005', '1e54f9d7', '117.132.195.25', '2021-10-06 14:08:58', 40, 320, '北京市 北京市', 42770);
INSERT INTO `sys_monitor_receive_log` VALUES (1053, '100000005', 'a7be6ad0', '221.178.126.47', '2021-10-06 23:03:11', 40, 320, '重庆市 重庆市', 42794);
INSERT INTO `sys_monitor_receive_log` VALUES (1054, '100000005', '699aa5d4', '221.178.125.0', '2021-10-07 00:11:16', 1, 8, '重庆市 重庆市', 42802);
INSERT INTO `sys_monitor_receive_log` VALUES (1055, '100000005', 'bb658a5f', '223.104.255.88', '2021-10-07 01:31:51', 38, 304, '广东省 广州市', 42826);
INSERT INTO `sys_monitor_receive_log` VALUES (1056, '100000005', '989f6753', '117.132.192.206', '2021-10-07 02:28:12', 33, 264, '北京市 北京市', 42850);
INSERT INTO `sys_monitor_receive_log` VALUES (1057, '100000005', '3415941c', '117.132.196.35', '2021-10-07 05:12:39', 13, 104, '北京市 北京市', 42866);
INSERT INTO `sys_monitor_receive_log` VALUES (1058, '100000005', 'bffaeab1', '117.132.191.133', '2021-10-07 05:32:33', 6, 48, '北京市 北京市', 42874);
INSERT INTO `sys_monitor_receive_log` VALUES (1059, '100000005', '3782bb8e', '117.132.193.59', '2021-10-07 05:36:23', 1, 8, '北京市 北京市', 42882);
INSERT INTO `sys_monitor_receive_log` VALUES (1060, '100000005', '43933d34', '39.144.14.209', '2021-10-07 05:52:18', 1, 8, ' ', 42890);
INSERT INTO `sys_monitor_receive_log` VALUES (1061, '100000005', '57ee429e', '117.132.192.174', '2021-10-07 06:00:54', 40, 320, '北京市 北京市', 42914);
INSERT INTO `sys_monitor_receive_log` VALUES (1062, '100000108', '2c5155c0', '186.85.252.85', '2021-10-07 06:12:38', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1063, '100000005', '33733166', '39.144.4.188', '2021-10-07 06:24:52', 3, 24, ' ', 42922);
INSERT INTO `sys_monitor_receive_log` VALUES (1064, '100000005', '5091524c', '218.204.252.35', '2021-10-07 07:29:25', 16, 128, '广东省 广州市', 42932);
INSERT INTO `sys_monitor_receive_log` VALUES (1065, '100000005', '2ed3454e', '117.132.196.24', '2021-10-07 07:49:15', 6, 48, '北京市 北京市', 42940);
INSERT INTO `sys_monitor_receive_log` VALUES (1066, '100000005', '5822bd16', '223.104.255.83', '2021-10-07 08:21:40', 37, 296, '广东省 广州市', 42964);
INSERT INTO `sys_monitor_receive_log` VALUES (1067, '100000005', '0720be25', '117.132.198.144', '2021-10-07 08:37:30', 9, 72, '北京市 北京市', 42972);
INSERT INTO `sys_monitor_receive_log` VALUES (1068, '100000005', '48457118', '218.204.253.245', '2021-10-07 08:45:19', 9, 72, '广东省 广州市', 42980);
INSERT INTO `sys_monitor_receive_log` VALUES (1069, '100000005', '1b6a6af5', '117.132.196.249', '2021-10-07 08:49:47', 34, 272, '北京市 北京市', 43004);
INSERT INTO `sys_monitor_receive_log` VALUES (1070, '100000005', '9e7e20c5', '39.144.15.202', '2021-10-07 09:17:15', 1, 8, ' ', 43012);
INSERT INTO `sys_monitor_receive_log` VALUES (1071, '100000005', '21dc72b9', '223.104.254.91', '2021-10-07 10:10:06', 35, 280, '北京市 北京市', 43036);
INSERT INTO `sys_monitor_receive_log` VALUES (1072, '100000005', '63d72682', '117.132.191.230', '2021-10-07 11:58:33', 40, 320, '北京市 北京市', 43060);
INSERT INTO `sys_monitor_receive_log` VALUES (1073, '100000071', '98493d35', '183.136.225.14', '2021-10-07 12:53:51', 1, 0, '浙江省 嘉兴市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1074, '100000005', 'cfb51c82', '218.204.252.102', '2021-10-07 18:32:12', 34, 272, '广东省 广州市', 43084);
INSERT INTO `sys_monitor_receive_log` VALUES (1075, '100000005', '951da599', '39.144.13.80', '2021-10-07 21:53:02', 33, 264, ' ', 43108);
INSERT INTO `sys_monitor_receive_log` VALUES (1076, '100000003', '6c7e7db2', '78.128.91.5', '2021-10-07 23:09:19', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1077, '100000005', '5c1391ca', '39.144.11.115', '2021-10-08 01:24:13', 15, 120, ' ', 43116);
INSERT INTO `sys_monitor_receive_log` VALUES (1078, '100000005', '9f5bb659', '221.178.124.209', '2021-10-08 06:17:04', 13, 104, '重庆市 重庆市', 43124);
INSERT INTO `sys_monitor_receive_log` VALUES (1079, '100000005', '15902772', '117.132.196.174', '2021-10-08 08:30:12', 31, 248, '北京市 北京市', 43148);
INSERT INTO `sys_monitor_receive_log` VALUES (1080, '100000003', '1a69182e', '89.248.165.82', '2021-10-08 09:31:56', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1081, '100000005', '7a9821bd', '39.144.15.152', '2021-10-08 11:54:53', 40, 320, ' ', 43172);
INSERT INTO `sys_monitor_receive_log` VALUES (1082, '100000005', '09ae14fc', '39.144.5.15', '2021-10-09 00:09:56', 40, 320, ' ', 43196);
INSERT INTO `sys_monitor_receive_log` VALUES (1083, '100000005', '8d284b27', '39.144.8.33', '2021-10-09 00:13:58', 40, 320, ' ', 43220);
INSERT INTO `sys_monitor_receive_log` VALUES (1084, '100000005', '54bba7b9', '117.132.196.179', '2021-10-09 02:14:06', 40, 320, '北京市 北京市', 43244);
INSERT INTO `sys_monitor_receive_log` VALUES (1085, '100000005', '13b5ef57', '223.104.255.192', '2021-10-09 02:43:45', 22, 176, '广东省 广州市', 43260);
INSERT INTO `sys_monitor_receive_log` VALUES (1086, '100000005', 'ce72585b', '117.132.193.189', '2021-10-09 02:48:05', 40, 320, '北京市 北京市', 43284);
INSERT INTO `sys_monitor_receive_log` VALUES (1087, '100000005', '023b7861', '39.144.7.113', '2021-10-09 03:36:13', 40, 320, ' ', 43308);
INSERT INTO `sys_monitor_receive_log` VALUES (1088, '100000005', 'cb579ef0', '39.144.11.58', '2021-10-09 04:48:32', 40, 320, ' ', 43332);
INSERT INTO `sys_monitor_receive_log` VALUES (1089, '100000005', 'c589853a', '39.144.5.90', '2021-10-09 06:11:32', 40, 320, ' ', 43364);
INSERT INTO `sys_monitor_receive_log` VALUES (1090, '100000005', '72987834', '221.178.127.195', '2021-10-09 08:28:08', 40, 320, '重庆市 重庆市', 43388);
INSERT INTO `sys_monitor_receive_log` VALUES (1091, '100000005', 'd2d73baf', '117.132.193.254', '2021-10-09 08:52:11', 40, 320, '北京市 北京市', 43412);
INSERT INTO `sys_monitor_receive_log` VALUES (1092, '100000005', 'cc95582e', '221.178.125.186', '2021-10-09 08:56:25', 34, 272, '重庆市 重庆市', 43428);
INSERT INTO `sys_monitor_receive_log` VALUES (1093, '100000005', '70a3be5f', '39.144.2.41', '2021-10-09 09:44:34', 35, 280, ' ', 43452);
INSERT INTO `sys_monitor_receive_log` VALUES (1094, '100000005', '52575982', '39.144.2.141', '2021-10-09 11:45:05', 34, 272, ' ', 43476);
INSERT INTO `sys_monitor_receive_log` VALUES (1095, '100000005', '70ea7cce', '39.144.16.5', '2021-10-09 14:21:43', 35, 280, ' ', 43500);
INSERT INTO `sys_monitor_receive_log` VALUES (1096, '100000005', '6bcfbae1', '39.144.3.183', '2021-10-09 14:33:46', 32, 256, ' ', 43524);
INSERT INTO `sys_monitor_receive_log` VALUES (1097, '100000005', '69e9c8bb', '39.144.8.69', '2021-10-09 16:53:47', 5, 40, ' ', 43532);
INSERT INTO `sys_monitor_receive_log` VALUES (1098, '100000005', '1006e48b', '39.144.15.21', '2021-10-09 20:11:10', 31, 248, ' ', 43556);
INSERT INTO `sys_monitor_receive_log` VALUES (1099, '100000005', '56893991', '117.132.196.109', '2021-10-09 23:29:57', 35, 280, '北京市 北京市', 43580);
INSERT INTO `sys_monitor_receive_log` VALUES (1100, '100000005', '0a4ffcba', '223.104.255.38', '2021-10-10 00:41:53', 40, 320, '广东省 广州市', 43604);
INSERT INTO `sys_monitor_receive_log` VALUES (1101, '100000005', 'a325effc', '39.144.7.157', '2021-10-10 01:05:59', 40, 320, ' ', 43620);
INSERT INTO `sys_monitor_receive_log` VALUES (1102, '100000005', 'f295ca49', '39.144.2.22', '2021-10-10 01:14:02', 40, 320, ' ', 43644);
INSERT INTO `sys_monitor_receive_log` VALUES (1103, '100000005', '9a384c6e', '39.144.16.56', '2021-10-10 03:48:12', 20, 160, ' ', 43668);
INSERT INTO `sys_monitor_receive_log` VALUES (1104, '100000005', 'b375ee71', '117.132.195.236', '2021-10-10 04:36:22', 18, 144, '北京市 北京市', 43684);
INSERT INTO `sys_monitor_receive_log` VALUES (1105, '100000005', 'dcf4b425', '39.144.8.80', '2021-10-10 05:12:53', 40, 320, ' ', 43708);
INSERT INTO `sys_monitor_receive_log` VALUES (1106, '100000005', '118c38e6', '117.132.194.82', '2021-10-10 05:40:58', 40, 320, '北京市 北京市', 43732);
INSERT INTO `sys_monitor_receive_log` VALUES (1107, '100000005', '52044699', '221.178.124.171', '2021-10-10 05:44:59', 40, 320, '重庆市 重庆市', 43756);
INSERT INTO `sys_monitor_receive_log` VALUES (1108, '100000005', '24e30901', '39.144.16.81', '2021-10-10 05:49:00', 40, 320, ' ', 43780);
INSERT INTO `sys_monitor_receive_log` VALUES (1109, '100000005', '6077e5f1', '39.144.7.5', '2021-10-10 06:19:57', 40, 320, ' ', 43804);
INSERT INTO `sys_monitor_receive_log` VALUES (1110, '100000005', '397623dd', '39.144.6.201', '2021-10-10 06:32:02', 40, 320, ' ', 43828);
INSERT INTO `sys_monitor_receive_log` VALUES (1111, '100000005', '0e823440', '117.132.194.160', '2021-10-10 06:36:06', 40, 320, '北京市 北京市', 43852);
INSERT INTO `sys_monitor_receive_log` VALUES (1112, '100000005', '033fae61', '117.132.194.86', '2021-10-10 07:48:18', 40, 320, '北京市 北京市', 43884);
INSERT INTO `sys_monitor_receive_log` VALUES (1113, '100000005', '65d64137', '39.144.4.85', '2021-10-10 07:55:50', 3, 24, ' ', 43892);
INSERT INTO `sys_monitor_receive_log` VALUES (1114, '100000005', '9837d34e', '221.178.126.120', '2021-10-10 08:19:52', 3, 24, '重庆市 重庆市', 43908);
INSERT INTO `sys_monitor_receive_log` VALUES (1115, '100000005', 'e096f50c', '117.132.195.163', '2021-10-10 08:24:28', 40, 320, '北京市 北京市', 43980);
INSERT INTO `sys_monitor_receive_log` VALUES (1116, '100000005', 'cd571d9b', '218.204.252.53', '2021-10-10 08:36:31', 40, 320, '广东省 广州市', 44044);
INSERT INTO `sys_monitor_receive_log` VALUES (1117, '100000005', '981554c5', '117.132.191.60', '2021-10-10 09:00:10', 33, 264, '北京市 北京市', 44092);
INSERT INTO `sys_monitor_receive_log` VALUES (1118, '100000003', '2cb6abe5', '77.83.36.32', '2021-10-10 10:56:53', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1119, '100000071', '9fdc82e7', '183.136.225.14', '2021-10-10 11:08:10', 1, 0, '浙江省 嘉兴市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1120, '100000003', '77df4025', '178.159.37.58', '2021-10-10 11:27:22', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1121, '100000005', 'a47cab3c', '223.104.254.66', '2021-10-10 13:52:35', 40, 320, '北京市 北京市', 44156);
INSERT INTO `sys_monitor_receive_log` VALUES (1122, '100000005', '99b24021', '117.132.192.72', '2021-10-10 21:10:34', 35, 280, '北京市 北京市', 44196);
INSERT INTO `sys_monitor_receive_log` VALUES (1123, '100000005', '6a988a0b', '117.132.195.10', '2021-10-10 22:14:50', 35, 280, '北京市 北京市', 44220);
INSERT INTO `sys_monitor_receive_log` VALUES (1124, '100000005', 'a9bb54a3', '39.144.8.98', '2021-10-11 00:18:22', 30, 240, ' ', 44244);
INSERT INTO `sys_monitor_receive_log` VALUES (1125, '100000005', 'c9910765', '221.178.125.236', '2021-10-11 01:43:22', 4, 32, '重庆市 重庆市', 44252);
INSERT INTO `sys_monitor_receive_log` VALUES (1126, '100000005', '2dd3bcf4', '117.132.196.205', '2021-10-11 02:11:48', 22, 176, '北京市 北京市', 44268);
INSERT INTO `sys_monitor_receive_log` VALUES (1127, '100000005', '7bb7d3f7', '221.178.127.188', '2021-10-11 02:56:14', 40, 320, '重庆市 重庆市', 44292);
INSERT INTO `sys_monitor_receive_log` VALUES (1128, '100000005', '1ce5e1c3', '39.144.2.215', '2021-10-11 03:24:18', 32, 256, ' ', 44316);
INSERT INTO `sys_monitor_receive_log` VALUES (1129, '100000005', '3fcf43d8', '39.144.12.140', '2021-10-11 04:51:22', 40, 320, ' ', 44348);
INSERT INTO `sys_monitor_receive_log` VALUES (1130, '100000005', '212a1b95', '117.132.191.105', '2021-10-11 05:11:37', 31, 248, '北京市 北京市', 44372);
INSERT INTO `sys_monitor_receive_log` VALUES (1131, '100000005', '063120e1', '39.144.9.65', '2021-10-11 05:23:30', 40, 320, ' ', 44404);
INSERT INTO `sys_monitor_receive_log` VALUES (1132, '100000005', '9d6c8fa0', '39.144.18.117', '2021-10-11 05:39:11', 13, 104, ' ', 44420);
INSERT INTO `sys_monitor_receive_log` VALUES (1133, '100000005', 'bb06433b', '223.104.255.98', '2021-10-11 05:47:38', 40, 320, '广东省 广州市', 44444);
INSERT INTO `sys_monitor_receive_log` VALUES (1134, '100000005', 'f5c84f9f', '39.144.18.127', '2021-10-11 06:39:48', 40, 320, ' ', 44476);
INSERT INTO `sys_monitor_receive_log` VALUES (1135, '100000005', 'ad61096f', '117.132.192.106', '2021-10-11 07:07:58', 38, 304, '北京市 北京市', 44508);
INSERT INTO `sys_monitor_receive_log` VALUES (1136, '100000005', '0b77cd23', '39.144.14.253', '2021-10-11 08:11:44', 10, 80, ' ', 44516);
INSERT INTO `sys_monitor_receive_log` VALUES (1137, '100000005', '87dbe0f5', '39.144.7.218', '2021-10-11 08:16:13', 40, 320, ' ', 44540);
INSERT INTO `sys_monitor_receive_log` VALUES (1138, '100000005', 'a5d2af0d', '117.132.198.185', '2021-10-11 08:27:47', 8, 64, '北京市 北京市', 44556);
INSERT INTO `sys_monitor_receive_log` VALUES (1139, '100000005', 'fdc8647d', '39.144.18.138', '2021-10-11 09:12:29', 40, 320, ' ', 44580);
INSERT INTO `sys_monitor_receive_log` VALUES (1140, '100000005', 'c4b704c3', '117.132.196.158', '2021-10-11 09:16:27', 40, 320, '北京市 北京市', 44604);
INSERT INTO `sys_monitor_receive_log` VALUES (1141, '100000005', 'e94961af', '117.132.191.246', '2021-10-11 09:32:12', 17, 136, '北京市 北京市', 44620);
INSERT INTO `sys_monitor_receive_log` VALUES (1142, '100000005', 'f738a47c', '39.144.16.161', '2021-10-11 09:44:34', 40, 320, ' ', 44692);
INSERT INTO `sys_monitor_receive_log` VALUES (1143, '100000005', '20ab2acf', '117.132.191.42', '2021-10-11 17:30:40', 33, 264, '北京市 北京市', 44716);
INSERT INTO `sys_monitor_receive_log` VALUES (1144, '100000005', '706a61c4', '117.132.198.46', '2021-10-11 18:02:48', 35, 280, '北京市 北京市', 44748);
INSERT INTO `sys_monitor_receive_log` VALUES (1145, '100000071', 'c182d760', '162.142.125.128', '2021-10-11 21:47:33', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1146, '100000005', '9807e3cf', '117.132.191.46', '2021-10-12 00:28:25', 31, 248, '北京市 北京市', 44772);
INSERT INTO `sys_monitor_receive_log` VALUES (1147, '100000005', '58a29c8b', '39.144.3.88', '2021-10-12 00:43:46', 4, 32, ' ', 44788);
INSERT INTO `sys_monitor_receive_log` VALUES (1148, '100000005', 'c0ff5701', '39.144.8.78', '2021-10-12 01:08:29', 40, 320, ' ', 44812);
INSERT INTO `sys_monitor_receive_log` VALUES (1149, '100000005', '5c8da94a', '221.178.126.234', '2021-10-12 01:20:28', 40, 320, '重庆市 重庆市', 44876);
INSERT INTO `sys_monitor_receive_log` VALUES (1150, '100000005', 'a4b40279', '117.132.196.112', '2021-10-12 01:40:34', 40, 320, '北京市 北京市', 44940);
INSERT INTO `sys_monitor_receive_log` VALUES (1151, '100000071', 'b8075e61', '101.133.148.169', '2021-10-12 01:43:43', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1152, '100000003', 'e5a7a991', '101.133.148.169', '2021-10-12 01:44:18', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1153, '100000108', 'ecce85f9', '101.133.148.169', '2021-10-12 01:44:32', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1154, '100000071', 'cfb11309', '101.133.148.169', '2021-10-12 01:44:36', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1155, '100000005', '18b7b528', '39.144.3.253', '2021-10-12 01:44:36', 40, 320, ' ', 44988);
INSERT INTO `sys_monitor_receive_log` VALUES (1156, '100000003', 'f8dfcefe', '101.133.148.169', '2021-10-12 01:45:02', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1157, '100000005', '745bdc7b', '101.133.148.169', '2021-10-12 01:45:45', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1158, '100000067', '15cb65bd', '101.133.148.169', '2021-10-12 01:46:19', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1159, '100000071', '6dbb021d', '101.133.148.169', '2021-10-12 01:46:57', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1160, '100000071', 'db5e5327', '101.133.148.169', '2021-10-12 01:47:40', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1161, '100000071', 'd213a4e4', '101.133.148.169', '2021-10-12 01:47:52', 1, 8, '北京市 北京市', 80);
INSERT INTO `sys_monitor_receive_log` VALUES (1162, '100000067', '58ad1c50', '101.133.148.169', '2021-10-12 01:48:01', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1163, '100000005', 'b8257d39', '218.204.252.40', '2021-10-12 01:48:36', 40, 320, '广东省 广州市', 45020);
INSERT INTO `sys_monitor_receive_log` VALUES (1164, '100000005', '21265025', '39.144.5.92', '2021-10-12 03:12:56', 40, 320, ' ', 45060);
INSERT INTO `sys_monitor_receive_log` VALUES (1165, '100000005', '8e62bdb2', '39.144.2.194', '2021-10-12 04:13:11', 40, 320, ' ', 45084);
INSERT INTO `sys_monitor_receive_log` VALUES (1166, '100000005', '17771a1d', '39.144.14.147', '2021-10-12 04:53:03', 20, 160, ' ', 45100);
INSERT INTO `sys_monitor_receive_log` VALUES (1167, '100000005', '6e291875', '117.132.193.244', '2021-10-12 05:33:17', 18, 144, '北京市 北京市', 45116);
INSERT INTO `sys_monitor_receive_log` VALUES (1168, '100000005', 'a809329a', '117.132.196.152', '2021-10-12 06:01:37', 40, 320, '北京市 北京市', 45140);
INSERT INTO `sys_monitor_receive_log` VALUES (1169, '100000005', '6101723d', '39.144.3.9', '2021-10-12 06:25:45', 39, 312, ' ', 45164);
INSERT INTO `sys_monitor_receive_log` VALUES (1170, '100000005', 'd3ff8ffa', '39.144.13.209', '2021-10-12 07:21:40', 19, 152, ' ', 45188);
INSERT INTO `sys_monitor_receive_log` VALUES (1171, '100000005', '4ff153ec', '39.144.15.138', '2021-10-12 07:45:57', 31, 248, ' ', 45212);
INSERT INTO `sys_monitor_receive_log` VALUES (1172, '100000005', '0ecf3c62', '117.132.191.243', '2021-10-12 14:21:19', 40, 320, '北京市 北京市', 45244);
INSERT INTO `sys_monitor_receive_log` VALUES (1173, '100000005', 'c618d44f', '218.204.252.76', '2021-10-12 17:58:23', 27, 216, '广东省 广州市', 45268);
INSERT INTO `sys_monitor_receive_log` VALUES (1174, '100000005', '976bcf74', '39.144.18.200', '2021-10-12 23:07:32', 40, 320, ' ', 45316);
INSERT INTO `sys_monitor_receive_log` VALUES (1175, '100000005', '362d1d62', '117.132.191.253', '2021-10-13 00:35:27', 10, 80, '北京市 北京市', 45324);
INSERT INTO `sys_monitor_receive_log` VALUES (1176, '100000005', 'a881b053', '39.144.5.189', '2021-10-13 01:16:04', 40, 320, ' ', 45348);
INSERT INTO `sys_monitor_receive_log` VALUES (1177, '100000005', '92bef2e2', '117.132.191.52', '2021-10-13 01:48:11', 40, 320, '北京市 北京市', 45372);
INSERT INTO `sys_monitor_receive_log` VALUES (1178, '100000005', 'df15993f', '39.144.10.98', '2021-10-13 02:24:12', 29, 232, ' ', 45396);
INSERT INTO `sys_monitor_receive_log` VALUES (1179, '100000005', '270fe71b', '39.144.7.195', '2021-10-13 02:48:36', 32, 256, ' ', 45420);
INSERT INTO `sys_monitor_receive_log` VALUES (1180, '100000005', '8200a0e1', '39.144.4.252', '2021-10-13 03:32:36', 40, 320, ' ', 45444);
INSERT INTO `sys_monitor_receive_log` VALUES (1181, '100000071', '20e8f678', '39.103.166.103', '2021-10-13 03:37:37', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1182, '100000003', 'dfbc27bf', '39.103.166.103', '2021-10-13 03:38:13', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1183, '100000108', '7803b6d4', '39.103.166.103', '2021-10-13 03:38:28', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1184, '100000071', '972ef710', '39.103.166.103', '2021-10-13 03:38:32', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1185, '100000003', '0f753837', '39.103.166.103', '2021-10-13 03:38:58', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1186, '100000005', '679cf7ef', '39.103.166.103', '2021-10-13 03:39:42', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1187, '100000067', 'a8a3039e', '39.103.166.103', '2021-10-13 03:40:16', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1188, '100000071', '9ca095ec', '39.103.166.103', '2021-10-13 03:40:55', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1189, '100000071', '4cb8e2cc', '39.103.166.103', '2021-10-13 03:41:37', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1190, '100000071', '22d2e5a0', '39.103.166.103', '2021-10-13 03:41:50', 1, 8, '北京市 北京市', 88);
INSERT INTO `sys_monitor_receive_log` VALUES (1191, '100000067', '60bac685', '39.103.166.103', '2021-10-13 03:41:59', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1192, '100000005', '7a772dfb', '218.204.252.10', '2021-10-13 04:24:59', 33, 264, '广东省 广州市', 45468);
INSERT INTO `sys_monitor_receive_log` VALUES (1193, '100000005', 'fe6b6aac', '218.204.252.87', '2021-10-13 05:13:02', 40, 320, '广东省 广州市', 45492);
INSERT INTO `sys_monitor_receive_log` VALUES (1194, '100000005', 'ce04e32e', '39.144.17.1', '2021-10-13 05:24:38', 11, 88, ' ', 45508);
INSERT INTO `sys_monitor_receive_log` VALUES (1195, '100000005', '36fab617', '218.204.253.136', '2021-10-13 05:49:20', 32, 256, '广东省 广州市', 45532);
INSERT INTO `sys_monitor_receive_log` VALUES (1196, '100000005', '42bf1bfd', '39.144.2.6', '2021-10-13 06:13:05', 25, 200, ' ', 45556);
INSERT INTO `sys_monitor_receive_log` VALUES (1197, '100000005', '2ec3ebc6', '221.178.127.173', '2021-10-13 06:17:18', 40, 320, '重庆市 重庆市', 45580);
INSERT INTO `sys_monitor_receive_log` VALUES (1198, '100000003', '3fcb343c', '45.155.205.127', '2021-10-13 06:33:35', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1199, '100000005', '791fa97a', '223.104.254.75', '2021-10-13 06:49:25', 40, 320, '北京市 北京市', 45604);
INSERT INTO `sys_monitor_receive_log` VALUES (1200, '100000003', '359098b4', '45.155.205.127', '2021-10-13 07:24:07', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1201, '100000005', 'd6cd69e2', '39.144.1.143', '2021-10-13 07:41:20', 19, 152, ' ', 45620);
INSERT INTO `sys_monitor_receive_log` VALUES (1202, '100000005', '35013c5f', '39.144.17.42', '2021-10-13 07:45:12', 10, 80, ' ', 45636);
INSERT INTO `sys_monitor_receive_log` VALUES (1203, '100000005', 'ba7fbc18', '39.144.18.217', '2021-10-13 07:53:07', 1, 8, ' ', 45644);
INSERT INTO `sys_monitor_receive_log` VALUES (1204, '100000003', 'df6f7236', '193.106.29.74', '2021-10-13 08:14:25', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1205, '100000005', '0f3879c7', '39.144.5.154', '2021-10-13 08:33:19', 3, 24, ' ', 45652);
INSERT INTO `sys_monitor_receive_log` VALUES (1206, '100000005', '04071c7f', '39.144.3.86', '2021-10-13 09:13:31', 7, 56, ' ', 45668);
INSERT INTO `sys_monitor_receive_log` VALUES (1207, '100000003', 'eccaafeb', '45.155.205.127', '2021-10-13 09:54:46', 1, 0, ' ', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1208, '100000005', '2dd5f7cc', '39.144.8.85', '2021-10-13 14:31:31', 35, 280, ' ', 45692);
INSERT INTO `sys_monitor_receive_log` VALUES (1209, '100000005', '3496c276', '117.132.193.247', '2021-10-13 17:28:17', 34, 272, '北京市 北京市', 45716);
INSERT INTO `sys_monitor_receive_log` VALUES (1210, '100000005', 'e61d4f74', '39.144.4.1', '2021-10-13 18:52:36', 34, 272, ' ', 45740);
INSERT INTO `sys_monitor_receive_log` VALUES (1211, '100000005', 'ca36a4ab', '39.144.13.78', '2021-10-13 23:17:21', 25, 200, ' ', 45756);
INSERT INTO `sys_monitor_receive_log` VALUES (1212, '100000005', '16b2b411', '39.144.12.59', '2021-10-14 00:01:43', 40, 320, ' ', 45780);
INSERT INTO `sys_monitor_receive_log` VALUES (1213, '100000005', 'f268c030', '117.132.194.119', '2021-10-14 00:05:09', 2, 16, '北京市 北京市', 45788);
INSERT INTO `sys_monitor_receive_log` VALUES (1214, '100000005', 'b91e12ef', '39.144.13.147', '2021-10-14 00:41:18', 1, 8, ' ', 45796);
INSERT INTO `sys_monitor_receive_log` VALUES (1215, '100000005', '97c04709', '218.204.253.187', '2021-10-14 01:02:00', 38, 304, '广东省 广州市', 45820);
INSERT INTO `sys_monitor_receive_log` VALUES (1216, '100000005', 'a251bdaf', '221.178.127.86', '2021-10-14 01:06:02', 40, 320, '重庆市 重庆市', 45852);
INSERT INTO `sys_monitor_receive_log` VALUES (1217, '100000005', '89f8103a', '39.144.4.123', '2021-10-14 01:36:40', 40, 320, ' ', 45876);
INSERT INTO `sys_monitor_receive_log` VALUES (1218, '100000071', 'a043f71f', '101.133.138.230', '2021-10-14 01:36:53', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1219, '100000003', '0fe6ddc7', '101.133.138.230', '2021-10-14 01:37:33', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1220, '100000108', 'bb974eb2', '101.133.138.230', '2021-10-14 01:37:49', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1221, '100000071', 'cfd01115', '101.133.138.230', '2021-10-14 01:37:54', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1222, '100000003', '2f8f4dc4', '101.133.138.230', '2021-10-14 01:38:19', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1223, '100000005', '01959ae5', '101.133.138.230', '2021-10-14 01:39:02', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1224, '100000067', 'e185ba36', '101.133.138.230', '2021-10-14 01:39:36', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1225, '100000071', '365251f0', '101.133.138.230', '2021-10-14 01:40:15', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1226, '100000071', 'fa53d3a2', '101.133.138.230', '2021-10-14 01:40:57', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1227, '100000071', 'a8b9ae34', '101.133.138.230', '2021-10-14 01:41:09', 1, 8, '北京市 北京市', 96);
INSERT INTO `sys_monitor_receive_log` VALUES (1228, '100000067', 'c4ced4bf', '101.133.138.230', '2021-10-14 01:41:18', 1, 0, '北京市 北京市', NULL);
INSERT INTO `sys_monitor_receive_log` VALUES (1229, '100000005', '3a76951c', '39.144.13.66', '2021-10-14 01:48:05', 6, 48, ' ', 45892);
INSERT INTO `sys_monitor_receive_log` VALUES (1230, '100000005', 'abd5fc04', '39.144.1.160', '2021-10-14 02:16:21', 16, 128, ' ', 45908);
INSERT INTO `sys_monitor_receive_log` VALUES (1231, '100000005', 'f50369c8', '223.104.255.114', '2021-10-14 02:24:44', 40, 320, '广东省 广州市', 45932);
INSERT INTO `sys_monitor_receive_log` VALUES (1232, '100000005', '2f411f7e', '39.144.14.46', '2021-10-14 02:32:22', 13, 104, ' ', 45948);
INSERT INTO `sys_monitor_receive_log` VALUES (1233, '100000005', 'aaa3c57c', '117.132.198.72', '2021-10-14 02:44:19', 8, 64, '北京市 北京市', 45964);
INSERT INTO `sys_monitor_receive_log` VALUES (1234, '100000005', '3458b7c1', '221.178.127.66', '2021-10-14 02:48:50', 40, 320, '重庆市 重庆市', 45988);
INSERT INTO `sys_monitor_receive_log` VALUES (1235, '100000005', '32e2157a', '39.144.12.163', '2021-10-14 04:07:54', 27, 216, ' ', 46004);
INSERT INTO `sys_monitor_receive_log` VALUES (1236, '100000005', 'cf876276', '223.104.254.200', '2021-10-14 04:12:01', 34, 272, '北京市 北京市', 46020);
INSERT INTO `sys_monitor_receive_log` VALUES (1237, '100000005', 'c4e07abb', '39.144.11.237', '2021-10-14 04:24:08', 40, 320, ' ', 46044);
INSERT INTO `sys_monitor_receive_log` VALUES (1238, '100000005', '21876990', '39.144.3.227', '2021-10-14 04:32:08', 40, 320, ' ', 46068);
INSERT INTO `sys_monitor_receive_log` VALUES (1239, '100000005', 'a3cdfbb3', '39.144.12.245', '2021-10-14 05:00:25', 28, 224, ' ', 46092);
INSERT INTO `sys_monitor_receive_log` VALUES (1240, '100000005', '59f9b15a', '117.132.191.39', '2021-10-14 05:31:53', 4, 32, '北京市 北京市', 46100);
INSERT INTO `sys_monitor_receive_log` VALUES (1241, '100000005', '7a68b044', '39.144.17.96', '2021-10-14 05:44:30', 41, 320, ' ', 46124);
INSERT INTO `sys_monitor_receive_log` VALUES (1242, '100000005', 'b5e135df', '117.132.191.119', '2021-10-14 05:48:18', 27, 216, '北京市 北京市', 46140);
INSERT INTO `sys_monitor_receive_log` VALUES (1243, '100000005', 'fb5d2175', '117.132.196.162', '2021-10-14 06:40:40', 40, 320, '北京市 北京市', 46164);
INSERT INTO `sys_monitor_receive_log` VALUES (1244, '100000005', 'ccd2c0a4', '117.132.197.91', '2021-10-14 07:16:26', 13, 104, '北京市 北京市', 46180);
INSERT INTO `sys_monitor_receive_log` VALUES (1245, '100000005', '104746af', '39.144.1.57', '2021-10-14 07:32:55', 40, 320, ' ', 46204);
INSERT INTO `sys_monitor_receive_log` VALUES (1246, '100000005', '8fb91b1e', '221.178.127.27', '2021-10-14 07:40:57', 40, 320, '重庆市 重庆市', 46232);
INSERT INTO `sys_monitor_receive_log` VALUES (1247, '100000005', 'faca7802', '39.144.9.22', '2021-10-14 08:00:59', 40, 320, ' ', 46264);
INSERT INTO `sys_monitor_receive_log` VALUES (1248, '100000005', '0fe6cfd6', '39.144.11.43', '2021-10-14 08:08:31', 6, 48, ' ', 46280);
INSERT INTO `sys_monitor_receive_log` VALUES (1249, '100000005', '373e3306', '39.144.16.74', '2021-10-14 08:37:08', 40, 320, ' ', 46304);
INSERT INTO `sys_monitor_receive_log` VALUES (1250, '100000005', '9aaffc90', '221.178.127.84', '2021-10-14 08:56:51', 11, 88, '重庆市 重庆市', 46312);
INSERT INTO `sys_monitor_receive_log` VALUES (1251, '100000005', 'dc5079a9', '39.144.9.238', '2021-10-14 09:21:12', 10, 80, ' ', 46328);
INSERT INTO `sys_monitor_receive_log` VALUES (1252, '100000005', 'bcc4f1ed', '39.144.6.153', '2021-10-14 09:45:36', 31, 248, ' ', 46360);
INSERT INTO `sys_monitor_receive_log` VALUES (1253, '100000005', 'b422bb92', '221.178.127.204', '2021-10-14 09:53:30', 38, 304, '重庆市 重庆市', 46384);
INSERT INTO `sys_monitor_receive_log` VALUES (1254, '100000005', '791a1c90', '223.104.254.11', '2021-10-14 09:57:41', 28, 224, '北京市 北京市', 46408);
INSERT INTO `sys_monitor_receive_log` VALUES (1255, '100000005', '22f2750c', '39.144.10.227', '2021-10-14 10:09:10', 5, 40, ' ', 46416);
INSERT INTO `sys_monitor_receive_log` VALUES (1256, '100000005', 'ff42a832', '117.132.195.93', '2021-10-14 10:17:25', 29, 232, '北京市 北京市', 46440);
INSERT INTO `sys_monitor_receive_log` VALUES (1257, '100000005', 'a162d338', '39.144.5.249', '2021-10-14 10:29:38', 40, 320, ' ', 46464);
INSERT INTO `sys_monitor_receive_log` VALUES (1258, '100000005', 'f2842d47', '223.104.255.191', '2021-10-14 11:25:53', 40, 320, '广东省 广州市', 46488);
INSERT INTO `sys_monitor_receive_log` VALUES (1259, '100000005', '8577372b', '39.144.1.124', '2021-10-14 11:53:57', 40, 320, ' ', 46512);
INSERT INTO `sys_monitor_receive_log` VALUES (1260, '100000005', '82df87f2', '117.132.193.4', '2021-10-14 12:25:48', 20, 160, '北京市 北京市', 46528);
INSERT INTO `sys_monitor_receive_log` VALUES (1261, '100000005', '8a63a043', '39.144.2.123', '2021-10-14 13:30:22', 40, 320, ' ', 46568);
INSERT INTO `sys_monitor_receive_log` VALUES (1262, '100000005', 'b13596d9', '39.144.10.123', '2021-10-14 13:46:36', 34, 272, ' ', 46592);
INSERT INTO `sys_monitor_receive_log` VALUES (1263, '100000005', '9f7f41aa', '39.144.3.78', '2021-10-14 15:06:56', 33, 264, ' ', 46616);
INSERT INTO `sys_monitor_receive_log` VALUES (1264, '100000005', 'ae12c6e8', '39.144.14.149', '2021-10-14 16:59:26', 33, 264, ' ', 46640);
INSERT INTO `sys_monitor_receive_log` VALUES (1265, '100000005', '7581e997', '39.144.17.148', '2021-10-15 00:45:10', 40, 320, ' ', 46664);
INSERT INTO `sys_monitor_receive_log` VALUES (1266, '100000005', '46bd074b', '117.132.198.244', '2021-10-15 01:13:22', 40, 320, '北京市 北京市', 46688);
INSERT INTO `sys_monitor_receive_log` VALUES (1267, '100000005', 'd1f29bb0', '117.132.198.247', '2021-10-15 01:16:56', 14, 112, '北京市 北京市', 46696);
INSERT INTO `sys_monitor_receive_log` VALUES (1268, '100000005', 'a0f6b968', '39.144.13.244', '2021-10-15 01:33:22', 40, 320, ' ', 46736);
INSERT INTO `sys_monitor_receive_log` VALUES (1269, '100000005', 'e796274a', '117.132.195.4', '2021-10-15 03:01:56', 34, 272, '北京市 北京市', 46760);
INSERT INTO `sys_monitor_receive_log` VALUES (1270, '100000005', '97bfed23', '39.144.13.45', '2021-10-15 04:26:07', 40, 320, ' ', 46784);
INSERT INTO `sys_monitor_receive_log` VALUES (1271, '100000005', '6d723bfe', '117.132.193.28', '2021-10-15 04:38:08', 40, 320, '北京市 北京市', 46808);
INSERT INTO `sys_monitor_receive_log` VALUES (1272, '100000005', '4c8943e8', '39.144.7.98', '2021-10-15 04:46:10', 40, 320, ' ', 46832);
INSERT INTO `sys_monitor_receive_log` VALUES (1273, '100000005', 'e352dfbb', '117.132.198.126', '2021-10-15 04:49:42', 7, 56, '北京市 北京市', 46848);
INSERT INTO `sys_monitor_receive_log` VALUES (1274, '100000005', '8213503d', '39.144.13.88', '2021-10-15 04:58:09', 11, 88, ' ', 46864);
INSERT INTO `sys_monitor_receive_log` VALUES (1275, '100000005', 'f0e966da', '39.144.5.126', '2021-10-15 05:21:51', 10, 80, ' ', 46880);
INSERT INTO `sys_monitor_receive_log` VALUES (1276, '100000005', '2ea41237', '117.132.197.178', '2021-10-15 05:26:21', 40, 320, '北京市 北京市', 46904);
INSERT INTO `sys_monitor_receive_log` VALUES (1277, '100000005', 'd8c0369a', '39.144.2.139', '2021-10-15 05:49:53', 5, 40, ' ', 46912);
INSERT INTO `sys_monitor_receive_log` VALUES (1278, '100000005', 'a58f75fd', '39.144.3.9', '2021-10-15 06:02:30', 40, 320, ' ', 46936);
INSERT INTO `sys_monitor_receive_log` VALUES (1279, '100000005', '1853032e', '221.178.126.168', '2021-10-15 06:14:33', 40, 320, '重庆市 重庆市', 46960);
INSERT INTO `sys_monitor_receive_log` VALUES (1280, '100000005', '8d5ee2b4', '117.132.196.158', '2021-10-15 06:42:49', 32, 256, '北京市 北京市', 46984);
INSERT INTO `sys_monitor_receive_log` VALUES (1281, '100000005', '59c1dad6', '39.144.12.29', '2021-10-15 07:30:43', 28, 224, ' ', 47000);
INSERT INTO `sys_monitor_receive_log` VALUES (1282, '100000005', 'f97b8aa9', '39.144.6.212', '2021-10-15 08:19:00', 35, 280, ' ', 47024);
INSERT INTO `sys_monitor_receive_log` VALUES (1283, '100000005', '74c52a5b', '117.132.192.200', '2021-10-15 08:46:42', 10, 80, '北京市 北京市', 47040);
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
INSERT INTO `sys_role_menu` VALUES (2, 20);
INSERT INTO `sys_role_menu` VALUES (3, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 3);
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
-- Table structure for sys_view_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_view_menu`;
CREATE TABLE `sys_view_menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `api_path` varchar(255) DEFAULT NULL COMMENT '请求路由路径',
  `role_code` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `path` varchar(255) DEFAULT NULL COMMENT '前端路径',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  `hidden` int(11) DEFAULT NULL COMMENT '是否隐藏',
  `always_show` varchar(255) DEFAULT NULL COMMENT '是否长显示',
  `redirect` varchar(255) DEFAULT NULL COMMENT '定向地址',
  `is_base_node` varchar(255) DEFAULT NULL COMMENT '是否为根节点',
  `upper_node` int(11) DEFAULT NULL COMMENT '上层节点',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updater` varchar(255) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COMMENT='系统路由配置';

-- ----------------------------
-- Records of sys_view_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_view_menu` VALUES (1, 'UserInfo', '个人中心', 'peoples', NULL, NULL, '/user-info', 'Layout', 1, '0', '/user-info/index', '1', NULL, '2021-10-10 15:37:01', 'admin', '2021-10-10 15:37:08', 'admin');
INSERT INTO `sys_view_menu` VALUES (2, 'UserIndex', '个人信息', 'lock', NULL, NULL, 'index', 'user-info/index', 0, '0', NULL, '0', 1, '2021-10-10 15:38:37', 'admin', '2021-10-10 15:38:39', 'admin');
INSERT INTO `sys_view_menu` VALUES (3, 'PersonManager', '账号管理', 'peoples', NULL, NULL, '/person-manager', 'Layout', 0, '1', '/person-manager/index', '1', NULL, '2021-10-10 15:44:20', 'admin', '2021-10-10 15:44:22', 'admin');
INSERT INTO `sys_view_menu` VALUES (4, 'CurdManager', '人员账号管理', 'el-icon-user', NULL, NULL, 'index', 'person-manager/curd', 0, '0', NULL, '0', 3, '2021-10-10 15:47:32', 'admin', '2021-10-10 15:47:34', 'admin');
INSERT INTO `sys_view_menu` VALUES (5, 'GroupManager', '人员组管理', 'el-icon-s-check', NULL, NULL, 'group', 'person-manager/group', 0, '0', NULL, '0', 3, '2021-10-10 15:49:36', 'admin', '2021-10-10 15:49:38', 'admin');
INSERT INTO `sys_view_menu` VALUES (6, 'ProjectManager', '项目管理', 'el-icon-s-management', NULL, NULL, '/project-manager', 'Layout', 0, '1', '/project-manager/overview', '1', NULL, '2021-10-10 15:56:54', 'admin', '2021-10-10 15:57:00', 'admin');
INSERT INTO `sys_view_menu` VALUES (7, 'OverView', '项目总览', 'edit', NULL, NULL, 'overview', 'base-message/overview', 1, '0', NULL, '0', 6, '2021-10-10 16:01:08', 'admin', '2021-10-10 16:01:12', 'admin');
INSERT INTO `sys_view_menu` VALUES (8, 'BaseMessage', '项目列表', 'el-icon-s-grid', NULL, NULL, 'index', 'base-message/index', 0, '0', NULL, '0', 6, '2021-10-10 16:02:35', 'admin', '2021-10-10 16:02:37', 'admin');
INSERT INTO `sys_view_menu` VALUES (9, 'project-detail', '项目详情', NULL, NULL, NULL, 'detail', 'base-message/project-detail', 1, '0', NULL, '0', 6, '2021-10-10 16:07:48', 'admin', '2021-10-10 16:07:51', 'admin');
INSERT INTO `sys_view_menu` VALUES (10, 'AddStructure', '新建项目', NULL, NULL, NULL, 'add', 'base-message/add-structure', 1, '0', NULL, '0', 6, '2021-10-10 16:34:42', 'admin', '2021-10-10 16:34:46', 'admin');
INSERT INTO `sys_view_menu` VALUES (11, 'editorProject', '修改项目', NULL, NULL, NULL, 'editor', 'base-message/editor-structure', 1, '0', NULL, '0', 6, '2021-10-10 16:36:41', 'admin', '2021-10-10 16:36:44', 'admin');
INSERT INTO `sys_view_menu` VALUES (12, 'TimeWatcher', '设备监测', 'el-icon-view', NULL, NULL, 'watcher', 'time-watcher/index', 0, '0', NULL, '0', 6, '2021-10-10 16:37:22', 'admin', '2021-10-10 16:37:24', 'admin');
INSERT INTO `sys_view_menu` VALUES (13, 'DataAnalysis', '数据分析', 'el-icon-s-marketing', NULL, NULL, 'analysis', 'data-analysis/index', 0, '0', NULL, '0', 6, '2021-10-10 16:38:25', 'admin', '2021-10-10 16:38:27', 'admin');
INSERT INTO `sys_view_menu` VALUES (14, 'WarnList', '预警列表', 'el-icon-message-solid', NULL, NULL, 'warn', 'warn/index', 0, '0', NULL, '0', 6, '2021-10-10 16:39:07', 'admin', '2021-10-10 16:39:09', 'admin');
INSERT INTO `sys_view_menu` VALUES (15, 'WarnGroup', '预警组管理', 'el-icon-message-solid', NULL, NULL, 'warn-group', 'warn/group', 0, '0', NULL, '0', 6, '2021-10-10 16:39:48', 'admin', '2021-10-10 16:39:50', 'admin');
INSERT INTO `sys_view_menu` VALUES (16, 'StructureManager', '行业模版', 'el-icon-office-building', NULL, NULL, '/structure-manager', 'Layout', 0, '1', '/structure-manager/index', '1', NULL, '2021-10-10 16:41:01', 'admin', '2021-10-10 16:42:34', 'dmin');
INSERT INTO `sys_view_menu` VALUES (17, 'CheckStructure', '结构物管理', 'el-icon-s-grid', NULL, NULL, 'index', 'check-structure/index', 0, '0', NULL, '0', 16, '2021-10-10 16:43:29', 'admin', '2021-10-10 16:43:37', 'admin');
INSERT INTO `sys_view_menu` VALUES (18, 'EditStructure', '修改结构物', 'edit', NULL, NULL, 'edit', 'check-structure/edit-structure', 1, '0', NULL, '0', 16, '2021-10-10 16:45:29', 'admin', '2021-10-10 16:45:31', 'admin');
INSERT INTO `sys_view_menu` VALUES (19, 'SensorManager', '产品管理', 'el-icon-s-platform', NULL, NULL, '/sensor-manager', 'Layout', 0, '1', '/sensor-manager/index', '1', NULL, '2021-10-10 16:46:31', 'admin', '2021-10-10 16:46:33', 'admin');
INSERT INTO `sys_view_menu` VALUES (20, 'SensorEdit', '传感器配置', 'el-icon-s-help', NULL, NULL, 'index', 'sensor/index', 0, '0', NULL, '0', 19, '2021-10-10 16:49:01', 'admin', '2021-10-10 16:49:09', 'admin');
INSERT INTO `sys_view_menu` VALUES (21, 'SensorModule', '产品模板管理', 'el-icon-s-order', NULL, NULL, 'sensor-model-module', 'sensor-model/index', 0, '0', NULL, '0', 19, '2021-10-10 16:49:04', 'admin', '2021-10-10 16:49:11', 'admin');
COMMIT;

-- ----------------------------
-- Table structure for system_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_permission`;
CREATE TABLE `system_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT '权限URL',
  `comment` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统权限';

-- ----------------------------
-- Records of system_permission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `base_role_code` varchar(255) DEFAULT NULL COMMENT '上层角色编码',
  `role_code` varchar(255) NOT NULL COMMENT '角色编码',
  `role_name` varchar(255) NOT NULL COMMENT '角色名称',
  `is_base_role` varchar(255) DEFAULT NULL COMMENT '是否是根角色',
  `role_status` int(11) DEFAULT NULL COMMENT '角色状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE` (`role_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='系统角色';

-- ----------------------------
-- Records of system_role
-- ----------------------------
BEGIN;
INSERT INTO `system_role` VALUES (1, NULL, 'ROOT_ADMIN', '超级管理员', '1', 1);
INSERT INTO `system_role` VALUES (2, NULL, 'GROUP_ADMIN', '小组管理员', '1', 1);
INSERT INTO `system_role` VALUES (3, NULL, 'USER', '普通用户', '1', 1);
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
  `operate_group_id` int(11) DEFAULT NULL COMMENT '操作组编号',
  `enabled` int(11) DEFAULT NULL COMMENT '是否被销户',
  `account_non_locked` int(11) DEFAULT NULL COMMENT '是否被锁定',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `last_lease` int(11) DEFAULT NULL COMMENT '是否长期有效',
  `lease_start_time` datetime DEFAULT NULL COMMENT '起始时间',
  `lease_time` int(11) DEFAULT NULL COMMENT '租期（月）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX` (`account_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of system_user
-- ----------------------------
BEGIN;
INSERT INTO `system_user` VALUES (1, '100001', 'admin', '$2a$10$/6ArmS07S4n9gMLFN0ENpuEP5cq31AMIynEmRkUpvsvz4lko2OS/2', '北微', NULL, '15161513257', '1436550119@qq.com', 'ROOT_ADMIN', 1, NULL, 1, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_user` VALUES (6, '00123', 'tyler', '$2a$10$GkpWZqbiBpgHgUa4k5MTteEIrhhZ8Fic.DKRocsSV90.UE.USZnou', 'tyler', NULL, '12345', 'www.972237177.com@qq.com', 'USER', 3, NULL, 1, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `system_user` VALUES (7, '100010', 'bwadmin', '$2a$10$709OEKRujh0f1yPS7ixUnOUVNCMBkGAAaHyj5Wfds.T8jTak6XLcm', '张经理', NULL, '12344566', NULL, 'ROOT_ADMIN', 1, NULL, 1, 1, 1, NULL, NULL, NULL, NULL);
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
