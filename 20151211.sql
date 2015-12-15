CREATE TABLE t_website(
   site_id INT AUTO_INCREMENT PRIMARY KEY,
   site_name VARCHAR(256) DEFAULT "" COMMENT "站点名字",
   site_url VARCHAR(256) DEFAULT "" COMMENT "站点url",
   list_range VARCHAR(64) DEFAULT "" COMMENT "列表路径",
   title_node VARCHAR(64) DEFAULT "" COMMENT "标题节点",
   date_node VARCHAR(64) DEFAULT "" COMMENT "日期节点",
   pic_node VARCHAR(64) DEFAULT "" COMMENT "图片节点",
   web_record_content_node VARCHAR(256) DEFAULT "" COMMENT "正文节点",
   site_category VARCHAR(64) DEFAULT "" COMMENT "站点类型",
   site_category_id INT NOT NULL COMMENT "类型id",
   create_date  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   update_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='站点表'


INSERT INTO t_website(site_name,site_url,list_range,title_node,date_node,pic_node,web_record_content_node,site_category,site_category_id) VALUES("安徽网-房产","http://www.ahwang.cn/house/@@@page@@@.shtml","<div id=category class=clearfix>","div[NO]/h3/a","div[NO]/div/div[2]/div/div/div/span","div[NO]/div/div[1]/a/img","<p class=describe>;<div class=article-content fontSizeBig>","房产",1);

SELECT * FROM t_website;
DROP TABLE t_website

ALTER TABLE t_website ADD COLUMN web_record_content VARCHAR(256)
ALTER TABLE t_website MODIFY COLUMN web_record_content  web_record_content_path VARCHAR(256)


UPDATE t_website SET web_record_content = "<p class='describe'>;<div class='article-content fontSizeBig'>" WHERE site_id = 1;

UPDATE t_website SET list_range	 = "<div id='category' class='clearfix'>" WHERE site_id = 1;

<DIV id="category" class="clearfix">

DROP TABLE t_website


CREATE TABLE t_site_category(
   category_id INT AUTO_INCREMENT PRIMARY KEY,
   category_name VARCHAR(256) NOT NULL DEFAULT "" COMMENT "类别",
   create_date  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   update_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='站点类型表' 

INSERT INTO t_site_category(category_name) VALUES("房产");


CREATE TABLE t_web_record(
   web_record_id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
   title VARCHAR(256) NOT NULL DEFAULT "" COMMENT "新闻标题",
   href VARCHAR(256) NOT NULL DEFAULT "" COMMENT "新闻链接",
   content VARCHAR(1) DEFAULT "" COMMENT "新闻内容，不存储实际内容",
   source INTEGER NOT NULL DEFAULT 0 COMMENT "来源于哪个站点",
   img1 VARCHAR(64) DEFAULT "" COMMENT "图片1",
   img2 VARCHAR(64) DEFAULT "" COMMENT "图片2",
   img3 VARCHAR(64) DEFAULT "" COMMENT "图片3",
   STATUS INT DEFAULT 0 COMMENT "是否爬取过，0：没有；1：爬取",
   create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新闻创建时间',
   crawl_date  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '爬取时间',
   update_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '爬取时间'
);

SELECT * FROM t_web_record

DROP TABLE t_web_record





