package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindEmployeesByFirstNameAndLastNameOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindEmployeesByFirstNameAndLastNameOperationProcessor implements FindEmployeesByFirstNameAndLastNameOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindEmployeesByFirstNameAndLastNameResponse process(final FindEmployeesByFirstNameAndLastNameRequest request) {
        return null;
    }
}
