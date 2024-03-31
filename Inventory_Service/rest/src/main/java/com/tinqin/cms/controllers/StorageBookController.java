package com.tinqin.cms.controllers;

import com.tinqin.cms.operations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tinqin.cms.operations.ChangePriceOperation.*;
import static com.tinqin.cms.operations.DeleteStorageByIdOperation.*;
import static com.tinqin.cms.operations.DeleteStoragesByIdsOperation.*;
import static com.tinqin.cms.operations.ExportAssetOperation.*;
import static com.tinqin.cms.operations.FindAllStoragesOperation.*;
import static com.tinqin.cms.operations.FindStorageByIdOperation.*;
import static com.tinqin.cms.operations.FindStorageAssetsByIdsOperation.*;
import static com.tinqin.cms.operations.ImportAssetOperation.*;
import static com.tinqin.cms.operations.RegisterNewAssetOperation.*;

@Tag(
        name = "CRUD REST APIs for Inventory Resource",
        description = "CRUD REST APIs"
)
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(path = "/storage-item")
public class StorageBookController {
    private final FindStorageByIdOperation findStorageByIdOperation;
    private final FindAllStoragesOperation findAllStoragesOperation;
    private final FindStorageAssetsByIdsOperation findStorageAssetsByIdsOperation;
    private final RegisterNewAssetOperation registerNewAssetOperation;
    private final ImportAssetOperation importAssetOperation;
    private final ExportAssetOperation exportAssetOperation;
    private final ChangePriceOperation changePriceOperation;
    private final DeleteStoragesByIdsOperation deleteStoragesByIdsOperation;
    private final DeleteStorageByIdOperation deleteStorageByIdOperation;

    //region GET
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found a storage book by id."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing storage book in the database.",
            summary = "Finds a storage book by id.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<FindStorageByIdResponse> findStorageBookById(@PathVariable("id") String id) {
        FindStorageByIdRequest request = FindStorageByIdRequest.builder()
                .id(id)
                .build();

        return new ResponseEntity<>(findStorageByIdOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all storage books."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds all already existing storage books in the database.",
            summary = "Finds all storage books.")
    @GetMapping("/{pageNumber}/{numberOfBooksPerPage}")
    public ResponseEntity<FindAllStoragesResponse> findAllStorageBooks(@PathVariable("pageNumber") Integer pageNumber,
                                                                       @PathVariable("numberOfBooksPerPage") Integer numberOfBooksPerPage) {
        FindAllStoragesRequest request = FindAllStoragesRequest.builder()
                .pageNumber(pageNumber)
                .numberOfBooksPerPage(numberOfBooksPerPage)
                .build();

        return new ResponseEntity<>(findAllStoragesOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all storage books with given ids."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds all storage books with the given ids.",
            summary = "Finds all storage books with given ids.")
    @GetMapping("/{ids}")
    public ResponseEntity<FindStorageAssetsByIdsResponse> findStorageBooksByIds(@PathVariable("ids") List<String> ids) {
        FindStorageAssetsByIdsRequest request = FindStorageAssetsByIdsRequest.builder()
                .ids(ids)
                .build();

        return new ResponseEntity<>(findStorageAssetsByIdsOperation.process(request), HttpStatus.OK);
    }
    //endregion

    //region POST
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully registered a new book."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request registers a specific book with a given price and amount of copies of it.",
            summary = "Register a book.")
    @PostMapping(path = "/register")
    public ResponseEntity<RegisterNewAssetResponse> registerNewBook(@RequestBody RegisterNewAssetRequest request) {
        return new ResponseEntity<>(registerNewAssetOperation.process(request), HttpStatus.CREATED);
    }
    //endregion

    //region PUT/PATCH
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully imported book/s."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request imports an already existing in the database book.",
            summary = "Import a certain amount of books.")
    @PostMapping(path = "/import")
    public ResponseEntity<ImportAssetResponse> importBooks(@RequestBody ImportAssetRequest request) {
        return new ResponseEntity<>(importAssetOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully exported book/s."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request exports an already existing in the database book.",
            summary = "Exports a certain amount of books.")
    @PostMapping(path = "/export")
    public ResponseEntity<ExportAssetResponse> exportBooks(@RequestBody ExportAssetRequest request) {
        return new ResponseEntity<>(exportAssetOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully changed a books price."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request changes the price of an already existing in the database book.",
            summary = "Change a books price.")
    @PostMapping(path = "/chance-price")
    public ResponseEntity<ChangePriceResponse> changeBookPrice(@RequestBody ChangePriceRequest request) {
        return new ResponseEntity<>(changePriceOperation.process(request), HttpStatus.OK);
    }
    //endregion

    //region DELETE
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted storage books by ids."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request deleting already existing in the database storage books.",
            summary = "Delete storage books by ids.")
    @DeleteMapping(path = "/{ids}")
    public ResponseEntity<DeleteStoragesByIdsResponse> deleteStorageBooksByIds(@PathVariable("ids") List<String> ids) {
        DeleteStoragesByIdsRequest request = DeleteStoragesByIdsRequest.builder()
                .ids(ids)
                .build();

        return new ResponseEntity<>(deleteStoragesByIdsOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted a storage book by id."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request deletes already an existing in the database storage book.",
            summary = "Delete a storage book by id.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DeleteStorageByIdResponse> deleteStorageBookById(@PathVariable("id") String id) {
        DeleteStorageByIdRequest request = DeleteStorageByIdRequest.builder()
                .id(id)
                .build();

        return new ResponseEntity<>(deleteStorageByIdOperation.process(request), HttpStatus.OK);
    }
    //endregion
}
