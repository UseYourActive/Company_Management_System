package com.tinqin.cms.converters;

import com.tinqin.cms.entities.Leave;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.FindAllLeavesOperation.*;

@Component
public class FindAllLeavesResponseDTOConverter implements Converter<Leave, FindAllLeavesResponseDTO> {
    @Override
    public FindAllLeavesResponseDTO convert(Leave source) {
        return FindAllLeavesResponseDTO.builder()
                .id(String.valueOf(source.getId()))
                .employeeId(String.valueOf(source.getEmployeeId()))
                .startDate(String.valueOf(source.getStartDate()))
                .endDate(String.valueOf(source.getEndDate()))
                .leaveType(String.valueOf(source.getLeaveType()))
                .status(String.valueOf(source.getStatus()))
                .build();
    }
}
