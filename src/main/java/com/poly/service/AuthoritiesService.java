package com.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.poly.entity.Authorities;

public interface AuthoritiesService {
	Authorities save(Authorities authorities);
	Optional<Authorities> findById(Integer id);
	void deleteById(Integer id);
	List<Authorities> findAll();
	Page<Authorities> findAll(Pageable page);
	List<Authorities> findAll(Sort sort);
}
