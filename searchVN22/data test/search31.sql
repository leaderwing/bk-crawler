-- phpMyAdmin SQL Dump
-- version 2.11.11
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 21, 2011 at 11:17 AM
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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `document_relative`
--

INSERT INTO `document_relative` (`id`, `link`, `content`, `weight`) VALUES
(1, 'http://vnexpress.net/', ' giao_diện chuẩn menu cháy_chợ vinh , hơn 100 ki_ốt bị thiêu rụi 20h tối 20/6 , ngọn lửa bốc lên từ khu hàng lưới ngư cự và thuốc_bắc rồi lan_rộng ra các cửa_hàng khác của chợ vinh . hàng trăm tiểu_thương hoảng_loạn giữa vùng khói đen bao_trùm hàng nghìn m 2 . trường sa trong ký_ức các nhà_báo học_giả quốc_tế bàn về an_ninh biển đông tân chủ_tịch hà_nội :   giảm họp , tăng_cường tiếp dân   xã_hội   cháu càng kêu đau các chú công_an càng đánh mạnh   một tuần sau bị công_an phường thủy xuân ( thành_phố huế ) đánh , bé ngô đình phát , 11 tuổi , vẫn phải nằm viện điều_trị . các vết_thương đã bớt bầm tím , nhưng việc đi_lại rất khó_khăn . > cậu_bé 11 tuổi nhập_viện sau khi trở_về từ đồn công_an / thiếu_úy đánh bé 11 tuổi nhập_viện bị đình_chỉ_công_tác cảnh_sát cơ_động truy_bắt   quái xế   bên hồ gươm hoa_hậu ngọc hân :   từ_thiện phải xuất_phát từ trái_tim   nguy_hiểm rình_rập người đi đường trên quốc_lộ 51 thế_giới máy_bay nga rơi , 44 người chết ít_nhất 44 người thiệt_mạng và 8 người bị_thương khi một phi_cơ chở khách rơi xuống_đường cao_tốc , vỡ tan ở miền bắc nga đêm qua . trung quốc được yêu_cầu minh_bạch trong tranh_chấp biển đông phi_cơ   khủng   ở triển_lãm hàng_không paris quân_đội hàn quốc xin_lỗi vì bắn nhầm máy_bay dân_sự kinh_doanh khó như mua_bán điện với nhà   đèn   không_chỉ doanh_nghiệp bán điện cho tập_đoàn điện_lực việt nam ( evn ) kêu_trời mà ngay cả những khách_hàng tiêu_thụ cũng mệt_mỏi vì tiền đã đóng mà điện không được cấp đủ . > thiếu vốn evn lại xin tăng_giá ngân_hàng vẫn   đi_đêm   với khách gửi tiền được mua_bán vàng miếng tại các điểm có giấy_phép việt nam có thêm hãng hàng_không văn_hóa nhạc pop bị phê_phán   ngày_càng rẻ_tiền   ‘ rihanna thoải_mái hát về bạo_lực tình_dục , còn lady gaga thường_xuyên diện trang_phục như trẻ sơ_sinh lên sân_khấu ’ , các nhà phê_bình ở anh lo_ngại về sự xuống_cấp của âm_nhạc đại_chúng . mai phương thúy điệu với tóc xoăn huy khánh bỏ tính đào_hoa trong phim   cột mốc 23   kim hiền , lý hải vào_vai đôi tình_nhân già thể_thao nadal dễ_dàng ngày khởi_đầu wimbledon đương_kim vô_địch wimbledon hôm_qua giành chiến_thắng đúng như dự_đoán trước michael russell xếp_hạng 90 thế_giới . chelsea sắp bổ_nhiệm hậu_bối của jose mourinho con đường trở_thành ngôi_sao làng golf của rory mcilroy cách viết chính_xác tên của hlv falko g ö tz pháp_luật khởi_tố vụ_án giang_hồ sài gòn truy_sát nhau gần 3 tuần sau khi xảy_ra vụ_án giang_hồ sài gòn truy_sát nhau làm một người chết và 4 người bị_thương tại chung_cư tôn_thất thuyết ( quận 4 ) , công_an tp hcm đã quyết_định khởi_tố vụ_án . > bắt 2 ‘ đại_ca ’ trong vụ giang_hồ sài gòn truy_sát nhau / giang_hồ sài gòn truy_sát nhau vì nợ tiền bóng_đá / giang_hồ sài gòn truy_sát nhau , một người chết xác_chết bị trùm đầu trôi dưới kênh thị nghè nam công_nhân   quấy   bé gái trong quán cà_phê chòi lần đầu_tiên tìm ra hacker ăn_cắp mật_khẩu email đời_sống giếng nhà_hàng nhiễm vi_khuẩn nước_thải gây ngộ_độc du_khách ống nước_thải sinh_hoạt ngầm của nhà_hàng tâm châu vỡ trước khi đến bể xử_lý , thẩm_thấu vào nước giếng sinh_hoạt . đây là nguyên_nhân khiến hàng trăm du_khách đến đà lạt ăn_uống tại nhà_hàng này đã bị ngộ_độc . > hàng trăm du_khách đà lạt nhập_viện do ngộ_độc thực_phẩm / tìm ra thủ_phạm gây ngộ_độc hàng trăm du_khách đà lạt đàn_ông sắm xe_hơi thể_thao khó lấy vợ 10 quy_tắc để con bạn năng vận_động văcxin mới - tin_vui cho đàn_ông bị ung_thư tuyến_tiền_liệt khoa_học làm_thịt nhân_tạo từ phân người ? với giọng_điệu đầy hài_hước và hoài_nghi , một loạt các báo đang dẫn lại tin từ trang digital trends cho_hay một nhà_khoa_học nhật đã tạo nên thịt từ chất_thải của người . những ông bố tuyệt_vời trong thế_giới động_vật loài_người tiến_hóa chậm hơn ta tưởng nguy_cơ dịch_bệnh từ buôn_bán động_vật hoang_dã vi_tính bài hát đáng ghét nhất trên youtube bị xóa_bỏ ca_khúc nhạt_nhẽo   friday   của cô bé 13 tuổi rebecca black đạt kỷ_lục hơn 3 triệu người ghét trên youtube đã bị gỡ xuống do tranh_chấp bản_quyền . > những video bị   ghét   nhất trên youtube / bài hát nhạt_nhẽo thu_hút tới 36 triệu lượt xem trên youtube / steve ballmer sẽ rời microsoft sau khi windows 8 ra_mắt   tweet   đầu_tiên của barack obama là về ngày của cha thế_giới đón_nhận đợt bùng_nổ tên_miền mới ôtô - xe_máy suzuki gsr 750 - đối_thủ yamaha fz 8 tại việt nam mẫu xe nakebiked tầm trung kế_thừa khối động_cơ mạnh_mẽ gsx - r mới được nhập về sài gòn . audi a 6 2012 sẽ trình_làng tại triển_lãm autoexpo siêu xe tụ_hội tại hà_nội lexus tung ra ls 460 touring edition bạn_đọc viết lương công_chức không đủ trả công người giúp_việc nghe một cô bạn ở cơ_quan phàn_nàn , osin tối_qua đề_đạt tăng lương , từ 2 triệu lên 2,5 triệu/tháng , tất_cả anh_chị_em đồng_loạt kêu lên : thế_thì xin nghỉ không lương trông con . lương trí_thức đúng là chưa đủ tiền trả công cho người giúp_việc . tại_sao không có môn yêu lao_động trong trường_học ? đâu là câu_hỏi và đáp_án của nền giáo_dục ? kinh_nghiệm luyện thi đại_học tâm_sự quyết_tâm đến với nhau dù mẹ anh thuê giang_hồ giết tôi khi tôi giúp anh_ấy qua khó_khăn thì mẹ anh thay_đổi thái_độ , chửi_rủa tôi thậm_tệ bằng những ngôn_từ ngoa_ngoắt nhất trên đời này . đỉnh_điểm hơn_nữa là mẹ anh thuê giang_hồ giết tôi và mua axit về để dọa tạt vào mặt tôi , nguyên_nhân chỉ vì anh đưa tiền cho tôi giữ để lo_liệu công_việc . ( quynh anh ) > người_yêu bầu 4 tháng , cha_mẹ quyết không cho cưới cực_khổ sinh con nơi đất khách khi bị cha_mẹ phản_đối em muốn quên anh nhanh như lúc anh rời xa em anh long hãy nắm vững pháp_luật để tránh bị tổn_thương về sau cười đánh_lừa muỗi có anh_chàng bị bệnh_tâm_thần . một hôm bác_sĩ điều_trị cho anh_ta đến khám và quyết_định cho anh_ta về . > tâm_thần đi chơi / đi xe_máy trong bệnh_viện tâm_thần / thích tất sợi quyết_định của sếp kéo_dài thời_gian bước chuẩn_bị gay_cấn   menu trang_chủ xã_hội thế_giới kinh_doanh văn_hóa pháp_luật thể_thao đời_sống khoa_học vi_tính ôtô - xe_máy bạn_đọc viết tâm_sự cười về đầu trang ©   copyright 1997-2011 vnexpress.net , all rights reserved contact us - thông_tin tòa_soạn ®   vnexpress giữ bản_quyền nội_dung trên website này .', NULL),
(2, 'http://vnexpress.net/gl/the-thao/2011/06/chelsea-sap-bo-nhiem-hau-boi-cua-jose-mourinho/', ' giao_diện chuẩn menu thể_thao 23 : 10     |     20/06/2011 chelsea sắp bổ_nhiệm hậu_bối của jose mourinho andre villas - boas , từng phò_tá mourinho và vừa có một mùa giải đại thành_công cùng porto , gần_như chắc_chắn sẽ trở_thành tân huấn_luyện_viên trưởng của chelsea . các bên liên_quan chưa chính_thức xác_nhận , nhưng hầu_hết các hãng thông_tấn lớn của anh – trong đó có đài_truyền_hình quốc_gia bbc - đều khẳng_định villas - boas sắp_sửa tiếp_quản chiếc ghế hlv trưởng chelsea . vị_trí này vốn đã bỏ trống từ khi ban lãnh_đạo chelsea sa_thải carlo ancelotti hồi tháng 5 . villas-boas có nhiều nét tương_đồng với mourinho . villas-boas thực_sự nổi lên ở mùa giải vừa_qua khi dẫn_dắt porto đến với chức vô_địch bồ_đào nha , giành cup quốc_gia và europa league . chủ_tịch porto , pinto da costa , từng khẳng_định không có ý_định ngăn_cản villas - boas , nhưng nhà cầm_quân 33 tuổi chỉ được ra_đi một_khi có câu_lạc_bộ nào_đó trả đủ khoản phí bồi_thường hợp_đồng trị_giá 13,2 triệu bảng . trong thông_cáo mới_đây , porto khẳng_định chưa nhận được đề_nghị chính_thức nào . nhưng chelsea tuyên_bố chỉ một_vài ngày nữa họ sẽ công_bố danh_tính tân hlv . villas-boas , có_thể nói trôi_chảy tiếng anh , từng làm phụ_tá cho mourinho ở chelsea và inter . ông cũng từng có thời_gian cộng_tác với bobby robson khi cố huyền_thoại của bóng_đá anh dẫn_dắt porto . mùa giải vừa_qua porto vô_địch quốc_gia với thành_tích không thua trận nào ( thắng 27 trong tổng_số 30 trận ) . đây mới chỉ là đội bóng thứ_hai ở bồ_đào nha bất bại trong_suốt một mùa giải , sau thời của benfica 1972-1973 . trước khi nhắm đến villas - boas , ban lãnh_đạo của chelsea từng cân_nhắc một_số cái tên như mark hughes hay guus hiddink – người từng giành cup fa cùng đội chủ sân stamford bridge trong một thời_gian ngắn cộng_tác năm 2009 . mourinho dẫn_dắt chelsea trong giai_đoạn 2004-2007 , và được xem là hlv thành_công nhất trong lịch_sử đội bóng với hai chức vô_địch ngoại_hạng anh cũng một_số danh_hiệu quốc_nội khác . doãn mạnh       các tin khác - [ trở_về ] con đường trở_thành ngôi_sao làng golf của rory mcilroy   ( 20/06 ) tài_năng bóng_đá việt nam và tháng_ngày khổ_luyện ở qatar   ( 20/06 ) granada mừng lên hạng như_thể vô_địch liga   ( 20/06 ) cách viết chính_xác tên của hlv falko g ö tz   ( 20/06 ) michael phelps thắng nội_dung không phải sở_trường   ( 20/06 ) thầy_trò falko goetz hăng_say trên sân tập   ( 20/06 ) để mua alexis sanchez , barca sắp chi 54 triệu usd   ( 20/06 ) cristiano ronaldo gây   sốt   ở thổ nhĩ kỳ   ( 20/06 ) những đường chuyền hiệu_quả nhất_loạt trận tuần qua   ( 20/06 ) chiều nay bán vé trận u 23 việt nam - u23 ảrập xêut   ( 20/06 )   mourinho sắp thay_đổi chiến_thuật để real tấn_công tốt hơn     ( 20/06 ) mỹ vào bán_kết concacaf gold cup   ( 20/06 ) inter tan mộng lôi_kéo   mourinho mới     ( 20/06 ) mcilroy vô_địch với nhiều kỷ_lục ở us open   ( 20/06 ) u23 việt nam không_thể gây bất_ngờ trước ảrập xê út   ( 20/06 )   menu trang_chủ xã_hội thế_giới kinh_doanh văn_hóa pháp_luật thể_thao bóng_đá tennis chân_dung ảnh - video đời_sống khoa_học vi_tính ôtô - xe_máy bạn_đọc viết tâm_sự cười về đầu trang ©   copyright 1997-2011 vnexpress.net , all rights reserved contact us - thông_tin tòa_soạn ®   vnexpress giữ bản_quyền nội_dung trên website này .', 6.98768087930735),
(3, 'http://vnexpress.net/gl/the-thao/2011/06/chelsea-sap-bo-nhiem-hau-boi-cua-jose-mourinho/?cboGuidPDA=0', ' giao_diện pda rss trang nhất rss ebank việc_làm     game thủ     số_hóa     ngôi_sao     0123.888.0123 ( hn ) - 0129.233.3555 ( tp.hcm ) xã_hội tuyển_sinh 2011 giáo_dục nhịp_điệu trẻ du_lịch du_học thế_giới cuộc_sống đó_đây ảnh người việt 5 châu phân_tích tư_liệu kinh_doanh ebank chứng_khoán bất_động_sản nhà đẹp doanh_nhân quốc_tế mua_sắm doanh_nghiệp viết văn_hóa hoa_hậu nghệ sỹ âm_nhạc thời_trang điện_ảnh mỹ_thuật văn_học thể_thao bóng_đá tennis chân_dung ảnh - video pháp_luật hình_sự ký_sự tư_vấn đời_sống gia_đình sức_khỏe ẩm_thực làm_đẹp cửa_sổ blog khoa_học môi_trường thiên_nhiên ảnh công_nghệ mới vi_tính sản_phẩm mới kinh_nghiệm giải_trí hỏi_đáp hacker & virus ôtô - xe_máy tư_vấn bạn_đọc viết khoa_học đời_sống pháp_luật xã_hội kinh_doanh thể_thao văn_hoá thế_giới tâm_sự rao vặt ebank việc_làm     game thủ     số_hóa     ngôi_sao     cười video tiểu_phẩm thứ_hai , 20/6/2011 , 23 : 10 gmt +7   e-mail         bản in chelsea sắp bổ_nhiệm hậu_sinh của jose mourinho andre villas - boas , từng phò_tá mourinho và vừa có một mùa giải đại thành_công cùng porto , gần_như chắc_chắn sẽ trở_thành tân huấn_luyện_viên trưởng của chelsea . các bên liên_quan chưa chính_thức xác_nhận , nhưng hầu_hết các hãng thông_tấn lớn của anh – trong đó có đài_truyền_hình quốc_gia bbc - đều khẳng_định villas - boas sắp_sửa tiếp_quản chiếc ghế hlv trưởng chelsea . vị_trí này vốn đã bỏ trống từ khi ban lãnh_đạo chelsea sa_thải carlo ancelotti hồi tháng 5 . villas-boas có nhiều nét tương_đồng với mourinho . villas-boas thực_sự nổi lên ở mùa giải vừa_qua khi dẫn_dắt porto đến với chức vô_địch bồ_đào nha , giành cup quốc_gia và europa league . chủ_tịch porto , pinto da costa , từng khẳng_định không có ý_định ngăn_cản villas - boas , nhưng nhà cầm_quân 33 tuổi chỉ được ra_đi một_khi có câu_lạc_bộ nào_đó trả đủ khoản phí bồi_thường hợp_đồng trị_giá 13,2 triệu bảng . trong thông_cáo mới_đây , porto khẳng_định chưa nhận được đề_nghị chính_thức nào . nhưng chelsea tuyên_bố chỉ một_vài ngày nữa họ sẽ công_bố danh_tính tân hlv . villas-boas , có_thể nói trôi_chảy tiếng anh , từng làm phụ_tá cho mourinho ở chelsea và inter . ông cũng từng có thời_gian cộng_tác với bobby robson khi cố huyền_thoại của bóng_đá anh dẫn_dắt porto . mùa giải vừa_qua porto vô_địch quốc_gia với thành_tích không thua trận nào ( thắng 27 trong tổng_số 30 trận ) . đây mới chỉ là đội bóng thứ_hai ở bồ_đào nha bất bại trong_suốt một mùa giải , sau thời của benfica 1972-1973 . trước khi nhắm đến villas - boas , ban lãnh_đạo của chelsea từng cân_nhắc một_số cái tên như mark hughes hay guus hiddink – người từng giành cup fa cùng đội chủ sân stamford bridge trong một thời_gian ngắn cộng_tác năm 2009 . mourinho dẫn_dắt chelsea trong giai_đoạn 2004-2007 , và được xem là hlv thành_công nhất trong lịch_sử đội bóng với hai chức vô_địch ngoại_hạng anh cũng một_số danh_hiệu quốc_nội khác . doãn mạnh thể_thao , bóng_đá , ngoại_hạng anh , chelsea , villas-boas         off telex vni viqr   tin mới argentina khởi_động tưng_bừng cho copa amerrica   ( 21/06 ) nadal dễ_dàng ngày khởi_đầu wimbledon   ( 21/06 ) venus williams khởi_đầu suôn_sẻ tại wimbledon   ( 20/06 ) con đường trở_thành ngôi_sao làng golf của rory mcilroy   ( 20/06 ) tài_năng bóng_đá việt nam và tháng_ngày khổ_luyện ở qatar   ( 20/06 ) các tin khác [ trở_về ] granada mừng lên hạng như_thể vô_địch liga     ( 20/06 ) cách viết chính_xác tên của hlv falko g ö tz   ( 20/06 ) michael phelps thắng nội_dung không phải sở_trường   ( 20/06 ) thầy_trò falko goetz hăng_say trên sân tập     ( 20/06 ) để mua alexis sanchez , barca sắp chi 54 triệu usd   ( 20/06 ) cristiano ronaldo gây   sốt   ở thổ nhĩ kỳ       ( 20/06 ) những đường chuyền hiệu_quả nhất_loạt trận tuần qua   ( 20/06 ) chiều nay bán vé trận u 23 việt nam - u23 ảrập xêut   ( 20/06 )   mourinho sắp thay_đổi chiến_thuật để real tấn_công tốt hơn     ( 20/06 ) mỹ vào bán_kết concacaf gold cup   ( 20/06 ) inter tan mộng lôi_kéo   mourinho mới     ( 20/06 ) mcilroy vô_địch với nhiều kỷ_lục ở us open   ( 20/06 ) u23 việt nam không_thể gây bất_ngờ trước ảrập xê út       ( 20/06 ) mu thờ_ơ với berbatov   ( 19/06 ) mourinho từ_chối đại_gia dầu_mỏ để ở lại real   ( 19/06 )   * dự_đoán bóng_đá châu_âu và đón phần_thưởng lớn         * lịch đấu , kết_quả ngoại_hạng anh > > bảng điểm / / kết_quả ... ... ... ... ... ... ... ... ... ... ... ... ... ... ... . . serie a > > ... ... ... ... ... ... ... ... ... ... ... ... ... ... ... . vô_địch tây ban nha > > ... ... ... ... ... ... ... ... ... ... ... ... ... ... ... . . champions league > > ... ... ... ... ... ... ... ... ... ... ... ... ... ... ... . . v-league 2010 > > ... ... ... ... ... ... ... ... ... ... ... ... ... ... ... . . tỷ_lệ cược tham_khảo các giải ( theo asianbookie ) > > ... ... ... ... ... ... ... ... ... ... ... ... ... ... ... . . lịch đua , bảng điểm f 1 2011 > > * đua xe f 1 tiếp » * * lịch đua & bảng điểm f 1 2011 người_đẹp ba ngôi_sao nữ chụp ảnh bikini cho tạp_chí thể_thao mỹ > > loraine , kiều_nữ của làng billiards > > những vđv nữ quyến_rũ bậc nhất thế_giới > >   bên lề sân_cỏ tiếp »     các giải đấu champions league ngoại_hạng anh serie a primera liga bundesliga v - league   10 sự_kiện thể_thao năm 2010   bảng huy_chương asiad ( 12/11 - 27/11 )   chi_tiết »   tt đoàn vàng_bạc đồng tổng 1 trung quốc 199 119 98 416 2 hàn quốc 76 65 91 232 3 nhật 48 74 94 216 4 iran 20 14 25 59 5 kazakhstan 18 23 38 79 6 ấn độ 14 17 33 64 7 đài bắc 13 16 38 67 8 uzbekistan 11 22 23 56 9 thái lan 11 9 32 52 10 malaysia 9 18 14 41 11 hong kong 8 15 17 40 12 bắc triều tiên 6 10 20 36 13 saudi arabia 5 3 5 13 14 bahrain 5 0 4 9 15 indonesia 4 9 13 26 16 singapore 4 7 6 17 17 kuwait 4 6 1 11 18 qatar 4 5 6 15 19 philippines 3 4 9 16 20 pakistan 3 2 3 8 21 mông cổ 2 5 9 16 22 myanmar 2 5 3 10 23 jordan 2 2 2 6 24 việt nam 1 17 15 33 * toàn_cảnh asiad quảng châu   đặt làm trang_chủ   liên_hệ tòa_soạn   liên_hệ quảng_cáo   đường_dây_nóng : 0123.888.0123 ( hn ) - 0129.233.3555 ( tp hcm )   vnexpress tuyển_dụng trang_chủ     |     ©   copyright 1997-2011 vnexpress.net , all rights reserved contact us - thông_tin tòa_soạn ®   vnexpress giữ bản_quyền nội_dung trên website này .', 7.15006799408066);

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `link_crawled`
--

