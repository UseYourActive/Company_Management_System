package com.tinqin.cms.controllers;

import com.tinqin.cms.operations.ChangeUserPasswordOperation;
import com.tinqin.cms.operations.LoginOperation;
import com.tinqin.cms.operations.RefreshTokenOperation;
import com.tinqin.cms.operations.RegisterOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.tinqin.cms.operations.ChangeUserPasswordOperation.*;
import static com.tinqin.cms.operations.LoginOperation.*;
import static com.tinqin.cms.operations.RefreshTokenOperation.*;
import static com.tinqin.cms.operations.RegisterOperation.*;

@RequiredArgsConstructor
@RestController
@Validated
@RefreshScope
@Slf4j
@CrossOrigin
@RequestMapping("/auth")
public class AuthenticationController {
    private final RegisterOperation registerOperation;
    private final LoginOperation loginOperation;
    private final RefreshTokenOperation refreshTokenOperation;
    private final ChangeUserPasswordOperation changeUserPasswordOperation;

    //region GET
    //endregion

    //region POST
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully create a user."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request creates a new user that does not exist in the database yet.",
            summary = "Creates a new user.")
    @PostMapping(path = "/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        return new ResponseEntity<>(registerOperation.process(request), HttpStatus.CREATED);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing in the database user.",
            summary = "Finds a user by username.")
    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        request = LoginRequest.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        return new ResponseEntity<>(loginOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully refreshed token"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "Refreshes the authentication token.",
            summary = "Refresh authentication token.")
    @PostMapping(path = "/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        RefreshTokenResponse response = refreshTokenOperation.process(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Method is not necessary
    @PostMapping(path = "/logout")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged out."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "Logs out the currently authenticated user.",
            summary = "Logout user.")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok().build();
    }
    //endregion

    //region PUT
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Password changed successfully."),
            @ApiResponse(responseCode = "400",description = "Current or new password is empty."),
            @ApiResponse(responseCode = "403",description = "Current password is invalid or token is blacklisted.")
    })
    @PatchMapping(path = "/change-password")
    @Operation(description = "Changes the password of the currently logged user and ends current session.",
            summary = "Changes current password and invalidates current jwt.")
    //@SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ChangeUserPasswordResponse> changePassword(@RequestBody @Valid ChangeUserPasswordRequest input) {
        return ResponseEntity.ok(changeUserPasswordOperation.process(input));
    }
    //endregion

    //region DELETE
    //endregion

    // Log method entry
    @ModelAttribute
    public void logMethodEntry(HttpServletRequest request) {
        log.info("Entering {}", request.getRequestURI());
    }

    // Log method exit
    @ModelAttribute
    public void logMethodExit(HttpServletRequest request) {
        log.info("Exiting {}", request.getRequestURI());
    }
}
