/*
Navicat MySQL Data Transfer

Source Server         : mydata
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : hospitalapplydb

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2020-11-20 10:28:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for applymsg
-- ----------------------------
DROP TABLE IF EXISTS `applymsg`;
CREATE TABLE `applymsg` (
  `applyId` int(50) NOT NULL AUTO_INCREMENT,
  `applyUserId` varchar(11) DEFAULT NULL,
  `applyUserName` varchar(255) DEFAULT NULL,
  `applyMessageId` varchar(11) DEFAULT NULL,
  `applyMessageName` varchar(255) DEFAULT NULL,
  `applyChoiceTime` varchar(255) DEFAULT NULL,
  `applyTime` varchar(255) DEFAULT NULL,
  `applyState` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`applyId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of applymsg
-- ----------------------------
INSERT INTO `applymsg` VALUES ('15', '15', '画心,15249241001', '3', '王大夫', '2020-11-17 08:90-10:30', '2020-11-17 16:01', '2');
INSERT INTO `applymsg` VALUES ('16', '15', '画心,15249241001', '3', '王大夫', '2020-11-17 10:30-11:30', '2020-11-17 17:08', '2');
INSERT INTO `applymsg` VALUES ('17', '15', '画心,15249241001', '3', '王大夫', '2020-11-17 08:90-10:30', '2020-11-17 17:22', '2');
INSERT INTO `applymsg` VALUES ('18', '15', '画心,15249241001', '3', '王大夫', '2020-11-17 08:90-10:30', '2020-11-17 17:22', '2');
INSERT INTO `applymsg` VALUES ('19', '15', '画心,15249241001', '3', '王大夫', '2020-11-18 08:90-10:30', '2020-11-18 11:00', '1');
INSERT INTO `applymsg` VALUES ('20', '15', '画心,15249241001', '3', '王大夫', '2020-11-19 08:90-10:30', '2020-11-19 14:49', '2');
INSERT INTO `applymsg` VALUES ('21', '17', '小花,15249246002', '7', '王可可', '2020-11-20 10:30-11:30', '2020-11-20 10:05', '2');

-- ----------------------------
-- Table structure for bannertb
-- ----------------------------
DROP TABLE IF EXISTS `bannertb`;
CREATE TABLE `bannertb` (
  `bannerId` int(11) NOT NULL AUTO_INCREMENT,
  `bannerDoctorId` int(11) DEFAULT NULL,
  `bannerDoctorName` varchar(255) DEFAULT NULL,
  `bannerImg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bannerId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bannertb
-- ----------------------------
INSERT INTO `bannertb` VALUES ('1', '3', '王大夫', 'icon_bg_qh266.jpg');
INSERT INTO `bannertb` VALUES ('2', '4', '易主任', 'weixintupian_20200506145932.jpg');
INSERT INTO `bannertb` VALUES ('3', '5', '张主任', '1505208496181.jpg');
INSERT INTO `bannertb` VALUES ('4', '7', '王可可', '1497354407900.jpg');

-- ----------------------------
-- Table structure for departmentmsg
-- ----------------------------
DROP TABLE IF EXISTS `departmentmsg`;
CREATE TABLE `departmentmsg` (
  `departmentId` int(50) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(255) DEFAULT NULL,
  `departmentInfor` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`departmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of departmentmsg
-- ----------------------------
INSERT INTO `departmentmsg` VALUES ('3', '外科', '心胸外科、肝胆外科、泌尿外科、矫形外科、神经外科、烧伤、整形科');
INSERT INTO `departmentmsg` VALUES ('4', '内科', '呼吸内科，消化内科，心血管内科，神经内科，内分泌科，血液内科，传染病科，小儿科');
INSERT INTO `departmentmsg` VALUES ('5', '眼科', null);
INSERT INTO `departmentmsg` VALUES ('6', '儿科', null);
INSERT INTO `departmentmsg` VALUES ('7', '耳鼻科', null);
INSERT INTO `departmentmsg` VALUES ('8', '耳鼻喉科', null);
INSERT INTO `departmentmsg` VALUES ('9', '口腔科', null);
INSERT INTO `departmentmsg` VALUES ('10', '皮肤科', null);
INSERT INTO `departmentmsg` VALUES ('11', '中医科', null);
INSERT INTO `departmentmsg` VALUES ('12', '针灸推拿科', null);
INSERT INTO `departmentmsg` VALUES ('13', '心理咨询科', null);
INSERT INTO `departmentmsg` VALUES ('14', '骨科', null);

-- ----------------------------
-- Table structure for diagnosistb
-- ----------------------------
DROP TABLE IF EXISTS `diagnosistb`;
CREATE TABLE `diagnosistb` (
  `diagnosisId` int(11) NOT NULL AUTO_INCREMENT,
  `diagnosisApplyId` int(11) DEFAULT NULL,
  `diagnosisDoctorId` int(11) DEFAULT NULL,
  `diagnosisInfor` varchar(255) DEFAULT NULL,
  `diagnosisMethods` varchar(255) DEFAULT NULL,
  `diagnosisDrug` varchar(255) DEFAULT NULL,
  `diagnosisTime` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`diagnosisId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of diagnosistb
-- ----------------------------
INSERT INTO `diagnosistb` VALUES ('1', '15', '3', '111111111111111111', '1111111', '11111111', '2020-11-18 11:30');
INSERT INTO `diagnosistb` VALUES ('3', '16', '3', '222222', '222222222222', '22222222222222', '2020-11-18 11:36');
INSERT INTO `diagnosistb` VALUES ('4', '17', '3', '2', '222222', '2222222222', '2020-11-18 11:36');
INSERT INTO `diagnosistb` VALUES ('5', '18', '3', '9000000', '0000000000000000000', '0000000000000000000000000', '2020-11-18 14:46');
INSERT INTO `diagnosistb` VALUES ('6', '20', '3', '1111111', '11111111111111', '111111111111111111111', '2020-11-19 14:50');
INSERT INTO `diagnosistb` VALUES ('7', '21', '7', '感冒了', '注意休息', '感冒药', '2020-11-20 10:06');

-- ----------------------------
-- Table structure for doctormsg
-- ----------------------------
DROP TABLE IF EXISTS `doctormsg`;
CREATE TABLE `doctormsg` (
  `doctorId` int(11) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(255) DEFAULT NULL,
  `doctorName` varchar(255) DEFAULT NULL,
  `doctorMoney` varchar(255) DEFAULT NULL,
  `doctorLevel` varchar(255) DEFAULT NULL,
  `doctorCard` varchar(255) DEFAULT NULL,
  `doctorMessage` varchar(1500) DEFAULT NULL,
  `departmentId` varchar(100) DEFAULT NULL,
  `doctorPswd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`doctorId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of doctormsg
-- ----------------------------
INSERT INTO `doctormsg` VALUES ('3', '外科', '王大夫', '10', '专家', '888866661001', '三级教授，主任医师、博士生导师，曾任西京医院泌尿外科主任14年，培养研究生数十名，获得多项国家自然基金，出版了多部著作，发表论文数百篇。多次在国外进行学术交流。于1992年在美国休斯顿肿瘤中心学习工作，重点研究前列腺癌的诊治，取得很大进展。 ', '3', '661001');
INSERT INTO `doctormsg` VALUES ('4', '内科', '易主任', '20', '专家', '888866661002', '1938年11月生。第四军医大学西京医院血液内科教授、主任医师、博士研究生导师。原血液内科主任、校专家组成员，现任陕西省医学会疑难血液病会诊中心主任、中华医学会血液学会常委、中华医学会医疗事故鉴定专家、中华医学会陕西血液病学会名誉主任委员、陕西省抗癌协会血液肿瘤专业委员会名誉主任委员、中国医师协会血液病分会顾问、《临床血液学杂志》《中华临床医学荟萃杂志》等多家学术期刊常务编委。', '4', '661002');
INSERT INTO `doctormsg` VALUES ('5', '外科', '张主任', '30', '普通', '888866661003', '著名消化病学专家，中国工程院院士，第四军医大学校长，全军消化病研究所所长，肿瘤生物学国家重点实验室主任，国家临床药理基地主任，中华消化学会主任委员，中国抗癌协会副理事长，亚太胃肠病学会常务理事兼奖励委员会主席，国家教育部长江学者奖励计划特聘教授。', '3', '661003');
INSERT INTO `doctormsg` VALUES ('6', '外科', '李大夫', '50', '专家', '1116665002222', '李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫李大夫', '3', null);
INSERT INTO `doctormsg` VALUES ('7', '外科', '王可可', '5', '专家', '88889999221006', '王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可王可可', '3', '221006');

-- ----------------------------
-- Table structure for jijiantb
-- ----------------------------
DROP TABLE IF EXISTS `jijiantb`;
CREATE TABLE `jijiantb` (
  `jijianId` int(11) NOT NULL AUTO_INCREMENT,
  `jijianName` varchar(255) DEFAULT NULL,
  `jijianAddresse` varchar(255) DEFAULT NULL,
  `jijianPhone` varchar(255) DEFAULT NULL,
  `jijianCard` varchar(255) DEFAULT NULL,
  `jijianUserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`jijianId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jijiantb
-- ----------------------------
INSERT INTO `jijiantb` VALUES ('1', '小明', '西安市雁塔区', '15249248888', null, '17');
INSERT INTO `jijiantb` VALUES ('2', '小花', '西安市', '15249249999', null, '17');
INSERT INTO `jijiantb` VALUES ('3', '小萝莉', '上海市', '15249246969', null, '17');
INSERT INTO `jijiantb` VALUES ('4', '小品', '西安市', '15249248888', null, '17');
INSERT INTO `jijiantb` VALUES ('5', '小明', '西安市莲湖区', '15249249988', null, '26');
INSERT INTO `jijiantb` VALUES ('6', '小米', '西安市雁塔区', '15249243002', null, '92');
INSERT INTO `jijiantb` VALUES ('7', '小虎虎', '西安市雁塔区', '15249243002', null, '92');
INSERT INTO `jijiantb` VALUES ('8', '小花', '西安市雁塔区', '15249248877', null, '92');
INSERT INTO `jijiantb` VALUES ('9', '画心', '朋友', '15249241001', null, '15');
INSERT INTO `jijiantb` VALUES ('10', '小花', '朋友', '15249246002', '666999', '17');

-- ----------------------------
-- Table structure for newsmessage
-- ----------------------------
DROP TABLE IF EXISTS `newsmessage`;
CREATE TABLE `newsmessage` (
  `newsId` int(100) NOT NULL AUTO_INCREMENT,
  `newsTitle` varchar(255) DEFAULT NULL,
  `newsContent` varchar(5000) DEFAULT NULL,
  `newsTime` varchar(100) DEFAULT NULL,
  `newsState` varchar(255) DEFAULT NULL,
  `newsUserId` int(11) DEFAULT NULL,
  `newsUserName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`newsId`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of newsmessage
-- ----------------------------
INSERT INTO `newsmessage` VALUES ('73', '河南省“志愿服务乡村行”', '我省“志愿服务乡村行”活动以“举旗帜、聚民心、育新人、兴文化、展形象”为主题，以县级文明实践中心为依托，以志愿服务为重点，着眼凝聚群众、引导群众、服务群众，面向全省农村开展送理论、送政策、送文化、送健康、送科技、送法律、送环保、送新风等志愿服务活动，让农民群众在参与中得到更多实惠，推动新时代文明实践活动扎实有效开展。', '2020-11-19 14:50', '2', '3', '王大夫');
INSERT INTO `newsmessage` VALUES ('74', '愿者将服务70周年国庆', '为庆祝第56个学雷锋纪念日，2019年“爱满京城”首都学雷锋志愿服务推动日活动3月3日举行。记者从会上获悉，今年的学雷锋志愿服务活动将与北京市民喜迎中华人民共和国成立70周年、共建和谐宜居之都等主题相结合，北京市还将培育城市管理领域专业化志愿者队伍，动员志愿者参与城市治理服务。', '2020-11-19 14:50', '3', '3', '王大夫');
INSERT INTO `newsmessage` VALUES ('75', '雷锋志愿服务', '山西新闻网3月6日讯(记者 卢奕如 通讯员 苏顺生)为弘扬雷锋精神和“奉献、友爱、互助、进步”的志愿服务精神，在3月5日学雷锋活动日和全省上下热烈开展“改革创新、奋发有为”大讨论之际，山西省文明办、山西省民政厅向全省广大干部群众发出倡议。', '2020-11-19 14:50', '2', '3', '王大夫');
INSERT INTO `newsmessage` VALUES ('76', '我的资讯信息', '我的资讯信息我的资讯信息我的资讯信息我的资讯信息我的资讯信息我的资讯信息我的资讯信息我的资讯信息我的资讯信息我的资讯信息我的资讯信息我的资讯信息我的资讯信息我的资讯信息我的资讯信息我的资讯信息我的资讯信息我的资讯信息我的资讯信息', '2020-11-20 10:00', '2', '7', '王可可');
INSERT INTO `newsmessage` VALUES ('77', '尝试思思', '尝试思思尝试思思尝试思思尝试思思尝试思思尝试思思尝试思思尝试思思尝试思思尝试思思尝试思思尝试思思尝试思思尝试思思尝试思思尝试思思尝试思思尝试思思尝试思思尝试思思', '2020-11-20 10:00', '2', '7', '王可可');

-- ----------------------------
-- Table structure for rechargetb
-- ----------------------------
DROP TABLE IF EXISTS `rechargetb`;
CREATE TABLE `rechargetb` (
  `rechargeId` int(255) NOT NULL AUTO_INCREMENT,
  `rechargeMoney` varchar(255) DEFAULT NULL,
  `rechargeUserId` int(11) DEFAULT NULL,
  `rechargeUserName` varchar(255) DEFAULT NULL,
  `rechargeTime` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rechargeId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rechargetb
-- ----------------------------
INSERT INTO `rechargetb` VALUES ('16', '600', '15', '小米', '2020-11-17 16:36');
INSERT INTO `rechargetb` VALUES ('17', '60', '15', '小米', '2020-11-18 09:19');
INSERT INTO `rechargetb` VALUES ('18', '6000', '17', '小花猫', '2020-11-20 10:04');

-- ----------------------------
-- Table structure for reviewtb
-- ----------------------------
DROP TABLE IF EXISTS `reviewtb`;
CREATE TABLE `reviewtb` (
  `reviewId` int(11) NOT NULL AUTO_INCREMENT,
  `reviewMessageId` int(11) DEFAULT NULL,
  `reviewContent` varchar(255) DEFAULT NULL,
  `reviewUserId` int(11) DEFAULT NULL,
  `reviewUserName` varchar(255) DEFAULT NULL,
  `reviewTime` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`reviewId`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reviewtb
-- ----------------------------
INSERT INTO `reviewtb` VALUES ('19', '21', '你好吧', '106', '小丸子', '2020-11-10 10:55');
INSERT INTO `reviewtb` VALUES ('20', '23', 'ooooo', '106', '小丸子', '2020-11-10 14:29');
INSERT INTO `reviewtb` VALUES ('21', '23', 'ppppp', '106', '小丸子like', '2020-11-10 14:40');
INSERT INTO `reviewtb` VALUES ('22', '23', '你好啊', '110', '悦悦code', '2020-11-10 16:17');
INSERT INTO `reviewtb` VALUES ('23', '23', '我来看看', '110', '悦悦code', '2020-11-10 16:17');
INSERT INTO `reviewtb` VALUES ('25', '5', '66666', '15', '小米', '2020-11-17 15:07');
INSERT INTO `reviewtb` VALUES ('26', '5', '你是一个好医生', '15', '小米', '2020-11-17 15:32');
INSERT INTO `reviewtb` VALUES ('27', '3', '6666', '15', '小米', '2020-11-19 11:28');
INSERT INTO `reviewtb` VALUES ('28', '3', '999999', '15', '小米', '2020-11-19 15:17');
INSERT INTO `reviewtb` VALUES ('29', '7', '很不错的', '17', '小花猫', '2020-11-20 10:07');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(50) NOT NULL AUTO_INCREMENT,
  `uname` varchar(100) DEFAULT NULL,
  `uphone` varchar(100) DEFAULT NULL,
  `uSex` varchar(50) DEFAULT NULL,
  `uCard` varchar(255) DEFAULT NULL,
  `upswd` varchar(100) DEFAULT NULL,
  `utime` varchar(100) DEFAULT NULL,
  `umoney` varchar(255) DEFAULT NULL,
  `userImage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('15', '小米', '15249243001', '女', '618250', '123456', '2020-11-16 15:13', '630.0', '20201118094007.jpg');
INSERT INTO `user` VALUES ('16', 'admin', null, null, '66699910036', '123456', null, null, null);
INSERT INTO `user` VALUES ('17', '小花猫', '15249246001', '男', '6669998881001', '123456', '2020-11-20 10:03', '5995.0', '20201120100828.jpg');
