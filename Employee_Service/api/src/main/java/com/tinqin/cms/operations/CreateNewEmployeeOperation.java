package com.tinqin.cms.operations;

import com.tinqin.cms.annotations.validators.*;
import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public interface CreateNewEmployeeOperation extends OperationProcessor<CreateNewEmployeeOperation.CreateNewEmployeeResponse, CreateNewEmployeeOperation.CreateNewEmployeeRequest> {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class CreateNewEmployeeRequest implements OperationInput {
        @Schema(description = "Personal information of the employee.")
        @NotBlank(message = "Personal information cannot be blank")
        private CreateNewEmployeePersonalInformationRequestDTO personalInformation;

        @Schema(description = "Designation of the employee.")
        @NotBlank(message = "Designation cannot be blank")
        private String designation;

        @Schema(description = "Department of the employee.")
        @NotBlank(message = "Department cannot be blank")
        private String department;

        @Schema(description = "Contact information of the employee.")
        @NotNull(message = "Contact information cannot be null")
        private CreateNewEmployeeContactInformationRequestDTO contactInformation;

        @Schema(description = "Salary details of the employee.")
        @NotNull(message = "Salary details cannot be null")
        private CreateNewEmployeeSalaryRequestDTO salary;

        @Schema(description = "Employee details.")
        @NotNull(message = "Employee details cannot be null")
        private CreateNewEmployeeEmployeeDetailsRequestDTO employeeDetails;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class CreateNewEmployeeResponse implements OperationOutput {
        @Schema(description = "Unique identification number of the employee.")
        private String id;

        @Schema(description = "Personal information of the employee.")
        private CreateNewEmployeePersonalInformationResponseDTO personalInformation;

        @Schema(description = "Designation of the employee.")
        private String designation;

        @Schema(description = "Department of the employee.")
        private String department;

        @Schema(description = "Contact information of the employee.")
        private CreateNewEmployeeContactInformationResponseDTO contactInformation;

        @Schema(description = "Salary details of the employee.")
        private CreateNewEmployeeSalaryResponseDTO salary;

        @Schema(description = "Creation details of the employee.")
        private CreateNewEmployeeCreationResponseDTO creation;

        @Schema(description = "Employee details.")
        private CreateNewEmployeeEmployeeDetailsResponseDTO employeeDetails;
    }

    @Schema(description = "Personal Information of an employee Response DTO.")
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class CreateNewEmployeePersonalInformationResponseDTO {
        @Schema(description = "First name of the employee.")
        @NotBlank(message = "First name cannot be blank")
        private String firstName;

        @Schema(description = "Middle name of the employee.")
        @NotBlank(message = "Middle name cannot be blank")
        private String middleName;

        @Schema(description = "Last name of the employee.")
        @NotBlank(message = "Last name cannot be blank")
        private String lastName;

        @Schema(description = "Unique Civil Number of the employee.")
        @NotBlank(message = "Unique Civil Number cannot be blank")
        @ValidUCN
        private String uniqueCivilNumber;
    }

    @Schema(description = "Contact Information DTO.")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class CreateNewEmployeeContactInformationResponseDTO {
        @Schema(description = "Email address of the employee.")
        @Email(message = "Invalid email format")
        private String email;

        @Schema(description = "Phone number of the employee.")
        @NotBlank(message = "Phone number cannot be blank")
        @ValidPhoneNumber
        private String phoneNumber;
    }

    @Schema(description = "Salary DTO.")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class CreateNewEmployeeSalaryResponseDTO {
        @Schema(description = "Amount of the salary.")
        @NotNull(message = "Amount cannot be null")
        @ValidBigDecimal
//    @DecimalMin(value = "0.0", inclusive = false, message = "Salary amount must be greater than 0")
        private String amount;

        @Schema(description = "Effective date of the salary.")
        @NotNull(message = "Effective date cannot be null")
        @ValidDate
        private String effectiveDate;
    }

    @Schema(description = "Creation DTO.")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class CreateNewEmployeeCreationResponseDTO {
        @Schema(description = "Date when the record was created.")
        @NotNull(message = "Created at date cannot be null")
        @ValidDate
        private String createdAt;

        @Schema(description = "Date when the record was last modified.")
        @NotNull(message = "Last modified date cannot be null")
        @ValidDate
        private String lastModifiedAt;
    }

    @Schema(description = "Employee Details DTO.")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class CreateNewEmployeeEmployeeDetailsResponseDTO {
        @Schema(description = "Date of birth of the employee.")
        @NotNull(message = "Date of birth cannot be null")
        @ValidDate
        private String dateOfBirth;

        @Schema(description = "Gender of the employee.")
        @NotNull(message = "Gender cannot be null")
        @ValidGender
        private String gender;

        @Schema(description = "Address of the employee.")
        @NotNull(message = "Address cannot be null")
        private CreateNewEmployeeAddressResponseDTO address;

        @Schema(description = "Marital status of the employee.")
        @NotNull(message = "Marital status cannot be null")
        @ValidMaritalStatus
        private String maritalStatus;
    }

    @Schema(description = "Address DTO.")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class CreateNewEmployeeAddressResponseDTO {
        @Schema(description = "Street of the address.")
        @NotBlank(message = "Street cannot be blank")
        private String street;

        @Schema(description = "City of the address.")
        @NotBlank(message = "City cannot be blank")
        private String city;

        @Schema(description = "Zip code of the address.")
        @NotBlank(message = "Zip code cannot be blank")
        private String zipCode;
    }

    @Schema(description = "Personal Information of an employee Response DTO.")
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class CreateNewEmployeePersonalInformationRequestDTO {
        @Schema(description = "First name of the employee.")
        @NotBlank(message = "First name cannot be blank")
        private String firstName;

        @Schema(description = "Middle name of the employee.")
        @NotBlank(message = "Middle name cannot be blank")
        private String middleName;

        @Schema(description = "Last name of the employee.")
        @NotBlank(message = "Last name cannot be blank")
        private String lastName;

        @Schema(description = "Unique Civil Number of the employee.")
        @NotBlank(message = "Unique Civil Number cannot be blank")
        @ValidUCN
        private String uniqueCivilNumber;
    }

    @Schema(description = "Contact Information DTO.")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class CreateNewEmployeeContactInformationRequestDTO {
        @Schema(description = "Email address of the employee.")
        @Email(message = "Invalid email format")
        private String email;

        @Schema(description = "Phone number of the employee.")
        @NotBlank(message = "Phone number cannot be blank")
        @ValidPhoneNumber
        private String phoneNumber;
    }

    @Schema(description = "Salary DTO.")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class CreateNewEmployeeSalaryRequestDTO {
        @Schema(description = "Amount of the salary.")
        @NotNull(message = "Amount cannot be null")
        @ValidBigDecimal
//    @DecimalMin(value = "0.0", inclusive = false, message = "Salary amount must be greater than 0")
        private String amount;

        @Schema(description = "Effective date of the salary.")
        @NotNull(message = "Effective date cannot be null")
        @ValidDate
        private String effectiveDate;
    }

    @Schema(description = "Employee Details DTO.")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class CreateNewEmployeeEmployeeDetailsRequestDTO {
        @Schema(description = "Date of birth of the employee.")
        @NotNull(message = "Date of birth cannot be null")
        @ValidDate
        private String dateOfBirth;

        @Schema(description = "Gender of the employee.")
        @NotNull(message = "Gender cannot be null")
        @ValidGender
        private String gender;

        @Schema(description = "Address of the employee.")
        @NotNull(message = "Address cannot be null")
        private CreateNewEmployeeAddressRequestDTO address;

        @Schema(description = "Marital status of the employee.")
        @NotNull(message = "Marital status cannot be null")
        @ValidMaritalStatus
        private String maritalStatus;
    }

    @Schema(description = "Address DTO.")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class CreateNewEmployeeAddressRequestDTO {
        @Schema(description = "Street of the address.")
        @NotBlank(message = "Street cannot be blank")
        private String street;

        @Schema(description = "City of the address.")
        @NotBlank(message = "City cannot be blank")
        private String city;

        @Schema(description = "Zip code of the address.")
        @NotBlank(message = "Zip code cannot be blank")
        private String zipCode;
    }
}
