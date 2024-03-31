package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Department;
import com.tinqin.cms.exceptions.DepartmentNotFoundException;
import com.tinqin.cms.operations.DeleteDepartmentOperation;
import com.tinqin.cms.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteDepartmentOperationProcessor implements DeleteDepartmentOperation {
    private final DepartmentRepository departmentRepository;

    @Override
    public DeleteDepartmentResponse process(final DeleteDepartmentRequest request) {
        String id = request.getId();

        log.info("Processing request to delete a department with ID: {}", id);

        Department department = departmentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.warn("Department with ID {} not found. Unable to delete.", id);
                    return new DepartmentNotFoundException("Department with given id has not been found");
                });

        departmentRepository.delete(department);
        log.info("Department with ID {} deleted successfully", id);

        DeleteDepartmentResponse response = DeleteDepartmentResponse.builder()
                .successfullyDeletedDepartment(Boolean.TRUE)
                .build();

        log.info("Returning response for deleted department: {}", response);

        return response;
    }
}
