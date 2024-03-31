package com.tinqin.cms.repositories;

import com.tinqin.cms.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {

}
