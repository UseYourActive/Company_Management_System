package com.tinqin.cms.entities;

import com.tinqin.cms.enums.LeaveStatus;
import com.tinqin.cms.enums.LeaveType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "leaves")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "employee_id", nullable = false)
    private UUID employeeId;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "leave_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;
}
