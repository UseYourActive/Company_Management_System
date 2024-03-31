package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindEmployeesByDateOfBirthBetweenOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindEmployeesByDateOfBirthBetweenOperationProcessor implements FindEmployeesByDateOfBirthBetweenOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindEmployeesByDateOfBirthBetweenResponse process(final FindEmployeesByDateOfBirthBetweenRequest request) {
        return null;
    }
}
