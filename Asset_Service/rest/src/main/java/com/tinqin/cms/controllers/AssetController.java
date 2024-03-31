package com.tinqin.cms.controllers;

import com.tinqin.cms.operations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.UUID;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.tinqin.cms.operations.AssignAssetToEmployeeOperation.*;
import static com.tinqin.cms.operations.CreateNewAssetOperation.*;
import static com.tinqin.cms.operations.DeleteAssetOperation.*;
import static com.tinqin.cms.operations.EditAssetOperation.*;
import static com.tinqin.cms.operations.FindAllAssetsOperation.*;
import static com.tinqin.cms.operations.FindByIdAssetOperation.*;

@Tag(
        name = "CRUD REST APIs for Assets",
        description = "CRUD REST API"
)
@RequiredArgsConstructor
@RestController
@Validated
@RefreshScope
@Slf4j
@CrossOrigin
@RequestMapping(path = "/asset")
public class AssetController {
    private final CreateNewAssetOperation createNewAssetOperation;
    private final FindByIdAssetOperation findByIdAssetOperation;
    private final FindAllAssetsOperation findAllAssetsOperation;
    private final DeleteAssetOperation deleteAssetOperation;
    private final EditAssetOperation editAssetOperation;
    private final AssignAssetToEmployeeOperation assignAssetToEmployeeOperation;

    //region GET
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found a asset."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing in the database asset.",
            summary = "Finds a asset by id.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<FindByIdAssetResponse> findAssetById(@PathVariable(value = "id") @UUID String input) {
        FindByIdAssetRequest request = FindByIdAssetRequest.builder()
                .id(input)
                .build();

        return new ResponseEntity<>(findByIdAssetOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all assets."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users finds all assets in the database paginated.",
            summary = "Find all assets.")
    @GetMapping(path = "/find-all")
    public ResponseEntity<FindAllAssetsResponse> findAllAssets(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                               @RequestParam(defaultValue = "2") Integer numberOfBooksPerPage) {
        FindAllAssetsRequest request = FindAllAssetsRequest.builder()
                .pageNumber(pageNumber)
                .numberOfAssetsPerPage(numberOfBooksPerPage)
                .build();

        return new ResponseEntity<>(findAllAssetsOperation.process(request), HttpStatus.OK);
    }
    //endregion

    //region POST
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully create a asset."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request creates a new asset that does not exist in the database yet.",
            summary = "Creates a new asset.")
    @PostMapping(path = "/create")
    public ResponseEntity<CreateNewAssetResponse> createNewAsset(@Valid @RequestBody CreateNewAssetRequest request) {
        return new ResponseEntity<>(createNewAssetOperation.process(request), HttpStatus.CREATED);
    }
    //endregion

    //region PUT
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited a asset."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing in the database asset and updates it.",
            summary = "Edits a asset.")
    @PatchMapping(path = "/edit")
    public ResponseEntity<EditAssetResponse> editAsset(@Valid @RequestBody EditAssetRequest request) {
        return new ResponseEntity<>(editAssetOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully assigned a asset to employee."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request assigns a certain asset stored in the database to a employee.",
            summary = "Assign asset to an employee.")
    @PutMapping(path = "/assign-to-employee")
    public ResponseEntity<AssignAssetToEmployeeResponse> assignAssetToEmployee(@Valid @RequestBody AssignAssetToEmployeeRequest request) {
        return new ResponseEntity<>(assignAssetToEmployeeOperation.process(request), HttpStatus.OK);
    }
    //endregion

    //region DELETE
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted a asset."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing in the database asset and deletes it.",
            summary = "Deletes a asset.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DeleteAssetResponse> deleteAsset(@PathVariable("id") @UUID String input) {
        DeleteAssetRequest request = DeleteAssetRequest.builder()
                .id(input)
                .build();

        return new ResponseEntity<>(deleteAssetOperation.process(request), HttpStatus.OK);
    }
    //endregion
}
