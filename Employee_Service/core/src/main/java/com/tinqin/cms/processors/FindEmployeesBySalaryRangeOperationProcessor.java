package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindEmployeesBySalaryRangeOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindEmployeesBySalaryRangeOperationProcessor implements FindEmployeesBySalaryRangeOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindEmployeesBySalaryRangeResponse process(final FindEmployeesBySalaryRangeRequest request) {
        return null;
    }
}
