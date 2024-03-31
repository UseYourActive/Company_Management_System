package com.tinqin.cms.processors;

import com.tinqin.cms.converters.EditDepartmentEmailConverter;
import com.tinqin.cms.entities.Department;
import com.tinqin.cms.exceptions.DepartmentNotFoundException;
import com.tinqin.cms.operations.EditDepartmentEmailOperation;
import com.tinqin.cms.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EditDepartmentEmailOperationProcessor implements EditDepartmentEmailOperation {
    private final DepartmentRepository departmentRepository;
    private final EditDepartmentEmailConverter converter;

    @Override
    public EditDepartmentEmailResponse process(final EditDepartmentEmailRequest request) {
        String id = request.getId();
        String email = request.getEmail();

        log.info("Processing request to edit email for department with ID: {}", id);

        Department department = departmentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.warn("Department with ID {} not found. Unable to proceed.", id);
                    return new DepartmentNotFoundException("Department with given id has not been found");
                });

        log.debug("Department found with ID {}: {}", id, department);

        department.setEmail(email);

        Department savedDepartment = departmentRepository.save(department);

        log.info("Email updated successfully for department with ID: {}", id);

        return converter.convert(savedDepartment);
    }
}
