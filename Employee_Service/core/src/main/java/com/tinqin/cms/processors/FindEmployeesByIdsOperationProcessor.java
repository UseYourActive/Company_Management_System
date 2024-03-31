package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindEmployeesByIdsOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindEmployeesByIdsOperationProcessor implements FindEmployeesByIdsOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindEmployeesByIdsResponse process(final FindEmployeesByIdsRequest request) {
        return null;
    }
}
