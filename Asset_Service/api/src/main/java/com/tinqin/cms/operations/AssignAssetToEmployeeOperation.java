package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public interface AssignAssetToEmployeeOperation extends OperationProcessor<AssignAssetToEmployeeOperation.AssignAssetToEmployeeResponse, AssignAssetToEmployeeOperation.AssignAssetToEmployeeRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class AssignAssetToEmployeeResponse implements OperationOutput {
        @Schema(description = "ID of the edited asset", example = "123e4567-e89b-12d3-a456-556642440000")
        private String id;

        @Schema(description = "Employee ID")
        private String employeeId;

        @Schema(description = "Updated name of the asset")
        private String name;

        @Schema(description = "Updated description of the asset")
        private String description;

        @Schema(description = "Updated serial number of the asset")
        private String serialNumber;

        @Schema(description = "Type of the asset")
        private String assetType;

        @Schema(description = "Status of the asset")
        private String assetStatus;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class AssignAssetToEmployeeRequest implements OperationInput {
        @NotNull(message = "Employee ID must not be null")
        @NotBlank(message = "Employee ID must not be blank")
        private String employeeId;

        @NotNull(message = "Asset ID must not be null")
        @NotBlank(message = "Asset ID must not be blank")
        private String assetId;
    }
}
