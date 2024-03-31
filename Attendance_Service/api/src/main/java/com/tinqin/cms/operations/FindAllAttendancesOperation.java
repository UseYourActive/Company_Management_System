package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.List;

public interface FindAllAttendancesOperation extends OperationProcessor<FindAllAttendancesOperation.FindAllAttendancesResponse, FindAllAttendancesOperation.FindAllAttendancesRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindAllAttendancesResponse implements OperationOutput {
        @Schema(description = "List of attendance data")
        private List<FindAllAttendancesResponseDTO> findAllAttendancesResponseDTOS;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindAllAttendancesRequest implements OperationInput {
        @Schema(description = "Page number", example = "1")
        @PositiveOrZero(message = "Page number must be a positive integer or zero")
        private Integer pageNumber;

        @Schema(description = "Number of attendances per page", example = "10")
        @Positive(message = "Number of attendances per page must be a positive integer")
        private Integer numberOfAttendancesPerPage;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class FindAllAttendancesResponseDTO {
        @Schema(description = "ID of the attendance", example = "123e4567-e89b-12d3-a456-556642440000")
        private String id;

        @Schema(description = "ID of the employee", example = "123e4567-e89b-12d3-a456-556642440000")
        private String employeeId;

        @Schema(description = "Check-in time", example = "2024-03-09T09:00:00")
        private String checkInTime;

        @Schema(description = "Check-out time", example = "2024-03-09T17:00:00")
        private String checkOutTime;
    }
}
