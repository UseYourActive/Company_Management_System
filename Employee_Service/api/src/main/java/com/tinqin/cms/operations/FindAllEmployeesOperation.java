package com.tinqin.cms.operations;

import com.tinqin.cms.annotations.validators.*;
import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

import static com.tinqin.cms.operations.FindAllEmployeesOperation.*;

public interface FindAllEmployeesOperation extends OperationProcessor<FindAllEmployeesResponse, FindAllEmployeesRequest> {
    @Schema(
            description = "Employee Request DTO for finding all books."
    )
    @Getter
    @Builder
    @AllArgsConstructor
    class FindAllEmployeesRequest implements OperationInput {
        @Schema(
                description = "Page number for Pagination"
        )
        @NotEmpty(message = "Page number should not be null or empty!")
        @PositiveOrZero
        private Integer pageNumber;

        @Schema(
                description = "Number of pages per page for Pagination"
        )
        @NotEmpty(message = "Number of books per page should not be null or empty!")
        @Positive
        private Integer numberOfBooksPerPage;
    }

    @Schema(
            description = "Employee Response DTO for finding all books."
    )
    @Getter
    @Builder
    @AllArgsConstructor
    class FindAllEmployeesResponse implements OperationOutput {
        private List<FindAllEmployeesResponseDTO> employeeResponseDTOS;
    }

    @Schema(description = "Employee Response DTO.")
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class FindAllEmployeesResponseDTO {
        @Schema(description = "Unique identifier for the employee.")
        @NotNull(message = "Employee ID cannot be null")
        private String id;

        @Schema(description = "Personal information of the employee.")
        @NotBlank(message = "Personal information cannot be blank")
        private FindAllEmployeesPersonalInformationResponseDTO personalInformation;

        @Schema(description = "Designation of the employee.")
        @NotBlank(message = "Designation cannot be blank")
        private String designation;

        @Schema(description = "Department of the employee.")
        @NotBlank(message = "Department cannot be blank")
        private String department;

        @Schema(description = "Contact information of the employee.")
        @NotNull(message = "Contact information cannot be null")
        private FindAllEmployeesContactInformationResponseDTO contactInformation;

        @Schema(description = "Salary details of the employee.")
        @NotNull(message = "Salary details cannot be null")
        private FindAllEmployeesSalaryResponseDTO salary;

        @Schema(description = "Creation details of the employee.")
        @NotNull(message = "Creation details cannot be null")
        private FindAllEmployeesCreationResponseDTO creation;

        @Schema(description = "Employee details.")
        @NotNull(message = "Employee details cannot be null")
        private FindAllEmployeesEmployeeDetailsResponseDTO employeeDetails;
    }

    @Schema(description = "Personal Information of an employee Response DTO.")
    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    @AllArgsConstructor
    class FindAllEmployeesPersonalInformationResponseDTO {
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
    class FindAllEmployeesContactInformationResponseDTO {
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
    class FindAllEmployeesSalaryResponseDTO {
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
    class FindAllEmployeesCreationResponseDTO {
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
    class FindAllEmployeesEmployeeDetailsResponseDTO {
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
        private FindAllEmployeesAddressResponseDTO address;

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
    class FindAllEmployeesAddressResponseDTO {
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
