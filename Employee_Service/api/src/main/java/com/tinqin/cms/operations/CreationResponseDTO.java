package com.tinqin.cms.operations;

import com.tinqin.cms.annotations.validators.ValidDate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Schema(description = "Creation DTO.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreationResponseDTO {
    @Schema(description = "Date when the record was created.")
    @NotNull(message = "Created at date cannot be null")
    @ValidDate
    private String createdAt;

    @Schema(description = "Date when the record was last modified.")
    @NotNull(message = "Last modified date cannot be null")
    @ValidDate
    private String lastModifiedAt;
}
