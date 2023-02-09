/*
SQLyog Ultimate v10.00 Beta1
MySQL - 8.0.31 : Database - qssql
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`qssql` /*!40100 DEFAULT CHARACTER SET utf16 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `qssql`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
  `password` varchar(128) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `permiss` varchar(32) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT '"10"',
  `checka` varchar(32) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT '"a"',
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf16;

/*Data for the table `admin` */

insert  into `admin`(`id`,`name`,`password`,`phone`,`permiss`,`checka`) values (1,'admin','1','18404981127','\"10\"','\"a\"'),(2,'1','1','1','\"10\"','\"a\"'),(3,'3','3','222','\"10\"','\"a\"'),(10,'a','a','3','\"10\"','\"a\"');

/*Table structure for table `answer` */

DROP TABLE IF EXISTS `answer`;

CREATE TABLE `answer` (
  `answer_id` int NOT NULL AUTO_INCREMENT,
  `answer_name` varchar(128) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT NULL,
  `answer_password` varchar(128) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT NULL,
  `answer_phone` varchar(128) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT NULL,
  `answer_permiss` varchar(128) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT '"40"',
  `answer_checka` varchar(128) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT '"a"',
  PRIMARY KEY (`answer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf16;

/*Data for the table `answer` */

insert  into `answer`(`answer_id`,`answer_name`,`answer_password`,`answer_phone`,`answer_permiss`,`answer_checka`) values (1,'a','a','123','\"40\"','\"a\"'),(2,'a1','a1','122','\"40\"','\"a\"'),(3,'a2','a2','2','\"40\"','\"a\"'),(4,'a3','a3','3','\"40\"','\"a\"'),(5,'a4','a4','4','\"40\"','\"a\"'),(6,'a5','a5','5','\"40\"','\"a\"'),(8,'a6','a6','6','\"40\"','\"a\"'),(9,'a7','a7','7','\"40\"','\"a\"'),(10,'a8','a8','8','\"40\"','\"a\"'),(11,'a11','a11','11','\"40\"','\"a\"'),(17,'马俊杰','1','1233','\"40\"','\"a\"'),(18,'25','123','188','\"40\"','\"a\"');

/*Table structure for table `groupinfo` */

DROP TABLE IF EXISTS `groupinfo`;

CREATE TABLE `groupinfo` (
  `group_id` int DEFAULT NULL,
  `answer_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

/*Data for the table `groupinfo` */

insert  into `groupinfo`(`group_id`,`answer_id`) values (60,1),(60,2),(65,5),(60,3),(66,17),(69,18),(70,1),(71,1),(71,2),(71,3),(71,4),(71,5),(71,6);

/*Table structure for table `my_group` */

DROP TABLE IF EXISTS `my_group`;

CREATE TABLE `my_group` (
  `group_id` int NOT NULL AUTO_INCREMENT,
  `group_name` varchar(256) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT NULL,
  `group_leader` int DEFAULT NULL,
  `group_answernum` int DEFAULT '0',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf16;

/*Data for the table `my_group` */

insert  into `my_group`(`group_id`,`group_name`,`group_leader`,`group_answernum`) values (60,'u的一群',20,3),(65,'uu的群',21,1),(66,'u的群2',20,1),(69,'qun',20,1),(70,'u1的群',22,1),(71,'all',20,6);

/*Table structure for table `qn` */

DROP TABLE IF EXISTS `qn`;

CREATE TABLE `qn` (
  `qn_id` int NOT NULL AUTO_INCREMENT,
  `qn_user` int DEFAULT NULL,
  `qn_state` varchar(256) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT '"未发布"',
  `qn_name` varchar(256) DEFAULT NULL,
  `qn_del` int DEFAULT '0',
  PRIMARY KEY (`qn_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf16;

/*Data for the table `qn` */

insert  into `qn`(`qn_id`,`qn_user`,`qn_state`,`qn_name`,`qn_del`) values (5,20,'已发布','呃呃阿',0),(6,20,'已发布','哈哈',0),(7,20,'已发布','tf',0),(8,21,'已发布','uu的问卷',0),(9,20,'已发布','核算通缉',0),(10,20,'已发布','test',0),(11,20,'已发布','给250d问卷',0),(12,20,'未发布','准备删除的问卷',1),(13,22,'未发布','嘻嘻',0),(14,20,'已发布','统计测试问卷',0);

/*Table structure for table `qnchinfo` */

DROP TABLE IF EXISTS `qnchinfo`;

CREATE TABLE `qnchinfo` (
  `qn_id` int DEFAULT NULL,
  `qnchoice_id` int DEFAULT NULL,
  `qnchoice_anum` int DEFAULT NULL,
  `qnchoice_bnum` int DEFAULT NULL,
  `qnchoice_cnum` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

/*Data for the table `qnchinfo` */

insert  into `qnchinfo`(`qn_id`,`qnchoice_id`,`qnchoice_anum`,`qnchoice_bnum`,`qnchoice_cnum`) values (6,4,0,0,0),(6,2,0,0,0),(6,1,0,0,0),(6,5,0,0,0),(6,6,0,0,0),(7,7,0,0,0),(7,8,0,0,0),(8,9,0,0,0),(8,10,0,0,0),(7,11,0,0,0),(7,12,0,0,0),(7,13,0,0,0),(7,14,0,0,0),(5,15,0,0,0),(5,16,0,0,0),(9,17,0,0,0),(9,18,0,0,0),(10,19,0,0,0),(10,20,0,0,0),(11,21,0,0,0),(11,22,0,0,0),(14,23,0,0,0),(14,24,0,0,0),(14,25,0,0,0),(14,26,0,0,0),(14,27,0,0,0),(14,28,0,0,0),(14,29,0,0,0),(14,30,0,0,0),(14,31,0,0,0);

/*Table structure for table `qnchoice` */

DROP TABLE IF EXISTS `qnchoice`;

CREATE TABLE `qnchoice` (
  `qnchoice_id` int NOT NULL AUTO_INCREMENT,
  `qnchoice_word` varchar(256) DEFAULT NULL,
  `qnchoice_a` varchar(256) DEFAULT NULL,
  `qnchoice_b` varchar(256) DEFAULT NULL,
  `qnchoice_c` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`qnchoice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf16;

/*Data for the table `qnchoice` */

insert  into `qnchoice`(`qnchoice_id`,`qnchoice_word`,`qnchoice_a`,`qnchoice_b`,`qnchoice_c`) values (1,'你认识永雏塔菲吗','认识','不认识','真不熟'),(2,'tcg是什么意思','塔cg','瞳cg','呃呃'),(4,'新问题','好','我','给'),(5,'未确认犬瘟热','犬瘟热犬瘟热','去微软让q ','气味让人'),(6,'qwe','qweqwe','qweqwe','qweqweqwe'),(7,'为','请问','请问人情味','去微软去微软'),(8,'太阳是什么形状的？','长方形','三角形','圆形'),(9,'1+2=','3','4','5'),(10,'4+5','6','8','9'),(11,'东北大学建校年','1923','1924','1925'),(12,'东北大学建校年','','',''),(13,'5*7=','123','35','123'),(14,'你的班级是：','','',''),(15,'3*3=','1','2','9'),(16,'你是？','','',''),(17,'学号','','',''),(18,'完成核算','是','否','呃呃'),(19,'请填空1+1=','','',''),(20,'选什么','b','a','c'),(21,'250+250=','1','2','250'),(22,'你是那个250？','','',''),(23,'5*6=','23','30','12'),(24,'太阳有多大','','',''),(25,'3+4=','1','5','7'),(26,'你是？','','',''),(27,'选哪个呢','12','15','18'),(28,'哈哈哈','1','2','3'),(29,'11','111','111','111'),(30,'222','2222','2222','2222'),(31,'3333','333333','333333','333333');

/*Table structure for table `qngroup` */

DROP TABLE IF EXISTS `qngroup`;

CREATE TABLE `qngroup` (
  `qn_id` int DEFAULT NULL,
  `group_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

/*Data for the table `qngroup` */

insert  into `qngroup`(`qn_id`,`group_id`) values (6,60),(7,60),(8,65),(5,66),(9,66),(10,66),(11,69),(14,71);

/*Table structure for table `qnout` */

DROP TABLE IF EXISTS `qnout`;

CREATE TABLE `qnout` (
  `qn_id` int DEFAULT NULL,
  `id` int DEFAULT NULL,
  `answer_id` int DEFAULT NULL,
  `qnchoice_id` int DEFAULT NULL,
  `answerout` varchar(1024) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

/*Data for the table `qnout` */

insert  into `qnout`(`qn_id`,`id`,`answer_id`,`qnchoice_id`,`answerout`) values (7,20,2,7,'A'),(7,20,2,8,'B'),(7,20,2,11,'A'),(7,20,2,12,'1'),(7,20,2,13,'B'),(7,20,2,14,'2'),(6,20,2,4,'A'),(6,20,2,2,'A'),(6,20,2,1,'A'),(6,20,2,5,'A'),(6,20,2,6,'A'),(5,20,17,15,'C'),(5,20,17,16,'马俊杰'),(9,20,17,17,'20206729'),(9,20,17,18,'A'),(11,20,18,21,'C'),(11,20,18,22,'7'),(7,20,1,7,'A'),(7,20,1,8,'B'),(7,20,1,11,'B'),(7,20,1,12,'1923'),(7,20,1,13,'B'),(7,20,1,14,'软件2009'),(6,20,1,4,'B'),(6,20,1,2,'B'),(6,20,1,1,'B'),(6,20,1,5,'B'),(6,20,1,6,'B'),(14,20,1,23,'A'),(14,20,1,24,'212'),(14,20,1,25,'C'),(14,20,1,26,'123'),(14,20,1,27,'B'),(14,20,1,28,'B'),(14,20,1,29,'B'),(14,20,1,30,'B'),(14,20,1,31,'B'),(14,20,2,23,'A'),(14,20,2,24,'131'),(14,20,2,25,'B'),(14,20,2,26,'124'),(14,20,2,27,'B'),(14,20,2,28,'B'),(14,20,2,29,'B'),(14,20,2,30,'B'),(14,20,2,31,'B'),(14,20,3,23,'A'),(14,20,3,24,'a213'),(14,20,3,25,'B'),(14,20,3,26,'123'),(14,20,3,27,'B'),(14,20,3,28,'B'),(14,20,3,29,'B'),(14,20,3,30,'B'),(14,20,3,31,'B'),(14,20,4,23,'A'),(14,20,4,24,'333'),(14,20,4,25,'B'),(14,20,4,26,'333'),(14,20,4,27,'B'),(14,20,4,28,'A'),(14,20,4,29,'A'),(14,20,4,30,'A'),(14,20,4,31,'A'),(14,20,5,23,'A'),(14,20,5,24,'444'),(14,20,5,25,'B'),(14,20,5,26,'444'),(14,20,5,27,'A'),(14,20,5,28,'C'),(14,20,5,29,'C'),(14,20,5,30,'C'),(14,20,5,31,'C'),(14,20,6,23,'A'),(14,20,6,24,'aw1'),(14,20,6,25,'C'),(14,20,6,26,'w1'),(14,20,6,27,'C'),(14,20,6,28,'C'),(14,20,6,29,'B'),(14,20,6,30,'C'),(14,20,6,31,'C');

/*Table structure for table `qs_bank` */

DROP TABLE IF EXISTS `qs_bank`;

CREATE TABLE `qs_bank` (
  `user_id` int DEFAULT NULL,
  `qsbank_id` int NOT NULL AUTO_INCREMENT,
  `qsbank_word` varchar(256) DEFAULT NULL,
  `qsbank_a` varchar(256) DEFAULT NULL,
  `qsbank_b` varchar(256) DEFAULT NULL,
  `qsbank_c` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`qsbank_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf16;

/*Data for the table `qs_bank` */

insert  into `qs_bank`(`user_id`,`qsbank_id`,`qsbank_word`,`qsbank_a`,`qsbank_b`,`qsbank_c`) values (20,1,'4+5=','1','2','9'),(20,2,'5+5=','1','2','10'),(20,3,'6+5=','1','2','11'),(20,4,'7+5=','1','2','12'),(20,5,'8+5=','1','2','13'),(20,6,'9+5=','1','2','14'),(20,7,'10+5=','1','2','15'),(20,8,'啊伟大伟大','1','2','14'),(20,9,'10+啊我的娃5=','1','2','15');

/*Table structure for table `rent` */

DROP TABLE IF EXISTS `rent`;

CREATE TABLE `rent` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `password` varchar(128) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT NULL,
  `phone` varchar(128) DEFAULT NULL,
  `permiss` varchar(128) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT '"20"',
  `checka` varchar(128) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT '"a"',
  `usernum` int DEFAULT '0',
  `fee` double DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf16;

/*Data for the table `rent` */

insert  into `rent`(`id`,`name`,`password`,`phone`,`permiss`,`checka`,`usernum`,`fee`) values (71,'1','1','1','\"20\"','\"a\"',2,5.04),(72,'2','2','2','\"20\"','\"a\"',1,0.04);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `phone` varchar(128) DEFAULT NULL,
  `permiss` varchar(128) DEFAULT '"30"',
  `checka` varchar(128) DEFAULT '"a"',
  `leader` int DEFAULT NULL,
  `up` int DEFAULT NULL,
  `groupnum` int DEFAULT '0',
  `answernum` int DEFAULT '0',
  `qnnum` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf16;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`password`,`phone`,`permiss`,`checka`,`leader`,`up`,`groupnum`,`answernum`,`qnnum`) values (20,'u','u','1','\"30\"','\"a\"',71,11,4,11,15),(21,'uu','uu','2','\"30\"','\"a\"',72,22,1,1,1),(22,'u1','u1','1111','\"30\"','\"a\"',71,111,1,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
