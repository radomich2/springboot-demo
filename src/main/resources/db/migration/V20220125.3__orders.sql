CREATE TABLE orders (
  id varchar(50) NOT NULL,
  version bigint(20) unsigned NOT NULL,
  description varchar(200) NOT NULL,
  total_price decimal(12,2) NOT NULL,
  created_at datetime NOT NULL,
  updated_at datetime NOT NULL,
  PRIMARY KEY (id)
)
ENGINE=InnoDB;

CREATE TABLE order_items (
  order_id varchar(50) NOT NULL,
  product_id bigint(20) unsigned NOT NULL,
  unit_price decimal(12,2) NOT NULL,
  quantity int(11) unsigned NOT NULL,
  PRIMARY KEY (order_id, product_id),
  CONSTRAINT FK_order_items_order_id FOREIGN KEY (order_id) REFERENCES orders (id),
  CONSTRAINT FK_order_items_product_id FOREIGN KEY (product_id) REFERENCES products (id)
) ENGINE=InnoDB;

CREATE TABLE sequence_numbers (
  sequence_name varchar(50) NOT NULL,
  next_value bigint(20) NOT NULL,
  updated_at datetime NOT NULL,
  PRIMARY KEY (sequence_name)
) ENGINE=InnoDB;

INSERT INTO sequence_numbers (sequence_name, next_value, updated_at) VALUES ('ORDER_NUMBER', 1, NOW());
