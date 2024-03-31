package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.enums.LeaveStatus;
import com.tinqin.cms.exceptions.LeaveNotFoundException;
import com.tinqin.cms.operations.RejectLeaveOperation;
import com.tinqin.cms.repositories.LeaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RejectLeaveOperationProcessor implements RejectLeaveOperation {
    private final LeaveRepository leaveRepository;
    @Override
    public RejectLeaveResponse process(final RejectLeaveRequest request) {
        String id = request.getId();

        Leave leave = leaveRepository.findById(UUID.fromString(id))
                .orElseThrow(LeaveNotFoundException::new);

        leave.setStatus(LeaveStatus.REJECTED);

        Leave persistedLeave = leaveRepository.save(leave);

        RejectLeaveResponse response = RejectLeaveResponse.builder()
                .isSuccessfullyRejected(Boolean.TRUE)
                .build();

        return response;
    }
}
