package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Attendance;
import com.tinqin.cms.exceptions.InvalidCheckInTimeException;
import com.tinqin.cms.operations.CreateNewAttendanceOperation;
import com.tinqin.cms.repositories.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateNewAttendanceOperationProcessor implements CreateNewAttendanceOperation {
    private final AttendanceRepository attendanceRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private final KafkaTemplate<String, String> kafkaTemplate;


    @Override
    public CreateNewAttendanceResponse process(final CreateNewAttendanceRequest request) {
        log.info("Processing create new attendance request: {}", request);

        String checkInTime = request.getCheckInTime();
        String employeeId = request.getEmployeeId();
        String checkOutTime = request.getCheckOutTime();

        log.debug("Creating new Attendance object with check-in time: {}, check-out time: {}, employee ID: {}",
                checkInTime, checkOutTime, employeeId);

        LocalDateTime checkInTimeParsed = LocalDateTime.parse(checkInTime);
        LocalDateTime checkOutTimeParsed = LocalDateTime.parse(checkOutTime);

        if (checkOutTimeParsed.isBefore(checkInTimeParsed)) {
            throw new InvalidCheckInTimeException("Check-out time cannot be before check-in time");
        }

        Attendance attendance = Attendance.builder()
                .checkInTime(checkInTimeParsed)
                .checkOutTime(checkOutTimeParsed)
                .employeeId(UUID.fromString(employeeId))
                .build();

        log.debug("Saving new attendance record to the database: {}", attendance);

        Attendance persistedAttendance = attendanceRepository.save(attendance);

        log.info("Attendance successfully created: {}", persistedAttendance.getId());

        kafkaTemplate.send("ATTENDANCE-SERVICE", "Successfully created a new attendance.");

        return CreateNewAttendanceResponse.builder()
                .id(String.valueOf(persistedAttendance.getId()))
                .checkInTime(checkInTimeParsed.format(formatter))
                .checkOutTime(checkOutTimeParsed.format(formatter))
                .employeeId(String.valueOf(persistedAttendance.getEmployeeId()))
                .build();
    }
}
