/*
SQLyog Trial v12.2.3 (32 bit)
MySQL - 5.5.56 : Database - weiweb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`weiweb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `weiweb`;

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `dict_id` varchar(32) NOT NULL COMMENT '主键',
  `dict_name` varchar(256) DEFAULT NULL COMMENT '名称',
  `dict_type` varchar(64) DEFAULT NULL COMMENT '类型',
  `dict_remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `UK_SYS_DICT_TYPE` (`dict_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典主表';

/*Data for the table `sys_dict` */

/*Table structure for table `sys_dict_detail` */

DROP TABLE IF EXISTS `sys_dict_detail`;

CREATE TABLE `sys_dict_detail` (
  `detail_id` varchar(32) NOT NULL COMMENT '主键',
  `dict_type` varchar(64) DEFAULT NULL COMMENT '数据字典类型',
  `detail_name` varchar(256) DEFAULT NULL COMMENT '名称',
  `detail_code` varchar(32) DEFAULT NULL COMMENT '代码',
  `detail_sort` varchar(32) DEFAULT NULL COMMENT '排序号',
  `detail_type` varchar(32) DEFAULT NULL COMMENT '类型',
  `detail_state` varchar(32) DEFAULT NULL COMMENT '状态',
  `detail_content` varchar(256) DEFAULT NULL COMMENT '内容',
  `detail_remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

/*Data for the table `sys_dict_detail` */

/*Table structure for table `u_permission` */

DROP TABLE IF EXISTS `u_permission`;

CREATE TABLE `u_permission` (
  `id` varchar(32) NOT NULL,
  `create_date` date DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `modify_date` date DEFAULT NULL,
  `data_level` bit(1) DEFAULT NULL,
  `icon_id` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h4wyxq11wmfkbbts0sr9pntbd` (`parent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `u_permission` */

insert  into `u_permission`(`id`,`create_date`,`code`,`modify_date`,`data_level`,`icon_id`,`level`,`name`,`sort`,`status`,`target`,`url`,`parent`) values 
('10',NULL,NULL,NULL,NULL,NULL,NULL,'用户Session踢出',NULL,NULL,NULL,'/member/changeSessionStatus.shtml',NULL),
('11',NULL,NULL,NULL,NULL,NULL,NULL,'用户激活&禁止',NULL,NULL,NULL,'/member/forbidUserById.shtml',NULL),
('12',NULL,NULL,NULL,NULL,NULL,NULL,'用户删除',NULL,NULL,NULL,'/member/deleteUserById.shtml',NULL),
('13',NULL,NULL,NULL,NULL,NULL,NULL,'权限分配',NULL,NULL,NULL,'/permission/addPermission2Role.shtml',NULL),
('14',NULL,NULL,NULL,NULL,NULL,2,'用户角色分配清空',3,NULL,NULL,'/role/clearRoleByUserIds.shtml',NULL),
('15',NULL,NULL,NULL,NULL,NULL,2,'角色分配保存',NULL,NULL,NULL,'/role/addRole2User.shtml',NULL),
('16',NULL,NULL,NULL,NULL,NULL,2,'角色列表删除',NULL,NULL,NULL,'/role/deleteRoleById.shtml',NULL),
('17',NULL,NULL,NULL,NULL,NULL,2,'角色列表添加',NULL,NULL,NULL,'/role/addRole.shtml',NULL),
('18',NULL,NULL,NULL,NULL,NULL,2,'角色列表',1,NULL,NULL,'/role/list.shtml','22'),
('19',NULL,NULL,NULL,NULL,NULL,2,'权限分配',2,NULL,NULL,'/permission/allocation.shtml','22'),
('20',NULL,NULL,NULL,NULL,NULL,2,'角色分配',3,NULL,NULL,'/role/allocation.shtml','22'),
('21',NULL,NULL,NULL,NULL,NULL,1,'用户管理',1,NULL,NULL,'/member',NULL),
('22',NULL,NULL,NULL,NULL,NULL,1,'系统管理',2,NULL,NULL,'/permission',NULL),
('23',NULL,NULL,NULL,NULL,NULL,NULL,'个人中心',1,NULL,NULL,'/user',NULL),
('24',NULL,NULL,NULL,NULL,NULL,2,'字典列表',4,NULL,NULL,'/sysdict/list.shtml','22'),
('25',NULL,NULL,NULL,NULL,NULL,2,'菜单管理',6,NULL,NULL,'/menu/list.shtml','22'),
('4',NULL,NULL,NULL,NULL,NULL,2,'权限列表',4,NULL,NULL,'/permission/list.shtml','22'),
('6',NULL,NULL,NULL,NULL,NULL,2,'权限添加',NULL,NULL,NULL,'/permission/addPermission.shtml',NULL),
('7',NULL,NULL,NULL,NULL,NULL,2,'权限删除',NULL,NULL,NULL,'/permission/deletePermissionById.shtml',NULL),
('8',NULL,NULL,NULL,NULL,NULL,2,'用户列表',1,NULL,NULL,'/member/list.shtml','21'),
('9',NULL,NULL,NULL,NULL,NULL,2,'在线用户',2,NULL,NULL,'/member/online.shtml','21');

/*Table structure for table `u_role` */

DROP TABLE IF EXISTS `u_role`;

CREATE TABLE `u_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `u_role` */

insert  into `u_role`(`id`,`name`,`type`) values 
(1,'系统管理员','888888'),
(3,'权限角色','100003'),
(4,'用户中心','100002');

/*Table structure for table `u_role_permission` */

DROP TABLE IF EXISTS `u_role_permission`;

CREATE TABLE `u_role_permission` (
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `pid` bigint(20) DEFAULT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `u_role_permission` */

insert  into `u_role_permission`(`rid`,`pid`) values 
(4,8),
(4,9),
(4,10),
(4,11),
(4,12),
(3,4),
(3,6),
(3,7),
(3,13),
(3,14),
(3,15),
(3,16),
(3,17),
(3,18),
(3,19),
(3,20),
(1,4),
(1,6),
(1,7),
(1,8),
(1,9),
(1,10),
(1,11),
(1,12),
(1,13),
(1,14),
(1,15),
(1,16),
(1,17),
(1,18),
(1,19),
(1,20),
(1,21),
(1,22),
(1,25),
(1,24);

/*Table structure for table `u_user` */

DROP TABLE IF EXISTS `u_user`;

CREATE TABLE `u_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱|登录帐号',
  `pswd` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` bigint(1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `u_user` */

insert  into `u_user`(`id`,`nickname`,`email`,`pswd`,`create_time`,`last_login_time`,`status`) values 
(1,'管理员','admin','57dd03ed397eabaeaa395eb740b770fd','2016-06-16 11:15:33','2017-08-25 09:54:09',1),
(11,'soso','8446666@qq.com','d57ffbe486910dd5b26d0167d034f9ad','2016-05-26 20:50:54','2016-06-16 11:24:35',1),
(12,'8446666','8446666','4afdc875a67a55528c224ce088be2ab8','2016-05-27 22:34:19','2016-06-15 17:03:16',0),
(15,'test','test@123.com','5ebe20cf5fc1da2a3785607ec3002fcf','2017-08-20 11:52:38','2017-08-20 11:53:00',1);

/*Table structure for table `u_user_role` */

DROP TABLE IF EXISTS `u_user_role`;

CREATE TABLE `u_user_role` (
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `u_user_role` */

insert  into `u_user_role`(`uid`,`rid`) values 
(12,4),
(11,3),
(11,4),
(1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
