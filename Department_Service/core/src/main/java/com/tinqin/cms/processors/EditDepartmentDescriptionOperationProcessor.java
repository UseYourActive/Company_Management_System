package com.tinqin.cms.processors;

import com.tinqin.cms.converters.EditDepartmentDescriptionConverter;
import com.tinqin.cms.entities.Department;
import com.tinqin.cms.exceptions.DepartmentNotFoundException;
import com.tinqin.cms.operations.EditDepartmentDescriptionOperation;
import com.tinqin.cms.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class EditDepartmentDescriptionOperationProcessor implements EditDepartmentDescriptionOperation {
    private final DepartmentRepository departmentRepository;
    private final EditDepartmentDescriptionConverter converter;

    @Override
    public EditDepartmentDescriptionResponse process(final EditDepartmentDescriptionRequest request) {
        String id = request.getId();
        String description = request.getDescription();

        log.info("Processing request to edit description for department with ID: {}", id);

        Department department = departmentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.warn("Department with ID {} not found. Unable to proceed.", id);
                    return new DepartmentNotFoundException("Department with given id has not been found");
                });

        log.debug("Department found with ID {}: {}", id, department);

        department.setDescription(description);

        Department savedDepartment = departmentRepository.save(department);

        log.info("Description updated successfully for department with ID: {}", id);

        return converter.convert(savedDepartment);
    }
}
