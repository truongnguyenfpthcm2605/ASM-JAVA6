package com.poly.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poly.entity.Roles;
import com.poly.repository.RolesRepository;
import com.poly.service.RolesService;
@Service
public class RolesServiceImpl implements RolesService{
	@Autowired
	RolesRepository dao;
	@Override
	public Roles save(Roles roles) {
		return dao.save(roles);
	}

	@Override
	public Optional<Roles> findById(String id) {
		return dao.findById(id);
	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(id);	
	}

	@Override
	public List<Roles> findAll() {
		return dao.findAll();
	}

	@Override
	public Page<Roles> findAll(Pageable page) {
		return dao.findAll(page);
	}

	@Override
	public List<Roles> findAll(Sort sort) {
		return dao.findAll(sort);
	}

}
