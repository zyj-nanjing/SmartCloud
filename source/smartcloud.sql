/*
 Navicat Premium Data Transfer

 Source Server         : DEV——DB
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : smartcloud

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 03/12/2021 23:26:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_token
-- ----------------------------
DROP TABLE IF EXISTS `auth_token`;
CREATE TABLE `auth_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `enableRefresh` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否支持 refreshToken, 默认: 1. 1 表示支持, 0 表示不支持',
  `providerId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '第三方服务商,如: qq,github',
  `accessToken` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'accessToken',
  `expireIn` bigint DEFAULT '-1' COMMENT 'accessToken 过期时间, 无过期时间默认为 -1',
  `refreshTokenExpireIn` bigint DEFAULT '-1' COMMENT 'refreshToken 过期时间, 无过期时间默认为 -1',
  `refreshToken` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'refreshToken',
  `uid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'alipay userId',
  `openId` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'qq/mi/toutiao/wechatMp/wechatOpen/weibo/jd/kujiale/dingTalk/douyin/feishu',
  `accessCode` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'dingTalk, taobao 附带属性',
  `unionId` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'QQ附带属性',
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Google附带属性',
  `tokenType` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Google附带属性',
  `idToken` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Google附带属性',
  `macAlgorithm` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '小米附带属性',
  `macKey` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '小米附带属性',
  `code` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '企业微信附带属性',
  `oauthToken` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Twitter附带属性',
  `oauthTokenSecret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Twitter附带属性',
  `userId` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Twitter附带属性',
  `screenName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Twitter附带属性',
  `oauthCallbackConfirmed` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Twitter附带属性',
  `expireTime` bigint DEFAULT '-1' COMMENT '过期时间, 基于 1970-01-01T00:00:00Z, 无过期时间默认为 -1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

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
  `id` int NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `sensor_no` int DEFAULT NULL COMMENT '传感器编号',
  `model_id` int DEFAULT NULL COMMENT '模型编号',
  `project_id` int DEFAULT NULL,
  `manager_id` int DEFAULT NULL COMMENT '管理员编号',
  `member_group_id` int DEFAULT NULL COMMENT '当前分组编号',
  `position_id` int DEFAULT NULL COMMENT '测点编号',
  `longitude` double(30,10) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '维度',
  `electricity` float DEFAULT NULL COMMENT '电量',
  `first_online_time` datetime DEFAULT NULL COMMENT '初次提交时间',
  `last_send_size` int DEFAULT NULL COMMENT '上一次提交数据条数',
  `total_send_size` int DEFAULT NULL COMMENT '总提交量',
  `last_send_count` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '上一次提交总量',
  `last_send_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '上一次提交地址',
  `last_send_time` datetime DEFAULT NULL COMMENT '上次提交时间',
  `total_size` int DEFAULT NULL COMMENT '数据总量',
  `temperature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '温度',
  `phone_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `assign_time` datetime DEFAULT NULL COMMENT '布置时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`sn`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='传感器';

-- ----------------------------
-- Records of device_sensor_equipment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for monitor_items
-- ----------------------------
DROP TABLE IF EXISTS `monitor_items`;
CREATE TABLE `monitor_items` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '检测项名称',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `data_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据类型',
  `decimal_size` int DEFAULT NULL COMMENT '小数位',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位',
  `ascii_index` int DEFAULT NULL COMMENT 'ASCII 解析标位',
  `hex_index` int DEFAULT NULL COMMENT 'HEX 解析标位',
  `ascii_prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'ASCII 解析前缀',
  `reduce_number` int DEFAULT NULL COMMENT 'HEX减 偏移量',
  `divide_number` int DEFAULT NULL COMMENT 'HEX除 偏移量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_DATA_ID` (`data_id`) USING BTREE COMMENT '唯一时序数据库标识'
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='监控条目';

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
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `structure_id` int NOT NULL COMMENT '结构物编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '对应图片',
  `sensor_sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '传感器Sn码',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '说明',
  `order_sort` int DEFAULT NULL COMMENT '排序',
  `effective` int DEFAULT NULL COMMENT '有效性',
  `binding_status` int DEFAULT NULL COMMENT '绑定状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='测点';

-- ----------------------------
-- Records of monitor_position
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for monitor_position_model
-- ----------------------------
DROP TABLE IF EXISTS `monitor_position_model`;
CREATE TABLE `monitor_position_model` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `structure_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '结构物标识编号',
  `model_id` int DEFAULT NULL COMMENT '结构物模型编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '位置名称',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '位置描述',
  `version` double(20,2) DEFAULT NULL COMMENT '版本',
  `structure_version` double(20,2) DEFAULT NULL,
  `order_sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排序',
  `effective` int DEFAULT NULL COMMENT '有效性',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='测点模板';

-- ----------------------------
-- Records of monitor_position_model
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for monitor_project
-- ----------------------------
DROP TABLE IF EXISTS `monitor_project`;
CREATE TABLE `monitor_project` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '照片路劲',
  `owner_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属编号',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='监测项目';

-- ----------------------------
-- Records of monitor_project
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for monitor_prototype
-- ----------------------------
DROP TABLE IF EXISTS `monitor_prototype`;
CREATE TABLE `monitor_prototype` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `updater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_effective` int DEFAULT NULL,
  `order_sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='监测类型\n';

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
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `model_id` int DEFAULT NULL COMMENT '模型编号',
  `project_id` int DEFAULT NULL COMMENT '项目编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `structure_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `current_version` double(20,2) DEFAULT NULL COMMENT '当前版本',
  `order_sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排序',
  `effective` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '有效',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='结构物实体';

-- ----------------------------
-- Records of monitor_structure
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for monitor_structure_model
-- ----------------------------
DROP TABLE IF EXISTS `monitor_structure_model`;
CREATE TABLE `monitor_structure_model` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `picture` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '结构物模板图片',
  `structure_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '结构体表示编码',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `is_public` int DEFAULT NULL COMMENT '是否为公共结构物',
  `is_contain_mobile` int DEFAULT NULL COMMENT '是否包含手机号',
  `is_contain_position` int DEFAULT NULL COMMENT '是否包含经纬度',
  `version` double(20,2) DEFAULT NULL COMMENT '版本',
  `order_sort` int DEFAULT NULL COMMENT '排序',
  `effective` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '有效性',
  `group_id` int DEFAULT NULL COMMENT '小组编号',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `editor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改者',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='结构体模板';

-- ----------------------------
-- Records of monitor_structure_model
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for operate_group
-- ----------------------------
DROP TABLE IF EXISTS `operate_group`;
CREATE TABLE `operate_group` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '组名',
  `group_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '小组编码',
  `person_number` int DEFAULT NULL COMMENT '人员数量',
  `is_inner` int DEFAULT NULL COMMENT '是否为内部组织',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '公司名称',
  `is_enabled` int DEFAULT NULL COMMENT '是否允许',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='企业组';

-- ----------------------------
-- Records of operate_group
-- ----------------------------
BEGIN;
INSERT INTO `operate_group` VALUES (1, '核心组', 'BW_MAIN', 4, 1, '核心组', 1, '2021-08-07 18:24:50');
COMMIT;

-- ----------------------------
-- Table structure for project_member
-- ----------------------------
DROP TABLE IF EXISTS `project_member`;
CREATE TABLE `project_member` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id` int DEFAULT NULL COMMENT '项目编号',
  `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `account_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账户名称',
  `user_id` int DEFAULT NULL COMMENT '用户编号',
  `join_time` datetime DEFAULT NULL COMMENT '加入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='项目成员';

-- ----------------------------
-- Records of project_member
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for project_role
-- ----------------------------
DROP TABLE IF EXISTS `project_role`;
CREATE TABLE `project_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '编号',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='项目权限';

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
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_id` int DEFAULT NULL COMMENT '监测类型编号',
  `item_id` int DEFAULT NULL COMMENT '监测',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='监测类型与监测项关联';

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
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `model_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `model_kind` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `model_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_effective` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='传感器模板\n';

-- ----------------------------
-- Records of sensor_model
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sensor_model_type
-- ----------------------------
DROP TABLE IF EXISTS `sensor_model_type`;
CREATE TABLE `sensor_model_type` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `model_id` int NOT NULL COMMENT '模型编号',
  `type_id` int NOT NULL COMMENT '类型编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='传感器 监测类型关联';

-- ----------------------------
-- Records of sensor_model_type
-- ----------------------------
BEGIN;
INSERT INTO `sensor_model_type` VALUES (1, 7, 1);
INSERT INTO `sensor_model_type` VALUES (2, 7, 2);
INSERT INTO `sensor_model_type` VALUES (3, 7, 3);
COMMIT;

-- ----------------------------
-- Table structure for support_mail_config
-- ----------------------------
DROP TABLE IF EXISTS `support_mail_config`;
CREATE TABLE `support_mail_config` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `config_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '配置名称',
  `account_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '控制台创建的发信地址',
  `from_alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发信人昵称',
  `reply_to_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '目标地址',
  `tag_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '控制台创建的标签',
  `template_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '模板名称',
  `subscribe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮件主题',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮件类型',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '修改人',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '修改者',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统邮件推送设置';

-- ----------------------------
-- Records of support_mail_config
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for support_mail_template
-- ----------------------------
DROP TABLE IF EXISTS `support_mail_template`;
CREATE TABLE `support_mail_template` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '抬头',
  `version` double(8,2) DEFAULT NULL COMMENT '版本号',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件名称',
  `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '模板',
  `template_local` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '模板路径',
  `is_default` int DEFAULT NULL COMMENT '是否为默认',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='邮件模板';

-- ----------------------------
-- Records of support_mail_template
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_alert_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_alert_group`;
CREATE TABLE `sys_alert_group` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '预警分组名称',
  `operate_group_id` int DEFAULT NULL COMMENT '操作组',
  `current_sensor_id` int DEFAULT NULL COMMENT '当前传感器',
  `template_id` int DEFAULT NULL COMMENT '模板编号',
  `push_type` int DEFAULT NULL COMMENT '推送方式',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='预警分组 用作预警规则的聚合根';

-- ----------------------------
-- Records of sys_alert_group
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_alert_notification
-- ----------------------------
DROP TABLE IF EXISTS `sys_alert_notification`;
CREATE TABLE `sys_alert_notification` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` int DEFAULT NULL COMMENT '小组编号',
  `alert_group_id` int DEFAULT NULL COMMENT '预警分组编号',
  `group_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '小组名称',
  `alert_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '告警名称',
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '规则名称',
  `sensor_id` int DEFAULT NULL COMMENT '传感器编号',
  `sn` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '传感器Sn码',
  `sensor_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '传感器名称',
  `sensor_model` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '传感器型号',
  `model_id` int DEFAULT NULL COMMENT '传感器型号编号',
  `alert_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '告警时间',
  `summary` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '告警信息',
  `color` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '展示颜色',
  `is_handle` int DEFAULT '0' COMMENT '是否被处理',
  PRIMARY KEY (`id`),
  KEY `alert_time` (`alert_time`,`group_id`,`alert_name`,`role_name`,`sensor_id`,`summary`,`color`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='预警通知日志';

-- ----------------------------
-- Records of sys_alert_notification
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_alert_notification_member
-- ----------------------------
DROP TABLE IF EXISTS `sys_alert_notification_member`;
CREATE TABLE `sys_alert_notification_member` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `alert_group_id` int DEFAULT NULL COMMENT '预警分组编号',
  `user_id` int DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='消息通知接收';

-- ----------------------------
-- Records of sys_alert_notification_member
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_alert_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_alert_param`;
CREATE TABLE `sys_alert_param` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `template_id` int DEFAULT NULL COMMENT '模板编号',
  `alert_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '告警名称',
  `param_no` int DEFAULT NULL COMMENT '监测参数编号',
  `last_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '问题出现后多久开始预警',
  `period` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '监测周期',
  `formulas` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '函数公式',
  `color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '颜色',
  `summary` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '告警信息预设',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='告警参数\n';

-- ----------------------------
-- Records of sys_alert_param
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_alert_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_alert_role`;
CREATE TABLE `sys_alert_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `alert_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '告警名称',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '规则名称',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '告警规则名称信息',
  `alert_group_id` int DEFAULT NULL COMMENT '告警分组编号',
  `template_id` int DEFAULT NULL COMMENT '模板编号',
  `color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '颜色',
  `param_id` int DEFAULT NULL COMMENT '参数编号',
  `group_id` int DEFAULT NULL COMMENT '小组编号',
  `sensor_id` int DEFAULT NULL COMMENT '传感器编号',
  `formulas` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '预警公式',
  `forward` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '回查时间',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标签',
  `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '提示信息',
  `alert_status` int DEFAULT NULL COMMENT '监测状态',
  `alert_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '内涵信息用于解析',
  `state_sql` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'TDengine 查询SQL',
  `template_sql` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '未修改回查时间的SQL',
  `expr` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '计算公式',
  `last_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '问题持续时间',
  `check_period` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '规则检查周期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_NAME` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='告警规则';

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
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `model_no` int DEFAULT NULL COMMENT '模型编号',
  `group_id` int DEFAULT NULL COMMENT '小组编号',
  `template_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '模型名称',
  `name_prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '告警role前缀名称',
  `summary_model` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '告警信息模板',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `updater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='告警模板\n';

-- ----------------------------
-- Records of sys_alert_template
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_industry_field
-- ----------------------------
DROP TABLE IF EXISTS `sys_industry_field`;
CREATE TABLE `sys_industry_field` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '编码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='行业领域\n';

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
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'SN编码',
  `channel_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'CHANNEL编号',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'IP地址',
  `receive_time` datetime DEFAULT NULL COMMENT '接收时间',
  `receive_size` int DEFAULT NULL COMMENT '接收数据条数',
  `send_count` int DEFAULT NULL COMMENT '发送总量',
  `receive_message` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '接收日志',
  `total_size` int DEFAULT NULL COMMENT '保存后总数据量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='传感器接收日志';

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
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` int NOT NULL COMMENT '预警分组编号',
  `message` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '消息',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `status` int DEFAULT NULL COMMENT '状态',
  `reader_id` int DEFAULT NULL COMMENT '处理人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_notification_cache
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_product_manufacturer
-- ----------------------------
DROP TABLE IF EXISTS `sys_product_manufacturer`;
CREATE TABLE `sys_product_manufacturer` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `facture_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '厂商编码',
  `post_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮编',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `connector` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系人',
  `contact_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
  `is_inner` int DEFAULT NULL COMMENT '是否为内部',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='传感器厂商';

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
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色和菜单关联表';

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
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `api_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求路由路径',
  `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限标识',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '前端路径',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '组件',
  `hidden` int DEFAULT NULL COMMENT '是否隐藏',
  `always_show` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否长显示',
  `redirect` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '定向地址',
  `is_base_node` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否为根节点',
  `upper_node` int DEFAULT NULL COMMENT '上层节点',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统路由配置';

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
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限URL',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `status` int DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统权限';

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
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `base_role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '上层角色编码',
  `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编码',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `is_base_role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否是根角色',
  `role_status` int DEFAULT NULL COMMENT '角色状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE` (`role_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统角色';

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
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `work_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工号',
  `account_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮件地址',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限标识',
  `role_id` int DEFAULT NULL COMMENT '权限编号',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职位',
  `operate_group_id` int DEFAULT NULL COMMENT '操作组编号',
  `enabled` int DEFAULT NULL COMMENT '是否被销户',
  `account_non_locked` int DEFAULT NULL COMMENT '是否被锁定',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `last_lease` int DEFAULT NULL COMMENT '是否长期有效',
  `lease_start_time` datetime DEFAULT NULL COMMENT '起始时间',
  `lease_time` int DEFAULT NULL COMMENT '租期（月）',
  `enable_notification` int DEFAULT NULL COMMENT '是否允许推送服务(邮件等)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX` (`account_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户表';

-- ----------------------------
-- Records of system_user
-- ----------------------------
BEGIN;
INSERT INTO `system_user` VALUES (1, '100001', 'admin', '$2a$10$/6ArmS07S4n9gMLFN0ENpuEP5cq31AMIynEmRkUpvsvz4lko2OS/2', '超级管理员', NULL, '', '', 'ROOT_ADMIN', 1, NULL, 1, 1, 1, NULL, NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for user_connection
-- ----------------------------
DROP TABLE IF EXISTS `user_connection`;
CREATE TABLE `user_connection` (
  `userId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '本地用户id',
  `providerId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '第三方服务商',
  `providerUserId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '第三方用户id',
  `rank` int NOT NULL COMMENT 'userId 绑定同一个 providerId 的排序',
  `displayName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '第三方用户名',
  `profileUrl` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '主页',
  `imageUrl` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `accessToken` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'accessToken',
  `tokenId` bigint DEFAULT NULL COMMENT 'auth_token.id',
  `refreshToken` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'refreshToken',
  `expireTime` bigint DEFAULT '-1' COMMENT '过期时间, 基于 1970-01-01T00:00:00Z, 无过期时间默认为 -1',
  PRIMARY KEY (`userId`,`providerId`,`providerUserId`),
  UNIQUE KEY `idx_userId_providerId_rank` (`userId`,`providerId`,`rank`),
  KEY `idx_providerId_providerUserId_rank` (`providerId`,`providerUserId`,`rank`),
  KEY `idx_tokenId` (`tokenId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of user_connection
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
