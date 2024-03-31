package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindEmployeesByFirstNameOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindEmployeesByFirstNameOperationProcessor implements FindEmployeesByFirstNameOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindEmployeesByFirstNameResponse process(final FindEmployeesByFirstNameRequest request) {
        return null;
    }
}
