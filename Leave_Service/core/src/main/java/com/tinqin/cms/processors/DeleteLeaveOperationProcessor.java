package com.tinqin.cms.processors;

import com.tinqin.cms.operations.DeleteLeaveOperation;
import com.tinqin.cms.repositories.LeaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteLeaveOperationProcessor implements DeleteLeaveOperation {
    private final LeaveRepository leaveRepository;

    @Override
    public DeleteLeaveResponse process(final DeleteLeaveRequest request) {
        String id = request.getId();

        leaveRepository.deleteById(UUID.fromString(id));

        DeleteLeaveResponse response = DeleteLeaveResponse.builder()
                .isSuccessful(Boolean.TRUE)
                .build();

        return response;
    }
}
