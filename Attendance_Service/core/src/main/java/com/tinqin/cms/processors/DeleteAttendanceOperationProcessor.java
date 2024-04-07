package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Attendance;
import com.tinqin.cms.exceptions.AttendanceNotFoundException;
import com.tinqin.cms.operations.DeleteAttendanceOperation;
import com.tinqin.cms.repositories.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteAttendanceOperationProcessor implements DeleteAttendanceOperation {
    private final AttendanceRepository attendanceRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public DeleteAttendanceResponse process(final DeleteAttendanceRequest request) {
        log.info("Processing delete attendance request: {}", request);

        String id = request.getId();

        log.debug("Finding attendance record with ID: {}", id);

        Attendance attendance = attendanceRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.error("Attendance not found with ID: {}", id);
                    return new AttendanceNotFoundException();
                });

        log.debug("Attendance record found: {}", attendance);

        log.debug("Deleting attendance record: {}", attendance);

        attendanceRepository.delete(attendance);

        log.info("Attendance record successfully deleted");

        DeleteAttendanceResponse response = DeleteAttendanceResponse.builder()
                .isSuccessfullyDeleted(Boolean.TRUE)
                .build();

        log.info("Returning response for deleted attendance: {}", response);

        kafkaTemplate.send("ATTENDANCE-SERVICE", "Successfully deleted a attendance.");

        return response;
    }
}
