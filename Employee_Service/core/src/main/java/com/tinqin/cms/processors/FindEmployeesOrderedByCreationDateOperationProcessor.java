package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindEmployeesOrderedByCreationDateOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindEmployeesOrderedByCreationDateOperationProcessor implements FindEmployeesOrderedByCreationDateOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindEmployeesOrderedByCreationDateResponse process(final FindEmployeesOrderedByCreationDateRequest request) {
        return null;
    }
}
