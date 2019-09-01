/*
SQLyog Enterprise v12.08 (32 bit)
MySQL - 5.5.62 : Database - ruci
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ruci` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `ruci`;

/*Table structure for table `event` */

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `item_id` int(10) NOT NULL COMMENT '原价商品id',
  `event_price` decimal(10,2) DEFAULT NULL COMMENT '活动价格',
  `start_date` timestamp NULL DEFAULT NULL COMMENT '活动开始时间',
  `end_date` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `stoct` int(10) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `event` */

insert  into `event`(`item_id`,`event_price`,`start_date`,`end_date`,`stoct`) values (1,'88.00','2019-08-27 17:29:50','2019-08-27 17:29:53',97),(2,'1.00','2019-08-27 17:30:32','2019-08-27 17:30:35',1),(3,'2000.00','2019-08-31 17:32:10','2019-09-07 17:32:05',98),(4,'1000.00','2019-08-20 17:32:18','2019-09-03 17:32:21',97),(5,'666.00','2019-08-22 15:06:47','2019-08-31 15:06:52',96),(6,'3000.00','2019-09-04 17:32:25','2019-09-07 17:32:30',99);

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `tid` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) DEFAULT NULL COMMENT '所有者id',
  `title` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '商品名称',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `descrption` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `sales` int(10) DEFAULT NULL COMMENT '库存',
  `img_url` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '图片地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `item` */

insert  into `item`(`tid`,`uid`,`title`,`price`,`descrption`,`sales`,`img_url`,`create_date`) values (1,1,'某神奇物品','888.00','不要999，只要888。',147,'/dist/img/acc.jpg','2019-08-26 08:59:01'),(2,1,'某动漫少女','1888.00','独一无二',1,'/dist/img/2974.jpg','2019-08-27 08:16:36'),(3,1,'小米9','3000.00','为发烧而生',998,'/dist/img/xiaomi9.jpg','2019-08-27 08:18:51'),(4,1,'小米8青春版','2000.00','为发烧而生',1997,'/dist/img/xiaomi8.jpg','2019-08-27 08:20:44'),(5,1,'魅族note9','3000.00','魅族手机',4996,'/dist/img/meizuno9.jpg','2019-08-27 08:22:42'),(6,1,'华为MateX','5000.00','华为手机',999,'/dist/img/hauweimaxx.jpg','2019-08-27 08:24:31');

/*Table structure for table `order_inif` */

DROP TABLE IF EXISTS `order_inif`;

