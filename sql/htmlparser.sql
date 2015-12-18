/*
SQLyog v10.2 
MySQL - 5.6.24-log : Database - htmlparser
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`htmlparser` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `htmlparser`;

/*Table structure for table `t_site_category` */

DROP TABLE IF EXISTS `t_site_category`;

CREATE TABLE `t_site_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(256) NOT NULL DEFAULT '' COMMENT '类别',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='站点类型表';

/*Data for the table `t_site_category` */

insert  into `t_site_category`(`category_id`,`category_name`,`create_date`,`update_date`) values (1,'房产','2015-12-11 11:42:29','2015-12-11 11:42:29');

/*Table structure for table `t_web_record` */

DROP TABLE IF EXISTS `t_web_record`;

CREATE TABLE `t_web_record` (
  `web_record_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) NOT NULL DEFAULT '' COMMENT '新闻标题',
  `href` varchar(256) NOT NULL DEFAULT '' COMMENT '新闻链接',
  `content` varchar(1) DEFAULT '' COMMENT '新闻内容，不存储实际内容',
  `source` int(11) NOT NULL DEFAULT '1' COMMENT '来源于哪个站点',
  `img1` varchar(64) DEFAULT '' COMMENT '图片1',
  `img2` varchar(64) DEFAULT '' COMMENT '图片2',
  `img3` varchar(64) DEFAULT '' COMMENT '图片3',
  `status` int(11) DEFAULT '0' COMMENT '是否爬取过，0：没有；1：爬取',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新闻创建时间',
  `crawl_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '爬取时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '爬取时间',
  PRIMARY KEY (`web_record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;

/*Data for the table `t_web_record` */

