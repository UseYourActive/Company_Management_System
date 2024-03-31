package com.tinqin.cms.controllers;

import com.tinqin.cms.entities.ChronologyFilter;
import com.tinqin.cms.entities.IndexVM;
import com.tinqin.cms.entities.MessageLog;
import com.tinqin.cms.processors.ChronologyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class KafkaController {
    private final ChronologyService chronologyService;

    @Operation(summary = "Gets logs by filter.",
            description = "Used to return logs in the system by filter.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully, returns list of countries.")})
    @PostMapping("/getAll")
    public ResponseEntity<IndexVM<MessageLog>> getAll(@RequestBody ChronologyFilter chronologyFilter) {
        return ResponseEntity.ok(chronologyService.getChronology(chronologyFilter));
    }

    @Operation(summary = "Gets types of logs.",
            description = "Returns the types of logs, which are supported by system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully, returns list of types.")})
    @GetMapping("/getTypes")
    public ResponseEntity<Set<String>> getTypes(){
        return ResponseEntity.ok(chronologyService.getTypes());
    }
}