CREATE TABLE `order_inif` (
  `oid` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) DEFAULT NULL COMMENT '用户id',
  `tid` int(10) DEFAULT NULL COMMENT '商品id',
  `title` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '商品名称',
  `count` int(4) DEFAULT NULL COMMENT '购买数量',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `price` decimal(10,2) unsigned DEFAULT NULL COMMENT '价格',
  `img_url` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '图片地址',
  `owner_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `order_inif` */

insert  into `order_inif`(`oid`,`uid`,`tid`,`title`,`count`,`create_date`,`price`,`img_url`,`owner_id`) values (6,1,4,'小米8青春版',1,'2019-08-28 16:55:07','1000.00','/dist/img/xiaomi8.jpg',1),(7,1,4,'小米8青春版',1,'2019-08-28 16:56:14','1000.00','/dist/img/xiaomi8.jpg',1),(8,1,1,'某神奇物品',1,'2019-08-28 16:56:48','888.00','/dist/img/acc.jpg',1),(9,1,1,'某神奇物品',1,'2019-08-28 17:01:34','888.00','/dist/img/acc.jpg',1),(10,17,1,'某神奇物品',1,'2019-08-28 17:48:04','888.00','/dist/img/acc.jpg',1),(11,1,5,'魅族note9',1,'2019-08-29 10:42:46','666.00','/dist/img/meizuno9.jpg',1),(12,1,5,'魅族note9',1,'2019-08-29 10:43:30','666.00','/dist/img/meizuno9.jpg',1),(13,1,5,'魅族note9',1,'2019-08-29 10:46:40','666.00','/dist/img/meizuno9.jpg',1),(14,1,3,'小米9',1,'2019-08-29 10:47:10','3000.00','/dist/img/xiaomi9.jpg',1),(15,1,3,'小米9',1,'2019-08-29 10:47:17','3000.00','/dist/img/xiaomi9.jpg',1),(16,1,6,'华为MateX',1,'2019-08-29 10:47:27','5000.00','/dist/img/hauweimaxx.jpg',1),(17,1,5,'魅族note9',1,'2019-08-31 08:56:57','666.00','/dist/img/meizuno9.jpg',1),(18,1,4,'小米8青春版',1,'2019-08-31 17:53:30','1000.00','/dist/img/xiaomi8.jpg',1);

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `pid` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `parent_id` int(10) DEFAULT NULL,
  `level` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `sort` int(4) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `permission` */

insert  into `permission`(`pid`,`name`,`create_date`,`description`,`parent_id`,`level`,`sort`) values (1,'系统管理','2019-08-22 16:39:28','系统管理权限',0,'0',2),(2,'商家模块','2019-08-22 16:39:37','商家模块',0,'0',3),(3,'用户模块','2019-08-29 16:11:57','用户模块',0,'0',4),(4,'用户管理','2019-08-29 16:13:50','管理可以查看所有用户',1,'0.1',1),(5,'角色管理','2019-08-29 16:14:41','为角色分配权限',1,'0.1',2),(6,'权限管理','2019-08-29 16:16:29','查看当前系统所拥有的权限',1,'0.1',3),(7,'我的商品','2019-08-29 16:16:31','查看自己的商品',2,'0.2',1),(8,'我的订单','2019-08-29 16:16:34','查看自己商品的出售订单',2,'0.2',2),(9,'浏览商品','2019-08-29 16:16:36','查看所有商品',3,'0.3',1),(10,'购买列表','2019-08-29 16:16:38','查看自己的购买记录',3,'0.3',2),(11,'主页','2019-08-31 08:26:56','首页',0,NULL,1);

/*Table structure for table `permission_role` */

DROP TABLE IF EXISTS `permission_role`;

CREATE TABLE `permission_role` (
  `rid` int(10) NOT NULL,
  `pid` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `permission_role` */

insert  into `permission_role`(`rid`,`pid`) values (1,2),(1,2);

/*Table structure for table `ri_dept` */

DROP TABLE IF EXISTS `ri_dept`;

CREATE TABLE `ri_dept` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '部门名称',
  `sort` int(4) DEFAULT NULL COMMENT '排序',
  `parent_id` int(10) DEFAULT NULL COMMENT '父级id',
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `leven` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '递归关系',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ri_dept` */

/*Table structure for table `ri_login` */

DROP TABLE IF EXISTS `ri_login`;

