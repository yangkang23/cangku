-- auto Generated on 2021-04-09 22:31:57 
-- DROP TABLE IF EXISTS `emp`; 
CREATE TABLE emp(
    `id` INTEGER(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'name',
    `phone` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'phone',
    `entry_time` BIGINT NOT NULL DEFAULT -1 COMMENT 'entry_time',
    `sex` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'sex',
    `position_level` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'position_level',
    `storeid` INTEGER(12) NOT NULL DEFAULT -1 COMMENT 'storeid',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'emp';
