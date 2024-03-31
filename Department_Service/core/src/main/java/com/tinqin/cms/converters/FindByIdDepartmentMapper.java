package com.tinqin.cms.converters;

import com.tinqin.cms.entities.Department;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.FindByIdDepartmentOperation.*;

@Component
public class FindByIdDepartmentMapper implements Converter<Department, FindByIdDepartmentResponse> {
    @Override
    public FindByIdDepartmentResponse convert(Department source) {
        return FindByIdDepartmentResponse.builder()
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
