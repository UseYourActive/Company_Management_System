package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

public interface ClockInOperation extends OperationProcessor<ClockInOperation.ClockInResponse, ClockInOperation.ClockInRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class ClockInResponse implements OperationOutput {
        @Schema(description = "ID of the attendance", example = "123e4567-e89b-12d3-a456-556642440000")
        private String id;

        @Schema(description = "ID of the employee", example = "123e4567-e89b-12d3-a456-556642440001")
        private String employeeId;

        @Schema(description = "Clock-in time", example = "2024-03-09T09:00:00")
        private String clockInTime;

        @Schema(description = "Flag indicating if clock-in was successful", example = "true")
        private Boolean successfullyClockedIn;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class ClockInRequest implements OperationInput {
        @Schema(description = "ID of the employee to search", example = "123e4567-e89b-12d3-a456-556642440000")
        @NotBlank(message = "Employee ID cannot be blank")
        @Pattern(regexp = "[\\w-]+", message = "Invalid employee ID format")
        private String employeeId;
    }
}

//    public Attendance clockIn(Long employeeId) {
//        Attendance attendance = new Attendance();
//        attendance.setEmployeeId(employeeId);
//        attendance.setClockInTime(LocalDateTime.now());
//        return attendanceRepository.save(attendance);
//    }