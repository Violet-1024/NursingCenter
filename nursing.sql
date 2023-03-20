-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `eid` int NOT NULL AUTO_INCREMENT COMMENT '员工ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '员工账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '123456' COMMENT '员工密码',
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '员工职位',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '员工性别',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号码',
  `custIDCard` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `jointime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入职时间',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '在职' COMMENT '状态',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10014 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '员工表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (10001, 'admin', 'admin', '管理员', 'admin', NULL, NULL, NULL, NULL, '2022-11-21 21:46:16', NULL, '在职', NULL);
INSERT INTO `employee` VALUES (10002, 'lihong', '123456', '技师', '李红', '女', '17661728083', '543124198001124092', '山东威海', '2022-10-22 18:46:14', '17661728083@163.com', '在职', 42);
INSERT INTO `employee` VALUES (10003, 'zhangming', '123456', '工程师', '张明', '男', '15945073080', '417434199910294651', '辽宁大连', '2022-10-23 13:23:35', '15945073080@163.com', '在职', 23);
INSERT INTO `employee` VALUES (10004, 'lihua', '123456', '管理员', '李华', '男', '13812658702', '326271199504154451', '辽宁沈阳', '2022-10-24 21:46:18', '13812658702@163.com', '在职', 27);
INSERT INTO `employee` VALUES (10005, 'hongmei', '123456', '技师', '红梅', '女', '17651624519', '322371197604123324', '辽宁大连', '2022-10-27 21:46:19', '17651624519@163.com', '在职', 48);
INSERT INTO `employee` VALUES (10006, 'liuhong', '123456', '技师', '刘红', '女', '18796262104', '417434197809313432', '辽宁大连', '2022-10-28 21:46:20', '18796262104@163.com', '在职', 46);
INSERT INTO `employee` VALUES (10007, 'limeng', '123456', '护士', '李萌', '女', '15757294701', '417434199604123502', '辽宁大连', '2022-10-28 21:46:22', '15757294701@163.com', '在职', 26);

SET FOREIGN_KEY_CHECKS = 1;
-- ----------------------------
-- Table structure for outing
-- ----------------------------
DROP TABLE IF EXISTS `outing`;
CREATE TABLE `outing`  (
  `outingid` int NOT NULL AUTO_INCREMENT COMMENT '外出ID',
  `userid` int NULL DEFAULT NULL COMMENT '申请人ID',
  `starttime` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `endingtime` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `companion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '陪同人',
  `cophone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '陪同人手机号',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `createtime` datetime NULL DEFAULT NULL COMMENT '申请时间',
  `dealtime` datetime NULL DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`outingid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10002 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '外出表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of outing
-- ----------------------------
INSERT INTO `outing` VALUES (10001, 10001, '2022-10-13 13:01:41', '2022-10-25 13:01:53', '李梅', '18520095865', '未处理', '李红军', '2022-10-13 12:02:13', '2022-11-26 08:21:50');

