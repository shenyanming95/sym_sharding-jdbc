-- 需要先配置好MySQL的主从复制环境, 然后分别在主库和从库之间创建数据库和数据表

-- 先创建主库
CREATE DATABASE sharding_jdbc_slave DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE sharding_jdbc_slave;

-- 再创建数据表
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT '',
  `real_name` varchar(64) DEFAULT '',
  `is_deleted` tinyint(255) DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;