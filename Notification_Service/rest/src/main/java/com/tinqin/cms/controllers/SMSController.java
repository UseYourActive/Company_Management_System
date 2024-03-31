package com.tinqin.cms.controllers;

import com.tinqin.cms.operations.SendSMSOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "SMS Controller",
        description = "Controller specifically used for operations associated with sms"
)
@RequiredArgsConstructor
@RestController
@Validated
@RefreshScope
@Slf4j
@CrossOrigin
@RequestMapping(path = "/sms")
public class SMSController {
    private final SendSMSOperation sendSMSOperation;

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sent an sms."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request sends an sms to the specified location with text attached to it.",
            summary = "Sends an sms.")
    @PostMapping(path = "/send")
    public ResponseEntity<SendSMSOperation.SendSMSOperationResponse> sendSms(@Valid @RequestBody SendSMSOperation.SendSMSOperationRequest request) {
        return new ResponseEntity<>(sendSMSOperation.process(request), HttpStatus.OK);
    }
}
