-- ----------------------------
-- Table structure for ts_upload
-- ----------------------------
CREATE TABLE ts_upload (
ID VARCHAR2(64) NOT NULL ,
COMPANY_ID VARCHAR2(64) NOT NULL ,
UNIQUE_KEY VARCHAR2(50) NOT NULL ,
NAME VARCHAR2(50) NOT NULL ,
TYPE VARCHAR2(3) NOT NULL ,
URL VARCHAR2(50) NOT NULL ,
updated VARCHAR2(3) DEFAULT '0' NOT NULL,
VERSION VARCHAR2(10) DEFAULT '1' NOT NULL,
REMARK VARCHAR2(255) NULL ,
CREATE_BY VARCHAR2(64) NOT NULL ,
CREATE_DATE DATE NOT NULL ,
UPDATE_BY VARCHAR2(64) NULL ,
UPDATE_DATE DATE NULL 
)

;
COMMENT ON TABLE ts_upload IS '文件上传';
COMMENT ON COLUMN ts_upload.ID IS '主键';
COMMENT ON COLUMN ts_upload.COMPANY_ID IS '公司';
COMMENT ON COLUMN ts_upload.UNIQUE_KEY IS '文件标识';
COMMENT ON COLUMN ts_upload.NAME IS '文件名称';
COMMENT ON COLUMN ts_upload.TYPE IS '文件类型';
COMMENT ON COLUMN ts_upload.URL IS '文件地址';
COMMENT ON COLUMN ts_upload.updated IS '强制更新(0-否 1-是)';
COMMENT ON COLUMN ts_upload.VERSION IS '版本号';
COMMENT ON COLUMN ts_upload.REMARK IS '备注';
COMMENT ON COLUMN ts_upload.CREATE_BY IS '创建者';
COMMENT ON COLUMN ts_upload.CREATE_DATE IS '创建时间';
COMMENT ON COLUMN ts_upload.UPDATE_BY IS '更新者';
COMMENT ON COLUMN ts_upload.UPDATE_DATE IS '更新时间';

-- ----------------------------
-- Records of ts_upload
-- ----------------------------


-- ----------------------------
-- Table structure for ts_barcode
-- ----------------------------
CREATE TABLE TS_BARCODE (
ID VARCHAR2(64) NOT NULL ,
SEQUENCE_ID VARCHAR2(64) NOT NULL ,
CODE VARCHAR2(64) NOT NULL ,
NAME VARCHAR2(100) NULL ,
ENABLED VARCHAR2(3) DEFAULT '1' NOT NULL,
REMARK VARCHAR2(255) NULL ,
CREATE_BY VARCHAR2(64) NOT NULL ,
CREATE_DATE DATE NOT NULL ,
UPDATE_BY VARCHAR2(64) NULL ,
UPDATE_DATE DATE NULL 
)

;
COMMENT ON TABLE TS_BARCODE IS '条码生成';
COMMENT ON COLUMN TS_BARCODE.ID IS '主键';
COMMENT ON COLUMN TS_BARCODE.SEQUENCE_ID IS '序列号规则';
COMMENT ON COLUMN TS_BARCODE.CODE IS '条码规则号';
COMMENT ON COLUMN TS_BARCODE.NAME IS '条码规则名称';
COMMENT ON COLUMN TS_BARCODE.ENABLED IS '是否启用';
COMMENT ON COLUMN TS_BARCODE.REMARK IS '备注信息';
COMMENT ON COLUMN TS_BARCODE.CREATE_BY IS '创建者';
COMMENT ON COLUMN TS_BARCODE.CREATE_DATE IS '创建时间';
COMMENT ON COLUMN TS_BARCODE.UPDATE_BY IS '更新者';
COMMENT ON COLUMN TS_BARCODE.UPDATE_DATE IS '更新时间';

-- ----------------------------
-- Records of ts_barcode
-- ----------------------------

-- ----------------------------
-- Table structure for ts_depart
-- ----------------------------
CREATE TABLE TS_DEPART (
ID VARCHAR2(64) NOT NULL ,
PARENT_ID VARCHAR2(64) DEFAULT '0' NOT NULL ,
COMPANY_ID VARCHAR2(64) NOT NULL ,
CODE VARCHAR2(50) NOT NULL ,
NAME VARCHAR2(50) NOT NULL ,
SORT NUMBER(11) NOT NULL ,
ENABLED VARCHAR2(5) DEFAULT '1' NOT NULL,
DELETED NUMBER(11) DEFAULT 0 NOT NULL,
REMARK VARCHAR2(200) NULL ,
CREATE_BY VARCHAR2(50) NOT NULL ,
CREATE_DATE DATE NOT NULL ,
UPDATE_BY VARCHAR2(50) NULL ,
UPDATE_DATE DATE NULL 
)

;
COMMENT ON TABLE TS_DEPART IS '部门表';
COMMENT ON COLUMN TS_DEPART.PARENT_ID IS '上级部门';
COMMENT ON COLUMN TS_DEPART.COMPANY_ID IS '所属公司';
COMMENT ON COLUMN TS_DEPART.CODE IS '部门编码';
COMMENT ON COLUMN TS_DEPART.NAME IS '部门名称';
COMMENT ON COLUMN TS_DEPART.SORT IS '排序';
COMMENT ON COLUMN TS_DEPART.ENABLED IS '是否启用';
COMMENT ON COLUMN TS_DEPART.REMARK IS '备注';

-- ----------------------------
-- Records of ts_depart
-- ----------------------------

