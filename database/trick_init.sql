/*
 Navicat Premium Data Transfer

 Source Server         : 10.1.103.108
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 10.1.103.108:3306
 Source Schema         : testol

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for interface_msg
-- ----------------------------
DROP TABLE IF EXISTS `interface_msg`;
CREATE TABLE `interface_msg`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `request_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `url_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of interface_msg
-- ----------------------------


-- ----------------------------
-- Table structure for test_case
-- ----------------------------
DROP TABLE IF EXISTS `test_case`;
CREATE TABLE `test_case`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `case_status` int(11) NULL DEFAULT NULL,
  `interfacemsg_id` bigint(20) NULL DEFAULT NULL,
  `desc_case` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `headers_parames` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `body_parames` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `expect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `actual` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_case
-- ----------------------------
INSERT INTO `test_case` VALUES (1, 1, 1, 'corpid/corpsecret参数均为空', '{\"Content-Type\":\"application/json\"}', '{\"corpid\":\"\",\"corpsecret\":\"\"}', '\" \"\"errcode\"\": 41004,', NULL, NULL);


-- ----------------------------
-- Table structure for variable_substitution
-- ----------------------------
DROP TABLE IF EXISTS `variable_substitution`;
CREATE TABLE `variable_substitution`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `reflect_calss` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `reflect_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `reflect_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of variable_substitution
-- ----------------------------
INSERT INTO `variable_substitution` VALUES (1, '${corpidTrue}', '', 'com.javaelf.dataForger.TestDataFactory', 'getCorpidTrue', 'ww27d6f876d80ceec6', '有效corpid');

SET FOREIGN_KEY_CHECKS = 1;
