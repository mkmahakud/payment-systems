package com.ecm.util.paymentsystems.paypal.controller;


import com.ecm.util.paymentsystems.paypal.entity.Order;
import com.ecm.util.paymentsystems.paypal.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class PaypalController {

    private final PaypalService paypalService;

    public static final String successUrl = "http://localhost:8080/pay/success";
    public static final String cancelUrl = "http://localhost:8080/pay/cancel";

    public PaypalController(PaypalService paypalService) {
        this.paypalService = paypalService;
    }

    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") Order theOrder) throws PayPalRESTException {
        try {
            Payment thePayment = paypalService.createPayment(theOrder.getPrice(), theOrder.getCurrency(),
                    theOrder.getMethod(), theOrder.getIntent(), theOrder.getDescription(), cancelUrl, successUrl);
            for (Links links: thePayment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    System.out.println(links.getHref());
                    return "redirect:"+links.getHref();
                }
            }
        }
        catch (PayPalRESTException payPalRESTException) {
            payPalRESTException.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/success")
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

}