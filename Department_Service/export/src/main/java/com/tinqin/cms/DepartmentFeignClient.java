package com.tinqin.cms;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

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

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface DepartmentFeignClient {
    @RequestLine("GET /departments/{id}")
    FindByIdDepartmentResponse findDepartmentById(@Param("id") String id);

    @RequestLine("GET /departments/find-all?pageNumber={pageNumber}&numberOfBooksPerPage={numberOfBooksPerPage}")
    FindAllDepartmentsResponse findAllDepartments(@Param("pageNumber") Integer pageNumber,
                                                  @Param("numberOfBooksPerPage") Integer numberOfBooksPerPage);

    @RequestLine("POST /departments/create")
    CreateNewDepartmentResponse createNewDepartment(CreateNewDepartmentRequest request);

    @RequestLine("PUT /departments/edit-full")
    EditFullDepartmentResponse editFullDepartment(EditFullDepartmentRequest request);

    @RequestLine("PATCH /departments/edit-name")
    EditDepartmentNameResponse editDepartmentName(EditDepartmentNameRequest request);

    @RequestLine("PATCH /departments/edit-description")
    EditDepartmentDescriptionResponse editDepartmentDescription(EditDepartmentDescriptionRequest request);

    @RequestLine("PATCH /departments/edit-email")
    EditDepartmentEmailResponse editDepartmentEmail(EditDepartmentEmailRequest request);

    @RequestLine("PATCH /departments/edit-location")
    EditDepartmentLocationResponse editDepartmentLocation(EditDepartmentLocationRequest request);

    @RequestLine("PATCH /departments/edit-phone-number")
    EditDepartmentPhoneNumberResponse editDepartmentPhoneNumber(EditDepartmentPhoneNumberRequest request);

    @RequestLine("PATCH /departments/edit-budget")
    EditDepartmentBudgetResponse editDepartmentBudget(EditDepartmentBudgetRequest request);

    @RequestLine("DELETE /departments/{id}")
    DeleteDepartmentResponse deleteDepartment(@Param("id") String id);
}
