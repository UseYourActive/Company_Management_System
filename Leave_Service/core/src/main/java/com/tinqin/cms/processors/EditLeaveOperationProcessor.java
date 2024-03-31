package com.tinqin.cms.processors;

import com.tinqin.cms.operations.EditLeaveOperation;
import com.tinqin.cms.repositories.LeaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EditLeaveOperationProcessor implements EditLeaveOperation {
    private final LeaveRepository leaveRepository;

    @Override
    public EditLeaveResponse process(final EditLeaveRequest request) {
        return null;
    }
}
