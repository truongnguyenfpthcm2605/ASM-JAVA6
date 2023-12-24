package com.poly.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.poly.entity.OrderDetails;

public interface OrderdetailsService {
	OrderDetails save(OrderDetails orderdetails);
	Optional<OrderDetails> findById(Integer id);
	void deleteById(Integer id);
	List<OrderDetails> findAll();
	Page<OrderDetails> findAll(Pageable page);
	List<OrderDetails> findAll(Sort sort);

	Optional<List<Object[]>> getStatisticCart();

	List<OrderDetails> saveAll(List<OrderDetails> list);

}
