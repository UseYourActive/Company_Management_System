package com.tinqin.cms.processors;

import com.tinqin.cms.converters.CreateNewDepartmentMapper;
import com.tinqin.cms.entities.Department;
import com.tinqin.cms.operations.CreateNewDepartmentOperation;
import com.tinqin.cms.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Slf4j
@Service
public class CreateNewDepartmentOperationProcessor implements CreateNewDepartmentOperation {
    private final DepartmentRepository departmentRepository;
    private final CreateNewDepartmentMapper converter;

    @Override
    public CreateNewDepartmentResponse process(final CreateNewDepartmentRequest request) {
        String name = request.getName();
        String budget = request.getBudget();
        String description = request.getDescription();
        String email = request.getEmail();
        String location = request.getLocation();
        String phoneNumber = request.getPhoneNumber();

        Department department = Department.builder()
                .budget(new BigDecimal(budget))
                .email(email)
                .location(location)
                .phoneNumber(phoneNumber)
                .description(description)
                .name(name)
                .build();

        Department savedDepartment = departmentRepository.save(department);

        CreateNewDepartmentResponse response = converter.convert(savedDepartment);

        return response;
    }
}
