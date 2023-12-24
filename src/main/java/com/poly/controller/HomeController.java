package com.poly.controller;

import com.poly.impl.ProductServiceImpl;
import com.poly.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {


	private final ProductServiceImpl productService;
	private  final SessionService sessionService;
	@GetMapping("/index")
	private String home(Model model) {
		model.addAttribute("listTop10",productService.findTop10ByViewsDesc());
		model.addAttribute("listCategory", productService.findProductByCategory(1));
		return "/pages/index";
	}

	@GetMapping("/admin/view")
	private String admin() {
		return "/admin/Statistic";
	}

	@GetMapping("/cart/show")
	public String show(){
		return "/pages/Cart";
	}

	@GetMapping("/cart/order")
	private String order(Model model ){
		model.addAttribute("session", sessionService);
		return "/pages/Order";
	}

	@GetMapping("/thanks")
	private String thanks( ){

		return "/pages/thank";
	}
}
