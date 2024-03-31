package com.tinqin.cms;

import jakarta.validation.Valid;
import org.hibernate.validator.constraints.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.tinqin.cms.operations.CreateNewEmployeeOperation.*;
import static com.tinqin.cms.operations.DeleteEmployeeOperation.*;
import static com.tinqin.cms.operations.EditEmployeeOperation.*;
import static com.tinqin.cms.operations.FindAllEmployeesOperation.*;
import static com.tinqin.cms.operations.FindByIdEmployeeOperation.*;

@FeignClient(name = "EMPLOYEE-SERVICE")
public interface EmployeeFeignClient {
    @GetMapping("/employees/{id}")
    ResponseEntity<FindByIdEmployeeResponse> findEmployeeById(@PathVariable(value = "id") @UUID String input);

    @GetMapping("/employees/find-all")
    ResponseEntity<FindAllEmployeesResponse> findAllEmployees(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                              @RequestParam(defaultValue = "2") Integer numberOfBooksPerPage);

    @PostMapping("/employees/create")
    ResponseEntity<CreateNewEmployeeResponse> createNewEmployee(@Valid @RequestBody CreateNewEmployeeRequest request);

    @PatchMapping("/employees/edit")
    ResponseEntity<EditEmployeeResponse> editEmployee(@Valid @RequestBody EditEmployeeRequest request);

    @DeleteMapping("/employees/{id}")
    ResponseEntity<DeleteEmployeeResponse> deleteEmployee(@PathVariable("id") @UUID String input);
}

//@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultService") -> on the controller methods
//@LoadBalanced