CREATE TABLE `ri_login` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ri_login` */

insert  into `ri_login`(`uid`,`login_name`,`password`) values (1,'admin','b7797cce01b4b131b433b6acf4add449'),(2,'one','b7797cce01b4b131b433b6acf4add449'),(4,'sadmin','e53d30b06d4d0fb2e0d0bd6412e66440'),(5,'jojo','e53d30b06d4d0fb2e0d0bd6412e66440'),(6,'jar','80350a90447bfae56e87e410c36f0280'),(7,'cc','e53d30b06d4d0fb2e0d0bd6412e66440'),(8,'aa','e53d30b06d4d0fb2e0d0bd6412e66440'),(9,'bb','e53d30b06d4d0fb2e0d0bd6412e66440'),(10,'dd','e53d30b06d4d0fb2e0d0bd6412e66440'),(11,'ee','e53d30b06d4d0fb2e0d0bd6412e66440'),(14,'keke','b7797cce01b4b131b433b6acf4add449'),(16,'oo','e53d30b06d4d0fb2e0d0bd6412e66440'),(17,'hello','b7797cce01b4b131b433b6acf4add449');

/*Table structure for table `ri_menu` */

DROP TABLE IF EXISTS `ri_menu`;

CREATE TABLE `ri_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `parent_id` int(10) DEFAULT NULL,
  `sort` int(4) DEFAULT NULL,
  `leven` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ri_menu` */

insert  into `ri_menu`(`id`,`menu_name`,`parent_id`,`sort`,`leven`,`create_date`,`update_date`) values (1,'系统管理',0,1,'0','2019-08-18 14:39:47','2019-08-18 14:40:09'),(2,'商家模块',0,2,'0','2019-08-18 14:40:41','2019-08-18 14:40:46'),(3,'用户模块',0,3,'0','2019-08-18 14:41:01','2019-08-18 14:41:05'),(4,'用户管理',1,1,'0.1','2019-08-18 16:30:09','2019-08-18 16:30:14');

/*Table structure for table `ri_role` */

DROP TABLE IF EXISTS `ri_role`;

CREATE TABLE `ri_role` (
  `rid` int(10) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `sort` int(4) DEFAULT NULL COMMENT '排序',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `description` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ri_role` */

insert  into `ri_role`(`rid`,`role_name`,`sort`,`create_date`,`update_date`,`description`) values (1,'普通角色',1,'2019-08-22 16:39:11','2019-08-30 15:48:23','这是一个基础角色'),(2,'商家',2,'2019-08-30 15:48:39','2019-08-30 15:48:54','这是商家角色'),(3,'管理员',3,'2019-08-30 15:49:09','2019-08-30 15:49:20','这是管理员');

/*Table structure for table `ri_user` */

DROP TABLE IF EXISTS `ri_user`;

CREATE TABLE `ri_user` (
  `id` int(10) NOT NULL,
  `name` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `age` int(10) DEFAULT NULL COMMENT '年龄',
  `email` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '手机',
  `gender` tinyint(1) DEFAULT NULL COMMENT '0为男，1为女，2为保密',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ri_user` */

insert  into `ri_user`(`id`,`name`,`age`,`email`,`phone`,`gender`,`create_date`,`update_date`) values (1,'超级管理员',23,'a@qq.com','132654789',1,'2019-08-20 08:23:38','2019-08-20 08:23:41'),(2,'用户1',24,'b@qq.com','1896547832',1,'2019-08-20 08:23:42','2019-08-20 08:24:10'),(4,'用户2',18,'add@gmail.com','13698547',3,'2019-08-20 10:04:51','2019-08-29 15:39:44'),(5,'新用户',18,'dd@gmail.com','14785',2,'2019-08-20 10:25:34','2019-08-29 15:40:32'),(6,'老王',18,'7@qq.com','888',1,'2019-08-20 10:27:51','2019-08-29 15:42:15'),(7,'老三',80,'add@gmail.com','17722253538',1,'2019-08-20 10:58:29','2019-08-29 15:48:02'),(8,'老二',18,'add@gmail.com','13698547',1,'2019-08-20 11:19:53','2019-08-29 15:50:12'),(9,'呵呵',18,'add@gmail.com','123',1,'2019-08-20 11:20:54','2019-08-29 15:53:15'),(10,'小李',18,'add@gmail.com','13698547',2,'2019-08-20 11:22:02','2019-08-29 16:00:09'),(11,'一页',18,'XiaQiuadd@gmail.com','13698547',3,'2019-08-20 11:22:50','2019-08-31 09:41:26'),(16,NULL,NULL,NULL,NULL,NULL,'2019-08-26 08:28:28','2019-08-26 08:28:28'),(17,NULL,NULL,NULL,NULL,NULL,'2019-08-28 17:47:31','2019-08-28 17:47:31');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `uid` int(10) NOT NULL,
  `rid` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user_role` */

insert  into `user_role`(`uid`,`rid`) values (1,1),(16,1),(17,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
