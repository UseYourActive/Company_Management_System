package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Department;
import com.tinqin.cms.exceptions.DepartmentNotFoundException;
import com.tinqin.cms.operations.EditFullDepartmentOperation;
import com.tinqin.cms.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EditFullDepartmentOperationProcessor implements EditFullDepartmentOperation {
    private final DepartmentRepository departmentRepository;

    @Override
    public EditFullDepartmentResponse process(final EditFullDepartmentRequest request) {
        String id = request.getId();
        log.info("Processing edit full department request for department with ID: {}", id);

        Department department = departmentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.warn("Department with ID {} not found. Unable to proceed.", id);
                    return new DepartmentNotFoundException("Department with given id has not been found");
                });

        Optional.ofNullable(request.getName())
                .ifPresent(name -> {
                    department.setName(name);
                    log.debug("Updated department name to: {}", name);
                });
        Optional.ofNullable(request.getDescription())
                .ifPresent(description -> {
                    department.setDescription(description);
                    log.debug("Updated department description to: {}", description);
                });
        Optional.ofNullable(request.getBudget())
                .map(BigDecimal::new)
                .ifPresent(budget -> {
                    department.setBudget(budget);
                    log.debug("Updated department budget to: {}", budget);
                });
        Optional.ofNullable(request.getLocation())
                .ifPresent(location -> {
                    department.setLocation(location);
                    log.debug("Updated department location to: {}", location);
                });
        Optional.ofNullable(request.getPhoneNumber())
                .ifPresent(phoneNumber -> {
                    department.setPhoneNumber(phoneNumber);
                    log.debug("Updated department phone number to: {}", phoneNumber);
                });
        Optional.ofNullable(request.getEmail())
                .ifPresent(email -> {
                    department.setEmail(email);
                    log.debug("Updated department email to: {}", email);
                });

        Department savedDepartment = departmentRepository.save(department);
        log.info("Department with ID {} successfully updated", id);

        EditFullDepartmentResponse response = EditFullDepartmentResponse.builder()
                .id(String.valueOf(savedDepartment.getId()))
                .budget(String.valueOf(savedDepartment.getBudget()))
                .description(savedDepartment.getDescription())
                .email(savedDepartment.getEmail())
                .location(savedDepartment.getLocation())
                .phoneNumber(savedDepartment.getPhoneNumber())
                .name(savedDepartment.getName())
                .build();

        return response;
    }
}
