package com.tinqin.cms;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import static com.tinqin.cms.operations.SendEmailOperation.*;

@FeignClient(name = "NOTIFICATION-SERVICE")
public interface NotificationFeignClient {
    @RequestLine("POST /email/send")
    SendEmailOperationResponse sendEmail(SendEmailOperationRequest request);
}
