package com.tinqin.cms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Table(name = "project_assignments")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany
    @JoinColumn(name = "employees", nullable = false)
    private List<Employee> employees;

    @OneToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;
}
