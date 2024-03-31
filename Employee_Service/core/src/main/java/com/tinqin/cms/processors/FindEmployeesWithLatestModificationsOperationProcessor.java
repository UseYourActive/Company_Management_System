package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindEmployeesWithLatestModificationsOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindEmployeesWithLatestModificationsOperationProcessor implements FindEmployeesWithLatestModificationsOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindEmployeesWithLatestModificationsResponse process(final FindEmployeesWithLatestModificationsRequest request) {
        return null;
    }
}
