package com.poly.api;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.dto.MailModel;
import com.poly.entity.Account;
import com.poly.entity.Order;
import com.poly.entity.OrderDetails;
import com.poly.entity.Product;
import com.poly.impl.MailerServiceImpl;
import com.poly.impl.OrderDetailServiceImpl;
import com.poly.impl.OrderServiceImpl;
import com.poly.impl.ProductServiceImpl;
import com.poly.service.SessionService;
import com.poly.utils.Keyword;
import com.poly.utils.WriteBill;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class OrderRescontroller {
    private final OrderDetailServiceImpl orderDetailService;
    private final OrderServiceImpl orderService;
    private final ProductServiceImpl productService;
    private final SessionService sessionService;
    private final MailerServiceImpl mailerService;
    @Autowired
    ServletContext app;


    @PostMapping("/rest/api/cart/pay")
    public ResponseEntity<Order> getOrderPay(@RequestBody JsonNode orderData){
        Order order = create(orderData);
        return order!=null ? ResponseEntity.ok(order) : ResponseEntity.badRequest().build();

    }

    private Order create(JsonNode orderData){
        ObjectMapper mapper = new ObjectMapper();
        Order order = mapper.convertValue(orderData,Order.class);
        orderService.save(order);

        TypeReference<List<OrderDetails>> type = new TypeReference<>() {};
        List<OrderDetails> details
                = mapper.convertValue(orderData.get("orderDetails"), type).stream().peek(d -> d.setOrder(order)).toList();
        orderDetailService.saveAll(details);
        details.forEach( e -> {
            Product product = productService.findById(e.getProduct().getId()).get();
            product.setQuantitysold(product.getQuantitysold()+e.getQuanity());
            product.setQuanityfinal(product.getQuanityfinal()-e.getQuanity());
            productService.save(product);
        });
        Account account = sessionService.getAttribute(Keyword.accountSession);
        // Print Bill
        String paths = app.getRealPath("/document/Bill.docx");
        WriteBill.exportToWord(details,order,account,paths,productService);

        updateEmailSender();
        return order;

    }


    private void updateEmailSender() {
        Account account = sessionService.getAttribute(Keyword.accountSession);
        try {
            MailModel mailClient = new MailModel();
            mailClient.setTo(account.getEmail());
            mailClient.setSubject("Mail Xác Nhận Đơn Hàng");
            mailClient.setBody(" <div class=\"container\">\r\n"
                    + "        <h1 style=\"color: #080202; font-size: 50px; font-weight: 400;\">Cảm ơn bạn đã ủng hộ chúng tôi . \r\n"
                    + "        <p style=\"font-size: 30px; color: #C38154;\">Đơn hàng của bạn đã được chúng tôi xác nhận . Nếu có thay đổi chúng tôi sẽ phản hồi qua mail của bạn  </h1>\r\n"
                    + "\r\n" + "        <div style=\"background-color:\r\n"
                    + "         #C38154; width: 60%;height: 10px; margin: 30px auto; border-radius: 10px;box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;\"></div>\r\n"
                    + "        <h1 style=\"font-size: 50px; text-align: center; color: #C38154;\">\r\n"
                    + "          HTMobile\r\n" + "        </h1>\r\n"
                    + "        <h1 style=\"color: #B31312;\">Đây là hóa đơn của bạn</h1>\r\n" + "\r\n" + "        \r\n"
                    + "    </div>");
            File fileBill = new File(app.getRealPath("/document/Bill.docx"));
            List<File> list = new ArrayList<>();
            list.add(fileBill);
            mailClient.setFiles(list);
            mailerService.queue(mailClient);
            MailModel mailAdmin = new MailModel();
            mailAdmin.setTo("sakuramyaha678@gmail.com");
            mailAdmin.setSubject("Đơn Mua Hàng Từ  " + account.getFullname());
            mailAdmin.setBody(" <div class=\"container\">\r\n"
                    + "        <h1 style=\"color: #080202; font-size: 50px; font-weight: 400;\">Hóa Đơn Mua Hàng từ Khách Hàng . \r\n"
                    + "        <p style=\"font-size: 30px; color: #C38154;\">Đơn hàng đã được xác nhận mua bởi "
                    + account.getFullname() + "  </h1>\r\n" + "\r\n" + "        <div style=\"background-color:\r\n"
                    + "         #C38154; width: 60%;height: 10px; margin: 30px auto; border-radius: 10px;box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;\"></div>\r\n"
                    + "        <h1 style=\"font-size: 50px; text-align: center; color: #C38154;\">\r\n"
                    + "          HTMobile\r\n" + "        </h1>\r\n"
                    + "        <h1 style=\"color: #B31312;\">Đây là hóa đơn của  " + account.getFullname()
                    + " </h1>\r\n" + "\r\n" + "        \r\n" + "    </div>");
            mailAdmin.setFiles(list);
            mailerService.queue(mailAdmin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
