package com.tinqin.cms.converters;

import com.tinqin.cms.entities.Department;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static com.tinqin.cms.operations.EditDepartmentBudgetOperation.*;

@Component
public class EditDepartmentBudgetConverter implements Converter<Department, EditDepartmentBudgetResponse> {
    @Override
    public EditDepartmentBudgetResponse convert(Department source) {
        return EditDepartmentBudgetResponse.builder()
                .id(String.valueOf(source.getId()))
                .name(source.getName())
                .location(source.getLocation())
                .email(source.getEmail())
                .budget(String.valueOf(source.getBudget()))
                .description(source.getDescription())
                .phoneNumber(source.getPhoneNumber())
                .build();
    }
}
