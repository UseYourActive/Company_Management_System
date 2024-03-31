package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Employee;
import com.tinqin.cms.exceptions.EmployeeNotFoundException;
import com.tinqin.cms.operations.FindEmployeeByEmailOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindEmployeeByEmailOperationProcessor implements FindEmployeeByEmailOperation {
    private final EmployeeRepository employeeRepository;

    @Override
    public FindEmployeeByEmailResponse process(final FindEmployeeByEmailRequest request) {
        String email = request.getEmail();

        Employee employee = employeeRepository.findEmployeeByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with such email doesn't exist"));

//        return FindEmployeeByEmailResponse.builder()
//
//                .build();

        return null;
    }
}
