package com.tinqin.cms.entities;

import com.tinqin.cms.enums.Gender;
import com.tinqin.cms.enums.MaritalStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class EmployeeDetails {
    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "address", nullable = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "street")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "zip_code"))
    })
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status", nullable = false)
    private MaritalStatus maritalStatus;
}
