package com.tinqin.cms.converters;

import com.tinqin.cms.entities.Leave;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.FindByStartBetweenDatesOperation.*;

@Component
public class FindByStartBetweenDatesResponseDTOConverter implements Converter<Leave, FindByStartBetweenDatesResponseDTO> {
    @Override
    public FindByStartBetweenDatesResponseDTO convert(Leave source) {
        return FindByStartBetweenDatesResponseDTO.builder()
                .id(String.valueOf(source.getId()))
                .employeeId(String.valueOf(source.getEmployeeId()))
                .startDate(String.valueOf(source.getStartDate()))
                .endDate(String.valueOf(source.getEndDate()))
                .leaveType(String.valueOf(source.getLeaveType()))
                .status(String.valueOf(source.getStatus()))
                .build();
    }
}
