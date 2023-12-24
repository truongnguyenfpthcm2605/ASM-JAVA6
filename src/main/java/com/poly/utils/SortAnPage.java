package com.poly.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;


public class SortAnPage{
		
	public static Sort getSort(String keySort) {
        return Sort.by(Direction.DESC, keySort);
	}

	public static Sort getSortUp(String keySort) {
		return Sort.by(Direction.ASC, keySort);
	}
	public static Pageable getPage(Integer number, Integer pageSize){
        return PageRequest.of(number, pageSize);
	}
	
	
	public static Pageable getPage(Integer number,Integer pageSize,Sort sort){
        return PageRequest.of(number, pageSize,sort);
	}
}
