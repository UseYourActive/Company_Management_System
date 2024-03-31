package com.tinqin.cms.processors;

import com.tinqin.cms.mappers.EditEmployeeMapper;
import com.tinqin.cms.operations.EditEmployeeOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EditEmployeeOperationProcessor implements EditEmployeeOperation {
    private final EmployeeRepository employeeRepository;
    private final EditEmployeeMapper employeeMapper;
    @Override
    public EditEmployeeResponse process(final EditEmployeeRequest request) {
        return null;
    }
}
