package com.poly.controller;

import com.poly.config.AccountDetailService;
import com.poly.service.ParamService;
import com.poly.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final HttpServletRequest request;
    private final SessionService sessionService;
    private final ParamService paramService;
    private final AccountDetailService accountDetailService;

    @GetMapping("/auth/login/form")
    private String form() {
        return "/auth/login";
    }

    @GetMapping("/auth/login/success")
    private String success(Model model) {
        return "redirect:/auth/login/form";
    }


    @GetMapping("/auth/login/error")
    private String error(RedirectAttributes redirectAttributes ) {
        redirectAttributes.addFlashAttribute("message", "Đăng Nhập Thất Bại");
        return "redirect:/auth/login/form";
    }


    @GetMapping("/auth/logoff/success")
    private String logout() {
        return "redirect:/index";
    }

    @GetMapping("/auth/access/denied")
    private String denied(Model model) {
        model.addAttribute("message", "Không có quyền truy cập");
        return "/auth/login";
    }


    @GetMapping("/oauth2/login/success")
    public String successOauth2(@AuthenticationPrincipal OAuth2User oauth2, Model model ) {
        accountDetailService.loginFormOAuth2(oauth2);
        return "redirect:/auth/login/form";
    }



}
