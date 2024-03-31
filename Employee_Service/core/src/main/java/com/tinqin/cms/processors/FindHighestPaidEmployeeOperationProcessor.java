package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindHighestPaidEmployeeOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindHighestPaidEmployeeOperationProcessor implements FindHighestPaidEmployeeOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindHighestPaidEmployeeResponse process(final FindHighestPaidEmployeeRequest request) {
        return null;
    }
}
