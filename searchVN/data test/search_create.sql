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
(1, 'http://dantri.com.vn/', 0);

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
(1, 'bat_dong_san', NULL, NULL, NULL),
(2, 'nha_dat', NULL, NULL, NULL),
(3, 'gia_dat', NULL, NULL, NULL),
(4, 'chung_cu', NULL, NULL, NULL),
(5, 'khu_do_thi', NULL, NULL, NULL),
(6, 'nha_lien_ke', NULL, NULL, NULL),
(7, 'dia_oc', NULL, NULL, NULL),
(8, 'mua_nha', NULL, NULL, NULL),
(9, 'ban_nha', NULL, NULL, NULL),
(10, 'biet_thu', NULL, NULL, NULL),
(11, 'can_ho', NULL, NULL, NULL),
(12, 'dat_nen', NULL, NULL, NULL),
(13, 'nha_phan_lo', NULL, NULL, NULL),
(14, 'quy_hoach', NULL, NULL, NULL),
(15, 'moi_gioi', NULL, NULL, NULL),
(16, 'nha', NULL, NULL, NULL),
(17, 'dat', NULL, NULL, NULL),
(18, 'chu_dau_tu', NULL, NULL, NULL),
(19, 'so_do', NULL, NULL, NULL),
(20, 'so_hong', NULL, NULL, NULL),
(21, 'xay_dung', NULL, NULL, NULL),
(22, 'thi_truong_bat_dong_san', NULL, NULL, NULL),
(23, 'mat_bang', NULL, NULL, NULL),
(24, 'dien_tich', NULL, NULL, NULL),
(25, 'du_an_bat_dong_san', NULL, NULL, NULL),
(26, 'den_bu', NULL, NULL, NULL),
(27, 'kien_truc', NULL, NULL, NULL),
(28, 'dat_nong_nghiep', NULL, NULL, NULL),
(29, 'dat_gian_dan', NULL, NULL, NULL),
(30, 'dat_o', NULL, NULL, NULL),
(31, 'nha_thu_nhap_thap', NULL, NULL, NULL),
(32, 'can_ho_cao_cap', NULL, NULL, NULL),
(33, 'giao_dich', NULL, NULL, NULL),
(34, 'co_so_ha_tang', NULL, NULL, NULL),
(35, 'dau_tu', NULL, NULL, NULL),
(36, 'bds', NULL, NULL, NULL),
(37, 'van_phong_cho_thue', NULL, NULL, NULL),
(38, 'thi_truong', NULL, NULL, NULL),
(39, 'khu_cong_nghiep', NULL, NULL, NULL),
(40, 'trung_tam_thuong_mai', NULL, NULL, NULL),
(41, 'von', NULL, NULL, NULL),
(42, 'can_ho_trung_binh', NULL, NULL, NULL),
(43, 'chuyen_nhuong', NULL, NULL, NULL),
(44, 'giai_phong_mat_bang', NULL, NULL, NULL),
(45, 'san', NULL, NULL, NULL),
(46, 'nha_cao_cap', NULL, NULL, NULL),
(47, 'nha_hang_sang', NULL, NULL, NULL),
(48, 'noi_thanh', NULL, NULL, NULL),
(49, 'ngoai_thanh', NULL, NULL, NULL),
(50, 'nha_cao_tang', NULL, NULL, NULL);
