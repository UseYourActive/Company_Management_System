package com.tinqin.cms.operations;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Schema(description = "Employee Response DTO.")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class EmployeeResponseDTO {
    @Schema(description = "Unique identifier for the employee.")
    @NotNull(message = "Employee ID cannot be null")
    private String id;

    @Schema(description = "Personal information of the employee.")
    @NotBlank(message = "Personal information cannot be blank")
    private PersonalInformationResponseDTO personalInformation;

    @Schema(description = "Designation of the employee.")
    @NotBlank(message = "Designation cannot be blank")
    private String designation;

    @Schema(description = "Department of the employee.")
    @NotBlank(message = "Department cannot be blank")
    private String department;

    @Schema(description = "Contact information of the employee.")
    @NotNull(message = "Contact information cannot be null")
    private ContactInformationResponseDTO contactInformation;

    @Schema(description = "Salary details of the employee.")
    @NotNull(message = "Salary details cannot be null")
    private SalaryResponseDTO salary;

    @Schema(description = "Creation details of the employee.")
    @NotNull(message = "Creation details cannot be null")
    private CreationResponseDTO creation;

    @Schema(description = "Employee details.")
    @NotNull(message = "Employee details cannot be null")
    private EmployeeDetailsResponseDTO employeeDetails;
}
