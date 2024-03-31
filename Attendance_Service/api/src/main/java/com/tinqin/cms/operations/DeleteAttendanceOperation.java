package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

public interface DeleteAttendanceOperation extends OperationProcessor<DeleteAttendanceOperation.DeleteAttendanceResponse, DeleteAttendanceOperation.DeleteAttendanceRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class DeleteAttendanceResponse implements OperationOutput {
        @Schema(description = "Flag indicating if the attendance was successfully deleted", example = "true")
        private Boolean isSuccessfullyDeleted;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class DeleteAttendanceRequest implements OperationInput {
        @Schema(description = "ID of the attendance to delete", example = "123e4567-e89b-12d3-a456-556642440000")
        @NotBlank(message = "Attendance ID cannot be blank")
        @Pattern(regexp = "[\\w-]+", message = "Invalid attendance ID format")
        private String id;
    }
}
