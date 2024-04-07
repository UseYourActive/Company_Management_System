package com.tinqin.cms.repositories;

import com.tinqin.cms.entities.Chronology;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface ChronologyRepository extends JpaRepository<Chronology, UUID> {
    @Query(value = "SELECT distinct ch from Chronology ch " +
            "WHERE (:type IS NULL OR ch.log LIKE concat('%',:type,'%')) " +
            "AND ((cast(:createdAfter as localdatetime) IS NULL AND cast(:createdBefore as localdatetime) IS NULL) " +
            "OR (cast(:createdAfter as localdatetime) IS NOT NULL AND cast(:createdBefore as localdatetime) IS NOT NULL AND ch.createdOn >= :createdAfter AND ch.createdOn <= :createdBefore) " +
            "OR (cast(:createdAfter as localdatetime) IS NOT NULL AND cast(:createdBefore as localdatetime) IS NULL AND ch.createdOn >= :createdAfter) " +
            "OR (cast(:createdAfter as localdatetime) IS NULL AND cast(:createdBefore as localdatetime ) IS NOT NULL AND ch.createdOn <= :createdBefore)) ORDER BY ch.createdOn desc")
    Page<Chronology> findChronologyByFilter(OffsetDateTime createdAfter, OffsetDateTime createdBefore, String type, Pageable pageable);

}
