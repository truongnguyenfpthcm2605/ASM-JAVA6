package com.poly.impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poly.entity.GroupProduct;
import com.poly.repository.GroupProductRepository;
import com.poly.service.GroupProductService;
@Service
@RequiredArgsConstructor
public class GroupProductServiceImpl implements GroupProductService {

	private final GroupProductRepository dao;

	@Override
	public GroupProduct save(GroupProduct groupProduct) {
		return dao.save(groupProduct);
	}

	@Override
	public Optional<GroupProduct> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

	@Override
	//@Cacheable("Group")
	public List<GroupProduct> findAll() {
		return dao.findAll();
	}

	@Override
	public Page<GroupProduct> findAll(Pageable page) {
		return dao.findAll(page);
	}

	@Override
	public List<GroupProduct> findAll(Sort sort) {
		return dao.findAll(sort);
	}
}
