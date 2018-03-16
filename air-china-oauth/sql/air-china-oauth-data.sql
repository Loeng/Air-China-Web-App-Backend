/**
  name: air-china-oauth SQL data file
  type: sql data file
  create date: 2018/03/16
 */

-- ----------------------------
-- Records of t_base_menu
-- ----------------------------
INSERT INTO `t_base_menu` VALUES ('1', '2018-03-02 13:44:02', null, '1', '主页', '1', '0', '0', null, '0', '/home');
INSERT INTO `t_base_menu` VALUES ('2', '2018-03-02 13:44:02', null, '2', '航班动态', '1', '0', '0', null, '0', '/airline-dynamic');
INSERT INTO `t_base_menu` VALUES ('3', '2018-03-02 13:44:02', null, '3', '机票预订', '1', '0', '0', null, '0', '/ticket-order');

-- ----------------------------
-- Records of t_base_menu_base_role_rela
-- ----------------------------
INSERT INTO `t_base_menu_base_role_rela` VALUES ('1', '2018-03-02 13:44:33', null, '1', '1');
INSERT INTO `t_base_menu_base_role_rela` VALUES ('2', '2018-03-02 13:44:33', null, '1', '2');
INSERT INTO `t_base_menu_base_role_rela` VALUES ('3', '2018-03-02 13:44:33', null, '2', '3');

-- ----------------------------
-- Records of t_base_role
-- ----------------------------
INSERT INTO `t_base_role` VALUES ('1', '2018-03-02 13:42:06', null, '1', 'super-admin', '超级管理员', '1');
INSERT INTO `t_base_role` VALUES ('2', '2018-03-02 13:42:06', null, '2', 'general-admin', '普通管理员', '1');

-- ----------------------------
-- Records of t_base_sys
-- ----------------------------
INSERT INTO `t_base_sys` VALUES ('1', '2018-03-02 13:47:42', null, '1', 'sys_code_copyright', '版权信息', null, 'Copyright 2018 Steve Jrong All rights reserved.', '1');

-- ----------------------------
-- Records of t_base_token
-- ----------------------------
INSERT INTO `t_base_token` VALUES ('4', '2018-03-12 23:16:18', null, '201802241246-37486556', 'zhangsan@gmail.com', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ6aGFuZ3NhbkBnbWFpbC5jb20iLCJleHAiOjE1MjM0NjI0MDB9.cIt04OWI7QHB_r2fZS5VSOFaHLdM-3jipUuKw6vMPx0', '1');

-- ----------------------------
-- Records of t_base_user
-- ----------------------------
INSERT INTO `t_base_user` VALUES ('1', '2018-03-02 13:37:22', null, '201802241246-37486556', '张三', '$shiro1$SHA-256$500000$Ua8vPTe6viPm60T+XBzldA==$BiqEuNn/PBD0KewS8x82JHaQPC9UT9EmmDka1/fCRrY=', 'zhangsan@gmail.com', '28', '1', '13068009213', '张村#1');
INSERT INTO `t_base_user` VALUES ('2', '2018-03-02 13:37:22', null, '201802262023-45993369', '李四', '123456', 'lisi.com', '28', '1', '13534212236', '李村#15');

-- ----------------------------
-- Records of t_base_user_base_user_group_rela
-- ----------------------------
INSERT INTO `t_base_user_base_user_group_rela` VALUES ('1', '2018-03-02 13:40:04', null, '1', '201802241246-37486556');
INSERT INTO `t_base_user_base_user_group_rela` VALUES ('2', '2018-03-02 13:40:04', null, '1', '201802262023-45993369');

-- ----------------------------
-- Records of t_base_user_group
-- ----------------------------
INSERT INTO `t_base_user_group` VALUES ('1', '2018-03-02 13:39:11', null, '1', '超级管理员组一', '1', '1', '50', null);
INSERT INTO `t_base_user_group` VALUES ('2', '2018-03-02 13:39:11', null, '2', '超级管理员组二', '1', '1', '50', null);
INSERT INTO `t_base_user_group` VALUES ('3', '2018-03-02 13:39:11', null, '3', '普通管理员组A', '1', '1', '50', null);

-- ----------------------------
-- Records of t_base_user_group_base_role_rela
-- ----------------------------
INSERT INTO `t_base_user_group_base_role_rela` VALUES ('1', '2018-03-02 13:43:24', null, '1', '1');
INSERT INTO `t_base_user_group_base_role_rela` VALUES ('2', '2018-03-02 13:43:24', null, '3', '2');