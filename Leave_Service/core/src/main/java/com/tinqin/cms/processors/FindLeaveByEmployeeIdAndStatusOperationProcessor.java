package com.tinqin.cms.processors;

import com.tinqin.cms.converters.FindByEmployeeIdAndStatusResponseDTOConverter;
import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.enums.LeaveStatus;
import com.tinqin.cms.operations.FindByEmployeeIdAndStatusOperation;
import com.tinqin.cms.repositories.LeaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindLeaveByEmployeeIdAndStatusOperationProcessor implements FindByEmployeeIdAndStatusOperation {
    private final LeaveRepository leaveRepository;
    private final FindByEmployeeIdAndStatusResponseDTOConverter converter;

    @Override
    public FindByEmployeeIdAndStatusResponse process(final FindByEmployeeIdAndStatusRequest request) {
        String employeeId = request.getEmployeeId();
        String status = request.getStatus();

        List<Leave> findByEmployeeIdAndStatus = leaveRepository.findByEmployeeIdAndStatus(UUID.fromString(employeeId), LeaveStatus.valueOf(status));

        List<FindByEmployeeIdAndStatusResponseDTO> list = findByEmployeeIdAndStatus.stream()
                .map(converter::convert)
                .toList();

        return FindByEmployeeIdAndStatusResponse.builder()
                .findByEmployeeIdAndStatusResponseDTOS(list)
                .build();
    }
}