SET FOREIGN_KEY_CHECKS = 1;
-- ----------------------------
-- Table structure for bed
-- ----------------------------
DROP TABLE IF EXISTS `bed`;
CREATE TABLE `bed`  (
  `bedID` int NOT NULL COMMENT '床位号',
  `roomID` int NULL DEFAULT NULL COMMENT '房间号',
  `staffID` int NULL DEFAULT NULL COMMENT '员工编号',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '床位状态',
  `bedLevel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '床位级别，如：高级床位、普通床位等',
  `createDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`bedID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bed
-- ----------------------------
INSERT INTO `bed` VALUES (20101, 201, 10006, '空闲', 'VIP床位', '2022-11-01 13:48:47');
INSERT INTO `bed` VALUES (20102, 201, 10006, '空闲', '普通床位', '2022-11-01 13:50:25');
INSERT INTO `bed` VALUES (20103, 201, 10005, '空闲', '普通床位', '2022-11-01 18:14:30');
INSERT INTO `bed` VALUES (20201, 202, 10005, '空闲', '普通床位', '2022-11-10 19:22:46');
INSERT INTO `bed` VALUES (20202, 202, 10006, '空闲', '高级床位', '2022-11-08 19:22:54');
INSERT INTO `bed` VALUES (20203, 202, 10005, '空闲', '普通床位', '2022-11-01 19:23:09');
INSERT INTO `bed` VALUES (50001, 501, 10005, '空闲', 'VIP床位', '2022-11-28 19:26:25');

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for bedrecord
-- ----------------------------
DROP TABLE IF EXISTS `bedrecord`;
CREATE TABLE `bedrecord`  (
  `recordID` int NOT NULL AUTO_INCREMENT,
  `bedID` int NOT NULL,
  `roomID` int NOT NULL,
  `custName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `staffID` int NULL DEFAULT NULL,
  `entryDate` datetime NULL DEFAULT NULL,
  `leaveDate` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`recordID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bedrecord
-- ----------------------------
INSERT INTO `bedrecord` VALUES (100001, 20101, 201, '曹志昊', 10006, '2022-11-04 16:00:55', '2022-11-16 16:01:01');

SET FOREIGN_KEY_CHECKS = 1;


DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `serviceid` int NOT NULL AUTO_INCREMENT COMMENT '日常服务申请id',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '申请人姓名',
  `applicationtime` datetime DEFAULT NULL COMMENT '申请时间',
  `handlingtime` datetime DEFAULT NULL COMMENT '处理时间',
  `reason` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '申请原因',
  `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '处理状态',
  `plan` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '处理方案',
  PRIMARY KEY (`serviceid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;





-- ----------------------------
-- Table structure for abnormal status
-- ----------------------------

DROP TABLE IF EXISTS `abnormal`;
CREATE TABLE `abnormal`  (
                             `ab_id` int NOT NULL auto_increment,
                             `customer_id` varchar(20),
                             `title` varchar(30),
                             `start_date` date NOT NULL,
                             `state` int NOT NULL,
                             `remark` varchar(255),
                             `audi_status` int not null,
                             `publisher` varchar(10),
                             PRIMARY KEY (`ab_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8;

insert into `abnormal` values ('1', '123456789','catalinaGoesOut', '2022-01-01', 1, 'catalina goes out', 0, 'mio');
insert into `abnormal` values ('2', '123456789','CTXGoesOut', '2022-01-02', 1, 'CTX goes out', 0, 'mio');
insert into `abnormal` values ('3', '123456789','hikariGoesOut', '2022-01-03', 1, 'hikari goes out', 0, 'mio');
insert into `abnormal` values ('4', '123456789','riGoesOut', '2022-01-04', 1, 'ri goes out', 0, 'mio');

-- ----------------------------
-- Table structure for abnormal status types
-- ----------------------------

DROP TABLE IF EXISTS `abnormal_type`;
CREATE TABLE `abnormal_type`
(
    `ab_type_id` int NOT NULL auto_increment,
    `state` int NOT NULL,     #
                 `status` int NOT NULL,    # 0 : 禁用
        `title` varchar(20) NOT NULL,
    `publish_time` date NOT NULL ,
    PRIMARY KEY (`ab_type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8;

insert into `abnormal_type` values ('1', 0, 0, 'temporarily', '2022-01-01');
insert into `abnormal_type` values ('2', 1, 0, 'permanently', '2022-01-02');
insert into `abnormal_type` values ('3', 2, 0, 'illness', '2022-01-01');
insert into `abnormal_type` values ('4', 3, 0, 'passed away', '2022-01-04');
insert into `abnormal_type` values ('5', 4, 0, 'lost connection', '2022-01-05');

-- ----------------------------
-- Table structure for evaluation types
-- ----------------------------
DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE `evaluation`
(
    `eva_id` varchar(20) NOT NULL,
    `user_id` varchar(20) NOT NULL,
    `user_name` varchar(20) NOT NULL,
    `nurse` varchar(20) NOT NULL,
    `service_score` varchar(10),
    `serv_time` date,
    `create_time` date NOT NULL ,
    `professional` varchar(20),
    `content` varchar(20),
    PRIMARY KEY (`eva_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8;

insert into `evaluation` values ('1', '12332', 'mio', 'ctx', '7.5', '2022-01-23', '2022-10-19', 'professional', 'very good');

-- ----------------------------
-- Table structure for evaluation disposition
-- ----------------------------
DROP TABLE IF EXISTS `evaluationDis`;
CREATE TABLE `evaluationDis`
(
    `dis_id` varchar(20) NOT NULL,
    `user_id` varchar(20) NOT NULL,
    `user_name` varchar(20) NOT NULL,
    `title` varchar(20) NOT NULL,
    `dish_score` varchar(10),
    `serv_time` date,
    `content` varchar(20),
    PRIMARY KEY (`dis_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8;

insert into `evaluationDis` values ('1', '12332', 'mio', 'ctx', '1', '2022-01-23', 'very good');





-- ----------------------------
-- Table of health rank
-- ----------------------------
DROP TABLE IF EXISTS `healthRank`;
CREATE TABLE `healthRank` (
  `rankid` int NOT NULL AUTO_INCREMENT COMMENT '健康评级ID',
  `performance` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表现',
  `describe` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '状态',

  PRIMARY KEY (`rankid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='健康评级表';

INSERT INTO `healthRank` (`rankid`, `performance`, `describe`, `state`) VALUES ('1', '优秀', '健康状况良好', '1');
INSERT INTO `healthRank` (`rankid`, `performance`, `describe`, `state`) VALUES ('2', '需调养', '健康状况出现些许异常，需调整疗养计划', '1');


-- ----------------------------
-- Table structure for reserveplan
-- ----------------------------
DROP TABLE IF EXISTS `reserveplan`;
CREATE TABLE `reserveplan`  (
  `planID` int NOT NULL AUTO_INCREMENT COMMENT '预案的ID',
  `planName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '预案名称',
  `planType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '预案的类型',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '状态',
  `newTime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `accentType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL,
  PRIMARY KEY (`planID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9998889 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reserveplan
-- ----------------------------
INSERT INTO `reserveplan` VALUES (123, NULL, '医疗', NULL, '2022-11-26 19:36:43', '');
INSERT INTO `reserveplan` VALUES (9002, '预案2', '安全', '停用', '2022-11-26 16:06:10', '66');
INSERT INTO `reserveplan` VALUES (9003, '预案1', '医疗', '停用', '2022-11-25 21:13:47', '777');
INSERT INTO `reserveplan` VALUES (9004, '预案3', '卫生', '启用', '2022-11-23 21:21:26', NULL);
INSERT INTO `reserveplan` VALUES (9005, '999', NULL, NULL, '2022-11-25 22:30:08', '777');
INSERT INTO `reserveplan` VALUES (9006, '888', NULL, NULL, NULL, NULL);
INSERT INTO `reserveplan` VALUES (9007, '777', NULL, NULL, '2022-11-25 21:22:12', NULL);
INSERT INTO `reserveplan` VALUES (123456, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `reserveplan` VALUES (9998888, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  `custName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户姓名',
  `custSex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户性别',
  `custAge` int NULL DEFAULT NULL COMMENT '客户年龄',
  `custLevel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户等级',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户手机号',
  `custIDCard` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户身份证号',
  `custEmail` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户邮箱',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户状态',
  `nursingLevel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '护理级别',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `wallet` double NULL DEFAULT NULL COMMENT '钱包',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (100001, '曹志昊', '男', 23, '高级会员', '19818969698', '412543199901160532', NULL, 'caozhihao', '123456', '在住', '6', NULL, 1000);
INSERT INTO `customer` VALUES (100002, '梁森', '男', 21, '高级会员', '19818965422', '412543200104151540', NULL, 'liangsen', '123456', '离开', '7', NULL, NULL);
INSERT INTO `customer` VALUES (100003, '王森弘', '男', 21, '高级会员', '19818963213', '412543200001041426', NULL, 'wangsenhong', '123456', '暂离', '2', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for nursingcontent
-- ----------------------------
DROP TABLE IF EXISTS `nursingcontent`;
CREATE TABLE `nursingcontent`  (
  `nursingID` int NOT NULL AUTO_INCREMENT COMMENT '护理ID',
  `nursingLevel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '护理级别',
  `nursingName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '护理名称',
  `price` double NULL DEFAULT NULL COMMENT '价格',
  `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`nursingID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of nursingcontent
-- ----------------------------
INSERT INTO `nursingcontent` VALUES (10001, '1', '下棋', 10, '陪顾客下棋', '未启用');
INSERT INTO `nursingcontent` VALUES (10002, '2', '吃饭', 20, '喂顾客吃饭', '已启用');
INSERT INTO `nursingcontent` VALUES (10003, '3', '日常服务', 100, '照顾客户每日的如厕，整理仪容仪表、衣物等级别的接口被忘记报财务部', '已启用');
INSERT INTO `nursingcontent` VALUES (10004, '10', '你说啥就干啥', 200, '你想干嘛就干嘛', '已启用');
INSERT INTO `nursingcontent` VALUES (10005, '2', '测试1', 10, '测试1', '未启用');
INSERT INTO `nursingcontent` VALUES (10006, '4', '测试2', 11, '测试2', '已启用');

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table of health datas
-- ----------------------------
DROP TABLE IF EXISTS `healthdatas`;
CREATE TABLE `healthdatas`  (
  `customerid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` double NULL DEFAULT NULL,
  `performance` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rate` double NULL DEFAULT NULL,
	`sugar` double NULL DEFAULT NULL,
	`oxygen` double NULL DEFAULT NULL,
  PRIMARY KEY (`customerid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;
INSERT INTO `healthdatas` VALUES (3, '张三', '男', '22','优秀',72, 23,21);

-- ----------------------------
-- Table of food list
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food`  (
  `foodid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `protein` double NULL DEFAULT NULL,
  `cho` double NULL DEFAULT NULL,
  `heat` double NULL DEFAULT NULL,
  `fat` double NULL DEFAULT NULL,
	`price` double NULL DEFAULT NULL,

  PRIMARY KEY (`foodid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES (3, '西红柿炒鸡蛋', 5.7, 4.3, 85, 5.4, 12);
INSERT INTO `food` VALUES (5, '蚝油杏鲍菇', 2.3, 12.2, 101.2, 5.7,15);
INSERT INTO `food` VALUES (6, '老母鸡炖栗子', 17.4, 6.3, 224, 14.3,20);


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL COMMENT '管理员id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456', '19818966123');

SET FOREIGN_KEY_CHECKS = 1;



SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orderfood
-- ----------------------------
DROP TABLE IF EXISTS `orderfood`;
CREATE TABLE `orderfood`  (
  `orderID` int NOT NULL AUTO_INCREMENT,
  `id` int NULL DEFAULT NULL,
  `foodid` int NULL DEFAULT NULL,
  `orderTime` date NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL,
  PRIMARY KEY (`orderID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11011 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orderfood
-- ----------------------------
INSERT INTO `orderfood` VALUES (11001, 11011, 1, '2022-11-23', '已付款');
INSERT INTO `orderfood` VALUES (11002, 11022, 2, NULL, '待付款');
INSERT INTO `orderfood` VALUES (11003, 11033, 3, '2022-11-20', NULL);
INSERT INTO `orderfood` VALUES (11004, 11044, 4, NULL, NULL);
INSERT INTO `orderfood` VALUES (11005, 11055, 5, '2022-11-03', NULL);
INSERT INTO `orderfood` VALUES (11006, 11066, 6, NULL, NULL);
INSERT INTO `orderfood` VALUES (11007, 11077, 7, NULL, NULL);
INSERT INTO `orderfood` VALUES (11008, 11088, 8, NULL, NULL);
INSERT INTO `orderfood` VALUES (11009, 100001, 6, NULL, NULL);
INSERT INTO `orderfood` VALUES (11010, 100010, 3, '2022-11-30', NULL);

SET FOREIGN_KEY_CHECKS = 1;



SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for reserveplan
-- ----------------------------
DROP TABLE IF EXISTS `reserveplan`;
CREATE TABLE `reserveplan`  (
  `planID` int NOT NULL AUTO_INCREMENT COMMENT '预案的ID',
  `planName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '预案名称',
  `planType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '预案的类型',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL COMMENT '状态',
  `newTime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `accentType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci NULL DEFAULT NULL,
  PRIMARY KEY (`planID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9998889 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_520_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of reserveplan
-- ----------------------------
INSERT INTO `reserveplan` VALUES (123, NULL, '医疗', NULL, '2022-11-26 19:36:43', '');
INSERT INTO `reserveplan` VALUES (9002, '预案2', '安全', '停用', '2022-11-26 16:06:10', '66');
INSERT INTO `reserveplan` VALUES (9003, '预案1', '医疗', '停用', '2022-11-25 21:13:47', '777');
INSERT INTO `reserveplan` VALUES (9004, '预案3', '卫生', '启用', '2022-11-23 21:21:26', NULL);
INSERT INTO `reserveplan` VALUES (9005, '999', NULL, NULL, '2022-11-25 22:30:08', '777');
INSERT INTO `reserveplan` VALUES (9006, '888', NULL, NULL, NULL, NULL);
INSERT INTO `reserveplan` VALUES (9007, '777', NULL, NULL, '2022-11-25 21:22:12', NULL);
INSERT INTO `reserveplan` VALUES (123456, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `reserveplan` VALUES (9998888, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

