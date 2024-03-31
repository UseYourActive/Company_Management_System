package com.tinqin.cms.repositories;

import com.tinqin.cms.entities.Employee;
import com.tinqin.cms.enums.Department;
import com.tinqin.cms.enums.Designation;
import com.tinqin.cms.enums.Gender;
import com.tinqin.cms.enums.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    //region Salary related
    @Query(value = "SELECT * FROM employees e ORDER BY e.salary_amount DESC LIMIT 3", nativeQuery = true)
    List<Employee> findTopThreeByOrderBySalaryAmountDesc();

    @Query("SELECT e FROM Employee e WHERE e.salary.amount BETWEEN :minSalary AND :maxSalary")
    List<Employee> findEmployeesBySalaryRange(@Param("minSalary") BigDecimal minSalary, @Param("maxSalary") BigDecimal maxSalary);

    @Query("SELECT e FROM Employee e ORDER BY e.salary.amount DESC")
    Optional<Employee> findHighestPaidEmployee();

    @Query("SELECT e FROM Employee e WHERE e.salary.amount > :minSalary")
    List<Employee> findEmployeesWithSalaryGreaterThan(@Param("minSalary") BigDecimal minSalary);

    @Query("SELECT e FROM Employee e WHERE e.designation = :designation ORDER BY e.salary.amount DESC")
    List<Employee> findEmployeesByDesignationOrderBySalary(@Param("designation") Designation designation);
    //endregion

    //region Project related
//    @Query("SELECT e FROM Employee e WHERE NOT EXISTS (SELECT pa FROM ProjectAssignment pa WHERE pa.employee.id = e.id)")
//    List<Employee> findEmployeesWithNoProjectAssignments();
//
//    @Query("SELECT e FROM Employee e JOIN ProjectAssignment pa ON e.id = pa.employee.id WHERE pa.project.id = :projectId")
//    List<Employee> findEmployeesByProjectAssignment(@Param("projectId") UUID projectId);
    //endregion

    //region Name related
    @Query("SELECT e FROM Employee e WHERE e.personalInformation.firstName = :firstName")
    List<Employee> findEmployeesByFirstName(@Param("firstName") String firstName);

    @Query("SELECT e FROM Employee e WHERE e.personalInformation.lastName = :lastName")
    List<Employee> findEmployeesByLastName(@Param("lastName") String lastName);

    @Query("SELECT e FROM Employee e WHERE e.personalInformation.firstName = :firstName AND e.personalInformation.lastName = :lastName")
    List<Employee> findEmployeesByFirstNameAndLastName(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName
    );
    //endregion

    @Query(value = "SELECT e FROM Employee e WHERE e.department = :department")
    List<Employee> findEmployeesByDepartment(@Param("department") Department department);

    @Query("SELECT e FROM Employee e WHERE e.designation = :designation")
    List<Employee> findEmployeesByDesignation(@Param("designation") String designation);

    @Query("SELECT e FROM Employee e WHERE e.department = :department AND e.designation = :designation")
    List<Employee> findEmployeesByDepartmentAndDesignation(
            @Param("department") Department department,
            @Param("designation") String designation
    );

    @Query("SELECT e FROM Employee e WHERE e.contactInformation.phoneNumber = :phoneNumber")
    Optional<Employee> findEmployeeByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("SELECT e FROM Employee e WHERE e.personalInformation.uniqueCivilNumber = :uniqueCivilianNumber")
    Optional<Employee> findEmployeeByUniqueCivilianNumber(@Param("uniqueCivilianNumber") String uniqueCivilianNumber);

    @Query("SELECT e FROM Employee e WHERE e.employeeDetails.dateOfBirth BETWEEN :startDate AND :endDate")
    List<Employee> findEmployeesByDateOfBirthBetween(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    @Query("SELECT e FROM Employee e WHERE e.employeeDetails.maritalStatus = :maritalStatus")
    List<Employee> findEmployeesByEmployeeDetailsMaritalStatus(
            @Param("maritalStatus") MaritalStatus maritalStatus
    );

    @Query("SELECT e FROM Employee e WHERE e.creation.createdAt < :joinDate")
    List<Employee> findEmployeesJoinedBefore(@Param("joinDate") Date joinDate);

    @Query("SELECT e FROM Employee e WHERE e.employeeDetails.gender = :gender")
    List<Employee> findEmployeesByGender(@Param("gender") Gender gender);

    @Query("SELECT e FROM Employee e WHERE e.contactInformation.email = :email")
    Optional<Employee> findEmployeeByEmail(@Param("email") String email);

    @Query("SELECT e FROM Employee e WHERE e.creation.lastModifiedAt = (SELECT MAX(e2.creation.lastModifiedAt) FROM Employee e2)")
    List<Employee> findEmployeesWithLatestModifications();

    @Query("SELECT e FROM Employee e WHERE e.id IN :employeeIds")
    List<Employee> findEmployeesByIds(@Param("employeeIds") List<UUID> employeeIds);

    @Query("SELECT e FROM Employee e ORDER BY e.creation.createdAt")
    List<Employee> findEmployeesOrderedByCreationDate();

    @Query("SELECT e FROM Employee e WHERE e.employeeDetails.address.city = :city")
    List<Employee> findEmployeesByCity(@Param("city") String city);

//    @Query("SELECT e FROM Employee e JOIN ProjectAssignment pa ON e.id = pa.employees " +
//            "WHERE pa.project.id = :projectId AND pa.startDate BETWEEN :startDate AND :endDate")
//    List<Employee> findEmployeesByProjectAssignmentAndDateRange(
//            @Param("projectId") UUID projectId,
//            @Param("startDate") Date startDate,
//            @Param("endDate") Date endDate
//    );

    @Query("SELECT e FROM Employee e WHERE e.employeeDetails.address.zipCode = :zipCode")
    List<Employee> findEmployeesByZipCode(@Param("zipCode") String zipCode);
}
