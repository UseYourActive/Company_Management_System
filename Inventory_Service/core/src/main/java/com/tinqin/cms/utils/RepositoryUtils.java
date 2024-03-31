package com.tinqin.cms.utils;

import com.tinqin.cms.entities.Storage;
import com.tinqin.cms.repositories.StorageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RepositoryUtils {
//    public <T, ID> T findByIdOrThrow(CrudRepository<T, ID> repository, ID id, String entityName) {
//        return repository.findById(id)
//                .orElseThrow(() -> {
//                    log.error("{} not found for ID: {}", entityName, id);
//                    return new EntityNotFoundException(entityName);
//                });
//    }

    public Storage findByStorageBookIdOrThrow(StorageRepository repository, UUID id, String entityName) {
        return repository.findStorageBookByItemId(id)
                .orElseThrow(() -> {
            log.error("{} not found for ID: {}", entityName, id);
            return new EntityNotFoundException(entityName);
        });
    }
}
