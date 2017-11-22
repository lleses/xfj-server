/*
 Navicat MySQL Data Transfer

 Source Server         : didi
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : dlj

 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 04/05/2017 19:03:55 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_baseset`
-- ----------------------------
DROP TABLE IF EXISTS `t_baseset`;
CREATE TABLE `t_baseset` (
  `keyword` varchar(50) NOT NULL DEFAULT '' COMMENT '关键字标识',
  `value` varchar(255) DEFAULT NULL COMMENT '相应的值'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='基本信息表';

-- ----------------------------
--  Table structure for `t_bcategory`
-- ----------------------------
DROP TABLE IF EXISTS `t_bcategory`;
CREATE TABLE `t_bcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '建筑类别',
  `townId` int(11) DEFAULT NULL COMMENT '所属镇区',
  `departmentId` int(11) DEFAULT NULL COMMENT '所属社区',
  `location` varchar(255) DEFAULT NULL COMMENT '位置',
  `adsenseName` varchar(50) DEFAULT NULL COMMENT '站长姓名',
  `dutyCalls` varchar(50) DEFAULT NULL COMMENT '值班电话',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `addIp` varchar(50) DEFAULT NULL COMMENT '添加的IP',
  `userId` int(11) DEFAULT NULL COMMENT '添加者ID',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=267 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `t_building`
-- ----------------------------
DROP TABLE IF EXISTS `t_building`;
CREATE TABLE `t_building` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stationId` int(11) DEFAULT NULL COMMENT '所属类别',
  `name` varchar(50) DEFAULT NULL COMMENT '建筑物名称',
  `birthdate` date DEFAULT NULL COMMENT '登记日期',
  `obligation` varchar(100) DEFAULT NULL COMMENT '建筑负责人',
  `workTime` date DEFAULT NULL COMMENT '变更时间',
  `telphone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `isTrain` enum('0','1') DEFAULT NULL COMMENT '消防培训0无1有',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `modTime` datetime DEFAULT NULL COMMENT '修改时间',
  `addIp` varchar(50) DEFAULT NULL COMMENT '添加的IP',
  `userId` int(11) DEFAULT NULL COMMENT '添加者ID',
  `townId` int(11) DEFAULT NULL COMMENT '所属镇区',
  `departmentId` int(11) DEFAULT NULL COMMENT '所属社区',
  `bimg` varchar(250) NOT NULL COMMENT '图片',
  `message` text NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `stationId` (`stationId`,`departmentId`,`townId`,`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=1829 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `t_car`
-- ----------------------------
DROP TABLE IF EXISTS `t_car`;
CREATE TABLE `t_car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stationId` int(11) DEFAULT NULL COMMENT '所属消防站',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `number` varchar(50) DEFAULT NULL COMMENT '车辆号牌',
  `engineCode` varchar(50) DEFAULT NULL COMMENT '发动机号',
  `purchaseTime` date DEFAULT NULL COMMENT '购置时间',
  `staffedTime` date DEFAULT NULL COMMENT '装备时间',
  `resperson` varchar(50) DEFAULT NULL COMMENT '负责人',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `addIp` varchar(50) DEFAULT NULL COMMENT '添加IP',
  `userId` int(11) DEFAULT NULL COMMENT '添加者ID',
  `townId` int(11) DEFAULT NULL COMMENT '所属镇区',
  `departmentId` int(11) DEFAULT NULL COMMENT '所属社区',
  PRIMARY KEY (`id`),
  KEY `stationId` (`stationId`,`departmentId`,`townId`,`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=263 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='车辆信息表';

-- ----------------------------
--  Table structure for `t_delunit`
-- ----------------------------
DROP TABLE IF EXISTS `t_delunit`;
CREATE TABLE `t_delunit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unitId` int(11) DEFAULT NULL COMMENT '单位ID对应t_unit表id',
  `unitName` varchar(100) NOT NULL DEFAULT '' COMMENT '单位名称',
  `address` varchar(255) DEFAULT NULL COMMENT '单位地址',
  `departId` int(11) DEFAULT NULL COMMENT '社区/村ID，对应t_department',
  `departName` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `townId` int(11) DEFAULT NULL COMMENT '镇区ID，对应t_town',
  `townName` varchar(100) DEFAULT NULL COMMENT '镇区名称',
  `duserId` int(11) DEFAULT NULL COMMENT '删除者ID',
  `duserName` varchar(50) DEFAULT NULL COMMENT '删除者姓名',
  `ddepartId` int(11) DEFAULT NULL COMMENT '删除者部门ID',
  `ddepartName` varchar(100) DEFAULT NULL COMMENT '删除者部门名称',
  `dtime` datetime DEFAULT NULL COMMENT '删除时间',
  `dip` varchar(50) DEFAULT NULL COMMENT '删除时的IP',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=195808 DEFAULT CHARSET=utf8 COMMENT='记录已删除的单位';

-- ----------------------------
--  Table structure for `t_department`
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `charger` varchar(50) DEFAULT NULL COMMENT '责任人',
  `telphone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `townId` varchar(255) DEFAULT NULL COMMENT '对应镇区ID',
  `kind` int(11) DEFAULT NULL COMMENT '所属类型',
  `flag` enum('1','2','3') DEFAULT '1' COMMENT '部门标识 1市级部门2镇级部门3社区部门',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `modTime` datetime DEFAULT NULL COMMENT '修改时间',
  `userId` varchar(255) DEFAULT NULL COMMENT '添加者ID',
  `addIp` varchar(50) DEFAULT NULL COMMENT '添加IP',
  `percent` int(11) DEFAULT NULL COMMENT '任务百分比',
  PRIMARY KEY (`id`),
  KEY `townId` (`townId`)
) ENGINE=MyISAM AUTO_INCREMENT=1381 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门表';

-- ----------------------------
--  Table structure for `t_equipment`
-- ----------------------------
DROP TABLE IF EXISTS `t_equipment`;
CREATE TABLE `t_equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stationId` int(11) DEFAULT NULL COMMENT '所属消防站',
  `name` varchar(50) DEFAULT NULL COMMENT '器材名称',
  `price` varchar(50) DEFAULT NULL COMMENT '单价',
  `vehicleAmount` varchar(50) DEFAULT NULL COMMENT '车载数量',
  `stockAmount` varchar(50) DEFAULT NULL COMMENT '库存数量',
  `recordTime` date DEFAULT NULL COMMENT '登记日期',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `modTime` datetime DEFAULT NULL COMMENT '修改时间',
  `addIp` varchar(50) DEFAULT NULL COMMENT '添加IP',
  `userId` int(11) DEFAULT NULL COMMENT '添加者ID',
  `townId` int(11) DEFAULT NULL COMMENT '所属镇区',
  `departmentId` int(11) DEFAULT NULL COMMENT '所属社区',
  PRIMARY KEY (`id`),
  KEY `stationId` (`stationId`,`departmentId`,`townId`,`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=2628 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='器材信息表';

-- ----------------------------
--  Table structure for `t_joinaction`
-- ----------------------------
DROP TABLE IF EXISTS `t_joinaction`;
CREATE TABLE `t_joinaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade` enum('1','2') DEFAULT NULL COMMENT '1市级的联合行动2镇级的联合行动',
  `townId` int(11) DEFAULT NULL COMMENT '镇ID',
  `townName` varchar(50) DEFAULT NULL COMMENT '镇名称',
  `number` varchar(50) DEFAULT NULL COMMENT '行动编号',
  `name` varchar(100) DEFAULT NULL COMMENT '行动名称',
  `departmentId` int(11) DEFAULT NULL COMMENT '牵头部门ID',
  `departmentName` varchar(50) DEFAULT NULL COMMENT '牵头部门名称',
  `actTime` datetime DEFAULT NULL COMMENT '行动时间',
  `content` text COMMENT '行动内容',
  `resultOption` text COMMENT '行动处理结果',
  `discontinueOption` text COMMENT '行动中止原因',
  `flag` enum('0','1','2','3') DEFAULT NULL COMMENT '0未提交1完成2已提3中止',
  `xunchaId` int(11) DEFAULT NULL COMMENT '巡查ID',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `addIp` varchar(50) DEFAULT NULL COMMENT '添加的IP',
  `userId` varchar(255) DEFAULT NULL COMMENT '添加者ID',
  `modTime` datetime DEFAULT NULL COMMENT '修改时间',
  `ischeck` enum('0','1') DEFAULT '0' COMMENT '0未审1已审',
  PRIMARY KEY (`id`),
  KEY `grade` (`grade`),
  KEY `townId` (`townId`),
  KEY `departmentId` (`departmentId`),
  KEY `flag` (`flag`),
  KEY `ischeck` (`ischeck`)
) ENGINE=MyISAM AUTO_INCREMENT=1035 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='联合行动表';

-- ----------------------------
--  Table structure for `t_joindepartment`
-- ----------------------------
DROP TABLE IF EXISTS `t_joindepartment`;
CREATE TABLE `t_joindepartment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `joinactionId` int(11) DEFAULT NULL COMMENT '联合行动的ID',
  `departmentId` int(11) DEFAULT NULL COMMENT '参与部门的ID',
  `departmentName` varchar(50) DEFAULT NULL COMMENT '参与部门名称',
  `gobackOption` text COMMENT '不参与的意见',
  `receptOption` text COMMENT '接收意见',
  `flag` enum('1','2','3') DEFAULT NULL COMMENT '1参加2收到3不参加',
  `modTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `joinactionId` (`joinactionId`)
) ENGINE=MyISAM AUTO_INCREMENT=10339 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='参与联合行动的部门';

-- ----------------------------
--  Table structure for `t_jointip`
-- ----------------------------
DROP TABLE IF EXISTS `t_jointip`;
CREATE TABLE `t_jointip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actionId` int(11) NOT NULL DEFAULT '0' COMMENT '对应表t_joinaction',
  `content` varchar(255) DEFAULT NULL COMMENT '提示内容',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `isRead` enum('0','1') NOT NULL DEFAULT '0' COMMENT '0未读1已读',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1020 DEFAULT CHARSET=utf8 COMMENT='联合行动的提示表';

-- ----------------------------
--  Table structure for `t_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `townId` int(11) DEFAULT NULL COMMENT '镇区ID 对应t_town表id',
  `departId` varchar(255) DEFAULT NULL COMMENT '社区/村ID，对应t_department',
  `userId` int(11) DEFAULT NULL COMMENT '添加者ID',
  `num` int(11) DEFAULT NULL COMMENT '登陆次数',
  `loginTime` date DEFAULT NULL COMMENT '登陆日期',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=516955 DEFAULT CHARSET=utf8 COMMENT='用户登陆日志';

-- ----------------------------
--  Table structure for `t_person`
-- ----------------------------
DROP TABLE IF EXISTS `t_person`;
CREATE TABLE `t_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stationId` int(11) DEFAULT NULL COMMENT '所属消防站',
  `name` varchar(50) DEFAULT NULL COMMENT '人员名称',
  `birthdate` date DEFAULT NULL COMMENT '出生日期',
  `obligation` varchar(100) DEFAULT NULL COMMENT '职务',
  `workTime` date DEFAULT NULL COMMENT '到站工作时间',
  `telphone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `isTrain` enum('0','1') DEFAULT NULL COMMENT '消防培训0无1有',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `modTime` datetime DEFAULT NULL COMMENT '修改时间',
  `addIp` varchar(50) DEFAULT NULL COMMENT '添加的IP',
  `userId` int(11) DEFAULT NULL COMMENT '添加者ID',
  `townId` int(11) DEFAULT NULL COMMENT '所属镇区',
  `departmentId` int(11) DEFAULT NULL COMMENT '所属社区',
  PRIMARY KEY (`id`),
  KEY `stationId` (`stationId`,`departmentId`,`townId`,`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=1809 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='人员信息表';

-- ----------------------------
--  Table structure for `t_police`
-- ----------------------------
DROP TABLE IF EXISTS `t_police`;
CREATE TABLE `t_police` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unitname` varchar(50) DEFAULT NULL COMMENT '单位名称/户主名',
  `townId` int(11) DEFAULT NULL COMMENT '所属镇区',
  `departmentId` int(11) DEFAULT NULL COMMENT '所属社区',
  `location` varchar(200) DEFAULT NULL COMMENT '事件地点',
  `rescueFolk` varchar(255) DEFAULT NULL COMMENT '抢救人员',
  `protectedProperty` varchar(50) DEFAULT NULL COMMENT '保护财产(万元)',
  `recusedProperty` varchar(50) DEFAULT NULL COMMENT '抢救财产(万元)',
  `evacuatePerson` varchar(50) DEFAULT NULL COMMENT '疏散人员(人)',
  `recusedPerson` varchar(50) DEFAULT NULL COMMENT '抢救人员(人)',
  `stationId` int(11) DEFAULT NULL COMMENT '出动消防站',
  `form` enum('1','2') DEFAULT NULL COMMENT '参战形式1主战2增援',
  `reTime` datetime DEFAULT NULL COMMENT '接警时间',
  `cdTime` datetime DEFAULT NULL COMMENT '出动时间',
  `dcTime` datetime DEFAULT NULL COMMENT '到场时间',
  `handleState` enum('1','2','3','4') DEFAULT NULL COMMENT '处置情况1到场处置2虚警3假警4中途返回',
  `startTime` datetime DEFAULT NULL COMMENT '作战开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '作战结束时间',
  `distance` varchar(50) DEFAULT NULL COMMENT '到现场距离',
  `personNum` int(11) DEFAULT NULL COMMENT '参战投入人员',
  `carNum` int(11) DEFAULT NULL COMMENT '参战投入车辆（辆）',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `modTime` varchar(255) DEFAULT NULL COMMENT '修改时间',
  `addIp` varchar(50) DEFAULT NULL COMMENT '添加IP',
  `userId` int(11) DEFAULT NULL COMMENT '添加者ID',
  PRIMARY KEY (`id`),
  KEY `townId` (`townId`,`departmentId`)
) ENGINE=MyISAM AUTO_INCREMENT=170 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='接处警表';

-- ----------------------------
--  Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '角色名称',
  `grade` enum('1','2','3') DEFAULT '1' COMMENT '级别1市级2镇级3社区',
  `rights` varchar(300) DEFAULT NULL COMMENT '权限',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `userId` int(11) DEFAULT NULL COMMENT '添加者ID',
  `addIp` varchar(50) DEFAULT NULL COMMENT '添加的IP',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
--  Table structure for `t_station`
-- ----------------------------
DROP TABLE IF EXISTS `t_station`;
CREATE TABLE `t_station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '消防站名称',
  `townId` int(11) DEFAULT NULL COMMENT '所属镇区',
  `departmentId` int(11) DEFAULT NULL COMMENT '所属社区',
  `location` varchar(255) DEFAULT NULL COMMENT '位置',
  `adsenseName` varchar(50) DEFAULT NULL COMMENT '站长姓名',
  `dutyCalls` varchar(50) DEFAULT NULL COMMENT '值班电话',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `addIp` varchar(50) DEFAULT NULL COMMENT '添加的IP',
  `userId` int(11) DEFAULT NULL COMMENT '添加者ID',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=267 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='消防站表';

-- ----------------------------
--  Table structure for `t_taskcty`
-- ----------------------------
DROP TABLE IF EXISTS `t_taskcty`;
CREATE TABLE `t_taskcty` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `year` varchar(10) DEFAULT NULL COMMENT '年份',
  `month` varchar(10) DEFAULT NULL COMMENT '开始月份',
  `ten` enum('1','2','3') NOT NULL DEFAULT '1' COMMENT '旬1上旬2中旬3下旬',
  `townId` int(11) DEFAULT NULL COMMENT '镇区ID 对应t_town表id',
  `departmentId` int(11) DEFAULT NULL COMMENT '社区ID 对应t_department表id',
  `xunchaPersonId` int(11) DEFAULT NULL COMMENT '巡查员的ID',
  `unitId` int(11) DEFAULT NULL COMMENT '单位ID对应t_unit表id',
  `unitName` varchar(100) DEFAULT NULL COMMENT '单位名称',
  `num` int(11) DEFAULT NULL COMMENT '任务数',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `addIp` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `userId` int(11) DEFAULT NULL COMMENT '用户ID',
  `source` enum('1','2') NOT NULL DEFAULT '1' COMMENT '任务的来源1系统下发2人员转移',
  `userName` varchar(50) DEFAULT NULL COMMENT '转移人员姓名',
  `depPercent` int(1) DEFAULT NULL COMMENT '此时村/社区任务的百分比',
  PRIMARY KEY (`id`),
  KEY `year` (`year`),
  KEY `month` (`month`),
  KEY `ten` (`ten`),
  KEY `townId` (`townId`),
  KEY `departmentId` (`departmentId`),
  KEY `xunchaPersonId` (`xunchaPersonId`),
  KEY `unitId` (`unitId`)
) ENGINE=MyISAM AUTO_INCREMENT=2644878 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='社区巡查员任务数表';

-- ----------------------------
--  Table structure for `t_taskdep`
-- ----------------------------
DROP TABLE IF EXISTS `t_taskdep`;
CREATE TABLE `t_taskdep` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` varchar(10) DEFAULT NULL COMMENT '年份',
  `month` varchar(10) DEFAULT NULL COMMENT '开始月份',
  `townId` int(11) DEFAULT NULL COMMENT '镇区ID 对应t_town表id',
  `departmentId` int(11) DEFAULT NULL COMMENT '社区ID 对应t_department表id',
  `num` int(11) DEFAULT '0' COMMENT '任务数',
  `finishnum` int(11) DEFAULT '0' COMMENT '本月完成的任务数',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `year` (`year`),
  KEY `month` (`month`),
  KEY `townId` (`townId`),
  KEY `departmentId` (`departmentId`)
) ENGINE=MyISAM AUTO_INCREMENT=8926 DEFAULT CHARSET=utf8 COMMENT='部门的抽查任务';

-- ----------------------------
--  Table structure for `t_town`
-- ----------------------------
DROP TABLE IF EXISTS `t_town`;
CREATE TABLE `t_town` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '镇区名称',
  `charger` varchar(50) DEFAULT NULL COMMENT '责任人',
  `telphone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `modTime` datetime DEFAULT NULL COMMENT '修改时间',
  `userId` varchar(255) DEFAULT NULL COMMENT '添加人员ID',
  `addIp` varchar(50) DEFAULT NULL COMMENT '信息添加IP',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='镇区信息表';

-- ----------------------------
--  Table structure for `t_unit`
-- ----------------------------
DROP TABLE IF EXISTS `t_unit`;
CREATE TABLE `t_unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '单位名称',
  `departmentId` int(11) NOT NULL DEFAULT '0' COMMENT '所属社区',
  `townId` int(11) DEFAULT NULL COMMENT '所属镇区',
  `code` varchar(100) DEFAULT NULL COMMENT '单位编码',
  `license` varchar(100) DEFAULT NULL COMMENT '营业执照',
  `address` varchar(255) DEFAULT NULL COMMENT '单位地址',
  `type` enum('11','12','13','14','15','16','21','22','23','31','32','33','34','35') NOT NULL DEFAULT '16' COMMENT '单位类型 11酒店类12教育类13市场类14大娱乐场所15出租屋16其他21小作坊22小档口23小娱乐场所31工地类32卫生类33消防重点单位34工厂企业35十五类场所',
  `safedLinkman` varchar(50) DEFAULT NULL COMMENT '消防安全责任人',
  `safedTelphone` varchar(50) DEFAULT NULL COMMENT '消防安全责任人联系电话',
  `manageLinkman` varchar(50) DEFAULT NULL COMMENT '消防安全管理人',
  `manageTelphone` varchar(50) DEFAULT NULL COMMENT '消防安全管理人联系电话',
  `stationId` int(11) unsigned DEFAULT NULL COMMENT '单位归属建筑物',
  `area` varchar(50) DEFAULT NULL COMMENT '建筑面积',
  `crewSize` int(11) DEFAULT NULL COMMENT '培训人员数量',
  `buildingsLayer` int(11) DEFAULT NULL COMMENT '建筑物层数',
  `meno` text COMMENT '备注',
  `belongUserId` int(11) DEFAULT NULL COMMENT '单位归属巡查员ID',
  `starLevel` int(11) DEFAULT '1' COMMENT '单位星级1绿色2黄色3红色',
  `isxc` enum('0','1') NOT NULL DEFAULT '0' COMMENT '是否巡查流转过程中0否1是',
  `longitude` varchar(50) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(50) DEFAULT NULL COMMENT '纬度',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `modTime` datetime DEFAULT NULL COMMENT '修改时间',
  `addIp` varchar(255) DEFAULT NULL COMMENT '添加时的IP地址',
  `userId` int(11) DEFAULT NULL COMMENT '添加者ID',
  `delflag` enum('1','2') DEFAULT NULL COMMENT '申请删除标志1申请2驳回',
  `delReason` text COMMENT '删除原因',
  `rejectReason` text COMMENT '驳回原因',
  `surveyLevel` int(11) DEFAULT '1' COMMENT '抽查结果1绿色2红色',
  `nameId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `departmentId` (`departmentId`),
  KEY `townId` (`townId`),
  KEY `type` (`type`),
  KEY `belongUserId` (`belongUserId`),
  KEY `userId` (`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=581699 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='巡查单位表';

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
  `username` varchar(100) DEFAULT NULL COMMENT '帐户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `departmentId` int(11) DEFAULT NULL COMMENT '所属部门',
  `townId` int(11) DEFAULT NULL COMMENT '所属镇区',
  `roleId` int(11) DEFAULT NULL COMMENT '用户角色',
  `isLeader` enum('0','1') NOT NULL DEFAULT '0' COMMENT '是否部门负责人1是0否',
  `telphone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `meno` varchar(255) DEFAULT NULL COMMENT '备注',
  `flag` enum('1','2','3') DEFAULT '1' COMMENT '1市级2镇级3社区',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `userId` int(11) DEFAULT NULL COMMENT '添加者ID',
  `addIp` varchar(50) DEFAULT NULL COMMENT '添加IP',
  `loginTime` datetime DEFAULT NULL COMMENT '登陆时间',
  `isFreeze` enum('1','2') DEFAULT '1' COMMENT '1正常2冻结',
  `modpwd_time` datetime DEFAULT NULL,
  `ddepartmentId` varchar(255) NOT NULL DEFAULT '0',
  `ttownId` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8038 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
--  Table structure for `t_work`
-- ----------------------------
DROP TABLE IF EXISTS `t_work`;
CREATE TABLE `t_work` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `grade` enum('1','2') DEFAULT NULL COMMENT '1市级发布镇级接收2镇级发布社区接收',
  `departmentId` int(11) DEFAULT NULL COMMENT '发布任务的部门ID',
  `departmentName` varchar(50) DEFAULT NULL COMMENT '发布任务的部门名称',
  `title` varchar(200) NOT NULL DEFAULT '' COMMENT '工作标题',
  `type` enum('1','2') DEFAULT NULL COMMENT '工作类型1交办2督办',
  `content` text COMMENT '工作内容',
  `annex` varchar(100) DEFAULT NULL COMMENT '上传的附件',
  `annexName` varchar(100) DEFAULT NULL COMMENT '上传的附件名',
  `finishTime` datetime DEFAULT NULL COMMENT '完成时间',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `modTime` datetime DEFAULT NULL COMMENT '修改时间',
  `userId` int(11) DEFAULT NULL COMMENT '添加者ID',
  `addIp` varchar(50) DEFAULT NULL COMMENT '新增的IP',
  `flag` enum('0','1','2') NOT NULL DEFAULT '0' COMMENT '状态0未提交，1已提交,2已完成',
  PRIMARY KEY (`id`),
  KEY `grade` (`grade`),
  KEY `departmentId` (`departmentId`),
  KEY `flag` (`flag`)
) ENGINE=MyISAM AUTO_INCREMENT=472 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='工作信息表';

-- ----------------------------
--  Table structure for `t_workreceivers`
-- ----------------------------
DROP TABLE IF EXISTS `t_workreceivers`;
CREATE TABLE `t_workreceivers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `workId` int(11) DEFAULT NULL COMMENT '对应表t_work 的id',
  `townId` int(11) DEFAULT NULL COMMENT '所属镇区ID',
  `departmentId` int(11) DEFAULT NULL COMMENT '部门ID',
  `departmentName` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `score` int(11) DEFAULT NULL COMMENT '评分值',
  `result` text COMMENT '工作结果',
  `gobackOption` text COMMENT '回退原因',
  `flag` enum('1','2','3','4','5') DEFAULT NULL COMMENT '1完成任务2收到任务3提交任务4上级回退5已查看',
  `modTime` datetime DEFAULT NULL COMMENT '修改时间',
  `annex` varchar(100) DEFAULT NULL COMMENT '上传的附件',
  `annexName` varchar(100) DEFAULT NULL COMMENT '上传的附件名',
  PRIMARY KEY (`id`),
  KEY `workId` (`workId`),
  KEY `townId` (`townId`),
  KEY `departmentId` (`departmentId`)
) ENGINE=MyISAM AUTO_INCREMENT=7164 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='工作的接收者表';

-- ----------------------------
--  Table structure for `t_xcpoint`
-- ----------------------------
DROP TABLE IF EXISTS `t_xcpoint`;
CREATE TABLE `t_xcpoint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unitId` int(11) NOT NULL DEFAULT '0' COMMENT '单位ID对应t_unit表id',
  `year` varchar(10) NOT NULL DEFAULT '' COMMENT '年份',
  `month` varchar(10) NOT NULL DEFAULT '' COMMENT '开始月份',
  `ten` enum('1','2','3') NOT NULL DEFAULT '1' COMMENT '旬1上旬2中旬3下旬',
  `townId` int(11) NOT NULL DEFAULT '0' COMMENT '镇区ID 对应t_town表id',
  `departmentId` int(11) NOT NULL DEFAULT '0' COMMENT '社区ID 对应t_department表id',
  `userId` int(11) NOT NULL DEFAULT '0' COMMENT '巡查员的ID',
  `hdNum` int(11) DEFAULT '0' COMMENT '隐患数',
  `fhNum` int(11) DEFAULT '0' COMMENT '解决隐患数',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2093575 DEFAULT CHARSET=utf8 COMMENT='统计隐患数表';

-- ----------------------------
--  Table structure for `t_xuncha`
-- ----------------------------
DROP TABLE IF EXISTS `t_xuncha`;
CREATE TABLE `t_xuncha` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `xctype` enum('1','2','3') NOT NULL DEFAULT '1' COMMENT '1人员密集场所巡查2三少场所巡查3工业企业巡查',
  `unitId` int(11) NOT NULL DEFAULT '0' COMMENT '对应t_xcunit的ID，查看巡查时的单位信息',
  `unitName` varchar(100) NOT NULL DEFAULT '' COMMENT '单位名称',
  `departId` varchar(255) DEFAULT NULL COMMENT '社区/村ID，对应t_department',
  `departName` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `townId` int(11) DEFAULT NULL COMMENT '镇区ID，对应t_town',
  `townName` varchar(100) DEFAULT NULL COMMENT '镇区名称',
  `xcTime` date NOT NULL DEFAULT '0000-00-00' COMMENT '巡查时间',
  `oxcTime` date DEFAULT '0000-00-00' COMMENT '起初的巡查时间',
  `xcPerson` varchar(50) DEFAULT '' COMMENT '巡查员姓名',
  `etPerson` varchar(100) DEFAULT NULL COMMENT '陪同人员',
  `xcItem1` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem2` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem3` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem4` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem5` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem6` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem7` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem8` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem9` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem10` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem11` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem12` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `rectDate` date DEFAULT NULL COMMENT '整改日期',
  `meno` text COMMENT '备注',
  `agnDepartId` int(11) DEFAULT NULL COMMENT '分派的镇级部门ID',
  `agnDepartName` varchar(100) DEFAULT NULL COMMENT '分派的镇级部门名称',
  `flag` enum('0','1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19') NOT NULL DEFAULT '0',
  `actionId` int(11) DEFAULT NULL COMMENT '行动ID',
  `ccdepId` int(11) DEFAULT NULL COMMENT '抽查 部门ID',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `modTime` datetime DEFAULT NULL COMMENT '修改时间',
  `userId` int(11) DEFAULT NULL COMMENT '添加者ID',
  `addIp` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  PRIMARY KEY (`id`),
  KEY `unitId` (`unitId`),
  KEY `departId` (`departId`),
  KEY `townId` (`townId`),
  KEY `xcTime` (`xcTime`),
  KEY `agnDepartId` (`agnDepartId`),
  KEY `flag` (`flag`),
  KEY `userId` (`userId`),
  KEY `oxcTime` (`oxcTime`)
) ENGINE=MyISAM AUTO_INCREMENT=2129914 DEFAULT CHARSET=utf8 COMMENT='巡查表';

-- ----------------------------
--  Table structure for `t_xunchaflag`
-- ----------------------------
DROP TABLE IF EXISTS `t_xunchaflag`;
CREATE TABLE `t_xunchaflag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `xunchaId` int(11) NOT NULL DEFAULT '0' COMMENT '巡查情况ID',
  `townId` int(11) DEFAULT NULL COMMENT '镇区ID',
  `townName` varchar(100) DEFAULT NULL COMMENT '镇区名称',
  `departId` int(11) DEFAULT NULL COMMENT '部门ID',
  `departName` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `userId` int(11) DEFAULT NULL COMMENT '记录人员ID',
  `userName` varchar(50) DEFAULT NULL COMMENT '记录人员姓名',
  `option` varchar(255) DEFAULT NULL COMMENT '送审意见',
  `addTime` datetime DEFAULT NULL COMMENT '记录添加时间',
  `addIp` varchar(50) DEFAULT NULL COMMENT '记录添加时的IP',
  `flag` enum('0','1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19') DEFAULT NULL COMMENT '流转标记0未提交1审核通过2复查3提交到社区4社区接收5社区回退6社区陪同巡查员巡查7社区领导回退8提交到镇9分派到部门10镇回退社区11部门分派到人员12部门回退镇13部门人员陪同巡查员巡查14部门人员回退15联合行动16中止17提交联合行动结果18回退联合行动结果19抽查',
  PRIMARY KEY (`id`),
  KEY `xunchaId` (`xunchaId`),
  KEY `flag` (`flag`),
  KEY `townId` (`townId`),
  KEY `departId` (`departId`)
) ENGINE=MyISAM AUTO_INCREMENT=2289907 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='巡查流转情况表';

-- ----------------------------
--  Table structure for `t_xunchaimg`
-- ----------------------------
DROP TABLE IF EXISTS `t_xunchaimg`;
CREATE TABLE `t_xunchaimg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `xunchaId` int(11) DEFAULT '0' COMMENT '对应表t_xcdensely的id',
  `picName` varchar(100) DEFAULT NULL COMMENT '图片名称',
  `intro` varchar(255) DEFAULT NULL COMMENT '说明',
  `longitude` varchar(50) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(50) DEFAULT NULL COMMENT '纬度',
  `flag` enum('1','2') DEFAULT NULL COMMENT '1电脑上传2手机上传',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`),
  KEY `xunchaId` (`xunchaId`)
) ENGINE=MyISAM AUTO_INCREMENT=2034401 DEFAULT CHARSET=utf8 COMMENT='巡查图片表';

-- ----------------------------
--  Table structure for `t_xunchard`
-- ----------------------------
DROP TABLE IF EXISTS `t_xunchard`;
CREATE TABLE `t_xunchard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `xunchaId` int(11) NOT NULL DEFAULT '0' COMMENT '对应表t_xuncha的ID',
  `xctype` enum('1','2','3') NOT NULL DEFAULT '1' COMMENT '1人员密集场所巡查2三少场所巡查3工业企业巡查',
  `unitId` int(11) DEFAULT NULL COMMENT '对应t_xcunit的ID，查看巡查时的单位信息',
  `unitName` varchar(100) NOT NULL DEFAULT '' COMMENT '单位名称',
  `departId` varchar(255) DEFAULT NULL COMMENT '社区/村ID，对应t_department',
  `departName` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `townId` int(11) DEFAULT NULL COMMENT '镇区ID，对应t_town',
  `townName` varchar(100) DEFAULT NULL COMMENT '镇区名称',
  `code` varchar(100) DEFAULT NULL COMMENT '单位编码',
  `license` varchar(100) DEFAULT NULL COMMENT '营业执照',
  `address` varchar(255) DEFAULT NULL COMMENT '单位地址',
  `type` enum('11','12','13','14','15','16','21','22','23','31','32','33','34','35') DEFAULT NULL COMMENT '单位类型 11酒店类12教育类13市场类14大娱乐场所15出租屋16其他21小作坊22小档口23小娱乐场所31工地类32卫生类33消防重点单位34工厂企业35十五类场所',
  `safedLinkman` varchar(50) DEFAULT NULL COMMENT '消防安全责任人',
  `safedTelphone` varchar(50) DEFAULT NULL COMMENT '消防安全责任人联系电话',
  `manageLinkman` varchar(50) DEFAULT NULL COMMENT '消防安全管理人',
  `manageTelphone` varchar(50) DEFAULT NULL COMMENT '消防安全管理人联系电话',
  `area` varchar(50) DEFAULT NULL COMMENT '建筑面积',
  `crewSize` int(11) DEFAULT NULL COMMENT '培训人员数量',
  `buildingsLayer` int(11) DEFAULT NULL COMMENT '建筑物层数',
  `unitMeno` text COMMENT '单位备注',
  `xcTime` date NOT NULL DEFAULT '0000-00-00' COMMENT '巡查时间',
  `oxcTime` date DEFAULT '0000-00-00' COMMENT '起初的巡查时间',
  `xcPerson` varchar(50) NOT NULL DEFAULT '' COMMENT '巡查员姓名',
  `etPerson` varchar(100) DEFAULT NULL COMMENT '陪同人员',
  `xcItem1` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem2` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem3` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem4` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem5` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem6` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem7` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem8` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem9` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem10` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem11` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `xcItem12` enum('1','2') DEFAULT NULL COMMENT '1是2否',
  `rectDate` date DEFAULT NULL COMMENT '整改日期',
  `meno` text COMMENT '备注',
  `flag` enum('0','1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19') DEFAULT NULL COMMENT '流转标记0未提交1审核通过2复查3提交到社区4社区接收5社区回退6社区陪同巡查员巡查7社区领导回退8提交到镇9分派到部门10镇回退社区11部门分派到人员12部门回退镇13部门人员陪同巡查员巡查14部门人员回退15联合行动16中止17提交联合行动结果18回退联合行动结果19抽查',
  `imgIds` varchar(255) DEFAULT NULL COMMENT '图片的ID',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `userId` int(11) DEFAULT NULL COMMENT '记录人员ID',
  `userName` varchar(50) DEFAULT NULL COMMENT '记录人员姓名',
  PRIMARY KEY (`id`),
  KEY `xunchaId` (`xunchaId`,`xctype`,`unitId`,`departId`,`townId`,`flag`,`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=2274435 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='巡查流转情况记录表';

SET FOREIGN_KEY_CHECKS = 1;
