/*
SQLyog Enterprise - MySQL GUI v8.12 
MySQL - 5.1.33-community : Database - search
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`search` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*Table structure for table `bestword` */

DROP TABLE IF EXISTS `bestword`;

CREATE TABLE `bestword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bestword` text,
  `list_weight` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

/*Table structure for table `document_not_relative` */

DROP TABLE IF EXISTS `document_not_relative`;

CREATE TABLE `document_not_relative` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(511) DEFAULT NULL,
  `content` text,
  `weight` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `document_relative` */

DROP TABLE IF EXISTS `document_relative`;

CREATE TABLE `document_relative` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(511) DEFAULT NULL,
  `content` text,
  `weight` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

/*Table structure for table `document_sample` */

DROP TABLE IF EXISTS `document_sample`;

CREATE TABLE `document_sample` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(511) DEFAULT NULL,
  `content` text,
  `weight` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `link_crawled` */

DROP TABLE IF EXISTS `link_crawled`;

CREATE TABLE `link_crawled` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(511) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

/*Table structure for table `link_queue` */

DROP TABLE IF EXISTS `link_queue`;

CREATE TABLE `link_queue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(511) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

/*Table structure for table `new_keyword` */

DROP TABLE IF EXISTS `new_keyword`;

CREATE TABLE `new_keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `new_key` varchar(100) DEFAULT NULL,
  `link` varchar(511) DEFAULT NULL,
  `weight_experiment` double DEFAULT NULL,
  `avg_fitness_experiment` double DEFAULT NULL,
  `weight_key` double DEFAULT NULL,
  `best_fitness` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

/*Table structure for table `variable_state` */

DROP TABLE IF EXISTS `variable_state`;

CREATE TABLE `variable_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(100) DEFAULT NULL,
  `num_doc_contain_key` int(11) DEFAULT NULL,
  `num_doc_crawled` int(11) DEFAULT NULL,
  `sum_length` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;