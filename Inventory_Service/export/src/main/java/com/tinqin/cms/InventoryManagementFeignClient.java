package com.tinqin.cms;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;

@Headers({
        "Content-Type: application/json"
})
@FeignClient()
public interface InventoryManagementFeignClient {
}
