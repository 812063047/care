/*
 Navicat MySQL Data Transfer

 Source Server         : PDAS
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 10.60.2.175:3306
 Source Schema         : care

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 01/04/2021 17:00:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for crm_banner
-- ----------------------------
DROP TABLE IF EXISTS `crm_banner`;
CREATE TABLE `crm_banner`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'ID',
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '标题',
  `image_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '图片地址',
  `link_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '链接地址',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`title`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '首页banner表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crm_banner
-- ----------------------------
INSERT INTO `crm_banner` VALUES ('1194556896025845762', 'test1', 'https://care-file.oss-cn-shanghai.aliyuncs.com/banner/1525939573202.jpg', '/course', 1, 0, '2019-11-13 18:05:32', '2019-11-18 10:28:22');
INSERT INTO `crm_banner` VALUES ('1194607458461216769', 'test2', 'https://care-file.oss-cn-shanghai.aliyuncs.com/banner/153525d0ef15459596.jpg', '/teacher', 2, 0, '2019-11-13 21:26:27', '2019-11-14 09:12:15');
INSERT INTO `crm_banner` VALUES ('1367687142363205634', 'string', 'string', 'string', 0, 1, '2021-03-05 12:03:20', '2021-03-05 12:03:20');

-- ----------------------------
-- Table structure for edu_chapter
-- ----------------------------
DROP TABLE IF EXISTS `edu_chapter`;
CREATE TABLE `edu_chapter`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节ID',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节名称',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '显示排序',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_chapter
-- ----------------------------
INSERT INTO `edu_chapter` VALUES ('1', '14', '第一章：HTML', 0, '2019-01-01 12:27:40', '2019-01-01 12:55:30');
INSERT INTO `edu_chapter` VALUES ('1181729226915577857', '18', '阿达阿', 1, '2019-10-09 08:32:58', '2021-02-08 09:19:17');
INSERT INTO `edu_chapter` VALUES ('1192252428399751169', '1192252213659774977', '第一章节', 0, '2019-11-07 09:28:25', '2019-11-07 09:28:25');
INSERT INTO `edu_chapter` VALUES ('1357634332372299778', '18', '第九章：web完全asd', 12, '2021-02-05 18:17:03', '2021-02-07 12:01:58');
INSERT INTO `edu_chapter` VALUES ('1358586273202016258', '18', '发达', 100, '2021-02-08 09:19:44', '2021-02-08 09:19:44');
INSERT INTO `edu_chapter` VALUES ('1366662634273542146', '1357605131032293378', 'weishm ', 2, '2021-03-02 16:12:18', '2021-03-02 16:12:18');
INSERT INTO `edu_chapter` VALUES ('1367347615903883265', '1367347590658367490', '去', 1, '2021-03-04 13:34:10', '2021-03-04 13:34:10');
INSERT INTO `edu_chapter` VALUES ('1367347947643969537', '1367347922616557570', '2 ', 1, '2021-03-04 13:35:29', '2021-03-04 13:35:29');
INSERT INTO `edu_chapter` VALUES ('1367765493601861634', '1367765455018459138', '第一章', 2, '2021-03-05 17:14:40', '2021-03-05 17:14:40');
INSERT INTO `edu_chapter` VALUES ('1377082442815205377', '1377082402856071169', '第一章', 1, '2021-03-31 10:16:54', '2021-03-31 10:16:54');
INSERT INTO `edu_chapter` VALUES ('1377083666188824577', '1377083634534412290', '第一节', 0, '2021-03-31 10:21:46', '2021-03-31 10:21:46');
INSERT INTO `edu_chapter` VALUES ('15', '18', '第一章：Java入门', 0, '2019-01-01 12:27:40', '2019-10-09 09:13:19');
INSERT INTO `edu_chapter` VALUES ('3', '14', '第二章：CSS', 0, '2019-01-01 12:55:35', '2019-01-01 12:27:40');
INSERT INTO `edu_chapter` VALUES ('32', '18', '第二章：控制台输入和输出', 0, '2019-01-01 12:27:40', '2019-01-01 12:27:40');
INSERT INTO `edu_chapter` VALUES ('44', '18', '第三章：控制流', 0, '2019-01-01 12:27:40', '2019-01-01 12:27:40');
INSERT INTO `edu_chapter` VALUES ('48', '18', '第四章：类的定义', 0, '2019-01-01 12:27:40', '2019-01-01 12:27:40');
INSERT INTO `edu_chapter` VALUES ('63', '18', '第五章：数组', 0, '2019-01-01 12:27:40', '2019-01-01 12:27:40');
INSERT INTO `edu_chapter` VALUES ('64', '18', '第六章：继承', 61, '2019-01-01 12:27:40', '2019-10-09 08:32:47');

-- ----------------------------
-- Table structure for edu_comment
-- ----------------------------
DROP TABLE IF EXISTS `edu_comment`;
CREATE TABLE `edu_comment`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '讲师ID',
  `course_id` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '课程id',
  `teacher_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '讲师id',
  `member_id` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '会员id',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员头像',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE,
  INDEX `idx_teacher_id`(`teacher_id`) USING BTREE,
  INDEX `idx_member_id`(`member_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_comment
-- ----------------------------
INSERT INTO `edu_comment` VALUES ('1194499162790211585', '1192252213659774977', '1189389726308478977', '1', '小三123', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', '课程很好', 0, '2019-11-13 14:16:08', '2019-11-13 14:16:08');
INSERT INTO `edu_comment` VALUES ('1194898406466420738', '1192252213659774977', '1189389726308478977', '1', '小三123', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', '11', 0, '2019-11-14 16:42:35', '2019-11-14 16:42:35');
INSERT INTO `edu_comment` VALUES ('1194898484388200450', '1192252213659774977', '1189389726308478977', '1', '小三123', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', '111', 0, '2019-11-14 16:42:53', '2019-11-14 16:42:53');
INSERT INTO `edu_comment` VALUES ('1195251020861317122', '1192252213659774977', '1189389726308478977', '1', NULL, NULL, '2233', 0, '2019-11-15 16:03:45', '2019-11-15 16:03:45');
INSERT INTO `edu_comment` VALUES ('1195251382720700418', '1192252213659774977', '1189389726308478977', '1', NULL, NULL, '4455', 0, '2019-11-15 16:05:11', '2019-11-15 16:05:11');
INSERT INTO `edu_comment` VALUES ('1195252819177570306', '1192252213659774977', '1189389726308478977', '1', '小三1231', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', '55', 0, '2019-11-15 16:10:53', '2019-11-15 16:10:53');
INSERT INTO `edu_comment` VALUES ('1195252899448160258', '1192252213659774977', '1189389726308478977', '1', '小三1231', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', '55', 0, '2019-11-15 16:11:13', '2019-11-15 16:11:13');
INSERT INTO `edu_comment` VALUES ('1195252920587452417', '1192252213659774977', '1189389726308478977', '1', '小三1231', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', '223344', 0, '2019-11-15 16:11:18', '2019-11-15 16:11:18');
INSERT INTO `edu_comment` VALUES ('1195262128095559681', '14', '1189389726308478977', '1', '小三1231', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', '11', 0, '2019-11-15 16:47:53', '2019-11-15 16:47:53');
INSERT INTO `edu_comment` VALUES ('1196264505170767874', '1192252213659774977', '1189389726308478977', '1', '小三1231', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', '666666', 0, '2019-11-18 11:10:58', '2019-11-18 11:10:58');

-- ----------------------------
-- Table structure for edu_course
-- ----------------------------
DROP TABLE IF EXISTS `edu_course`;
CREATE TABLE `edu_course`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程ID',
  `teacher_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程讲师ID',
  `subject_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程专业ID',
  `subject_parent_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程专业父级ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程标题',
  `price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '课程销售价格，设置为0则可免费观看',
  `lesson_num` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '总课时',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程封面图片路径',
  `buy_count` bigint(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '销售数量',
  `view_count` bigint(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '浏览数量',
  `version` bigint(20) UNSIGNED NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Draft' COMMENT '课程状态 Draft未发布  Normal已发布',
  `is_deleted` tinyint(3) NULL DEFAULT NULL COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_title`(`title`) USING BTREE,
  INDEX `idx_subject_id`(`subject_id`) USING BTREE,
  INDEX `idx_teacher_id`(`teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_course
-- ----------------------------
INSERT INTO `edu_course` VALUES ('1192252213659774977', '1189389726308478977', '1178214681139539969', '1178214681118568449', 'java基础课程：test', 0.01, 2, 'https://care-file.oss-cn-shanghai.aliyuncs.com/courseCover/2021/03/05/1e2ec9947dff4f9b8243f4c7f6418f4ccover.png', 4, 388, 1, 'Normal', 0, '2019-11-07 09:27:33', '2021-04-01 16:00:05');
INSERT INTO `edu_course` VALUES ('1354727034020790273', 'string12', 'string65', NULL, 'string10', 0.00, 0, 'https://care-file.oss-cn-shanghai.aliyuncs.com/courseCover/2021/03/05/1e2ec9947dff4f9b8243f4c7f6418f4ccover.png', 0, 1, 1, 'Draft', 0, '2021-01-28 17:44:29', '2021-04-01 16:04:29');
INSERT INTO `edu_course` VALUES ('1354728759972663297', 'string2', 'string3', NULL, 'string1', 0.00, 0, '', 0, 0, 1, 'Draft', 1, '2021-01-28 17:51:21', '2021-01-28 17:51:21');
INSERT INTO `edu_course` VALUES ('1354977065650950146', NULL, NULL, NULL, '0203 ajva', 2.00, 63, NULL, 0, 0, 1, 'Draft', 1, '2021-01-29 10:18:01', '2021-01-29 10:18:01');
INSERT INTO `edu_course` VALUES ('1354980223450165250', NULL, NULL, NULL, '测试', 3.00, 2, NULL, 0, 0, 1, 'Draft', 1, '2021-01-29 10:30:34', '2021-01-29 10:30:34');
INSERT INTO `edu_course` VALUES ('1354983785731272706', '1', NULL, NULL, '奥德赛', 2.00, 3, NULL, 0, 0, 1, 'Draft', 1, '2021-01-29 10:44:44', '2021-01-29 10:44:44');
INSERT INTO `edu_course` VALUES ('1354983992883752961', '1189389726308478977', NULL, NULL, '阿达', 3.00, 2, NULL, 0, 0, 1, 'Draft', 1, '2021-01-29 10:45:33', '2021-01-29 10:45:33');
INSERT INTO `edu_course` VALUES ('1354989829358432257', '1189426464967995393', '1178214681428946945', NULL, '阿达', 4.00, 2, NULL, 0, 0, 1, 'Draft', 1, '2021-01-29 11:08:44', '2021-01-29 11:08:44');
INSERT INTO `edu_course` VALUES ('1354990988567539713', '1189426464967995393', '1178214681382809602', '1178214681324089345', '测试父类id', 100.00, 2, NULL, 0, 0, 1, 'Draft', 1, '2021-01-29 11:13:21', '2021-01-29 11:13:21');
INSERT INTO `edu_course` VALUES ('1354995436807286785', '1351812140673839105', '1178214681382809602', '1178214681324089345', '萨达', 3.00, 1, NULL, 0, 0, 1, 'Draft', 1, '2021-01-29 11:31:01', '2021-01-29 11:31:01');
INSERT INTO `edu_course` VALUES ('1354999638895915010', '1189426464967995393', '1178214681139539969', '1178214681118568449', '测试', 3.00, 4, NULL, 0, 0, 1, 'Draft', 1, '2021-01-29 11:47:43', '2021-01-29 11:47:43');
INSERT INTO `edu_course` VALUES ('1357557981640491009', '1', '1178214681139539969', '1178214681118568449', '测试java', 3.00, 5, 'https://care-file.oss-cn-shanghai.aliyuncs.com/2021/02/05/7c463f3116f74d42824ab31163be5d14tes.jpg', 0, 0, 1, 'Draft', 1, '2021-02-05 13:13:40', '2021-02-05 13:13:40');
INSERT INTO `edu_course` VALUES ('1357558808539136001', '1189426437876985857', '1178214681353449473', '1178214681324089345', '测试123', 10.00, 62, 'https://care-file.oss-cn-shanghai.aliyuncs.com/2021/02/05/c7335e38fd824f0ab1916c5a6fcfc333tes.jpg', 0, 0, 1, 'Draft', 1, '2021-02-05 13:16:57', '2021-02-05 13:16:57');
INSERT INTO `edu_course` VALUES ('1357562325530148866', '1351812140673839105', '1178214681252786178', '1178214681231814658', '学习', 90.00, 98, 'https://care-file.oss-cn-shanghai.aliyuncs.com/2021/02/05/3ba8f3cb5a534f62994aad07dd4ab947tes.jpg', 0, 0, 1, 'Draft', 1, '2021-02-05 13:30:55', '2021-02-05 13:30:55');
INSERT INTO `edu_course` VALUES ('1357598447153012738', '1189390295668469762', '1178214681210843137', '1178214681181483010', 'haha', 56.00, 20, 'https://care-file.oss-cn-shanghai.aliyuncs.com/2021/02/05/a9b9a88d19794486bc253ed1c1399d03tes.jpg', 0, 0, 1, 'Draft', 1, '2021-02-05 15:54:27', '2021-02-05 15:54:27');
INSERT INTO `edu_course` VALUES ('1357599970297733121', '1351812965043359745', '1178214681504444418', '1178214681483472898', '测试开始', 36.00, 32, 'https://care-file.oss-cn-shanghai.aliyuncs.com/2021/02/05/760dee7fc9ff4e98ad59a433dbefc4a5tes.jpg', 0, 0, 1, 'Draft', 1, '2021-02-05 16:00:31', '2021-02-05 16:00:31');
INSERT INTO `edu_course` VALUES ('1366318946011914241', '1351812965043359745', '1178214681584136193', '1178214681554776066', 'Python学习', 20.00, 20, 'https://care-file.oss-cn-shanghai.aliyuncs.com/courseCover/2021/03/05/1e2ec9947dff4f9b8243f4c7f6418f4ccover.png', 0, 0, 1, 'Normal', 0, '2021-03-01 17:26:36', '2021-03-01 17:30:45');
INSERT INTO `edu_course` VALUES ('1366628207069769730', '1', '1178214681139539969', '1178214681118568449', 'waw ', 10.00, 10, 'https://care-file.oss-cn-shanghai.aliyuncs.com/courseCover/2021/03/05/1e2ec9947dff4f9b8243f4c7f6418f4ccover.png', 0, 0, 1, 'Normal', 0, '2021-03-02 13:55:30', '2021-03-02 13:55:43');
INSERT INTO `edu_course` VALUES ('1367353190792396801', '1189426464967995393', '1178214681382809602', '1178214681324089345', '测试1', 52.00, 32, 'https://care-file.oss-cn-shanghai.aliyuncs.com/courseCover/2021/03/05/1e2ec9947dff4f9b8243f4c7f6418f4ccover.png', 0, 0, 1, 'Normal', 0, '2021-03-04 13:56:20', '2021-03-04 13:57:24');
INSERT INTO `edu_course` VALUES ('1367353681492410369', '1189389726308478977', '1178214681252786178', '1178214681231814658', '测试2', 2.00, 1, 'https://care-file.oss-cn-shanghai.aliyuncs.com/courseCover/2021/03/05/1e2ec9947dff4f9b8243f4c7f6418f4ccover.png', 0, 0, 1, 'Normal', 0, '2021-03-04 13:58:17', '2021-03-04 13:58:38');
INSERT INTO `edu_course` VALUES ('1367358053811011586', '1192249914833055746', '1178214681139539969', '1178214681118568449', 'java学习', 10.00, 10, 'https://care-file.oss-cn-shanghai.aliyuncs.com/courseCover/2021/03/05/1e2ec9947dff4f9b8243f4c7f6418f4ccover.png', 0, 0, 1, 'Normal', 0, '2021-03-04 14:15:39', '2021-03-04 14:26:28');
INSERT INTO `edu_course` VALUES ('1367765455018459138', '1', '1178214681139539969', '1178214681118568449', '课程1', 10.00, 1, 'https://care-file.oss-cn-shanghai.aliyuncs.com/courseCover/2021/03/05/1e2ec9947dff4f9b8243f4c7f6418f4ccover.png', 0, 0, 1, 'Normal', 0, '2021-03-05 17:14:31', '2021-03-05 17:32:52');
INSERT INTO `edu_course` VALUES ('1377082402856071169', '1189390295668469762', '1178214681210843137', '1178214681181483010', '课程1', 20.00, 20, 'https://care-file.oss-cn-shanghai.aliyuncs.com/courseCover/2021/03/05/1e2ec9947dff4f9b8243f4c7f6418f4ccover.png', 0, 0, 1, 'Normal', 0, '2021-03-31 10:16:44', '2021-03-31 10:17:59');
INSERT INTO `edu_course` VALUES ('1377083634534412290', '1351812140673839105', '1178214681353449473', '1178214681324089345', '啊实打实', 52.00, 45, 'https://care-file.oss-cn-shanghai.aliyuncs.com/courseCover/2021/03/05/1e2ec9947dff4f9b8243f4c7f6418f4ccover.png', 0, 3, 1, 'Normal', 0, '2021-03-31 10:21:38', '2021-04-01 16:19:13');
INSERT INTO `edu_course` VALUES ('14', '1189389726308478977', '1101348944971091969', '1101348944920760321', 'XHTML CSS2 JS整站制作教程课程学习', 0.00, 3, 'https://care-file.oss-cn-shanghai.aliyuncs.com/courseCover/2021/03/05/1e2ec9947dff4f9b8243f4c7f6418f4ccover.png', 3, 44, 15, 'Normal', 0, '2018-04-02 18:33:34', '2019-11-16 21:21:45');
INSERT INTO `edu_course` VALUES ('15', '1189389726308478977', '1101348944971091969', '1101348944920760321', 'HTML5入门课程学习', 0.00, 23, 'https://care-file.oss-cn-shanghai.aliyuncs.com/courseCover/2021/03/05/1e2ec9947dff4f9b8243f4c7f6418f4ccover.png', 0, 51, 17, 'Normal', 0, '2018-04-02 18:34:32', '2019-11-12 10:19:20');
INSERT INTO `edu_course` VALUES ('18', '1189389726308478977', '1178214681139539969', '1178214681118568449', 'Java精品课程', 0.01, 20, 'https://care-file.oss-cn-shanghai.aliyuncs.com/courseCover/2021/03/05/1e2ec9947dff4f9b8243f4c7f6418f4ccover.png', 151, 737, 6, 'Normal', 0, '2018-04-02 21:28:46', '2019-11-18 11:14:52');

-- ----------------------------
-- Table structure for edu_course_collect
-- ----------------------------
DROP TABLE IF EXISTS `edu_course_collect`;
CREATE TABLE `edu_course_collect`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收藏ID',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程讲师ID',
  `member_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '课程专业ID',
  `is_deleted` tinyint(3) NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程收藏' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_course_collect
-- ----------------------------
INSERT INTO `edu_course_collect` VALUES ('1196269345666019330', '1192252213659774977', '1', 1, '2019-11-18 11:30:12', '2019-11-18 11:30:12');

-- ----------------------------
-- Table structure for edu_course_description
-- ----------------------------
DROP TABLE IF EXISTS `edu_course_description`;
CREATE TABLE `edu_course_description`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程ID',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '课程简介',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程简介' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_course_description
-- ----------------------------
INSERT INTO `edu_course_description` VALUES ('1104870479077879809', '<p>11</p>', '2019-03-11 06:23:44', '2019-03-11 06:23:44');
INSERT INTO `edu_course_description` VALUES ('1192252213659774977', '<p>测试</p>', '2019-11-07 09:27:33', '2019-11-13 16:21:28');
INSERT INTO `edu_course_description` VALUES ('1354727034368917506', 'stringwqe', '2021-01-28 17:44:29', '2021-01-28 17:44:29');
INSERT INTO `edu_course_description` VALUES ('1357605131032293378', '<p>很棒</p>', '2021-02-05 16:21:01', '2021-02-05 16:24:39');
INSERT INTO `edu_course_description` VALUES ('1358595903621763073', '<p>哈哈</p>', '2021-02-08 09:58:00', '2021-02-08 10:00:06');
INSERT INTO `edu_course_description` VALUES ('1366286586226728962', '<p>hahahha</p>', '2021-03-01 15:18:01', '2021-03-01 15:18:01');
INSERT INTO `edu_course_description` VALUES ('1367347590658367490', '<p>安慰</p>', '2021-03-04 13:34:04', '2021-03-04 13:34:04');
INSERT INTO `edu_course_description` VALUES ('1367347922616557570', '<p>请问</p>', '2021-03-04 13:35:24', '2021-03-04 13:35:24');
INSERT INTO `edu_course_description` VALUES ('1367765455018459138', '<p>哈哈</p>', '2021-03-05 17:14:31', '2021-03-05 17:14:31');
INSERT INTO `edu_course_description` VALUES ('1377082402856071169', '<p>阿达</p>', '2021-03-31 10:16:45', '2021-03-31 10:16:45');
INSERT INTO `edu_course_description` VALUES ('1377083634534412290', '<p>电风扇</p>', '2021-03-31 10:21:38', '2021-03-31 10:21:38');
INSERT INTO `edu_course_description` VALUES ('14', '', '2019-03-13 06:04:43', '2019-03-13 06:05:33');
INSERT INTO `edu_course_description` VALUES ('15', '', '2019-03-13 06:03:33', '2019-03-13 06:04:22');
INSERT INTO `edu_course_description` VALUES ('18', '<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>\n<p>------------------------------------</p>\n<p>视频特点：</p>\n<p>通过学习本Java视频教程，大家能够真正将Java基础知识学以致用、活学活用，构架Java编程思想，牢牢掌握\"源码级\"的Javase核心技术，并为后续JavaWeb等技术的学习奠定扎实基础。<br /><br />1.通俗易懂，细致入微：每个知识点高屋建瓴，深入浅出，简洁明了的说明问题<br />2.具实战性：全程真正代码实战，涵盖上百个企业应用案例及练习<br />3.深入：源码分析，更有 Java 反射、动态代理的实际应用等<br />4.登录尚硅谷官网，技术讲师免费在线答疑</p>', '2019-03-06 18:06:36', '2019-10-30 19:58:36');

-- ----------------------------
-- Table structure for edu_subject
-- ----------------------------
DROP TABLE IF EXISTS `edu_subject`;
CREATE TABLE `edu_subject`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程类别ID',
  `title` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类别名称',
  `parent_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父ID',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程科目' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_subject
-- ----------------------------
INSERT INTO `edu_subject` VALUES ('1178214681118568449', '后端开发', '0', 1, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681139539969', 'Java', '1178214681118568449', 1, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681181483010', '前端开发', '0', 3, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681210843137', 'JavaScript', '1178214681181483010', 4, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681231814658', '云计算', '0', 5, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681252786178', 'Docker', '1178214681231814658', 5, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681294729217', 'Linux', '1178214681231814658', 6, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681324089345', '系统/运维', '0', 7, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681353449473', 'Linux', '1178214681324089345', 7, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681382809602', 'Windows', '1178214681324089345', 8, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681399586817', '数据库', '0', 9, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681428946945', 'MySQL', '1178214681399586817', 9, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681454112770', 'MongoDB', '1178214681399586817', 10, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681483472898', '大数据', '0', 11, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681504444418', 'Hadoop', '1178214681483472898', 11, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681529610242', 'Spark', '1178214681483472898', 12, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681554776066', '人工智能', '0', 13, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681584136193', 'Python', '1178214681554776066', 13, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681613496321', '编程语言', '0', 14, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681626079234', 'Java', '1178214681613496321', 14, '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178585108407984130', 'Python', '1178214681118568449', 2, '2019-09-30 16:19:22', '2019-09-30 16:19:22');
INSERT INTO `edu_subject` VALUES ('1178585108454121473', 'HTML/CSS', '1178214681181483010', 3, '2019-09-30 16:19:22', '2019-09-30 16:19:22');

-- ----------------------------
-- Table structure for edu_teacher
-- ----------------------------
DROP TABLE IF EXISTS `edu_teacher`;
CREATE TABLE `edu_teacher`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '讲师ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '讲师姓名',
  `intro` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '讲师简介',
  `career` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '讲师资历,一句话说明讲师',
  `level` int(10) UNSIGNED NOT NULL COMMENT '头衔 1高级讲师 2首席讲师',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '讲师头像',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '讲师' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_teacher
-- ----------------------------
INSERT INTO `edu_teacher` VALUES ('1', '张三', '高级讲师简介', '高级', 1, 'https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg', 0, 0, '2019-10-30 14:18:46', '2019-11-12 13:36:36');
INSERT INTO `edu_teacher` VALUES ('1189389726308478977', '晴天', '高级讲师简介', '高级讲师资历', 2, 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/10/30/de47ee9b-7fec-43c5-8173-13c5f7f689b2.png', 1, 0, '2019-10-30 11:53:03', '2019-10-30 11:53:03');
INSERT INTO `edu_teacher` VALUES ('1189390295668469762', '李刚', '高级讲师简介', '高级讲师', 2, 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/10/30/b8aa36a2-db50-4eca-a6e3-cc6e608355e0.png', 2, 0, '2019-10-30 11:55:19', '2019-11-12 13:37:52');
INSERT INTO `edu_teacher` VALUES ('1189426437876985857', '王二', '高级讲师简介', '高级讲师', 1, 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/11/08/e44a2e92-2421-4ea3-bb49-46f2ec96ef88.png', 0, 0, '2019-10-30 14:18:56', '2019-11-12 13:37:35');
INSERT INTO `edu_teacher` VALUES ('1189426464967995393', '王五', '高级讲师简介', '高级讲师', 1, 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/10/30/65423f14-49a9-4092-baf5-6d0ef9686a85.png', 0, 0, '2019-10-30 14:19:02', '2019-11-12 13:37:18');
INSERT INTO `edu_teacher` VALUES ('1192249914833055746', '李四', '高级讲师简介', '高级讲师', 1, 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/11/07/91871e25-fd83-4af6-845f-ea8d471d825d.png', 0, 0, '2019-11-07 09:18:25', '2019-11-12 13:37:01');
INSERT INTO `edu_teacher` VALUES ('1192327476087115778', '1222-12-12', '1111', '11', 1, 'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/11/08/5805c6cd-c8ad-4a77-aafd-d2e083bfd8a4.png', 0, 1, '2019-11-07 14:26:37', '2019-11-11 16:26:26');
INSERT INTO `edu_teacher` VALUES ('1351812140673839105', '岳不群520', '高级讲师简介', '高级讲师', 3, 'https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg', 1, 0, '2021-01-20 16:41:44', '2021-01-20 17:14:11');
INSERT INTO `edu_teacher` VALUES ('1351812965043359745', '武则天', '高级讲师简介', '高级讲师', 1, 'https://care-file.oss-cn-shanghai.aliyuncs.com/teacherAvatar/2021/03/03/4a3053c9d81f4797997d897d24cf2fe6file.png', 2, 0, '2021-01-20 16:45:01', '2021-01-20 16:45:01');
INSERT INTO `edu_teacher` VALUES ('1351814951679582210', 'string23124', 'string', 'string', 0, 'string', 0, 0, '2021-01-20 16:52:55', '2021-01-20 16:52:55');
INSERT INTO `edu_teacher` VALUES ('1351815436599853058', 'stringwqeqwe', 'string', 'string', 0, 'string', 0, 1, '2021-01-20 16:54:50', '2021-01-20 16:54:50');
INSERT INTO `edu_teacher` VALUES ('1351817169908539394', '123', 'string', 'string', 0, 'string', 0, 0, '2021-01-20 17:01:44', '2021-01-20 17:01:44');
INSERT INTO `edu_teacher` VALUES ('1351817781018669057', 'stringqweqw', 'string', 'string', 0, 'string', 0, 1, '2021-01-20 17:04:09', '2021-01-20 17:04:09');
INSERT INTO `edu_teacher` VALUES ('1353976497214160898', '啊', '阿达额', '阿达请问q阿达', 2, '', 2, 0, '2021-01-26 16:02:07', '2021-01-26 16:28:40');
INSERT INTO `edu_teacher` VALUES ('1353983357254230018', '阿达阿达', '发给', '一个号', 1, '', 1, 1, '2021-01-26 16:29:23', '2021-01-26 16:29:23');
INSERT INTO `edu_teacher` VALUES ('1354005803793494018', '测试头像', '阿达', '阿达', 1, 'https://care-file.oss-cn-shanghai.aliyuncs.com/2021/01/26/3e9c87e4a2f84304a2b281dc6ecfadf9file.png', 1, 0, '2021-01-26 17:58:34', '2021-01-26 17:58:34');
INSERT INTO `edu_teacher` VALUES ('1367023754536660993', '老黄', '很好的老师', '为什么', 1, 'https://care-file.oss-cn-shanghai.aliyuncs.com/teacher2021/03/03/0c9f93f8a50c47c080669f341c457e29file.png', 20, 0, '2021-03-03 16:07:16', '2021-03-03 16:07:16');
INSERT INTO `edu_teacher` VALUES ('1367025198266429441', '老狗', '阿萨德', '呵呵', 2, 'https://care-file.oss-cn-shanghai.aliyuncs.com/teacherAvatar/2021/03/03/4a3053c9d81f4797997d897d24cf2fe6file.png', 2, 0, '2021-03-03 16:13:00', '2021-03-03 16:13:00');
INSERT INTO `edu_teacher` VALUES ('1367052580163420162', '测试讲师', '按时', '哇', 2, 'https://care-file.oss-cn-shanghai.aliyuncs.com/teacherAvatar/2021/03/03/8bdf3cb27ca440d4aaad3b3b0915dc4ffile.png', 5, 1, '2021-03-03 18:01:48', '2021-03-03 18:01:48');
INSERT INTO `edu_teacher` VALUES ('1367318910708035585', '车上', '啊', '阿达', 1, 'https://care-file.oss-cn-shanghai.aliyuncs.com/teacherAvatar/2021/03/04/7f51c05ac65f4df8b551543013dd4896file.png', 1, 1, '2021-03-04 11:40:07', '2021-03-04 11:40:07');
INSERT INTO `edu_teacher` VALUES ('1367321703149850625', '测试1', '  啊', 'a', 1, 'https://care-file.oss-cn-shanghai.aliyuncs.com/teacherAvatar/2021/03/04/8c86c6ffa80a4772833d64ede4241708file.png', 1, 1, '2021-03-04 11:51:12', '2021-03-04 11:51:12');
INSERT INTO `edu_teacher` VALUES ('1367321818128306177', '2', '啊', '啊', 1, 'https://care-file.oss-cn-shanghai.aliyuncs.com/teacherAvatar/2021/03/04/23537b3a69714aaca23b9a4db2c93741file.png', 2, 1, '2021-03-04 11:51:40', '2021-03-04 11:51:40');
INSERT INTO `edu_teacher` VALUES ('1367687708288012289', 'string', 'string', 'string', 0, '', 0, 1, '2021-03-05 12:05:35', '2021-03-05 12:05:35');
INSERT INTO `edu_teacher` VALUES ('1367764905187147778', '哈哈', '阿萨德', '阿达', 1, 'https://care-file.oss-cn-shanghai.aliyuncs.com/teacherAvatar/2021/03/05/4f93a6b1713f44fc8f2e0ec7b5b0ed42file.png', 1, 0, '2021-03-05 17:12:20', '2021-03-05 17:12:20');
INSERT INTO `edu_teacher` VALUES ('1367765049383124994', '哈哈单号', '地方', '到时', 1, 'https://care-file.oss-cn-shanghai.aliyuncs.com/teacherAvatar/2021/03/05/a82061a9e18749b397239f7006135058file.png', 1, 0, '2021-03-05 17:12:54', '2021-03-05 17:12:54');
INSERT INTO `edu_teacher` VALUES ('1367765213044867074', '阿是', '是的', '的', 2, 'https://care-file.oss-cn-shanghai.aliyuncs.com/teacherAvatar/2021/03/05/6b48b11aebd44c7d9dd539b98e03ecc7file.png', 1, 0, '2021-03-05 17:13:33', '2021-03-05 17:13:33');

-- ----------------------------
-- Table structure for edu_video
-- ----------------------------
DROP TABLE IF EXISTS `edu_video`;
CREATE TABLE `edu_video`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '视频ID',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程ID',
  `chapter_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点名称',
  `video_source_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '云端视频资源',
  `video_original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原始文件名称',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `play_count` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '播放次数',
  `is_free` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否可以试听：0收费 1免费',
  `duration` float NOT NULL DEFAULT 0 COMMENT '视频时长（秒）',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Empty' COMMENT 'Empty未上传 Transcoding转码中  Normal正常',
  `size` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '视频源文件大小（字节）',
  `version` bigint(20) UNSIGNED NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE,
  INDEX `idx_chapter_id`(`chapter_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程视频' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_video
-- ----------------------------
INSERT INTO `edu_video` VALUES ('1182499307429339137', '18', '32', '第一节', '', '', 0, 0, 0, 0, '', 0, 1, '2019-10-11 11:32:59', '2019-10-11 11:57:38');
INSERT INTO `edu_video` VALUES ('1185312444399071234', '14', '1', '12', '', '', 0, 0, 0, 0, 'Empty', 0, 1, '2019-10-19 05:51:23', '2019-10-19 05:51:33');
INSERT INTO `edu_video` VALUES ('1189434737808990210', '18', '44', '测试', '', '', 1, 0, 0, 0, 'Empty', 0, 1, '2019-10-30 14:51:55', '2019-10-30 14:51:55');
INSERT INTO `edu_video` VALUES ('1189476403626409986', '18', '1181729226915577857', '22', '5155c73dc112475cbbddccf4723f7cef', '视频.mp4', 0, 0, 0, 0, 'Empty', 0, 1, '2019-10-30 17:37:29', '2019-10-30 17:37:29');
INSERT INTO `edu_video` VALUES ('1192252824606289921', '1192252213659774977', '1192252428399751169', '第一课时', '756cf06db9cb4f30be85a9758b19c645', 'eae2b847ef8503b81f5d5593d769dde2.mp4', 0, 0, 0, 0, 'Empty', 0, 1, '2019-11-07 09:29:59', '2019-11-07 09:29:59');
INSERT INTO `edu_video` VALUES ('1192628092797730818', '1192252213659774977', '1192252428399751169', '第二课时', '2a02d726622f4c7089d44cb993c531e1', 'eae2b847ef8503b81f5d5593d769dde2.mp4', 0, 0, 1, 0, 'Empty', 0, 1, '2019-11-08 10:21:10', '2019-11-08 10:21:22');
INSERT INTO `edu_video` VALUES ('1192632495013380097', '1192252213659774977', '1192252428399751169', '第三课时', '4e560c892fdf4fa2b42e0671aa42fa9d', 'eae2b847ef8503b81f5d5593d769dde2.mp4', 0, 0, 1, 0, 'Empty', 0, 1, '2019-11-08 10:38:40', '2019-11-08 10:38:40');
INSERT INTO `edu_video` VALUES ('1194117638832111617', '1192252213659774977', '1192252428399751169', '第四课时', '4e560c892fdf4fa2b42e0671aa42fa9d', 'eae2b847ef8503b81f5d5593d769dde2.mp4', 0, 0, 0, 0, 'Empty', 0, 1, '2019-11-12 13:00:05', '2019-11-12 13:00:05');
INSERT INTO `edu_video` VALUES ('1196263770832023554', '1192252213659774977', '1192252428399751169', '第五课时', '27d21158b0834cb5a8d50710937de330', 'eae2b847ef8503b81f5d5593d769dde2.mp4', 5, 0, 0, 0, 'Empty', 0, 1, '2019-11-18 11:08:03', '2019-11-18 11:08:03');
INSERT INTO `edu_video` VALUES ('1358595061686538241', '18', '15', '法规的健康', '', NULL, 20, 0, 0, 0, 'Empty', 0, 1, '2021-02-08 09:54:39', '2021-02-08 09:54:39');
INSERT INTO `edu_video` VALUES ('1358595511655665666', '18', '1358586273202016258', '阿萨德啊', '', NULL, 2, 0, 0, 0, 'Empty', 0, 1, '2021-02-08 09:56:26', '2021-02-08 09:56:30');
INSERT INTO `edu_video` VALUES ('1366261697004482561', '18', '1181729226915577857', '打切勿', '', NULL, 0, 0, 0, 0, 'Empty', 0, 1, '2021-03-01 13:39:07', '2021-03-01 13:39:07');
INSERT INTO `edu_video` VALUES ('1367347753678381057', '1367347590658367490', '1367347615903883265', '请问', '142a45db718847ddbedb2aa3e24a54dc', 'testvideo.mp4', 2, 0, 0, 0, 'Empty', 0, 1, '2021-03-04 13:34:43', '2021-03-04 13:34:43');
INSERT INTO `edu_video` VALUES ('1367348013221912578', '1367347922616557570', '1367347947643969537', '伟大', 'e1a00e9214674700847d9794a53b5fad', 'testvideo2.mp4', 1, 0, 0, 0, 'Empty', 0, 1, '2021-03-04 13:35:45', '2021-03-04 13:35:45');
INSERT INTO `edu_video` VALUES ('1367765596723019777', '1367765455018459138', '1367765493601861634', '第一章一小节', '5fd1fbfafcb9420e91e9d9b4f33fcc60', 'testvideo.mp4', 1, 0, 0, 0, 'Empty', 0, 1, '2021-03-05 17:15:05', '2021-03-05 17:15:05');
INSERT INTO `edu_video` VALUES ('1377082537455480834', '1377082402856071169', '1377082442815205377', '第一节', 'e434ead3052043768223248ee811ab80', 'testvideo.mp4', 1, 0, 0, 0, 'Empty', 0, 1, '2021-03-31 10:17:17', '2021-03-31 10:17:17');
INSERT INTO `edu_video` VALUES ('1377083744177713154', '1377083634534412290', '1377083666188824577', '第一小节', 'eeee4697f882488fa4ceb224f3a0cf88', 'testvideo.mp4', 0, 0, 0, 0, 'Empty', 0, 1, '2021-03-31 10:22:04', '2021-03-31 10:22:04');
INSERT INTO `edu_video` VALUES ('17', '18', '15', '第一节：Java简介', '196116a6fee742e1ba9f6c18f65bd8c1', '1', 1, 1000, 1, 100, 'Draft', 0, 1, '2019-01-01 13:08:57', '2019-10-11 11:26:39');
INSERT INTO `edu_video` VALUES ('18', '18', '15', '第二节：表达式和赋值语句', '2d99b08ca0214909899910c9ba042d47', '7 - How Do I Find Time for My ', 2, 999, 1, 100, 'Draft', 0, 1, '2019-01-01 13:09:02', '2019-03-08 03:30:27');
INSERT INTO `edu_video` VALUES ('19', '18', '15', '第三节：String类', '51120d59ddfd424cb5ab08b44fc8b23a', 'eae2b847ef8503b81f5d5593d769dde2.mp4', 3, 888, 0, 100, 'Draft', 0, 1, '2019-01-01 13:09:05', '2019-11-12 12:50:45');
INSERT INTO `edu_video` VALUES ('20', '18', '15', '第四节：程序风格', '2a38988892d84df598752226c50f3fa3', '00-day10总结.avi', 4, 666, 0, 100, 'Draft', 0, 1, '2019-01-01 13:09:05', '2019-10-11 09:20:09');

-- ----------------------------
-- Table structure for ucenter_member
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_member`;
CREATE TABLE `ucenter_member`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会员id',
  `openid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(2) UNSIGNED NULL DEFAULT NULL COMMENT '性别 1 女，2 男',
  `age` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `sign` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户签名',
  `is_disabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用 1（true）已禁用，  0（false）未禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ucenter_member
-- ----------------------------
INSERT INTO `ucenter_member` VALUES ('1377140286193152001', NULL, '17701629866', 'e10adc3949ba59abbe56e057f20f883e', 'care', NULL, NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', NULL, 0, 0, '2021-03-31 14:06:45', '2021-03-31 14:06:45');
INSERT INTO `ucenter_member` VALUES ('1377140669426708481', NULL, '17701629869', 'e10adc3949ba59abbe56e057f20f883e', 'care', NULL, NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', NULL, 0, 0, '2021-03-31 14:08:16', '2021-03-31 14:08:16');
INSERT INTO `ucenter_member` VALUES ('1377449520516689922', 'o3_SC50tJyOyc5Vlu6OzJnlMNvfg', '', NULL, 'X.6-CARE', NULL, 1, 'https://thirdwx.qlogo.cn/mmopen/vi_32/1LSSCaDvib3NibicxUckbhq4RibADjxPfEYfNecibqmVjRUtxv8uSnWJg1A71FwLjHeMTPZhqyYs5Kxchxvzt7ibGz1A/132', NULL, 0, 0, '2021-04-01 10:35:32', '2021-04-01 10:35:32');

SET FOREIGN_KEY_CHECKS = 1;
