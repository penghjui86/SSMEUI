/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.17-log : Database - ssm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ssm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ssm`;

/*Table structure for table `ssm_org` */

DROP TABLE IF EXISTS `ssm_org`;

CREATE TABLE `ssm_org` (
  `id` bigint(19) NOT NULL DEFAULT '0' COMMENT '主键id',
  `name` varchar(64) NOT NULL COMMENT '组织名',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `code` varchar(64) NOT NULL COMMENT '编号',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '父级主键',
  `is_leaf` tinyint(1) DEFAULT '0' COMMENT '叶子节点',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ssm_org` */

insert  into `ssm_org`(`id`,`name`,`address`,`code`,`icon`,`pid`,`is_leaf`,`seq`,`del_flag`,`update_time`,`create_time`) values 
(26,'百度','','','',NULL,1,1,0,'2017-04-21 15:32:14','2017-01-06 12:37:12'),
(27,'事业部','','','',26,1,0,0,'2017-01-06 12:38:56','2017-01-06 12:37:26'),
(28,'开发部','','','',27,0,0,0,'2017-01-06 12:37:37','2017-01-06 12:37:37'),
(29,'产品部','','','',27,0,1,1,'2017-01-06 12:37:50','2017-01-06 12:37:50'),
(30,'售后部','','','',26,0,1,0,'2017-01-06 12:38:12','2017-01-06 12:38:12'),
(31,'产品部','','','',27,0,1,1,'2017-01-06 12:41:17','2017-01-06 12:41:17'),
(32,'产品部','','','',27,0,1,1,'2017-01-06 12:42:38','2017-01-06 12:42:38'),
(33,'产品部','','','',27,0,1,1,'2017-01-06 12:45:32','2017-01-06 12:45:32'),
(34,'系统开发','','','',NULL,0,0,0,'2017-04-21 15:32:00','2017-01-06 12:55:39'),
(35,'测试','','','',NULL,0,0,1,'2017-04-21 15:33:06','2017-04-21 15:33:06');

/*Table structure for table `ssm_role` */

DROP TABLE IF EXISTS `ssm_role`;

CREATE TABLE `ssm_role` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) NOT NULL COMMENT '角色名',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序号',
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `ssm_role` */

insert  into `ssm_role`(`id`,`name`,`seq`,`description`,`status`,`del_flag`,`update_time`,`create_time`) values 
(11,'系统管理员',0,'系统管理员',0,NULL,NULL,NULL),
(14,'普通员工',1,'普通员工',0,NULL,NULL,NULL),
(16,'测试',0,'ceshi',0,NULL,NULL,NULL),
(17,'测试',0,'ceshi',0,NULL,NULL,NULL),
(18,'分站管理员',0,'分站管理员',0,NULL,NULL,NULL);

/*Table structure for table `ssm_user` */

DROP TABLE IF EXISTS `ssm_user`;

CREATE TABLE `ssm_user` (
  `id` bigint(19) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(64) NOT NULL COMMENT '登录名',
  `name` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `sex` tinyint(2) DEFAULT '0' COMMENT '性别',
  `age` tinyint(2) DEFAULT '0' COMMENT '年龄',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `user_type` tinyint(2) DEFAULT '0' COMMENT '用户类别',
  `status` tinyint(2) DEFAULT '0' COMMENT '用户状态',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

/*Data for the table `ssm_user` */

insert  into `ssm_user`(`id`,`login_name`,`name`,`password`,`sex`,`age`,`phone`,`user_type`,`status`,`del_flag`,`update_time`,`create_time`) values 
(38,'admin','系统管理员','6ff3b18929bae1973fceaf16e8746f08',0,NULL,'',0,0,0,'2017-01-09 10:52:59','2017-01-09 10:52:59'),
(42,'test','测试','986b9372eaaa6adab68b024c0be2c7e3',0,NULL,'',0,0,0,'2017-01-16 21:46:39','2017-01-16 21:34:54');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
