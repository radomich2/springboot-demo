CREATE TABLE categories (
  id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  category_name varchar(100) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Categories';
