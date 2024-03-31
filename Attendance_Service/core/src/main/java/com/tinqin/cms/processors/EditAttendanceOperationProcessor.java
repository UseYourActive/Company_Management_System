package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Attendance;
import com.tinqin.cms.exceptions.AttendanceNotFoundException;
import com.tinqin.cms.operations.EditAttendanceOperation;
import com.tinqin.cms.repositories.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EditAttendanceOperationProcessor implements EditAttendanceOperation {
    private final AttendanceRepository attendanceRepository;

    @Override
    public EditAttendanceResponse process(final EditAttendanceRequest request) {
        log.info("Processing edit attendance request: {}", request);

        String checkInTime = request.getCheckInTime();
        String checkOutTime = request.getCheckOutTime();
        String id = request.getId();
        String employeeId = request.getEmployeeId();

        log.debug("Fetching attendance with ID: {}", id);

        Optional<Attendance> optionalAttendance = attendanceRepository.findById(UUID.fromString(id));
        Attendance attendance = optionalAttendance.orElseThrow(AttendanceNotFoundException::new);

        optionalAttendance.ifPresent(att -> {
            log.debug("Updating check-in time to: {}", checkInTime);
            att.setCheckInTime(LocalDateTime.parse(checkInTime));
        });

        optionalAttendance.ifPresent(att -> {
            log.debug("Updating check-out time to: {}", checkOutTime);
            att.setCheckOutTime(LocalDateTime.parse(checkOutTime));
        });

        optionalAttendance.ifPresent(att -> {
            log.debug("Updating employee ID to: {}", employeeId);
            att.setEmployeeId(UUID.fromString(employeeId));
        });

        Attendance persistedAttendance = attendanceRepository.save(attendance);

        log.info("Attendance successfully edited: {}", persistedAttendance);

        return EditAttendanceResponse.builder()
                .id(String.valueOf(persistedAttendance.getId()))
                .checkInTime(String.valueOf(persistedAttendance.getCheckInTime()))
                .checkOutTime(String.valueOf(persistedAttendance.getCheckOutTime()))
                .employeeId(String.valueOf(persistedAttendance.getEmployeeId()))
                .build();
    }
}

