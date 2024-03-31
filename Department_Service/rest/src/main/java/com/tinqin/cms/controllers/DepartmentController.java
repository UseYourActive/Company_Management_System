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

import static com.tinqin.cms.operations.CreateNewDepartmentOperation.*;
import static com.tinqin.cms.operations.DeleteDepartmentOperation.*;
import static com.tinqin.cms.operations.EditDepartmentBudgetOperation.*;
import static com.tinqin.cms.operations.EditDepartmentDescriptionOperation.*;
import static com.tinqin.cms.operations.EditDepartmentEmailOperation.*;
import static com.tinqin.cms.operations.EditDepartmentLocationOperation.*;
import static com.tinqin.cms.operations.EditDepartmentNameOperation.*;
import static com.tinqin.cms.operations.EditDepartmentPhoneNumberOperation.*;
import static com.tinqin.cms.operations.EditFullDepartmentOperation.*;
import static com.tinqin.cms.operations.FindAllDepartmentsOperation.*;
import static com.tinqin.cms.operations.FindByIdDepartmentOperation.*;

@Tag(
        name = "CRUD REST APIs for Department Resource",
        description = "CRUD REST APIs - Create department, Update department, Find department, Find all department, Delete department"
)
@RequiredArgsConstructor
@RestController
@Validated
@RefreshScope
@Slf4j
@RequestMapping(path = "/departments")
public class DepartmentController {
    private final CreateNewDepartmentOperation createNewDepartmentOperation;
    private final FindByIdDepartmentOperation findByIdDepartmentOperation;
    private final FindAllDepartmentsOperation findAllDepartmentsOperation;
    private final DeleteDepartmentOperation deleteDepartmentOperation;
    private final EditFullDepartmentOperation editFullDepartmentOperation;
    private final EditDepartmentBudgetOperation editDepartmentBudgetOperation;
    private final EditDepartmentNameOperation editDepartmentNameOperation;
    private final EditDepartmentDescriptionOperation editDepartmentDescriptionOperation;
    private final EditDepartmentEmailOperation editDepartmentEmailOperation;
    private final EditDepartmentLocationOperation editDepartmentLocationOperation;
    private final EditDepartmentPhoneNumberOperation editDepartmentPhoneNumberOperation;
    //region GET
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found a department."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing in the database department.",
            summary = "Finds a department by id.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<FindByIdDepartmentResponse> findDepartmentById(@PathVariable(value = "id") @UUID String input) {
        FindByIdDepartmentRequest request = FindByIdDepartmentRequest.builder()
                .id(input)
                .build();

        return new ResponseEntity<>(findByIdDepartmentOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found all departments."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users finds all departments in the database paginated.",
            summary = "Find all departments.")
    @GetMapping(path = "/find-all")
    public ResponseEntity<FindAllDepartmentsResponse> findAllDepartments(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                                         @RequestParam(defaultValue = "2") Integer numberOfBooksPerPage) {
        FindAllDepartmentsRequest request = FindAllDepartmentsRequest.builder()
                .pageNumber(pageNumber)
                .numberOfBooksPerPage(numberOfBooksPerPage)
                .build();

        return new ResponseEntity<>(findAllDepartmentsOperation.process(request), HttpStatus.OK);
    }
    //endregion

    //region POST
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully create a department."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request creates a new department that does not exist in the database yet.",
            summary = "Creates a new department.")
    @PostMapping(path = "/create")
    public ResponseEntity<CreateNewDepartmentResponse> createNewDepartment(@Valid @RequestBody CreateNewDepartmentRequest request) {
        return new ResponseEntity<>(createNewDepartmentOperation.process(request), HttpStatus.CREATED);
    }
    //endregion

    //region PUT
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited a department."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing in the database department and updates it.",
            summary = "Edits a department.")
    @PutMapping(path = "/edit-full")
    public ResponseEntity<EditFullDepartmentResponse> editFullDepartment(@Valid @RequestBody EditFullDepartmentRequest request) {
        return new ResponseEntity<>(editFullDepartmentOperation.process(request), HttpStatus.OK);
    }
    //endregion

    //region PATCH
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited a departments name."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing in the database department and updates its name.",
            summary = "Edits a departments name.")
    @PatchMapping(path = "/edit-name")
    public ResponseEntity<EditDepartmentNameResponse> editDepartmentName(@Valid @RequestBody EditDepartmentNameRequest request) {
        return new ResponseEntity<>(editDepartmentNameOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited a departments description."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing in the database department and updates its description.",
            summary = "Edits a departments description.")
    @PatchMapping(path = "/edit-description")
    public ResponseEntity<EditDepartmentDescriptionResponse> editDepartmentDescription(@Valid @RequestBody EditDepartmentDescriptionRequest request) {
        return new ResponseEntity<>(editDepartmentDescriptionOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited a departments email."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing in the database department and updates its email.",
            summary = "Edits a departments email.")
    @PatchMapping(path = "/edit-email")
    public ResponseEntity<EditDepartmentEmailResponse> editDepartmentEmail(@Valid @RequestBody EditDepartmentEmailRequest request) {
        return new ResponseEntity<>(editDepartmentEmailOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited a departments location."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing in the database department and updates its location.",
            summary = "Edits a departments location.")
    @PatchMapping(path = "/edit-location")
    public ResponseEntity<EditDepartmentLocationResponse> editDepartmentLocation(@Valid @RequestBody EditDepartmentLocationRequest request) {
        return new ResponseEntity<>(editDepartmentLocationOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited a departments phone number."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing in the database department and updates its phone number.",
            summary = "Edits a departments phone number.")
    @PatchMapping(path = "/edit-phone-number")
    public ResponseEntity<EditDepartmentPhoneNumberResponse> editDepartmentPhoneNumber(@Valid @RequestBody EditDepartmentPhoneNumberRequest request) {
        return new ResponseEntity<>(editDepartmentPhoneNumberOperation.process(request), HttpStatus.OK);
    }

    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited a departments budget."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing in the database department and updates its budget.",
            summary = "Edits a departments budget.")
    @PatchMapping(path = "/edit-budget")
    public ResponseEntity<EditDepartmentBudgetResponse> editDepartmentBudget(@Valid @RequestBody EditDepartmentBudgetRequest request) {
        return new ResponseEntity<>(editDepartmentBudgetOperation.process(request), HttpStatus.OK);
    }
    //endregion

    //region DELETE
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted a department."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "text/html"))
    })
    @Operation(description = "From the users request finds an already existing in the database department and deletes it.",
            summary = "Deletes a department.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DeleteDepartmentResponse> deleteDepartment(@PathVariable("id") @UUID String input) {
        DeleteDepartmentRequest request = DeleteDepartmentRequest.builder()
                .id(input)
                .build();

        return new ResponseEntity<>(deleteDepartmentOperation.process(request), HttpStatus.OK);
    }
    //endregion
}
