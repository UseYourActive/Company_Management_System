package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public interface FindByIdTrainingOperation extends OperationProcessor<FindByIdTrainingOperation.FindByIdTrainingResponse, FindByIdTrainingOperation.FindByIdTrainingRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindByIdTrainingResponse implements OperationOutput {
        @Schema(description = "ID of the training", example = "123e4567-e89b-12d3-a456-556642440000")
        private String id;

        @Schema(description = "Title of the training")
        private String title;

        @Schema(description = "Description of the training")
        private String description;

        @Schema(description = "Start date of the training", example = "2023-12-31T23:59:59")
        private String startDate;

        @Schema(description = "End date of the training", example = "2024-01-01T00:00:00")
        private String endDate;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindByIdTrainingRequest implements OperationInput {
        @Schema(description = "ID of the training to find", example = "123e4567-e89b-12d3-a456-556642440000")
        private String id;
    }
}
