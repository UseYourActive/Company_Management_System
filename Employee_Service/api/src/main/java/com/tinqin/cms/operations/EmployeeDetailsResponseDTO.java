package com.tinqin.cms.operations;

import com.tinqin.cms.annotations.validators.ValidDate;
import com.tinqin.cms.annotations.validators.ValidGender;
import com.tinqin.cms.annotations.validators.ValidMaritalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Schema(description = "Employee Details DTO.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDetailsResponseDTO {
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
    private AddressResponseDTO address;

    @Schema(description = "Marital status of the employee.")
    @NotNull(message = "Marital status cannot be null")
    @ValidMaritalStatus
    private String maritalStatus;
}
