# ORM

Object–Relational Mapping - it's general programming technique (not a particular framework) that allows to work with
**relational database structures** (tables, relations, SQL queries) by using
**object-oriented programming** concepts (objects, associations, JPQL queries).

- encapsulates db structures details in one place
- allows working with plain objects
- provides a query language which is separated from specific SQL dialect
- it's not serialization (can perform queries on each field)
- provides lifecycle events (on persist, update, remove)

---

# JPA

Jakarta Persistence API - it's Java industry standard specification of Object–Relational Mapping for.
It's a set of annotations and interfaces that creates common contract of how object-relational mapping
should be used by Java programmers.
You can find them in `javax.persistence.*` package).

Example implementations:
- EclipseLink - reference JPA implementation
- Hibernate - most popular JPA implementation
- there are many others not only for Java language

Each implementation must provide all features specified in JPA.
Also might implement some more features beyond the standard.

---
# Demo

```SQL
CREATE TABLE `categories` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Categories';

CREATE TABLE `products` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `version` bigint(20) unsigned NOT NULL,
  `category_id` bigint(20) unsigned DEFAULT NULL,
  `product_name` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `products_category_FK` (`category_id`),
  CONSTRAINT `products_category_FK` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Products';
```


- DataSource and JPA [configuration](src/main/resources/application.properties)
- Entities:
  [Product](src/main/java/com/opuscapita/demo/products/model/Product.java) /
  [Category](src/main/java/com/opuscapita/demo/products/model/Product.java)
- Product CRUD [REST controller](src/main/java/com/opuscapita/demo/products/product/ProductController.java)
- Product service [on bare JPA](src/main/java/com/opuscapita/demo/products/product/ProductServiceWithJpa.java)
- Product service [on Spring repository](src/main/java/com/opuscapita/demo/products/product/ProductServiceWithRepositories.java)
- Product list [REST controller](src/main/java/com/opuscapita/demo/products/list/ProductListController.java)
