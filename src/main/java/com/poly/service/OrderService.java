package com.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.poly.entity.Order;

public interface OrderService {
	Order save(Order order);
	Optional<Order> findById(Integer id);
	void deleteById(Integer id);
	List<Order> findAll();
	List<Order> findAll(Sort sort);
	Page<Order> findAll(Pageable page);
}
