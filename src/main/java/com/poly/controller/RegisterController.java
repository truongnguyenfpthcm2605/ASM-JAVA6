package com.poly.controller;

import com.poly.entity.Account;
import com.poly.impl.AccountServiceImpl;
import com.poly.impl.MailerServiceImpl;
import com.poly.utils.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class RegisterController {

    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    MailerServiceImpl mailerService;

    private final String code = Keyword.keyCodeRandom();
    private final String message = "message";

    @GetMapping("/auth/register/form")
    private String register(Model model){
        model.addAttribute("account", new Account());
        return "/auth/Register";
    }

    @PostMapping("/auth/register")
    private String create( Model model,@Validated  @ModelAttribute("account") Account account, Errors errors
    ,@RequestParam("keyemail") String keymail, @RequestParam("confirm") String confirm){

        if(errors.hasErrors()){
            model.addAttribute(message,"vui lòng nhập chính xác thông tin");
        }else{
           if(!account.getPassword().equalsIgnoreCase(confirm)){
               model.addAttribute("cofirmError","Xác nhận mật khẩu không chính xác");
           }else if(!code.equalsIgnoreCase(keymail)){
               model.addAttribute("keyError","Mã xác thực không chính xác");
           }else{
                if(accountService.existsById(account.getEmail())){
                    model.addAttribute(message,"Email này đã tồn tại");
                }else{
                    accountService.save(account);
                    return "redirect:/index";
                }
           }
        }

        return  "/auth/Register";
    }

    @GetMapping("/auth/register/mail")
    @ResponseBody
    public ResponseEntity<String> sendMail(@RequestParam("codemail") Optional<String> email) {
        mailerService.queue(email.orElse("abc@gmail.com"), "Mã Xác Nhận Từ HTMobile",
                "    <div style=\"width:80%; margin:0 auto;text-align: center ;\">\r\n"
                        + "            <h1 style=\"color:#080202 ;\">HTMobile</h1>\r\n"
                        + "            <p >Dùng mã này để xác minh địa chỉ email của bạn trên Facebook</p>\r\n"
                        + "            <p>Xin chào Bạn,Chúng tôi cần xác minh địa chỉ email của bạn để đảm bảo là có thể liên hệ với bạn sau khi xem xét ID.</p>\r\n"
                        + "           \r\n"
                        + "            <p>Chúng tôi cần xác minh địa chỉ email của bạn để đảm bảo là có thể liên hệ với bạn sau khi xem xét ID.</p>\r\n"
                        + "            <h5>Mã xác nhận</h5>\r\n" + "             <h2 style=\"color: #116D6E ;\">" + code
                        + "</h2>    \r\n" + "             <br>\r\n"
                        + "              <p style=\"font-size: 15px;font-weight: 200;\">Tin nhắn này được gửi tới bạn theo yêu cầu của HTMobile.\r\n"
                        + "                HTMobile © 2023 All rights re6served. Privacy Policy|T&C|System Status</p>\r\n"
                        + "    </div> ");
        return ResponseEntity.ok("Mã xác nhận đã được gửi đến email.");
    }
}
