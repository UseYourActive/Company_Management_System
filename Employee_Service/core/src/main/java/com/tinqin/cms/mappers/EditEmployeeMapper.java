package com.tinqin.cms.mappers;

import com.tinqin.cms.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static com.tinqin.cms.operations.EditEmployeeOperation.*;

@Mapper(componentModel = "spring")
public interface EditEmployeeMapper {
    EditEmployeeMapper INSTANCE = Mappers.getMapper(EditEmployeeMapper.class);

    EditEmployeeResponse mapToResponse(Employee employee);
}
