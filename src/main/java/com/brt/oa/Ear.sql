-- auto Generated on 2021-04-19
-- DROP TABLE IF EXISTS ear;
CREATE TABLE ear(
	id VARCHAR (50) NOT NULL COMMENT 'id',
	deaf VARCHAR (50) COMMENT '耳聋',
	deaf_time VARCHAR (50) COMMENT '耳聋时长',
	tinnitus VARCHAR (50) COMMENT '耳鸣',
	tinnitus_time VARCHAR (50) COMMENT '耳鸣时长',
	other VARCHAR (50) COMMENT '其他',
	other_time VARCHAR (50) COMMENT '其他时长',
	planid INT (11) COMMENT '外键',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'ear';
