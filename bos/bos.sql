/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : bos

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-20 08:42:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_function
-- ----------------------------
DROP TABLE IF EXISTS `auth_function`;
CREATE TABLE `auth_function` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `page` varchar(255) DEFAULT NULL,
  `generatemenu` varchar(255) DEFAULT NULL,
  `zindex` int(11) DEFAULT NULL,
  `pid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `AK_Key_2` (`name`),
  KEY `FK33r6np87v1p6gge7t6rpcao5h` (`pid`),
  CONSTRAINT `FK33r6np87v1p6gge7t6rpcao5h` FOREIGN KEY (`pid`) REFERENCES `auth_function` (`id`),
  CONSTRAINT `FK_Reference_1x` FOREIGN KEY (`pid`) REFERENCES `auth_function` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_function
-- ----------------------------
INSERT INTO `auth_function` VALUES ('11', '基础档案', 'jichudangan', null, null, '1', '0', null);
INSERT INTO `auth_function` VALUES ('112', '收派标准', 'standard', null, 'page_base_standard.action', '1', '1', '11');
INSERT INTO `auth_function` VALUES ('113', '取派员设置', 'staff', null, 'page_base_staff.action', '1', '2', '11');
INSERT INTO `auth_function` VALUES ('114', '区域设置', 'region', null, 'page_base_region.action', '1', '3', '11');
INSERT INTO `auth_function` VALUES ('115', '管理分区', 'subarea', null, 'page_base_subarea.action', '1', '4', '11');
INSERT INTO `auth_function` VALUES ('116', '管理定区/调度排班', 'decidedzone', null, 'page_base_decidedzone.action', '1', '5', '11');
INSERT INTO `auth_function` VALUES ('12', '受理', 'shouli', null, null, '1', '1', null);
INSERT INTO `auth_function` VALUES ('121', '业务受理', 'noticebill', null, 'page_qupai_noticebill_add.action', '1', '0', '12');
INSERT INTO `auth_function` VALUES ('122', '工作单快速录入', 'quickworkordermanage', null, 'page_qupai_quickworkorder.action', '1', '1', '12');
INSERT INTO `auth_function` VALUES ('124', '工作单导入', 'workordermanageimport', null, 'page_qupai_workorderimport.action', '1', '3', '12');
INSERT INTO `auth_function` VALUES ('13', '调度', 'diaodu', null, null, '1', '2', null);
INSERT INTO `auth_function` VALUES ('131', '查台转单', 'changestaff', null, null, '1', '0', '13');
INSERT INTO `auth_function` VALUES ('132', '人工调度', 'personalassign', null, 'page_qupai_diaodu.action', '1', '1', '13');
INSERT INTO `auth_function` VALUES ('14', '物流配送流程管理', 'zhongzhuan', null, null, '1', '3', null);
INSERT INTO `auth_function` VALUES ('141', '启动配送流程', 'start', null, 'workOrderManageAction_list.action', '1', '0', '14');
INSERT INTO `auth_function` VALUES ('142', '查看个人任务', 'personaltask', null, 'taskAction_findPersonalTask.action', '1', '1', '14');
INSERT INTO `auth_function` VALUES ('143', '查看我的组任务', 'grouptask', null, 'taskAction_findGroupTask.action', '1', '2', '14');
INSERT INTO `auth_function` VALUES ('402882bc60592de601605939c3820001', '取派员设置', 'staff-list', 'xx', 'staffAction_pageQuery.action', '0', '2', '113');
INSERT INTO `auth_function` VALUES ('8a7e843355a4392d0155a43aa7150000', '删除取派员', 'staff.delete', '删除取派员', 'staffAction_delete.action', '0', '1', '113');

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `AK_Key_2` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES ('402882bc6058364d01605838ff200000', 'test', null, 'test');
INSERT INTO `auth_role` VALUES ('402882bc60583f760160584a59890000', '超级管理员', null, '超级管理员');
INSERT INTO `auth_role` VALUES ('402882bc60583f760160584b69f30001', '普通管理员', null, '普通管理员');
INSERT INTO `auth_role` VALUES ('402882bc6067029d016067076a710000', '调度权限', null, '调度');

-- ----------------------------
-- Table structure for bc_decidedzone
-- ----------------------------
DROP TABLE IF EXISTS `bc_decidedzone`;
CREATE TABLE `bc_decidedzone` (
  `id` varchar(32) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `staff_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh0xplk12o52a6eryw4hcqnfwt` (`staff_id`),
  CONSTRAINT `FK_decidedzone_staff` FOREIGN KEY (`staff_id`) REFERENCES `bc_staff` (`id`),
  CONSTRAINT `FKh0xplk12o52a6eryw4hcqnfwt` FOREIGN KEY (`staff_id`) REFERENCES `bc_staff` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bc_decidedzone
-- ----------------------------
INSERT INTO `bc_decidedzone` VALUES ('123', '456', '402882a95fc8e816015fc8eae3a00000');
INSERT INTO `bc_decidedzone` VALUES ('124', '457', '402882a95fc8e816015fc8f1962c0001');
INSERT INTO `bc_decidedzone` VALUES ('125', '458', '402882a95fccdd08015fcd7b3f8a0001');
INSERT INTO `bc_decidedzone` VALUES ('126', '459', '402882a95fcdb972015fcdcac5bf0001');
INSERT INTO `bc_decidedzone` VALUES ('qwe', 'qwe ', '402882a95fccdd08015fcd7b3f8a0001');

-- ----------------------------
-- Table structure for bc_region
-- ----------------------------
DROP TABLE IF EXISTS `bc_region`;
CREATE TABLE `bc_region` (
  `id` varchar(32) NOT NULL,
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  `postcode` varchar(50) DEFAULT NULL,
  `shortcode` varchar(30) DEFAULT NULL,
  `citycode` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bc_region
-- ----------------------------
INSERT INTO `bc_region` VALUES ('JX001', '江西省', '抚州市', '临川区', '344100', 'JXFZLC', 'jiangxi');
INSERT INTO `bc_region` VALUES ('QY001', '北京市', '北京市', '东城区', '110101', 'BJBJDC', 'beijing');
INSERT INTO `bc_region` VALUES ('QY002', '北京市', '北京市', '西城区', '110102', 'BJBJXC', 'beijing');
INSERT INTO `bc_region` VALUES ('QY003', '北京市', '北京市', '朝阳区', '110105', 'BJBJCY', 'beijing');
INSERT INTO `bc_region` VALUES ('QY004', '北京市', '北京市', '丰台区', '110106', 'BJBJFT', 'beijing');
INSERT INTO `bc_region` VALUES ('QY005', '北京市', '北京市', '石景山区', '110107', 'BJBJSJS', 'beijing');
INSERT INTO `bc_region` VALUES ('QY006', '北京市', '北京市', '海淀区', '110108', 'BJBJHD', 'beijing');
INSERT INTO `bc_region` VALUES ('QY007', '北京市', '北京市', '门头沟区', '110109', 'BJBJMTG', 'beijing');
INSERT INTO `bc_region` VALUES ('QY008', '北京市', '北京市', '房山区', '110111', 'BJBJFS', 'beijing');
INSERT INTO `bc_region` VALUES ('QY009', '北京市', '北京市', '通州区', '110112', 'BJBJTZ', 'beijing');
INSERT INTO `bc_region` VALUES ('QY010', '北京市', '北京市', '顺义区', '110113', 'BJBJSY', 'beijing');
INSERT INTO `bc_region` VALUES ('QY011', '北京市', '北京市', '昌平区', '110114', 'BJBJCP', 'beijing');
INSERT INTO `bc_region` VALUES ('QY012', '北京市', '北京市', '大兴区', '110115', 'BJBJDX', 'beijing');
INSERT INTO `bc_region` VALUES ('QY013', '北京市', '北京市', '怀柔区', '110116', 'BJBJHR', 'beijing');
INSERT INTO `bc_region` VALUES ('QY014', '北京市', '北京市', '平谷区', '110117', 'BJBJPG', 'beijing');
INSERT INTO `bc_region` VALUES ('QY015', '北京市', '北京市', '密云县', '110228', 'BJBJMY', 'beijing');
INSERT INTO `bc_region` VALUES ('QY016', '北京市', '北京市', '延庆县', '110229', 'BJBJYQ', 'beijing');
INSERT INTO `bc_region` VALUES ('QY017', '河北省', '石家庄市', '长安区', '130102', 'HBSJZZA', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY018', '河北省', '石家庄市', '桥东区', '130103', 'HBSJZQD', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY019', '河北省', '石家庄市', '桥西区', '130104', 'HBSJZQX', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY020', '河北省', '石家庄市', '新华区', '130105', 'HBSJZXH', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY021', '河北省', '石家庄市', '井陉矿区', '130107', 'HBSJZJXK', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY022', '河北省', '石家庄市', '裕华区', '130108', 'HBSJZYH', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY023', '河北省', '石家庄市', '井陉县', '130121', 'HBSJZJX', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY024', '河北省', '石家庄市', '正定县', '130123', 'HBSJZZD', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY025', '河北省', '石家庄市', '栾城县', '130124', 'HBSJZLC', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY026', '河北省', '石家庄市', '行唐县', '130125', 'HBSJZXT', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY027', '河北省', '石家庄市', '灵寿县', '130126', 'HBSJZLS', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY028', '河北省', '石家庄市', '高邑县', '130127', 'HBSJZGY', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY029', '河北省', '石家庄市', '深泽县', '130128', 'HBSJZSZ', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY030', '河北省', '石家庄市', '赞皇县', '130129', 'HBSJZZH', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY031', '河北省', '石家庄市', '无极县', '130130', 'HBSJZWJ', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY032', '河北省', '石家庄市', '平山县', '130131', 'HBSJZPS', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY033', '河北省', '石家庄市', '元氏县', '130132', 'HBSJZYS', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY034', '河北省', '石家庄市', '赵县', '130133', 'HBSJZZ', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY035', '河北省', '石家庄市', '辛集市', '130181', 'HBSJZXJ', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY036', '河北省', '石家庄市', '藁城市', '130182', 'HBSJZGC', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY037', '河北省', '石家庄市', '晋州市', '130183', 'HBSJZJZ', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY038', '河北省', '石家庄市', '新乐市', '130184', 'HBSJZXL', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY039', '河北省', '石家庄市', '鹿泉市', '130185', 'HBSJZLQ', 'shijiazhuang');
INSERT INTO `bc_region` VALUES ('QY040', '天津市', '天津市', '和平区', '120101', 'TJTJHP', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY041', '天津市', '天津市', '河东区', '120102', 'TJTJHD', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY042', '天津市', '天津市', '河西区', '120103', 'TJTJHX', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY043', '天津市', '天津市', '南开区', '120104', 'TJTJNK', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY044', '天津市', '天津市', '河北区', '120105', 'TJTJHB', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY045', '天津市', '天津市', '红桥区', '120106', 'TJTJHQ', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY046', '天津市', '天津市', '滨海新区', '120116', 'TJTJBHX', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY047', '天津市', '天津市', '东丽区', '120110', 'TJTJDL', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY048', '天津市', '天津市', '西青区', '120111', 'TJTJXQ', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY049', '天津市', '天津市', '津南区', '120112', 'TJTJJN', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY050', '天津市', '天津市', '北辰区', '120113', 'TJTJBC', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY051', '天津市', '天津市', '武清区', '120114', 'TJTJWQ', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY052', '天津市', '天津市', '宝坻区', '120115', 'TJTJBC', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY053', '天津市', '天津市', '宁河县', '120221', 'TJTJNH', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY054', '天津市', '天津市', '静海县', '120223', 'TJTJJH', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY055', '天津市', '天津市', '蓟县', '120225', 'TJTJJ', 'tianjin');
INSERT INTO `bc_region` VALUES ('QY056', '山西省', '太原市', '小店区', '140105', 'SXTYXD', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY057', '山西省', '太原市', '迎泽区', '140106', 'SXTYYZ', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY058', '山西省', '太原市', '杏花岭区', '140107', 'SXTYXHL', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY059', '山西省', '太原市', '尖草坪区', '140108', 'SXTYJCP', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY060', '山西省', '太原市', '万柏林区', '140109', 'SXTYWBL', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY061', '山西省', '太原市', '晋源区', '140110', 'SXTYJY', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY062', '山西省', '太原市', '清徐县', '140121', 'SXTYQX', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY063', '山西省', '太原市', '阳曲县', '140122', 'SXTYYQ', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY064', '山西省', '太原市', '娄烦县', '140123', 'SXTYLF', 'taiyuan');
INSERT INTO `bc_region` VALUES ('QY065', '山西省', '太原市', '古交市', '140181', 'SXTYGJ', 'taiyuan');

-- ----------------------------
-- Table structure for bc_staff
-- ----------------------------
DROP TABLE IF EXISTS `bc_staff`;
CREATE TABLE `bc_staff` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `haspda` char(1) DEFAULT NULL,
  `deltag` char(1) DEFAULT NULL,
  `station` varchar(40) DEFAULT NULL,
  `standard` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bc_staff
-- ----------------------------
INSERT INTO `bc_staff` VALUES ('402882a95fc8e816015fc8eae3a00000', '张三', '15920020534', '1', '1', '运输一公司', '自提');
INSERT INTO `bc_staff` VALUES ('402882a95fc8e816015fc8f1962c0001', '李四', '15970412711', '1', '1', '运输二公司', '送货上门');
INSERT INTO `bc_staff` VALUES ('402882a95fccdd08015fcd43ec4a0000', '王五', '15920020534', '0', '0', '运输三公司', '自提');
INSERT INTO `bc_staff` VALUES ('402882a95fccdd08015fcd7b3f8a0001', '许山', '15345678945', '1', '0', '运输一公司', '自提');
INSERT INTO `bc_staff` VALUES ('402882a95fccdd08015fcd7d2d7d0002', '赵强', '15920020534', '1', '0', '运输一公司', '送货上门');
INSERT INTO `bc_staff` VALUES ('402882a95fcdb972015fcdbb98260000', '肖海娴', '15920020534', '1', '0', '运输一公司', '自提');
INSERT INTO `bc_staff` VALUES ('402882a95fcdb972015fcdcac5bf0001', '许智强', '15920020534', '1', '0', '运输三公司', '送货上门');
INSERT INTO `bc_staff` VALUES ('402882a95fcdfc53015fce0f85670000', '小黑', '15920020534', '1', '0', '运输三公司', '自提');
INSERT INTO `bc_staff` VALUES ('402882a95fcdfc53015fce121ea80001', '小白', '15345678945', '1', '0', '运输一公司', '送货上门');
INSERT INTO `bc_staff` VALUES ('402882a95fcdfc53015fce12a3e70002', '小海', '15920020534', '0', '0', '运输二公司', '自提');
INSERT INTO `bc_staff` VALUES ('402882a95fcdfc53015fce12f9fc0003', '小肖', '15345678945', '1', '0', '运输三公司', '自提');
INSERT INTO `bc_staff` VALUES ('402882a95fcdfc53015fce13d84f0004', '刘山', '15345678945', '1', '0', '运输三公司', '自提');
INSERT INTO `bc_staff` VALUES ('402882a95fcdfc53015fce1528d30005', '许仙', '15920020534', '1', '0', '运输四公司', '自提');
INSERT INTO `bc_staff` VALUES ('402882a95fcdfc53015fce15ca8b0006', '许瑞希', '15920020534', '1', '0', '运输四公司', '送货上门');
INSERT INTO `bc_staff` VALUES ('402882a95fcdfc53015fce1641460007', '曾其', '15970412711', '1', '0', '运输四公司', '自提');
INSERT INTO `bc_staff` VALUES ('402882a95fcdfc53015fce16be820008', '叶文', '15920020534', '1', '0', '运输四公司', '自提');
INSERT INTO `bc_staff` VALUES ('402882a95fcdfc53015fce17235a0009', '叶问', '15345678945', '1', '0', '运输四公司', '送货上门');
INSERT INTO `bc_staff` VALUES ('402882a95fcdfc53015fce1782ed000a', '周杰伦', '15920020534', '1', '0', '运输四公司', '送货上门');
INSERT INTO `bc_staff` VALUES ('402882a95fcdfc53015fce180045000b', '李民浩', '15970412711', '1', '0', '运输四公司', '送货上门');
INSERT INTO `bc_staff` VALUES ('402882a95fcdfc53015fce18af19000c', '谢黄山', '15345678945', '1', '0', '运输一公司', '自提');
INSERT INTO `bc_staff` VALUES ('402882a95fcdfc53015fce1912ab000d', '赵文', '15970412711', '1', '0', '运输四公司', '自提');

-- ----------------------------
-- Table structure for bc_subarea
-- ----------------------------
DROP TABLE IF EXISTS `bc_subarea`;
CREATE TABLE `bc_subarea` (
  `id` varchar(32) NOT NULL,
  `decidedzone_id` varchar(32) DEFAULT NULL,
  `region_id` varchar(32) DEFAULT NULL,
  `addresskey` varchar(100) DEFAULT NULL,
  `startnum` varchar(30) DEFAULT NULL,
  `endnum` varchar(30) DEFAULT NULL,
  `single` char(1) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlndbc8oldgbg3k1x63n3n6t7n` (`decidedzone_id`),
  KEY `FKcjwxm19mx5njn3xyqgqp431mb` (`region_id`),
  CONSTRAINT `FK_area_decidedzone` FOREIGN KEY (`decidedzone_id`) REFERENCES `bc_decidedzone` (`id`),
  CONSTRAINT `FK_area_region` FOREIGN KEY (`region_id`) REFERENCES `bc_region` (`id`),
  CONSTRAINT `FKcjwxm19mx5njn3xyqgqp431mb` FOREIGN KEY (`region_id`) REFERENCES `bc_region` (`id`),
  CONSTRAINT `FKlndbc8oldgbg3k1x63n3n6t7n` FOREIGN KEY (`decidedzone_id`) REFERENCES `bc_decidedzone` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bc_subarea
