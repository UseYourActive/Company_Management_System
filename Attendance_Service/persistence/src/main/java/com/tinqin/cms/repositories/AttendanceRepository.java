package com.tinqin.cms.repositories;

import com.tinqin.cms.entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
    @Query("SELECT a FROM Attendance a WHERE a.employeeId = :employeeId")
    List<Attendance> findByEmployeeId(UUID employeeId);

    @Query("SELECT a FROM Attendance a WHERE a.employeeId = :employeeId ORDER BY a.checkInTime DESC")
    Optional<Attendance> findLastAttendanceByEmployeeId(UUID employeeId);
}
