package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindEmployeesByLastNameOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindEmployeesByLastNameOperationProcessor implements FindEmployeesByLastNameOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindEmployeesByLastNameResponse process(final FindEmployeesByLastNameRequest request) {
        return null;
    }
}
