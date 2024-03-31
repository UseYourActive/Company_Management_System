package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindTopThreeByOrderBySalaryAmountDescOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindTopThreeByOrderBySalaryAmountDescOperationProcessor implements FindTopThreeByOrderBySalaryAmountDescOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindTopThreeByOrderBySalaryAmountDescResponse process(final FindTopThreeByOrderBySalaryAmountDescRequest request) {
        return null;
    }
}
