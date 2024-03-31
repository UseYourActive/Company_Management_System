package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindEmployeesByDesignationOrderBySalaryOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindEmployeesByDesignationOrderBySalaryOperationProcessor implements FindEmployeesByDesignationOrderBySalaryOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindEmployeesByDesignationOrderBySalaryResponse process(final FindEmployeesByDesignationOrderBySalaryRequest request) {
        return null;
    }
}
