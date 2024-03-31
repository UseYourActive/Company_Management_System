package com.tinqin.cms.processors;

import com.tinqin.cms.operations.FindEmployeeByPhoneNumberOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindEmployeeByPhoneNumberOperationProcessor implements FindEmployeeByPhoneNumberOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindEmployeeByPhoneNumberResponse process(final FindEmployeeByPhoneNumberRequest request) {
        return null;
    }
}