-- ----------------------------
-- Table structure for ts_dict
-- ----------------------------
CREATE TABLE TS_DICT (
ID VARCHAR2(64) NOT NULL ,
PARENT_ID VARCHAR2(64) DEFAULT '0' NOT NULL ,
CODE VARCHAR2(100) NOT NULL ,
NAME VARCHAR2(100) NOT NULL ,
SORT NUMBER(11) NOT NULL ,
EDIT VARCHAR2(3) NOT NULL ,
ENABLED VARCHAR2(3) DEFAULT '1' NOT NULL,
DELETED NUMBER(11) DEFAULT 0 NOT NULL,
REMARK VARCHAR2(255) NULL ,
CREATE_BY VARCHAR2(64) NOT NULL ,
CREATE_DATE DATE NOT NULL ,
UPDATE_BY VARCHAR2(64) NULL ,
UPDATE_DATE DATE NULL 
)

;
COMMENT ON TABLE TS_DICT IS '字典';
COMMENT ON COLUMN TS_DICT.ID IS '编号';
COMMENT ON COLUMN TS_DICT.PARENT_ID IS '父级编号';
COMMENT ON COLUMN TS_DICT.CODE IS '数据值';
COMMENT ON COLUMN TS_DICT.NAME IS '标签名';
COMMENT ON COLUMN TS_DICT.EDIT IS '是否前端可编辑（0 不可编辑 1 可编辑）';
COMMENT ON COLUMN TS_DICT.ENABLED IS '是否启用';
COMMENT ON COLUMN TS_DICT.DELETED IS '逻辑删除（0 未删除  1已删除）';
COMMENT ON COLUMN TS_DICT.REMARK IS '备注信息';
COMMENT ON COLUMN TS_DICT.CREATE_BY IS '创建者';
COMMENT ON COLUMN TS_DICT.CREATE_DATE IS '创建时间';
COMMENT ON COLUMN TS_DICT.UPDATE_BY IS '更新者';
COMMENT ON COLUMN TS_DICT.UPDATE_DATE IS '更新时间';

