package com.tinqin.cms.processors;

import com.tinqin.cms.converters.EditDepartmentPhoneNumberConverter;
import com.tinqin.cms.entities.Department;
import com.tinqin.cms.exceptions.DepartmentNotFoundException;
import com.tinqin.cms.operations.EditDepartmentPhoneNumberOperation;
import com.tinqin.cms.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EditDepartmentPhoneNumberOperationProcessor implements EditDepartmentPhoneNumberOperation {
    private final DepartmentRepository departmentRepository;
    private final EditDepartmentPhoneNumberConverter converter;

    @Override
    public EditDepartmentPhoneNumberResponse process(final EditDepartmentPhoneNumberRequest request) {
        String id = request.getId();
        String phoneNumber = request.getPhoneNumber();

        log.info("Processing request to edit phone number for department with ID: {}", id);

        Department department = departmentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.warn("Department with ID {} not found. Unable to proceed.", id);
                    return new DepartmentNotFoundException("Department with given id has not been found");
                });

        log.debug("Department found with ID {}: {}", id, department);

        department.setPhoneNumber(phoneNumber);

        Department savedDepartment = departmentRepository.save(department);

        log.info("Phone number updated successfully for department with ID: {}", id);

        return converter.convert(savedDepartment);
    }
}