INSERT INTO `link_crawled` (`id`, `link`) VALUES
(1, 'http://vnexpress.net/'),
(2, 'http://vnexpress.net/gl/the-thao/2011/06/chelsea-sap-bo-nhiem-hau-boi-cua-jose-mourinho/'),
(3, 'http://vnexpress.net/gl/the-thao/2011/06/chelsea-sap-bo-nhiem-hau-boi-cua-jose-mourinho/?cboGuidPDA=0');

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
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=175 ;

--
-- Dumping data for table `link_queue`
--

INSERT INTO `link_queue` (`id`, `link`, `weight`) VALUES
(2, 'http://vnexpress.net/gl/vi-tinh/giai-tri/2011/03/nhung-video-bi-ghet-nhat-tren-youtube/', NULL),
(3, 'http://vnexpress.net/gl/oto-xe-may/2011/06/lexus-tung-ra-ls460-touring-edition/', NULL),
(4, 'http://vnexpress.net/gl/xa-hoi/2011/06/truong-sa-trong-ky-uc-cac-nha-bao/', NULL),
(5, 'http://vnexpress.net/gl/vi-tinh/2011/06/tweet-dau-tien-cua-barack-obama-la-ve-ngay-cua-cha/', NULL),
(6, 'http://vnexpress.net/gl/xa-hoi/2011/06/chay-cho-vinh-hon-100-ki-ot-bi-thieu-rui/', NULL),
(7, 'http://vnexpress.net/gl/kinh-doanh/2011/06/duoc-mua-ban-vang-mieng-tai-cac-diem-co-giay-phep/', NULL),
(8, 'http://vnexpress.net/GL/The-thao/', NULL),
(9, 'http://vnexpress.net/GL/Van-hoa/', NULL),
(10, 'http://vnexpress.net/gl/phap-luat/2011/06/xac-chet-bi-trum-dau-troi-duoi-kenh-thi-nghe/', NULL),
(11, 'http://vnexpress.net/gl/ban-doc-viet/tam-su/2011/06/anh-long-hay-nam-vung-phap-luat-de-tranh-bi-ton-thuong-ve-sau/', NULL),
(12, 'http://vnexpress.net/gl/ban-doc-viet/xa-hoi/2011/06/dau-la-cau-hoi-va-dap-an-cua-nen-giao-duc/', NULL),
(13, 'http://vnexpress.net/gl/doi-song/2011/06/gieng-nha-hang-nhiem-vi-khuan-nuoc-thai-gay-ngo-doc-du-khach/', NULL),
(14, 'http://vnexpress.net/gl/vi-tinh/giai-tri/2011/03/bai-hat-nhat-nheo-thu-hut-toi-36-trieu-luot-xem-tren-youtube/', NULL),
(15, 'http://vnexpress.net/ContactUs/?id=webmaster@vnexpress.net', NULL),
(16, 'http://vnexpress.net/gl/vi-tinh/giai-tri/2011/06/bai-hat-dang-ghet-nhat-tren-youtube-bi-xoa-bo/', NULL),
(17, 'http://vnexpress.net/gl/doi-song/2011/06/vacxin-moi-tin-vui-cho-dan-ong-bi-ung-thu-tuyen-tien-liet/', NULL),
(18, 'http://vnexpress.net/?cboGuidPDA=0', 3.11344445049498),
(19, 'http://vnexpress.net/gl/phap-luat/2011/06/giang-ho-sai-gon-truy-sat-nhau-mot-nguoi-chet/', NULL),
(37, 'http://vnexpress.net/gl/the-thao/2011/06/nhung-duong-chuyen-hieu-qua-nhat-loat-tran-tuan-qua/', NULL),
(21, 'http://vnexpress.net/gl/the-gioi/anh/2011/06/phi-co-khung-o-trien-lam-hang-khong-paris/', NULL),
(22, 'http://vnexpress.net/gl/vi-tinh/2011/06/steve-ballmer-se-roi-microsoft-sau-khi-windows-8-ra-mat/', NULL),
(23, 'http://vnexpress.net/gl/home/', NULL),
(24, 'http://vnexpress.net/gl/phap-luat/2011/06/nam-cong-nhan-quay-be-gai-trong-quan-ca-phe-choi/', NULL),
(25, 'http://vnexpress.net/GL/Ban-doc-viet/', NULL),
(26, 'http://vnexpress.net/gl/xa-hoi/2011/06/hoa-hau-ngoc-han-tu-thien-phai-xuat-phat-tu-trai-tim/', NULL),
(27, 'http://vnexpress.net/gl/van-hoa/am-nhac/2011/06/nhac-pop-bi-phe-phan-ngay-cang-re-tien/', NULL),
(28, 'http://vnexpress.net/GL/The-gioi/', NULL),
(29, 'http://vnexpress.net/gl/kinh-doanh/2011/06/viet-nam-co-them-hang-hang-khong/', NULL),
(30, 'http://vnexpress.net/gl/ban-doc-viet/tam-su/2011/06/em-muon-quen-anh-nhanh-nhu-luc-anh-roi-xa-em/', NULL),
(31, 'http://vnexpress.net/gl/vi-tinh/2011/06/the-gioi-don-nhan-dot-bung-no-ten-mien-moi/', NULL),
(32, 'http://ebank.vnexpress.net/gl/ebank/thi-truong/2011/06/ngan-hang-van-di-dem-voi-khach-gui-tien/', 3.01797453734975),
(33, 'http://vnexpress.net/gl/the-gioi/2011/06/trung-quoc-duoc-yeu-cau-minh-bach-trong-tranh-chap-bien-dong/', NULL),
(34, 'http://vnexpress.net/gl/kinh-doanh/2011/06/kho-nhu-mua-ban-dien-voi-nha-den/', NULL),
(35, 'http://vnexpress.net/gl/ban-doc-viet/2011/06/tai-sao-khong-co-mon-yeu-lao-dong-trong-truong-hoc/', NULL),
(36, 'http://vnexpress.net/gl/the-thao/2011/06/con-duong-tro-thanh-ngoi-sao-lang-golf-cua-rory-mcilroy/', NULL),
(38, 'http://vnexpress.net/gl/the-thao/2011/06/cach-viet-chinh-xac-ten-cua-hlv-falko-g-246-tz/', 3.3467733686542),
(39, 'http://vnexpress.net/GL/The-thao/Chan-dung/', NULL),
(48, 'http://vnexpress.net/gl/ban-doc-viet/phap-luat/', NULL),
(49, 'http://sohoa.vnexpress.net/', NULL),
(41, 'http://vnexpress.net/GL/Xa-hoi/', 2.62844411080675),
(42, 'http://vnexpress.net/GL/The-thao/Anh-Video/', 2.5448788838844),
(43, 'http://vnexpress.net/GL/Oto-Xe-may/', NULL),
(44, 'http://vnexpress.net/gl/the-thao/2011/06/mcilroy-vo-dich-voi-nhieu-ky-luc-o-us-open/', NULL),
(45, 'http://vnexpress.net/gl/the-thao/2011/06/thay-tro-falko-goetz-hang-say-tren-san-tap/', NULL),
(46, 'http://vnexpress.net/GL/Phap-luat/', NULL),
(47, 'http://vnexpress.net/gl/the-thao/2011/06/inter-tan-mong-loi-keo-mourinho-moi/', 5.39466989923692),
(50, 'http://vnexpress.net/gl/doi-song/gia-dinh/', NULL),
(51, 'http://vnexpress.net/gl/the-gioi/nguoi-viet-5-chau/', 2.4633100861473),
(52, 'http://vnexpress.net/gl/vi-tinh/kinh-nghiem/', NULL),
(53, 'http://vnexpress.net/gl/the-thao/chan-dung/2009/05/3ba0ea0f/', NULL),
(54, 'http://gamethu.vnexpress.net/', NULL),
(55, 'http://vnexpress.net/gl/the-thao/chan-dung/2009/03/3ba0d222/', NULL),
(56, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/11/3ba08baa/', NULL),
(57, 'http://vnexpress.net/gl/doi-song/', NULL),
(58, 'http://www.gz2010.cn/info/ENG_ENG/ZZ/ZZS102A_@@@@@@@@@@@@@@@@@ENG_NOC=HKG.html', NULL),
(59, 'http://vieclam.vnexpress.net/', NULL),
(60, 'http://vnexpress.net/gl/the-thao/chan-dung/2010/01/3ba1760c/', NULL),
(61, 'http://vnexpress.net/gl/ban-doc-viet/khoa-hoc/', NULL),
(62, 'http://vnexpress.net/gl/the-thao/2010/09/3ba2018c/', 5.69852758439251),
(63, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/10/3ba07af5/', NULL),
(64, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/11/3ba08d2b/', NULL),
(65, 'http://vnexpress.net/gl/the-gioi/cuoc-song-do-day/', NULL),
(66, 'http://vnexpress.net/gl/vi-tinh/hoi-dap/', NULL),
(67, 'http://vnexpress.net/gl/the-thao/2011/06/tai-nang-bong-da-viet-nam-va-thang-ngay-kho-luyen-o-qatar/', NULL),
(68, 'http://vnexpress.net/gl/the-thao/2011/06/granada-mung-len-hang-nhu-the-vo-dich-liga/', 2.94383688383327),
(69, 'http://vnexpress.net/gl/the-thao/2009/02/3ba0b42e/', 2.56056396595621),
(70, 'http://vnexpress.net/gl/the-thao/2011/06/my-vao-ban-ket-concacaf-gold-cup/', NULL),
(71, 'http://vnexpress.net/GL/The-thao/Bong-da/2009/08/3BA11F0D/page_6.asp', NULL),
(72, 'http://vnexpress.net/gl/the-thao/2011/06/mu-tho-o-voi-berbatov/', 2.52160813288603),
(73, 'http://vnexpress.net/gl/the-thao/chan-dung/2009/03/3ba0d7ea/', 2.75873466246183),
(74, 'http://vnexpress.net/gl/vi-tinh/hacker-virus/', NULL),
(75, 'http://nhadep.vnexpress.net/gl/nha-dep/', NULL),
(76, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/10/3ba0710c/', NULL),
(77, 'http://vnexpress.net/gl/van-hoa/thoi-trang/', NULL),
(78, 'http://vnexpress.net/gl/the-thao/2009/07/3ba1134b/', NULL),
(79, 'http://vnexpress.net/gl/xa-hoi/du-lich/', 9.30058829804373),
(80, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/10/3ba0705d/', NULL),
(81, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/07/3ba047b1/', 5.2651001668349),
(82, 'http://www.gz2010.cn/info/ENG_ENG/ZZ/ZZS102A_@@@@@@@@@@@@@@@@@ENG_NOC=UZB.html', NULL),
(83, 'http://vnexpress.net/gl/the-thao/chan-dung/2009/01/3ba0cea6/', NULL),
(84, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/08/3ba058e4/', 2.73900032749428),
(85, 'http://vnexpress.net/gl/vi-tinh/san-pham-moi/', NULL),
(86, 'http://vnexpress.net/gl/the-thao/anh-video/2010/08/3ba1fd6b/', 2.39708117903837),
(87, 'http://thantai.vnexpress.net/', 2.20814475981573),
(88, 'http://vnexpress.net/gl/the-thao/2011/06/u23-viet-nam-khong-the-gay-bat-ngo-truoc-arap-xe-ut/', NULL),
(89, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/10/3ba07e13/', 7.3279329962749),
(90, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/08/3ba052b7/', NULL),
(91, 'http://ngoisao.net/', 2.52547373956905),
(92, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/07/3ba04d3c/', NULL),
(93, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/12/3ba099b7/', 6.19488557083729),
(94, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/04/3ba018fb/', NULL),
(95, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/09/3ba06ae5/', NULL),
(96, 'http://vnexpress.net/gl/the-thao/2011/06/mourinho-sap-thay-doi-chien-thuat-de-real-tan-cong-tot-hon/', 2.38743703800331),
(97, 'http://vnexpress.net/gl/the-thao/2011/06/michael-phelps-thang-noi-dung-khong-phai-so-truong/', NULL),
(98, 'http://vnexpress.net/gl/the-thao/2011/06/chieu-nay-ban-ve-tran-u23-viet-nam-u23-arap-xeut/', NULL),
(99, 'http://vnexpress.net/GL/Topic/?ID=6298', NULL),
(100, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/08/3ba0592c/', NULL),
(101, 'http://www.gz2010.cn/info/ENG_ENG/ZZ/ZZS102A_@@@@@@@@@@@@@@@@@ENG_NOC=SIN.html', NULL),
(102, 'http://vnexpress.net/gl/the-thao/2010/04/3ba1a9de/', NULL),
(103, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/12/3ba096a1/', NULL),
(104, 'http://www.gz2010.cn/info/ENG_ENG/ZZ/ZZS102A_@@@@@@@@@@@@@@@@@ENG_NOC=BRN.html', NULL),
(105, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/11/3ba0871a/', 2.56167696566374),
(106, 'http://ads.fpt.net/lienhe.aspx', NULL),
(107, 'http://vnexpress.net/tag/3305/chelsea/', NULL),
(108, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/08/3ba05334/', NULL),
(109, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/09/3ba06c7a/', NULL),
(110, 'http://vnexpress.net/gl/the-thao/chan-dung/2009/03/3ba0d647/', NULL),
(111, 'http://vnexpress.net/gl/the-thao/2010/10/3ba21eb0/', 5.66476140118002),
(112, 'http://vnexpress.net/gl/suc-khoe/lam-dep/', NULL),
(113, 'http://vnexpress.net/gl/cuoi/tieu-pham/', NULL),
(114, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/12/3ba09402/', NULL),
(115, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/07/3ba0425c/', NULL),
(116, 'http://vnexpress.net/gl/vi-tinh/giai-tri/', NULL),
(117, 'http://vnexpress.net/gl/xa-hoi/giao-duc/', NULL),
(118, 'http://vnexpress.net/gl/vi-tinh/', NULL),
(119, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/11/3ba0822a/', NULL),
(120, 'http://vnexpress.net/gl/the-thao/bong-da/2008/11/3ba08c45/', NULL),
(121, 'http://www.fptad.com/qc/v/vnexpress/2011/tuyenphongvienbanvanhoa/', NULL),
(122, 'http://vnexpress.net/rao-vat/', 2.39014372615385),
(123, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/07/3ba04ba2/', 2.747961325942),
(124, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/10/3ba072c5/', NULL),
(125, 'http://ebank.vnexpress.net/gl/ebank/', NULL),
(126, 'http://vnexpress.net/gl/the-thao/Page_1.asp', 5.79629686308572),
(127, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/10/3ba07416/', NULL),
(128, 'http://vnexpress.net/gl/the-thao/chan-dung/2009/05/3ba0ede9/', NULL),
(129, 'http://vnexpress.net/gl/the-thao/2010/02/3ba18d2b/', NULL),
(130, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/09/3ba06169/', NULL),
(131, 'http://vnexpress.net/gl/the-thao/2011/06/argentina-khoi-dong-tung-bung-cho-copa-amerrica/', NULL),
(132, 'http://vnexpress.net/gl/the-thao/chan-dung/2009/04/3ba0dd40/', NULL),
(133, 'http://vnexpress.net/gl/doi-song/mua-sam/', NULL),
(134, 'http://ebank.vnexpress.net/', NULL),
(135, 'http://vnexpress.net/gl/the-thao/chan-dung/2009/07/3ba11c73/', NULL),
(136, 'http://vnexpress.net/gl/the-thao/chan-dung/2010/01/3ba17a90/', NULL),
(137, 'http://vnexpress.net/gl/the-thao/chan-dung/2009/03/3ba0cb8c/', NULL),
(138, 'http://vnexpress.net/gl/kinh-doanh/quoc-te/', 2.42293102885758),
(139, 'http://vnexpress.net/gl/khoa-hoc/moi-truong/', 4.47160043896276),
(140, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/12/3ba094fe/', NULL),
(141, 'http://vnexpress.net/gl/the-thao/2009/03/3ba0cac2/', 2.55207024488564),
(142, 'http://vnexpress.net/gl/the-thao/chan-dung/2010/11/3ba2290f/', NULL),
(143, 'http://vnexpress.net/gl/van-hoa/guong-mat-nghe-sy/', NULL),
(144, 'http://vnexpress.net/gl/the-thao/bong-da/', 2.16929613708348),
(145, 'http://vnexpress.net/gl/khoa-hoc/anh/', NULL),
(146, 'http://vnexpress.net/GL/Topic/?ID=5788', 8.24611781655064),
(147, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/10/3ba071b9/', NULL),
(148, 'http://vnexpress.net/gl/van-hoa/san-khau-dien-anh/', NULL),
(149, 'http://vnexpress.net/gl/kinh-doanh/', 2.18094566352526),
(150, 'http://vnexpress.net/gl/the-thao/chan-dung/2009/05/3ba0eac7/', NULL),
(151, 'http://vnexpress.net/gl/ban-doc-viet/xa-hoi/', NULL),
(152, 'http://www.gz2010.cn/info/ENG_ENG/ZZ/ZZS102A_@@@@@@@@@@@@@@@@@ENG_NOC=PHI.html', NULL),
(153, 'http://vnexpress.net/gl/the-thao/bong-da/2010/08/lich-dau-ngoai-hang-anh/page_1.asp', NULL),
(154, 'http://vnexpress.net/gl/ban-doc-viet/van-hoa/', NULL),
(155, 'http://vnexpress.net/gl/the-thao/2011/06/de-mua-alexis-sanchez-barca-sap-chi-54-trieu-usd/', NULL),
(156, 'http://vnexpress.net/gl/xa-hoi/nhip-dieu-tre/', NULL),
(157, 'http://vnexpress.net/gl/the-thao/chan-dung/2009/07/3ba10bdc/', 2.64238647329567),
(158, 'http://www.fptad.com/lienhe.aspx', NULL),
(159, 'http://vnexpress.net/gl/xa-hoi/tuyen-sinh/', NULL),
(160, 'http://www.gz2010.cn/info/ENG_ENG/ZZ/ZZS102A_@@@@@@@@@@@@@@@@@ENG_NOC=MAS.html', NULL),
(161, 'http://vnexpress.net/gl/the-thao/chan-dung/2008/05/3ba024a0/', 2.64544286002137),
(162, 'http://vnexpress.net/gl/the-thao/chan-dung/2010/11/3ba23486/', NULL),
(163, 'http://vnexpress.net/gl/the-thao/chan-dung/2011/02/ba-ngoi-sao-nu-chup-anh-bikini-cho-tap-chi-the-thao-my/', NULL),
(164, 'http://vnexpress.net/gl/the-thao/tennis/2010/02/3ba18b23/', NULL),
(165, 'http://vnexpress.net/gl/ban-doc-viet/the-thao/', 7.37636949749175),
(166, 'http://vnexpress.net/gl/kinh-doanh/bat-dong-san/', 2.25003598158562),
(167, 'http://vnexpress.net/gl/the-thao/chan-dung/2009/08/3ba12533/', NULL),
(168, 'http://vnexpress.net/gl/the-thao/tennis/2009/02/3ba0b33a/', NULL),
(169, 'http://vnexpress.net/gl/the-thao/chan-dung/2009/02/3ba0b6ea/', NULL),
(170, 'http://vnexpress.net/gl/the-thao/bong-da/2009/02/3ba0ae3f/', 6.67013125084479),
(171, 'http://vnexpress.net/gl/phap-luat/ky-su/', NULL),
(172, 'http://vnexpress.net/gl/24h-qua/', 3.36574515094853),
(173, 'http://vnexpress.net/gl/the-thao/bong-da/2010/08/lich-dau-ngoai-hang-anh/', NULL),
(174, 'http://vnexpress.net/gl/the-gioi/phan-tich/', NULL);

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
