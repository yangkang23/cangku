-- auto Generated on 2021-04-19
-- DROP TABLE IF EXISTS conditioning;
CREATE TABLE conditioning(
	id INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
	progress VARCHAR (50) DEFAULT '' COMMENT '进度',
	direction VARCHAR (50) DEFAULT '' COMMENT '调理方向',
	outside VARCHAR (50) DEFAULT '' COMMENT '外部理疗',
	inside VARCHAR (50) DEFAULT '' COMMENT '内服调理',
	other VARCHAR (50) DEFAULT '' COMMENT '其他调理',
	planid INT (11) DEFAULT -1 COMMENT '外键',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'conditioning';
