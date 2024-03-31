package com.tinqin.cms.processors;

import com.tinqin.cms.converters.FindByEmployeeIdResponseDTOConverter;
import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.operations.FindByEmployeeIdOperation;
import com.tinqin.cms.repositories.LeaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindLeaveByEmployeeIdOperationProcessor implements FindByEmployeeIdOperation {
    private final LeaveRepository leaveRepository;
    private final FindByEmployeeIdResponseDTOConverter converter;

    @Override
    public FindByEmployeeIdResponse process(final FindByEmployeeIdRequest request) {
        String employeeId = request.getEmployeeId();

        List<Leave> findByEmployeeId = leaveRepository.findByEmployeeId(UUID.fromString(employeeId));

        List<FindByEmployeeIdResponseDTO> list = findByEmployeeId.stream()
                .map(converter::convert)
                .toList();

        return FindByEmployeeIdResponse.builder()
                .findByEmployeeIdResponseDTOS(list)
                .build();
    }
}
