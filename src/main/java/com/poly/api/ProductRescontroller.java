package com.poly.api;

import com.poly.entity.Product;
import com.poly.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProductRescontroller {

    private final ProductServiceImpl productServiceImpl;

    @GetMapping("/rest/api/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
        Optional<Product> productOptional = productServiceImpl.findById(id);
        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/rest/api/product")
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productServiceImpl.findAll());
    }
}
