package com.tinqin.cms.processors;

import com.tinqin.cms.converters.FindAllDepartmentsMapper;
import com.tinqin.cms.entities.Department;
import com.tinqin.cms.operations.FindAllDepartmentsOperation;
import com.tinqin.cms.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindAllDepartmentsOperationProcessor implements FindAllDepartmentsOperation {
    private final DepartmentRepository departmentRepository;
    private final FindAllDepartmentsMapper converter;

    @Override
    public FindAllDepartmentsResponse process(final FindAllDepartmentsRequest request) {
        log.info("Processing request to find all departments");

        PageRequest pageRequest = PageRequest.of(
                request.getPageNumber(),
                request.getNumberOfBooksPerPage());

        Page<Department> allDepartmentsList = departmentRepository.findAll(pageRequest);
        log.info("Found {} departments", allDepartmentsList.getTotalElements());

        List<FindAllDepartmentsResponseDTO> departmentResponseDTOList = allDepartmentsList.stream()
                .map(converter::convert)
                .toList();
        log.info("Mapped responses for all departments: {}", departmentResponseDTOList);

        FindAllDepartmentsResponse response = FindAllDepartmentsResponse.builder()
                .findAllDepartmentsResponseDTOS(departmentResponseDTOList)
                .build();
        log.info("Returning response for all departments: {}", response);

        return response;
    }
}
