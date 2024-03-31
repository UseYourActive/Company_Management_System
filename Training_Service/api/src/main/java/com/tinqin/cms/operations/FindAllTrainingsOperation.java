package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.List;

public interface FindAllTrainingsOperation extends OperationProcessor<FindAllTrainingsOperation.FindAllTrainingsResponse, FindAllTrainingsOperation.FindAllTrainingsRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindAllTrainingsResponse implements OperationOutput {
        @Schema(description = "List of training responses")
        private List<FindAllTrainingsResponseDTO> findAllTrainingsResponseDTOS;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindAllTrainingsRequest implements OperationInput {
        @Schema(description = "Page number of the result set", example = "1")
        @Min(value = 1, message = "Page number must be greater than or equal to 1")
        private Integer pageNumber;

        @Schema(description = "Number of trainings per page", example = "10")
        @Min(value = 1, message = "Number of trainings per page must be greater than or equal to 1")
        private Integer numberOfBooksPerPage;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindAllTrainingsResponseDTO {
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
}
