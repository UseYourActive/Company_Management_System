package com.tinqin.cms.processors;

import com.tinqin.cms.converters.FindLeavesByEmployeeIdAndDateRangeResponseDTOConverter;
import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.operations.FindLeavesByEmployeeIdAndDateRangeOperation;
import com.tinqin.cms.repositories.LeaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindLeavesByEmployeeIdAndDateRangeOperationProcessor implements FindLeavesByEmployeeIdAndDateRangeOperation {
    private final LeaveRepository leaveRepository;
    private final FindLeavesByEmployeeIdAndDateRangeResponseDTOConverter converter;

    @Override
    public FindLeavesByEmployeeIdAndDateRangeResponse process(final FindLeavesByEmployeeIdAndDateRangeRequest request) {
        String employeeId = request.getEmployeeId();
        String startDate = request.getStartDate();
        String endDate = request.getEndDate();

        List<Leave> findLeavesByEmployeeIdAndDateRange =
                leaveRepository.findLeavesByEmployeeIdAndDateRange(UUID.fromString(employeeId), LocalDate.parse(startDate), LocalDate.parse(endDate));

        List<FindLeavesByEmployeeIdAndDateRangeResponseDTO> list = findLeavesByEmployeeIdAndDateRange.stream()
                .map(converter::convert)
                .toList();

        return FindLeavesByEmployeeIdAndDateRangeResponse.builder()
                .findLeavesByEmployeeIdAndDateRangeResponseDTOS(list)
                .build();
    }
}
