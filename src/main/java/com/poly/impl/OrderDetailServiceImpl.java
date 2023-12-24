package com.poly.impl;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poly.entity.OrderDetails;
import com.poly.repository.OrderDetailsRepository;
import com.poly.service.OrderdetailsService;
@Service
public class OrderDetailServiceImpl  implements OrderdetailsService{
	
	@Autowired
	OrderDetailsRepository dao;
	@Override
	public OrderDetails save(OrderDetails orderdetails) {
		return dao.save(orderdetails);
	}

	@Override
	public Optional<OrderDetails> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<OrderDetails> findAll() {
		return dao.findAll();
	}

	@Override
	public Page<OrderDetails> findAll(Pageable page) {
	return dao.findAll(page);
	}

	@Override
	public List<OrderDetails> findAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Optional<List<Object[]>> getStatisticCart() {
		return dao.getStatisticCart();
	}

	@Override
	public List<OrderDetails> saveAll(List<OrderDetails> list) {
		return dao.saveAll(list);
	}

}
