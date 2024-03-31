package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.enums.LeaveStatus;
import com.tinqin.cms.enums.LeaveType;
import com.tinqin.cms.operations.CreateLeaveOperation;
import com.tinqin.cms.repositories.LeaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateLeaveOperationProcessor implements CreateLeaveOperation {
    private final LeaveRepository leaveRepository;

    @Override
    public CreateLeaveResponse process(final CreateLeaveRequest request) {
        String employeeId = request.getEmployeeId();
        String leaveType = request.getLeaveType();
        String endDate = request.getEndDate();
        String startDate = request.getStartDate();

        Leave leave = Leave.builder()
                .status(LeaveStatus.PENDING)
                .employeeId(UUID.fromString(employeeId))
                .leaveType(LeaveType.valueOf(leaveType))
                .startDate(LocalDate.parse(startDate))
                .endDate(LocalDate.parse(endDate))
                .build();

        Leave persistedLeave = leaveRepository.save(leave);

        CreateLeaveResponse response = CreateLeaveResponse.builder()
                .id(String.valueOf(persistedLeave.getId()))
                .employeeId(String.valueOf(persistedLeave.getEmployeeId()))
                .leaveType(String.valueOf(persistedLeave.getLeaveType()))
                .status(String.valueOf(persistedLeave.getStatus()))
                .startDate(String.valueOf(persistedLeave.getStartDate()))
                .endDate(String.valueOf(persistedLeave.getEndDate()))
                .build();

        return response;
    }
}
