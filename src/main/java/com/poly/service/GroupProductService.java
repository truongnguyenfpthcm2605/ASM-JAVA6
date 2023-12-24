package com.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.poly.entity.GroupProduct;

public interface GroupProductService {
	GroupProduct save(GroupProduct groupProduct);
	Optional<GroupProduct> findById(Integer id);
	void deleteById(Integer id);
	List<GroupProduct> findAll();
	Page<GroupProduct> findAll(Pageable page);
	List<GroupProduct> findAll(Sort sort);
}
