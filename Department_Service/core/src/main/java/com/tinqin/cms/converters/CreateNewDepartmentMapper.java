package com.tinqin.cms.converters;

import com.tinqin.cms.entities.Department;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.CreateNewDepartmentOperation.*;

@Component
public class CreateNewDepartmentMapper implements Converter<Department, CreateNewDepartmentResponse> {
    @Override
    public CreateNewDepartmentResponse convert(Department source) {
        return CreateNewDepartmentResponse.builder()
                .id(String.valueOf(source.getId()))
                .budget(String.valueOf(source.getBudget()))
                .email(source.getEmail())
                .location(source.getLocation())
                .phoneNumber(source.getPhoneNumber())
                .description(source.getDescription())
                .name(source.getName())
                .build();
    }
}
