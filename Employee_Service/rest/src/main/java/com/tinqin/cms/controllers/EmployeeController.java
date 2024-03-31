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

import static com.tinqin.cms.operations.CreateNewEmployeeOperation.*;
import static com.tinqin.cms.operations.DeleteEmployeeOperation.*;
import static com.tinqin.cms.operations.EditEmployeeOperation.*;
import static com.tinqin.cms.operations.FindAllEmployeesOperation.*;
import static com.tinqin.cms.operations.FindByIdEmployeeOperation.*;

@Tag(
        name = "CRUD REST APIs for Employee Resource",
        description = "CRUD REST APIs - Create employee, Update employee, Find employee, Find all employees, Delete employee"
)
@RequiredArgsConstructor
@RestController
@Validated
@RefreshScope
@Slf4j
@CrossOrigin
@RequestMapping(path = "/employees")
public class EmployeeController {
    private final FindByIdEmployeeOperation findByIdEmployeeOperation;
    private final FindAllEmployeesOperation findAllEmployeesOperation;
    private final CreateNewEmployeeOperation createNewEmployeeOperation;
    private final DeleteEmployeeOperation deleteEmployeeOperation;
    private final EditEmployeeOperation editEmployeeOperation;

    //region GET
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found a employee."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing in the database employee.",
            summary = "Finds a employee by id.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<FindByIdEmployeeResponse> findEmployeeById(@PathVariable(value = "id") @UUID String input) {
        FindByIdEmployeeRequest request = FindByIdEmployeeRequest.builder()
                .id(input)
                .build();

        return new ResponseEntity<>(findByIdEmployeeOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all employees."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users finds all employees in the database paginated.",
            summary = "Find all employee.")
    @GetMapping(path = "/find-all")
    public ResponseEntity<FindAllEmployeesResponse> findAllEmployees(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                                     @RequestParam(defaultValue = "2") Integer numberOfBooksPerPage) {
        FindAllEmployeesRequest request = FindAllEmployeesRequest.builder()
                .pageNumber(pageNumber)
                .numberOfBooksPerPage(numberOfBooksPerPage)
                .build();

        return new ResponseEntity<>(findAllEmployeesOperation.process(request), HttpStatus.OK);
    }
    //endregion

    //region POST
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully create a employee."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request creates a new employee that does not exist in the database yet.",
            summary = "Creates a new employee.")
    @PostMapping(path = "/create")
    public ResponseEntity<CreateNewEmployeeResponse> createNewEmployee(@Valid @RequestBody CreateNewEmployeeRequest request) {
        return new ResponseEntity<>(createNewEmployeeOperation.process(request), HttpStatus.CREATED);
    }
    //endregion

    //region PUT
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited a employee."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing in the database employee and updates it.",
            summary = "Edits a employee.")
    @PatchMapping(path = "/edit")
    public ResponseEntity<EditEmployeeResponse> editEmployee(@Valid @RequestBody EditEmployeeRequest request) {
        return new ResponseEntity<>(editEmployeeOperation.process(request), HttpStatus.OK);
    }
    //endregion

    //region DELETE
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted a employee."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing in the database employee and deletes it.",
            summary = "Deletes a employee.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DeleteEmployeeResponse> deleteEmployee(@PathVariable("id") @UUID String input) {
        DeleteEmployeeRequest request = DeleteEmployeeRequest.builder()
                .id(input)
                .build();

        return new ResponseEntity<>(deleteEmployeeOperation.process(request), HttpStatus.OK);
    }
    //endregion
}
