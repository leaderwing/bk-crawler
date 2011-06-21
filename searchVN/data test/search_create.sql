-- phpMyAdmin SQL Dump
-- version 2.11.11
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 21, 2011 at 01:10 AM
-- Server version: 5.0.91
-- PHP Version: 5.2.14

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `search31`
--

-- --------------------------------------------------------

--
-- Table structure for table `bestword`
--

DROP TABLE IF EXISTS `bestword`;
CREATE TABLE IF NOT EXISTS `bestword` (
  `id` int(11) NOT NULL auto_increment,
  `bestword` text,
  `list_weight` text,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `bestword`
--


-- --------------------------------------------------------

--
-- Table structure for table `document_not_relative`
--

DROP TABLE IF EXISTS `document_not_relative`;
CREATE TABLE IF NOT EXISTS `document_not_relative` (
  `id` int(11) NOT NULL auto_increment,
  `link` varchar(511) default NULL,
  `content` text,
  `weight` double default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `document_not_relative`
--


-- --------------------------------------------------------

--
-- Table structure for table `document_relative`
--

DROP TABLE IF EXISTS `document_relative`;
CREATE TABLE IF NOT EXISTS `document_relative` (
  `id` int(11) NOT NULL auto_increment,
  `link` varchar(511) default NULL,
  `content` text,
  `weight` double default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `document_relative`
--


-- --------------------------------------------------------

--
-- Table structure for table `document_sample`
--

DROP TABLE IF EXISTS `document_sample`;
CREATE TABLE IF NOT EXISTS `document_sample` (
  `id` int(11) NOT NULL auto_increment,
  `link` varchar(511) default NULL,
  `content` text,
  `weight` double default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `document_sample`
--


-- --------------------------------------------------------

--
-- Table structure for table `link_crawled`
--

DROP TABLE IF EXISTS `link_crawled`;
CREATE TABLE IF NOT EXISTS `link_crawled` (
  `id` int(11) NOT NULL auto_increment,
  `link` varchar(511) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `link_crawled`
--


-- --------------------------------------------------------

--
-- Table structure for table `link_queue`
--

DROP TABLE IF EXISTS `link_queue`;
CREATE TABLE IF NOT EXISTS `link_queue` (
  `id` int(11) NOT NULL auto_increment,
  `link` varchar(511) default NULL,
  `weight` double default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `link_queue`
--

INSERT INTO `link_queue` (`id`, `link`, `weight`) VALUES
(1, 'http://vnexpress.net/', 0);

-- --------------------------------------------------------

--
-- Table structure for table `new_keyword`
--

DROP TABLE IF EXISTS `new_keyword`;
CREATE TABLE IF NOT EXISTS `new_keyword` (
  `id` int(11) NOT NULL auto_increment,
  `new_key` varchar(100) default NULL,
  `link` varchar(511) default NULL,
  `weight_experiment` double default NULL,
  `avg_fitness_experiment` double default NULL,
  `weight_key` double default NULL,
  `best_fitness` double default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `new_keyword`
--


-- --------------------------------------------------------

--
-- Table structure for table `variable_state`
--

DROP TABLE IF EXISTS `variable_state`;
CREATE TABLE IF NOT EXISTS `variable_state` (
  `id` int(11) NOT NULL auto_increment,
  `keyword` varchar(100) default NULL,
  `num_doc_contain_key` int(11) default NULL,
  `num_doc_crawled` int(11) default NULL,
  `sum_length` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=51 ;

--
-- Dumping data for table `variable_state`
--

INSERT INTO `variable_state` (`id`, `keyword`, `num_doc_contain_key`, `num_doc_crawled`, `sum_length`) VALUES
(1, 'bất_động_sản', NULL, NULL, NULL),
(2, 'nhà_đất', NULL, NULL, NULL),
(3, 'giá_đất', NULL, NULL, NULL),
(4, 'chung_cư', NULL, NULL, NULL),
(5, 'đô_thị', NULL, NULL, NULL),
(6, 'liền_kề', NULL, NULL, NULL),
(7, 'địa_ốc', NULL, NULL, NULL),
(8, 'mua_nhà', NULL, NULL, NULL),
(9, 'bán_nhà', NULL, NULL, NULL),
(10, 'biệt_thự', NULL, NULL, NULL),
(11, 'căn_hộ', NULL, NULL, NULL),
(12, 'đất_nền', NULL, NULL, NULL),
(13, 'phân_lô', NULL, NULL, NULL),
(14, 'quy_hoạch', NULL, NULL, NULL),
(15, 'môi_giới', NULL, NULL, NULL),
(16, 'nhà', 37, NULL, NULL),
(17, 'đất', NULL, NULL, NULL),
(18, 'đầu_tư', NULL, NULL, NULL),
(19, 'sổ_đỏ', NULL, NULL, NULL),
(20, 'sổ_hồng', NULL, NULL, NULL),
(21, 'xây_dựng', NULL, NULL, NULL),
(22, 'thị_trường', NULL, NULL, NULL),
(23, 'mặt_bằng', NULL, NULL, NULL),
(24, 'diện_tích', NULL, NULL, NULL),
(25, 'dự_án', NULL, NULL, NULL),
(26, 'đền_bù', NULL, NULL, NULL),
(27, 'kiến_trúc', NULL, NULL, NULL),
(28, 'nông_nghiệp', NULL, NULL, NULL),
(29, 'giãn_dân', NULL, NULL, NULL),
(30, 'đất_ở', NULL, NULL, NULL),
(31, 'thu_nhập', NULL, NULL, NULL),
(32, 'cao_cấp', NULL, NULL, NULL),
(33, 'giao_dịch', NULL, NULL, NULL),
(34, 'hạ_tầng', NULL, NULL, NULL),
(35, 'đầu_tư', NULL, NULL, NULL),
(36, 'bđs', NULL, NULL, NULL),
(37, 'văn_phòng', NULL, NULL, NULL),
(38, 'cho_thuê', NULL, NULL, NULL),
(39, 'hạng_sang', NULL, NULL, NULL),
(40, 'trung_tâm', NULL, NULL, NULL),
(41, 'vốn', NULL, NULL, NULL),
(42, 'trung_bình', NULL, NULL, NULL),
(43, 'chuyển_nhng', NULL, NULL, NULL),
(44, 'giải_phóng', NULL, NULL, NULL),
(45, 'sàn', 3, NULL, NULL),
(46, 'mở_rộng', NULL, NULL, NULL),
(47, 'sinh_thái', NULL, NULL, NULL),
(48, 'nội_thành', NULL, NULL, NULL),
(49, 'ngoại_thành', NULL, NULL, NULL),
(50, 'cao_tầng', NULL, NULL, NULL);
