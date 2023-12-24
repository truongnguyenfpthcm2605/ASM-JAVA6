package com.poly.api;


import com.poly.impl.OrderDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class StatisticRestcontroller {

    private final OrderDetailServiceImpl orderDetailService;

    @GetMapping("/rest/api/statistic")
    public ResponseEntity<List<Object[]>> getStatistic() {
        Optional<List<Object[]>> listStas = orderDetailService.getStatisticCart();
        return listStas.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
