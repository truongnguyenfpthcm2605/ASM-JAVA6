package com.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.poly.entity.Roles;

public interface RolesService {
	Roles save(Roles roles);
	Optional<Roles> findById(String id);
	void deleteById(String id);
	List<Roles> findAll();
	Page<Roles> findAll(Pageable page);
	List<Roles> findAll(Sort sort);
}
