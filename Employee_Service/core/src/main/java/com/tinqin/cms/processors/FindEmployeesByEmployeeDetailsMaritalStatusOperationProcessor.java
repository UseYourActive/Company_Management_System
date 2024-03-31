package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindEmployeesByEmployeeDetailsMaritalStatusOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindEmployeesByEmployeeDetailsMaritalStatusOperationProcessor implements FindEmployeesByEmployeeDetailsMaritalStatusOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindEmployeesByEmployeeDetailsMaritalStatusResponse process(final FindEmployeesByEmployeeDetailsMaritalStatusRequest request) {
        return null;
    }
}
