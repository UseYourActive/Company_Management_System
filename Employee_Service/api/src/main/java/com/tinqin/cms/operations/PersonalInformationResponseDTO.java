package com.tinqin.cms.operations;

import com.tinqin.cms.annotations.validators.ValidUCN;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Schema(description = "Personal Information of an employee Response DTO.")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class PersonalInformationResponseDTO {
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
