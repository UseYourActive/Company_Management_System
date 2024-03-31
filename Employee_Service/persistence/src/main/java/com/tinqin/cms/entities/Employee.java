package com.tinqin.cms.entities;

import com.tinqin.cms.enums.Department;
import com.tinqin.cms.enums.Designation;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.UUID;

@Entity
@Table(name = "employees")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Embedded
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name")),
            @AttributeOverride(name = "middleName", column = @Column(name = "middle_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "last_name")),
            @AttributeOverride(name = "uniqueCivilNumber", column = @Column(name = "unique_civil_number"))
    })
    private PersonalInformation personalInformation;

    @Embedded
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    @AttributeOverrides({
            @AttributeOverride(name = "email", column = @Column(name = "email")),
            @AttributeOverride(name = "phoneNumber", column = @Column(name = "phone_number"))
    })
    private ContactInformation contactInformation;

    @Embedded
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    @AttributeOverrides({
            @AttributeOverride(name = "dateOfBirth", column = @Column(name = "date_of_birth")),
            @AttributeOverride(name = "gender", column = @Column(name = "gender")),
            @AttributeOverride(name = "address", column = @Column(name = "address")),
            @AttributeOverride(name = "maritalStatus", column = @Column(name = "marital_status"))
    })
    private EmployeeDetails employeeDetails;

    @Enumerated(EnumType.STRING)
    @Column(name = "designation", nullable = false)
    private Designation designation;

    @Enumerated(EnumType.STRING)
    @Column(name = "department", nullable = false)
    private Department department;

    @Embedded
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount")),
            @AttributeOverride(name = "effectiveDate", column = @Column(name = "effective_date"))
    })
    private Salary salary;

    @Embedded
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    @AttributeOverrides({
            @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
            @AttributeOverride(name = "lastModifiedAt", column = @Column(name = "last_modified_at"))
    })
    private Creation creation;
}
