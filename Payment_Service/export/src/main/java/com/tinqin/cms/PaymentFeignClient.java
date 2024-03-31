package com.tinqin.cms;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentFeignClient {
//    @RequestLine("POST /payment/salary")
//    SalaryResponse makeCharge(SalaryRequest input);
}
