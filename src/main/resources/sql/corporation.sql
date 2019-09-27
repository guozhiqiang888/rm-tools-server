DROP TABLE IF EXISTS `corporation`;
CREATE TABLE `corporation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `corp_name_ch` varchar(255) DEFAULT NULL,
  `corp_name_en` varchar(255) DEFAULT NULL,
  `corp_img_url` varchar(255) DEFAULT NULL,
  `legal_rep` varchar(255) DEFAULT NULL,
  `reg_capital` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO `corporation` (`corp_name_ch`,`corp_name_en`, `corp_img_url`,`legal_rep`, `reg_capital` )
VALUES
(  '公司4', 'corporation four', '4.png', '雷军4', 4000.40),
(  '公司5', 'corporation fivr', '5.png', '雷军5', 5000.50),
(  '公司6', 'corporation six', '6.png', '雷军6', 6000.60),
(  '公司7', 'corporation senven', '7.png', '雷军7', 7000.70),
(  '公司8', 'corporation eight', '8.png', '雷军8', 8000.80),
(  '公司9', 'corporation nine', '9.png', '雷军9', 9000.90),
(  '公司10', 'corporation ten', '10.png', '雷军10', 11111.11);
