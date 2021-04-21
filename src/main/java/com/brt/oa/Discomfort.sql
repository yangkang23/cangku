-- auto Generated on 2021-04-19
-- DROP TABLE IF EXISTS discomfort;
CREATE TABLE discomfort(
	id INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
	otherDiscomfort VARCHAR (50) COMMENT '其他不适',
	otherDiscomfort_time VARCHAR (50) COMMENT '其他不适时长',
	`operation` VARCHAR (50) COMMENT '手术史',
	planid VARCHAR (50) COMMENT '外键',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'discomfort';
