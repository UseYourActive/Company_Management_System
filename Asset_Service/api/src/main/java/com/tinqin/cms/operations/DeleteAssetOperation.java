package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

public interface DeleteAssetOperation extends OperationProcessor<DeleteAssetOperation.DeleteAssetResponse, DeleteAssetOperation.DeleteAssetRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class DeleteAssetResponse implements OperationOutput {
        @Schema(description = "Flag indicating if the asset was successfully deleted", example = "true")
        private Boolean isSuccessfullyDeleted;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class DeleteAssetRequest implements OperationInput {
        @Schema(description = "ID of the asset to delete", example = "123e4567-e89b-12d3-a456-556642440000", required = true)
        @NotBlank(message = "ID must not be blank")
        @Pattern(regexp = "\\b\\p{XDigit}{8}(-\\p{XDigit}{4}){3}-\\p{XDigit}{12}\\b", message = "Invalid UUID format")
        private String id;
    }
}
