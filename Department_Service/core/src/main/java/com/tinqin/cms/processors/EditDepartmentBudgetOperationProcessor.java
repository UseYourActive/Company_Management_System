package com.tinqin.cms.processors;

import com.tinqin.cms.converters.EditDepartmentBudgetConverter;
import com.tinqin.cms.entities.Department;
import com.tinqin.cms.exceptions.DepartmentNotFoundException;
import com.tinqin.cms.operations.EditDepartmentBudgetOperation;
import com.tinqin.cms.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EditDepartmentBudgetOperationProcessor implements EditDepartmentBudgetOperation {
    private final DepartmentRepository departmentRepository;
    private final EditDepartmentBudgetConverter converter;

    @Override
    public EditDepartmentBudgetResponse process(final EditDepartmentBudgetRequest request) {
        String id = request.getId();
        String budget = request.getBudget();

        log.info("Processing request to edit budget for department with ID: {}", id);

        Department department = departmentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    log.warn("Department with ID {} not found. Unable to proceed.", id);
                    return new DepartmentNotFoundException("Department with given id has not been found");
                });

        log.debug("Department found with ID {}: {}", id, department);

        department.setBudget(new BigDecimal(budget));

        Department savedDepartment = departmentRepository.save(department);

        log.info("Budget updated successfully for department with ID: {}", id);

        return converter.convert(savedDepartment);
    }
}
