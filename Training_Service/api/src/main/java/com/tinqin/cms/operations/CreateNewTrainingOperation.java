package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public interface CreateNewTrainingOperation extends OperationProcessor<CreateNewTrainingOperation.CreateNewTrainingResponse, CreateNewTrainingOperation.CreateNewTrainingRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class CreateNewTrainingResponse implements OperationOutput {
        @Schema(description = "ID of the training", example = "123e4567-e89b-12d3-a456-556642440000")
        private String id;

        @Schema(description = "Title of the training", example = "Java Programming")
        private String title;

        @Schema(description = "Description of the training", example = "Learn Java programming basics.")
        private String description;

        @Schema(description = "Start date of the training", example = "2024-03-10T10:00:00")
        private String startDate;

        @Schema(description = "End date of the training", example = "2024-03-15T16:00:00")
        private String endDate;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class CreateNewTrainingRequest implements OperationInput {
        @Schema(description = "Title of the training", example = "Java Programming")
        @NotBlank(message = "Title is required")
        private String title;

        @Schema(description = "Description of the training", example = "Learn Java programming basics.")
        @NotBlank(message = "Description is required")
        private String description;

        @Schema(description = "Start date of the training", example = "2024-03-10")
        @NotBlank(message = "Start date is required")
        private String startDate;

        @Schema(description = "End date of the training", example = "2024-03-15")
        @NotBlank(message = "End date is required")
        private String endDate;
    }
}
