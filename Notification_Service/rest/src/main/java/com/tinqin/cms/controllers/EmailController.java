package com.tinqin.cms.controllers;

import com.tinqin.cms.operations.SendEmailOperation;
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
        name = "Email Controller",
        description = "Controller specifically used for operations associated with emails"
)
@RequiredArgsConstructor
@RestController
@Validated
@RefreshScope
@Slf4j
@CrossOrigin
@RequestMapping(path = "/email")
public class EmailController {
    private final SendEmailOperation sendEmailOperation;

    //region GET
    //endregion

    //region POST
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sent an email."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request sends an email to the specified location with a subject and text attached to it.",
            summary = "Sends an email.")
    @PostMapping(path = "/send")
    public ResponseEntity<SendEmailOperation.SendEmailOperationResponse> sendEmail(@Valid @RequestBody SendEmailOperation.SendEmailOperationRequest request) {
        return new ResponseEntity<>(sendEmailOperation.process(request), HttpStatus.OK);
    }
    //endregion

    //region PUT
    //endregion

    //region DELETE
    //endregion
}
