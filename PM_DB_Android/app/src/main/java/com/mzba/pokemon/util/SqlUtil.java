package com.mzba.pokemon.util;

/**
 * Created by 06peng on 16/8/19.
 */
public class SqlUtil {

    public static String CREATE_TABLE_SQL = "CREATE TABLE `pokemon_list_cn_tb` (\n" +
            "  `id` int(4) NOT NULL AUTO_INCREMENT,\n" +
            "  `name` varchar(32) DEFAULT NULL COMMENT '名字',\n" +
            "  `description` varchar(64) DEFAULT NULL COMMENT '说明',\n" +
            "  `property` varchar(32) DEFAULT NULL COMMENT '属性',\n" +
            "  `feature` varchar(32) DEFAULT NULL COMMENT '特性',\n" +
            "  `hidden_feature` varchar(32) DEFAULT NULL COMMENT '隐藏特性',\n" +
            "  `sort` varchar(32) DEFAULT NULL COMMENT '分类',\n" +
            "  `height` varchar(32) DEFAULT NULL COMMENT '身高',\n" +
            "  `weight` varchar(32) DEFAULT NULL COMMENT '体重',\n" +
            "  `HP` int(8) DEFAULT NULL COMMENT 'HP',\n" +
            "  `attack` int(8) DEFAULT NULL COMMENT '攻击',\n" +
            "  `defense` int(8) DEFAULT NULL COMMENT '防御',\n" +
            "  `special_attack` int(8) DEFAULT NULL COMMENT '特攻',\n" +
            "  `special_defense` int(8) DEFAULT NULL COMMENT '特防',\n" +
            "  `speed` int(8) DEFAULT NULL COMMENT '速度',\n" +
            "  `total` int(8) DEFAULT NULL COMMENT '总和',\n" +
            "  `egg_group` varchar(32) DEFAULT NULL COMMENT '生蛋分组',\n" +
            "  `hatch_step` varchar(32) DEFAULT NULL COMMENT '孵化步数',\n" +
            "  `sex_ratio` varchar(32) DEFAULT NULL COMMENT '性别比率',\n" +
            "  `capture_chance` int(4) DEFAULT NULL COMMENT '捕获度',\n" +
            "  `initial_intimacy` int(4) DEFAULT NULL COMMENT '初始亲密度',\n" +
            "  `max_experience` bigint(13) DEFAULT NULL COMMENT '满级经验',\n" +
            "  `base_points` varchar(32) DEFAULT NULL COMMENT '基础点数',\n" +
            "  `remarks` varchar(256) DEFAULT NULL COMMENT '备注',\n" +
            "  `version_history` varchar(256) DEFAULT NULL COMMENT '历史版本',\n" +
            "  `set_data` varchar(256) DEFAULT NULL COMMENT '设置资料',\n" +
            "  `image` varchar(64) DEFAULT NULL COMMENT '图片',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=387 DEFAULT CHARSET=utf8;";

