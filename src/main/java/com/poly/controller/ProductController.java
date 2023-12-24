package com.poly.controller;

import java.util.List;
import java.util.Optional;

import com.poly.entity.GroupProduct;
import com.poly.impl.GroupProductServiceImpl;
import com.poly.service.SessionService;
import com.poly.utils.Keyword;
import com.poly.utils.SortAnPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.poly.entity.Product;
import com.poly.impl.ProductServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("store")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;
    private final GroupProductServiceImpl groupProductService;
    private final SessionService sessionService;
    private final Integer pageSize = 9;
    private final  Integer pageStar =  0;



    @ModelAttribute("group")
    private List<GroupProduct> getGroupProducts(){
        sessionService.setAttribute(Keyword.search,null);
        return groupProductService.findAll();
    }

    @GetMapping("")
    private String store(Model model){
        Page<Product> page = productServiceImpl.findAll(SortAnPage.getPage(pageStar,pageSize));
        model.addAttribute("products", page);
        return "/pages/Store";
    }

    @GetMapping("/detail/{id}")
    private String detail(Model model, @PathVariable("id") Integer id){
        Product product = productServiceImpl.findById(id).get();
        product.setViews(product.getViews()+1);
        productServiceImpl.save(product);
        model.addAttribute("product",product);
        model.addAttribute("productList",
                productServiceImpl.findPriceMinAndMax(product.getPrice()- 2000000,product.getPrice()+3000000));
        model.addAttribute("thumnail", product.getThumnail().split("-"));
        return "/pages/DetailProduct";
    }



    @GetMapping("page")
    public String Page(Model model,@RequestParam("p") Optional<Integer> p) {

        String keyw = sessionService.getAttribute(Keyword.search);
        System.out.println(p + keyw);
        if(keyw!=null) {
            Page<Product> page = productServiceImpl.findByKeywords("%"+(String) keyw+"%", SortAnPage.getPage(p.orElse(0),pageSize));
            model.addAttribute("products", page);

        }else {
            model.addAttribute("products", productServiceImpl.findAll(SortAnPage.getPage(p.orElse(0),pageSize)));

        }
        return "/pages/Store";
    }

    @PostMapping("search")
    private String search(Model model,@RequestParam("search") Optional<String> keyword) {
        sessionService.setAttribute(Keyword.search, keyword.orElse(""));
        Page<Product> page = productServiceImpl.findByKeywords("%"+keyword.orElse("")+"%", SortAnPage.getPage(0,9));
        model.addAttribute("products", page);
        model.addAttribute("keywords", keyword.orElse(""));
        return "/pages/Store";
    }

    @PostMapping("sort")
    private String sort(Model model ,@RequestParam("sort") Integer sort) {
        Page<Product> page =null;
        switch (sort) {
            case 0-> page = getPage(pageStar,SortAnPage.getSort("createDate"),pageSize);
            case 1-> page = getPage(pageStar,SortAnPage.getSort("price"),pageSize);
            case 2-> page = getPage(pageStar,SortAnPage.getSortUp("price"),pageSize);
        }
        model.addAttribute("products", page);
        model.addAttribute("check", sort);
        return "/pages/Store";
    }

    @GetMapping("/group/{id}")
    private String productByGroup(@PathVariable("id") Integer id, Model model){
        model.addAttribute("products", productServiceImpl.findByGroupToPage(id,SortAnPage.getPage(pageStar,pageSize)));
        return "/pages/Store";
    }

    public Page<Product> getPage(Integer number, Sort sort,int pageSize){
        Pageable pageable = PageRequest.of(number, pageSize,sort);
        return productServiceImpl.findAll(pageable);

    }





}
