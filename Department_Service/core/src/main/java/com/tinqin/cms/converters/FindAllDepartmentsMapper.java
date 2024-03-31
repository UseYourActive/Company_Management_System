package com.tinqin.cms.converters;

import com.tinqin.cms.entities.Department;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.FindAllDepartmentsOperation.*;

@Component
public class FindAllDepartmentsMapper implements Converter<Department, FindAllDepartmentsResponseDTO> {
    @Override
    public FindAllDepartmentsResponseDTO convert(Department source) {
        return FindAllDepartmentsResponseDTO.builder()
                .id(String.valueOf(source.getId()))
                .email(source.getEmail())
                .name(source.getName())
                .budget(String.valueOf(source.getBudget()))
                .description(source.getDescription())
                .location(source.getLocation())
                .phoneNumber(source.getPhoneNumber())
                .build();
    }
}
