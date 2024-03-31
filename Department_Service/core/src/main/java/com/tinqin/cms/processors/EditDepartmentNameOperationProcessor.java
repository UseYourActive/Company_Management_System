package com.tinqin.cms.processors;

import com.tinqin.cms.converters.EditDepartmentNameConverter;
import com.tinqin.cms.entities.Department;
import com.tinqin.cms.exceptions.DepartmentNotFoundException;
import com.tinqin.cms.operations.EditDepartmentNameOperation;
import com.tinqin.cms.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class EditDepartmentNameOperationProcessor implements EditDepartmentNameOperation {
    private final DepartmentRepository departmentRepository;
    private final EditDepartmentNameConverter converter;

    @Override
    public EditDepartmentNameResponse process(final EditDepartmentNameRequest request) {
        String name = request.getName();
        String id = request.getId();

        log.info("Processing request to edit name for department with ID: {}", id);

        Department department = departmentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.warn("Department with ID {} not found. Unable to proceed.", id);
                    return new DepartmentNotFoundException("Department with given id has not been found");
                });

        log.debug("Department found with ID {}: {}", id, department);

        department.setName(name);

        Department savedDepartment = departmentRepository.save(department);

        log.info("Name updated successfully for department with ID: {}", id);

        return converter.convert(savedDepartment);
    }
}