-- ----------------------------
-- Records of ts_dict
-- ----------------------------
INSERT INTO TS_DICT VALUES ('1195498667916079106', '0', 'YES_NO', '是与否', '1', '0', '1', '0', null, 'admin', TO_TIMESTAMP('2019-11-16 08:27:48', 'YYYY-MM-DD HH24:MI:SS'), 'admin', TO_TIMESTAMP('2019-11-16 08:27:48', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_DICT VALUES ('1195499452884267010', '1195498667916079106', '0', '否', '0', '0', '1', '0', null, 'admin', TO_TIMESTAMP('2019-11-16 08:30:56', 'YYYY-MM-DD HH24:MI:SS'), 'admin', TO_TIMESTAMP('2019-11-16 08:30:56', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_DICT VALUES ('1195500195037003777', '1195498667916079106', '1', '是', '1', '0', '1', '0', null, 'admin', TO_TIMESTAMP('2019-11-16 08:33:52', 'YYYY-MM-DD HH24:MI:SS'), 'admin', TO_TIMESTAMP('2019-11-16 08:33:52', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_DICT VALUES ('1195500377849937921', '0', 'MENU_TARGET', '菜单打开方式', '2', '0', '1', '0', null, 'admin', TO_TIMESTAMP('2019-11-16 08:34:36', 'YYYY-MM-DD HH24:MI:SS'), 'admin', TO_TIMESTAMP('2019-11-16 08:34:36', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_DICT VALUES ('1195500537564839937', '1195500377849937921', 'TAB', '标签', '1', '0', '1', '0', null, 'admin', TO_TIMESTAMP('2019-11-16 08:35:14', 'YYYY-MM-DD HH24:MI:SS'), 'admin', TO_TIMESTAMP('2019-11-16 08:35:14', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_DICT VALUES ('1195500625901076482', '1195500377849937921', 'HTML', '网页', '2', '0', '1', '0', null, 'admin', TO_TIMESTAMP('2019-11-16 08:35:35', 'YYYY-MM-DD HH24:MI:SS'), 'admin', TO_TIMESTAMP('2019-11-16 08:35:35', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_DICT VALUES ('1195500745111584770', '1195500377849937921', 'BUTTON', '按钮', '3', '0', '1', '0', null, 'admin', TO_TIMESTAMP('2019-11-16 08:36:04', 'YYYY-MM-DD HH24:MI:SS'), 'admin', TO_TIMESTAMP('2019-11-16 08:36:04', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_DICT VALUES ('1195990048782667778', '0', 'COMPANY_TYPE', '公司类型', '3', '0', '1', '0', null, 'admin', TO_TIMESTAMP('2019-11-17 17:00:23', 'YYYY-MM-DD HH24:MI:SS'), 'admin', TO_TIMESTAMP('2019-11-17 17:00:23', 'YYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- Table structure for ts_job_log
-- ----------------------------
CREATE TABLE TS_JOB_LOG (
ID VARCHAR2(64) NOT NULL ,
JOB_NAME VARCHAR2(200) NOT NULL ,
JOB_GROUP VARCHAR2(200) NOT NULL ,
JOB_CLASS VARCHAR2(200) NOT NULL ,
EXEC_TIME DATE NULL ,
DURATION NUMBER(11) NULL ,
JOB_MSG NCLOB NULL ,
CREATE_BY VARCHAR2(50) NOT NULL ,
CREATE_DATE DATE NOT NULL ,
UPDATE_BY VARCHAR2(255) NULL ,
UPDATE_DATE DATE NULL 
)

;
COMMENT ON TABLE TS_JOB_LOG IS '定时任务日志';
COMMENT ON COLUMN TS_JOB_LOG.JOB_NAME IS '任务名称';
COMMENT ON COLUMN TS_JOB_LOG.JOB_GROUP IS '任务组';
COMMENT ON COLUMN TS_JOB_LOG.JOB_CLASS IS '任务类';
COMMENT ON COLUMN TS_JOB_LOG.EXEC_TIME IS '运行时间';
COMMENT ON COLUMN TS_JOB_LOG.DURATION IS '总耗时（秒）';
COMMENT ON COLUMN TS_JOB_LOG.JOB_MSG IS '任务运行信息';

-- ----------------------------
-- Records of ts_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for ts_menu
-- ----------------------------
CREATE TABLE TS_MENU (
ID VARCHAR2(64) NOT NULL ,
PARENT_ID VARCHAR2(64) DEFAULT '0' NOT NULL ,
NAME VARCHAR2(100) NOT NULL ,
SORT NUMBER(11) NOT NULL ,
HREF VARCHAR2(200) NULL ,
TARGET VARCHAR2(20) NULL ,
MENU_ICON VARCHAR2(100) NULL ,
ENABLED VARCHAR2(3) DEFAULT '1' NOT NULL,
DELETED NUMBER(11) DEFAULT 0 NOT NULL,
REMARK VARCHAR2(255) NULL ,
CREATE_BY VARCHAR2(64) NOT NULL ,
CREATE_DATE DATE NOT NULL ,
UPDATE_BY VARCHAR2(64) NULL ,
UPDATE_DATE DATE NULL 
)

;
COMMENT ON TABLE TS_MENU IS '菜单';
COMMENT ON COLUMN TS_MENU.ID IS '编号';
COMMENT ON COLUMN TS_MENU.PARENT_ID IS '父级编号';
COMMENT ON COLUMN TS_MENU.NAME IS '名称';
COMMENT ON COLUMN TS_MENU.SORT IS '排序';
COMMENT ON COLUMN TS_MENU.HREF IS '链接';
COMMENT ON COLUMN TS_MENU.TARGET IS '目标';
COMMENT ON COLUMN TS_MENU.MENU_ICON IS '图标';
COMMENT ON COLUMN TS_MENU.ENABLED IS '是否启用';
COMMENT ON COLUMN TS_MENU.REMARK IS '备注信息';
COMMENT ON COLUMN TS_MENU.CREATE_BY IS '创建者';
COMMENT ON COLUMN TS_MENU.CREATE_DATE IS '创建时间';
COMMENT ON COLUMN TS_MENU.UPDATE_BY IS '更新者';
COMMENT ON COLUMN TS_MENU.UPDATE_DATE IS '更新时间';

-- ----------------------------
-- Records of ts_menu
-- ----------------------------
INSERT INTO TS_MENU VALUES ('1194499298376736769', '0', 'autumn', '1', null, null, null, '1', '0', null, 'anonymous', TO_TIMESTAMP('2019-11-13 14:16:40', 'YYYY-MM-DD HH24:MI:SS'), 'anonymous', TO_TIMESTAMP('2019-11-13 14:16:40', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_MENU VALUES ('1194499419231412226', '1194499298376736769', '系统管理', '1', null, null, 'icon-settings', '1', '0', null, 'anonymous', TO_TIMESTAMP('2019-11-13 14:17:09', 'YYYY-MM-DD HH24:MI:SS'), 'anonymous', TO_TIMESTAMP('2019-11-13 14:17:09', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_MENU VALUES ('1194499492371685378', '1194499419231412226', '菜单管理', '1', '../system/Menu/MenuList.html', 'TAB', null, '1', '0', null, 'anonymous', TO_TIMESTAMP('2019-11-13 14:17:26', 'YYYY-MM-DD HH24:MI:SS'), 'anonymous', TO_TIMESTAMP('2019-11-13 14:17:26', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_MENU VALUES ('1194499553784684546', '1194499419231412226', '用户管理', '2', '../system/User/UserList.html', 'TAB', null, '1', '0', null, 'anonymous', TO_TIMESTAMP('2019-11-13 14:17:41', 'YYYY-MM-DD HH24:MI:SS'), 'anonymous', TO_TIMESTAMP('2019-11-13 14:17:41', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_MENU VALUES ('1194499607060733954', '1194499419231412226', '角色管理', '3', '../system/Role/RoleList.html', 'TAB', null, '1', '0', null, 'anonymous', TO_TIMESTAMP('2019-11-13 14:17:54', 'YYYY-MM-DD HH24:MI:SS'), 'admin', TO_TIMESTAMP('2019-11-16 07:10:28', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_MENU VALUES ('1194499850217119745', '1194499419231412226', '字典管理', '4', '../system/Dict/DictList.html', 'TAB', null, '1', '0', null, 'anonymous', TO_TIMESTAMP('2019-11-13 14:18:52', 'YYYY-MM-DD HH24:MI:SS'), 'admin', TO_TIMESTAMP('2019-11-16 07:14:23', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_MENU VALUES ('1194499883134017537', '1194499419231412226', '公司管理', '5', '../system/Company/CompanyList.html', 'TAB', null, '1', '0', null, 'anonymous', TO_TIMESTAMP('2019-11-13 14:19:00', 'YYYY-MM-DD HH24:MI:SS'), 'anonymous', TO_TIMESTAMP('2019-11-13 14:19:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_MENU VALUES ('1194499912301207554', '1194499419231412226', '部门管理', '6', '../system/Depart/DepartList.html', 'TAB', null, '1', '0', null, 'anonymous', TO_TIMESTAMP('2019-11-13 14:19:06', 'YYYY-MM-DD HH24:MI:SS'), 'anonymous', TO_TIMESTAMP('2019-11-13 14:19:06', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_MENU VALUES ('1194500051778592770', '1194499419231412226', '区域管理', '7', '../system/Provinces/ProvincesList.html', 'TAB', null, '1', '0', null, 'anonymous', TO_TIMESTAMP('2019-11-13 14:19:40', 'YYYY-MM-DD HH24:MI:SS'), 'anonymous', TO_TIMESTAMP('2019-11-13 14:19:40', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_MENU VALUES ('1194500391563354113', '1194499419231412226', '条码管理', '8', '', null, null, '1', '0', null, 'anonymous', TO_TIMESTAMP('2019-11-13 14:21:01', 'YYYY-MM-DD HH24:MI:SS'), 'anonymous', TO_TIMESTAMP('2019-11-13 14:21:01', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_MENU VALUES ('1194500615098785793', '1194500391563354113', '条码规则', '1', '../system/Sequence/SequenceList.html', 'TAB', null, '1', '0', null, 'anonymous', TO_TIMESTAMP('2019-11-13 14:21:54', 'YYYY-MM-DD HH24:MI:SS'), 'anonymous', TO_TIMESTAMP('2019-11-13 14:21:54', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO TS_MENU VALUES ('1194500649466912770', '1194500391563354113', '条码生成', '2', '../system/Barcode/BarcodeList.html', 'TAB', null, '1', '0', null, 'anonymous', TO_TIMESTAMP('2019-11-13 14:22:02', 'YYYY-MM-DD HH24:MI:SS'), 'anonymous', TO_TIMESTAMP('2019-11-13 14:22:02', 'YYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- Table structure for ts_company
-- ----------------------------
CREATE TABLE TS_COMPANY (
ID VARCHAR2(64) NOT NULL ,
PARENT_ID VARCHAR2(64) DEFAULT '0' NOT NULL ,
NAME VARCHAR2(100) NOT NULL ,
SORT NUMBER(11) NOT NULL ,
TYPE VARCHAR2(100) NULL ,
MASTER VARCHAR2(100) NULL ,
PHONE VARCHAR2(200) NULL ,
FAX VARCHAR2(200) NULL ,
EMAIL VARCHAR2(200) NULL ,
DEPUTY_PERSON VARCHAR2(64) NULL ,
REMARK VARCHAR2(255) NULL ,
ENABLED VARCHAR2(3) DEFAULT '1' NOT NULL,
DELETED NUMBER(11) DEFAULT 0 NOT NULL,
CREATE_BY VARCHAR2(64) NOT NULL ,
CREATE_DATE DATE NOT NULL ,
UPDATE_BY VARCHAR2(64) NULL ,
UPDATE_DATE DATE NULL 
)

;
COMMENT ON TABLE TS_COMPANY IS '机构';
COMMENT ON COLUMN TS_COMPANY.ID IS '编号';
COMMENT ON COLUMN TS_COMPANY.PARENT_ID IS '父级编号';
COMMENT ON COLUMN TS_COMPANY.NAME IS '名称';
COMMENT ON COLUMN TS_COMPANY.SORT IS '排序';
COMMENT ON COLUMN TS_COMPANY.TYPE IS '机构类型';
COMMENT ON COLUMN TS_COMPANY.MASTER IS '负责人';
COMMENT ON COLUMN TS_COMPANY.PHONE IS '电话';
COMMENT ON COLUMN TS_COMPANY.FAX IS '传真';
COMMENT ON COLUMN TS_COMPANY.EMAIL IS '邮箱';
COMMENT ON COLUMN TS_COMPANY.DEPUTY_PERSON IS '副负责人';
COMMENT ON COLUMN TS_COMPANY.REMARK IS '备注信息';
COMMENT ON COLUMN TS_COMPANY.ENABLED IS '是否启用';
COMMENT ON COLUMN TS_COMPANY.CREATE_BY IS '创建者';
COMMENT ON COLUMN TS_COMPANY.CREATE_DATE IS '创建时间';
COMMENT ON COLUMN TS_COMPANY.UPDATE_BY IS '更新者';
COMMENT ON COLUMN TS_COMPANY.UPDATE_DATE IS '更新时间';

-- ----------------------------
-- Records of ts_COMPANY
-- ----------------------------
INSERT INTO TS_COMPANY VALUES ('1194213889648566274', '0', '默认公司', '1', null, null, null, null, null, null, null, '1', '0', 'anonymous', TO_TIMESTAMP('2019-11-12 19:22:33', 'YYYY-MM-DD HH24:MI:SS'), 'anonymous', TO_TIMESTAMP('2019-11-12 19:22:33', 'YYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- Table structure for ts_operation_log
-- ----------------------------
CREATE TABLE TS_OPERATION_LOG (
ID VARCHAR2(64) NOT NULL ,
OPERTION_TYPE VARCHAR2(255) NOT NULL ,
TABLE_NAME NCLOB NOT NULL ,
OPERATION_DETAIL NCLOB NOT NULL ,
REQUEST_URI VARCHAR2(255) NULL ,
IP VARCHAR2(50) NULL ,
CREATE_BY VARCHAR2(64) NOT NULL ,
CREATE_DATE DATE NOT NULL ,
UPDATE_BY VARCHAR2(64) NULL ,
UPDATE_DATE DATE NULL 
)

;
COMMENT ON TABLE TS_OPERATION_LOG IS '操作日志';
COMMENT ON COLUMN TS_OPERATION_LOG.ID IS '编号';
COMMENT ON COLUMN TS_OPERATION_LOG.OPERTION_TYPE IS '操作类型(增加、修改、删除)';
COMMENT ON COLUMN TS_OPERATION_LOG.TABLE_NAME IS '被操作的表';
COMMENT ON COLUMN TS_OPERATION_LOG.OPERATION_DETAIL IS '操作详细信息';
COMMENT ON COLUMN TS_OPERATION_LOG.REQUEST_URI IS '请求URI';
COMMENT ON COLUMN TS_OPERATION_LOG.IP IS '操作ip';
COMMENT ON COLUMN TS_OPERATION_LOG.CREATE_BY IS '创建者';
COMMENT ON COLUMN TS_OPERATION_LOG.CREATE_DATE IS '创建时间';
COMMENT ON COLUMN TS_OPERATION_LOG.UPDATE_BY IS '更新者';
COMMENT ON COLUMN TS_OPERATION_LOG.UPDATE_DATE IS '更新时间';

-- ----------------------------
-- Records of ts_operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for ts_provinces
-- ----------------------------
CREATE TABLE TS_PROVINCES (
ID VARCHAR2(64) NOT NULL ,
PARENT_ID VARCHAR2(64) DEFAULT '0' NOT NULL ,
CITY_NAME VARCHAR2(30) NOT NULL ,
SHORT_NAME VARCHAR2(30) NULL ,
DEPTH NUMBER(11) NOT NULL ,
CITY_CODE VARCHAR2(4) NOT NULL ,
ZIP_CODE VARCHAR2(6) NULL ,
MERGER_NAME VARCHAR2(50) NULL ,
LONGITUDE VARCHAR2(16) NULL ,
LATITUDE VARCHAR2(16) NULL ,
PINYIN VARCHAR2(30) NULL ,
ENABLED NUMBER(11) DEFAULT '1' NOT NULL,
CREATE_BY VARCHAR2(50) NOT NULL ,
CREATE_DATE DATE NOT NULL ,
UPDATE_BY VARCHAR2(50) NULL ,
UPDATE_DATE DATE NULL 
)

;
COMMENT ON TABLE TS_PROVINCES IS '区县行政编码';
COMMENT ON COLUMN TS_PROVINCES.PARENT_ID IS '父级id';
COMMENT ON COLUMN TS_PROVINCES.CITY_NAME IS '城市名称';
COMMENT ON COLUMN TS_PROVINCES.SHORT_NAME IS '城市缩写名称';
COMMENT ON COLUMN TS_PROVINCES.DEPTH IS '城市层级';
COMMENT ON COLUMN TS_PROVINCES.CITY_CODE IS '城市代码';
COMMENT ON COLUMN TS_PROVINCES.ZIP_CODE IS '城市邮编';
COMMENT ON COLUMN TS_PROVINCES.MERGER_NAME IS '城市组合名称';
COMMENT ON COLUMN TS_PROVINCES.LONGITUDE IS '精度';
COMMENT ON COLUMN TS_PROVINCES.LATITUDE IS '维度';
COMMENT ON COLUMN TS_PROVINCES.PINYIN IS '城市拼音';

-- ----------------------------
-- Records of ts_provinces
-- ----------------------------

-- ----------------------------
-- Table structure for ts_role
-- ----------------------------
CREATE TABLE TS_ROLE (
ID VARCHAR2(64) NOT NULL ,
COMPANY_ID VARCHAR2(64) NULL ,
CODE VARCHAR2(100) NOT NULL ,
NAME VARCHAR2(255) NULL ,
REMARK VARCHAR2(255) NULL ,
ENABLED VARCHAR2(3) DEFAULT '1' NOT NULL,
DELETED NUMBER(11) DEFAULT 0 NOT NULL,
CREATE_BY VARCHAR2(64) NOT NULL ,
CREATE_DATE DATE NOT NULL ,
UPDATE_BY VARCHAR2(64) NULL ,
UPDATE_DATE DATE NULL 
)

;
COMMENT ON TABLE TS_ROLE IS '角色';
COMMENT ON COLUMN TS_ROLE.ID IS '编号';
COMMENT ON COLUMN TS_ROLE.CODE IS '角色名称';
COMMENT ON COLUMN TS_ROLE.NAME IS '英文名称';
COMMENT ON COLUMN TS_ROLE.REMARK IS '备注信息';
COMMENT ON COLUMN TS_ROLE.ENABLED IS '是否启用';
COMMENT ON COLUMN TS_ROLE.DELETED IS '逻辑删除标识';
COMMENT ON COLUMN TS_ROLE.CREATE_BY IS '创建者';
COMMENT ON COLUMN TS_ROLE.CREATE_DATE IS '创建时间';
COMMENT ON COLUMN TS_ROLE.UPDATE_BY IS '更新者';
COMMENT ON COLUMN TS_ROLE.UPDATE_DATE IS '更新时间';

-- ----------------------------
-- Records of ts_role
-- ----------------------------

-- ----------------------------
-- Table structure for ts_role_menu
-- ----------------------------
CREATE TABLE TS_ROLE_MENU (
ID VARCHAR2(64) NOT NULL ,
ROLE_ID VARCHAR2(64) NOT NULL ,
MENU_ID VARCHAR2(64) NOT NULL ,
CREATE_BY VARCHAR2(50) NOT NULL ,
CREATE_DATE DATE NOT NULL ,
UPDATE_BY VARCHAR2(50) NULL ,
UPDATE_DATE DATE NULL 
)

;
COMMENT ON TABLE TS_ROLE_MENU IS '角色-菜单';
COMMENT ON COLUMN TS_ROLE_MENU.ROLE_ID IS '角色主键';
COMMENT ON COLUMN TS_ROLE_MENU.MENU_ID IS '菜单主键';

-- ----------------------------
-- Records of ts_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for ts_sequence
-- ----------------------------
CREATE TABLE TS_SEQUENCE (
ID VARCHAR2(64) NOT NULL ,
CODE VARCHAR2(64) NOT NULL ,
NAME VARCHAR2(100) NOT NULL ,
REMARK VARCHAR2(255) NULL ,
ENABLED VARCHAR2(3) DEFAULT '1' NOT NULL,
CREATE_BY VARCHAR2(64) NOT NULL ,
CREATE_DATE DATE NOT NULL ,
UPDATE_BY VARCHAR2(64) NULL ,
UPDATE_DATE DATE NULL 
)

;
COMMENT ON TABLE TS_SEQUENCE IS '条码规则';
COMMENT ON COLUMN TS_SEQUENCE.ID IS '主键';
COMMENT ON COLUMN TS_SEQUENCE.CODE IS '序列规则编码';
COMMENT ON COLUMN TS_SEQUENCE.NAME IS '序列规则名称';
COMMENT ON COLUMN TS_SEQUENCE.REMARK IS '备注信息';
COMMENT ON COLUMN TS_SEQUENCE.ENABLED IS '是否启用';
COMMENT ON COLUMN TS_SEQUENCE.CREATE_BY IS '创建者';
COMMENT ON COLUMN TS_SEQUENCE.CREATE_DATE IS '创建时间';
COMMENT ON COLUMN TS_SEQUENCE.UPDATE_BY IS '更新者';
COMMENT ON COLUMN TS_SEQUENCE.UPDATE_DATE IS '更新时间';

-- ----------------------------
-- Records of ts_sequence
-- ----------------------------

-- ----------------------------
-- Table structure for ts_sequence_detail
-- ----------------------------
CREATE TABLE TS_SEQUENCE_DETAIL (
ID VARCHAR2(64) NOT NULL ,
SEQUENCE_ID VARCHAR2(64) NOT NULL ,
TYPE VARCHAR2(100) NOT NULL ,
VALUE VARCHAR2(255) NULL ,
SORT NUMBER(11) NOT NULL ,
LENGTH NUMBER(11) NULL ,
FORMAT VARCHAR2(100) NULL ,
DELIMIT VARCHAR2(100) NULL ,
CREATE_BY VARCHAR2(64) NOT NULL ,
CREATE_DATE DATE NOT NULL ,
UPDATE_BY VARCHAR2(64) NULL ,
UPDATE_DATE DATE NULL 
)

;
COMMENT ON TABLE TS_SEQUENCE_DETAIL IS '条码规则明细';
COMMENT ON COLUMN TS_SEQUENCE_DETAIL.ID IS '主键';
COMMENT ON COLUMN TS_SEQUENCE_DETAIL.SEQUENCE_ID IS '序列规则主键';
COMMENT ON COLUMN TS_SEQUENCE_DETAIL.TYPE IS '类型（0 字符 1 数字 2 日期）';
COMMENT ON COLUMN TS_SEQUENCE_DETAIL.VALUE IS '值';
COMMENT ON COLUMN TS_SEQUENCE_DETAIL.SORT IS '排序';
COMMENT ON COLUMN TS_SEQUENCE_DETAIL.LENGTH IS '长度';
COMMENT ON COLUMN TS_SEQUENCE_DETAIL.FORMAT IS '日期格式化';
COMMENT ON COLUMN TS_SEQUENCE_DETAIL.DELIMIT IS '分隔符';
COMMENT ON COLUMN TS_SEQUENCE_DETAIL.CREATE_BY IS '创建者';
COMMENT ON COLUMN TS_SEQUENCE_DETAIL.CREATE_DATE IS '创建时间';
COMMENT ON COLUMN TS_SEQUENCE_DETAIL.UPDATE_BY IS '更新者';
COMMENT ON COLUMN TS_SEQUENCE_DETAIL.UPDATE_DATE IS '更新时间';

-- ----------------------------
-- Records of ts_sequence_detail
-- ----------------------------

-- ----------------------------
-- Table structure for ts_user
-- ----------------------------
CREATE TABLE TS_USER (
ID VARCHAR2(64) NOT NULL ,
LOGIN_NAME VARCHAR2(100) NOT NULL ,
PASSWORD VARCHAR2(100) NOT NULL ,
TRADE_NUMBER VARCHAR2(100) NULL ,
USER_NAME VARCHAR2(100) NULL ,
EMAIL VARCHAR2(200) NULL ,
PHONE VARCHAR2(200) NULL ,
PHOTO VARCHAR2(1000) NULL ,
LOGIN_IP VARCHAR2(100) NULL ,
LOGIN_DATE DATE NULL ,
SUPER_ADMIN VARCHAR2(10) NOT NULL ,
ENABLED VARCHAR2(3) DEFAULT '1' NOT NULL,
DELETED NUMBER(11) DEFAULT 0 NOT NULL,
REMARK VARCHAR2(255) NULL ,
CREATE_BY VARCHAR2(64) NOT NULL ,
CREATE_DATE DATE NOT NULL ,
UPDATE_BY VARCHAR2(64) NULL ,
UPDATE_DATE DATE NULL 
)

;
COMMENT ON TABLE TS_USER IS '用户';
COMMENT ON COLUMN TS_USER.ID IS '主键';
COMMENT ON COLUMN TS_USER.LOGIN_NAME IS '登录名';
COMMENT ON COLUMN TS_USER.PASSWORD IS '密码';
COMMENT ON COLUMN TS_USER.TRADE_NUMBER IS '工牌号';
COMMENT ON COLUMN TS_USER.USER_NAME IS '姓名';
COMMENT ON COLUMN TS_USER.EMAIL IS '邮箱';
COMMENT ON COLUMN TS_USER.PHONE IS '电话';
COMMENT ON COLUMN TS_USER.PHOTO IS '用户头像';
COMMENT ON COLUMN TS_USER.LOGIN_IP IS '最后登陆IP';
COMMENT ON COLUMN TS_USER.LOGIN_DATE IS '最后登陆时间';
COMMENT ON COLUMN TS_USER.SUPER_ADMIN IS '超级管理员';
COMMENT ON COLUMN TS_USER.ENABLED IS '是否启用';
COMMENT ON COLUMN TS_USER.DELETED IS '逻辑删除标识';
COMMENT ON COLUMN TS_USER.REMARK IS '备注信息';
COMMENT ON COLUMN TS_USER.CREATE_BY IS '创建者';
COMMENT ON COLUMN TS_USER.CREATE_DATE IS '创建时间';
COMMENT ON COLUMN TS_USER.UPDATE_BY IS '更新者';
COMMENT ON COLUMN TS_USER.UPDATE_DATE IS '更新时间';

-- ----------------------------
-- Records of ts_user
-- ----------------------------
INSERT INTO TS_USER VALUES ('1194216110712938497', 'admin', '992F3C4E7EC9A0E96173284C816612BD', null, '超级管理员', '623374047@qq.com', '15605515779', null, null, null, '1', '1', '0', '', 'anonymous', TO_TIMESTAMP('2019-11-12 19:31:23', 'YYYY-MM-DD HH24:MI:SS'), 'anonymous', TO_TIMESTAMP('2019-11-12 19:31:23', 'YYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- Table structure for ts_user_depart
-- ----------------------------
CREATE TABLE TS_USER_DEPART (
ID VARCHAR2(64) NOT NULL ,
USER_ID VARCHAR2(64) NOT NULL ,
DEPART_ID VARCHAR2(64) NOT NULL ,
CREATE_BY VARCHAR2(50) NOT NULL ,
CREATE_DATE DATE NOT NULL ,
UPDATE_BY VARCHAR2(50) NULL ,
UPDATE_DATE DATE NULL 
)

;
COMMENT ON COLUMN TS_USER_DEPART.USER_ID IS '用户主键';
COMMENT ON COLUMN TS_USER_DEPART.DEPART_ID IS '部门主键';

-- ----------------------------
-- Records of ts_user_depart
-- ----------------------------

-- ----------------------------
-- Table structure for ts_user_COMPANY
-- ----------------------------
CREATE TABLE TS_USER_COMPANY (
ID VARCHAR2(64) NOT NULL ,
USER_ID VARCHAR2(64) NOT NULL ,
COMPANY_ID VARCHAR2(64) NOT NULL ,
CREATE_BY VARCHAR2(50) NOT NULL ,
CREATE_DATE DATE NOT NULL ,
UPDATE_BY VARCHAR2(50) NULL ,
UPDATE_DATE DATE NULL 
)

;
COMMENT ON COLUMN TS_USER_COMPANY.USER_ID IS '用户主键';
COMMENT ON COLUMN TS_USER_COMPANY.COMPANY_ID IS '公司主键';

-- ----------------------------
-- Records of ts_user_COMPANY
-- ----------------------------
INSERT INTO TS_USER_COMPANY VALUES ('1194216111497273346', '1194216110712938497', '1194213889648566274', 'anonymous', TO_TIMESTAMP('2019-11-12 19:31:23', 'YYYY-MM-DD HH24:MI:SS'), 'anonymous', TO_TIMESTAMP('2019-11-12 19:31:23', 'YYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- Table structure for ts_user_role
-- ----------------------------
CREATE TABLE TS_USER_ROLE (
ID VARCHAR2(64) NOT NULL ,
USER_ID VARCHAR2(64) NOT NULL ,
ROLE_ID VARCHAR2(64) NOT NULL ,
CREATE_BY VARCHAR2(50) NOT NULL ,
CREATE_DATE DATE NOT NULL ,
UPDATE_BY VARCHAR2(50) NULL ,
UPDATE_DATE DATE NULL 
)

;
COMMENT ON TABLE TS_USER_ROLE IS '用户-角色';
COMMENT ON COLUMN TS_USER_ROLE.USER_ID IS '用户主键';
COMMENT ON COLUMN TS_USER_ROLE.ROLE_ID IS '角色主键';

-- ----------------------------
-- Records of ts_user_role
-- ----------------------------

-- ----------------------------
-- Indexes structure for table ts_barcode
-- ----------------------------
CREATE UNIQUE INDEX UK_BARCODE_01
ON TS_BARCODE (CODE );

-- ----------------------------
-- Primary Key structure for table ts_barcode
-- ----------------------------
ALTER TABLE TS_BARCODE ADD PRIMARY KEY (ID);

-- ----------------------------
-- Indexes structure for table ts_depart
-- ----------------------------
CREATE UNIQUE INDEX UK_DEPART_01
ON TS_DEPART (CODE );
CREATE UNIQUE INDEX UK_DEPART_02
ON TS_DEPART (PARENT_ID , CODE );

-- ----------------------------
-- Primary Key structure for table ts_depart
-- ----------------------------
ALTER TABLE TS_DEPART ADD PRIMARY KEY (ID);

-- ----------------------------
-- Indexes structure for table ts_dict
-- ----------------------------
CREATE UNIQUE INDEX UK_DICT_02
ON TS_DICT (PARENT_ID , SORT );
CREATE UNIQUE INDEX UK_DICT_01
ON TS_DICT (PARENT_ID , CODE );

-- ----------------------------
-- Primary Key structure for table ts_dict
-- ----------------------------
ALTER TABLE TS_DICT ADD PRIMARY KEY (ID);

-- ----------------------------
-- Primary Key structure for table ts_job_log
-- ----------------------------
ALTER TABLE TS_JOB_LOG ADD PRIMARY KEY (ID);

-- ----------------------------
-- Indexes structure for table ts_menu
-- ----------------------------
CREATE UNIQUE INDEX UK_MENU_01
ON TS_MENU (PARENT_ID , ID );
CREATE UNIQUE INDEX UK_MENU_02
ON TS_MENU (PARENT_ID , SORT );

-- ----------------------------
-- Primary Key structure for table ts_menu
-- ----------------------------
ALTER TABLE TS_MENU ADD PRIMARY KEY (ID);

-- ----------------------------
-- Indexes structure for table ts_COMPANY
-- ----------------------------
CREATE UNIQUE INDEX UK_COMPANY_01
ON TS_COMPANY (PARENT_ID , NAME );
CREATE UNIQUE INDEX UK_COMPANY_02
ON TS_COMPANY (PARENT_ID , SORT );

-- ----------------------------
-- Primary Key structure for table ts_COMPANY
-- ----------------------------
ALTER TABLE TS_COMPANY ADD PRIMARY KEY (ID);

-- ----------------------------
-- Primary Key structure for table ts_operation_log
-- ----------------------------
ALTER TABLE TS_OPERATION_LOG ADD PRIMARY KEY (ID);

-- ----------------------------
-- Indexes structure for table ts_provinces
-- ----------------------------
CREATE INDEX UK_PROVINCES_01
ON TS_PROVINCES (PARENT_ID , CITY_CODE );

-- ----------------------------
-- Primary Key structure for table ts_provinces
-- ----------------------------
ALTER TABLE TS_PROVINCES ADD PRIMARY KEY (ID);

-- ----------------------------
-- Indexes structure for table ts_role
-- ----------------------------
CREATE UNIQUE INDEX UK_ROLE_01
ON TS_ROLE (CODE );

-- ----------------------------
-- Primary Key structure for table ts_role
-- ----------------------------
ALTER TABLE TS_ROLE ADD PRIMARY KEY (ID);

-- ----------------------------
-- Indexes structure for table ts_role_menu
-- ----------------------------
CREATE UNIQUE INDEX UK_ROLE_MENU_01
ON TS_ROLE_MENU (ROLE_ID , MENU_ID );

-- ----------------------------
-- Primary Key structure for table ts_role_menu
-- ----------------------------
ALTER TABLE TS_ROLE_MENU ADD PRIMARY KEY (ID);

-- ----------------------------
-- Indexes structure for table ts_sequence
-- ----------------------------
CREATE UNIQUE INDEX UK_SEQUENCE_01
ON TS_SEQUENCE (CODE );

-- ----------------------------
-- Primary Key structure for table ts_sequence
-- ----------------------------
ALTER TABLE TS_SEQUENCE ADD PRIMARY KEY (ID);

-- ----------------------------
-- Primary Key structure for table ts_sequence_detail
-- ----------------------------
ALTER TABLE TS_SEQUENCE_DETAIL ADD PRIMARY KEY (ID);

-- ----------------------------
-- Indexes structure for table ts_user
-- ----------------------------
CREATE UNIQUE INDEX UK_USER_01
ON TS_USER (LOGIN_NAME );

-- ----------------------------
-- Primary Key structure for table ts_user
-- ----------------------------
ALTER TABLE TS_USER ADD PRIMARY KEY (ID);

-- ----------------------------
-- Indexes structure for table ts_user_depart
-- ----------------------------
CREATE UNIQUE INDEX UK_USER_DEPART_01
ON TS_USER_DEPART (USER_ID , DEPART_ID );

-- ----------------------------
-- Primary Key structure for table ts_user_depart
-- ----------------------------
ALTER TABLE TS_USER_DEPART ADD PRIMARY KEY (ID);

-- ----------------------------
-- Indexes structure for table ts_user_COMPANY
-- ----------------------------
CREATE UNIQUE INDEX UK_USER_COMPANY_01
ON TS_USER_COMPANY (USER_ID , COMPANY_ID );

-- ----------------------------
-- Primary Key structure for table ts_user_COMPANY
-- ----------------------------
ALTER TABLE TS_USER_COMPANY ADD PRIMARY KEY (ID);

-- ----------------------------
-- Indexes structure for table ts_user_role
-- ----------------------------
CREATE UNIQUE INDEX UK_USER_ROLE_01
ON TS_USER_ROLE (USER_ID , ROLE_ID );

-- ----------------------------
-- Primary Key structure for table ts_user_role
-- ----------------------------
ALTER TABLE TS_USER_ROLE ADD PRIMARY KEY (ID);
