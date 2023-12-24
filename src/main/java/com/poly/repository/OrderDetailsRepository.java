package com.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.OrderDetails;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

    @Query("select o.product.title , sum(o.price), sum(o.quanity ), avg(o.quanity*o.price) " +
            "from OrderDetails o group by o.product.title order by avg(o.quanity*o.price) desc ")
    Optional<List<Object[]>> getStatisticCart();
}