-- ----------------------------
INSERT INTO `bc_subarea` VALUES ('402882a95fe104f6015fe10e388b0000', 'qwe', 'QY002', '123', '123 ', '123 ', '0', '132 ');
INSERT INTO `bc_subarea` VALUES ('402882a95fe19197015fe1b647840001', '126', 'JX001', 'a说的', 'as', '爱上', '0', '阿萨德');
INSERT INTO `bc_subarea` VALUES ('402882bc60007dd6016000babb930000', '125', 'JX001', '上顿度镇', '1', '123', '1', '泽源华府');
INSERT INTO `bc_subarea` VALUES ('402882bc60201efb01602024a9ad0000', '123', 'QY001', '北京市', '1', '100', '1', '亲王府');
INSERT INTO `bc_subarea` VALUES ('402882bc60201efb01602026334a0002', '123', 'QY026', '河北', '2', '109', '1', '石家庄');

-- ----------------------------
-- Table structure for qp_noticebill
-- ----------------------------
DROP TABLE IF EXISTS `qp_noticebill`;
CREATE TABLE `qp_noticebill` (
  `id` varchar(32) NOT NULL,
  `staff_id` varchar(32) DEFAULT NULL,
  `customer_id` varchar(32) DEFAULT NULL,
  `customer_name` varchar(20) DEFAULT NULL,
  `delegater` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `pickaddress` varchar(200) DEFAULT NULL,
  `arrivecity` varchar(20) DEFAULT NULL,
  `product` varchar(20) DEFAULT NULL,
  `pickdate` date DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `volume` varchar(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `ordertype` varchar(20) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl5j3pm969oy1qdc1318jrmbgt` (`user_id`),
  KEY `FKhmbqr6qlg0uets978w5xshler` (`staff_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`staff_id`) REFERENCES `bc_staff` (`id`),
  CONSTRAINT `FKhmbqr6qlg0uets978w5xshler` FOREIGN KEY (`staff_id`) REFERENCES `bc_staff` (`id`),
  CONSTRAINT `FKl5j3pm969oy1qdc1318jrmbgt` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `abc` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qp_noticebill
-- ----------------------------
INSERT INTO `qp_noticebill` VALUES ('402882bc60391c480160391df1790000', '402882a95fc8e816015fc8eae3a00000', '1', '张三', '张三', '13811111111', '北京市西城区长安街100号', '', '', null, null, null, '', '', '自动分单', null);

-- ----------------------------
-- Table structure for qp_workbill
-- ----------------------------
DROP TABLE IF EXISTS `qp_workbill`;
CREATE TABLE `qp_workbill` (
  `id` varchar(32) NOT NULL,
  `noticebill_id` varchar(32) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `pickstate` varchar(20) DEFAULT NULL,
  `buildtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `attachbilltimes` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `staff_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKggojlcovnpimuukxcueb18apt` (`noticebill_id`),
  KEY `FK55ckcnjyvwinnnniu5jpelg7y` (`staff_id`),
  CONSTRAINT `FK55ckcnjyvwinnnniu5jpelg7y` FOREIGN KEY (`staff_id`) REFERENCES `bc_staff` (`id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`staff_id`) REFERENCES `bc_staff` (`id`),
  CONSTRAINT `FK_workbill_noticebill_fk` FOREIGN KEY (`noticebill_id`) REFERENCES `qp_noticebill` (`id`),
  CONSTRAINT `FKggojlcovnpimuukxcueb18apt` FOREIGN KEY (`noticebill_id`) REFERENCES `qp_noticebill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qp_workbill
-- ----------------------------
INSERT INTO `qp_workbill` VALUES ('402882bc60391c480160391e0e9e0001', '402882bc60391c480160391df1790000', '新单', '未取件', '2017-12-09 10:32:39', '0', '', '402882a95fc8e816015fc8eae3a00000');

-- ----------------------------
-- Table structure for qp_workordermanage
-- ----------------------------
DROP TABLE IF EXISTS `qp_workordermanage`;
CREATE TABLE `qp_workordermanage` (
  `id` varchar(32) NOT NULL,
  `arrivecity` varchar(20) DEFAULT NULL,
  `product` varchar(20) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `floadreqr` varchar(255) DEFAULT NULL,
  `prodtimelimit` varchar(40) DEFAULT NULL,
  `prodtype` varchar(40) DEFAULT NULL,
  `sendername` varchar(20) DEFAULT NULL,
  `senderphone` varchar(20) DEFAULT NULL,
  `senderaddr` varchar(200) DEFAULT NULL,
  `receivername` varchar(20) DEFAULT NULL,
  `receiverphone` varchar(20) DEFAULT NULL,
  `receiveraddr` varchar(200) DEFAULT NULL,
  `feeitemnum` int(11) DEFAULT NULL,
  `actlweit` double DEFAULT NULL,
  `vol` varchar(20) DEFAULT NULL,
  `managerCheck` varchar(1) DEFAULT NULL,
  `updatetime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qp_workordermanage
-- ----------------------------
INSERT INTO `qp_workordermanage` VALUES ('1', '1', '1', '1', '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `qp_workordermanage` VALUES ('12', '1', '1', '1', '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `qp_workordermanage` VALUES ('13', '1', '1', '1', '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for role_function
-- ----------------------------
DROP TABLE IF EXISTS `role_function`;
CREATE TABLE `role_function` (
  `role_id` varchar(32) NOT NULL,
  `function_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`function_id`),
  KEY `FK5f8riddotqjpm9vly0b8c5nmf` (`function_id`),
  CONSTRAINT `FK10qo908yd9evkyb40vf88og85` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`),
  CONSTRAINT `FK5f8riddotqjpm9vly0b8c5nmf` FOREIGN KEY (`function_id`) REFERENCES `auth_function` (`id`),
  CONSTRAINT `FK_Reference_2x` FOREIGN KEY (`function_id`) REFERENCES `auth_function` (`id`),
  CONSTRAINT `FK_Reference_3x` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_function
-- ----------------------------
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '11');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584b69f30001', '11');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '112');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584b69f30001', '112');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '113');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584b69f30001', '113');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '114');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584b69f30001', '114');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '115');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584b69f30001', '115');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '116');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584b69f30001', '116');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '12');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584b69f30001', '12');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '121');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584b69f30001', '121');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '122');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584b69f30001', '122');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '124');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584b69f30001', '124');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '13');
INSERT INTO `role_function` VALUES ('402882bc6067029d016067076a710000', '13');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '131');
INSERT INTO `role_function` VALUES ('402882bc6067029d016067076a710000', '131');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '132');
INSERT INTO `role_function` VALUES ('402882bc6067029d016067076a710000', '132');
INSERT INTO `role_function` VALUES ('402882bc6058364d01605838ff200000', '14');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '14');
INSERT INTO `role_function` VALUES ('402882bc6058364d01605838ff200000', '141');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '141');
INSERT INTO `role_function` VALUES ('402882bc6058364d01605838ff200000', '142');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '142');
INSERT INTO `role_function` VALUES ('402882bc6058364d01605838ff200000', '143');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '143');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584a59890000', '8a7e843355a4392d0155a43aa7150000');
INSERT INTO `role_function` VALUES ('402882bc60583f760160584b69f30001', '8a7e843355a4392d0155a43aa7150000');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `station` varchar(40) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('402882bc6058a9f0016058ac1c0d0000', 'D14958', '525770', '3500', '1995-12-16', '男', '分公司', '15920020534', null);
INSERT INTO `t_user` VALUES ('402882bc6058c6ac016058cb238c0000', 'admin', '123456', '5000', '1995-12-12', '女', '总公司', '15970412711', null);
INSERT INTO `t_user` VALUES ('402882bc6067029d016067094abb0001', 'XuZQCare', '525770', '4500', '2017-12-06', '女', '厅点', '15920020534', null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKqqlqhas35obkljn18mrh6mmms` (`role_id`),
  CONSTRAINT `FK_Reference_4x` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_Reference_5x` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`),
  CONSTRAINT `FKeqon9sx5vssprq67dxm3s7ump` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FKqqlqhas35obkljn18mrh6mmms` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('402882bc6058a9f0016058ac1c0d0000', '402882bc60583f760160584a59890000');
INSERT INTO `user_role` VALUES ('402882bc6058c6ac016058cb238c0000', '402882bc60583f760160584a59890000');
INSERT INTO `user_role` VALUES ('402882bc6058c6ac016058cb238c0000', '402882bc60583f760160584b69f30001');
INSERT INTO `user_role` VALUES ('402882bc6067029d016067094abb0001', '402882bc6067029d016067076a710000');
