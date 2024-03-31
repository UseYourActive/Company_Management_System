package com.tinqin.cms.processors;

import com.tinqin.cms.operations.DeleteStoragesByIdsOperation;
import com.tinqin.cms.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class DeleteStorageBooksByIdsOperationProcessor implements DeleteStoragesByIdsOperation {
    private final StorageRepository storageBookRepository;

    @Override
    public DeleteStoragesByIdsResponse process(final DeleteStoragesByIdsRequest request) {
        List<String> ids = request.getIds();

        List<UUID> uuidList = ids.stream()
                .map(UUID::fromString)
                .toList();
        log.info("Deleting storages with IDs: {}", uuidList);

        storageBookRepository.deleteAllById(uuidList);
        log.info("Deleted storages with IDs: {}", uuidList);

        return DeleteStoragesByIdsResponse.builder()
                .status("Deleting operation complete")
                .build();
    }
}
