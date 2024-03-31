package com.tinqin.cms;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "LEAVE-SERVICE")
public interface LeaveFeignClient {
}
