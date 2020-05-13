-- 先创建数据库
CREATE DATABASE sharding_jdbc_1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE sharding_jdbc_1;

-- 再创建数据表
DROP TABLE IF EXISTS `t_simple_1`;
CREATE TABLE `t_simple_1`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `is_deleted` tinyint(255) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
);

CREATE TABLE `t_simple_2`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `is_deleted` tinyint(255) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
);