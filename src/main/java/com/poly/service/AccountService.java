package com.poly.service;

import com.poly.entity.Account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface AccountService {
	Account save(Account account);
	Optional<Account> findById(String email);
	void deleteById(String email);
	List<Account> findAll();	
	Page<Account> findAll(Pageable pageable);
	List<Account> findAll(Sort sort);

	Boolean existsById(String email);
	
	
}
