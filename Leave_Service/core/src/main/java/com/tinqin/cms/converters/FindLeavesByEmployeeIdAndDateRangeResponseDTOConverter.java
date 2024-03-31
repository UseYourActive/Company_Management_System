package com.tinqin.cms.converters;

import com.tinqin.cms.entities.Leave;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.FindLeavesByEmployeeIdAndDateRangeOperation.*;

@Component
public class FindLeavesByEmployeeIdAndDateRangeResponseDTOConverter implements Converter<Leave, FindLeavesByEmployeeIdAndDateRangeResponseDTO> {
    @Override
    public FindLeavesByEmployeeIdAndDateRangeResponseDTO convert(Leave source) {
        return FindLeavesByEmployeeIdAndDateRangeResponseDTO.builder()
                .id(String.valueOf(source.getId()))
                .employeeId(String.valueOf(source.getEmployeeId()))
                .startDate(String.valueOf(source.getStartDate()))
                .endDate(String.valueOf(source.getEndDate()))
                .leaveType(String.valueOf(source.getLeaveType()))
                .status(String.valueOf(source.getStatus()))
                .build();
    }
}
