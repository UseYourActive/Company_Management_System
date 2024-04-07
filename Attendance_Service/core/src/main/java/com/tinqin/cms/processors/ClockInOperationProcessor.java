package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Attendance;
import com.tinqin.cms.operations.ClockInOperation;
import com.tinqin.cms.repositories.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClockInOperationProcessor implements ClockInOperation {
    private final AttendanceRepository attendanceRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public ClockInResponse process(final ClockInRequest request) {
        String employeeId = request.getEmployeeId();

        Attendance attendance = Attendance.builder()
                .employeeId(UUID.fromString(employeeId))
                .checkInTime(LocalDateTime.now())
                .build();

        Attendance persistedAttendance = attendanceRepository.save(attendance);

        kafkaTemplate.send("ATTENDANCE-SERVICE", "Successfully clocked in attendance.");

       return ClockInResponse.builder()
               .id(String.valueOf(attendance.getId()))
               .clockInTime(String.valueOf(persistedAttendance.getCheckInTime()))
               .employeeId(String.valueOf(persistedAttendance.getEmployeeId()))
               .successfullyClockedIn(Boolean.TRUE)
               .build();
    }
}
