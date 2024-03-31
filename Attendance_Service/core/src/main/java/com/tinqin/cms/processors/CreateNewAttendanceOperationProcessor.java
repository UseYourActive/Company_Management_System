package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Attendance;
import com.tinqin.cms.operations.CreateNewAttendanceOperation;
import com.tinqin.cms.repositories.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateNewAttendanceOperationProcessor implements CreateNewAttendanceOperation {
    private final AttendanceRepository attendanceRepository;

    @Override
    public CreateNewAttendanceResponse process(final CreateNewAttendanceRequest request) {
        log.info("Processing create new attendance request: {}", request);

        String checkInTime = request.getCheckInTime();
        String employeeId = request.getEmployeeId();
        String checkOutTime = request.getCheckOutTime();

        log.debug("Creating new Attendance object with check-in time: {}, check-out time: {}, employee ID: {}",
                checkInTime, checkOutTime, employeeId);

        Attendance attendance = Attendance.builder()
                .checkInTime(LocalDateTime.parse(checkInTime))
                .checkOutTime(LocalDateTime.parse(checkOutTime))
                .employeeId(UUID.fromString(employeeId))
                .build();

        log.debug("Saving new attendance record to the database: {}", attendance);

        Attendance persistedAttendance = attendanceRepository.save(attendance);

        log.info("Attendance successfully created: {}", persistedAttendance.getId());

        return CreateNewAttendanceResponse.builder()
                .id(String.valueOf(persistedAttendance.getId()))
                .checkInTime(String.valueOf(persistedAttendance.getCheckInTime()))
                .checkOutTime(String.valueOf(persistedAttendance.getCheckOutTime()))
                .employeeId(String.valueOf(persistedAttendance.getEmployeeId()))
                .build();
    }
}
