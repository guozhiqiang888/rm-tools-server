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
(  '��˾4', 'corporation four', '4.png', '�׾�4', 4000.40),
(  '��˾5', 'corporation fivr', '5.png', '�׾�5', 5000.50),
(  '��˾6', 'corporation six', '6.png', '�׾�6', 6000.60),
(  '��˾7', 'corporation senven', '7.png', '�׾�7', 7000.70),
(  '��˾8', 'corporation eight', '8.png', '�׾�8', 8000.80),
(  '��˾9', 'corporation nine', '9.png', '�׾�9', 9000.90),
(  '��˾10', 'corporation ten', '10.png', '�׾�10', 11111.11);
