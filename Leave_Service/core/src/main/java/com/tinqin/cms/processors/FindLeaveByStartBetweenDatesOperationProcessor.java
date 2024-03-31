package com.tinqin.cms.processors;

import com.tinqin.cms.converters.FindByStartBetweenDatesResponseDTOConverter;
import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.operations.FindByStartBetweenDatesOperation;
import com.tinqin.cms.repositories.LeaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindLeaveByStartBetweenDatesOperationProcessor implements FindByStartBetweenDatesOperation {
    private final LeaveRepository leaveRepository;
    private final FindByStartBetweenDatesResponseDTOConverter converter;

    @Override
    public FindByStartBetweenDatesResponse process(final FindByStartBetweenDatesRequest request) {
        String startDate = request.getStartDate();
        String endDate = request.getEndDate();

        List<Leave> findByStartDateBetween = leaveRepository.findByStartDateBetween(LocalDate.parse(startDate), LocalDate.parse(endDate));

        List<FindByStartBetweenDatesResponseDTO> list = findByStartDateBetween.stream()
                .map(converter::convert)
                .toList();

        return FindByStartBetweenDatesResponse.builder()
                .findByStartBetweenDatesResponseDTOS(list)
                .build();
    }
}
