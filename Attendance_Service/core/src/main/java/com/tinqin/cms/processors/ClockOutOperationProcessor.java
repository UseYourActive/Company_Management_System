package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Attendance;
import com.tinqin.cms.exceptions.AttendanceNotFoundException;
import com.tinqin.cms.operations.ClockOutOperation;
import com.tinqin.cms.repositories.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClockOutOperationProcessor implements ClockOutOperation {
    private final AttendanceRepository attendanceRepository;
    @Override
    public ClockOutResponse process(final ClockOutRequest request) {
        String employeeId = request.getEmployeeId();

        Attendance attendance = attendanceRepository.findLastAttendanceByEmployeeId(UUID.fromString(employeeId))
                .orElseThrow(AttendanceNotFoundException::new);

        attendance.setCheckOutTime(LocalDateTime.now());

        Attendance persistedAttendance = attendanceRepository.save(attendance);

        return ClockOutResponse.builder()
                .successfullyClockedOut(Boolean.TRUE)
                .clockInTime(String.valueOf(persistedAttendance.getCheckInTime()))
                .employeeId(String.valueOf(persistedAttendance.getEmployeeId()))
                .clockOutTime(String.valueOf(persistedAttendance.getCheckOutTime()))
                .build();
    }
}
