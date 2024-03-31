package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindEmployeeByUniqueCivilianNumberOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindEmployeeByUniqueCivilianNumberOperationProcessor implements FindEmployeeByUniqueCivilianNumberOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindEmployeeByUniqueCivilianNumberResponse process(final FindEmployeeByUniqueCivilianNumberRequest request) {
        return null;
    }
}
