package com.tinqin.cms.processors;

import com.tinqin.cms.entities.Employee;
import com.tinqin.cms.mappers.FindAllEmployeesMapper;
import com.tinqin.cms.operations.EmployeeResponseDTO;
import com.tinqin.cms.operations.FindAllEmployeesOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindAllEmployeeOperationProcessor implements FindAllEmployeesOperation {
    private final EmployeeRepository employeeRepository;
    private final FindAllEmployeesMapper employeeMapper;

    @Override
    public FindAllEmployeesResponse process(final FindAllEmployeesRequest request) {
        log.info("Processing request to find all employees");

        PageRequest pageRequest = PageRequest.of(
                request.getPageNumber(),
                request.getNumberOfBooksPerPage());

        Page<Employee> allEmployeesList = employeeRepository.findAll(pageRequest);
        log.info("Found {} employees", allEmployeesList.getTotalElements());

        List<FindAllEmployeesResponseDTO> employeeResponseDTOList = allEmployeesList.stream()
                .map(employeeMapper::mapToResponse)
                .toList();
        log.info("Mapped responses for all employees: {}", employeeResponseDTOList);

        FindAllEmployeesResponse response = FindAllEmployeesResponse.builder()
                .employeeResponseDTOS(employeeResponseDTOList)
                .build();
        log.info("Returning response for all employees: {}", response);

        return response;
    }
}
