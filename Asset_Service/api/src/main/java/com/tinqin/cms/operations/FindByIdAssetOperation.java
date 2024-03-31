package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public interface FindByIdAssetOperation extends OperationProcessor<FindByIdAssetOperation.FindByIdAssetResponse, FindByIdAssetOperation.FindByIdAssetRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindByIdAssetResponse implements OperationOutput {
        @Schema(description = "ID of the asset")
        private String id;

        @Schema(description = "Name of the asset")
        private String name;

        @Schema(description = "Description of the asset")
        private String description;

        @Schema(description = "Serial number of the asset")
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
    class FindByIdAssetRequest implements OperationInput {
        @Schema(description = "ID of the asset to find", required = true)
        @NotBlank(message = "ID must not be blank")
        private String id;
    }
}
