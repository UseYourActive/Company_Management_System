package com.tinqin.cms.operations;

import com.tinqin.cms.base.OperationInput;
import com.tinqin.cms.base.OperationOutput;
import com.tinqin.cms.base.OperationProcessor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

public interface ClockOutOperation extends OperationProcessor<ClockOutOperation.ClockOutResponse, ClockOutOperation.ClockOutRequest> {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class ClockOutResponse implements OperationOutput {
        @Schema(description = "ID of the attendance", example = "123e4567-e89b-12d3-a456-556642440000")
        private String id;

        @Schema(description = "ID of the employee", example = "123e4567-e89b-12d3-a456-556642440001")
        private String employeeId;

        @Schema(description = "Clock-in time", example = "2024-03-09T09:00:00")
        private String clockInTime;

        @Schema(description = "Clock-out time", example = "2024-03-09T17:00:00")
        private String clockOutTime;

        @Schema(description = "Flag indicating if clock-out was successful", example = "true")
        private Boolean successfullyClockedOut;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class ClockOutRequest implements OperationInput {
        @Schema(description = "ID of the employee to search", example = "123e4567-e89b-12d3-a456-556642440000")
        @NotBlank(message = "Employee ID cannot be blank")
        @Pattern(regexp = "[\\w-]+", message = "Invalid employee ID format")
        private String employeeId;
    }
}

//    public Attendance clockOut(Long employeeId) {
//        List<Attendance> employeeAttendance = attendanceRepository.findByEmployeeId(employeeId);
//        if (!employeeAttendance.isEmpty()) {
//            Attendance latestAttendance = employeeAttendance.get(employeeAttendance.size() - 1);
//            if (latestAttendance.getClockOutTime() == null) {
//                latestAttendance.setClockOutTime(LocalDateTime.now());
//                return attendanceRepository.save(latestAttendance);
//            }
//        }
//        return null; // Employee has no active clock-in
//    }