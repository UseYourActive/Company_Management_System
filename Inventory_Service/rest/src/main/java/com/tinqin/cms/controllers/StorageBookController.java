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

import static com.tinqin.cms.operations.ChangeBookPriceOperation.*;
import static com.tinqin.cms.operations.DeleteStorageBookByIdOperation.*;
import static com.tinqin.cms.operations.DeleteStorageBooksByIdsOperation.*;
import static com.tinqin.cms.operations.ExportBookOperation.*;
import static com.tinqin.cms.operations.FindAllStorageBooksOperation.*;
import static com.tinqin.cms.operations.FindStorageBookByIdOperation.*;
import static com.tinqin.cms.operations.FindStorageBooksByIdsOperation.*;
import static com.tinqin.cms.operations.ImportBookOperation.*;
import static com.tinqin.cms.operations.RegisterNewBookOperation.*;

@Tag(
        name = "CRUD REST APIs for Inventory Resource",
        description = "CRUD REST APIs"
)
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(path = "/storage-item")
public class StorageBookController {
    private final FindStorageBookByIdOperation findStorageBookByIdOperation;
    private final FindAllStorageBooksOperation findAllStorageBooksOperation;
    private final FindStorageBooksByIdsOperation findStorageBooksByIdsOperation;
    private final RegisterNewBookOperation registerNewBookOperation;
    private final ImportBookOperation importBookOperation;
    private final ExportBookOperation exportBookOperation;
    private final ChangeBookPriceOperation changeBookPriceOperation;
    private final DeleteStorageBooksByIdsOperation deleteStorageBooksByIdsOperation;
    private final DeleteStorageBookByIdOperation deleteStorageBookByIdOperation;

    //region GET
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found a storage book by id."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing storage book in the database.",
            summary = "Finds a storage book by id.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<FindStorageBookByIdResponse> findStorageBookById(@PathVariable("id") String id) {
        FindStorageBookByIdRequest request = FindStorageBookByIdRequest.builder()
                .storageBookId(id)
                .build();

        return new ResponseEntity<>(findStorageBookByIdOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all storage books."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds all already existing storage books in the database.",
            summary = "Finds all storage books.")
    @GetMapping("/{pageNumber}/{numberOfBooksPerPage}")
    public ResponseEntity<FindAllStorageBooksResponse> findAllStorageBooks(@PathVariable("pageNumber") Integer pageNumber,
                                                                           @PathVariable("numberOfBooksPerPage") Integer numberOfBooksPerPage) {
        FindAllStorageBooksRequest request = FindAllStorageBooksRequest.builder()
                .pageNumber(pageNumber)
                .numberOfBooksPerPage(numberOfBooksPerPage)
                .build();

        return new ResponseEntity<>(findAllStorageBooksOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all storage books with given ids."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds all storage books with the given ids.",
            summary = "Finds all storage books with given ids.")
    @GetMapping("/{ids}")
    public ResponseEntity<FindStorageBooksByIdsResponse> findStorageBooksByIds(@PathVariable("ids") List<String> ids) {
        FindStorageBooksByIdsRequest request = FindStorageBooksByIdsRequest.builder()
                .ids(ids)
                .build();

        return new ResponseEntity<>(findStorageBooksByIdsOperation.process(request), HttpStatus.OK);
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
    public ResponseEntity<RegisterNewBookResponse> registerNewBook(@RequestBody RegisterNewBookRequest request) {
        return new ResponseEntity<>(registerNewBookOperation.process(request), HttpStatus.CREATED);
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
    public ResponseEntity<ImportBookResponse> importBooks(@RequestBody ImportBookRequest request) {
        return new ResponseEntity<>(importBookOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully exported book/s."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request exports an already existing in the database book.",
            summary = "Exports a certain amount of books.")
    @PostMapping(path = "/export")
    public ResponseEntity<ExportBookResponse> exportBooks(@RequestBody ExportBookRequest request) {
        return new ResponseEntity<>(exportBookOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully changed a books price."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request changes the price of an already existing in the database book.",
            summary = "Change a books price.")
    @PostMapping(path = "/chance-price")
    public ResponseEntity<ChangeBookPriceResponse> changeBookPrice(@RequestBody ChangeBookPriceRequest request) {
        return new ResponseEntity<>(changeBookPriceOperation.process(request), HttpStatus.OK);
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
    public ResponseEntity<DeleteStorageBooksByIdsResponse> deleteStorageBooksByIds(@PathVariable("ids") List<String> ids) {
        DeleteStorageBooksByIdsRequest request = DeleteStorageBooksByIdsRequest.builder()
                .ids(ids)
                .build();

        return new ResponseEntity<>(deleteStorageBooksByIdsOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted a storage book by id."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request deletes already an existing in the database storage book.",
            summary = "Delete a storage book by id.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DeleteStorageBookByIdResponse> deleteStorageBookById(@PathVariable("id") String id) {
        DeleteStorageBookByIdRequest request = DeleteStorageBookByIdRequest.builder()
                .storageBookId(id)
                .build();

        return new ResponseEntity<>(deleteStorageBookByIdOperation.process(request), HttpStatus.OK);
    }
    //endregion
}
