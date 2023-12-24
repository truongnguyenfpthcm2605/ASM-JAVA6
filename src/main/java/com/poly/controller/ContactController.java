package com.poly.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContactController {
    @GetMapping("contact")
    public String contact(){
        return "/pages/Contact";
    }

    @GetMapping("/contact/report")
    @ResponseBody
    public ResponseEntity<String> report(@RequestParam("content") String content){
        return ResponseEntity.ok("Gửi Thành Công");
    }
}
