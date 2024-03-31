package com.tinqin.cms.repositories;

import com.tinqin.cms.entities.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StorageRepository extends JpaRepository<Storage, UUID> {
    Optional<Storage> findStorageBookByItemId(UUID id);
}
