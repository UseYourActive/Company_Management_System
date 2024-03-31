package com.tinqin.cms.repositories;

import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.enums.LeaveStatus;
import com.tinqin.cms.enums.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeaveRepository extends JpaRepository<Leave, UUID> {
    @Query("SELECT l FROM Leave l WHERE l.employeeId = :employeeId")
    List<Leave> findByEmployeeId(@Param("employeeId") UUID employeeId);

    @Query("SELECT l FROM Leave l WHERE l.startDate BETWEEN :startDate AND :endDate")
    List<Leave> findByStartDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT l FROM Leave l WHERE l.employeeId = :employeeId AND l.startDate BETWEEN :startDate AND :endDate")
    List<Leave> findLeavesByEmployeeIdAndDateRange(@Param("employeeId") UUID employeeId,
                                                   @Param("startDate") LocalDate startDate,
                                                   @Param("endDate") LocalDate endDate);

    @Query("SELECT l FROM Leave l WHERE l.status = :status")
    List<Leave> findByStatus(@Param("status") LeaveStatus status);

    @Query("SELECT l FROM Leave l WHERE l.employeeId = :employeeId AND l.status = :status")
    List<Leave> findByEmployeeIdAndStatus(@Param("employeeId") UUID employeeId, @Param("status") LeaveStatus status);

    @Query("SELECT COUNT(l) FROM Leave l WHERE l.employeeId = :employeeId")
    Long countByEmployeeId(@Param("employeeId") UUID employeeId);

    @Query("SELECT COUNT(l) FROM Leave l WHERE l.employeeId = :employeeId AND l.leaveType = :leaveType")
    Long countByEmployeeIdAndLeaveType(@Param("employeeId") UUID employeeId, @Param("leaveType") LeaveType leaveType);

    @Query("SELECT l FROM Leave l WHERE l.leaveType = :leaveType")
    List<Leave> findByLeaveType(@Param("leaveType") LeaveType leaveType);

    @Query("SELECT l FROM Leave l WHERE l.leaveType = :leaveType AND l.status = :status")
    List<Leave> findByLeaveTypeAndStatus(@Param("leaveType") LeaveType leaveType, @Param("status") LeaveStatus status);

    @Query("SELECT l FROM Leave l WHERE l.employeeId = :employeeId AND l.status != :status")
    List<Leave> findByEmployeeIdAndStatusNot(@Param("employeeId") UUID employeeId, @Param("status") LeaveStatus status);

    @Query("SELECT l FROM Leave l WHERE l.id = :leaveId AND l.employeeId = :employeeId")
    Optional<Leave> findByIdAndEmployeeId(@Param("leaveId") UUID leaveId, @Param("employeeId") UUID employeeId);

    @Query("SELECT l FROM Leave l WHERE l.status = :status AND l.startDate < :startDate")
    List<Leave> findByStatusAndStartDateBefore(@Param("status") LeaveStatus status, @Param("startDate") LocalDate startDate);

    @Query("SELECT l FROM Leave l WHERE l.employeeId = :employeeId AND l.startDate > :startDate")
    List<Leave> findByEmployeeIdAndStartDateAfter(@Param("employeeId") UUID employeeId, @Param("startDate") LocalDate startDate);

    @Query("SELECT l FROM Leave l WHERE l.employeeId = :employeeId AND l.endDate < :endDate")
    List<Leave> findByEmployeeIdAndEndDateBefore(@Param("employeeId") UUID employeeId, @Param("endDate") LocalDate endDate);

    @Query("SELECT l FROM Leave l WHERE l.employeeId = :employeeId AND l.endDate > :endDate")
    List<Leave> findByEmployeeIdAndEndDateAfter(@Param("employeeId") UUID employeeId, @Param("endDate") LocalDate endDate);

    @Query("SELECT l FROM Leave l WHERE l.employeeId = :employeeId AND l.startDate >= :startDate AND l.endDate <= :endDate")
    List<Leave> findByEmployeeIdAndDateRange(@Param("employeeId") UUID employeeId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT l FROM Leave l WHERE l.status = :status AND l.employeeId = :employeeId AND l.startDate >= :startDate AND l.endDate <= :endDate")
    List<Leave> findByStatusAndEmployeeIdAndDateRange(@Param("status") LeaveStatus status, @Param("employeeId") UUID employeeId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT l FROM Leave l WHERE l.employeeId = :employeeId ORDER BY l.startDate DESC")
    List<Leave> findByEmployeeIdOrderByStartDateDesc(@Param("employeeId") UUID employeeId);

    @Query("SELECT l FROM Leave l WHERE l.status = 'PENDING' ORDER BY l.startDate ASC")
    List<Leave> findPendingLeavesOrderByStartDateAsc();

    @Query("SELECT l FROM Leave l WHERE l.leaveType = :leaveType AND l.startDate > :startDate")
    List<Leave> findByLeaveTypeAndStartDateAfter(@Param("leaveType") LeaveType leaveType, @Param("startDate") LocalDate startDate);

    @Query("SELECT l FROM Leave l WHERE l.leaveType = :leaveType AND l.endDate < :endDate")
    List<Leave> findByLeaveTypeAndEndDateBefore(@Param("leaveType") LeaveType leaveType, @Param("endDate") LocalDate endDate);

    @Query("SELECT l FROM Leave l WHERE l.leaveType = :leaveType AND l.status = :status AND l.startDate < :startDate")
    List<Leave> findByLeaveTypeAndStatusAndStartDateBefore(@Param("leaveType") LeaveType leaveType, @Param("status") LeaveStatus status, @Param("startDate") LocalDate startDate);

    @Query("SELECT l FROM Leave l WHERE l.leaveType = :leaveType AND l.status = :status AND l.endDate > :endDate")
    List<Leave> findByLeaveTypeAndStatusAndEndDateAfter(@Param("leaveType") LeaveType leaveType, @Param("status") LeaveStatus status, @Param("endDate") LocalDate endDate);
}
