package com.tinqin.cms.controllers;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.tinqin.cms.processors.PaypalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Tag(
        name = "Payment service",
        description = "Payment service for providing employees with salaries"
)
@RequiredArgsConstructor
@RestController
@Validated
@RefreshScope
@Slf4j
@RequestMapping(path = "/payment")
public class PaymentController {
//    @Transactional
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "202", description = "Successfully gave a salary to an employee."),
//            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
//    })
//    @Operation(description = "Provided the employees debit card details will provide him with his deserved salary.",
//            summary = "Give salary to an employee.")
//    @PostMapping(path = "/salary")
//    public ResponseEntity<SalaryResponse> giveSalary(@Valid @RequestBody SalaryRequest request) {
//        return new ResponseEntity<>(salaryOperation.process(request), HttpStatus.ACCEPTED);
//    }

    private final PaypalService paypalService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @PostMapping("/create")
    public RedirectView createPayment(@RequestParam("method") String method, //paypal
                                      @RequestParam("amount") String amount,
                                      @RequestParam("currency") String currency, //USD
                                      @RequestParam("description") String description) {
        try {
            String cancelUrl = "http://localhost:8081/payment/cancel";
            String successUrl = "http://localhost:8081/payment/success";
            Payment payment = paypalService.createPayment(Double.valueOf(amount), currency, method, "sale", description, cancelUrl, successUrl);

            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return new RedirectView(links.getHref());
                }
            }

        } catch (PayPalRESTException e) {
            log.error("Error occurred during payment", e);
        }
        return new RedirectView("/payment/error");
    }

    @GetMapping("/success")
    public String paymentSuccess(@RequestParam("paymentId") String paymentId,
                                 @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                return "paymentSuccess";
            }
        } catch (PayPalRESTException e) {
            log.error("Error occurred during payment", e);
        }
        return "paymentSuccess";
    }

    @GetMapping("/cancel")
    public String paymentCancel() {
        return "paymentCancel";
    }

    @GetMapping("/error")
    public String paymentError() {
        return "paymentError";
    }
}
