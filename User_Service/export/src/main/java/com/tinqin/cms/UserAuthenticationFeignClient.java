package com.tinqin.cms;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.tinqin.cms.operations.ChangeUserPasswordOperation.*;
import static com.tinqin.cms.operations.LoginOperation.*;
import static com.tinqin.cms.operations.RegisterOperation.*;


@FeignClient(name = "USER-AUTHENTICATION-SERVICE")
public interface UserAuthenticationFeignClient {
    @PostMapping("/auth/register")
    RegisterResponse registerUser(@RequestBody RegisterRequest request);

    @PostMapping("/auth/login")
    LoginResponse loginUser(@RequestBody LoginRequest request);

    @PatchMapping("/auth/change-password")
    ChangeUserPasswordResponse changePassword(@RequestBody ChangeUserPasswordRequest request);
}
