package com.tinqin.cms.mappers;

import com.tinqin.cms.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static com.tinqin.cms.operations.CreateNewEmployeeOperation.*;

@Mapper(componentModel = "spring")
public interface CreateNewEmployeeMapper {
    CreateNewEmployeeMapper INSTANCE = Mappers.getMapper(CreateNewEmployeeMapper.class);

    @Mapping(source = "contactInformation.email", target = "contactInformation.email")
    @Mapping(source = "contactInformation.phoneNumber", target = "contactInformation.phoneNumber")
    @Mapping(source = "salary.amount", target = "salary.amount")
    @Mapping(source = "salary.effectiveDate", target = "salary.effectiveDate")
    @Mapping(source = "employeeDetails.address", target = "employeeDetails.address")
    @Mapping(source = "employeeDetails.dateOfBirth", target = "employeeDetails.dateOfBirth")
    @Mapping(source = "employeeDetails.gender", target = "employeeDetails.gender")
    @Mapping(source = "employeeDetails.maritalStatus", target = "employeeDetails.maritalStatus")
    Employee mapToEntity(CreateNewEmployeeRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "personalInformation.firstName", target = "personalInformation.firstName")
    @Mapping(source = "personalInformation.middleName", target = "personalInformation.middleName")
    @Mapping(source = "personalInformation.lastName", target = "personalInformation.lastName")
    @Mapping(source = "personalInformation.uniqueCivilNumber", target = "personalInformation.uniqueCivilNumber")
    @Mapping(source = "designation", target = "designation")
    @Mapping(source = "department", target = "department")
    @Mapping(source = "contactInformation.email", target = "contactInformation.email")
    @Mapping(source = "contactInformation.phoneNumber", target = "contactInformation.phoneNumber")
    @Mapping(source = "salary.amount", target = "salary.amount")
    @Mapping(source = "salary.effectiveDate", target = "salary.effectiveDate")
    @Mapping(source = "creation.createdAt", target = "creation.createdAt")
    @Mapping(source = "creation.lastModifiedAt", target = "creation.lastModifiedAt")
    @Mapping(source = "employeeDetails.address", target = "employeeDetails.address")
    @Mapping(source = "employeeDetails.dateOfBirth", target = "employeeDetails.dateOfBirth")
    @Mapping(source = "employeeDetails.gender", target = "employeeDetails.gender")
    @Mapping(source = "employeeDetails.maritalStatus", target = "employeeDetails.maritalStatus")
    CreateNewEmployeeResponse mapToResponse(Employee savedEmployee);
}
