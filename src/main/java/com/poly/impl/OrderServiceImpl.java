package com.poly.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poly.entity.Order;
import com.poly.repository.OrderRepository;
import com.poly.service.OrderService;
@Service
public class OrderServiceImpl  implements OrderService{
	
	@Autowired
	OrderRepository dao;
	@Override
	public Order save(Order order) {
		return dao.save(order);
	}

	@Override
	public Optional<Order> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<Order> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Order> findAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Order> findAll(Pageable page) {
			return dao.findAll(page);
	}

}
