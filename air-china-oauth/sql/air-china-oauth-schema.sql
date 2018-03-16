/**
  name: air-china-oauth SQL schema file
  type: sql schema file
  create date: 2018/03/16
 */

CREATE DATABASE IF not exists `air-china-oauth-db` DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

-- ----------------------------
-- Table structure for t_base_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_base_menu`;
CREATE TABLE `t_base_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `menu_id` int(10) unsigned NOT NULL COMMENT '菜单编号',
  `menu_name` varchar(100) NOT NULL COMMENT '菜单名称',
  `menu_type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '菜单类型(保留字段:1-系统菜单,2-其他菜单)',
  `is_parent_menu` tinyint(3) unsigned NOT NULL COMMENT '是否为父级菜单(1-是,0-否)',
  `is_root_menu` tinyint(3) unsigned NOT NULL COMMENT '是否为根菜单(1-是,0-否)',
  `parent_menu_id` int(10) unsigned DEFAULT NULL COMMENT '父级菜单编号',
  `order` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '菜单顺序',
  `request_url` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '请求地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_menu_id` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单基础表';

-- ----------------------------
-- Table structure for t_base_menu_base_role_rela
-- ----------------------------
DROP TABLE IF EXISTS `t_base_menu_base_role_rela`;
CREATE TABLE `t_base_menu_base_role_rela` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `role_id` int(10) unsigned NOT NULL COMMENT '角色编号',
  `menu_id` int(10) unsigned NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`id`),
  KEY `idx_role_id_menu_id` (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单基础表和角色基础表关联表';

-- ----------------------------
-- Table structure for t_base_role
-- ----------------------------
DROP TABLE IF EXISTS `t_base_role`;
CREATE TABLE `t_base_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `role_id` int(10) unsigned NOT NULL COMMENT '角色编号',
  `role_code` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '角色代码',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `state` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '角色状态(使用-1,禁用-0)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_id` (`role_id`),
  KEY `idx_role_id_role_name` (`role_id`,`role_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色基础表';

-- ----------------------------
-- Table structure for t_base_sys
-- ----------------------------
DROP TABLE IF EXISTS `t_base_sys`;
CREATE TABLE `t_base_sys` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `sys_item_id` int(10) unsigned NOT NULL COMMENT '系统设置项编号',
  `sys_item_code` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '系统设置项代码',
  `sys_item_name` varchar(100) NOT NULL COMMENT '系统设置项名称',
  `sys_item_description` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '系统设置项描述',
  `sys_item_value` text NOT NULL COMMENT '系统设置项值',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态(使用-1,禁用-0)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统设置基础表';

-- ----------------------------
-- Table structure for t_base_token
-- ----------------------------
DROP TABLE IF EXISTS `t_base_token`;
CREATE TABLE `t_base_token` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` varchar(255) NOT NULL COMMENT '用户识别码（具有唯一性）',
  `email` varchar(100) DEFAULT NULL COMMENT '用户电子邮件地址',
  `token` varchar(500) NOT NULL COMMENT 'Token令牌',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态(使用-1,禁用-0)',
  PRIMARY KEY (`id`),
  KEY `idx_user_id_token` (`user_id`,`token`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Token基础表';

-- ----------------------------
-- Table structure for t_base_user
-- ----------------------------
DROP TABLE IF EXISTS `t_base_user`;
CREATE TABLE `t_base_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` varchar(255) NOT NULL COMMENT '用户识别码（具有唯一性）',
  `user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户姓名',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `email` varchar(100) DEFAULT NULL COMMENT '用户电子邮件地址',
  `age` tinyint(3) unsigned DEFAULT '1' COMMENT '用户年龄',
  `gender` tinyint(3) unsigned DEFAULT NULL COMMENT '用户性别(1-男,0-女)',
  `telephone` varchar(30) DEFAULT NULL COMMENT '用户联系电话',
  `address` varchar(500) DEFAULT NULL COMMENT '联系地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_id` (`user_id`),
  KEY `idx_uuid_name` (`user_id`,`user_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户基础表';

-- ----------------------------
-- Table structure for t_base_user_base_user_group_rela
-- ----------------------------
DROP TABLE IF EXISTS `t_base_user_base_user_group_rela`;
CREATE TABLE `t_base_user_base_user_group_rela` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `user_group_id` int(10) unsigned NOT NULL COMMENT '用户组编号',
  `user_id` varchar(255) NOT NULL COMMENT '用户识别码（具有唯一性）',
  PRIMARY KEY (`id`),
  KEY `idx_user_group_id_user_id` (`user_group_id`,`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户基础表和用户组基础表关联表';

-- ----------------------------
-- Table structure for t_base_user_group
-- ----------------------------
DROP TABLE IF EXISTS `t_base_user_group`;
CREATE TABLE `t_base_user_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `user_group_id` int(10) unsigned NOT NULL COMMENT '用户组编号',
  `user_group_name` varchar(100) NOT NULL COMMENT '用户组名称',
  `user_group_state` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '用户组状态(使用-1,禁用-0)',
  `min_capacity` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '组最小容量',
  `max_capacity` int(10) unsigned NOT NULL DEFAULT '50' COMMENT '组最大容量',
  `description` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '组描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_group_id` (`user_group_id`),
  KEY `idx_user_group_id_user_group_name` (`user_group_id`,`user_group_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组基础表';

-- ----------------------------
-- Table structure for t_base_user_group_base_role_rela
-- ----------------------------
DROP TABLE IF EXISTS `t_base_user_group_base_role_rela`;
CREATE TABLE `t_base_user_group_base_role_rela` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `user_group_id` int(10) unsigned NOT NULL COMMENT '用户组编号',
  `role_id` int(10) unsigned NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`),
  KEY `idx_user_group_id_role_id` (`user_group_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组基础表和角色基础表关联表';
