package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

public interface DeleteTrainingOperation extends OperationProcessor<DeleteTrainingOperation.DeleteTrainingResponse, DeleteTrainingOperation.DeleteTrainingRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class DeleteTrainingResponse implements OperationOutput {
        @Schema(description = "Indicates whether the training was successfully deleted", example = "true")
        private Boolean isSuccessfullyDeleted;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class DeleteTrainingRequest implements OperationInput {
        @Schema(description = "ID of the training to delete", example = "123e4567-e89b-12d3-a456-556642440000")
        @NotBlank(message = "ID is required")
        @Pattern(regexp = "\\b\\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}\\b", message = "Invalid UUID format")
        private String id;
    }
}
