package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindEmployeesByZipCodeOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindEmployeesByZipCodeOperationProcessor implements FindEmployeesByZipCodeOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindEmployeesByZipCodeResponse process(final FindEmployeesByZipCodeRequest request) {
        return null;
    }
}
