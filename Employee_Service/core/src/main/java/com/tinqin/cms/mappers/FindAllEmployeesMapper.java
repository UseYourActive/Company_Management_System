package com.tinqin.cms.mappers;

import com.tinqin.cms.entities.Employee;
import com.tinqin.cms.operations.FindAllEmployeesOperation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FindAllEmployeesMapper {
    FindAllEmployeesMapper INSTANCE = Mappers.getMapper(FindAllEmployeesMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "personalInformation.firstName", target = "personalInformation.firstName")
    @Mapping(source = "personalInformation.middleName", target = "personalInformation.middleName")
    @Mapping(source = "personalInformation.lastName", target = "personalInformation.lastName")
    @Mapping(source = "personalInformation.uniqueCivilNumber", target = "personalInformation.uniqueCivilNumber")
    @Mapping(source = "designation", target = "designation")
    @Mapping(source = "department", target = "department")
    @Mapping(source = "contactInformation.phoneNumber", target = "contactInformation.phoneNumber")
    @Mapping(source = "contactInformation.email", target = "contactInformation.email")
    @Mapping(source = "salary.amount", target = "salary.amount")
    @Mapping(source = "salary.effectiveDate", target = "salary.effectiveDate")
    @Mapping(source = "creation.createdAt", target = "creation.createdAt")
    @Mapping(source = "creation.lastModifiedAt", target = "creation.lastModifiedAt")
    @Mapping(source = "employeeDetails.dateOfBirth", target = "employeeDetails.dateOfBirth")
    @Mapping(source = "employeeDetails.gender", target = "employeeDetails.gender")
    @Mapping(source = "employeeDetails.maritalStatus", target = "employeeDetails.maritalStatus")
    @Mapping(source = "employeeDetails.address.city", target = "employeeDetails.address.city")
    @Mapping(source = "employeeDetails.address.street", target = "employeeDetails.address.street")
    @Mapping(source = "employeeDetails.address.zipCode", target = "employeeDetails.address.zipCode")
    FindAllEmployeesOperation.FindAllEmployeesResponseDTO mapToResponse(Employee employee);
}

