/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80041 (8.0.41)
 Source Host           : localhost:3306
 Source Schema         : biomass

 Target Server Type    : MySQL
 Target Server Version : 80041 (8.0.41)
 File Encoding         : 65001

 Date: 19/04/2025 16:22:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mangrove
-- ----------------------------
DROP TABLE IF EXISTS `mangrove`;
CREATE TABLE `mangrove`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '树名',
  `scientific_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学名',
  `description` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `density` double NULL DEFAULT NULL COMMENT '密度',
  `carbon_content_ratio` double NULL DEFAULT NULL COMMENT '含碳率',
  `formula` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '计算key',
  `img` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '图片',
  `create_by` int NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_at` bigint NOT NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mangrove
-- ----------------------------
INSERT INTO `mangrove` VALUES (1, '桐花树', 'Aegiceras', '', 0.8, 0.44, 'Aegiceras', NULL, 1, '2025-04-06 20:28:27', 1, '2025-04-18 22:09:05', 0);
INSERT INTO `mangrove` VALUES (2, '白骨壤', NULL, NULL, 0.62, 0.41, 'Avicennia', NULL, 1, '2025-04-06 20:31:36', 1, '2025-04-18 22:09:15', 0);
INSERT INTO `mangrove` VALUES (3, '木榄', NULL, NULL, 0.81, 0.46, 'Bgymnorrhiza', NULL, 1, '2025-04-06 20:31:54', 1, '2025-04-19 06:35:44', 0);
INSERT INTO `mangrove` VALUES (4, '海莲', NULL, NULL, 0.7, 0.46, 'Bgymnorrhiza', NULL, 1, '2025-04-06 20:32:29', 1, '2025-04-19 06:35:46', 0);
INSERT INTO `mangrove` VALUES (5, '尖瓣海莲', NULL, NULL, 0.7, 0.46, 'Bgymnorrhiza', NULL, 1, '2025-04-06 20:32:45', 1, '2025-04-19 06:35:48', 0);
INSERT INTO `mangrove` VALUES (6, '角果木', NULL, NULL, 0.85, 0.46, 'CeriopsTagal', NULL, 1, '2025-04-06 20:33:00', 1, '2025-04-18 22:10:17', 0);
INSERT INTO `mangrove` VALUES (7, '秋茄', NULL, NULL, 0.7, 0.47, 'Kandelia', NULL, 1, '2025-04-06 20:13:01', 1, '2025-04-18 22:10:24', 0);
INSERT INTO `mangrove` VALUES (8, '红树', NULL, NULL, 0.87, 0.46, 'Rhizophora', NULL, 1, '2025-04-06 20:37:52', 1, '2025-04-18 22:11:23', 0);
INSERT INTO `mangrove` VALUES (9, '拉氏红树', NULL, NULL, 0.8, 0.46, 'Rhizophora', NULL, 1, '2025-04-06 20:38:20', 1, '2025-04-18 22:11:33', 0);
INSERT INTO `mangrove` VALUES (10, '红海榄', NULL, NULL, 0.85, 0.46, 'Stylosa', NULL, 1, '2025-04-06 20:38:06', 1, '2025-04-19 06:42:13', 0);
INSERT INTO `mangrove` VALUES (11, '红榄李', NULL, NULL, 0.71, 0.46, 'Common', NULL, 1, '2025-04-06 20:37:38', 1, '2025-04-18 21:57:48', 0);
INSERT INTO `mangrove` VALUES (12, '海桑', NULL, NULL, 0.5, 0.43, 'Sonneratia', NULL, 1, '2025-04-06 20:38:48', 1, '2025-04-19 06:44:15', 0);
INSERT INTO `mangrove` VALUES (13, '杯萼海桑', NULL, NULL, 0.47, 0.47, 'Sonneratia', NULL, 1, '2025-04-06 20:38:34', 1, '2025-04-19 06:44:29', 0);
INSERT INTO `mangrove` VALUES (14, '海桑属', NULL, NULL, 0.5, 0.41, 'Sonneratia', NULL, 1, '2025-04-06 20:39:25', 1, '2025-04-19 06:44:39', 0);
INSERT INTO `mangrove` VALUES (15, '许树', NULL, NULL, 0.71, 0.46, 'Common', NULL, 1, '2025-04-06 20:37:23', 1, '2025-04-19 06:44:32', 0);
INSERT INTO `mangrove` VALUES (16, '银叶树', NULL, NULL, 0.84, 0.46, 'Common', NULL, 1, '2025-04-06 20:33:15', 1, '2025-04-19 11:23:22', 0);
INSERT INTO `mangrove` VALUES (18, '榄李', NULL, NULL, 0.71, 0.46, 'Common', NULL, 1, '2025-04-06 20:39:48', 1, '2025-04-18 21:57:55', 0);
INSERT INTO `mangrove` VALUES (19, '黄槿', NULL, NULL, 0.34, 0.46, 'Common', NULL, 1, '2025-04-06 20:40:03', 1, '2025-04-18 21:57:57', 0);
INSERT INTO `mangrove` VALUES (20, '其他', NULL, NULL, 0.71, 0.45, 'Common', NULL, 1, '2025-04-16 22:39:11', 1, '2025-04-18 21:57:59', 0);

SET FOREIGN_KEY_CHECKS = 1;
