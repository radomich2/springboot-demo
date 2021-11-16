package com.opuscapita.demo.products.model;

import com.opuscapita.demo.products.list.ProductSummaryDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

    //@EntityGraph(attributePaths = {"category"})
    List<Product> findAll();



    @Query("SELECT p FROM Product p JOIN FETCH p.category")
    List<Product> getAllWithCategories(Sort sort);



    // findAll         - SELECT products.*
    // By              - WHERE
    // ProductNameLike -  products.product_name LIKE ?
    // And             -  AND
    // CategoryIdIn    -  products.category_id IN(?)
    // OrderByIdDesc   - ORDER BY ...
    //
    // See all method naming conventions at:
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
    //
    @EntityGraph(attributePaths = {"category"})
    List<Product> findAllByProductNameLikeAndCategoryIdInOrderByIdDesc(String productName, Collection<Long> categoryId);


    // ProductSummaryDto interface has no implementation!
    @Query("SELECT CONCAT(p.productName, ' #', p.id) AS productSummary," +
        "          CONCAT(c.categoryName, ' #', c.id)  AS categorySummary" +
        " FROM Product p INNER JOIN p.category c")
    List<ProductSummaryDto> getProductsAsSummary();
}
