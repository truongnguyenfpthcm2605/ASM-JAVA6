package com.poly.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import com.poly.entity.Product;
import com.poly.repository.ProductRepository;
import com.poly.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository dao;

	@Override
	public Product save(Product product) {
		return dao.save(product);
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

	@Override
	//@Cacheable("Product")
	public List<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Product> findAll(Sort sort) {
			return dao.findAll(sort);
	}

	@Override
	public Page<Product> findAll(Pageable page) {
		return dao.findAll(page);
	}

	@Override
	public List<Product> findProductByCategory(Integer id) {
		return dao.findProductByCategory(id);
	}

	@Override
	public Page<Product> findByKeywords(String keywords, Pageable pageable) {
		return dao.findByKeywords(keywords,pageable);
	}

	@Override
	public Integer getTotalQuantitySold() {
		return dao.getTotalQuantitySold();
	}

	@Override
	public List<Product> findByKeywordsSearch(String key) {
		return dao.findByKeywordsSearch(key);
	}

	@Override
	public Optional<List<Product>> findByProductMonth(Integer month) {
		return dao.findByProductMonth(month);
	}



	@Override
	public List<Product> findTop10ByViewsDesc() {
		return dao.findTop10ByViewsDesc();
	}

	@Override
	public Page<Product> findByGroupToPage(Integer id, Pageable pageable) {
		return dao.findByGroupToPage(id,pageable);
	}

	@Override
	public List<Product> findPriceMinAndMax(Double min, double Max) {
		List<Product> originalList = dao.findPriceMinAndMax(min, Max);
		int limit = 4;
		int endIndex = Math.min(limit, originalList.size());
		return originalList.subList(0, endIndex);
	}


}
