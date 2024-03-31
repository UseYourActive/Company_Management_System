package com.tinqin.cms.converters;

import com.tinqin.cms.entities.Leave;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.FindByEmployeeIdOperation.*;

@Component
public class FindByEmployeeIdResponseDTOConverter implements Converter<Leave, FindByEmployeeIdResponseDTO> {
    @Override
    public FindByEmployeeIdResponseDTO convert(Leave source) {
        return FindByEmployeeIdResponseDTO.builder()
                .id(String.valueOf(source.getId()))
                .employeeId(String.valueOf(source.getEmployeeId()))
                .startDate(String.valueOf(source.getStartDate()))
                .endDate(String.valueOf(source.getEndDate()))
                .leaveType(String.valueOf(source.getLeaveType()))
                .status(String.valueOf(source.getStatus()))
                .build();
    }
}
