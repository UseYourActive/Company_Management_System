package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindEmployeesWithSalaryGreaterThanOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindEmployeesWithSalaryGreaterThanOperationProcessor implements FindEmployeesWithSalaryGreaterThanOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindEmployeesWithSalaryGreaterThanResponse process(final FindEmployeesWithSalaryGreaterThanRequest request) {
        return null;
    }
}
