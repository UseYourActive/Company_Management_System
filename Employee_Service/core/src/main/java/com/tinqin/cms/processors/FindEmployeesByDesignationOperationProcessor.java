package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindEmployeesByDesignationOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindEmployeesByDesignationOperationProcessor implements FindEmployeesByDesignationOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindEmployeesByDesignationResponse process(final FindEmployeesByDesignationRequest request) {
        return null;
    }
}
