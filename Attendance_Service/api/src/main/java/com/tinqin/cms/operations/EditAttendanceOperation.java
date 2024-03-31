package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

public interface EditAttendanceOperation extends OperationProcessor<EditAttendanceOperation.EditAttendanceResponse, EditAttendanceOperation.EditAttendanceRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class EditAttendanceResponse implements OperationOutput {
        @Schema(description = "ID of the edited attendance", example = "123e4567-e89b-12d3-a456-556642440000")
        private String id;

        @Schema(description = "ID of the employee", example = "123e4567-e89b-12d3-a456-556642440000")
        private String employeeId;

        @Schema(description = "Check-in time of the attendance", example = "2024-03-09T08:00:00")
        private String checkInTime;

        @Schema(description = "Check-out time of the attendance", example = "2024-03-09T17:00:00")
        private String checkOutTime;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class EditAttendanceRequest implements OperationInput {
        @Schema(description = "ID of the attendance to edit", example = "123e4567-e89b-12d3-a456-556642440000")
        @NotBlank(message = "Attendance ID cannot be blank")
        @Pattern(regexp = "[\\w-]+", message = "Invalid attendance ID format")
        private String id;

        @Schema(description = "ID of the employee", example = "123e4567-e89b-12d3-a456-556642440000")
        @NotBlank(message = "Employee ID cannot be blank")
        @Pattern(regexp = "[\\w-]+", message = "Invalid employee ID format")
        private String employeeId;

        @Schema(description = "Check-in time of the attendance", example = "2024-03-09T08:00:00")
        private String checkInTime;

        @Schema(description = "Check-out time of the attendance", example = "2024-03-09T17:00:00")
        private String checkOutTime;
    }
}