    public static String INSERT_SQL = "insert  into `pokemon_list_cn_tb`(`id`,`name`,`description`,`property`,`feature`,`hidden_feature`,`sort`,`height`,`weight`,`HP`,`attack`,`defense`,`special_attack`,`special_defense`,`speed`,`total`,`egg_group`,`hatch_step`,`sex_ratio`,`capture_chance`,`initial_intimacy`,`max_experience`,`base_points`,`remarks`,`version_history`,`set_data`,`image`) values " +
            "(1,'妙蛙种子','妙蛙种子（フシギダネ、Bulbasaur）是第一世代登场的宝可梦。','草 、毒','茂盛','叶绿素','种子宝可梦','0.7m / 2\\'04\\\"','6.9kg / 15.2lbs.',45,49,49,65,65,46,318,'怪兽 / 植物','20圈（5120步）','♂7 : ♀1',45,70,1059860,'特攻+1','妙蛙种子是唯一具有双属性的未进化初始精灵。\\n妙蛙种子是唯一可通过遗传习得花瓣舞的精灵。不过，其最终进化型妙蛙花可通过升级习得。','金·银\\n删除特殊种族值65，替换为特攻65、特防65。\\n红宝石·蓝宝石\\n增加特性茂盛。\\n黑·白\\n增加隐藏特性叶绿素。',NULL,'http://res.pokemon.name/common/pokemon/pdw/001.00.png')," +
            "(2,'妙蛙草','妙蛙草（フシギソウ、Ivysaur）是第一世代登场的宝可梦。','草 、毒','茂盛','叶绿素','种子宝可梦','1.0m / 3\\'03\\\"','13.0kg / 28.7lbs.',60,62,63,80,80,60,405,'怪兽 / 植物','20圈（5120步）','♂7 : ♀1',45,70,1059860,'特攻+1、特防+1',NULL,'金·银\\n删除特殊种族值80，替换为特攻80、特防80。\\n红宝石·蓝宝石\\n增加特性茂盛。\\n黑·白\\n增加隐藏特性叶绿素。',NULL,'http://res.pokemon.name/common/pokemon/pdw/002.00.png')," +
            "(3,'妙蛙花','妙蛙花（フシギバナ、Venusaur）是第一世代登场的宝可梦。','草 、毒','茂盛','叶绿素','种子宝可梦','2.0m / 6\\'07\\\"','100.0kg / 220.5lbs.',80,82,83,100,100,80,525,'怪兽 / 植物','20圈（5120步）','♂7 : ♀1',45,70,1059860,'特攻+2、特防+1','妙娃花是口袋妖怪 绿与口袋妖怪 叶绿的封面精灵。\\n妙蛙花与热带龙的身高与体重完全一致。\\n在日版口袋妖怪 红、口袋妖怪 绿、第二世代与第三世代的游戏中，妙蛙花背上的花有5片花瓣，与真正的大王花相同，而其它版本中则有6片花瓣。',NULL,NULL,'http://res.pokemon.name/common/pokemon/pdw/003.00.png')," +
            "(4,'小火龙','小火龙（ヒトカゲ、Charmander）是第一世代登场的宝可梦。','火','猛火','太阳','蜥蜴宝可梦','0.6m / 2\\'00\\\"','8.5kg / 18.7lbs.',39,52,43,60,60,65,309,'怪兽 / 龙','20圈（5120步）','♂7 : ♀1',45,70,1059860,'速度＋1','小火龙在火红·叶绿中可升级习得金属爪，而在第四世代与第五世代中仅能通过遗传习得。\\n这是因为火红·叶绿中首个道馆，尼比道馆，对小火龙是一大难关。','金·银\\n删除特殊种族值50，替换为特攻60、特防50。\\n红宝石·蓝宝石\\n增加特性猛火。\\n黑·白\\n增加隐藏特性太阳力量。\\n',NULL,'http://res.pokemon.name/common/pokemon/pdw/004.00.png')," +
            "(5,'火恐龙','火恐龙（リザード、Charmeleon）是第一世代登场的宝可梦。','火','猛火','太阳','火焰宝可梦','1.1m / 3\\'07\\\"','19.0kg / 41.9lbs.',58,64,58,80,65,40,405,'怪兽 / 龙','20圈（5120步）','♂7 : ♀1',45,70,1059860,'特攻+1、速度+1',NULL,'金·银\\n删除特殊种族值65，替换为特攻80、特防65。\\n红宝石·蓝宝石\\n增加特性猛火。\\n黑·白\\n增加隐藏特性太阳力量。',NULL,'http://res.pokemon.name/common/pokemon/pdw/005.00.png')," +
            "(6,'喷火龙','喷火龙（リザードン、Charizard）是第一世代登场的宝可梦。','火、飞行','猛火','太阳力量','火焰宝可梦','1.7m / 5\\'07\\\"','90.5kg / 199.5lbs.',78,84,78,109,85,100,534,'怪兽 / 龙','20圈（5120步）','♂7 : ♀1',45,70,1059860,'特攻+3','喷火龙是口袋妖怪 红与口袋妖怪 火红的封面精灵。\\n在游戏中，喷火龙与铁甲犀牛具有完全一致的叫声。\\n喷火龙与火暴兽具有完全一致的种族值。','金·银\\n删除特殊种族值85，替换为特攻109、特防85。\\n红宝石·蓝宝石\\n增加特性猛火。\\n黑·白\\n增加隐藏特性太阳力量。\\nX·Y\\n增加超进化“百万喷火龙X”。\\n增加超进化“百万喷火龙Y”。\\n',NULL,'http://res.pokemon.name/common/pokemon/pdw/006.00.png')," +
            "(7,'杰尼龟','杰尼龟（ゼニガメ、Squirtle）是第一世代登场的宝可梦。','水','激流','接雨盘','小龟宝可梦','0.5m / 1\\'08\\\"','9.0kg / 19.8lbs.',44,48,65,50,64,43,314,'怪兽 / 水中1','20圈（5120步）','♂7 : ♀1',45,70,1059860,'防御+1','杰尼龟与尼多郎、小灰怪、秃鹰小子在身高、体重上完全一致。\\n杰尼龟一族是当前环境下唯一可习得水泡而无法习得泡沫光线的精灵。而在第一世代中，杰尼龟一族可通过技能机器习得泡沫光线。','金·银\\n删除特殊种族值50，替换为特攻50、特防64。\\n红宝石·蓝宝石\\n增加特性激流。\\n黑·白\\n增加隐藏特性接雨盘。',NULL,'http://res.pokemon.name/common/pokemon/pdw/007.00.png')," +
            "(8,'卡咪龟','卡咪龟（カメール、Wartortle）是第一世代登场的宝可梦。','水','激流','接雨盘','乌龟宝可梦','1.0m / 3\\'03\\\"','22.5kg / 49.6lbs.',59,63,80,65,80,58,405,'怪兽 / 水中1','20圈（5120步）','♂7 : ♀1',45,70,1059860,'防御+1、特防+1',NULL,'金·银\\n删除特殊种族值65，替换为特攻65、特防80。\\n红宝石·蓝宝石\\n增加特性激流。\\n黑·白\\n增加隐藏特性接雨盘。',NULL,'http://res.pokemon.name/common/pokemon/pdw/008.00.png')," +
            "(9,'水箭龟','水箭龟（カメックス、Blastoise）是第一世代登场的宝可梦。','水','激流','接雨盘','甲壳宝可梦','1.6m / 5\\'03\\\"','85.5kg / 188.5lbs.',79,83,100,85,105,78,530,'怪兽 / 水中1','20圈（5120步）','♂7 : ♀1',45,70,1059860,'特防+3',NULL,'金·银\\n删除特殊种族值85，替换为特攻85、特防105。\\n红宝石·蓝宝石\\n增加特性激流。\\n黑·白\\n增加隐藏特性接雨盘。\\nX·Y\\n增加超进化“百万水箭龟”。\\n',NULL,'http://res.pokemon.name/common/pokemon/pdw/009.00.png')," +
            "(10,'绿毛虫','绿毛虫（キャタピー、Caterpie）是第一世代登场的宝可梦。','虫','鳞粉','逃足','芋虫宝可梦','0.3m / 1\\'00\\\"','2.9kg / 6.4lbs.',45,30,35,20,20,45,195,'虫','15圈（3840步）','♂1 : ♀1',255,70,1000000,'HP+1','绿毛虫与独角虫、鲤鱼王、宝宝丁、利牙鱼、代欧奇希斯攻击形态一样，拥有最低的特防种族值。它的特防种族值是20。','金·银\\n删除特殊种族值20，替换为特攻20、特防20。\\n红宝石·蓝宝石\\n增加特性鳞粉。\\n黑·白\\n增加隐藏特性逃足。',NULL,'http://res.pokemon.name/common/pokemon/pdw/010.00.png')";
}