insert  into `t_web_record`(`web_record_id`,`title`,`href`,`content`,`source`,`img1`,`img2`,`img3`,`status`,`create_date`,`crawl_date`,`update_date`) values (81,'繁华中不乏宁静 合肥优质好楼盘凸显品质生活','http://www.ahwang.cn/house/20151215/1481318.shtml','',1,'http://upload.ahwang.cn/2015/1215/thumb_150_90_1450145441992.png',NULL,NULL,1,'2015-12-15 10:10:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(82,'经开区房价逼9 “傍土豪政务任性涨','http://www.ahwang.cn/house/20151215/1481296.shtml','',1,'http://upload.ahwang.cn/2015/1215/thumb_150_90_1450144391678.png',NULL,NULL,1,'2015-12-15 09:53:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(83,'一年不到涨1655元/㎡ 滨湖房价明年破万','http://www.ahwang.cn/house/20151215/1481224.shtml','',1,'http://upload.ahwang.cn/2015/1215/1450139093330.jpg',NULL,NULL,1,'2015-12-15 09:03:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(84,'“不动产统一登记来了” 房地产税何时开征仍是谜','http://www.ahwang.cn/house/20151215/1481211.shtml','',1,'http://upload.ahwang.cn/2015/1215/thumb_150_90_1450140512117.jpg',NULL,NULL,1,'2015-12-15 08:56:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(85,'安徽省首个“国际教育园”落户庐阳 从幼儿园覆盖到高中','http://www.ahwang.cn/house/20151214/1481022.shtml','',1,'http://upload.ahwang.cn/2015/1214/thumb_150_90_1450060516495.jpg',NULL,NULL,1,'2015-12-14 11:13:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(86,'上周合肥楼市量价齐跌 但均价仍逼近9000元/㎡','http://www.ahwang.cn/house/20151214/1481019.shtml','',1,'http://upload.ahwang.cn/2015/1214/thumb_150_90_1450062381464.png',NULL,NULL,1,'2015-12-14 11:06:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(87,'包河90套房遭300人抢购 上周合肥再现“日光盘”','http://www.ahwang.cn/house/20151214/1480987.shtml','',1,'http://upload.ahwang.cn/2015/1214/thumb_150_90_1450060466250.png',NULL,NULL,1,'2015-12-14 10:34:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(88,'12月13日合肥市区住宅售120套 航空新城27套领先','http://www.ahwang.cn/house/20151214/1480971.shtml','',1,'http://upload.ahwang.cn/2015/1214/thumb_150_90_1450056870773.jpg',NULL,NULL,1,'2015-12-14 10:07:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(89,'学区房价格高?5200元/㎡起入名校走捷径省钱安心','http://www.ahwang.cn/house/20151214/1480962.shtml','',1,'http://upload.ahwang.cn/2015/1214/thumb_150_90_1450058359554.png',NULL,NULL,1,'2015-12-14 09:59:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(90,'高新房价涨幅超2000元/㎡ 刚需盘将破8','http://www.ahwang.cn/house/20151214/1480955.shtml','',1,'http://upload.ahwang.cn/2015/1214/thumb_150_90_1450055300845.jpg',NULL,NULL,1,'2015-12-14 09:53:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(91,'鼓励农民买房能否去库存 贷款执行难度较大','http://www.ahwang.cn/house/20151211/1480597.shtml','',1,'http://upload.ahwang.cn/2015/1211/thumb_150_90_1449803777679.jpg',NULL,NULL,1,'2015-12-11 11:22:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(92,'合肥经开区建成全国首个建筑产业化安置小区','http://www.ahwang.cn/house/20151211/1480583.shtml','',1,'http://upload.ahwang.cn/2015/1211/thumb_150_90_1449802135910.jpg',NULL,NULL,1,'2015-12-11 10:50:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(93,'12月首场抢地大战将上演 11日土拍4大看点不容错过','http://www.ahwang.cn/house/20151211/1480564.shtml','',1,'http://upload.ahwang.cn/2015/1211/thumb_150_90_1449800771242.jpg',NULL,NULL,1,'2015-12-11 10:27:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(94,'“独立经纪人”能把中介费降多少？','http://www.ahwang.cn/house/20151211/1480538.shtml','',1,'http://upload.ahwang.cn/2015/1211/thumb_150_90_1449799372370.jpg',NULL,NULL,1,'2015-12-11 10:02:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(95,'中介设置“霸王条款” 法院判决认定无效','http://www.ahwang.cn/house/20151211/1480521.shtml','',1,'http://upload.ahwang.cn/2015/1211/thumb_150_90_1449798283681.jpg',NULL,NULL,1,'2015-12-11 09:48:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(96,'12月10日合肥市区住宅售340套 滨湖区庐阳区超50套','http://www.ahwang.cn/house/20151211/1480498.shtml','',1,'http://upload.ahwang.cn/2015/1211/thumb_150_90_1449797387963.jpg',NULL,NULL,1,'2015-12-11 09:29:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(97,'12月9日合肥市住宅售325套 东城一品轩50套领先','http://www.ahwang.cn/house/20151210/1480250.shtml','',1,'http://upload.ahwang.cn/2015/1210/thumb_150_90_1449712547510.jpg',NULL,NULL,1,'2015-12-10 09:55:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(98,'开发商借二孩政策卖房 大户型新房供应增加','http://www.ahwang.cn/house/20151210/1480194.shtml','',1,'http://upload.ahwang.cn/2015/1210/thumb_150_90_1449710285190.jpg',NULL,NULL,1,'2015-12-10 09:24:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(99,'集体土地无偿“送人” 村委说村民无权过问','http://www.ahwang.cn/house/20151208/1479733.shtml','',1,'http://upload.ahwang.cn/2015/1208/thumb_150_90_1449543005357.jpg',NULL,NULL,1,'2015-12-08 10:50:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(100,'外媒：中国大城市再现天价地块 或引发过热风险','http://www.ahwang.cn/house/20151208/1479698.shtml','',1,'http://upload.ahwang.cn/2015/1208/thumb_150_90_1449540771240.jpg',NULL,NULL,1,'2015-12-08 10:12:00','2015-12-15 16:28:18','2015-12-15 16:28:18'),(101,'樊纲：房地产暴利已过去 散户不要自己炒股','http://www.ahwang.cn/house/20151208/1479676.shtml','',1,'http://upload.ahwang.cn/2015/1208/thumb_150_90_1449539479752.jpg',NULL,NULL,1,'2015-12-08 09:52:00','2015-12-15 16:28:19','2015-12-15 16:28:18'),(102,'房地产税收入将全部划归地方 由两税种合并而成','http://www.ahwang.cn/house/20151208/1479596.shtml','',1,'http://upload.ahwang.cn/2015/1208/thumb_150_90_1449535601375.jpg',NULL,NULL,1,'2015-12-08 08:46:00','2015-12-15 16:28:19','2015-12-15 16:28:18'),(103,'11月合肥市库存仅2.5万套 刷新5年来最低纪录！','http://www.ahwang.cn/house/20151204/1479079.shtml','',1,'http://upload.ahwang.cn/2015/1204/thumb_150_90_1449196833210.png',NULL,NULL,1,'2015-12-04 10:40:00','2015-12-15 16:28:19','2015-12-15 16:28:18'),(104,'周末合肥楼市6家开盘 推盘量缩水只有740余套','http://www.ahwang.cn/house/20151204/1479054.shtml','',1,'http://upload.ahwang.cn/2015/1204/thumb_150_90_1449195206919.jpg',NULL,NULL,1,'2015-12-04 10:15:00','2015-12-15 16:28:19','2015-12-15 16:28:18'),(105,'合肥明年房价继续涨?刚需族购房置业年底要抓紧','http://www.ahwang.cn/house/20151204/1479035.shtml','',1,'http://upload.ahwang.cn/2015/1204/thumb_150_90_1449194132275.png',NULL,NULL,1,'2015-12-04 09:55:00','2015-12-15 16:28:19','2015-12-15 16:28:18'),(106,'巢湖市2014-25号地块12月30日挂牌 为加油站用地','http://www.ahwang.cn/house/20151204/1479016.shtml','',1,'http://upload.ahwang.cn/2015/1204/thumb_150_90_1449193061255.jpg',NULL,NULL,1,'2015-12-04 09:37:00','2015-12-15 16:28:19','2015-12-15 16:28:18'),(107,'合肥市区楼市火到不行！“近万人奔赴售楼部抢房”','http://www.ahwang.cn/house/20151204/1478999.shtml','',1,'http://upload.ahwang.cn/2015/1204/thumb_150_90_1449191888546.png',NULL,NULL,1,'2015-12-04 09:18:00','2015-12-15 16:28:19','2015-12-15 16:28:18'),(108,'肥西风头正猛 3字头到6000+厮杀升级','http://www.ahwang.cn/house/20151203/1478718.shtml','',1,'http://upload.ahwang.cn/2015/1203/thumb_150_90_1449107617613.jpg',NULL,NULL,1,'2015-12-03 09:53:00','2015-12-15 16:28:19','2015-12-15 16:28:18'),(109,'12月2日合肥市区住宅售297套 包河瑶海销量大幅领先','http://www.ahwang.cn/house/20151203/1478713.shtml','',1,'http://upload.ahwang.cn/2015/1203/thumb_150_90_1449107330998.jpg',NULL,NULL,1,'2015-12-03 09:48:00','2015-12-15 16:28:19','2015-12-15 16:28:18'),(110,'合肥将加大老旧小区绿化整治 创建“花园式单位”','http://www.ahwang.cn/house/20151203/1478687.shtml','',1,'http://upload.ahwang.cn/2015/1203/thumb_150_90_1449104062906.jpg',NULL,NULL,1,'2015-12-03 09:25:00','2015-12-15 16:28:19','2015-12-15 16:28:18'),(111,'12月瑶海3宗182亩地拍卖 供应量不及去年4成','http://www.ahwang.cn/house/20151203/1478686.shtml','',1,'http://upload.ahwang.cn/2015/1203/thumb_150_90_1449105752979.jpg',NULL,NULL,1,'2015-12-03 09:22:00','2015-12-15 16:28:19','2015-12-15 16:28:18'),(112,'合肥社区商业不愁未来 投资风险小','http://www.ahwang.cn/house/20151203/1478673.shtml','',1,'http://upload.ahwang.cn/2015/1203/thumb_150_90_1449105165108.png',NULL,NULL,1,'2015-12-03 09:12:00','2015-12-15 16:28:19','2015-12-15 16:28:18');

/*Table structure for table `t_website` */

DROP TABLE IF EXISTS `t_website`;

CREATE TABLE `t_website` (
  `site_id` int(11) NOT NULL AUTO_INCREMENT,
  `site_name` varchar(256) DEFAULT '' COMMENT '站点名字',
  `site_url` varchar(256) DEFAULT '' COMMENT '站点url',
  `list_range` varchar(64) DEFAULT '' COMMENT '列表路径',
  `title_node` varchar(64) DEFAULT '' COMMENT '标题节点',
  `date_node` varchar(64) DEFAULT '' COMMENT '日期节点',
  `pic_node` varchar(64) DEFAULT '' COMMENT '图片节点',
  `web_record_content_node` varchar(256) DEFAULT '' COMMENT '正文节点',
  `site_category` varchar(64) DEFAULT '' COMMENT '站点类型',
  `site_category_id` int(11) NOT NULL COMMENT '类型id',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `nextpage` varchar(64) DEFAULT NULL,
  `type` int(11) DEFAULT '0',
  `begindate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`site_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='站点表';

/*Data for the table `t_website` */

insert  into `t_website`(`site_id`,`site_name`,`site_url`,`list_range`,`title_node`,`date_node`,`pic_node`,`web_record_content_node`,`site_category`,`site_category_id`,`create_date`,`update_date`,`nextpage`,`type`,`begindate`) values (1,'安徽网-房产','http://www.ahwang.cn/house','<div id=category class=clearfix>','div[NO]/h3/a','div[NO]/div/div[2]/div/div/div/span','div[NO]/div/div[1]/a/img','<p class=describe>;<div class=article-content fontSizeBig>','房产',1,'2015-12-11 15:56:37','2015-12-11 15:56:37','<a class=next>',0,'2015-12-15 10:10:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
