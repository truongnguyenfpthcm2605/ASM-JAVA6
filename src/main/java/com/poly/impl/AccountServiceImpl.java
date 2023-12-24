package com.poly.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import com.poly.entity.Account;
import com.poly.repository.AccountRepository;
import com.poly.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository dao;
	@Override
	public Account save(Account account) {
		return dao.save(account);
	}

	@Override
	public Optional<Account> findById(String email) {
		return dao.findById(email);
	}

	@Override
	public void deleteById(String email) {
		dao.deleteById(email);
		
	}

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public Page<Account> findAll(Pageable page) {
		return dao.findAll(page);
	}

	@Override
	public List<Account> findAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Boolean existsById(String email) {
		return dao.existsById(email);
	}

}
