package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindEmployeesByGenderOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindEmployeesByGenderOperationProcessor implements FindEmployeesByGenderOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindEmployeesByGenderResponse process(final FindEmployeesByGenderRequest request) {
        return null;
    }
}
