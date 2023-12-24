package com.poly.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p where p.group.id = :id ")
    public List<Product> findProductByCategory(@Param("id")Integer id);

    @Query("SELECT o FROM Product o WHERE o.title LIKE :keywords or o.group.title Like :keywords ")
    Page<Product> findByKeywords(@Param("keywords") String  keywords, Pageable pageable);

    @Query("select count(o.quantitysold) from Product o")
    Integer getTotalQuantitySold();

    @Query("SELECT o FROM Product o WHERE o.title LIKE :keywords or o.group.title Like :keywords ")
    List<Product> findByKeywordsSearch(@Param("keywords") String key);


    @Query("select o from Product o where MONTH(o.createDate) = ?1")
    Optional<List<Product>> findByProductMonth(Integer month);


    @Query("SELECT  p FROM Product p ORDER BY p.views DESC")
    List<Product> findTop10ByViewsDesc();

    @Query("select p from Product p where p.group.id = :id ")
    public Page<Product> findByGroupToPage(Integer id, Pageable pageable);

    @Query("select p from Product p where p.price between :min and :max ")
    List<Product> findPriceMinAndMax(@Param("min")Double min,@Param("max")double Max);
}
