package com.poly.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.poly.entity.Authorities;
import com.poly.repository.AuthoritiesRepository;
import com.poly.service.AuthoritiesService;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService  {

	@Autowired
	AuthoritiesRepository dao;
	
	@Override
	public Authorities save(Authorities authorities) {
		return dao.save(authorities);
	}

	@Override
	public Optional<Authorities> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);	
	}

	@Override
	public List<Authorities> findAll() {
		return dao.findAll();
	}

	@Override
	public Page<Authorities> findAll(Pageable page) {
		return dao.findAll(page);
	}

	@Override
	public List<Authorities> findAll(Sort sort) {
		return dao.findAll(sort);
	}

}
