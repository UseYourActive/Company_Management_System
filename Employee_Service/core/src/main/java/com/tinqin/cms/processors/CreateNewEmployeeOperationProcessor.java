package com.tinqin.cms.processors;

import com.tinqin.cms.entities.*;
import com.tinqin.cms.enums.Department;
import com.tinqin.cms.enums.Designation;
import com.tinqin.cms.enums.Gender;
import com.tinqin.cms.enums.MaritalStatus;
import com.tinqin.cms.mappers.CreateNewEmployeeMapper;
import com.tinqin.cms.operations.CreateNewEmployeeOperation;
import com.tinqin.cms.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateNewEmployeeOperationProcessor implements CreateNewEmployeeOperation {
    private final EmployeeRepository employeeRepository;
    private final CreateNewEmployeeMapper employeeMapper;
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    @Override
    public CreateNewEmployeeResponse process(final CreateNewEmployeeRequest request) {
        log.info("Processing request to create a new employee for {}", request.getPersonalInformation().getFirstName());

        Employee employee = Employee.builder()
                .personalInformation(PersonalInformation.builder()
                        .firstName(request.getPersonalInformation().getFirstName())
                        .middleName(request.getPersonalInformation().getMiddleName())
                        .lastName(request.getPersonalInformation().getLastName())
                        .uniqueCivilNumber(request.getPersonalInformation().getUniqueCivilNumber())
                        .build())
                .designation(Designation.valueOf(request.getDesignation()))
                .department(Department.valueOf(request.getDepartment()))
                .contactInformation(ContactInformation.builder()
                        .email(request.getContactInformation().getEmail())
                        .phoneNumber(request.getContactInformation().getPhoneNumber())
                        .build())
                .salary(Salary.builder()
                        .amount(new BigDecimal(request.getSalary().getAmount()))
                        .effectiveDate(Date.from(LocalDate.parse(request.getSalary().getEffectiveDate(), dateFormatter).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build())
                .creation(Creation.builder()
                        .createdAt(Date.from(Instant.now()))
                        .lastModifiedAt(Date.from(Instant.now()))
                        .build())
                .employeeDetails(EmployeeDetails.builder()
                        .address(Address.builder()
                                .city(request.getEmployeeDetails().getAddress().getCity())
                                .street(request.getEmployeeDetails().getAddress().getStreet())
                                .zipCode(request.getEmployeeDetails().getAddress().getZipCode())
                                .build())
                        .gender(Gender.valueOf(request.getEmployeeDetails().getGender()))
                        .maritalStatus(MaritalStatus.valueOf(request.getEmployeeDetails().getMaritalStatus()))
                        .dateOfBirth(Date.from(LocalDate.parse(request.getEmployeeDetails().getDateOfBirth(), dateFormatter).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build())
                .build();
        log.info(employee.toString());

        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Employee with ID {} created successfully", savedEmployee.getId());

        CreateNewEmployeeResponse response = employeeMapper.mapToResponse(savedEmployee);
        log.info("Returning response for created employee: {}", response);

        return response;
    }
}
