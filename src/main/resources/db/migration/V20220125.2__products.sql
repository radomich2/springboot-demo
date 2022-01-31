CREATE TABLE products (
  id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  version bigint(20) unsigned NOT NULL,
  category_id bigint(20) unsigned NOT NULL,
  product_name varchar(100) NOT NULL,
  description text NOT NULL,
  price decimal(12,2) NOT NULL,
  created_at datetime NOT NULL,
  updated_at datetime DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_products_category_id FOREIGN KEY (category_id) REFERENCES categories (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Products';

