package com.poly.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.poly.entity.Product;

public interface ProductService {
	Product save(Product product);
	Optional<Product> findById(Integer id);
	void deleteById(Integer id);
	List<Product> findAll();
	List<Product> findAll(Sort sort);
	Page<Product> findAll(Pageable page);

	List<Product> findProductByCategory(Integer id);

	Page<Product> findByKeywords( String  keywords,Pageable pageable);

	Integer getTotalQuantitySold();
	List<Product> findByKeywordsSearch(String key);
	Optional<List<Product>> findByProductMonth(Integer month);

	List<Product> findTop10ByViewsDesc();

	Page<Product> findByGroupToPage(Integer id, Pageable pageable);

	List<Product> findPriceMinAndMax(Double min,double Max);
}
