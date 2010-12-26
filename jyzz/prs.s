# SQL Manager 2010 Lite for MySQL 4.5.0.9
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : prs


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

SET FOREIGN_KEY_CHECKS=0;

USE `prs`;

#
# Structure for the `prs_area` table : 
#

DROP TABLE IF EXISTS `prs_area`;

CREATE TABLE `prs_area` (
  `areaid` mediumint(9) NOT NULL default '0',
  `stype` varchar(32) default NULL,
  `name` varchar(64) default NULL,
  `fatherid` bigint(20) default NULL,
  `code` varchar(8) default NULL,
  `zip` varchar(8) default NULL,
  `description` varchar(128) default NULL,
  `sortnum` int(11) default NULL,
  `canmodify` char(1) NOT NULL default '0',
  `createtime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`areaid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for the `prs_articles` table : 
#

DROP TABLE IF EXISTS `prs_articles`;

CREATE TABLE `prs_articles` (
  `articleid` mediumint(8) NOT NULL auto_increment,
  `catid` mediumint(8) default NULL,
  `title` varchar(150) default NULL,
  `content` longtext,
  `authorid` mediumint(8) default NULL,
  `keywords` varchar(255) default NULL,
  `isopen` char(1) default NULL,
  `createtime` int(11) default NULL,
  `fileurl` varchar(150) default NULL,
  PRIMARY KEY  (`articleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `prs_cartitems` table : 
#

DROP TABLE IF EXISTS `prs_cartitems`;

CREATE TABLE `prs_cartitems` (
  `cartitemid` mediumint(8) NOT NULL auto_increment,
  `cartid` mediumint(8) default NULL,
  `goodsid` mediumint(9) NOT NULL,
  `quantity` mediumint(9) NOT NULL default '1',
  `monthnum` mediumint(9) NOT NULL default '6',
  `price` double(9,2) NOT NULL,
  PRIMARY KEY  (`cartitemid`)
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=utf8;

#
# Structure for the `prs_carts` table : 
#

DROP TABLE IF EXISTS `prs_carts`;

CREATE TABLE `prs_carts` (
  `cartid` mediumint(8) NOT NULL auto_increment,
  `userid` mediumint(8) NOT NULL,
  `username` varchar(32) NOT NULL,
  `createtime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`cartid`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

#
# Structure for the `prs_cats` table : 
#

DROP TABLE IF EXISTS `prs_cats`;

CREATE TABLE `prs_cats` (
  `categoryid` mediumint(8) NOT NULL auto_increment,
  `cattype` varchar(32) default NULL,
  `catname` varchar(32) default NULL,
  `fatherid` mediumint(8) default NULL,
  PRIMARY KEY  (`categoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

#
# Structure for the `prs_consignees` table : 
#

DROP TABLE IF EXISTS `prs_consignees`;

CREATE TABLE `prs_consignees` (
  `consigneeid` mediumint(8) NOT NULL auto_increment,
  `userid` mediumint(8) NOT NULL,
  `username` varchar(255) NOT NULL,
  `consigneename` varchar(255) NOT NULL,
  `provinceid` mediumint(9) NOT NULL,
  `cityid` mediumint(9) NOT NULL,
  `street` varchar(255) NOT NULL,
  `postcode` varchar(20) NOT NULL,
  `tele` varchar(20) NOT NULL,
  `createtime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `dft` char(1) NOT NULL default 'F',
  PRIMARY KEY  (`consigneeid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

#
# Structure for the `prs_dispatches` table : 
#

DROP TABLE IF EXISTS `prs_dispatches`;

CREATE TABLE `prs_dispatches` (
  `dispatchId` int(11) NOT NULL auto_increment,
  `createtime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `userid` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `status` char(2) NOT NULL,
  `orderid` int(11) NOT NULL,
  PRIMARY KEY  (`dispatchId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Structure for the `prs_dispatchitemgoodses` table : 
#

DROP TABLE IF EXISTS `prs_dispatchitemgoodses`;

CREATE TABLE `prs_dispatchitemgoodses` (
  `dispatchitemgoodsid` int(11) NOT NULL auto_increment,
  `dispatchItemId` int(11) NOT NULL,
  `goodsid` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY  (`dispatchitemgoodsid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

#
# Structure for the `prs_dispatchitems` table : 
#

DROP TABLE IF EXISTS `prs_dispatchitems`;

CREATE TABLE `prs_dispatchitems` (
  `dispatchItemId` int(11) NOT NULL auto_increment,
  `dispatchId` int(11) NOT NULL,
  `dispatchDate` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `userid` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `itemNum` int(11) NOT NULL,
  `sequence` int(11) default NULL,
  PRIMARY KEY  (`dispatchItemId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

#
# Structure for the `prs_goods` table : 
#

DROP TABLE IF EXISTS `prs_goods`;

CREATE TABLE `prs_goods` (
  `goodsid` mediumint(8) NOT NULL auto_increment,
  `goodsname` varchar(255) default NULL,
  `detail` varchar(255) default NULL,
  `issale` char(1) default NULL,
  `createtime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `sn` varchar(255) default NULL,
  `price` double(8,2) default NULL,
  `packageid` mediumint(8) default NULL COMMENT '包装',
  `publishid` mediumint(8) default NULL,
  `typeid` mediumint(8) default NULL COMMENT '类型',
  `jyid` varchar(32) default NULL,
  `authorname` varchar(255) default NULL,
  `authorid` mediumint(9) default NULL,
  `categoryid` mediumint(9) default NULL,
  PRIMARY KEY  (`goodsid`)
) ENGINE=InnoDB AUTO_INCREMENT=476 DEFAULT CHARSET=utf8;

#
# Structure for the `prs_imgs` table : 
#

DROP TABLE IF EXISTS `prs_imgs`;

CREATE TABLE `prs_imgs` (
  `imgid` mediumint(8) NOT NULL auto_increment,
  `createtime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `targetid` mediumint(8) default NULL,
  `url` varchar(255) default NULL,
  `targettype` varchar(255) default NULL,
  `dftimg` char(1) NOT NULL default 'F',
  PRIMARY KEY  (`imgid`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

#
# Structure for the `prs_objectcats` table : 
#

DROP TABLE IF EXISTS `prs_objectcats`;

CREATE TABLE `prs_objectcats` (
  `objectid` mediumint(8) NOT NULL auto_increment,
  `categoryid` mediumint(8) default NULL,
  `targetid` mediumint(8) default NULL,
  `cattype` varchar(32) character set utf8 collate utf8_bin default NULL,
  PRIMARY KEY  (`objectid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Structure for the `prs_options` table : 
#

DROP TABLE IF EXISTS `prs_options`;

CREATE TABLE `prs_options` (
  `optionid` mediumint(9) NOT NULL,
  `optionname` varchar(64) default NULL,
  `optionvalue` longtext,
  PRIMARY KEY  (`optionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `prs_orderitems` table : 
#

DROP TABLE IF EXISTS `prs_orderitems`;

CREATE TABLE `prs_orderitems` (
  `orderitemid` int(11) NOT NULL auto_increment,
  `goodsid` int(11) NOT NULL,
  `orderid` mediumint(9) NOT NULL,
  `price` decimal(11,2) default NULL,
  `quantity` int(4) NOT NULL,
  `monthnum` int(5) default NULL,
  `createtime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`orderitemid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

#
# Structure for the `prs_orders` table : 
#

DROP TABLE IF EXISTS `prs_orders`;

CREATE TABLE `prs_orders` (
  `orderid` int(11) NOT NULL auto_increment,
  `createtime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `userid` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `ordersn` varchar(32) NOT NULL,
  `status` char(2) NOT NULL,
  `totalPrice` decimal(9,2) NOT NULL,
  `consigneeid` int(11) NOT NULL,
  PRIMARY KEY  (`orderid`),
  UNIQUE KEY `ordersn` (`ordersn`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

#
# Structure for the `prs_resources` table : 
#

DROP TABLE IF EXISTS `prs_resources`;

CREATE TABLE `prs_resources` (
  `resourceid` mediumint(8) NOT NULL auto_increment,
  `type` varchar(32) default NULL,
  `value` varchar(32) default NULL,
  `resourcename` varchar(32) default NULL,
  PRIMARY KEY  (`resourceid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='???';

#
# Structure for the `prs_roleresources` table : 
#

DROP TABLE IF EXISTS `prs_roleresources`;

CREATE TABLE `prs_roleresources` (
  `roleid` mediumint(8) NOT NULL,
  `resourceid` mediumint(8) NOT NULL,
  PRIMARY KEY  (`roleid`,`resourceid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `prs_roles` table : 
#

DROP TABLE IF EXISTS `prs_roles`;

CREATE TABLE `prs_roles` (
  `roleid` mediumint(8) NOT NULL auto_increment,
  `rolename` varchar(20) default NULL,
  `remark` varchar(250) default NULL,
  `updatetime` date default NULL,
  PRIMARY KEY  (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='??';

#
# Structure for the `prs_usermeta` table : 
#

DROP TABLE IF EXISTS `prs_usermeta`;

CREATE TABLE `prs_usermeta` (
  `umeta_id` mediumint(9) unsigned NOT NULL auto_increment,
  `user_id` mediumint(9) unsigned NOT NULL default '0',
  `meta_key` varchar(255) default NULL,
  `meta_value` longtext,
  PRIMARY KEY  (`umeta_id`),
  KEY `user_id` (`user_id`),
  KEY `meta_key` (`meta_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `prs_usermetaoption` table : 
#

DROP TABLE IF EXISTS `prs_usermetaoption`;

CREATE TABLE `prs_usermetaoption` (
  `usermetaoption` mediumint(9) NOT NULL auto_increment,
  `usermetaname` varchar(255) default NULL,
  `usermetavalue` varchar(255) default NULL,
  `disable` char(1) NOT NULL default '0',
  PRIMARY KEY  (`usermetaoption`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `prs_useroles` table : 
#

DROP TABLE IF EXISTS `prs_useroles`;

CREATE TABLE `prs_useroles` (
  `userid` mediumint(8) NOT NULL,
  `roleid` mediumint(8) NOT NULL,
  PRIMARY KEY  (`userid`,`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `prs_users` table : 
#

DROP TABLE IF EXISTS `prs_users`;

CREATE TABLE `prs_users` (
  `userid` mediumint(8) NOT NULL auto_increment,
  `username` varchar(255) NOT NULL,
  `openid` varchar(255) default NULL,
  `email` varchar(255) NOT NULL,
  `createtime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `realname` varchar(255) NOT NULL default '',
  `password` varchar(32) NOT NULL,
  `disable` char(1) NOT NULL default '0',
  PRIMARY KEY  (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='??';

#
# Data for the `prs_area` table  (LIMIT 0,500)
#

INSERT INTO `prs_area` (`areaid`, `stype`, `name`, `fatherid`, `code`, `zip`, `description`, `sortnum`, `canmodify`, `createtime`) VALUES 
  (1,'AREA_COUNTRY','中国',-1,NULL,NULL,NULL,NULL,'0','2010-12-03 12:43:54'),
  (20031,'AREA_PROVINCE','上海市',1,NULL,NULL,NULL,NULL,'0','2010-12-03 00:00:00'),
  (30031,'AREA_CITY','上海',20031,NULL,NULL,NULL,NULL,'0','2010-12-03 12:55:56');
COMMIT;

#
# Data for the `prs_cartitems` table  (LIMIT 0,500)
#

INSERT INTO `prs_cartitems` (`cartitemid`, `cartid`, `goodsid`, `quantity`, `monthnum`, `price`) VALUES 
  (191,71,1,2,6,5.60),
  (192,72,1,3,6,5.60),
  (193,72,2,1,6,8.00),
  (194,73,1,3,6,5.60),
  (195,74,1,3,6,5.60);
COMMIT;

#
# Data for the `prs_carts` table  (LIMIT 0,500)
#

INSERT INTO `prs_carts` (`cartid`, `userid`, `username`, `createtime`) VALUES 
  (72,1,'admin','2010-12-08 15:59:32'),
  (73,1,'admin','2010-12-13 15:10:17'),
  (74,1,'admin','2010-12-24 10:51:51');
COMMIT;

#
# Data for the `prs_cats` table  (LIMIT 0,500)
#

INSERT INTO `prs_cats` (`categoryid`, `cattype`, `catname`, `fatherid`) VALUES 
  (8,'0','商品',NULL),
  (9,'0','书籍',8),
  (10,'0','报纸',8),
  (11,'0','杂志',9);
COMMIT;

#
# Data for the `prs_consignees` table  (LIMIT 0,500)
#

INSERT INTO `prs_consignees` (`consigneeid`, `userid`, `username`, `consigneename`, `provinceid`, `cityid`, `street`, `postcode`, `tele`, `createtime`, `dft`) VALUES 
  (1,1,'admin','张三',20031,30031,'淮海中路','200121','123456','2010-12-03 14:12:07','T'),
  (2,1,'admin','张三',20031,30031,'淮海中路','200121','123456','2010-12-03 14:12:10','F'),
  (4,1,'admin','张三',20031,30031,'淮海中路','200121','123456','2010-12-03 14:12:14','F'),
  (8,1,'admin','测试',20031,30031,'测试','20112','123456','2010-12-03 14:48:36','F'),
  (9,1,'admin','李四',20031,30031,'福建路','20002','654321','2010-12-03 15:07:36','F'),
  (10,13,'zhang1','李四',20031,30031,'宝山','200111','000000','2010-12-13 14:36:43','F'),
  (11,13,'zhang1','李四',20031,30031,'宝山','200111','000000','2010-12-13 14:37:31','F'),
  (12,13,'zhang1','张三',20031,30031,'张三','200121','1234556','2010-12-13 14:39:25','T'),
  (13,13,'zhang1','dd',20031,30031,'dd','20121','123456','2010-12-13 14:42:19','F');
COMMIT;

#
# Data for the `prs_dispatches` table  (LIMIT 0,500)
#

INSERT INTO `prs_dispatches` (`dispatchId`, `createtime`, `userid`, `username`, `status`, `orderid`) VALUES 
  (1,'2010-12-24 14:58:36',1,'admin','0',0),
  (2,'2010-12-24 15:33:47',1,'admin','0',20),
  (3,'2010-12-24 15:38:24',1,'admin','0',21);
COMMIT;

#
# Data for the `prs_dispatchitemgoodses` table  (LIMIT 0,500)
#

INSERT INTO `prs_dispatchitemgoodses` (`dispatchitemgoodsid`, `dispatchItemId`, `goodsid`, `num`, `price`) VALUES 
  (1,1,1,0,6),
  (2,2,1,0,6),
  (3,3,1,0,6),
  (4,4,1,0,6),
  (5,5,1,0,6),
  (6,6,1,0,6),
  (7,7,2,1,8),
  (8,8,2,1,8),
  (9,9,2,1,8),
  (10,10,2,1,8),
  (11,11,2,1,8),
  (12,12,2,1,8),
  (13,13,6,1,5),
  (14,14,6,1,5),
  (15,15,6,1,5),
  (16,16,6,1,5),
  (17,17,6,1,5),
  (18,18,6,1,5);
COMMIT;

#
# Data for the `prs_dispatchitems` table  (LIMIT 0,500)
#

INSERT INTO `prs_dispatchitems` (`dispatchItemId`, `dispatchId`, `dispatchDate`, `userid`, `username`, `itemNum`, `sequence`) VALUES 
  (1,1,'2011-02-24 14:58:36',1,'admin',0,0),
  (2,1,'2011-03-24 14:58:36',1,'admin',1,1),
  (3,1,'2011-04-24 14:58:36',1,'admin',2,2),
  (4,1,'2011-05-24 14:58:36',1,'admin',3,3),
  (5,1,'2011-06-24 14:58:36',1,'admin',4,4),
  (6,1,'2011-07-24 14:58:36',1,'admin',5,5),
  (7,2,'2011-02-24 15:33:47',1,'admin',0,0),
  (8,2,'2011-03-24 15:33:47',1,'admin',1,1),
  (9,2,'2011-04-24 15:33:47',1,'admin',2,2),
  (10,2,'2011-05-24 15:33:47',1,'admin',3,3),
  (11,2,'2011-06-24 15:33:47',1,'admin',4,4),
  (12,2,'2011-07-24 15:33:47',1,'admin',5,5),
  (13,3,'2011-02-24 15:38:24',1,'admin',0,0),
  (14,3,'2011-03-24 15:38:24',1,'admin',1,1),
  (15,3,'2011-04-24 15:38:24',1,'admin',2,2),
  (16,3,'2011-05-24 15:38:24',1,'admin',3,3),
  (17,3,'2011-06-24 15:38:24',1,'admin',4,4),
  (18,3,'2011-07-24 15:38:24',1,'admin',5,5);
COMMIT;

#
# Data for the `prs_goods` table  (LIMIT 0,500)
#

INSERT INTO `prs_goods` (`goodsid`, `goodsname`, `detail`, `issale`, `createtime`, `sn`, `price`, `packageid`, `publishid`, `typeid`, `jyid`, `authorname`, `authorid`, `categoryid`) VALUES 
  (1,'青年一代','青年一代','T','2010-11-29 12:46:51',NULL,5.60,NULL,NULL,NULL,'001001',NULL,NULL,11),
  (2,'新民周刊','新民周刊','T','2010-11-29 13:28:41',NULL,8.00,NULL,NULL,NULL,'001002',NULL,NULL,11),
  (3,'青年一代','青年一代','F','2010-11-29 13:54:31',NULL,5.00,NULL,NULL,NULL,'001001',NULL,NULL,11),
  (4,'青年一代','青年一代','F','2010-11-29 13:54:40',NULL,5.00,NULL,NULL,NULL,'001001',NULL,NULL,11),
  (5,'青年一代','青年一代','F','2010-11-29 10:26:39',NULL,5.00,NULL,NULL,NULL,'001001',NULL,NULL,11),
  (6,'经济展望','经济展望','T','2010-11-29 13:55:43',NULL,4.55,NULL,NULL,NULL,'001012',NULL,NULL,11),
  (7,'新民周刊','新民周刊','F','2010-11-30 13:42:22',NULL,8.00,NULL,NULL,NULL,'001002','admin',1,11),
  (8,'上海电视(包销)','上海电视(包销)','F','2010-11-30 14:40:04',NULL,4.50,NULL,NULL,NULL,'001003',NULL,NULL,11),
  (9,'海外文摘','海外文摘','F','2010-11-30 13:42:22',NULL,4.50,NULL,NULL,NULL,'001007','admin',1,11),
  (10,'轿车情报','轿车情报','F','2010-11-30 13:42:22',NULL,15.00,NULL,NULL,NULL,'001009','admin',1,11),
  (11,'经济展望','经济展望','F','2010-11-30 13:42:22',NULL,15.00,NULL,NULL,NULL,'001012','admin',1,11),
  (12,'新周刊','新周刊','F','2010-11-30 13:42:22',NULL,15.00,NULL,NULL,NULL,'001013','admin',1,11),
  (13,'电影故事','电影故事','F','2010-11-30 13:42:23',NULL,5.00,NULL,NULL,NULL,'001015','admin',1,11),
  (14,'世界都市','世界都市','F','2010-11-30 13:42:23',NULL,20.00,NULL,NULL,NULL,'001017','admin',1,11),
  (15,'健康之友','健康之友','F','2010-11-30 13:42:23',NULL,12.00,NULL,NULL,NULL,'001019','admin',1,11),
  (16,'世界时装之苑','世界时装之苑','F','2010-11-30 13:42:23',NULL,20.00,NULL,NULL,NULL,'001020','admin',1,11),
  (17,'米老鼠','米老鼠','F','2010-11-30 13:42:23',NULL,8.80,NULL,NULL,NULL,'001024','admin',1,11),
  (18,'乐名牌世界','乐名牌世界','F','2010-11-30 13:42:23',NULL,5.00,NULL,NULL,NULL,'001026','admin',1,11),
  (19,'萌芽','萌芽','F','2010-11-30 13:42:23',NULL,4.80,NULL,NULL,NULL,'001028','admin',1,11),
  (20,'租售情报','租售情报','F','2010-11-30 13:42:23',NULL,5.00,NULL,NULL,NULL,'001032','admin',1,11),
  (21,'F1速报','F1速报','F','2010-11-30 13:42:23',NULL,15.00,NULL,NULL,NULL,'001034','admin',1,11),
  (22,'新现代画报','新现代画报','F','2010-11-30 13:42:23',NULL,20.00,NULL,NULL,NULL,'001035','admin',1,11),
  (23,'现代青年','现代青年','F','2010-11-30 13:42:23',NULL,5.00,NULL,NULL,NULL,'001040','admin',1,11),
  (24,'希望','希望','F','2010-11-30 13:42:23',NULL,12.00,NULL,NULL,NULL,'001044','admin',1,11),
  (25,'昕薇','昕薇','F','2010-11-30 13:42:23',NULL,20.00,NULL,NULL,NULL,'001048','admin',1,11),
  (26,'风采','风采','F','2010-11-30 13:42:23',NULL,16.00,NULL,NULL,NULL,'001049','admin',1,11),
  (27,'女友','女友','F','2010-11-30 13:42:23',NULL,6.00,NULL,NULL,NULL,'001050','admin',1,11),
  (28,'检察风云','检察风云','F','2010-11-30 13:42:23',NULL,5.50,NULL,NULL,NULL,'001052','admin',1,11),
  (29,'青年视觉','青年视觉','F','2010-11-30 13:42:23',NULL,30.00,NULL,NULL,NULL,'001054','admin',1,11),
  (30,'读者(包销)','读者(包销)','F','2010-11-30 13:42:23',NULL,4.00,NULL,NULL,NULL,'001058','admin',1,11),
  (31,'科技新时代','科技新时代','F','2010-11-30 13:42:23',NULL,12.00,NULL,NULL,NULL,'001063','admin',1,11),
  (32,'上海故事(包销)','上海故事(包销)','F','2010-11-30 13:42:23',NULL,3.00,NULL,NULL,NULL,'001066','admin',1,11),
  (33,'婚姻与家庭(上)(包销)','婚姻与家庭(上)(包销)','F','2010-11-30 13:42:23',NULL,4.00,NULL,NULL,NULL,'001069','admin',1,11),
  (34,'少男少女','少男少女','F','2010-11-30 13:42:23',NULL,5.00,NULL,NULL,NULL,'001071','admin',1,11),
  (35,'中外书摘','中外书摘','F','2010-11-30 13:42:23',NULL,6.00,NULL,NULL,NULL,'001074','admin',1,11),
  (36,'收获','收获','F','2010-11-30 13:42:23',NULL,15.00,NULL,NULL,NULL,'001080','admin',1,11),
  (37,'世界军事(包销)','世界军事(包销)','F','2010-11-30 13:42:23',NULL,5.00,NULL,NULL,NULL,'001088','admin',1,11),
  (38,'上海采风','上海采风','F','2010-11-30 13:42:23',NULL,10.00,NULL,NULL,NULL,'001089','admin',1,11),
  (39,'南风窗','南风窗','F','2010-11-30 13:42:23',NULL,8.00,NULL,NULL,NULL,'001090','admin',1,11),
  (40,'汽车杂志','汽车杂志','F','2010-11-30 13:42:23',NULL,15.00,NULL,NULL,NULL,'001093','admin',1,11),
  (41,'中国汽车画报','中国汽车画报','F','2010-11-30 13:42:23',NULL,18.00,NULL,NULL,NULL,'001094','admin',1,11),
  (42,'虹','虹','F','2010-11-30 13:42:23',NULL,20.00,NULL,NULL,NULL,'001095','admin',1,11),
  (43,'故事会(包销)','故事会(包销)','F','2010-11-30 13:42:23',NULL,3.00,NULL,NULL,NULL,'001096','admin',1,11),
  (44,'自我保健','自我保健','F','2010-11-30 13:42:24',NULL,5.00,NULL,NULL,NULL,'001097','admin',1,11),
  (45,'经理人','经理人','F','2010-11-30 13:42:24',NULL,20.00,NULL,NULL,NULL,'001098','admin',1,11),
  (46,'你','你','F','2010-11-30 13:42:24',NULL,20.00,NULL,NULL,NULL,'001108','admin',1,11),
  (47,'艺术世界','艺术世界','F','2010-11-30 13:42:24',NULL,20.00,NULL,NULL,NULL,'001110','admin',1,11),
  (48,'小说界','小说界','F','2010-11-30 13:42:24',NULL,13.00,NULL,NULL,NULL,'001113','admin',1,11),
  (49,'旅游天地','旅游天地','F','2010-11-30 13:42:24',NULL,18.00,NULL,NULL,NULL,'001114','admin',1,11),
  (50,'都市丽人','都市丽人','F','2010-11-30 13:42:24',NULL,6.00,NULL,NULL,NULL,'001115','admin',1,11),
  (51,'名车志','名车志','F','2010-11-30 13:42:24',NULL,15.00,NULL,NULL,NULL,'001116','admin',1,11),
  (52,'旅行者','旅行者','F','2010-11-30 13:42:24',NULL,20.00,NULL,NULL,NULL,'001119','admin',1,11),
  (53,'家居主张','家居主张','F','2010-11-30 13:42:24',NULL,20.00,NULL,NULL,NULL,'001121','admin',1,11),
  (54,'新电脑','新电脑','F','2010-11-30 13:42:24',NULL,15.00,NULL,NULL,NULL,'001123','admin',1,11),
  (55,'财经','财经','F','2010-11-30 13:42:24',NULL,15.00,NULL,NULL,NULL,'001125','admin',1,11),
  (56,'双休日','双休日','F','2010-11-30 13:42:24',NULL,8.00,NULL,NULL,NULL,'001129','admin',1,11),
  (57,'快活林','快活林','F','2010-11-30 13:42:24',NULL,5.80,NULL,NULL,NULL,'001131','admin',1,11),
  (58,'新居室','新居室','F','2010-11-30 13:42:24',NULL,10.00,NULL,NULL,NULL,'001132','admin',1,11),
  (59,'新概念电脑(数码摄影)','新概念电脑(数码摄影)','F','2010-11-30 13:42:24',NULL,18.00,NULL,NULL,NULL,'001135','admin',1,11),
  (60,'健康天地','健康天地','F','2010-11-30 13:42:24',NULL,7.80,NULL,NULL,NULL,'001137','admin',1,11),
  (61,'上海家居','上海家居','F','2010-11-30 13:42:24',NULL,20.00,NULL,NULL,NULL,'001140','admin',1,11),
  (62,'三联生活周刊','三联生活周刊','F','2010-11-30 13:42:24',NULL,10.00,NULL,NULL,NULL,'001141','admin',1,11),
  (63,'知音(包销)','知音(包销)','F','2010-11-30 13:42:24',NULL,3.90,NULL,NULL,NULL,'001143','admin',1,11),
  (64,'歌迷大世界(包销)','歌迷大世界(包销)','F','2010-11-30 13:42:24',NULL,7.00,NULL,NULL,NULL,'001145','admin',1,11),
  (65,'销售与市场','销售与市场','F','2010-11-30 13:42:24',NULL,8.00,NULL,NULL,NULL,'001146','admin',1,11),
  (66,'家庭之友','家庭之友','F','2010-11-30 13:42:24',NULL,5.00,NULL,NULL,NULL,'001147','admin',1,11),
  (67,'现代家庭','现代家庭','F','2010-11-30 13:42:24',NULL,5.00,NULL,NULL,NULL,'001151','admin',1,11),
  (68,'健康女孩','健康女孩','F','2010-11-30 13:42:24',NULL,10.00,NULL,NULL,NULL,'001152','admin',1,11),
  (69,'高尔夫','高尔夫','F','2010-11-30 13:42:24',NULL,40.00,NULL,NULL,NULL,'001155','admin',1,11),
  (70,'环球企业家','环球企业家','F','2010-11-30 13:42:24',NULL,20.00,NULL,NULL,NULL,'001159','admin',1,11),
  (71,'花溪','花溪','F','2010-11-30 13:42:24',NULL,10.00,NULL,NULL,NULL,'001162','admin',1,11),
  (72,'新潮电子','新潮电子','F','2010-11-30 13:42:24',NULL,20.00,NULL,NULL,NULL,'001167','admin',1,11),
  (73,'新财经','新财经','F','2010-11-30 13:42:24',NULL,15.00,NULL,NULL,NULL,'001168','admin',1,11),
  (74,'大都市(男)','大都市(男)','F','2010-11-30 13:42:24',NULL,15.00,NULL,NULL,NULL,'001171','admin',1,11),
  (75,'理财周刊','理财周刊','F','2010-11-30 13:42:25',NULL,8.00,NULL,NULL,NULL,'001177','admin',1,11),
  (76,'新财富','新财富','F','2010-11-30 13:42:25',NULL,20.00,NULL,NULL,NULL,'001183','admin',1,11),
  (77,'缤纷','缤纷','F','2010-11-30 13:42:25',NULL,20.00,NULL,NULL,NULL,'001187','admin',1,11),
  (78,'中国国家地理','中国国家地理','F','2010-11-30 13:42:25',NULL,20.00,NULL,NULL,NULL,'001191','admin',1,11),
  (79,'今日风采','今日风采','F','2010-11-30 13:42:25',NULL,20.00,NULL,NULL,NULL,'001194','admin',1,11),
  (80,'周末画报','周末画报','F','2010-11-30 13:42:25',NULL,5.00,NULL,NULL,NULL,'001196','admin',1,11),
  (81,'青春一族','青春一族','F','2010-11-30 13:42:25',NULL,16.00,NULL,NULL,NULL,'001199','admin',1,11),
  (82,'爱人','爱人','F','2010-11-30 13:42:25',NULL,4.50,NULL,NULL,NULL,'001200','admin',1,11),
  (83,'好日子','好日子','F','2010-11-30 13:42:25',NULL,6.00,NULL,NULL,NULL,'001201','admin',1,11),
  (84,'地产','地产','F','2010-11-30 13:42:25',NULL,10.00,NULL,NULL,NULL,'001203','admin',1,11),
  (85,'汽车族','汽车族','F','2010-11-30 13:42:25',NULL,18.00,NULL,NULL,NULL,'001206','admin',1,11),
  (86,'摄影之友','摄影之友','F','2010-11-30 13:42:25',NULL,25.00,NULL,NULL,NULL,'001208','admin',1,11),
  (87,'数码','数码','F','2010-11-30 13:42:25',NULL,18.00,NULL,NULL,NULL,'001214','admin',1,11),
  (88,'通信技术/家庭电子','通信技术/家庭电子','F','2010-11-30 13:42:25',NULL,12.00,NULL,NULL,NULL,'001216','admin',1,11),
  (89,'家庭用药','家庭用药','F','2010-11-30 13:42:25',NULL,5.00,NULL,NULL,NULL,'001219','admin',1,11),
  (90,'小熊维尼','小熊维尼','F','2010-11-30 13:42:25',NULL,12.00,NULL,NULL,NULL,'001222','admin',1,11),
  (91,'为了孩子','为了孩子','F','2010-11-30 13:42:25',NULL,6.80,NULL,NULL,NULL,'001224','admin',1,11),
  (92,'时尚育儿','时尚育儿','F','2010-11-30 13:42:25',NULL,12.00,NULL,NULL,NULL,'001232','admin',1,11),
  (93,'新视线','新视线','F','2010-11-30 13:42:25',NULL,20.00,NULL,NULL,NULL,'001234','admin',1,11),
  (94,'东方企业家','东方企业家','F','2010-11-30 13:42:25',NULL,20.00,NULL,NULL,NULL,'001235','admin',1,11),
  (95,'好主妇','好主妇','F','2010-11-30 13:42:25',NULL,20.00,NULL,NULL,NULL,'001236','admin',1,11),
  (96,'中国企业家','中国企业家','F','2010-11-30 13:42:25',NULL,20.00,NULL,NULL,NULL,'001241','admin',1,11),
  (97,'家饰','家饰','F','2010-11-30 13:42:25',NULL,18.00,NULL,NULL,NULL,'001242','admin',1,11),
  (98,'商务周刊','商务周刊','F','2010-11-30 13:42:25',NULL,10.00,NULL,NULL,NULL,'001243','admin',1,11),
  (99,'打工','打工','F','2010-11-30 13:42:25',NULL,4.00,NULL,NULL,NULL,'001245','admin',1,11),
  (100,'嘉人','嘉人','F','2010-11-30 13:42:25',NULL,20.00,NULL,NULL,NULL,'001251','admin',1,11),
  (101,'世界家苑','世界家苑','F','2010-11-30 13:42:26',NULL,15.00,NULL,NULL,NULL,'001253','admin',1,11),
  (102,'女报（上）','女报（上）','F','2010-11-30 13:42:26',NULL,5.00,NULL,NULL,NULL,'001254','admin',1,11),
  (103,'秀','秀','F','2010-11-30 13:42:26',NULL,18.00,NULL,NULL,NULL,'001255','admin',1,11),
  (104,'妈妈宝宝','妈妈宝宝','F','2010-11-30 13:42:26',NULL,12.00,NULL,NULL,NULL,'001257','admin',1,11),
  (105,'新闻周刊','新闻周刊','F','2010-11-30 13:42:26',NULL,10.00,NULL,NULL,NULL,'001259','admin',1,11),
  (106,'车时代','车时代','F','2010-11-30 13:42:26',NULL,20.00,NULL,NULL,NULL,'001260','admin',1,11),
  (107,'商业周刊','商业周刊','F','2010-11-30 13:42:26',NULL,10.00,NULL,NULL,NULL,'001265','admin',1,11),
  (108,'母子健康','母子健康','F','2010-11-30 13:42:26',NULL,15.00,NULL,NULL,NULL,'001268','admin',1,11),
  (109,'IT经理世界','IT经理世界','F','2010-11-30 13:42:26',NULL,20.00,NULL,NULL,NULL,'001276','admin',1,11),
  (110,'数码精品世界','数码精品世界','F','2010-11-30 13:42:26',NULL,18.00,NULL,NULL,NULL,'001278','admin',1,11),
  (111,'汽车导报','汽车导报','F','2010-11-30 13:42:26',NULL,15.00,NULL,NULL,NULL,'001281','admin',1,11),
  (112,'时尚橘子','时尚橘子','F','2010-11-30 13:42:26',NULL,10.00,NULL,NULL,NULL,'001283','admin',1,11),
  (113,'贝太厨房','贝太厨房','F','2010-11-30 13:42:26',NULL,10.00,NULL,NULL,NULL,'001287','admin',1,11),
  (114,'汽车之友','汽车之友','F','2010-11-30 13:42:26',NULL,10.00,NULL,NULL,NULL,'001289','admin',1,11),
  (115,'竞赛画报','竞赛画报','F','2010-11-30 13:42:26',NULL,10.00,NULL,NULL,NULL,'001292','admin',1,11),
  (116,'看电影','看电影','F','2010-11-30 13:42:26',NULL,12.80,NULL,NULL,NULL,'001296','admin',1,11),
  (117,'新娘','新娘','F','2010-11-30 13:42:26',NULL,25.00,NULL,NULL,NULL,'001297','admin',1,11),
  (118,'经济月刊','经济月刊','F','2010-11-30 13:42:26',NULL,20.00,NULL,NULL,NULL,'001299','admin',1,11),
  (119,'恋爱婚姻家庭','恋爱婚姻家庭','F','2010-11-30 13:42:26',NULL,4.00,NULL,NULL,NULL,'001303','admin',1,11),
  (120,'IT时代周刊','IT时代周刊','F','2010-11-30 13:42:26',NULL,10.00,NULL,NULL,NULL,'001309','admin',1,11),
  (121,'名牌','名牌','F','2010-11-30 13:42:26',NULL,28.00,NULL,NULL,NULL,'001310','admin',1,11),
  (122,'装潢世界','装潢世界','F','2010-11-30 13:42:26',NULL,48.00,NULL,NULL,NULL,'001315','admin',1,11),
  (123,'车王','车王','F','2010-11-30 13:42:26',NULL,20.00,NULL,NULL,NULL,'001322','admin',1,11),
  (124,'时尚伊人','时尚伊人','F','2010-11-30 13:42:26',NULL,20.00,NULL,NULL,NULL,'001324','admin',1,11),
  (125,'时尚家居','时尚家居','F','2010-11-30 13:42:26',NULL,20.00,NULL,NULL,NULL,'001325','admin',1,11),
  (126,'时尚芭莎','时尚芭莎','F','2010-11-30 13:42:27',NULL,20.00,NULL,NULL,NULL,'001326','admin',1,11),
  (127,'时尚旅游','时尚旅游','F','2010-11-30 13:42:27',NULL,20.00,NULL,NULL,NULL,'001328','admin',1,11),
  (128,'时尚健康（女）','时尚健康（女）','F','2010-11-30 13:42:27',NULL,20.00,NULL,NULL,NULL,'001330','admin',1,11),
  (129,'时尚先生','时尚先生','F','2010-11-30 13:42:27',NULL,20.00,NULL,NULL,NULL,'001331','admin',1,11),
  (130,'哈佛商业评论','哈佛商业评论','F','2010-11-30 13:42:27',NULL,70.00,NULL,NULL,NULL,'001332','admin',1,11),
  (131,'网球','网球','F','2010-11-30 13:42:27',NULL,20.00,NULL,NULL,NULL,'001333','admin',1,11),
  (132,'时尚好管家','时尚好管家','F','2010-11-30 13:42:27',NULL,20.00,NULL,NULL,NULL,'001334','admin',1,11),
  (133,'女友/增刊(花园）','女友/增刊(花园）','F','2010-11-30 13:42:27',NULL,15.00,NULL,NULL,NULL,'001341','admin',1,11),
  (134,'幸福','幸福','F','2010-11-30 13:42:27',NULL,5.00,NULL,NULL,NULL,'001343','admin',1,11),
  (135,'健康世界','健康世界','F','2010-11-30 13:42:27',NULL,10.00,NULL,NULL,NULL,'001346','admin',1,11),
  (136,'新版地图/科技版','新版地图/科技版','F','2010-11-30 13:42:27',NULL,6.00,NULL,NULL,NULL,'001352','admin',1,11),
  (137,'软件与光盘','软件与光盘','F','2010-11-30 13:42:27',NULL,10.00,NULL,NULL,NULL,'001354','admin',1,11),
  (138,'家庭','家庭','F','2010-11-30 13:42:27',NULL,3.80,NULL,NULL,NULL,'001364','admin',1,11),
  (139,'音乐时空channel','音乐时空channel','F','2010-11-30 13:42:27',NULL,12.00,NULL,NULL,NULL,'001365','admin',1,11),
  (140,'国际商业技术','国际商业技术','F','2010-11-30 13:42:27',NULL,15.00,NULL,NULL,NULL,'001366','admin',1,11),
  (141,'家家乐','家家乐','F','2010-11-30 13:42:27',NULL,4.30,NULL,NULL,NULL,'001367','admin',1,11),
  (142,'体育时空','体育时空','F','2010-11-30 13:42:27',NULL,10.00,NULL,NULL,NULL,'001368','admin',1,11),
  (143,'格调','格调','F','2010-11-30 13:42:27',NULL,16.00,NULL,NULL,NULL,'001370','admin',1,11),
  (144,'时尚座驾','时尚座驾','F','2010-11-30 13:42:27',NULL,20.00,NULL,NULL,NULL,'001377','admin',1,11),
  (145,'时尚钟表','时尚钟表','F','2010-11-30 13:42:27',NULL,25.00,NULL,NULL,NULL,'001380','admin',1,11),
  (146,'上海楼市','上海楼市','F','2010-11-30 13:42:27',NULL,5.00,NULL,NULL,NULL,'001383','admin',1,11),
  (147,'少女服饰(下）','少女服饰(下）','F','2010-11-30 13:42:27',NULL,15.00,NULL,NULL,NULL,'001394','admin',1,11),
  (148,'汽车驾驶员','汽车驾驶员','F','2010-11-30 13:42:27',NULL,10.00,NULL,NULL,NULL,'001396','admin',1,11),
  (149,'精品家居','精品家居','F','2010-11-30 13:42:27',NULL,20.00,NULL,NULL,NULL,'001398','admin',1,11),
  (150,'达人志','达人志','F','2010-11-30 13:42:27',NULL,20.00,NULL,NULL,NULL,'001401','admin',1,11),
  (151,'家居廊','家居廊','F','2010-11-30 13:42:27',NULL,20.00,NULL,NULL,NULL,'001403','admin',1,11),
  (152,'南方人物周刊','南方人物周刊','F','2010-11-30 13:42:27',NULL,8.00,NULL,NULL,NULL,'001404','admin',1,11),
  (153,'新世代(特别文摘)','新世代(特别文摘)','F','2010-11-30 13:42:27',NULL,5.00,NULL,NULL,NULL,'001407','admin',1,11),
  (154,'中国财富','中国财富','F','2010-11-30 13:42:28',NULL,10.00,NULL,NULL,NULL,'001408','admin',1,11),
  (155,'出色','出色','F','2010-11-30 13:42:28',NULL,20.00,NULL,NULL,NULL,'001409','admin',1,11),
  (156,'时间观念/珠宝之星','时间观念/珠宝之星','F','2010-11-30 13:42:28',NULL,25.00,NULL,NULL,NULL,'001410','admin',1,11),
  (157,'车谈/汽车生活','车谈/汽车生活','F','2010-11-30 13:42:28',NULL,20.00,NULL,NULL,NULL,'001411','admin',1,11),
  (158,'25安（炫色）','25安（炫色）','F','2010-11-30 13:42:28',NULL,20.00,NULL,NULL,NULL,'001413','admin',1,11),
  (159,'国际家居','国际家居','F','2010-11-30 13:42:28',NULL,30.00,NULL,NULL,NULL,'001414','admin',1,11),
  (160,'中国新时代','中国新时代','F','2010-11-30 13:42:28',NULL,20.00,NULL,NULL,NULL,'001416','admin',1,11),
  (161,'南风','南风','F','2010-11-30 13:42:28',NULL,7.00,NULL,NULL,NULL,'001417','admin',1,11),
  (162,'瑞丽服饰美容','瑞丽服饰美容','F','2010-11-30 13:42:28',NULL,20.00,NULL,NULL,NULL,'001420','admin',1,11),
  (163,'瑞丽家居','瑞丽家居','F','2010-11-30 13:42:28',NULL,20.00,NULL,NULL,NULL,'001421','admin',1,11),
  (164,'瑞丽伊人风尚','瑞丽伊人风尚','F','2010-11-30 13:42:28',NULL,20.00,NULL,NULL,NULL,'001422','admin',1,11),
  (165,'新世纪','新世纪','F','2010-11-30 13:42:28',NULL,10.00,NULL,NULL,NULL,'001423','admin',1,11),
  (166,'瑞丽时尚先锋','瑞丽时尚先锋','F','2010-11-30 13:42:28',NULL,20.00,NULL,NULL,NULL,'001425','admin',1,11),
  (167,'装修情报','装修情报','F','2010-11-30 13:42:28',NULL,10.00,NULL,NULL,NULL,'001426','admin',1,11),
  (168,'都市主妇','都市主妇','F','2010-11-30 13:42:28',NULL,20.00,NULL,NULL,NULL,'001428','admin',1,11),
  (169,'米娜','米娜','F','2010-11-30 13:42:28',NULL,18.00,NULL,NULL,NULL,'001429','admin',1,11),
  (170,'大众(皆喜)','大众(皆喜)','F','2010-11-30 13:42:28',NULL,10.00,NULL,NULL,NULL,'001430','admin',1,11),
  (171,'父母','父母','F','2010-11-30 13:42:28',NULL,15.00,NULL,NULL,NULL,'001439','admin',1,11),
  (172,'微电脑世界','微电脑世界','F','2010-11-30 13:42:28',NULL,12.00,NULL,NULL,NULL,'001446','admin',1,11),
  (173,'时装','时装','F','2010-11-30 13:42:28',NULL,20.00,NULL,NULL,NULL,'001450','admin',1,11),
  (174,'西藏人文地理','西藏人文地理','F','2010-11-30 13:42:28',NULL,20.00,NULL,NULL,NULL,'001452','admin',1,11),
  (175,'新发现','新发现','F','2010-11-30 13:42:28',NULL,12.00,NULL,NULL,NULL,'001459','admin',1,11),
  (176,'新版地图','新版地图','F','2010-11-30 13:42:28',NULL,6.00,NULL,NULL,NULL,'001464','admin',1,11),
  (177,'大众文摘','大众文摘','F','2010-11-30 13:42:28',NULL,5.00,NULL,NULL,NULL,'001466','admin',1,11),
  (178,'汽车博览','汽车博览','F','2010-11-30 13:42:29',NULL,15.00,NULL,NULL,NULL,'001467','admin',1,11),
  (179,'意林','意林','F','2010-11-30 13:42:29',NULL,4.00,NULL,NULL,NULL,'001468','admin',1,11),
  (180,'VOGUE','VOGUE','F','2010-11-30 13:42:29',NULL,20.00,NULL,NULL,NULL,'001472','admin',1,11),
  (181,'娃娃画报（年轻妈妈）','娃娃画报（年轻妈妈）','F','2010-11-30 13:42:29',NULL,6.00,NULL,NULL,NULL,'001473','admin',1,11),
  (182,'旭茉/娱乐世界','旭茉/娱乐世界','F','2010-11-30 13:42:29',NULL,5.00,NULL,NULL,NULL,'001474','admin',1,11),
  (183,'风度','风度','F','2010-11-30 13:42:29',NULL,20.00,NULL,NULL,NULL,'001481','admin',1,11),
  (184,'财富圈','财富圈','F','2010-11-30 13:42:29',NULL,20.00,NULL,NULL,NULL,'001482','admin',1,11),
  (185,'名仕','名仕','F','2010-11-30 13:42:29',NULL,20.00,NULL,NULL,NULL,'001485','admin',1,11),
  (186,'美食与美酒','美食与美酒','F','2010-11-30 13:42:29',NULL,20.00,NULL,NULL,NULL,'001489','admin',1,11),
  (187,'俏丽','俏丽','F','2010-11-30 13:42:29',NULL,20.00,NULL,NULL,NULL,'001491','admin',1,11),
  (188,'购车指南','购车指南','F','2010-11-30 13:42:29',NULL,25.00,NULL,NULL,NULL,'001493','admin',1,11),
  (189,'携程自由行','携程自由行','F','2010-11-30 13:42:29',NULL,15.00,NULL,NULL,NULL,'001495','admin',1,11),
  (190,'明刊','明刊','F','2010-11-30 13:42:29',NULL,15.00,NULL,NULL,NULL,'001498','admin',1,11),
  (191,'城市漫步-英文版','城市漫步-英文版','F','2010-11-30 13:42:29',NULL,20.00,NULL,NULL,NULL,'001499','admin',1,11),
  (192,'男人装','男人装','F','2010-11-30 13:42:29',NULL,20.00,NULL,NULL,NULL,'001503','admin',1,11),
  (193,'数字家庭','数字家庭','F','2010-11-30 13:42:29',NULL,12.00,NULL,NULL,NULL,'001504','admin',1,11),
  (194,'看天下','看天下','F','2010-11-30 13:42:29',NULL,10.00,NULL,NULL,NULL,'001506','admin',1,11),
  (195,'北京青年','北京青年','F','2010-11-30 13:42:29',NULL,3.00,NULL,NULL,NULL,'001507','admin',1,11),
  (196,'品位(职场先锋)','品位(职场先锋)','F','2010-11-30 13:42:29',NULL,10.00,NULL,NULL,NULL,'001509','admin',1,11),
  (197,'美好家园','美好家园','F','2010-11-30 13:42:29',NULL,20.00,NULL,NULL,NULL,'001511','admin',1,11),
  (198,'华夏地理','华夏地理','F','2010-11-30 13:42:29',NULL,20.00,NULL,NULL,NULL,'001512','admin',1,11),
  (199,'新旅行','新旅行','F','2010-11-30 13:42:29',NULL,20.00,NULL,NULL,NULL,'001514','admin',1,11),
  (200,'新青年制造(生活潮)','新青年制造(生活潮)','F','2010-11-30 13:42:29',NULL,15.00,NULL,NULL,NULL,'001515','admin',1,11),
  (201,'别墅世界','别墅世界','F','2010-11-30 13:42:29',NULL,40.00,NULL,NULL,NULL,'001517','admin',1,11),
  (202,'心理月刊','心理月刊','F','2010-11-30 13:42:29',NULL,20.00,NULL,NULL,NULL,'001518','admin',1,11),
  (203,'流行色','流行色','F','2010-11-30 13:42:29',NULL,20.00,NULL,NULL,NULL,'001520','admin',1,11),
  (204,'风尚志','风尚志','F','2010-11-30 13:42:29',NULL,3.00,NULL,NULL,NULL,'001521','admin',1,11),
  (205,'旅游情报','旅游情报','F','2010-11-30 13:42:29',NULL,10.00,NULL,NULL,NULL,'001522','admin',1,11),
  (206,'汽车测试报告','汽车测试报告','F','2010-11-30 13:42:29',NULL,20.00,NULL,NULL,NULL,'001524','admin',1,11),
  (207,'完美孕妇','完美孕妇','F','2010-11-30 13:42:29',NULL,15.00,NULL,NULL,NULL,'001525','admin',1,11),
  (208,'三国杀-官方攻略本','三国杀-官方攻略本','F','2010-11-30 13:42:29',NULL,39.00,NULL,NULL,NULL,'001713','admin',1,11),
  (209,'财经年刊','财经年刊','F','2010-11-30 13:42:29',NULL,35.00,NULL,NULL,NULL,'001530','admin',1,11),
  (210,'百花故事','百花故事','F','2010-11-30 13:42:30',NULL,4.00,NULL,NULL,NULL,'001531','admin',1,11),
  (211,'中国商业评论(商界评论)','中国商业评论(商界评论)','F','2010-11-30 13:42:30',NULL,20.00,NULL,NULL,NULL,'001532','admin',1,11),
  (212,'商界时尚','商界时尚','F','2010-11-30 13:42:30',NULL,20.00,NULL,NULL,NULL,'001533','admin',1,11),
  (213,'人与自然','人与自然','F','2010-11-30 13:42:30',NULL,16.00,NULL,NULL,NULL,'001534','admin',1,11),
  (214,'天下美食','天下美食','F','2010-11-30 13:42:30',NULL,20.00,NULL,NULL,NULL,'001535','admin',1,11),
  (215,'私家地理','私家地理','F','2010-11-30 13:42:30',NULL,20.00,NULL,NULL,NULL,'001536','admin',1,11),
  (216,'动感驾驭','动感驾驭','F','2010-11-30 13:42:30',NULL,20.00,NULL,NULL,NULL,'001537','admin',1,11),
  (217,'购车情报','购车情报','F','2010-11-30 13:42:30',NULL,10.00,NULL,NULL,NULL,'001538','admin',1,11),
  (218,'优品','优品','F','2010-11-30 13:42:30',NULL,30.00,NULL,NULL,NULL,'001539','admin',1,11),
  (219,'新知客','新知客','F','2010-11-30 13:42:30',NULL,10.00,NULL,NULL,NULL,'001540','admin',1,11),
  (220,'体育画报','体育画报','F','2010-11-30 13:42:30',NULL,10.00,NULL,NULL,NULL,'001541','admin',1,11),
  (221,'1626产品设计','1626产品设计','F','2010-11-30 13:42:30',NULL,12.00,NULL,NULL,NULL,'001542','admin',1,11),
  (222,'SELF悦己','SELF悦己','F','2010-11-30 13:42:30',NULL,15.00,NULL,NULL,NULL,'001543','admin',1,11),
  (223,'时尚小伊人','时尚小伊人','F','2010-11-30 13:42:30',NULL,15.00,NULL,NULL,NULL,'001544','admin',1,11),
  (224,'淑媛','淑媛','F','2010-11-30 13:42:30',NULL,10.00,NULL,NULL,NULL,'001547','admin',1,11),
  (225,'钱经','钱经','F','2010-11-30 13:42:30',NULL,15.00,NULL,NULL,NULL,'001549','admin',1,11),
  (226,'人才情报','人才情报','F','2010-11-30 13:42:30',NULL,5.00,NULL,NULL,NULL,'001550','admin',1,11),
  (227,'世界时装之苑/上海增刊','世界时装之苑/上海增刊','F','2010-11-30 13:42:30',NULL,10.00,NULL,NULL,NULL,'001553','admin',1,11),
  (228,'新探索','新探索','F','2010-11-30 13:42:30',NULL,15.00,NULL,NULL,NULL,'001555','admin',1,11),
  (229,'世界','世界','F','2010-11-30 13:42:30',NULL,20.00,NULL,NULL,NULL,'001556','admin',1,11),
  (230,'金色年代','金色年代','F','2010-11-30 13:42:30',NULL,8.00,NULL,NULL,NULL,'001558','admin',1,11),
  (231,'都市心情(新)','都市心情(新)','F','2010-11-30 13:42:30',NULL,5.00,NULL,NULL,NULL,'001561','admin',1,11),
  (232,'汽车旅行','汽车旅行','F','2010-11-30 13:42:30',NULL,16.00,NULL,NULL,NULL,'001562','admin',1,11),
  (233,'歌剧','歌剧','F','2010-11-30 13:42:30',NULL,12.00,NULL,NULL,NULL,'001563','admin',1,11),
  (234,'城市文化情报','城市文化情报','F','2010-11-30 13:42:30',NULL,8.00,NULL,NULL,NULL,'001564','admin',1,11),
  (235,'聪明宝宝','聪明宝宝','F','2010-11-30 13:42:30',NULL,10.00,NULL,NULL,NULL,'001565','admin',1,11),
  (236,'网球俱乐部','网球俱乐部','F','2010-11-30 13:42:30',NULL,20.00,NULL,NULL,NULL,'001566','admin',1,11),
  (237,'新潮流','新潮流','F','2010-11-30 13:42:30',NULL,15.00,NULL,NULL,NULL,'001569','admin',1,11),
  (238,'健康时尚','健康时尚','F','2010-11-30 13:42:30',NULL,15.00,NULL,NULL,NULL,'001570','admin',1,11),
  (239,'荣宝斋','荣宝斋','F','2010-11-30 13:42:30',NULL,28.00,NULL,NULL,NULL,'001571','admin',1,11),
  (240,'心理月刊(便携本)','心理月刊(便携本)','F','2010-11-30 13:42:30',NULL,15.00,NULL,NULL,NULL,'001573','admin',1,11),
  (241,'普知','普知','F','2010-11-30 13:42:31',NULL,10.00,NULL,NULL,NULL,'001574','admin',1,11),
  (242,'芭比','芭比','F','2010-11-30 13:42:31',NULL,15.00,NULL,NULL,NULL,'001575','admin',1,11),
  (243,'终极米迷','终极米迷','F','2010-11-30 13:42:31',NULL,14.80,NULL,NULL,NULL,'001576','admin',1,11),
  (244,'杭州地图','杭州地图','F','2010-11-30 13:42:31',NULL,5.00,NULL,NULL,NULL,'001579','admin',1,11),
  (245,'心在遥远','心在遥远','F','2010-11-30 13:42:31',NULL,15.00,NULL,NULL,NULL,'001581','admin',1,11),
  (246,'37°女人','37°女人','F','2010-11-30 13:42:31',NULL,5.00,NULL,NULL,NULL,'001582','admin',1,11),
  (247,'现代艺术-8周刊','现代艺术-8周刊','F','2010-11-30 13:42:31',NULL,6.00,NULL,NULL,NULL,'001587','admin',1,11),
  (248,'星尚OK','星尚OK','F','2010-11-30 13:42:31',NULL,10.00,NULL,NULL,NULL,'001596','admin',1,11),
  (249,'第一财经周刊','第一财经周刊','F','2010-11-30 13:42:31',NULL,10.00,NULL,NULL,NULL,'001597','admin',1,11),
  (250,'小公主','小公主','F','2010-11-30 13:42:31',NULL,10.00,NULL,NULL,NULL,'001598','admin',1,11),
  (251,'玩家','玩家','F','2010-11-30 13:42:31',NULL,10.00,NULL,NULL,NULL,'001599','admin',1,11),
  (252,'音乐大观','音乐大观','F','2010-11-30 13:42:31',NULL,10.00,NULL,NULL,NULL,'001600','admin',1,11),
  (253,'小说月报','小说月报','F','2010-11-30 13:42:31',NULL,6.50,NULL,NULL,NULL,'001601','admin',1,11),
  (254,'中国收藏','中国收藏','F','2010-11-30 13:42:31',NULL,18.00,NULL,NULL,NULL,'001602','admin',1,11),
  (255,'国家历史','国家历史','F','2010-11-30 13:42:31',NULL,16.00,NULL,NULL,NULL,'001603','admin',1,11),
  (256,'SELF悦己（便携本)','SELF悦己（便携本)','F','2010-11-30 13:42:31',NULL,10.00,NULL,NULL,NULL,'001604','admin',1,11),
  (257,'超级粉丝','超级粉丝','F','2010-11-30 13:42:31',NULL,6.00,NULL,NULL,NULL,'001605','admin',1,11),
  (258,'VOGUE男士增刊','VOGUE男士增刊','F','2010-11-30 13:42:31',NULL,20.00,NULL,NULL,NULL,'001606','admin',1,11),
  (259,'大都市（男女）促销装','大都市（男女）促销装','F','2010-11-30 13:42:31',NULL,30.00,NULL,NULL,NULL,'001607','admin',1,11),
  (260,'ceci姐妹','ceci姐妹','F','2010-11-30 13:42:31',NULL,20.00,NULL,NULL,NULL,'001608','admin',1,11),
  (261,'尺码','尺码','F','2010-11-30 13:42:31',NULL,20.00,NULL,NULL,NULL,'001609','admin',1,11),
  (262,'新发现珍藏版','新发现珍藏版','F','2010-11-30 13:42:31',NULL,132.00,NULL,NULL,NULL,'001610','admin',1,11),
  (263,'人与自然2007合订本','人与自然2007合订本','F','2010-11-30 13:42:31',NULL,165.00,NULL,NULL,NULL,'001611','admin',1,11),
  (264,'伊周','伊周','F','2010-11-30 13:42:31',NULL,2.00,NULL,NULL,NULL,'001612','admin',1,11),
  (265,'型时代','型时代','F','2010-11-30 13:42:31',NULL,20.00,NULL,NULL,NULL,'001613','admin',1,11),
  (266,'卡娜','卡娜','F','2010-11-30 13:42:31',NULL,16.00,NULL,NULL,NULL,'001614','admin',1,11),
  (267,'布衣世界（家居世界）','布衣世界（家居世界）','F','2010-11-30 13:42:32',NULL,20.00,NULL,NULL,NULL,'001618','admin',1,11),
  (268,'写真地理','写真地理','F','2010-11-30 13:42:32',NULL,20.00,NULL,NULL,NULL,'001619','admin',1,11),
  (269,'家人','家人','F','2010-11-30 13:42:32',NULL,5.00,NULL,NULL,NULL,'001621','admin',1,11),
  (270,'孕味','孕味','F','2010-11-30 13:42:32',NULL,15.00,NULL,NULL,NULL,'001622','admin',1,11),
  (271,'他生活-明星时代','他生活-明星时代','F','2010-11-30 13:42:32',NULL,20.00,NULL,NULL,NULL,'001623','admin',1,11),
  (272,'恋物志','恋物志','F','2010-11-30 13:42:32',NULL,20.00,NULL,NULL,NULL,'001624','admin',1,11),
  (273,'今日人像','今日人像','F','2010-11-30 13:42:32',NULL,18.00,NULL,NULL,NULL,'001625','admin',1,11),
  (274,'环球人物','环球人物','F','2010-11-30 13:42:32',NULL,8.00,NULL,NULL,NULL,'001626','admin',1,11),
  (275,'宝贝世界','宝贝世界','F','2010-11-30 13:42:32',NULL,6.00,NULL,NULL,NULL,'001628','admin',1,11),
  (276,'格言','格言','F','2010-11-30 13:42:32',NULL,5.00,NULL,NULL,NULL,'001630','admin',1,11),
  (277,'蓝猫环球探险','蓝猫环球探险','F','2010-11-30 13:42:32',NULL,9.90,NULL,NULL,NULL,'001631','admin',1,11),
  (278,'COLOR色彩','COLOR色彩','F','2010-11-30 13:42:32',NULL,10.00,NULL,NULL,NULL,'001632','admin',1,11),
  (279,'第一家居','第一家居','F','2010-11-30 13:42:32',NULL,20.00,NULL,NULL,NULL,'001633','admin',1,11),
  (280,'小康财智','小康财智','F','2010-11-30 13:42:32',NULL,10.00,NULL,NULL,NULL,'001634','admin',1,11),
  (281,'汽车族/增刊','汽车族/增刊','F','2010-11-30 13:42:32',NULL,10.00,NULL,NULL,NULL,'001635','admin',1,11),
  (282,'优家画报','优家画报','F','2010-11-30 13:42:32',NULL,3.00,NULL,NULL,NULL,'001636','admin',1,11),
  (283,'1626产品设计特刊','1626产品设计特刊','F','2010-11-30 13:42:32',NULL,38.00,NULL,NULL,NULL,'001637','admin',1,11),
  (284,'传奇天下','传奇天下','F','2010-11-30 13:42:32',NULL,16.00,NULL,NULL,NULL,'001638','admin',1,11),
  (285,'风尚周报','风尚周报','F','2010-11-30 13:42:32',NULL,10.00,NULL,NULL,NULL,'001639','admin',1,11),
  (286,'红秀','红秀','F','2010-11-30 13:42:32',NULL,5.00,NULL,NULL,NULL,'001640','admin',1,11),
  (287,'经济生活文摘','经济生活文摘','F','2010-11-30 13:42:33',NULL,20.00,NULL,NULL,NULL,'001641','admin',1,11),
  (288,'城客','城客','F','2010-11-30 13:42:33',NULL,20.00,NULL,NULL,NULL,'001642','admin',1,11),
  (289,'芭莎珠宝','芭莎珠宝','F','2010-11-30 13:42:33',NULL,20.00,NULL,NULL,NULL,'001643','admin',1,11),
  (290,'男人风尚','男人风尚','F','2010-11-30 13:42:33',NULL,20.00,NULL,NULL,NULL,'001644','admin',1,11),
  (291,'健康女性','健康女性','F','2010-11-30 13:42:33',NULL,15.00,NULL,NULL,NULL,'001645','admin',1,11),
  (292,'汽车族全球新车300/增刊','汽车族全球新车300/增刊','F','2010-11-30 13:42:33',NULL,38.00,NULL,NULL,NULL,'001646','admin',1,11),
  (293,'女书/现代家庭增刊','女书/现代家庭增刊','F','2010-11-30 13:42:33',NULL,15.00,NULL,NULL,NULL,'001647','admin',1,11),
  (294,'读者欣赏','读者欣赏','F','2010-11-30 13:42:33',NULL,16.00,NULL,NULL,NULL,'001648','admin',1,11),
  (295,'养生大世界','养生大世界','F','2010-11-30 13:42:33',NULL,10.00,NULL,NULL,NULL,'001649','admin',1,11),
  (296,'中国周刊','中国周刊','F','2010-11-30 13:42:33',NULL,10.00,NULL,NULL,NULL,'001650','admin',1,11),
  (297,'葡萄酒','葡萄酒','F','2010-11-30 13:42:33',NULL,25.00,NULL,NULL,NULL,'001651','admin',1,11),
  (298,'09中国汽车购车指南','09中国汽车购车指南','F','2010-11-30 13:42:33',NULL,20.00,NULL,NULL,NULL,'001652','admin',1,11),
  (299,'真倩','真倩','F','2010-11-30 13:42:33',NULL,20.00,NULL,NULL,NULL,'001653','admin',1,11),
  (300,'37°女人/优格','37°女人/优格','F','2010-11-30 13:42:33',NULL,8.00,NULL,NULL,NULL,'001654','admin',1,11),
  (301,'中欧商业评论','中欧商业评论','F','2010-11-30 13:42:33',NULL,80.00,NULL,NULL,NULL,'001655','admin',1,11),
  (302,'世界电影之窗','世界电影之窗','F','2010-11-30 13:42:33',NULL,16.00,NULL,NULL,NULL,'001656','admin',1,11),
  (303,'新电脑/特刊','新电脑/特刊','F','2010-11-30 13:42:33',NULL,68.00,NULL,NULL,NULL,'001657','admin',1,11),
  (304,'数码摄影/特刊','数码摄影/特刊','F','2010-11-30 13:42:33',NULL,58.00,NULL,NULL,NULL,'001658','admin',1,11),
  (305,'NBA体育时空HOOP','NBA体育时空HOOP','F','2010-11-30 13:42:33',NULL,16.00,NULL,NULL,NULL,'001659','admin',1,11),
  (306,'上海轨道交通指南','上海轨道交通指南','F','2010-11-30 13:42:33',NULL,15.00,NULL,NULL,NULL,'001660','admin',1,11),
  (307,'完美妈咪','完美妈咪','F','2010-11-30 13:42:33',NULL,15.00,NULL,NULL,NULL,'001661','admin',1,11),
  (308,'SEARCHING 玩味','SEARCHING 玩味','F','2010-11-30 13:42:33',NULL,80.00,NULL,NULL,NULL,'001662','admin',1,11),
  (309,'壹周悦读','壹周悦读','F','2010-11-30 13:42:33',NULL,20.00,NULL,NULL,NULL,'001663','admin',1,11),
  (310,'厨易百分','厨易百分','F','2010-11-30 13:42:33',NULL,6.00,NULL,NULL,NULL,'001664','admin',1,11),
  (311,'商业价值','商业价值','F','2010-11-30 13:42:33',NULL,20.00,NULL,NULL,NULL,'001665','admin',1,11),
  (312,'瑞丽服饰美容（小刊本）','瑞丽服饰美容（小刊本）','F','2010-11-30 13:42:33',NULL,16.00,NULL,NULL,NULL,'001666','admin',1,11),
  (313,'GQ智族','GQ智族','F','2010-11-30 13:42:33',NULL,20.00,NULL,NULL,NULL,'001667','admin',1,11),
  (314,'精品购物指南','精品购物指南','F','2010-11-30 13:42:33',NULL,2.00,NULL,NULL,NULL,'001668','admin',1,11),
  (315,'祝您健康（合订本）','祝您健康（合订本）','F','2010-11-30 13:42:33',NULL,10.00,NULL,NULL,NULL,'001669','admin',1,11),
  (316,'商用车购车指南','商用车购车指南','F','2010-11-30 13:42:33',NULL,30.00,NULL,NULL,NULL,'001670','admin',1,11),
  (317,'花火','花火','F','2010-11-30 13:42:33',NULL,5.00,NULL,NULL,NULL,'001671','admin',1,11),
  (318,'新蕾','新蕾','F','2010-11-30 13:42:34',NULL,8.80,NULL,NULL,NULL,'001672','admin',1,11),
  (319,'漫画世界','漫画世界','F','2010-11-30 13:42:34',NULL,5.00,NULL,NULL,NULL,'001673','admin',1,11),
  (320,'都市丽人-美食堂','都市丽人-美食堂','F','2010-11-30 13:42:34',NULL,6.00,NULL,NULL,NULL,'001674','admin',1,11),
  (321,'日本欢迎您','日本欢迎您','F','2010-11-30 13:42:34',NULL,20.00,NULL,NULL,NULL,'001675','admin',1,11),
  (322,'文史参考','文史参考','F','2010-11-30 13:42:34',NULL,8.00,NULL,NULL,NULL,'001676','admin',1,11),
  (323,'足球周刊','足球周刊','F','2010-11-30 13:42:34',NULL,8.00,NULL,NULL,NULL,'001677','admin',1,11),
  (324,'高尔夫大师','高尔夫大师','F','2010-11-30 13:42:34',NULL,39.90,NULL,NULL,NULL,'001678','admin',1,11),
  (325,'户外','户外','F','2010-11-30 13:42:34',NULL,20.00,NULL,NULL,NULL,'001679','admin',1,11),
  (326,'东西','东西','F','2010-11-30 13:42:34',NULL,18.00,NULL,NULL,NULL,'001680','admin',1,11),
  (327,'世界奢侈品情报','世界奢侈品情报','F','2010-11-30 13:42:34',NULL,45.00,NULL,NULL,NULL,'001681','admin',1,11),
  (328,'世界名表年鉴','世界名表年鉴','F','2010-11-30 13:42:34',NULL,158.00,NULL,NULL,NULL,'001682','admin',1,11),
  (329,'宠物世界-狗迷','宠物世界-狗迷','F','2010-11-30 13:42:34',NULL,20.00,NULL,NULL,NULL,'001683','admin',1,11),
  (330,'中国改革','中国改革','F','2010-11-30 13:42:34',NULL,20.00,NULL,NULL,NULL,'001684','admin',1,11),
  (331,'上海壹周','上海壹周','F','2010-11-30 13:42:34',NULL,2.00,NULL,NULL,NULL,'001685','admin',1,11),
  (332,'博客天下','博客天下','F','2010-11-30 13:42:34',NULL,8.00,NULL,NULL,NULL,'001686','admin',1,11),
  (333,'世界腕表杂志','世界腕表杂志','F','2010-11-30 13:42:34',NULL,45.00,NULL,NULL,NULL,'001687','admin',1,11),
  (334,'SIZE潮流生活','SIZE潮流生活','F','2010-11-30 13:42:34',NULL,20.00,NULL,NULL,NULL,'001688','admin',1,11),
  (335,'富世','富世','F','2010-11-30 13:42:34',NULL,50.00,NULL,NULL,NULL,'001689','admin',1,11),
  (336,'地图/科普版','地图/科普版','F','2010-11-30 13:42:34',NULL,6.00,NULL,NULL,NULL,'001690','admin',1,11),
  (337,'名','名','F','2010-11-30 13:42:34',NULL,10.00,NULL,NULL,NULL,'001691','admin',1,11),
  (338,'泳装特刊','泳装特刊','F','2010-11-30 13:42:34',NULL,20.00,NULL,NULL,NULL,'001692','admin',1,11),
  (339,'职场','职场','F','2010-11-30 13:42:34',NULL,10.00,NULL,NULL,NULL,'001693','admin',1,11),
  (340,'商学院','商学院','F','2010-11-30 13:42:34',NULL,10.00,NULL,NULL,NULL,'001694','admin',1,11),
  (341,'世博会导览手册','世博会导览手册','F','2010-11-30 13:42:34',NULL,20.00,NULL,NULL,NULL,'001695','admin',1,11),
  (342,'艺术界','艺术界','F','2010-11-30 13:42:34',NULL,50.00,NULL,NULL,NULL,'001696','admin',1,11),
  (343,'第一财经周刊特刊','第一财经周刊特刊','F','2010-11-30 13:42:34',NULL,20.00,NULL,NULL,NULL,'001697','admin',1,11),
  (344,'中国化妆品杂志','中国化妆品杂志','F','2010-11-30 13:42:34',NULL,12.00,NULL,NULL,NULL,'001698','admin',1,11),
  (345,'上海世博城交地图','上海世博城交地图','F','2010-11-30 13:42:34',NULL,8.00,NULL,NULL,NULL,'001699','admin',1,11),
  (346,'上海世博会精彩看点解读','上海世博会精彩看点解读','F','2010-11-30 13:42:34',NULL,25.00,NULL,NULL,NULL,'001700','admin',1,11),
  (347,'世博生活宝典','世博生活宝典','F','2010-11-30 13:42:34',NULL,38.00,NULL,NULL,NULL,'001701','admin',1,11),
  (348,'婚姻与家庭(下)(包销)','婚姻与家庭(下)(包销)','F','2010-11-30 13:42:34',NULL,4.00,NULL,NULL,NULL,'011069','admin',1,11),
  (349,'妈咪宝贝','妈咪宝贝','F','2010-11-30 13:42:35',NULL,12.00,NULL,NULL,NULL,'011091','admin',1,11),
  (350,'龙漫','龙漫','F','2010-11-30 13:42:35',NULL,8.00,NULL,NULL,NULL,'011120','admin',1,11),
  (351,'快活林（浪漫）','快活林（浪漫）','F','2010-11-30 13:42:35',NULL,5.80,NULL,NULL,NULL,'011131','admin',1,11),
  (352,'大都市(女)','大都市(女)','F','2010-11-30 13:42:35',NULL,30.00,NULL,NULL,NULL,'011171','admin',1,11),
  (353,'女报情感（下）','女报情感（下）','F','2010-11-30 13:42:35',NULL,5.00,NULL,NULL,NULL,'011254','admin',1,11),
  (354,'时尚健康（男）','时尚健康（男）','F','2010-11-30 13:42:35',NULL,20.00,NULL,NULL,NULL,'011330','admin',1,11),
  (355,'乐名牌世界（临）','乐名牌世界（临）','F','2010-11-30 13:42:35',NULL,5.00,NULL,NULL,NULL,'100026','admin',1,11),
  (356,'中国国家地理（临）','中国国家地理（临）','F','2010-11-30 13:42:35',NULL,20.00,NULL,NULL,NULL,'100191','admin',1,11),
  (357,'装修情报(临)','装修情报(临)','F','2010-11-30 13:42:35',NULL,10.00,NULL,NULL,NULL,'100426','admin',1,11),
  (358,'明刊（临）','明刊（临）','F','2010-11-30 13:42:35',NULL,10.00,NULL,NULL,NULL,'100498','admin',1,11),
  (359,'普知（临）','普知（临）','F','2010-11-30 13:42:35',NULL,8.00,NULL,NULL,NULL,'100574','admin',1,11),
  (360,'城市画报','城市画报','F','2010-11-30 13:42:35',NULL,12.00,NULL,NULL,NULL,'111107','admin',1,11),
  (361,'女报时尚（中）','女报时尚（中）','F','2010-11-30 13:42:35',NULL,5.00,NULL,NULL,NULL,'111254','admin',1,11),
  (362,'时尚伊人特(新娘)','时尚伊人特(新娘)','F','2010-11-30 13:42:35',NULL,20.00,NULL,NULL,NULL,'111324','admin',1,11),
  (363,'少女服饰（上）','少女服饰（上）','F','2010-11-30 13:42:35',NULL,15.00,NULL,NULL,NULL,'111394','admin',1,11),
  (364,'汉语世界','汉语世界','F','2010-11-30 13:42:35',NULL,19.00,NULL,NULL,NULL,'001704','admin',1,11),
  (365,'北京周报','北京周报','F','2010-11-30 13:42:35',NULL,6.00,NULL,NULL,NULL,'001705','admin',1,11),
  (366,'69个梦','69个梦','F','2010-11-30 13:42:35',NULL,69.00,NULL,NULL,NULL,'001706','admin',1,11),
  (367,'最不能错过的上海小店','最不能错过的上海小店','F','2010-11-30 13:42:35',NULL,35.00,NULL,NULL,NULL,'001707','admin',1,11),
  (368,'中国企业家世博特刊','中国企业家世博特刊','F','2010-11-30 13:42:35',NULL,60.00,NULL,NULL,NULL,'001708','admin',1,11),
  (369,'三国杀标准版蜀','三国杀标准版蜀','F','2010-11-30 13:42:35',NULL,39.00,NULL,NULL,NULL,'001709','admin',1,11),
  (370,'三国杀扩充包-神话再临?风','三国杀扩充包-神话再临?风','F','2010-11-30 13:42:35',NULL,10.00,NULL,NULL,NULL,'001710','admin',1,11),
  (371,'三国杀扩充包-神话再临?火','三国杀扩充包-神话再临?火','F','2010-11-30 13:42:36',NULL,10.00,NULL,NULL,NULL,'001711','admin',1,11),
  (372,'三国杀扩充包-军争篇','三国杀扩充包-军争篇','F','2010-11-30 13:42:36',NULL,20.00,NULL,NULL,NULL,'001712','admin',1,11),
  (373,'扬州地图','扬州地图','F','2010-11-30 13:42:36',NULL,6.80,NULL,NULL,NULL,'001714','admin',1,11),
  (374,'优家画报','优家画报','F','2010-11-30 13:42:36',NULL,3.00,NULL,NULL,NULL,'881636','admin',1,11),
  (375,'世界时装之苑（世博会）','世界时装之苑（世博会）','F','2010-11-30 13:42:36',NULL,20.00,NULL,NULL,NULL,'881020','admin',1,11),
  (376,'时尚伊人（世博会）','时尚伊人（世博会）','F','2010-11-30 13:42:36',NULL,20.00,NULL,NULL,NULL,'881324','admin',1,11),
  (377,'名车志（世博会）','名车志（世博会）','F','2010-11-30 13:42:36',NULL,15.00,NULL,NULL,NULL,'881116','admin',1,11),
  (378,'健康之友（世博会）','健康之友（世博会）','F','2010-11-30 13:42:36',NULL,12.00,NULL,NULL,NULL,'881019','admin',1,11),
  (379,'中国汽车画报（世博会）','中国汽车画报（世博会）','F','2010-11-30 13:42:36',NULL,18.00,NULL,NULL,NULL,'881094','admin',1,11),
  (380,'双休日（世博会）','双休日（世博会）','F','2010-11-30 13:42:36',NULL,8.00,NULL,NULL,NULL,'881129','admin',1,11),
  (381,'三联生活周刊（世博会）','三联生活周刊（世博会）','F','2010-11-30 13:42:36',NULL,10.00,NULL,NULL,NULL,'881141','admin',1,11),
  (382,'环球企业家（世博会）','环球企业家（世博会）','F','2010-11-30 13:42:36',NULL,20.00,NULL,NULL,NULL,'881159','admin',1,11),
  (383,'昕薇（世博会）','昕薇（世博会）','F','2010-11-30 13:42:36',NULL,20.00,NULL,NULL,NULL,'881048','admin',1,11),
  (384,'新民周刊（世博会）','新民周刊（世博会）','F','2010-11-30 13:42:36',NULL,8.00,NULL,NULL,NULL,'881002','admin',1,11),
  (385,'嘉人（世博会）','嘉人（世博会）','F','2010-11-30 13:42:36',NULL,20.00,NULL,NULL,NULL,'881251','admin',1,11),
  (386,'贝太厨房（世博会）','贝太厨房（世博会）','F','2010-11-30 13:42:36',NULL,10.00,NULL,NULL,NULL,'881287','admin',1,11),
  (387,'时尚先生（世博会）','时尚先生（世博会）','F','2010-11-30 13:42:36',NULL,20.00,NULL,NULL,NULL,'881331','admin',1,11),
  (388,'时尚芭莎（世博会）','时尚芭莎（世博会）','F','2010-11-30 13:42:36',NULL,20.00,NULL,NULL,NULL,'881326','admin',1,11),
  (389,'租售情报（世博会）','租售情报（世博会）','F','2010-11-30 13:42:36',NULL,5.00,NULL,NULL,NULL,'881032','admin',1,11),
  (390,'家居廊（世博会）','家居廊（世博会）','F','2010-11-30 13:42:36',NULL,20.00,NULL,NULL,NULL,'881403','admin',1,11),
  (391,'中国企业家（世博会）','中国企业家（世博会）','F','2010-11-30 13:42:36',NULL,20.00,NULL,NULL,NULL,'881241','admin',1,11),
  (392,'时装（世博会）','时装（世博会）','F','2010-11-30 13:42:36',NULL,20.00,NULL,NULL,NULL,'881450','admin',1,11),
  (393,'新发现（世博会）','新发现（世博会）','F','2010-11-30 13:42:36',NULL,12.00,NULL,NULL,NULL,'881459','admin',1,11),
  (394,'乐名牌世界（世博会）','乐名牌世界（世博会）','F','2010-11-30 13:42:36',NULL,5.00,NULL,NULL,NULL,'881026','admin',1,11),
  (395,'看天下（世博会）','看天下（世博会）','F','2010-11-30 13:42:36',NULL,10.00,NULL,NULL,NULL,'881506','admin',1,11),
  (396,'天下美食（世博会）','天下美食（世博会）','F','2010-11-30 13:42:37',NULL,20.00,NULL,NULL,NULL,'881535','admin',1,11),
  (397,'心理月刊（世博会）','心理月刊（世博会）','F','2010-11-30 13:42:37',NULL,20.00,NULL,NULL,NULL,'881518','admin',1,11),
  (398,'旅游情报（世博会）','旅游情报（世博会）','F','2010-11-30 13:42:37',NULL,10.00,NULL,NULL,NULL,'881522','admin',1,11),
  (399,'理财周刊（世博会）','理财周刊（世博会）','F','2010-11-30 13:42:37',NULL,8.00,NULL,NULL,NULL,'881177','admin',1,11),
  (400,'动感驾驭（世博会）','动感驾驭（世博会）','F','2010-11-30 13:42:37',NULL,20.00,NULL,NULL,NULL,'881537','admin',1,11),
  (401,'装修情报（世博会）','装修情报（世博会）','F','2010-11-30 13:42:37',NULL,10.00,NULL,NULL,NULL,'881426','admin',1,11),
  (402,'IT经理世界（世博会）','IT经理世界（世博会）','F','2010-11-30 13:42:37',NULL,20.00,NULL,NULL,NULL,'881276','admin',1,11),
  (403,'财经（世博会）','财经（世博会）','F','2010-11-30 13:42:37',NULL,15.00,NULL,NULL,NULL,'881125','admin',1,11),
  (404,'健康时尚（世博会）','健康时尚（世博会）','F','2010-11-30 13:42:37',NULL,15.00,NULL,NULL,NULL,'881570','admin',1,11),
  (405,'普知（世博会）','普知（世博会）','F','2010-11-30 13:42:37',NULL,10.00,NULL,NULL,NULL,'881574','admin',1,11),
  (406,'第一财经周刊（世博会）','第一财经周刊（世博会）','F','2010-11-30 13:42:37',NULL,10.00,NULL,NULL,NULL,'881597','admin',1,11),
  (407,'星尚OK（世博会）','星尚OK（世博会）','F','2010-11-30 13:42:37',NULL,10.00,NULL,NULL,NULL,'881596','admin',1,11),
  (408,'风尚周报（世博会）','风尚周报（世博会）','F','2010-11-30 13:42:37',NULL,10.00,NULL,NULL,NULL,'881639','admin',1,11),
  (409,'新世纪（世博会）','新世纪（世博会）','F','2010-11-30 13:42:37',NULL,10.00,NULL,NULL,NULL,'881423','admin',1,11),
  (410,'中国改革（世博会）','中国改革（世博会）','F','2010-11-30 13:42:37',NULL,20.00,NULL,NULL,NULL,'881684','admin',1,11),
  (411,'伊周（世博会）','伊周（世博会）','F','2010-11-30 13:42:37',NULL,2.00,NULL,NULL,NULL,'881612','admin',1,11),
  (412,'旅行者（世博会）','旅行者（世博会）','F','2010-11-30 13:42:37',NULL,20.00,NULL,NULL,NULL,'881119','admin',1,11),
  (413,'红秀（世博会）','红秀（世博会）','F','2010-11-30 13:42:37',NULL,5.00,NULL,NULL,NULL,'881640','admin',1,11),
  (414,'今日风采（世博会）','今日风采（世博会）','F','2010-11-30 13:42:37',NULL,20.00,NULL,NULL,NULL,'881194','admin',1,11),
  (415,'高尔夫（世博会）','高尔夫（世博会）','F','2010-11-30 13:42:37',NULL,40.00,NULL,NULL,NULL,'881155','admin',1,11),
  (416,'VOGUE（世博会）','VOGUE（世博会）','F','2010-11-30 13:42:37',NULL,20.00,NULL,NULL,NULL,'881472','admin',1,11),
  (417,'SELF悦己（世博会）','SELF悦己（世博会）','F','2010-11-30 13:42:37',NULL,15.00,NULL,NULL,NULL,'881543','admin',1,11),
  (418,'GQ智族（世博会）','GQ智族（世博会）','F','2010-11-30 13:42:37',NULL,20.00,NULL,NULL,NULL,'881667','admin',1,11),
  (419,'俏丽（世博会）','俏丽（世博会）','F','2010-11-30 13:42:37',NULL,20.00,NULL,NULL,NULL,'881491','admin',1,11),
  (420,'健康女性（世博会）','健康女性（世博会）','F','2010-11-30 13:42:37',NULL,15.00,NULL,NULL,NULL,'881645','admin',1,11),
  (421,'新潮流（世博会）','新潮流（世博会）','F','2010-11-30 13:42:37',NULL,15.00,NULL,NULL,NULL,'881569','admin',1,11),
  (422,'高尔夫大师（世博会）','高尔夫大师（世博会）','F','2010-11-30 13:42:37',NULL,39.90,NULL,NULL,NULL,'881678','admin',1,11),
  (423,'他生活-明星时代（世博会）','他生活-明星时代（世博会）','F','2010-11-30 13:42:37',NULL,20.00,NULL,NULL,NULL,'881623','admin',1,11),
  (424,'艺术世界（世博会）','艺术世界（世博会）','F','2010-11-30 13:42:37',NULL,20.00,NULL,NULL,NULL,'881110','admin',1,11),
  (425,'家居廊增刊（世博会）','家居廊增刊（世博会）','F','2010-11-30 13:42:38',NULL,28.00,NULL,NULL,NULL,'001715','admin',1,11),
  (426,'Q版三国杀','Q版三国杀','F','2010-11-30 13:42:38',NULL,49.00,NULL,NULL,NULL,'001716','admin',1,11),
  (427,'我的世界杯','我的世界杯','F','2010-11-30 13:42:38',NULL,68.00,NULL,NULL,NULL,'001717','admin',1,11),
  (428,'世博周刊（日文版）','世博周刊（日文版）','F','2010-11-30 13:42:38',NULL,6.00,NULL,NULL,NULL,'001718','admin',1,11),
  (429,'世博周刊（英文版）','世博周刊（英文版）','F','2010-11-30 13:42:38',NULL,6.00,NULL,NULL,NULL,'001719','admin',1,11),
  (430,'世博周刊（中文版）','世博周刊（中文版）','F','2010-11-30 13:42:38',NULL,6.00,NULL,NULL,NULL,'001720','admin',1,11),
  (431,'三国杀扩充包-神话再临?林','三国杀扩充包-神话再临?林','F','2010-11-30 13:42:38',NULL,10.00,NULL,NULL,NULL,'001721','admin',1,11),
  (432,'风声标准版','风声标准版','F','2010-11-30 13:42:38',NULL,49.00,NULL,NULL,NULL,'001722','admin',1,11),
  (433,'风声-绝密行动扩充包','风声-绝密行动扩充包','F','2010-11-30 13:42:38',NULL,10.00,NULL,NULL,NULL,'001723','admin',1,11),
  (434,'中国品牌豪宅','中国品牌豪宅','F','2010-11-30 13:42:38',NULL,30.00,NULL,NULL,NULL,'001724','admin',1,11),
  (435,'风采美妆','风采美妆','F','2010-11-30 13:42:38',NULL,20.00,NULL,NULL,NULL,'001725','admin',1,11),
  (436,'达人志','达人志','F','2010-11-30 13:42:38',NULL,20.00,NULL,NULL,NULL,'100401','admin',1,11),
  (437,'三国杀标准版-魏','三国杀标准版-魏','F','2010-11-30 13:42:38',NULL,39.00,NULL,NULL,NULL,'001726','admin',1,11),
  (438,'可不可以不要上班','可不可以不要上班','F','2010-11-30 13:42:38',NULL,22.00,NULL,NULL,NULL,'001727','admin',1,11),
  (439,'可不可以不要上学','可不可以不要上学','F','2010-11-30 13:42:38',NULL,22.00,NULL,NULL,NULL,'001728','admin',1,11),
  (440,'山楂树之恋（电影）','山楂树之恋（电影）','F','2010-11-30 13:42:38',NULL,32.00,NULL,NULL,NULL,'001729','admin',1,11),
  (441,'鲤?荷尔蒙','鲤?荷尔蒙','F','2010-11-30 13:42:38',NULL,25.00,NULL,NULL,NULL,'001730','admin',1,11),
  (442,'裸婚','裸婚','F','2010-11-30 13:42:38',NULL,24.80,NULL,NULL,NULL,'001731','admin',1,11),
  (443,'心术','心术','F','2010-11-30 13:42:38',NULL,28.00,NULL,NULL,NULL,'001732','admin',1,11),
  (444,'蜗居','蜗居','F','2010-11-30 13:42:38',NULL,25.00,NULL,NULL,NULL,'001733','admin',1,11),
  (445,'有一种智慧叫舍得','有一种智慧叫舍得','F','2010-11-30 13:42:38',NULL,25.00,NULL,NULL,NULL,'001734','admin',1,11),
  (446,'人脉圈Ⅱ','人脉圈Ⅱ','F','2010-11-30 13:42:38',NULL,32.00,NULL,NULL,NULL,'001735','admin',1,11),
  (447,'末日密码不只玛雅预言：2012地球悬念','末日密码不只玛雅预言：2012地球悬念','F','2010-11-30 13:42:38',NULL,29.80,NULL,NULL,NULL,'001737','admin',1,11),
  (448,'女孩为何要“富”着养','女孩为何要“富”着养','F','2010-11-30 13:42:38',NULL,30.00,NULL,NULL,NULL,'001736','admin',1,11),
  (449,'男孩为何要“穷”着养','男孩为何要“穷”着养','F','2010-11-30 13:42:38',NULL,28.00,NULL,NULL,NULL,'001738','admin',1,11),
  (450,'藏地密码9','藏地密码9','F','2010-11-30 13:42:38',NULL,29.80,NULL,NULL,NULL,'001739','admin',1,11),
  (451,'周立波 诙新闻','周立波 诙新闻','F','2010-11-30 13:42:38',NULL,26.00,NULL,NULL,NULL,'001740','admin',1,11),
  (452,'山楂树之恋2','山楂树之恋2','F','2010-11-30 13:42:38',NULL,28.00,NULL,NULL,NULL,'001741','admin',1,11),
  (453,'山楂树之恋（精装）','山楂树之恋（精装）','F','2010-11-30 13:42:38',NULL,28.00,NULL,NULL,NULL,'001742','admin',1,11),
  (454,'历史是什么玩意儿4','历史是什么玩意儿4','F','2010-11-30 13:42:38',NULL,25.00,NULL,NULL,NULL,'001743','admin',1,11),
  (455,'杜拉拉3','杜拉拉3','F','2010-11-30 13:42:39',NULL,29.80,NULL,NULL,NULL,'001744','admin',1,11),
  (456,'独唱团','独唱团','F','2010-11-30 13:42:39',NULL,16.00,NULL,NULL,NULL,'001745','admin',1,11),
  (457,'每天懂一点好玩心理学','每天懂一点好玩心理学','F','2010-11-30 13:42:39',NULL,28.00,NULL,NULL,NULL,'001746','admin',1,11),
  (458,'每天懂一点色彩心理学','每天懂一点色彩心理学','F','2010-11-30 13:42:39',NULL,32.80,NULL,NULL,NULL,'001747','admin',1,11),
  (459,'名侦探柯南66','名侦探柯南66','F','2010-11-30 13:42:39',NULL,7.50,NULL,NULL,NULL,'001748','admin',1,11),
  (460,'名侦探柯南67','名侦探柯南67','F','2010-11-30 13:42:39',NULL,7.50,NULL,NULL,NULL,'001749','admin',1,11),
  (461,'名侦探柯南68','名侦探柯南68','F','2010-11-30 13:42:39',NULL,7.50,NULL,NULL,NULL,'001750','admin',1,11),
  (462,'三国杀标准版-吴','三国杀标准版-吴','F','2010-11-30 13:42:39',NULL,39.00,NULL,NULL,NULL,'001764','admin',1,11),
  (463,'死亡循环','死亡循环','F','2010-11-30 13:42:39',NULL,28.00,NULL,NULL,NULL,'001751','admin',1,11),
  (464,'郎咸平说：新帝国主义在中国II','郎咸平说：新帝国主义在中国II','F','2010-11-30 13:42:39',NULL,32.00,NULL,NULL,NULL,'001752','admin',1,11),
  (465,'富豪俱乐部','富豪俱乐部','F','2010-11-30 13:42:39',NULL,32.00,NULL,NULL,NULL,'001753','admin',1,11),
  (466,'中国大趋势大博弈','中国大趋势大博弈','F','2010-11-30 13:42:39',NULL,29.00,NULL,NULL,NULL,'001754','admin',1,11),
  (467,'历史是个什么玩意儿1','历史是个什么玩意儿1','F','2010-11-30 13:42:39',NULL,32.80,NULL,NULL,NULL,'001755','admin',1,11),
  (468,'历史是个什么玩意儿2','历史是个什么玩意儿2','F','2010-11-30 13:42:39',NULL,32.80,NULL,NULL,NULL,'001756','admin',1,11),
  (469,'历史是个什么玩意儿3','历史是个什么玩意儿3','F','2010-11-30 13:42:39',NULL,32.80,NULL,NULL,NULL,'001757','admin',1,11),
  (470,'皇帝内经使用手册2','皇帝内经使用手册2','F','2010-11-30 13:42:39',NULL,32.80,NULL,NULL,NULL,'001758','admin',1,11),
  (471,'1Q84 BOOK2(7月-9月）','1Q84 BOOK2(7月-9月）','F','2010-11-30 13:42:39',NULL,36.00,NULL,NULL,NULL,'001759','admin',1,11),
  (472,'1Q84 BOOK1（4月-6月）','1Q84 BOOK1（4月-6月）','F','2010-11-30 13:42:39',NULL,36.00,NULL,NULL,NULL,'001760','admin',1,11),
  (473,'好妈妈胜过好老师','好妈妈胜过好老师','F','2010-11-30 13:42:39',NULL,28.00,NULL,NULL,NULL,'001761','admin',1,11),
  (474,'酥油（平装）','酥油（平装）','F','2010-11-30 13:42:39',NULL,32.00,NULL,NULL,NULL,'001762','admin',1,11),
  (475,'风语','风语','F','2010-11-30 13:42:40',NULL,29.80,NULL,NULL,NULL,'001763','admin',1,11);
COMMIT;

#
# Data for the `prs_imgs` table  (LIMIT 0,500)
#

INSERT INTO `prs_imgs` (`imgid`, `createtime`, `targetid`, `url`, `targettype`, `dftimg`) VALUES 
  (60,'2010-11-10 16:21:12',5,'upload/201011悦己.jpg','goods','F'),
  (61,'2010-11-10 16:21:12',5,'upload/201012卡娜.jpg','goods','F'),
  (62,'2010-11-10 16:21:12',5,'upload/201099优家画报.jpg','goods','F'),
  (69,'2010-11-23 15:09:03',1,'upload/201011GQ智族.jpg','user','T'),
  (71,'2010-11-25 13:23:53',NULL,'upload/201011色彩.jpg','goods','F'),
  (72,'2010-11-25 14:00:01',NULL,'upload/201011米娜.jpg','goods','F'),
  (73,'2010-11-25 14:19:06',5,'upload/201011瑞丽时尚先锋.jpg','goods','F'),
  (74,'2010-11-25 14:21:44',1,'upload/201011瑞丽服饰美容.jpg','goods','F'),
  (75,'2010-11-25 14:22:11',1,'upload/201011GQ智族.jpg','goods','F'),
  (76,'2010-11-25 14:22:33',1,'upload/201011GQ智族.jpg','goods','F'),
  (77,'2010-11-25 14:22:33',1,'upload/201011米娜.jpg','goods','F'),
  (78,'2010-11-25 14:22:33',1,'upload/201011汽车杂志.jpg','goods','F'),
  (79,'2010-11-25 14:22:33',1,'upload/201011瑞丽服饰美容.jpg','goods','F'),
  (80,'2010-11-25 14:22:34',1,'upload/201011瑞丽时尚先锋.jpg','goods','F'),
  (81,'2010-11-25 14:22:34',1,'upload/201011瑞丽伊人风尚.jpg','goods','F'),
  (82,'2010-11-25 14:22:34',1,'upload/201011色彩.jpg','goods','F'),
  (83,'2010-11-25 14:22:35',1,'upload/201011昕薇.jpg','goods','F'),
  (84,'2010-11-25 14:22:35',1,'upload/201011悦己.jpg','goods','F'),
  (85,'2010-11-25 14:22:35',1,'upload/201012卡娜.jpg','goods','F'),
  (86,'2010-11-25 14:22:36',1,'upload/201099优家画报.jpg','goods','F'),
  (87,'2010-11-29 13:28:40',2,'upload/201011米娜.jpg','goods','F'),
  (88,'2010-11-29 13:54:30',3,'upload/201011汽车杂志.jpg','goods','F'),
  (89,'2010-11-29 13:54:39',4,'upload/201011色彩.jpg','goods','F'),
  (90,'2010-11-29 13:55:40',6,'upload/201011悦己.jpg','goods','F'),
  (91,'2010-11-29 16:47:36',13,'upload/201011汽车杂志.jpg','user','F'),
  (92,'2010-11-29 16:47:36',13,'upload/201011瑞丽服饰美容.jpg','user','F'),
  (93,'2010-11-29 16:47:36',13,'upload/201011瑞丽时尚先锋.jpg','user','F'),
  (94,'2010-11-29 16:47:37',13,'upload/201011瑞丽伊人风尚.jpg','user','F'),
  (95,'2010-11-29 16:47:37',13,'upload/201011色彩.jpg','user','F'),
  (96,'2010-11-29 16:47:37',13,'upload/201011昕薇.jpg','user','T'),
  (97,'2010-11-29 16:47:37',13,'upload/201011悦己.jpg','user','F'),
  (98,'2010-11-29 16:47:37',13,'upload/201012卡娜.jpg','user','F'),
  (99,'2010-11-29 16:47:38',13,'upload/201099优家画报.jpg','user','F'),
  (100,'2010-11-30 14:39:56',8,'upload/201011瑞丽伊人风尚.jpg','goods','F'),
  (102,'2010-12-13 10:37:18',1,'upload/Water lilies.jpg','user','F');
COMMIT;

#
# Data for the `prs_objectcats` table  (LIMIT 0,500)
#

INSERT INTO `prs_objectcats` (`objectid`, `categoryid`, `targetid`, `cattype`) VALUES 
  (1,11,1,'0'),
  (2,11,2,'0'),
  (3,8,3,'0'),
  (4,8,4,'0'),
  (5,8,5,'0');
COMMIT;

#
# Data for the `prs_options` table  (LIMIT 0,500)
#

INSERT INTO `prs_options` (`optionid`, `optionname`, `optionvalue`) VALUES 
  (0,'siteurl','http://localhost:8080/jyzz');
COMMIT;

#
# Data for the `prs_orderitems` table  (LIMIT 0,500)
#

INSERT INTO `prs_orderitems` (`orderitemid`, `goodsid`, `orderid`, `price`, `quantity`, `monthnum`, `createtime`) VALUES 
  (1,1,1,5.60,1,6,'2010-12-06 15:21:01'),
  (2,2,1,8.00,1,6,'2010-12-06 15:21:01'),
  (3,1,2,5.60,1,6,'2010-12-06 15:29:53'),
  (4,2,2,8.00,1,6,'2010-12-06 15:29:53'),
  (5,1,3,5.60,1,6,'2010-12-06 15:36:45'),
  (6,3,3,10.00,2,6,'2010-12-06 15:36:45'),
  (7,1,4,5.60,1,6,'2010-12-06 15:37:44'),
  (8,3,4,10.00,2,6,'2010-12-06 15:37:44'),
  (9,1,5,5.60,1,6,'2010-12-06 15:37:45'),
  (10,3,5,10.00,2,6,'2010-12-06 15:37:45'),
  (11,1,6,5.60,1,6,'2010-12-06 15:38:11'),
  (12,3,6,10.00,2,6,'2010-12-06 15:38:11'),
  (13,1,7,5.60,1,6,'2010-12-13 12:52:46'),
  (14,1,8,5.60,1,6,'2010-12-13 12:56:01'),
  (15,1,9,5.60,1,6,'2010-12-13 12:57:51'),
  (16,1,10,5.60,1,6,'2010-12-13 12:58:21'),
  (17,1,11,11.20,2,6,'2010-12-13 15:17:42'),
  (18,1,12,11.20,2,6,'2010-12-13 15:26:58'),
  (19,1,13,5.60,1,6,'2010-12-13 15:31:34'),
  (20,1,14,5.60,1,6,'2010-12-13 15:33:58'),
  (21,1,15,5.60,1,6,'2010-12-13 15:36:02'),
  (22,1,16,5.60,1,6,'2010-12-13 15:39:11'),
  (25,1,19,5.60,1,6,'2010-12-24 15:04:07'),
  (26,2,20,8.00,1,6,'2010-12-24 15:34:09'),
  (27,6,21,4.55,1,6,'2010-12-24 15:38:34');
COMMIT;

#
# Data for the `prs_orders` table  (LIMIT 0,500)
#

INSERT INTO `prs_orders` (`orderid`, `createtime`, `userid`, `username`, `ordersn`, `status`, `totalPrice`, `consigneeid`) VALUES 
  (1,'2010-12-06 15:21:01',1,'admin','2010120600152','0',81.60,0),
  (2,'2010-12-06 15:29:53',1,'admin','2010120607963','0',81.60,0),
  (3,'2010-12-06 15:36:45',1,'admin','2010120605314','0',93.60,0),
  (4,'2010-12-06 15:37:44',1,'admin','2010120600625','0',93.60,0),
  (5,'2010-12-06 15:37:45',1,'admin','2010120608286','0',93.60,0),
  (6,'2010-12-06 15:38:11',1,'admin','2010120607037','0',93.60,0),
  (7,'2010-12-13 12:52:46',13,'zhang1','201012130296null','0',33.60,0),
  (8,'2010-12-13 12:56:01',13,'zhang1','201012130937null','0',33.60,0),
  (9,'2010-12-13 12:57:51',13,'zhang1','201012130718null','0',33.60,0),
  (10,'2010-12-13 12:58:21',13,'zhang1','201012130625null','0',33.60,0),
  (11,'2010-12-13 15:17:42',13,'zhang1','20101213048474','0',67.20,12),
  (12,'2010-12-13 15:26:58',1,'admin','20101213026575','0',67.20,1),
  (13,'2010-12-13 15:31:34',13,'zhang1','20101213004676','0',33.60,12),
  (14,'2010-12-13 15:33:58',13,'zhang1','20101213048478','0',33.60,12),
  (15,'2010-12-13 15:36:02',13,'zhang1','20101213065681','0',33.60,12),
  (16,'2010-12-13 15:39:11',13,'zhang1','20101213054682','0',33.60,12),
  (19,'2010-12-24 15:04:07',1,'admin','20101224042475','0',33.60,1),
  (20,'2010-12-24 15:34:09',1,'admin','20101224051876','0',48.00,1),
  (21,'2010-12-24 15:38:34',1,'admin','20101224047177','0',27.30,1);
COMMIT;

#
# Data for the `prs_resources` table  (LIMIT 0,500)
#

INSERT INTO `prs_resources` (`resourceid`, `type`, `value`, `resourcename`) VALUES 
  (3,'URL','/resource/new','增加资源'),
  (4,'URL','/resource/save','保存资源'),
  (7,'URL','/resource/list','资源列表'),
  (8,'URL','/user/list','用户列表'),
  (9,'URL','/role/list','角色列表'),
  (11,'URL','/user/torole/*','用户-分配资源'),
  (16,'URL','/test','/test'),
  (17,'URL','/user/delete/*','删除用户'),
  (18,'URL','/category/list','分类'),
  (19,'URL','/mgz/new','增加杂志'),
  (20,'URL','category/','分类list'),
  (21,'URL','/order/check','购物车订单确认'),
  (22,'URL','/mgz/upsale/*','上架'),
  (23,'URL','/mgz/downsale/*','下架'),
  (24,'URL','/admin/admin','网站管理'),
  (25,'URL','/user/myinfo','修改我的信息'),
  (26,'URL','/user/myorder','我的订单');
COMMIT;

#
# Data for the `prs_roleresources` table  (LIMIT 0,500)
#

INSERT INTO `prs_roleresources` (`roleid`, `resourceid`) VALUES 
  (3,3),
  (3,4),
  (3,7),
  (3,8),
  (3,9),
  (3,11),
  (3,17),
  (3,18),
  (3,19),
  (3,20),
  (3,21),
  (3,22),
  (3,23),
  (3,24),
  (3,25),
  (3,26),
  (4,8),
  (5,21),
  (5,25),
  (5,26);
COMMIT;

#
# Data for the `prs_roles` table  (LIMIT 0,500)
#

INSERT INTO `prs_roles` (`roleid`, `rolename`, `remark`, `updatetime`) VALUES 
  (3,'ROLE_admin','admin','2010-10-26'),
  (4,'ROLE_test','测试角色','2010-10-28'),
  (5,'ROLE_user','普通用户','2010-11-15');
COMMIT;

#
# Data for the `prs_useroles` table  (LIMIT 0,500)
#

INSERT INTO `prs_useroles` (`userid`, `roleid`) VALUES 
  (1,3),
  (4,4),
  (13,5),
  (14,5),
  (15,5);
COMMIT;

#
# Data for the `prs_users` table  (LIMIT 0,500)
#

INSERT INTO `prs_users` (`userid`, `username`, `openid`, `email`, `createtime`, `realname`, `password`, `disable`) VALUES 
  (1,'admin',NULL,'manneting@gmail.com','2010-09-18 00:00:00','admin','21232f297a57a5a743894a0e4a801fc3','F'),
  (2,'editor',NULL,'manneting@gmail.com','2010-10-25 00:00:00','网站编辑','5aee9dbd2a188839105073571bee1b1f','F'),
  (3,'zz',NULL,'zz','2010-10-26 00:00:00','zz','25ed1bcb423b0b7200f485fc5ff71c8e','T'),
  (4,'test',NULL,'manneting@163.com','2010-10-28 00:00:00','test','098f6bcd4621d373cade4e832627b4f6','F'),
  (13,'zhang1',NULL,'zhang1@163.com','2010-11-15 16:57:17','zhang1','61585ed9c0328d1fad8475be9e8ecd5b','F'),
  (14,'zhang2',NULL,'zhang2@163.com','2010-11-16 11:06:30','zhang2','035e65a6633b692c9613b422d00a355b','F'),
  (15,'li',NULL,'li@163.com','2010-12-08 13:42:53','li','e10adc3949ba59abbe56e057f20f883e','F'),
  (16,'q1',NULL,'qqqqqqqqqqqqq@163.com','2010-12-08 13:52:13','qqqqqqqqqqq','96e79218965eb72c92a549dd5a330112','T'),
  (17,'qq',NULL,'11@1563.com','2010-12-08 14:03:18','qq','827ccb0eea8a706c4c34a16891f84e7b','T');
COMMIT;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;