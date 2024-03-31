package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.enums.LeaveStatus;
import com.tinqin.cms.exceptions.LeaveNotFoundException;
import com.tinqin.cms.operations.ApproveLeaveOperation;
import com.tinqin.cms.repositories.LeaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApproveLeaveOperationProcessor implements ApproveLeaveOperation {
    private final LeaveRepository leaveRepository;

    @Override
    public ApproveLeaveOperation.ApproveLeaveResponse process(final ApproveLeaveRequest request) {
        String id = request.getId();

        Leave leave = leaveRepository.findById(UUID.fromString(id))
                .orElseThrow(LeaveNotFoundException::new);

        leave.setStatus(LeaveStatus.APPROVED);

        Leave persistedLeave = leaveRepository.save(leave);

        ApproveLeaveResponse response = ApproveLeaveResponse.builder()
                .successfullyApprovedLeave(Boolean.TRUE)
                .build();

        return response;
    }
}
